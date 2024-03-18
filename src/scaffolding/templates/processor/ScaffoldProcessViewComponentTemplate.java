package scaffolding.templates.processor;

import java.util.ArrayList;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.database.ScaffoldDatabaseInfomations;

public class ScaffoldProcessViewComponentTemplate extends IScaffoldProcessTemplate{
    public static final String VIEWCOMPONENT_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.component.path.";
    public ScaffoldProcessViewComponentTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields,String modelName,String language){
        currentTemplatePath = VIEWCOMPONENT_TEMPLATE_PATH_CONFIG_PREFIX;

        storeStringVariable("class_name_camel", pascalCase(modelName));
        storeStringVariable("class_name_lower", modelName);

        // Obtenir primary key
        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);
        primaryKey.language = language;
        storeObjectVariable("primary_key", primaryKey);
    }
}
