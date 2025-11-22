package gui;

import database.DBConnection;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // Attempt to connect to the DB first
        if (!DBConnection.connect()) {
            System.out.println("App cannot start without database connection.");
            System.exit(0);
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
