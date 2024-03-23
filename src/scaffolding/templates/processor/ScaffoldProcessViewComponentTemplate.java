package scaffolding.templates.processor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import scaffolding.database.ScaffoldDatabaseTableInfo;
import scaffolding.database.ScaffoldDatabaseInfomations;

public class ScaffoldProcessViewComponentTemplate extends IScaffoldProcessTemplate{
    public ScaffoldProcessViewComponentTemplate(ArrayList<ScaffoldDatabaseTableInfo> fields, String modelName, String language){
        ArrayList<ScaffoldDatabaseTableInfo> foreignKeys = ScaffoldDatabaseInfomations.getInstance().getTableForeignKeys(modelName);

        storeStringVariable("class_name_camel", pascalCase(modelName));
        storeStringVariable("class_name_lower", modelName);
        storeStringVariable("class_name", pascalCase(modelName));

        storeListVariable("foreign_keys", foreignKeys);

        // Obtenir primary key
        ScaffoldDatabaseTableInfo primaryKey = ScaffoldDatabaseInfomations.getInstance().getTablePrimaryKey(modelName);
        primaryKey.language = language;

        storeObjectVariable("primary_key", primaryKey);
    }

    @Override
    public String getCurrentTemplatePath() {
        return "scaffold.templates.view.component.path.";
    }
}
