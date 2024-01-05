package scaffolding;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.args.ScaffoldingCommandLineParser;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.templates.processor.IScaffoldProcessTemplate;
import scaffolding.templates.processor.ScaffoldProcessModelTemplate;

import java.util.ArrayList;

public class ScaffoldDatabase {
    public static void scaffoldDatabase(String[] args) {
        ScaffoldingArguments scaffoldingArguments = ScaffoldingCommandLineParser.parseArgs(args);

        // Loading database
        ScaffoldDatabaseInfomations scaffoldDatabaseInfomations = new ScaffoldDatabaseInfomations();
        scaffoldDatabaseInfomations.setScaffoldingArguments(scaffoldingArguments);

        String[] models = scaffoldDatabaseInfomations.getDatabaseTableNames();
        for (String model : models) {
            ArrayList<ScaffoldDatabaseTableInfo> fields = scaffoldDatabaseInfomations.getColumns(model);
            ScaffoldDatabaseTableInfo.addLanguagesFor(scaffoldingArguments.getLanguage(), fields);

            System.out.println("########################## MODEL #########################");
            ArrayList<String> codeLines = new ScaffoldProcessModelTemplate(fields, model, scaffoldingArguments.getPackage(), scaffoldingArguments.getLanguage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
            for (String lines :codeLines) {
                System.out.println(lines);
            }
            System.out.println("############################################################");

            // Generation the code in the path
            String fileName = IScaffoldProcessTemplate.pascalCase(model);
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getOutputDir(), scaffoldingArguments.getLanguage(), fileName, codeLines);

        }
    }
}
