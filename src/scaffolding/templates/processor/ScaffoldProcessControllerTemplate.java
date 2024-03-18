package scaffolding.templates.processor;

import scaffolding.database.ScaffoldDatabaseTableInfo;

import java.util.ArrayList;

public class ScaffoldProcessControllerTemplate extends IScaffoldProcessTemplate {
    public static final String CONTROLLER_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.controller.path.";

    public ScaffoldProcessControllerTemplate(String modelName, String controllerPackage, String modelPackage, String servicePackage) {
        currentTemplatePath = CONTROLLER_TEMPLATE_PATH_CONFIG_PREFIX;

        ArrayList<String> imports = new ArrayList<>();
        imports.add(modelPackage + "." + pascalCase(processModelName(modelName)));
        imports.add(servicePackage + "." + pascalCase(processModelName(modelName)) + "Service");

        // Storing the variable
        storeStringVariable("class_name", pascalCase(processModelName(modelName)));
        storeStringVariable("package", controllerPackage);

        storeListVariable("imports", imports);


        // Spring boot
        storeStringVariable("class_name_camel", camelCase(processModelName(modelName)));
        storeStringVariable("modeles_path", modelPackage);
        storeStringVariable("class_name_lower", modelName.toLowerCase());
    }
}
