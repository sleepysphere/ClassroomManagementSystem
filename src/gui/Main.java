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

        System.out.println("\n--- Attempting to Cancel a Class ---");
        ScheduleException cancelRequest = new ScheduleException(
            0, 1, java.time.LocalDate.now().plusDays(1), "Cancelled", null, null, null, 0
        );

        if (ScheduleExceptionRepositorySQL.addScheduleException(cancelRequest)) {
            System.out.println("Class Cancelled Successfully!");
        } else {
            System.out.println("Cancellation Failed.");
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
