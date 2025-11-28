package gui;

import javax.swing.SwingUtilities;
import database.*;

public class Main {

    static boolean ConnStatus = false; // DB CONNECTION STATUS

    public static void main(String[] args) {

        ConnStatus = DBConnection.connect(); // CONNECT TO DB
        if (ConnStatus)
            System.out.println("Successfully connected to database");
        else
            System.out.println("Failed to connect to database");

        if (ConnStatus) {
            SwingUtilities.invokeLater(() -> {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            });
        }
        else {
            System.out.println("GUI not initialized due to DB connection failure");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DBConnection.disconnect(); // DISCONNECT FROM DB
            if (ConnStatus)
                System.out.println("Successfully closed GUI"); // CLOSE GUI
        }));
    }
}
