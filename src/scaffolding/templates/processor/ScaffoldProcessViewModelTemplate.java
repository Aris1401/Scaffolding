package scaffolding.templates.processor;

import java.util.ArrayList;

import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.database.ScaffoldDatabaseTableInfo;

public class ScaffoldProcessViewModelTemplate extends IScaffoldProcessTemplate{
    public ScaffoldProcessViewModelTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields,String modelName){
        ArrayList<ScaffoldDatabaseTableInfo> foreignKeys = ScaffoldDatabaseInfomations.getInstance().getTableForeignKeys(modelName);

        storeListVariable("foreign_keys", foreignKeys);
        storeListVariable("fields", fields);

        storeStringVariable("class_name_camel", pascalCase(modelName));
    }

    @Override
    public String getCurrentTemplatePath() {
        return "scaffold.templates.view.model.path.";
    }
}
