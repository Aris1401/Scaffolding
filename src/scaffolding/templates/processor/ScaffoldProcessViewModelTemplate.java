package scaffolding.templates.processor;

import java.util.ArrayList;
import scaffolding.database.ScaffoldDatabaseTableInfo;

public class ScaffoldProcessViewModelTemplate extends IScaffoldProcessTemplate{
    public static final String VIEWMODEL_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.view.model.path.";
    public ScaffoldProcessViewModelTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields,String modelName){
        currentTemplatePath = VIEWMODEL_TEMPLATE_PATH_CONFIG_PREFIX;

        storeListVariable("fields", fields);

        storeStringVariable("class_name_camel", pascalCase(modelName));
    }
}
