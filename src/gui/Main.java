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

        // 1. Let's assume ClassID 1 exists (from your previous tests). 
        // Let's move it to Room #2 (Make sure RoomID 2 exists in your DB first!)
        // We create a Schedule object with the SAME ID (1) but NEW data.
        Schedule updatedClass = new Schedule(
            1, // Target ClassID 1
            1, 1, "Lecture", 
            java.time.LocalDate.of(2025, 1, 1), 
            java.time.LocalDate.of(2025, 5, 1), 
            2, // <--- CHANGING ROOM TO ID 2
            "Friday", 
            java.time.LocalTime.of(14, 0), 
            java.time.LocalTime.of(16, 0), 
            "SS2026"
        );

        // TEST UPDATE
        if (ScheduleRepositorySQL.updateSchedule(updatedClass)) {
            System.out.println("Update Successful: Class 1 moved to Room 2.");
        } else {
            System.out.println("Update Failed (Does Class 1 or Room 2 exist?)");
        }

        // TEST DELETE
        if (ScheduleRepositorySQL.deleteSchedule(1)) {
            System.out.println("Delete Successful: Class 1 removed.");
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
