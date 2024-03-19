package scaffolding.templates.processor;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;

public class ScaffoldProcessViewServiceTemplate extends IScaffoldProcessTemplate {
    public static final String VIEWSERVICE_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.servicecomponent.path.";

    public ScaffoldProcessViewServiceTemplate(String modelName, String language){
        currentTemplatePath = VIEWSERVICE_TEMPLATE_PATH_CONFIG_PREFIX;

        storeStringVariable("class_name_camel", pascalCase(modelName));
        storeStringVariable("class_name_lower", modelName);
        storeStringVariable("class_name", pascalCase(modelName));

        // Obtenir primary key
        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);
        primaryKey.language = language;
        storeObjectVariable("primary_key", primaryKey);
    }
}
