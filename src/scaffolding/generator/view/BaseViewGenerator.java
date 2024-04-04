package scaffolding.generator.view;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.database.ScaffoldDatabaseInfomations;

public class BaseViewGenerator implements IViewGenerator{
	@Override
	public boolean generateView(ScaffoldingArguments scaffoldingArguments) {
		processOnce(scaffoldingArguments);

		String[] models = ScaffoldDatabaseInfomations.getInstance().getDatabaseTableNames();
		for (String model : models) {
			if (model.toLowerCase().startsWith("v_")) continue;
			processModel(model, scaffoldingArguments);
		}

		return true;
	}

	public void processOnce(ScaffoldingArguments scaffoldingArguments) {}

	public void processModel(String model, ScaffoldingArguments scaffoldingArguments) {}
}
