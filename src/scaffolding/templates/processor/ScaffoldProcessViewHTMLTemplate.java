package scaffolding.templates.processor;

import java.util.ArrayList;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;

public class ScaffoldProcessViewHTMLTemplate extends IScaffoldProcessTemplate {
    public static final String VIEWHTML_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.html.path.";
    public ScaffoldProcessViewHTMLTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields, String modelName, ScaffoldDatabaseInfomations scaffoldDatabaseInfomations){
        currentTemplatePath = VIEWHTML_TEMPLATE_PATH_CONFIG_PREFIX;

        // Assigner language
        ScaffoldDatabaseTableInfo.addLanguagesFor("html", fields);

        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);

        // Obtenir les foreign key
        ArrayList<ScaffoldDatabaseTableInfo> foreignKeys = scaffoldDatabaseInfomations.getTableForeignKeys(modelName);

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
}
