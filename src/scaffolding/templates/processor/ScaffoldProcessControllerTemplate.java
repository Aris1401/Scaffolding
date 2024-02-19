package scaffolding.templates.processor;

import scaffolding.database.ScaffoldDatabaseTableInfo;

import java.util.ArrayList;

public class ScaffoldProcessControllerTemplate extends IScaffoldProcessTemplate {
    public static final String CONTROLLER_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.controller.path.";

    public ScaffoldProcessControllerTemplate(String modelName, String controllerPackage, String modelPackage) {
        currentTemplatePath = CONTROLLER_TEMPLATE_PATH_CONFIG_PREFIX;

        ArrayList<String> imports = new ArrayList<>();
        imports.add(modelPackage + "." + pascalCase(modelName));

        // Storing the variable
        storeStringVariable("class_name", pascalCase(modelName));
        storeStringVariable("package", controllerPackage);

        storeListVariable("imports", imports);

    }
}
