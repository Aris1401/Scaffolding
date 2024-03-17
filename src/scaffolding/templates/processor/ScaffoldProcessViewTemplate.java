package scaffolding.templates.processor;

import java.util.ArrayList;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.database.ScaffoldDatabaseInfomations;

public class ScaffoldProcessViewTemplate extends IScaffoldProcessTemplate{
    public static final String VIEW_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.path.";

    public ScaffoldProcessViewTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields,String modelName,String language){
        currentTemplatePath = VIEW_TEMPLATE_PATH_CONFIG_PREFIX;

        storeListVariable("fields", fields);
        storeStringVariable("class_name_camel", pascalCase(modelName));
        storeStringVariable("class_name_lower", modelName);

        // Obtenir primary key
        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);
        primaryKey.language = language;
        storeObjectVariable("primary_key", primaryKey);
    }
}
