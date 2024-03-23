package scaffolding.templates.processor;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;

import java.util.ArrayList;

public class ScaffoldProcessModelTemplate extends IScaffoldProcessTemplate{

    public ScaffoldProcessModelTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields, ArrayList<ScaffoldDatabaseTableInfo> foreignKeys, String modelName, String modelPackage, String language) {
        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);

        // Getting the fields to not print the primary key
        ArrayList<ScaffoldDatabaseTableInfo> fieldsFiltered = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getRawColumnName().equalsIgnoreCase(primaryKey.getRawColumnName())) continue;
            else fieldsFiltered.add(fields.get(i));
        }

        // Storing the variables
        storeListVariable("fields", fieldsFiltered);
        storeListVariable("foreign_keys", foreignKeys);
        ArrayList<String> imports = ScaffoldDatabaseTableInfo.neededImportsForLanguage(language, fields);
        storeListVariable("imports", imports);
        storeStringVariable("namespace", modelPackage);
        storeStringVariable("class_name", pascalCase(processModelName(modelName)));

        // Obtenir primary key
        primaryKey.language = language;
        System.out.println("PK: " + primaryKey.getColumnName());
        if (primaryKey.getRawColumnName() != null)
            storeObjectVariable("primary_key", primaryKey);
    }

    @Override
    public String getCurrentTemplatePath() {
        return "scaffold.templates.model.path.";
    }
}
