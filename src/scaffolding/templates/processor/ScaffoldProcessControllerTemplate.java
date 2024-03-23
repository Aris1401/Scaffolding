package scaffolding.templates.processor;

import scaffolding.database.ScaffoldDatabaseTableInfo;

import java.util.ArrayList;

public class ScaffoldProcessControllerTemplate extends IScaffoldProcessTemplate {
    public ScaffoldProcessControllerTemplate(String modelName, String controllerPackage, String modelPackage, String servicePackage) {
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

    @Override
    public String getCurrentTemplatePath() {
        return "scaffold.templates.controller.path.";
    }
}
