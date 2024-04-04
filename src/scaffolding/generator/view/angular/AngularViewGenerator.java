package scaffolding.generator.view.angular;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.generator.view.BaseViewGenerator;
import scaffolding.generator.view.IViewGenerator;
import scaffolding.templates.processor.*;
import scaffolding.templates.processor.angular.auth.ScaffoldProcessAngularLoginComponentTemplate;
import scaffolding.templates.processor.angular.auth.ScaffoldProcessAngularLoginHtmlTemplate;
import scaffolding.templates.processor.angular.auth.ScaffoldProcessAngularLoginServiceTemplate;
import scaffolding.templates.processor.angular.interceptor.ScaffoldProcessAngularInterceptorAuthTemplate;
import scaffolding.templates.processor.angular.interceptor.ScaffoldProcessAngularInterceptorCredentialsTemplate;

import java.util.ArrayList;

public class AngularViewGenerator extends BaseViewGenerator {
	@Override
	public void processModel(String model, ScaffoldingArguments scaffoldingArguments) {
		ArrayList<ScaffoldDatabaseTableInfo> fields = ScaffoldDatabaseInfomations.getInstance().getColumns(model);

		ScaffoldDatabaseTableInfo.addLanguagesFor("ts", fields);
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

	@Override
	public void processOnce(ScaffoldingArguments scaffoldingArguments) {
		if (scaffoldingArguments.getAuthentification()) {
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

		// TODO: Generate configs

		// TODO: Generate routes
	}
}
