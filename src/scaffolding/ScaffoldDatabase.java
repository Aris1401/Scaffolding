package scaffolding;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.args.ScaffoldingCommandLineParser;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.templates.processor.IScaffoldProcessTemplate;
import scaffolding.templates.processor.ScaffoldProcessControllerTemplate;
import scaffolding.templates.processor.ScaffoldProcessModelTemplate;

import java.util.ArrayList;

public class ScaffoldDatabase {
    public static void scaffoldDatabase(String[] args) {
        ScaffoldingArguments scaffoldingArguments = ScaffoldingCommandLineParser.parseArgs(args);

        // Loading database
        ScaffoldDatabaseInfomations scaffoldDatabaseInfomations = ScaffoldDatabaseInfomations.getInstance(scaffoldingArguments);

        // Check si le framework est specifiee
        if (scaffoldingArguments.getFramework() == null || scaffoldingArguments.getFramework().isEmpty())
            throw new RuntimeException("Framework non specifee... Annulation de l'operation");

        String[] models = scaffoldDatabaseInfomations.getDatabaseTableNames();
        for (String model : models) {
            ArrayList<ScaffoldDatabaseTableInfo> fields = scaffoldDatabaseInfomations.getColumns(model);
            ScaffoldDatabaseTableInfo.addLanguagesFor(scaffoldingArguments.getLanguage(), fields);

            // Model codeLines
            ArrayList<String> codeLines = new ScaffoldProcessModelTemplate(fields, model, scaffoldingArguments.getModelPackage(), scaffoldingArguments.getLanguage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
//
//            System.out.println("########################## MODEL #########################");
//            for (String lines :codeLines) {
//                System.out.println(lines);
//            }
//            System.out.println("############################################################");
//
            // Generation the code in the path
            String fileName = IScaffoldProcessTemplate.pascalCase(model);
            ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getOutputDir(), scaffoldingArguments.getLanguage(), fileName, codeLines);

            // Creation de controller
            if (scaffoldingArguments.getController() != null && !scaffoldingArguments.getController().isEmpty()) {
                System.out.println("Creation controllers...");

                ArrayList<String> controllerCodeLines = new ScaffoldProcessControllerTemplate(model, scaffoldingArguments.getControllerPackage(), scaffoldingArguments.getModelPackage())
                                                        .processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());

                String controllerName = fileName + "Controller";
                ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getController(), scaffoldingArguments.getLanguage(), controllerName, controllerCodeLines);
            }
        }
    }
}
