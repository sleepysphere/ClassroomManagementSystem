package gui;

import database.DBConnection;
import model.*;
import repository.sql.*;
import javax.swing.SwingUtilities;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {

        // Attempt to connect to the DB first
        try {
            DBConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("App cannot start without database connection.");
            e.printStackTrace();
            System.exit(0);
        }

        // Create a test Course object
        // Constructor: ID, Name, Code, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit
        Course testCourse = new Course(
            0, 
            "Intro to Java", 
            "CS101", 
            "Basic Java Programming concepts", 
            3.0, 
            true, 
            15, 
            30
        );

        // Try to add the course to the database
        boolean result = CourseRepositorySQL.addCourse(testCourse);
        
        if (result) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed to add course.");
        }

        // If connected → load GUI
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });

        // Ensure DB disconnect when app closes
        Runtime.getRuntime().addShutdownHook(new Thread(DBConnection::disconnect));
    }
}
