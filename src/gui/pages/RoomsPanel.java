package gui.pages;

import javax.swing.*;
import java.awt.*;

public class RoomsPanel extends JPanel {

    private JTable classTable;
    private JButton addButton, editButton, deleteButton;

    public RoomsPanel() {

        String[] columns = {"Room ID","Room Number","Room Type","Capacity"};
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
            JOptionPane.showMessageDialog(this, "Add (not yet implemented)")
        );

        editButton.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Edit (not yet implemented)")
        );

        deleteButton.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Delete (not yet implemented)")
        );
    }

}
