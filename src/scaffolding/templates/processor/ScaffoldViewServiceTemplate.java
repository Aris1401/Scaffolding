package scaffolding.templates.processor;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;

public class ScaffoldViewServiceTemplate extends IScaffoldProcessTemplate {
    public static final String VIEWSERVICE_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.service.path.";

    public ScaffoldViewServiceTemplate(String modelName,String language){
        

        storeStringVariable("class_name_camel", pascalCase(modelName));
        storeStringVariable("class_name_lower", modelName);

        // Obtenir primary key
        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);
        primaryKey.language = language;
        storeObjectVariable("primary_key", primaryKey);
    }
}
