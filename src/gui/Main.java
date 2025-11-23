package gui;

import database.DBConnection;
import model.*;
import repository.sql.*;
import javax.swing.SwingUtilities;
import java.sql.SQLException;
import java.time.LocalDate;


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

        // Create a test Instructor object
        // Constructor: ID, Name, Code, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit
        Instructor testInstructor = new Instructor(
            0, 
            "John", 
            "Doe", 
            "john.doe@example.com", 
            "555-1234", 
            "Computer Science", 
            LocalDate.of(2020, 1, 15)
        );

        // Try to add the instructor to the database
        boolean result = InstructorRepositorySQL.addInstructor(testInstructor);
        
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
