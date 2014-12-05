package main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQLite{

	/**
	 * URL de connection
	 */
	private static String url = "jdbc:postgresql://localhost:5432/Societe";
	/**
	 * Nom du user
	 */
	private static String user = "postgres";
	/**
	 * Mot de passe du user
	 */
	private static String passwd = "postgres";
	/**
	 * Objet Connection
	 */
	
	private static Connection connect;
	
	/**
	 * Méthode qui va nous retourner notre instance
	 * et la créer si elle n'existe pas...
	 * @return
	 */
	public static Connection getInstance(){
		//if(connect == null){
			    try {
			      Class.forName("org.sqlite.JDBC");
			      connect = DriverManager.getConnection("jdbc:sqlite:browser.db");
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
			//    System.out.println("Opened database successfully");		
		return connect;	
	}	
}

