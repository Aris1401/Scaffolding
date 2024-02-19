package scaffolding.templates.processor;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;

import java.util.ArrayList;

public class ScaffoldProcessModelTemplate extends IScaffoldProcessTemplate{
    public static final String MODEL_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.model.path.";

    public ScaffoldProcessModelTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields, String modelName, String modelPackage, String language) {
        currentTemplatePath = MODEL_TEMPLATE_PATH_CONFIG_PREFIX;

        // Storing the variables
        storeListVariable("fields", fields);
        ArrayList<String> imports = ScaffoldDatabaseTableInfo.neededImportsForLanguage(language, fields);
        storeListVariable("imports", imports);
        storeStringVariable("namespace", modelPackage);
        storeStringVariable("class_name", pascalCase(modelName));

        // Obtenir primary key
        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);
        primaryKey.language = language;
        storeObjectVariable("primary_key", primaryKey);
    }
}
