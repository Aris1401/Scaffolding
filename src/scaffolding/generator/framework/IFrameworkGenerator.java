package scaffolding.generator.framework;

import scaffolding.args.ScaffoldingArguments;
import scaffolding.database.ScaffoldDatabaseInfomations;

public interface IFrameworkGenerator {
    boolean generateFramework(ScaffoldDatabaseInfomations scaffoldDatabaseInfomations, ScaffoldingArguments scaffoldingArguments) throws Exception;
}
