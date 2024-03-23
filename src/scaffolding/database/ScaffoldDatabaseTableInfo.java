package scaffolding.database;

import scaffolding.loader.ScaffoldLoader;
import scaffolding.templates.processor.IScaffoldProcessTemplate;

import java.util.ArrayList;
import java.util.Properties;

public class ScaffoldDatabaseTableInfo {
    static final String IMPORTS_CONFIG_PROPERTIES = "scaffold.column.import.";
    static final String COLUMN_TYPE_CONFIG_PROPERTIES = "scaffold.column.type.";

    String fromModel;
    String columnName;
    String columnType;
    public boolean isForeignKey = false;
    String foreignKeyTo;
    String foreignKeyId;
    public String language;

    public ScaffoldDatabaseTableInfo() {

    }

    public ScaffoldDatabaseTableInfo(String columnName, String columnType) {
        setColumnName(columnName);
        setColumnType(columnType);
    }

    public void setFromModel(String model) {
        this.fromModel = model;
    }

    public String getFromModel() {
        return this.fromModel;
    }

    public String getColumnName() {
        return IScaffoldProcessTemplate.processModelName(columnName);
    }
    public String getRawColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }
    public String getColumnTypePascal() {return IScaffoldProcessTemplate.pascalCase(IScaffoldProcessTemplate.processModelName(getColumnType()));}
    public String getColumnTypeProcessed() {return (IScaffoldProcessTemplate.processModelName(getColumnType()));}

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
    public String getColumnNamePascal() {
        return IScaffoldProcessTemplate.pascalCase(getColumnName());
    }

    public String getLanguageType() {
        // Obtenir les configuration
        Properties columnTypesProperties = ScaffoldLoader.getColumnTypesProperties();

        String currentColumnType = columnTypesProperties.getProperty(COLUMN_TYPE_CONFIG_PROPERTIES + getColumnType().replace(" ", "") + "." + language);
        return currentColumnType == null ? getColumnType() : currentColumnType;
    }
    // Getting the imports needed

    public static ArrayList<String> neededImportsForLanguage(String language, ArrayList<ScaffoldDatabaseTableInfo> tableInfos) {
        ArrayList<String> imports = new ArrayList<>();

        // Obtenir les configuration
        Properties columnTypesProperties = ScaffoldLoader.getColumnTypesProperties();
        for (ScaffoldDatabaseTableInfo databaseTableInfo : tableInfos) {
            String imported = columnTypesProperties.getProperty(IMPORTS_CONFIG_PROPERTIES + databaseTableInfo.getColumnType().toLowerCase().replace(" ", "") + "." + language);
            if (imported != null) imports.add(imported);
        }

        return imports;
    }

    public static void addLanguagesFor(String language, ArrayList<ScaffoldDatabaseTableInfo> infos) {
        for (ScaffoldDatabaseTableInfo info : infos) {
            info.language = language;
        }
    }

    public void setIsForeignKey(String foreignKeyTo, String foreignKeyId) {
        isForeignKey = true;
        this.foreignKeyTo = foreignKeyTo;
        this.foreignKeyId = foreignKeyId;
    }

    public String getForeignKeyTo() {
        return IScaffoldProcessTemplate.processModelName(foreignKeyTo);
    }
    public String getForeignKeyId() { return IScaffoldProcessTemplate.processModelName(foreignKeyId); }

    public String getForeignKeyFirstStringColumn() {
        ArrayList<ScaffoldDatabaseTableInfo> fields = ScaffoldDatabaseInfomations.getInstance().getColumns(columnType);
        ScaffoldDatabaseTableInfo.addLanguagesFor("java", fields);

        // Getting the first string
        String stringColumn = "";
        for (ScaffoldDatabaseTableInfo field : fields) {
            if (field.getLanguageType().equalsIgnoreCase("string")) {
                System.out.println("Field: " + field.getLanguageType() + " | " + field.getLanguageType());
                stringColumn = field.getColumnName();
                break;
            }
        }

        return stringColumn;
    }
}
