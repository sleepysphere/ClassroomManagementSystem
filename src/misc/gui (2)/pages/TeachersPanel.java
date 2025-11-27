package gui.pages;

import gui.MainFrame;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Instructor;
import repository.sql.InstructorRepositorySQL;

/**
 * Teachers Management Panel
 * Provides interface to add, edit, delete and view list of teachers/instructors
 */
public class TeachersPanel extends JPanel {

    // UI Components
    private JTable teacherTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton, refreshButton;

    /**
     * Constructor
     */
    public TeachersPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(MainFrame.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        
        refreshTable();
    }

    /**
     * Creates header panel containing title and description
     * @return JPanel containing "Teacher Management" title
     */
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
    
    /**
     * Creates table panel with scrollable teacher list
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MainFrame.PANEL_COLOR);
        
        String[] columnNames = {"ID", "First Name", "Last Name", "Email", "Phone", "Department", "Hire Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        teacherTable = new JTable(tableModel);
        teacherTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        teacherTable.setRowHeight(30);
        teacherTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        teacherTable.getTableHeader().setBackground(MainFrame.PRIMARY_COLOR);
        teacherTable.getTableHeader().setForeground(Color.WHITE);
        teacherTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teacherTable.setSelectionBackground(new Color(52, 152, 219, 100));
        
        JScrollPane scrollPane = new JScrollPane(teacherTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Creates button panel with action buttons
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBackground(MainFrame.BACKGROUND_COLOR);
        
        addButton = createStyledButton("Add Teacher", MainFrame.SUCCESS_COLOR);
        editButton = createStyledButton("Edit Teacher", MainFrame.WARNING_COLOR);
        deleteButton = createStyledButton("Delete Teacher", MainFrame.DANGER_COLOR);
        refreshButton = createStyledButton("Refresh", MainFrame.SECONDARY_COLOR);
        
        addButton.addActionListener(e -> showAddTeacherDialog());
        editButton.addActionListener(e -> showEditTeacherDialog());
        deleteButton.addActionListener(e -> deleteSelectedTeacher());
        refreshButton.addActionListener(e -> refreshTable());
        
        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(refreshButton);
        
        return panel;
    }
    
    /**
     * Creates styled button with hover effect
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(140, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    /**
     * Refreshes the teacher table with current data
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        // TODO: Add getAllInstructors() method to InstructorRepositorySQL
        // For now, table will be empty until that method is implemented
    }
    
    /**
     * Shows dialog to add new teacher
     */
    private void showAddTeacherDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add New Teacher", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 450);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField firstNameField = new JTextField();
        firstNameField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField lastNameField = new JTextField();
        lastNameField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField phoneField = new JTextField();
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField departmentField = new JTextField();
        departmentField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel hireDateLabel = new JLabel("Hire Date:");
        hireDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        JPanel hireDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        hireDatePanel.setBackground(MainFrame.PANEL_COLOR);
        
        SpinnerNumberModel yearModel = new SpinnerNumberModel(2024, 2000, 2030, 1);
        JSpinner yearSpinner = new JSpinner(yearModel);
        yearSpinner.setPreferredSize(new Dimension(70, 25));
        
        SpinnerNumberModel monthModel = new SpinnerNumberModel(1, 1, 12, 1);
        JSpinner monthSpinner = new JSpinner(monthModel);
        monthSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel dayModel = new SpinnerNumberModel(1, 1, 31, 1);
        JSpinner daySpinner = new JSpinner(dayModel);
        daySpinner.setPreferredSize(new Dimension(50, 25));
        
        hireDatePanel.add(new JLabel("Year:"));
        hireDatePanel.add(yearSpinner);
        hireDatePanel.add(new JLabel("Month:"));
        hireDatePanel.add(monthSpinner);
        hireDatePanel.add(new JLabel("Day:"));
        hireDatePanel.add(daySpinner);
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(departmentLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(departmentField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(hireDateLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(hireDatePanel, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("Save", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String department = departmentField.getText().trim();
            
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                LocalDate hireDate = LocalDate.of(
                    (Integer) yearSpinner.getValue(),
                    (Integer) monthSpinner.getValue(),
                    (Integer) daySpinner.getValue()
                );
                
                Instructor newInstructor = new Instructor(0, firstName, lastName, email, phone, department, hireDate);
                
                if (InstructorRepositorySQL.addInstructor(newInstructor)) {
                    JOptionPane.showMessageDialog(dialog, "Teacher added successfully!");
                    refreshTable();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to add teacher!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid date or data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    /**
     * Shows dialog to edit selected teacher
     */
    private void showEditTeacherDialog() {
        int selectedRow = teacherTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a teacher to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int instructorId = (int) tableModel.getValueAt(selectedRow, 0);
        String currentFirstName = (String) tableModel.getValueAt(selectedRow, 1);
        String currentLastName = (String) tableModel.getValueAt(selectedRow, 2);
        String currentEmail = (String) tableModel.getValueAt(selectedRow, 3);
        String currentPhone = (String) tableModel.getValueAt(selectedRow, 4);
        String currentDepartment = (String) tableModel.getValueAt(selectedRow, 5);
        
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Teacher", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 450);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        
        JTextField firstNameField = new JTextField(currentFirstName);
        JTextField lastNameField = new JTextField(currentLastName);
        JTextField emailField = new JTextField(currentEmail);
        JTextField phoneField = new JTextField(currentPhone);
        JTextField departmentField = new JTextField(currentDepartment);
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        formPanel.add(departmentField, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("Update", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String department = departmentField.getText().trim();
            
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Instructor updatedInstructor = new Instructor(instructorId, firstName, lastName, email, phone, department, LocalDate.now());
            
            if (InstructorRepositorySQL.updateInstructor(updatedInstructor)) {
                JOptionPane.showMessageDialog(dialog, "Teacher updated successfully!");
                refreshTable();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to update teacher!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    /**
     * Deletes selected teacher
     */
    private void deleteSelectedTeacher() {
        int selectedRow = teacherTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a teacher to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this teacher?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            int instructorId = (int) tableModel.getValueAt(selectedRow, 0);
            
            if (InstructorRepositorySQL.deleteInstructor(instructorId)) {
                JOptionPane.showMessageDialog(this, "Teacher deleted successfully!");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete teacher!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
