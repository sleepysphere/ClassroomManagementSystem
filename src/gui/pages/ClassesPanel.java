package gui.pages;

import javax.swing.*;
import java.awt.*;

public class ClassesPanel extends JPanel {

    private JTable classTable;
    private JButton addButton, editButton, deleteButton;

    public ClassesPanel() {
        setLayout(new BorderLayout());

        // Table
        String[] columns = {"ID", "Class Name", "Level", "Teacher"};
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
    }
}
