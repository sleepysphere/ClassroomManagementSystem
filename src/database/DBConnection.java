package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn;

    private static final String URL = "jdbc:mysql://localhost:3306/class_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBConnection() {} // Prevent instantiation

    public static boolean connect() {
        try {
            // 🔹 IMPORTANT: Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 🔹 Now connect to database
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database Connected Successfully!");
            return true;

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found! Add the JAR to project.");
            return false;

        } catch (SQLException e) {
            System.err.println("Connection Failed: " + e.getMessage());
            return false;
        }
    }

    public static Connection getConnection() {
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
