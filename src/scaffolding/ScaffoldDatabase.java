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

import java.lang.reflect.InvocationTargetException;
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
            handleFrameworkGeneration(scaffoldingArguments);

            // Generating views
            handleViewGeneration(scaffoldingArguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleFrameworkGeneration(ScaffoldingArguments scaffoldingArguments) throws Exception {
        if (scaffoldingArguments.getFramework() == null || scaffoldingArguments.getFramework().isEmpty()) {
            BaseFrameworkGenerator baseGenerator = new BaseFrameworkGenerator();
            baseGenerator.generateFramework(ScaffoldDatabaseInfomations.getInstance(), scaffoldingArguments);
        } else {
            final String BASE_FRAMEWORK_GENERATOR_PROPERTIES = "scaffold.framework.";

            // Invoke the method
            IFrameworkGenerator frameworkGenerator = getDesignatedGenerator(BASE_FRAMEWORK_GENERATOR_PROPERTIES, BASE_PACKAGE, ScaffoldLoader.getFrameworkGeneratorProperties(), scaffoldingArguments.getFramework());
            frameworkGenerator.generateFramework(ScaffoldDatabaseInfomations.getInstance(), scaffoldingArguments);
        }
    }

    public static void handleViewGeneration(ScaffoldingArguments scaffoldingArguments) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(!scaffoldingArguments.getView().trim().isEmpty() && scaffoldingArguments.getView() != null) {
            if (!scaffoldingArguments.getViewoutputdir().isEmpty()) {
                final String BASE_VIEW_GENERATOR_PROPERTIES = "scaffold.view.";

                IViewGenerator viewGenerator = getDesignatedGenerator(BASE_VIEW_GENERATOR_PROPERTIES, "", ScaffoldLoader.getViewGeneratorProperties(), scaffoldingArguments.getView());
                viewGenerator.generateView(scaffoldingArguments);
            } else {
                throw new RuntimeException("Chemin specifier pour generer les views: " + scaffoldingArguments.getView() + " non specifiee.");
            }
        }
    }

    public static<T>  T getDesignatedGenerator(String baseProperties, String basePathPackage, Properties generatorProperties, String type) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String generatorPath = generatorProperties.getProperty(baseProperties + type);

        // Getting the class
        Class<?> generator = Class.forName(basePathPackage + generatorPath);

        // Instancing
		return (T) generator.getDeclaredConstructor().newInstance();
    }
}
