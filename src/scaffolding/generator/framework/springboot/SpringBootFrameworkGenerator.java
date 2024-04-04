package scaffolding.generator.framework.springboot;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.generator.framework.BaseFrameworkGenerator;
import scaffolding.templates.processor.IScaffoldProcessTemplate;
import scaffolding.templates.processor.ScaffoldProcessControllerTemplate;
import scaffolding.templates.processor.ScaffoldProcessRepositoryTemplate;
import scaffolding.templates.processor.ScaffoldProcessServiceTemplate;
import scaffolding.templates.processor.springboot.auth.ScaffoldProcessSpringBootAuthControllerTemplate;
import scaffolding.templates.processor.springboot.auth.ScaffoldProcessSpringBootAuthRequestTemplate;
import scaffolding.templates.processor.springboot.configuration.ScaffoldProcessSpringBootCORSConfigurationTemplate;
import scaffolding.templates.processor.springboot.filter.ScaffoldProcessSpringBootAuthFilterTemplate;

import java.util.ArrayList;

public class SpringBootFrameworkGenerator extends BaseFrameworkGenerator {
    @Override
    public void processOnce(ScaffoldDatabaseInfomations scaffoldDatabaseInfomations, ScaffoldingArguments scaffoldingArguments) {
        if (scaffoldingArguments.getAuthentification()) {
            // Auth controller
            ArrayList<String> authControllerCodeLines = new ScaffoldProcessSpringBootAuthControllerTemplate(scaffoldingArguments.getTableName(), scaffoldingArguments.getEmailColumn(), scaffoldingArguments.getPasswordColumn(), scaffoldingArguments.getModelPackage(), scaffoldingArguments.getBasePackage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
            String authControllerName = "AuthController";
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getDirsBasePackage() + "auth", scaffoldingArguments.getLanguage(), authControllerName, authControllerCodeLines);

            // Login request
            ArrayList<String> loginRequestCodeLiens = new ScaffoldProcessSpringBootAuthRequestTemplate(scaffoldingArguments.getBasePackage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
            String loginRequestName = "LoginRequest";
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getDirsBasePackage() + "auth", scaffoldingArguments.getLanguage(), loginRequestName, loginRequestCodeLiens);

            // Configuration
            ArrayList<String> corsConfigurationCodeLines = new ScaffoldProcessSpringBootCORSConfigurationTemplate(scaffoldingArguments.getBasePackage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getDirsBasePackage() + "configuration", scaffoldingArguments.getLanguage(), "CORSConfig", corsConfigurationCodeLines);

            // Filter
            ArrayList<String> authFilterCodeLines = new ScaffoldProcessSpringBootAuthFilterTemplate(scaffoldingArguments.getTableName(), scaffoldingArguments.getModelPackage(), scaffoldingArguments.getBasePackage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getDirsBasePackage() + "filter", scaffoldingArguments.getLanguage(), "AuthFilter", authFilterCodeLines);
        }
    }

    @Override
    public void processModel(String model, ScaffoldDatabaseInfomations scaffoldDatabaseInfomations, ScaffoldingArguments scaffoldingArguments) {
        super.processModel(model, scaffoldDatabaseInfomations, scaffoldingArguments);
        String fileName = IScaffoldProcessTemplate.pascalCase(IScaffoldProcessTemplate.processModelName(model));

        if (scaffoldingArguments.getController() != null && !scaffoldingArguments.getController().isEmpty()) {
            // Generation de repository
            String repositoryPackage = scaffoldingArguments.getBasePackage() + ".repository";
            ArrayList<String> repositoryCodeLines = new ScaffoldProcessRepositoryTemplate(model, repositoryPackage, scaffoldingArguments.getModelPackage())
                                                    .processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());

            String repositoryName = fileName + "Repository";
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getDirsBasePackage() + "repository", scaffoldingArguments.getLanguage(), repositoryName, repositoryCodeLines);

            // Generation de service
            String servicesPackage = scaffoldingArguments.getBasePackage() + ".service";
            ArrayList<String> serviceCodeLines = new ScaffoldProcessServiceTemplate(model, servicesPackage, scaffoldingArguments.getModelPackage(), repositoryPackage)
                                                .processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());

            String serviceName = fileName + "Service";
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getDirsBasePackage() + "service", scaffoldingArguments.getLanguage(), serviceName, serviceCodeLines);
            // Generation controller

            // Creation de controller
            System.out.println("Creation controllers...");

            ArrayList<String> controllerCodeLines = new ScaffoldProcessControllerTemplate(model, scaffoldingArguments.getControllerPackage(), scaffoldingArguments.getModelPackage(), servicesPackage)
                                                    .processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());

            String controllerName = fileName + "Controller";
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getDirsBasePackage() + scaffoldingArguments.getController(), scaffoldingArguments.getLanguage(), controllerName, controllerCodeLines);
        }
    }
}
