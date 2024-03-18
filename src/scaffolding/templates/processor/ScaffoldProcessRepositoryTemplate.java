package scaffolding.templates.processor;

import java.util.ArrayList;

public class ScaffoldProcessRepositoryTemplate extends IScaffoldProcessTemplate{
    public static final String CONTROLLER_TEMPLATE_PATH_CONFIG_PREFIX = "scaffold.templates.repository.path.";

    public ScaffoldProcessRepositoryTemplate(String modelName, String repositoryPackage, String modelPackage) {
        currentTemplatePath = CONTROLLER_TEMPLATE_PATH_CONFIG_PREFIX;

        ArrayList<String> imports = new ArrayList<>();
        imports.add(modelPackage + "." + pascalCase(processModelName(modelName)));

        // Storing the variable
        storeStringVariable("class_name", pascalCase(processModelName(modelName)));
        storeStringVariable("package", repositoryPackage);

        storeListVariable("imports", imports);
    }
}
