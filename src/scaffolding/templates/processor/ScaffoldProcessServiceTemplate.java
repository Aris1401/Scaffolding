package scaffolding.templates.processor;

import java.util.ArrayList;

public class ScaffoldProcessServiceTemplate extends IScaffoldProcessTemplate{
    public ScaffoldProcessServiceTemplate(String modelName, String servicePackage, String modelPackage, String repositoryPackage) {
        ArrayList<String> imports = new ArrayList<>();
        imports.add(modelPackage + "." + pascalCase(processModelName(modelName)));
        imports.add(repositoryPackage + "." + pascalCase(processModelName(modelName)) + "Repository");

        // Storing the variable
        storeStringVariable("class_name", pascalCase(processModelName(modelName)));
        storeStringVariable("package", servicePackage);

        storeListVariable("imports", imports);

        // Spring boot
        // Spring boot
        storeStringVariable("class_name_camel", camelCase(processModelName(modelName)));
        storeStringVariable("modeles_path", modelPackage);
        storeStringVariable("class_name_lower", modelName.toLowerCase());
    }

    @Override
    public String getCurrentTemplatePath() {
        return "scaffold.templates.services.path.";
    }
}
