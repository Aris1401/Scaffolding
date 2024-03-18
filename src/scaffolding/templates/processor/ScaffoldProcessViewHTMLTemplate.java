package scaffolding.templates.processor;

import java.util.ArrayList;
import scaffolding.database.ScaffoldDatabaseTableInfo;

public class ScaffoldProcessViewHTMLTemplate extends IScaffoldProcessTemplate {
    public static final String VIEWHTML_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.html.path.";
    public ScaffoldProcessViewHTMLTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields,String modelName){
        currentTemplatePath = VIEWHTML_TEMPLATE_PATH_CONFIG_PREFIX;

        storeListVariable("fields", fields);

        storeStringVariable("class_name_lower", modelName);
        storeStringVariable("class_name_camel", pascalCase(modelName));
    }
}
