package gui;

import javax.swing.SwingUtilities;

/**
 * Entry point of the application.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
