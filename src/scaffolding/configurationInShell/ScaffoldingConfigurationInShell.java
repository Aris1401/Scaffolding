package scaffolding.configurationInShell;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import scaffolding.args.ScaffoldingArguments;
import scaffolding.database.ScaffoldDatabaseInfomations;
import scaffolding.loader.ScaffoldLoader;

public class ScaffoldingConfigurationInShell{
	static boolean useAuth = false;
	static boolean usePagination = false;
	public static ScaffoldingArguments configureScaffolding(){
		Scanner scanner = new Scanner(System.in);
		ScaffoldingArguments scaffoldingArguments = new ScaffoldingArguments();

		System.out.println("Utiliser authentification ? (Y/N) : ");
		String authentification = scanner.nextLine();

		if (authentification.equalsIgnoreCase("y")) useAuth = true;

		if (useAuth) {
			System.out.println("Table d'authentification : ");
			String tableName = scanner.nextLine();
			System.out.println("Colonne d'email : ");
			String emailColumn = scanner.nextLine();
			System.out.println("Colonne mot de passe : ");
			String passwordColumn = scanner.nextLine();

			scaffoldingArguments.setTableName(tableName);
			scaffoldingArguments.setEmailColumn(emailColumn);
			scaffoldingArguments.setPasswordColumn(passwordColumn);
		}

		// DATABASE
		System.out.println("#########################################");
		System.out.println("#			     DATABASE				#");
		System.out.println("#########################################");
		System.out.println("Base de donnees disponibles: ");
		for (String databaseName : ScaffoldDatabaseInfomations.getAllAvailabeDatabaseConnections()) System.out.println(databaseName);
		System.out.println("");

		System.out.println("Database : ");
		String database = scanner.nextLine();
		System.out.println("Host : ");
		String host = scanner.nextLine();
		System.out.println("Database name : ");
		String databaseName = scanner.nextLine();
		System.out.println("User name : ");
		String user = scanner.nextLine();
		System.out.println("User password : ");
		String password = scanner.nextLine();

		// MODELS
		System.out.println("#########################################");
		System.out.println("#			     MODELS					#");
		System.out.println("#########################################");

		System.out.println("Language : ");
		String language = scanner.nextLine();
		System.out.println("Output directory : ");
		String outputDir = scanner.nextLine();

		// FRAMEWORKS
		System.out.println("#########################################");
		System.out.println("#			     FRAMEWORKS				#");
		System.out.println("#########################################");

		System.out.println("Framework : ");
		String framework = scanner.nextLine();

		// CONTROLLERS
		System.out.println("#########################################");
		System.out.println("#			  CONTROLLERS				#");
		System.out.println("#########################################");

		System.out.println("Controller path: ");
		String controller = scanner.nextLine();

		// VIEWS
		System.out.println("#########################################");
		System.out.println("#			     VIEWS					#");
		System.out.println("#########################################");

		System.out.println("View : ");
		String view = scanner.nextLine();
		System.out.println("Output directory for view : ");
		String viewOutputDir = scanner.nextLine();
		System.out.println("Utiliser pagination? (Y/N) : ");
		String pagination = scanner.nextLine();

		if (pagination.equalsIgnoreCase("y")) usePagination = true;

		scaffoldingArguments.setAuthentification(useAuth);

		scaffoldingArguments.setHost(host);
		scaffoldingArguments.setDatabase(database);
		scaffoldingArguments.setDatabaseName(databaseName);
		scaffoldingArguments.setUser(user);
		scaffoldingArguments.setPassword(password);
		scaffoldingArguments.setOutputDir(outputDir);
		scaffoldingArguments.setLanguage(language);
		scaffoldingArguments.setController(controller);
		scaffoldingArguments.setFramework(framework);
		scaffoldingArguments.setView(view);
		scaffoldingArguments.setViewoutputdir(viewOutputDir);
		scaffoldingArguments.setUsePagination(usePagination);

		return scaffoldingArguments;
  	}
}