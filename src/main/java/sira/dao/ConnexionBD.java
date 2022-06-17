package sira.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

	  private static Connection connection;
	    private static String     url      = "jdbc:mysql://localhost:3306/projet_sira";
	    private static String     user     = "root";
	    private static String     password = "";

	    public static Connection getConnection() {
	        try {
	            Class.forName( "com.mysql.cj.jdbc.Driver" );
	        } catch ( ClassNotFoundException e ) {
	            System.out.println( "classe " + e.getMessage() );
	        }

	        try {
	            connection = DriverManager.getConnection( url, user, password );
	        } catch ( SQLException e ) {
	            System.out.println( "getConnexion : " + e.getMessage() );
	        }

	        return connection;
	    }

}
