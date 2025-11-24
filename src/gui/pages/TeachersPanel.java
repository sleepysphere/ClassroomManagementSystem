package gui.pages;

import gui.MainFrame;
import java.awt.*;
import javax.swing.*;

public class TeachersPanel extends JPanel {

    private JTable classTable;
    private JButton addButton, editButton, deleteButton;

    public TeachersPanel() {
<<<<<<< HEAD
        String[] columns = {"Teacher ID","First Name","Last Name","Email","Phone","Department","Hire Date"};
        Object[][] data = {}; // Empty for now
        classTable = new JTable(data, columns);
        add(new JScrollPane(classTable), BorderLayout.CENTER);

         // Button controls
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setupButtonHandlers();
    }

    private void setupButtonHandlers() {
        addButton.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Add Class (not yet implemented)")
        );

        editButton.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Edit Class (not yet implemented)")
        );

        deleteButton.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Delete Class (not yet implemented)")
        );
=======
        setLayout(new BorderLayout(15, 15));
        setBackground(MainFrame.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Coming soon panel
        JPanel centerPanel = createComingSoonPanel();
        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MainFrame.PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        JLabel titleLabel = new JLabel("Teacher Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(52, 73, 94));
        
        JLabel descLabel = new JLabel("Manage teacher information and assignments");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(new Color(127, 140, 141));
        
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setBackground(MainFrame.PANEL_COLOR);
        textPanel.add(titleLabel);
        textPanel.add(descLabel);
        
        panel.add(textPanel, BorderLayout.WEST);
        return panel;
    }
    
    private JPanel createComingSoonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(MainFrame.PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(50, 50, 50, 50)
        ));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JLabel titleLabel = new JLabel("Coming Soon");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(MainFrame.TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel messageLabel = new JLabel("Teacher management feature will be available soon");
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        messageLabel.setForeground(new Color(127, 140, 141));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(messageLabel);
        
        panel.add(contentPanel);
        return panel;
>>>>>>> dc6678f18732aeca40dd4fa8a80d0cf312834a2f
    }
}
