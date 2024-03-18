package scaffolding;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.args.ScaffoldingCommandLineParser;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.templates.processor.IScaffoldProcessTemplate;
import scaffolding.templates.processor.ScaffoldProcessControllerTemplate;
import scaffolding.templates.processor.ScaffoldProcessModelTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewComponentTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewCssTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewHTMLTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewModelTemplate;
import scaffolding.templates.processor.ScaffoldViewServiceTemplate;

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

            // Creation de view
            if(scaffoldingArguments.getView() != null && !scaffoldingArguments.getViewoutputdir().isEmpty()) {
                if(scaffoldingArguments.getView().compareTo("angular")==0){
                    System.out.println("Creation views...");
                    ArrayList<String> viewComponentCodeLines = new ScaffoldProcessViewComponentTemplate(fields, model, "").processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                    String viewComponentName = model + ".component";
                    ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".ts", viewComponentName, viewComponentCodeLines);
                    System.out.println("Component create successfully...");
                    
                    ArrayList<String> viewCssCodeLines = new ScaffoldProcessViewCssTemplate().processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                    String viewCssName = model + ".component";
                    ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".css", viewCssName, viewCssCodeLines);

                    ArrayList<String> viewHtmlCodeLines = new ScaffoldProcessViewHTMLTemplate(fields, model).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                    String viewHtmlName = model + ".component";
                    ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".html", viewHtmlName, viewHtmlCodeLines);
                    System.out.println("Html & Css create successfully...");

                    ArrayList<String> viewModelCodeLines = new ScaffoldProcessViewModelTemplate(fields, model).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                    String viewModelName = model + ".model";
                    ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".ts", viewModelName, viewModelCodeLines);
                    System.out.println("Model create successfully...");

                    ArrayList<String> viewServiceCodeLines = new ScaffoldViewServiceTemplate(model,"").processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                    String viewServiceName = model + ".service";
                    ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".ts", viewServiceName, viewServiceCodeLines);
                    System.out.println("Service create successfully...");
                }
            }
        }
    }
}
