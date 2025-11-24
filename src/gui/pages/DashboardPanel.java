package gui.pages;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private JTable classTable;
    private JButton addButton, editButton, deleteButton;

    public DashboardPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Class Management DashBoard", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

    }
    
}
