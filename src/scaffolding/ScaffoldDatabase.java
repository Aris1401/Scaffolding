package scaffolding;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.args.ScaffoldingCommandLineParser;
import scaffolding.configurationInShell.ScaffoldingConfigurationInShell;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
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
                if (model.toLowerCase().startsWith("v_")) continue;
                // Creation de view
                ArrayList<ScaffoldDatabaseTableInfo> fields = scaffoldDatabaseInfomations.getColumns(model);
                ScaffoldDatabaseTableInfo.addLanguagesFor("ts", fields);
                if(scaffoldingArguments.getView() != null && !scaffoldingArguments.getViewoutputdir().isEmpty()) {
                    if(scaffoldingArguments.getView().compareTo("angular")==0){
                        System.out.println(model.toUpperCase() + ": Creation views...");

                        // COMPONENT
                        ArrayList<String> viewComponentCodeLines = new ScaffoldProcessViewComponentTemplate(fields, model, "").processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        String viewComponentName = model + ".component";
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".ts", viewComponentName, viewComponentCodeLines);
                        System.out.println("Component create successfully...");

                        // CSS
                        ArrayList<String> viewCssCodeLines = new ScaffoldProcessViewCssTemplate().processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        String viewCssName = model + ".component";
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".css", viewCssName, viewCssCodeLines);

                        // HTML
                        ArrayList<String> viewHtmlCodeLines = new ScaffoldProcessViewHTMLTemplate(fields, model).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        String viewHtmlName = model + ".component";
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".html", viewHtmlName, viewHtmlCodeLines);
                        System.out.println("Html & Css create successfully...");

                        // MODEL
                        ArrayList<String> viewModelCodeLines = new ScaffoldProcessViewModelTemplate(fields, model).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        String viewModelName = model + ".model";
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".ts", viewModelName, viewModelCodeLines);
                        System.out.println("Model create successfully...");

                        // SERVICE
                        ArrayList<String> viewServiceCodeLines = new ScaffoldProcessViewServiceTemplate(model,"").processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        String viewServiceName = model + ".service";
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir(), ".ts", viewServiceName, viewServiceCodeLines);
                        System.out.println("Service create successfully...");
                    }
                }
            }

            if (scaffoldingArguments.getAuthentification()) {
                if(scaffoldingArguments.getView() != null && !scaffoldingArguments.getViewoutputdir().isEmpty()) {
                    if(scaffoldingArguments.getView().compareTo("angular")==0){
                        // Component
                        ArrayList<String> componentCodeLines = new ScaffoldProcessAngularLoginComponentTemplate().processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        String loginComponentName = "login.component";
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir() + "/auth", ".ts", loginComponentName, componentCodeLines);

                        // HTML
                        ArrayList<String> htmlCodeLines = new ScaffoldProcessAngularLoginHtmlTemplate().processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        String loginHtmlName = "login.component";
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir() + "/auth", ".html", loginHtmlName, htmlCodeLines);

                        // Service
                        ArrayList<String> serviceCodeLines = new ScaffoldProcessAngularLoginServiceTemplate().processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        String loginServiceName = "login.service";
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir() + "/auth", ".ts", loginServiceName, serviceCodeLines);

                        /// --------------- INTERCPETORS
                        ArrayList<String> authInterceptorCodeLines = new ScaffoldProcessAngularInterceptorAuthTemplate().processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir() + "/interceptors", ".ts", "AuthInterceptor", authInterceptorCodeLines);

                        ArrayList<String> credentialsInterceptorCodeLines = new ScaffoldProcessAngularInterceptorCredentialsTemplate().processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
                        ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getViewoutputdir() + "/interceptors", ".ts", "WithCredentialsInterceptor", credentialsInterceptorCodeLines);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
