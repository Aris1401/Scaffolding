package scaffolding.templates.processor.springboot.filter;

import scaffolding.templates.processor.IScaffoldProcessTemplate;

import java.util.ArrayList;

public class ScaffoldProcessSpringBootAuthFilterTemplate extends IScaffoldProcessTemplate {
	public ScaffoldProcessSpringBootAuthFilterTemplate(String authTable, String modelPackage) {
		ArrayList<String> imports = new ArrayList<>();
		imports.add(modelPackage + "." + pascalCase(processModelName(authTable)));

		storeListVariable("imports", imports);

		storeStringVariable("auth_table_class_name", pascalCase(processModelName(authTable)));
	}

	@Override
	public String getCurrentTemplatePath() {
		return "scaffold.templates.springboot.auth.filter.path.";
	}
}
