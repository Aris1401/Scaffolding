package scaffolding;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.args.ScaffoldingCommandLineParser;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.templates.processor.ScaffoldProcessViewComponentTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewCssTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewHTMLTemplate;
import scaffolding.templates.processor.ScaffoldProcessViewModelTemplate;
import scaffolding.templates.processor.ScaffoldViewServiceTemplate;
import scaffolding.generator.framework.BaseFrameworkGenerator;
import scaffolding.generator.framework.IFrameworkGenerator;
import scaffolding.loader.ScaffoldLoader;
import java.util.ArrayList;
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

            String[] models = scaffoldDatabaseInfomations.getDatabaseTableNames();
            for (String model : models) {
                // Creation de view
                ArrayList<ScaffoldDatabaseTableInfo> fields = scaffoldDatabaseInfomations.getColumns(model);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
