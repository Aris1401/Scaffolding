package scaffolding.templates.processor.springboot.auth;

import scaffolding.templates.processor.IScaffoldProcessTemplate;

import java.util.ArrayList;

public class ScaffoldProcessSpringBootAuthControllerTemplate extends IScaffoldProcessTemplate {
	@Override
	public String getCurrentTemplatePath() {
		return "scaffold.templates.springboot.controller.auth.path.";
	}

	public ScaffoldProcessSpringBootAuthControllerTemplate(String authTable, String authUserColumn, String authPassColumn, String modelPackage, String basePackage) {
		ArrayList<String> imports = new ArrayList<>();
		imports.add(modelPackage + "." + pascalCase(processModelName(authTable)));
		imports.add(basePackage + ".repository." + pascalCase(processModelName(authTable)) + "Repository");

		storeListVariable("imports", imports);

		storeStringVariable("capital_auth_user", pascalCase(processModelName(authUserColumn)));
		storeStringVariable("capital_auth_pass", pascalCase(processModelName(authPassColumn)));

		storeStringVariable("auth_table_class_name", pascalCase(processModelName(authTable)));
		storeStringVariable("auth_table_lower_name", camelCase(processModelName(authTable)));

		storeStringVariable("base_package", basePackage);

	}
}
