package gui;

import database.DBConnection;
import model.*;
import repository.sql.*;
import javax.swing.SwingUtilities;
import java.sql.SQLException;
import java.time.LocalTime;


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

        // Create a test Schedule object
        // Constructor: ClassID, CourseID, RoomID, InstructorID, Day, StartTime, EndTime
        Schedule testSchedule = new Schedule(
            0,                  // ClassID (0 for new entry)
            1,                  // CourseID
            1,                  // RoomID
            1,                  // InstructorID
            "Monday",           // DayOfWeek
            LocalTime.of(8, 0), // StartTime
            LocalTime.of(10, 0), // EndTime
            "Fall 2024"          // Semester
        );

        // Try to add the schedule to the database
        boolean result = ScheduleRepositorySQL.addSchedule(testSchedule);
        
        if (result) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed to add schedule.");
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
