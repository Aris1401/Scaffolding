package scaffolding.database;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.loader.ScaffoldLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ScaffoldDatabaseInfomations {
    private ScaffoldingArguments scaffoldingArguments;

    public void setScaffoldingArguments(ScaffoldingArguments scaffoldingArguments) {
        this.scaffoldingArguments = scaffoldingArguments;
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
}
