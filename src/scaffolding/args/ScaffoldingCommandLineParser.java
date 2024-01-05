package scaffolding.args;

import scaffolding.loader.ScaffoldLoader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ScaffoldingCommandLineParser {
    private static final String COMMANDLINE_CONFIG_PREFIX = "command.prefix.";

    private static Properties commandLineProperties = null;
    static Properties getCommandLineProperties() {
        if (commandLineProperties == null) {
            commandLineProperties = ScaffoldLoader.getCommandLineProperties();
        }

        return commandLineProperties;
    }

    static HashMap<String, String> convertArgsToMap(String[] args) {
        Map<String, String> arguments = new HashMap<>();

        String currentKey = null;
        StringBuilder currentValue = new StringBuilder();
        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (!currentValue.toString().equals("")) {
                    arguments.put(currentKey, currentValue.toString());
                    currentValue = new StringBuilder();
                }

                currentKey = arg.substring(1);
                currentValue = new StringBuilder();
            } else {
                currentValue.append(currentValue.toString().equals("") ? arg : " " + arg);
            }
        }

        // Adding the last arg
        arguments.put(currentKey, currentValue.toString());
        return (HashMap<String, String>) arguments;
    }

    public static ScaffoldingArguments parseArgs(String[] args) {
        ScaffoldingArguments scaffoldingArguments = new ScaffoldingArguments();

        // Setting each field according to the args

        HashMap<String, String> commandLineArguments = convertArgsToMap(args);

        // Getting the objects fields
        Field[] scaffoldingArgumentsFields = scaffoldingArguments.getClass().getDeclaredFields();
        for (Field field : scaffoldingArgumentsFields) {
            String command = getCommandLineProperties().getProperty(COMMANDLINE_CONFIG_PREFIX + field.getName());
            String currentCommandValue = commandLineArguments.get(command);
//            System.out.println(command + ": " + currentCommandValue + " - " + COMMANDLINE_CONFIG_PREFIX + field.getName());

            // Settting the field value
            if (currentCommandValue == null) continue;

            try {
                field.setAccessible(true);
                if (field.getType().isArray()) {
                    field.set(scaffoldingArguments, currentCommandValue.trim().split(" "));
                } else {
                    field.set(scaffoldingArguments, currentCommandValue);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(scaffoldingArguments);
        return scaffoldingArguments;
    }
}
