package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/class_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBConnection() {} // Prevent instantiation


    public static Connection getConnection() throws SQLException {
        try {
            // 🔹 Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 🔹 Create and return new connection
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found! Add the JAR to project.", e);
        }
    }

    public static void disconnect() {
        // This method is no longer needed since connections are created per request
        // Users should close connections in try-with-resources or finally blocks
        System.out.println("Note: Close connections using try-with-resources or conn.close()");
    }
}
