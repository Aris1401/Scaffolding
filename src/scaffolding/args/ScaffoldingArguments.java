package scaffolding.args;

import java.util.Arrays;

public class ScaffoldingArguments {
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

    public String getPackage() {
        String namespace = outputDir;
        namespace = namespace.replace("./", "");
        namespace = namespace.replace("src/", "");
        namespace = namespace.replace("/", ".");

        return namespace;
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
                '}';
    }
}
