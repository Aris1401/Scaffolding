package scaffolding.templates.processor;

import java.util.ArrayList;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;

public class ScaffoldProcessViewHTMLTemplate extends IScaffoldProcessTemplate {
    public static final String VIEWHTML_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.html.path.";
    public ScaffoldProcessViewHTMLTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields,String modelName){
        currentTemplatePath = VIEWHTML_TEMPLATE_PATH_CONFIG_PREFIX;

        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);

        ArrayList<ScaffoldDatabaseTableInfo> availableFields = new ArrayList<>();
        // Enlever la clee primaire de la liste
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getRawColumnName().equalsIgnoreCase(primaryKey.getRawColumnName())) continue;
            else availableFields.add(fields.get(i));
        }

        storeListVariable("fields", fields);
        storeListVariable("a_fields", availableFields);

        storeStringVariable("class_name_lower", modelName);
        storeStringVariable("class_name_camel", pascalCase(modelName));

        System.out.println("PK: " + primaryKey.getColumnName());
        if (primaryKey.getRawColumnName() != null)
            storeObjectVariable("primary_key", primaryKey);
    }
}
