package scaffolding.database;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.loader.ScaffoldLoader;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class ScaffoldDatabaseInfomations {
    // Singleton
    private static ScaffoldDatabaseInfomations instance;

    public static ScaffoldDatabaseInfomations getInstance(ScaffoldingArguments scaffoldingArguments) {
        if (instance == null) {
            instance = new ScaffoldDatabaseInfomations();
            instance.setScaffoldingArguments(scaffoldingArguments);
        }

        return instance;
    }

    public static ScaffoldDatabaseInfomations getInstance() {
        return instance;
    }
    private ScaffoldingArguments scaffoldingArguments;

    public void setScaffoldingArguments(ScaffoldingArguments scaffoldingArguments) {
        this.scaffoldingArguments = scaffoldingArguments;
    }

    public static ArrayList<String> getAllAvailabeDatabaseConnections() {
        Properties databaseProperties = ScaffoldLoader.getDatabaseProperties();

        Set<String> databases = new HashSet<>();
        for (String databaseName : databaseProperties.stringPropertyNames()) {
            try {
                String databasePropertyName = databaseName.strip();
                String[] splittedProperties = databasePropertyName.split("\\.");

                String database = splittedProperties[2];
                database = database.substring(0, 1).toUpperCase() + database.substring(1);
                databases.add(database);
            } catch (Exception e) {
                continue;
            }
        }

        return (ArrayList<String>) databases.stream().collect(Collectors.toList());
    }

    public Connection loadConnection() {
        Connection c = null;

        // Loading the properties
        Properties databaseProperties = ScaffoldLoader.getDatabaseProperties();

        String dbUrl = databaseProperties.getProperty("database.url." + scaffoldingArguments.getDatabase());
        String dbDriver = databaseProperties.getProperty("database.driver." + scaffoldingArguments.getDatabase());

        // Replacing varialbes
        String hostVariable = ScaffoldGenerateCode.getVariable("host");
        dbUrl = dbUrl.replace(hostVariable, scaffoldingArguments.getHost());

        // Adding the selected database
        dbUrl += scaffoldingArguments.getDatabaseName();

        try {
            Class.forName(dbDriver);
            c = DriverManager.getConnection(dbUrl, scaffoldingArguments.getUser(), scaffoldingArguments.getPassword());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return c;
    }

    public String[] getDatabaseTableNames() {
        // Si il a specifier les models
        if (scaffoldingArguments.getModels() != null && scaffoldingArguments.getModels().length != 0) {
            return scaffoldingArguments.getModels();
        }

        Connection c = loadConnection();

        // Obtenir les noms des tables dans la base
        ArrayList<String> tableNames = new ArrayList<>();
        String tableNamesQuery = "SELECT table_name as name FROM information_schema.tables where table_schema = 'public'";
        try {
            PreparedStatement statement = c.prepareStatement(tableNamesQuery);
            ResultSet tableNamesRes = statement.executeQuery();

            while (tableNamesRes.next()) {
                tableNames.add(tableNamesRes.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tableNames.toArray(new String[tableNames.size()]);
    }

    public ArrayList<ScaffoldDatabaseTableInfo> getColumns(String table) {
        Connection c = loadConnection();

        ArrayList<ScaffoldDatabaseTableInfo> columnNameAndType = new ArrayList<ScaffoldDatabaseTableInfo>();
        String columnNameAndTypeQuery = "SELECT column_name as name, data_type as type FROM information_schema.columns WHERE table_schema = 'public' and table_name = ?";
        try {
            PreparedStatement statement = c.prepareStatement(columnNameAndTypeQuery);
            statement.setString(1, table);

            ResultSet columnNameAndTypeRes = statement.executeQuery();
            while (columnNameAndTypeRes.next()) {
                columnNameAndType.add(new ScaffoldDatabaseTableInfo(columnNameAndTypeRes.getString("name"), columnNameAndTypeRes.getString("type")));
            }

            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return columnNameAndType;
    }

    public ScaffoldDatabaseTableInfo getTablePrimaryKey(String table) {
        Connection c = loadConnection();

        ScaffoldDatabaseTableInfo databaseTableInfo = new ScaffoldDatabaseTableInfo();

        String columnNameAndTypeQuery = "SELECT column_name AS name, data_type AS type FROM information_schema.columns WHERE table_schema = 'public' AND table_name = ? AND column_name IN (SELECT column_name FROM information_schema.key_column_usage WHERE table_schema = 'public' AND table_name = ? AND constraint_name = (SELECT constraint_name FROM information_schema.table_constraints WHERE table_name = ? AND constraint_type = 'PRIMARY KEY'));";
        try {
            PreparedStatement statement = c.prepareStatement(columnNameAndTypeQuery);
            statement.setString(1, table);
            statement.setString(2, table);
            statement.setString(3, table);

            ResultSet columnNameAndTypeRes = statement.executeQuery();
            while (columnNameAndTypeRes.next()) {
                databaseTableInfo = new ScaffoldDatabaseTableInfo(columnNameAndTypeRes.getString("name"), columnNameAndTypeRes.getString("type"));
            }

            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return databaseTableInfo;
    }
    public ArrayList<ScaffoldDatabaseTableInfo> getTableForeignKeys(String tableName) {
        Connection connection = loadConnection();
        ArrayList<ScaffoldDatabaseTableInfo> infos = new ArrayList<>();

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getImportedKeys(null, null, tableName);

            while (resultSet.next()) {
                ScaffoldDatabaseTableInfo databaseTableInfo = new ScaffoldDatabaseTableInfo();

                String fkTableName = resultSet.getString("FKTABLE_NAME");
                String fkColumnName = resultSet.getString("FKCOLUMN_NAME");
                // Assuming correct column name for type is "PKTABLE_NAME"
                String pkTableName = resultSet.getString("PKTABLE_NAME");
                String pkTableId = resultSet.getString("PKCOLUMN_NAME");

                databaseTableInfo = new ScaffoldDatabaseTableInfo(fkColumnName, pkTableName);
                databaseTableInfo.setIsForeignKey(fkTableName, pkTableId);

                infos.add(databaseTableInfo);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return infos;
    }

    public static ScaffoldDatabaseTableInfo checkIfColumnIsPrimaryKey(String columnName, ArrayList<ScaffoldDatabaseTableInfo> knownForeignKeys) {
        for (ScaffoldDatabaseTableInfo foreignKey : knownForeignKeys) {
            if (columnName.equalsIgnoreCase(foreignKey.getColumnName())) {
                return foreignKey;
            }
        }

        return null;
    }
}
