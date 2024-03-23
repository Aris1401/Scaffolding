package scaffolding.templates.processor;

import java.util.ArrayList;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;

public class ScaffoldProcessViewHTMLTemplate extends IScaffoldProcessTemplate {
    public ScaffoldProcessViewHTMLTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields, String modelName){
        // Assigner language
        ScaffoldDatabaseTableInfo.addLanguagesFor("html", fields);

        // Database informatiions
        ScaffoldDatabaseInfomations databaseInfomations = ScaffoldDatabaseInfomations.getInstance();

        // Getting the primary keys
        ScaffoldDatabaseTableInfo primaryKey = databaseInfomations.getTablePrimaryKey(modelName);

        // Obtenir les foreign key
        ArrayList<ScaffoldDatabaseTableInfo> foreignKeys = databaseInfomations.getTableForeignKeys(modelName);

        ArrayList<ScaffoldDatabaseTableInfo> availableFields = new ArrayList<>();
        // Enlever la clee primaire de la liste
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getRawColumnName().equalsIgnoreCase(primaryKey.getRawColumnName())) continue;
            if (ScaffoldDatabaseInfomations.checkIfColumnIsPrimaryKey(fields.get(i).getColumnName(), foreignKeys) != null) continue;
            else availableFields.add(fields.get(i));
        }

        storeListVariable("fields", fields);
        storeListVariable("a_fields", availableFields);
        storeListVariable("foreign_keys", foreignKeys);

        storeStringVariable("class_name_lower", modelName);
        storeStringVariable("class_name_camel", pascalCase(modelName));

        System.out.println("PK: " + primaryKey.getColumnName());
        if (primaryKey.getRawColumnName() != null)
            storeObjectVariable("primary_key", primaryKey);
    }

    @Override
    public String getCurrentTemplatePath() {
        return "scaffold.templates.view.html.path.";
    }
}
