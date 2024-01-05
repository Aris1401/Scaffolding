package scaffolding.templates.processor;

import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.loader.ScaffoldLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public abstract class IScaffoldProcessTemplate {
    String currentTemplatePath = "";

    //<editor-fold desc="Variables">
    // Variables
    final String FOR_LOOP = ScaffoldGenerateCode.getVariable("for");
    final String ENDFOR_LOOP = ScaffoldGenerateCode.getVariable("endfor");

    final String IF_CONDITION = ScaffoldGenerateCode.getVariable("if");
    final String ENDIF_CONDITION = ScaffoldGenerateCode.getVariable("endif");
    //</editor-fold>

    // Storing the possible variables
    enum VariableTypes {
        HASHMAP, LIST, STRING, UNKNOWN
    }
    Map<String, ArrayList<?>> mappedListVariables = new LinkedHashMap<>();
    Map<String, String> mappedStringVariables = new HashMap<>();
    public boolean isListVariable(String variableName) {
        return mappedListVariables.containsKey(variableName);
    }

    public boolean isStringVariable(String variableName) {
        return mappedStringVariables.containsKey(variableName);
    }

    public <T> T getVariable(String variableName) {
        if (isListVariable(variableName)) return (T) mappedListVariables.get(variableName);
        if (isStringVariable(variableName)) return (T) mappedStringVariables.get(variableName);
        return null;
    }

    // Buffering purposes
    ArrayList<String> buffuredLines = new ArrayList<>();

    void storeListVariable(String name, ArrayList<?> value) {
        mappedListVariables.put(name, value);
    }

    void storeStringVariable(String name, String value) {
        mappedStringVariables.put(name, value);
    }

    File getTemplateFileInPathFor(String language) {
        Properties templateProperties = ScaffoldLoader.getTemplatesProperties();

        // Getting the template path
        String templatePath = templateProperties.getProperty(currentTemplatePath + language);

        return ScaffoldLoader.getFileFromPath(templatePath);
    }

    public ArrayList<String> processTemplate(String language, String framework) {
        ArrayList<String> processedLines = new ArrayList<>();

        // Getting the template file
        File templateFile = getTemplateFileInPathFor(language);

        try (BufferedReader reader = new BufferedReader(new FileReader(templateFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processedLines.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Proccessing the lines
        processedLines = processLines(language, framework, processedLines);
        processedLines = cleanUpLines(processedLines);

        return processedLines;
    }

    ArrayList<String> cleanUpLines(ArrayList<String> lines) {
        ArrayList<String> newLines = new ArrayList();

        if (!lines.get(0).trim().isEmpty()) newLines.add(lines.get(0));
        for (int i = 1; i < lines.size(); i++) {
            if (!newLines.isEmpty()) {
                if (lines.get(i).trim().isEmpty()) {
                    if (!newLines.get(newLines.size() - 1).trim().isEmpty())
                        newLines.add(lines.get(i));
                } else {
                    newLines.add(lines.get(i));
                }
            } else {
                if (!lines.get(i).trim().isEmpty()) newLines.add(lines.get(i));
            }
        }

        return newLines;
    }

    // Processing blocks
    String processBlocks(String line, String startBlock, String endBlock, Supplier<String> processFunction) {
        // If the current line is nothing
        if (line.isEmpty()) return "";

        // Loops
        if (line.contains(startBlock)) {
            buffuredLines.add(line);
            return "";
        }
        if (line.contains(endBlock)) {
            buffuredLines.add(line);
            String processed = processFunction.get();
            buffuredLines.clear();
            return processed;
        }

        if (!buffuredLines.isEmpty()) {
            buffuredLines.add(line);
            return "";
        } else {
            return line;
        }
    }

    ArrayList<String> parseLines(ArrayList<String> lines) {
        ArrayList<String> newLines = new ArrayList<>();

        for (String line : lines) {
            String[] parsedLines = line.split(System.lineSeparator());
            Collections.addAll(newLines, parsedLines);
        }

        return newLines;
    }

    private ArrayList<String> processLines(String language, String framework, ArrayList<String> processedLines) {
        for (int i = 0; i < processedLines.size(); i++) {
            String line = processedLines.get(i);
            String processedLine = processBlocks(line, IF_CONDITION, ENDIF_CONDITION, () -> {
                return processConditions(language, framework);
            });

            if (processedLine != null) {
                processedLines.set(i, processedLine);
            } else {
                processedLines.set(i, "");
            }
        }

        processedLines = parseLines(processedLines);
        buffuredLines.clear();

        for (int i = 0; i < processedLines.size(); i++) {
            String line = processedLines.get(i);
            String processedLine = processBlocks(line, FOR_LOOP, ENDFOR_LOOP, this::processLoops);

            if (processedLine != null) {
                processedLines.set(i, processedLine);
            }
        }

        processedLines = parseLines(processedLines);

        for (int i = 0; i < processedLines.size(); i++) {
            String[] variableCalls = getCallsInOneLine(processedLines.get(i));

            for (String variableCall : variableCalls) {
                String variableValue = getVariable(variableCall);
                String processedCall = processCall(variableCall, variableCall, getVariable(variableCall));
                processedLines.set(i, processedLines.get(i).replace(VARIABLE_NOTATION_START + variableCall + VARIABLE_NOTATION_END, processedCall));
            }
        }

        return processedLines;
    }

    final String CONDITION_SEPARATOR = ":";
    final String LANGUAGE_CONDITION = "language";
    final String FRAMEWORK_CONDITION = "framework";
    private String processConditions(String language, String framework) {
        StringBuilder processed = new StringBuilder();

        // Variables
        String firstLine = buffuredLines.getFirst();
        String condition = firstLine.trim().split(" ")[1];

        // Parsing condition
        String[] conditionInWhole = condition.split(CONDITION_SEPARATOR);
        String conditionStarter = conditionInWhole[0];
        String conditioner = conditionInWhole[1];

        String toEqual = language;
        if (conditionStarter.equals(FRAMEWORK_CONDITION)) {
            if (framework == null)
                throw new RuntimeException("Framework non specifier.");

            toEqual = framework;
        } else if (conditionStarter.equals(LANGUAGE_CONDITION)) {
            toEqual = language;
        }

        if (conditioner.equals(toEqual)) {
            for(int i = 1; i < buffuredLines.size() - 1; i++) {
                processed.append(buffuredLines.get(i));
                processed.append(System.lineSeparator());
            }
        } else {
            return null;
        }

        return processed.toString();
    }

    final String VARIABLE_NOTATION_START = "#{";
    final String VARIABLE_NOTATION_END = "}";
    final String VARIABLE_REGEX_PATTERN = "#\\{(.*?)}";
    private String processLoops() {
        StringBuilder processed = new StringBuilder();

        VariableTypes variableTypes = VariableTypes.UNKNOWN;
        String variableName = "";

        // Variables buffers
        ArrayList<?> listBuffured = null;
        // TODO: Ajouter listes....
        for (int i = 0; i < buffuredLines.size() - 1; i++) {
            String line = buffuredLines.get(i);

            if (line.contains(FOR_LOOP)) {
                String loopCondition = line.trim().split(" ")[1];

                String loopVariable = loopCondition.replaceAll(VARIABLE_REGEX_PATTERN, "$1");
                variableName = loopVariable;

                if (isListVariable(loopVariable)) {
                    variableTypes = VariableTypes.LIST;
                    listBuffured = getVariable(loopVariable);
                }

                break;
            }
        }

        switch (variableTypes) {
            case HASHMAP -> {
//                for (Map.Entry<String, String> hash : hashBuffured.entrySet()) {
//                    for (int i = 1; i < buffuredLines.size() - 1; i++) {
//                        String line = buffuredLines.get(i);
//
//                        // Obtenir les variables sur une ligne
//                        String[] calls = getCallsInOneLine(buffuredLines.get(i));
//                        for (String call : calls) {
//                            String callProcessed = processCall(call, variableName, hash);
//                            line = line.replace("#{" + call + "}", callProcessed);
//                        }
//
//                        processed.append(line);
//                        processed.append(System.lineSeparator());
//                    }
//                }
            }
            case LIST -> {
                for (Object info : listBuffured) {
                    for (int i = 1; i < buffuredLines.size() - 1; i++) {
                        String line = buffuredLines.get(i);

                        // Obtenir les variables sur une ligne
                        String[] calls = getCallsInOneLine(buffuredLines.get(i));
                        for (String call : calls) {
                            String callProcessed = processCall(call, variableName, info);
                            line = line.replace(VARIABLE_NOTATION_START + call + VARIABLE_NOTATION_END, callProcessed);
                        }

                        processed.append(line);
                        processed.append(System.lineSeparator());
                    }
                }
            }
            case UNKNOWN -> {
                throw new RuntimeException("Variable encore indefini.");
            }
        }


        return processed.toString();
    }

    final String CALL_SEPARATOR = ":";
    String processCall(String call, String variableName, Object invokedObject) {
        if (call.contains(CALL_SEPARATOR)) {
            String[] parsedCall = call.split(CALL_SEPARATOR);

            String getMethod = "get" + pascalCase(parsedCall[1]);
            try {
                Method invokedMethod = invokedObject.getClass().getDeclaredMethod(getMethod);
                invokedMethod.setAccessible(true);
                return invokedMethod.invoke(invokedObject).toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return invokedObject.toString();
        }
    }

    String[] getCallsInOneLine(String line) {
        Pattern pattern = Pattern.compile(VARIABLE_REGEX_PATTERN);

        // Use streams to collect matches into a String array

        return pattern.matcher(line)
                .results()
                .map(matchResult -> matchResult.group(1))
                .toArray(String[]::new);
    }

    public static String pascalCase(String string) {
        return  string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
    }
}
