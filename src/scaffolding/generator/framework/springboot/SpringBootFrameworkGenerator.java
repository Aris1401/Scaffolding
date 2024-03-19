package scaffolding.generator.framework.springboot;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.generator.framework.BaseFrameworkGenerator;
import scaffolding.generator.framework.IFrameworkGenerator;
import scaffolding.templates.processor.IScaffoldProcessTemplate;
import scaffolding.templates.processor.ScaffoldProcessControllerTemplate;
import scaffolding.templates.processor.ScaffoldProcessRepositoryTemplate;
import scaffolding.templates.processor.ScaffoldProcessServiceTemplate;

import java.util.ArrayList;

public class SpringBootFrameworkGenerator extends BaseFrameworkGenerator {
    @Override
    public void processModel(String model, ScaffoldDatabaseInfomations scaffoldDatabaseInfomations, ScaffoldingArguments scaffoldingArguments) {
        super.processModel(model, scaffoldDatabaseInfomations, scaffoldingArguments);
        String fileName = IScaffoldProcessTemplate.pascalCase(IScaffoldProcessTemplate.processModelName(model));

        if (scaffoldingArguments.getController() != null && !scaffoldingArguments.getController().isEmpty()) {
            // Generation de repository
            String repositoryPackage = "repositories";
            ArrayList<String> repositoryCodeLines = new ScaffoldProcessRepositoryTemplate(model, repositoryPackage, scaffoldingArguments.getModelPackage())
                    .processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());

            String repositoryName = fileName + "Repository";
            ScaffoldGenerateCode.generateCodeInPath("./src/res/" + repositoryPackage, scaffoldingArguments.getLanguage(), repositoryName, repositoryCodeLines);

            // Generation de service
            String servicesPackage = "services";
            ArrayList<String> serviceCodeLines = new ScaffoldProcessServiceTemplate(model, servicesPackage, scaffoldingArguments.getModelPackage(), repositoryPackage)
                    .processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());

            String serviceName = fileName + "Service";
            ScaffoldGenerateCode.generateCodeInPath("./src/res/" + servicesPackage, scaffoldingArguments.getLanguage(), serviceName, serviceCodeLines);
            // Generation controller

            // Creation de controller
            System.out.println("Creation controllers...");

            ArrayList<String> controllerCodeLines = new ScaffoldProcessControllerTemplate(model, scaffoldingArguments.getControllerPackage(), scaffoldingArguments.getModelPackage(), servicesPackage)
                    .processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());

            String controllerName = fileName + "Controller";
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getController(), scaffoldingArguments.getLanguage(), controllerName, controllerCodeLines);
        }
    }
}
