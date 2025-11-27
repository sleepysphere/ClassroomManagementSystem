package database;

import java.sql.*;

public class DBConnection {

    private static Connection conn;

    private static final String URL = "jdbc:mysql://localhost:3306/class_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBConnection() {} // Prevent instantiation

    public static boolean connect(){
        try {
             // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Only connect if not already connected
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("DB Connected Successfully");
            }
            return true;
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found! Add the JAR to project.");
            return false;
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;
        }
    }

    public static Connection getConnection(){
        return conn;
    }

    //for executing SELECT queries
    public static ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
    //for executing INSERT, UPDATE, DELETE queries
    public static int executeUpdate(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(query);
    }

    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("DB Disconnected Successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to disconnect: " + e.getMessage());
        }
    }

}
