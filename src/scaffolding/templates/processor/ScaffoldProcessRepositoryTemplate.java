package scaffolding.templates.processor;

import java.util.ArrayList;

public class ScaffoldProcessRepositoryTemplate extends IScaffoldProcessTemplate{
    public ScaffoldProcessRepositoryTemplate(String modelName, String repositoryPackage, String modelPackage) {
        ArrayList<String> imports = new ArrayList<>();
        imports.add(modelPackage + "." + pascalCase(processModelName(modelName)));

        // Storing the variable
        storeStringVariable("class_name", pascalCase(processModelName(modelName)));
        storeStringVariable("package", repositoryPackage);

        storeListVariable("imports", imports);
    }

    @Override
    public String getCurrentTemplatePath() {
        return "scaffold.templates.repository.path.";
    }
}
