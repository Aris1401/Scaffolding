package scaffolding.templates.processor.laravel;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.templates.processor.IScaffoldProcessTemplate;

import java.util.ArrayList;

public class LaravelControllerProcessor extends IScaffoldProcessTemplate {
	public LaravelControllerProcessor(ArrayList<ScaffoldDatabaseTableInfo> fields, String modelName, String modelPackage, String language) {
		// Get table primary key
		ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);
		storeStringVariable("primary_key", primaryKey.getRawColumnName());

		// Fillables
		ArrayList<ScaffoldDatabaseTableInfo> fillable = new ArrayList<>();
		for (ScaffoldDatabaseTableInfo field : fields) {
			if (field.getRawColumnName().equals(primaryKey.getRawColumnName())) continue;

			fillable.add(field);
		}

		storeListVariable("fillable", fillable);

		// Class name
		storeStringVariable("class_name", IScaffoldProcessTemplate.pascalCase(IScaffoldProcessTemplate.processModelName(modelName)));
		storeStringVariable("class_name_lower", modelName);
	}

	@Override
	public String getCurrentTemplatePath() {
		return "scaffold.template.laravel.controller.";
	}
}
