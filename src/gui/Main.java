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

        System.out.println("\n--- Testing STUDENT Update & Delete ---");

        // 1. Create a Student object with the SAME ID (1) but NEW details
        // (Assume we are changing their name to "Updated Name")
        Student updatedStudent = new Student(
            1, // Target StudentID 1
            "Updated", 
            "Name", 
            "555-999-9999", 
            "updated.email@test.com", 
            java.time.LocalDate.of(2000, 1, 1)
        );

        // TEST UPDATE
        if (StudentRepositorySQL.updateStudent(updatedStudent)) {
            System.out.println("Student 1 Updated Successfully!");
        } else {
            System.out.println("Update Failed.");
        }

        // TEST DELETE
        // (Warning: This will also delete their Enrollments!)
        if (StudentRepositorySQL.deleteStudent(1)) {
            System.out.println("Student 1 Deleted Successfully!");
        } else {
            System.out.println("Delete Failed.");
        }

        // If connected → load GUI
        //SwingUtilities.invokeLater(() -> {
        //    MainFrame mainFrame = new MainFrame();
        //    mainFrame.setVisible(true);
        //});

        // Ensure DB disconnect when app closes
        Runtime.getRuntime().addShutdownHook(new Thread(DBConnection::disconnect));
    }
}
