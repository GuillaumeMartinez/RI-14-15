package main.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OperationsSQLite {
	
	private Connection connect = ConnectionSQLite.getInstance();

	public static void createTables() {
		dropTables();
		createDocumentsTable();
		createDescriptorsTable();
		createDescRelationsTable();
	}

	public static void dropTables() {
		dropDocumentsTable();
		dropDescriptorsTable();
		dropDescRelationsTable();
	}

	private static void createDocumentsTable() {

		Statement stmt = null;
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "CREATE TABLE Documents "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " TITLE           TEXT    NOT NULL, "
					+ " URL            TEXT     NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");

	}

	private static void createDescriptorsTable() {

		Statement stmt = null;
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "CREATE TABLE Descriptors "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " WORD TEXT UNIQUE )";
			stmt.executeUpdate(sql);
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");

	}

	private static void createDescRelationsTable() {

		Statement stmt = null;
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "CREATE TABLE DescRelations "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " idDocumentRef            LONG     NOT NULL, "
					+ " idDescriptorRef            LONG     NOT NULL,"
					+ " position            INTEGER     NOT NULL,"
					+ " importance            INTEGER     NOT NULL,"
					+ " FOREIGN KEY(idDocumentRef) REFERENCES Documents(id),"
					+ " FOREIGN KEY(idDescriptorRef) REFERENCES Descriptors(id))";
			stmt.executeUpdate(sql);
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");

	}

	private static void dropDocumentsTable() {

		Statement stmt = null;
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "DROP TABLE IF EXISTS Documents ";
			stmt.executeUpdate(sql);
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table droped successfully");

	}

	private static void dropDescriptorsTable() {

		Statement stmt = null;
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "DROP TABLE IF EXISTS Descriptors ";
			stmt.executeUpdate(sql);
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table droped successfully");

	}

	private static void dropDescRelationsTable() {

		Statement stmt = null;
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "DROP TABLE IF EXISTS DescRelations ";
			stmt.executeUpdate(sql);
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table droped successfully");

	}

	public static int getLastId(String tableName) {

		Statement stmt = null;
		ResultSet result = null;
		int res = 0;
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "select seq from sqlite_sequence where name=\'"
					+ tableName + "\'";
			result = stmt.executeQuery(sql);
			res = result.getInt(1);
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return res;
	}
	
	public static int findWord(String word) {
		Statement stmt = null;
		ResultSet result = null;
		int res = -1;
		try {				
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "SELECT id FROM Descriptors WHERE word = \'" + word +"\'";
			result = stmt.executeQuery(sql);
			if (result.next()){
			res = result.getInt(1);
			}
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return res;
		
	}
	
	public static int findWeightFromDesc(int idDoc, int idWord) {
		Statement stmt = null;
		ResultSet result = null;
		int res = 0;
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "SELECT importance FROM DescRelations WHERE idDocumentRef = " + idDoc +" AND " +
					"idDescriptorRef = " + idWord +"";		
			result = stmt.executeQuery(sql);
			while(result.next()){	
				res += result.getInt(1);
			}
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return res;
		
	}
	
	
	public static ArrayList<Integer> findpositionFromDesc(int idDoc, int idWord) {
		Statement stmt = null;
		ResultSet result = null;
		ArrayList<Integer> res = new ArrayList<Integer>();
		try {
			stmt = ConnectionSQLite.getInstance().createStatement();
			String sql = "SELECT position FROM DescRelations WHERE idDocumentRef = " + idDoc +" AND " +
					"idDescriptorRef = " + idWord +"";		
			result = stmt.executeQuery(sql);
			while(result.next()){	
				res.add(result.getInt(1));
			}
			stmt.close();
			ConnectionSQLite.getInstance().close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return res;
		
	}

}
