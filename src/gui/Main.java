package gui;

import database.DBConnection;
import model.Student;
import repository.sql.StudentRepository;
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

        // Create a test student with dummy data (matching Student constructor pattern)
        Student testStudent = new Student(
            0,                              // ID (0 for new student)
            "John",                         // First name
            "Doe",                          // Last name
            "555-123-4567",                 // Phone
            "john.doe@test.com",            // Email
            LocalDate.of(2000, 5, 15)       // Date of birth
        );

        // Try to add the student to the database
        boolean result = StudentRepository.addStudent(testStudent);
        
        if (result) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed to add student.");
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
