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
            // 🔹 Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 🔹 Create and return new connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            conn.close();
            Statement stmt = conn.createStatement();
            stmt.close();
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

    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database Disconnected Successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to disconnect: " + e.getMessage());
        }
    }

}
