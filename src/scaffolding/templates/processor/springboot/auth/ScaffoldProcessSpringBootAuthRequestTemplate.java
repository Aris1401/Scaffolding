package scaffolding.templates.processor.springboot.auth;

import scaffolding.templates.processor.IScaffoldProcessTemplate;

public class ScaffoldProcessSpringBootAuthRequestTemplate extends IScaffoldProcessTemplate {
	public ScaffoldProcessSpringBootAuthRequestTemplate(String basePackage) {
		storeStringVariable("base_package", basePackage);
	}
	@Override
	public String getCurrentTemplatePath() {
		return "scaffold.templates.springboot.request.auth.path.";
	}
}
