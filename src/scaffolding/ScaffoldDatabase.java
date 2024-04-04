package scaffolding;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.args.ScaffoldingCommandLineParser;
import scaffolding.configurationInShell.ScaffoldingConfigurationInShell;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.generator.view.IViewGenerator;
import scaffolding.generator.view.angular.AngularViewGenerator;
import scaffolding.templates.processor.ScaffoldProcessViewComponentTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewCssTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewHTMLTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewModelTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewServiceTemplate;
import scaffolding.generator.framework.BaseFrameworkGenerator;
import scaffolding.generator.framework.IFrameworkGenerator;
import scaffolding.loader.ScaffoldLoader;
import scaffolding.templates.processor.angular.auth.ScaffoldProcessAngularLoginComponentTemplate;
import scaffolding.templates.processor.angular.auth.ScaffoldProcessAngularLoginHtmlTemplate;
import scaffolding.templates.processor.angular.auth.ScaffoldProcessAngularLoginServiceTemplate;
import scaffolding.templates.processor.angular.interceptor.ScaffoldProcessAngularInterceptorAuthTemplate;
import scaffolding.templates.processor.angular.interceptor.ScaffoldProcessAngularInterceptorCredentialsTemplate;

import java.util.ArrayList;
import java.util.Properties;

public class ScaffoldDatabase {
    private static final String BASE_PACKAGE = "scaffolding.";
    public static void scaffoldDatabase(String[] args) {
//        ScaffoldingArguments scaffoldingArguments = ScaffoldingCommandLineParser.parseArgs(args);

//        ScaffoldingArguments scaffoldingArguments = ScaffoldingConfigurationInShell.configureScaffolding();
        ScaffoldingArguments scaffoldingArguments = ScaffoldingArguments.getDefinedArgs();

        // Loading database
        ScaffoldDatabaseInfomations scaffoldDatabaseInfomations = ScaffoldDatabaseInfomations.getInstance(scaffoldingArguments);

        try {
            if (scaffoldingArguments.getFramework() == null || scaffoldingArguments.getFramework().isEmpty()) {
                BaseFrameworkGenerator baseGenerator = new BaseFrameworkGenerator();
                baseGenerator.generateFramework(scaffoldDatabaseInfomations, scaffoldingArguments);
            } else {
                final String BASE_FRAMEWORK_GENERATOR_PROPERTIES = "scaffold.framework.";

                // Framework correspondant
                Properties frameworkGeneratorProperties = ScaffoldLoader.getFrameworkGeneratorProperties();
                String generatorPath = frameworkGeneratorProperties.getProperty(BASE_FRAMEWORK_GENERATOR_PROPERTIES + scaffoldingArguments.getFramework());

                // Getting the class
                Class<?> generator = Class.forName(BASE_PACKAGE + generatorPath);

                // Invoke the method
                IFrameworkGenerator frameworkGenerator = (IFrameworkGenerator) generator.getDeclaredConstructor().newInstance();
                frameworkGenerator.generateFramework(scaffoldDatabaseInfomations, scaffoldingArguments);
            }

            // Generating views
            if(!scaffoldingArguments.getView().trim().isEmpty() && scaffoldingArguments.getView() != null) {
                if (!scaffoldingArguments.getViewoutputdir().isEmpty()) {
                    final String BASE_VIEW_GENERATOR_PROPERTIES = "scaffold.view.";

                    // View correspondant
                    Properties frameworkGeneratorProperties = ScaffoldLoader.getViewGeneratorProperties();
                    String generatorPath = frameworkGeneratorProperties.getProperty(BASE_VIEW_GENERATOR_PROPERTIES + scaffoldingArguments.getView());

                    // Getting the class
                    Class<?> generator = Class.forName(generatorPath);

                    // Instancing
                    IViewGenerator viewGenerator = (IViewGenerator) generator.getDeclaredConstructor().newInstance();
                    viewGenerator.generateView(scaffoldingArguments);
                } else {
                    throw new RuntimeException("Chemin specifier pour generer les views: " + scaffoldingArguments.getView() + " non specifiee.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
