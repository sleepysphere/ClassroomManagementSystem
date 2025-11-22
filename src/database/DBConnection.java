package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/class_management";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static Connection connection = null;
}

