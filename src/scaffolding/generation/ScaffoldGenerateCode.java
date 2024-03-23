package scaffolding.generation;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.loader.ScaffoldLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ScaffoldGenerateCode {
    public static final String VARIABLE_CONFIG_PREFIX = "scaffold.variable.";
    private static Properties variableProperties = null;
    public static Properties getVariableProperties() {
        if (variableProperties == null) {
            variableProperties = ScaffoldLoader.getVariablesProperties();
        }

        return variableProperties;
    }

    public static String getVariable(String variableName) {
        return getVariableProperties().getProperty(VARIABLE_CONFIG_PREFIX + variableName);
    }

    // CODE GENERATION: Properties
    public static final String FILE_EXTENSION_CONFIG_PREFIX = "scaffold.file.extension.";
    private static Properties generationProperties;
    static String getFileExtensionFor(String language) {
        return getGenerationProperties().getProperty(FILE_EXTENSION_CONFIG_PREFIX + language);
    }

    static Properties getGenerationProperties() {
        if (generationProperties == null) {
            generationProperties = ScaffoldLoader.getGenerationProperties();
        }

        return generationProperties;
    }

    private ScaffoldingArguments scaffoldingArguments;

    public void setScaffoldingArguments(ScaffoldingArguments scaffoldingArguments) {
        this.scaffoldingArguments = scaffoldingArguments;
    }

    String getFileExtension() {
        return getFileExtensionFor(scaffoldingArguments.getLanguage());
    }

    // CODE GENERATION
    public static boolean generateCodeInPath(String path, String language, String fileName, ArrayList<String> codeLines) {
        // Getting the directory
        File directoryPath = getDirectory(path, true);
        System.out.println("DIrectory: " + directoryPath);

        // Getting the file
        String filePath = directoryPath.getAbsolutePath() + "/" + fileName + getFileExtensionFor(language);
        File file = getFile(filePath, true);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (String codeLine : codeLines) {
                writer.write(codeLine);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static boolean generateCodeInPathWithExtension(String path, String extension, String fileName, ArrayList<String> codeLines) {
        // Getting the directory
        File directoryPath = getDirectory(path, true);

        // Getting the file
        String filePath = directoryPath.getAbsolutePath() + "/" + fileName + extension;
        File file = getFile(filePath, true);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (String codeLine : codeLines) {
                writer.write(codeLine);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    static File getDirectory(String dirPath, boolean create) {
        File dirFile = new File(dirPath);

        if (create)
            if(!dirFile.exists()) dirFile.mkdirs();

        return dirFile;
    }

    static File getFile(String filePath, boolean create) {
        File file = new File(filePath);
        if (create)
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        return file;
    }
}
