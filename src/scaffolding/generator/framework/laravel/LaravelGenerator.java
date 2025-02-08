package scaffolding.generator.framework.laravel;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.generator.framework.BaseFrameworkGenerator;
import scaffolding.templates.processor.IScaffoldProcessTemplate;
import scaffolding.templates.processor.ScaffoldProcessModelTemplate;
import scaffolding.templates.processor.laravel.LaravelControllerProcessor;
import scaffolding.templates.processor.laravel.LaravelModelProcessor;

import java.util.ArrayList;

public class LaravelGenerator extends BaseFrameworkGenerator {
	@Override
	public void processOnce(ScaffoldDatabaseInfomations scaffoldDatabaseInfomations, ScaffoldingArguments scaffoldingArguments) {
		return;
	}

	@Override
	public void processModel(String model, ScaffoldDatabaseInfomations scaffoldDatabaseInfomations, ScaffoldingArguments scaffoldingArguments) {
		// MODEL
		ArrayList<ScaffoldDatabaseTableInfo> fields = scaffoldDatabaseInfomations.getColumns(model);
		ScaffoldDatabaseTableInfo.addLanguagesFor(scaffoldingArguments.getLanguage(), fields);

		// Getting the know foreign keys of the table
		ArrayList<ScaffoldDatabaseTableInfo> foreignKeys = scaffoldDatabaseInfomations.getTableForeignKeys(model);

		// Model codeLines
		ArrayList<String> codeLines = new LaravelModelProcessor(fields, foreignKeys,  model, scaffoldingArguments.getModelPackage(), scaffoldingArguments.getLanguage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
		String fileName = IScaffoldProcessTemplate.pascalCase(IScaffoldProcessTemplate.processModelName(model));
		ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getDirsBasePackage() + scaffoldingArguments.getOutputDir(), ".php", fileName, codeLines);

		// Controller
		ArrayList<String> controllerCodelines = new LaravelControllerProcessor(fields, model, scaffoldingArguments.getModelPackage(), scaffoldingArguments.getLanguage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());
		String controllerFileName = IScaffoldProcessTemplate.pascalCase(IScaffoldProcessTemplate.processModelName(model)) + "Controller";
		ScaffoldGenerateCode.generateCodeInPathWithExtension(scaffoldingArguments.getDirsBasePackage() + scaffoldingArguments.getController(), ".php", controllerFileName, controllerCodelines);
	}
}
