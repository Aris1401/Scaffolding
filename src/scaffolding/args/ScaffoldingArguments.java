package scaffolding.args;

import java.util.Arrays;

public class ScaffoldingArguments {
    boolean authentification;
    String tableName;
    String emailColumn;
    String passwordColumn;
    String host;
    String database;
    String databaseName;
    String user;
    String password;
    String outputDir;
    String language;
    String[] models;
    String controller;
    String framework;
    String view;
    String viewoutputdir;
    boolean usePagination = false;
    String basePackage;

    public boolean isUsePagination() {
        return usePagination;
    }

    public void setUsePagination(boolean usePagination) {
        this.usePagination = usePagination;
    }

    public boolean getAuthentification() {
        return authentification;
    }

    public void setAuthentification(boolean authentification) {
        this.authentification = authentification;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEmailColumn() {
        return emailColumn;
    }

    public void setEmailColumn(String emailColumn) {
        this.emailColumn = emailColumn;
    }

    public String getPasswordColumn() {
        return passwordColumn;
    }

    public void setPasswordColumn(String passwordColumn) {
        this.passwordColumn = passwordColumn;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String[] getModels() {
        return models;
    }

    public void setModels(String[] models) {
        this.models = models;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getModelPackage() {
        String modelPackage = "";

        if (!getBasePackage().trim().isEmpty()) modelPackage += getBasePackage() + ".";
        modelPackage += outputDir;

        return modelPackage;
    }

    public String getControllerPackage() {
        String controllerPackage = "";

        if (!getBasePackage().trim().isEmpty()) controllerPackage += getBasePackage() + ".";
        controllerPackage += controller;

        return controllerPackage;
    }

    public String getNamespaceFrom(String path) {
        String namespace = path;
        namespace = namespace.replace("./", "");
        namespace = namespace.replace("src/", "");
        namespace = namespace.replace("/", ".");

        return namespace;
    }

    public String getDirsBasePackage() {
        String dir = "./src/res/";

        if(!getBasePackage().trim().isEmpty()) dir += getBasePackage().replace(".", "/") + "/";
        return dir;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getView(){
        return this.view;
    }

    public void setView(String view){
        this.view = view;
    }

    public String getViewoutputdir(){
        return this.viewoutputdir;
    }

    public void setViewoutputdir(String viewoutputdir){
        this.viewoutputdir = viewoutputdir;
    }

    @Override
    public String toString() {
        return "ScaffoldingArguments{" +
                "host='" + host + '\'' +
                ", database='" + database + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", outputDir='" + outputDir + '\'' +
                ", language='" + language + '\'' +
                ", models=" + Arrays.toString(models) +
                ", controller='" + controller + '\'' +
                ", view= '" + view + '\'' +
                ", viewoutputdir'" + viewoutputdir + '\'' +
                '}';
    }

    public static ScaffoldingArguments getDefinedArgs() {
        ScaffoldingArguments scaffoldingArguments = new ScaffoldingArguments();
//        boolean authentification;
//        String tableName;
//        String emailColumn;
//        String passwordColumn;
//        String host;
//        String database;
//        String databaseName;
//        String user;
//        String password;
//        String outputDir;
//        String language;
//        String[] models;
//        String controller;
//        String framework;
//        String view;
//        String viewoutputdir;
//        boolean usePagination = false;

        scaffoldingArguments.setAuthentification(true);
        scaffoldingArguments.setTableName("Utilisateur");
        scaffoldingArguments.setEmailColumn("email");
        scaffoldingArguments.setPasswordColumn("mot_de_passe");
        scaffoldingArguments.setHost("localhost");
        scaffoldingArguments.setDatabase("postgres");
        scaffoldingArguments.setDatabaseName("immobilier2");
        scaffoldingArguments.setUser("postgres");
        scaffoldingArguments.setPassword("root");
        scaffoldingArguments.setBasePackage("com.scaffolding.test");
        scaffoldingArguments.setOutputDir("models");
        scaffoldingArguments.setLanguage("java");
        scaffoldingArguments.setController("controllers");
        scaffoldingArguments.setFramework("springboot");
        scaffoldingArguments.setView("angular");
        scaffoldingArguments.setViewoutputdir("./src/res/views");
        scaffoldingArguments.setUsePagination(true);

        return scaffoldingArguments;
    }
}
