package scaffolding.templates.processor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.database.ScaffoldDatabaseInfomations;

public class ScaffoldProcessViewComponentTemplate extends IScaffoldProcessTemplate{
    public static final String VIEWCOMPONENT_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.component.path.";
    public ScaffoldProcessViewComponentTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields, String modelName, String language, ScaffoldDatabaseInfomations scaffoldDatabaseInfomations){
        currentTemplatePath = VIEWCOMPONENT_TEMPLATE_PATH_CONFIG_PREFIX;

        ArrayList<ScaffoldDatabaseTableInfo> foreignKeys = scaffoldDatabaseInfomations.getTableForeignKeys(modelName);

        storeStringVariable("class_name_camel", pascalCase(modelName));
        storeStringVariable("class_name_lower", modelName);
        storeStringVariable("class_name", pascalCase(modelName));

        storeListVariable("foreign_keys", foreignKeys);

        // Obtenir primary key
        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);
        primaryKey.language = language;

        storeObjectVariable("primary_key", primaryKey);
    }
}
