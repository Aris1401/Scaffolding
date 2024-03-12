package scaffolding;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.args.ScaffoldingCommandLineParser;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.generator.framework.BaseFrameworkGenerator;
import scaffolding.generator.framework.IFrameworkGenerator;
import scaffolding.loader.ScaffoldLoader;

import java.lang.reflect.Method;
import java.util.Properties;

public class ScaffoldDatabase {
    private static final String BASE_PACKAGE = "scaffolding.";
    public static void scaffoldDatabase(String[] args) {
        ScaffoldingArguments scaffoldingArguments = ScaffoldingCommandLineParser.parseArgs(args);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
