package scaffolding.generator.framework;

import scaffolding.ScaffoldDatabase;
import scaffolding.args.ScaffoldingArguments;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.generation.ScaffoldGenerateCode;
import scaffolding.templates.processor.IScaffoldProcessTemplate;
import scaffolding.templates.processor.ScaffoldProcessControllerTemplate;
import scaffolding.templates.processor.ScaffoldProcessModelTemplate;

import java.util.ArrayList;

public class BaseFrameworkGenerator implements IFrameworkGenerator{
    @Override
    public boolean generateFramework(ScaffoldDatabaseInfomations scaffoldDatabaseInfomations, ScaffoldingArguments scaffoldingArguments) throws Exception {
        String[] models = scaffoldDatabaseInfomations.getDatabaseTableNames();
        for (String model : models) {
            processModel(model, scaffoldDatabaseInfomations, scaffoldingArguments);
        }

        return true;
    }

    public void processModel(String model, ScaffoldDatabaseInfomations scaffoldDatabaseInfomations, ScaffoldingArguments scaffoldingArguments) {
        ArrayList<ScaffoldDatabaseTableInfo> fields = scaffoldDatabaseInfomations.getColumns(model);
        ScaffoldDatabaseTableInfo.addLanguagesFor(scaffoldingArguments.getLanguage(), fields);

        // Getting the know foreign keys of the table
        ArrayList<ScaffoldDatabaseTableInfo> foreignKeys = scaffoldDatabaseInfomations.getTableForeignKeys(model);
        ArrayList<ScaffoldDatabaseTableInfo> filteredFields = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            if (ScaffoldDatabaseInfomations.checkIfColumnIsPrimaryKey(fields.get(i).getColumnName(), foreignKeys) == null) {
                filteredFields.add(fields.get(i));
            }
        }
        fields.clear();

        // Model codeLines
        ArrayList<String> codeLines = new ScaffoldProcessModelTemplate(filteredFields, foreignKeys,  model, scaffoldingArguments.getModelPackage(), scaffoldingArguments.getLanguage()).processTemplate(scaffoldingArguments.getLanguage(), scaffoldingArguments.getFramework());

        // Generation the code in the path
        String fileName = IScaffoldProcessTemplate.pascalCase(IScaffoldProcessTemplate.processModelName(model));
        ScaffoldGenerateCode.generateCodeInPath(scaffoldingArguments.getOutputDir(), scaffoldingArguments.getLanguage(), fileName, codeLines);
    }
}
