package scaffolding.configurationInShell;

import java.util.Scanner;
import scaffolding.args.ScaffoldingArguments;

public class ScaffoldingConfigurationInShell{
    public static ScaffoldingArguments configureScaffolding(){
      Scanner scanner = new Scanner(System.in);
      ScaffoldingArguments scaffoldingArguments = new ScaffoldingArguments();

      System.out.println("Enable authentification ?! : ");
      String authentification = scanner.nextLine();
      System.out.println("Name of the table where the authentification data is : ");
      String tableName = scanner.nextLine();
      System.out.println("Name of the column for email : ");
      String emailColumn = scanner.nextLine();
      System.out.println("Name of the column for password : ");
      String passWordColumn = scanner.nextLine();
      System.out.println("Host : ");
      String host = scanner.nextLine();
      System.out.println("Database : ");
      String database = scanner.nextLine();
      System.out.println("Database name : ");
      String databaseName = scanner.nextLine();
      System.out.println("User name : ");
      String user = scanner.nextLine();
      System.out.println("User password : ");
      String password = scanner.nextLine();
      System.out.println("Output directory : ");
      String outputDir = scanner.nextLine();
      System.out.println("Language : ");
      String language = scanner.nextLine();
      System.out.println("Controller : ");
      String controller = scanner.nextLine();
      System.out.println("Framework : ");
      String framework = scanner.nextLine();
      System.out.println("View : ");
      String view = scanner.nextLine();
      System.out.println("Output directory for view : ");
      String viewoutputdir = scanner.nextLine();

      scaffoldingArguments.setAuthentification(authentification);
      scaffoldingArguments.setTableName(tableName);
      scaffoldingArguments.setEmailColumn(emailColumn);
      scaffoldingArguments.setPasswordColumn(passwordColumn);
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
      scaffoldingArguments.setViewoutputdir(viewoutputdir);
      return scaffoldingArguments;
    }
}