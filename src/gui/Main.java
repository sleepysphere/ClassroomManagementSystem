package gui;

import database.DBConnection;
import model.*;
import repository.sql.*;
import javax.swing.SwingUtilities;
import java.sql.SQLException;
import java.time.LocalTime;
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

        System.out.println("\n--- Testing UPDATE & DELETE ---");

        System.out.println("\n--- Testing INSTRUCTOR Update & Delete ---");

        // 1. Create a dummy Instructor to test with (ID 99 just to be safe/distinct)
        // Note: In a real app, you'd add them, getting the ID back. 
        // For this test, ensure you have an instructor with ID 1, or change the ID below.
        Instructor updatedProf = new Instructor(
            1, // Target InstructorID 1
            "Professor", 
            "Updated", 
            "updated.prof@univ.edu", 
            "555-000-0000", 
            "Quantum Physics", // Changed Department
            java.time.LocalDate.of(2020, 9, 1)
        );
        
        // TEST UPDATE
        if (InstructorRepositorySQL.updateInstructor(updatedProf)) {
            System.out.println("Instructor 1 Updated Successfully!");
        } else {
            System.out.println("Update Failed (Does Instructor 1 exist?).");
        }
        
        // TEST DELETE
        if (InstructorRepositorySQL.deleteInstructor(1)) {
            System.out.println("Instructor 1 Deleted Successfully!");
        } else {
            System.out.println("Delete Failed.");
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
