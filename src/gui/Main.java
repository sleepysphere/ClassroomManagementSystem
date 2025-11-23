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

        Room testRoom = new Room(0, "A101", "CLASSROOM", 30);

        // Try to add the room to the database
        boolean result = RoomRepositorySQL.addRoom(testRoom);
        
        if (result) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed to add room.");
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
