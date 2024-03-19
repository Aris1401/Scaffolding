package scaffolding.database;

import scaffolding.loader.ScaffoldLoader;
import scaffolding.templates.processor.IScaffoldProcessTemplate;

import java.util.ArrayList;
import java.util.Properties;

public class ScaffoldDatabaseTableInfo {
    static final String IMPORTS_CONFIG_PROPERTIES = "scaffold.column.import.";
    static final String COLUMN_TYPE_CONFIG_PROPERTIES = "scaffold.column.type.";

    String columnName;
    String columnType;
    public boolean isForeignKey = false;
    String foreignKeyTo;
    public String language;

    public ScaffoldDatabaseTableInfo() {

    }

    public ScaffoldDatabaseTableInfo(String columnName, String columnType) {
        setColumnName(columnName);
        setColumnType(columnType);
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

    public void setIsForeignKey(String foreignKeyTo) {
        isForeignKey = true;
        this.foreignKeyTo = foreignKeyTo;
    }

    public String getForeignKeyTo() {
        return IScaffoldProcessTemplate.processModelName(foreignKeyTo);
    }
}
