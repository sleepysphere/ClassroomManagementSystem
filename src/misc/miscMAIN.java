package misc;

import javax.swing.SwingUtilities;

/* 
public class miscMAIN {

    static boolean connStatus = false;

    public static void main(String[] args) {

        connStatus = miscDBCONN.connect(); // CONNECT TO DB

        if (connStatus)
            System.out.println("Successfully connected to database");
        else
            System.out.println("Failed to connect to database");

        SwingUtilities.invokeLater(() -> {
            
            if (connStatus) {
                miscGUI gui = new miscGUI();
                gui.setVisible(true);
                System.out.println("Successfully initialized GUI");
            }
            else{
                System.out.println("GUI not initialized due to DB connection failure");
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            miscDBCONN.disconnect(); // DISCONNECT FROM DB
            if (connStatus)
                System.out.println("Successfully closed GUI"); // CLOSE GUI
        }));
    }
}
*/