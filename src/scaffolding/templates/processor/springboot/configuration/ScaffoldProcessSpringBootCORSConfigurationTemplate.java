package scaffolding.templates.processor.springboot.configuration;

import scaffolding.templates.processor.IScaffoldProcessTemplate;

public class ScaffoldProcessSpringBootCORSConfigurationTemplate extends IScaffoldProcessTemplate {
	public ScaffoldProcessSpringBootCORSConfigurationTemplate(String basePackage) {
		storeStringVariable("base_package", basePackage);
	}

	@Override
	public String getCurrentTemplatePath() {
		return "scaffold.templates.springboot.cors.configutation.path.";
	}
}
