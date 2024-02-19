package scaffolding.bdd.generic.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	public static Connection connectToOracleDatabase() {
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","billard","billard");
	    	
	    	return con;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
	    return null;
	}
	
	public static Connection connectToPostgresDatabase() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/systemecommercial", "postgres", "root");
			
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Connection connectToSQLiteDatabase() {
	    try {
	        Class.forName("org.sqlite.JDBC");
	        Connection con = DriverManager.getConnection("jdbc:sqlite:/path/to/database.db");
	        
	        return con;
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	public static Connection connectToMySQLDatabase() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_name", "username", "password");
	        
	        return con;
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
}
