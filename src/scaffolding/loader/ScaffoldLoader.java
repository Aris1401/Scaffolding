package scaffolding.loader;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class ScaffoldLoader {
    public static final String CONFIGURATIONS_BASE_PATH = "scaffolding/configuration/";

    public static File getFileFromPath(String path) {
        URL ressourceUrl = ScaffoldLoader.class.getClassLoader().getResource(path);
        String proprtiesPath = ressourceUrl.getPath().replace("%20", " ");

        return new File(proprtiesPath);
    }
    static Properties getConfiguationProperties(String propertiesName) {
        File configProperties = getFileFromPath(CONFIGURATIONS_BASE_PATH + propertiesName + ".properties");

        // Creating the property
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(configProperties)) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    public static Properties getCommandLineProperties() {
        return getConfiguationProperties("scaffoldCommandLine");
    }
    public static Properties getDatabaseProperties() {
        return getConfiguationProperties("scaffoldDatabase");
    }

    public static Properties getVariablesProperties() {
        return getConfiguationProperties("scaffoldVariables");
    }

    public static Properties getGenerationProperties() {
        return getConfiguationProperties("scaffoldGeneration");
    }

    public static Properties getTemplatesProperties() {
        return getConfiguationProperties("scaffoldTemplates");
    }

    public static Properties getColumnTypesProperties() {
        return getConfiguationProperties("scaffoldColumnTypes");
    }

    public static Properties getFrameworkGeneratorProperties() {
        return getConfiguationProperties("scaffoldFrameworkGenerator");
    }

    public static Properties getViewGeneratorProperties() {
        return getConfiguationProperties("scaffoldViewGenerator");
    }
}
