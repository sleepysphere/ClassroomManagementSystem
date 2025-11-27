package gui.pages;

import gui.MainFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Course;
import repository.sql.CourseRepositorySQL;

/**
 * Classes Management Panel
 * Provides interface to add, edit, delete and view list of courses
 */
public class ClassesPanel extends JPanel {

    // UI Components
    private JTable classTable;              // Table displaying list of courses
    private DefaultTableModel tableModel;   // Data model for the table
    private JButton addButton, editButton, deleteButton, refreshButton;  // Action buttons

    /**
     * Constructor - initializes the panel
     */
    public ClassesPanel() {
        
        setLayout(new BorderLayout(15, 15));
        setBackground(MainFrame.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Table panel
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        
        refreshTable();
    }

    /**
     * Creates header panel containing title and description
     * @return JPanel containing "Class Management" title
     */
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MainFrame.PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        JLabel titleLabel = new JLabel("Course Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(52, 73, 94));
        
        JLabel descLabel = new JLabel("Add, edit, and manage courses");
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
     * Creates panel containing table displaying list of classes
     * Table has columns: ID, Class Name, Requires Lab, Student Count
     * Features hover effect and alternating row colors
     * @return JPanel containing table with scroll pane
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MainFrame.PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        String[] columns = {"ID", "Course Code", "Course Name", "Credits", "Requires Lab"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        classTable = new JTable(tableModel);
        classTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        classTable.setRowHeight(30);
        classTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        classTable.getTableHeader().setBackground(MainFrame.PRIMARY_COLOR);
        classTable.getTableHeader().setForeground(Color.WHITE);
        classTable.setSelectionBackground(MainFrame.SECONDARY_COLOR);
        classTable.setSelectionForeground(Color.WHITE);
        
        classTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            private int hoveredRow = -1;
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                int row = classTable.rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    classTable.repaint();
                }
            }
        });
        classTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(248, 249, 250));
                    }
                    java.awt.Point p = table.getMousePosition();
                    if (p != null && table.rowAtPoint(p) == row) {
                        c.setBackground(new Color(230, 240, 255));
                    }
                }
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(classTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Creates panel containing action buttons
     * Includes: Add, Edit, Delete, Refresh buttons
     * @return JPanel containing action buttons
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panel.setBackground(MainFrame.BACKGROUND_COLOR);
        
        // Create styled buttons
        addButton = createStyledButton("Add Course", MainFrame.SUCCESS_COLOR);
        editButton = createStyledButton("Edit Course", MainFrame.WARNING_COLOR);
        deleteButton = createStyledButton("Delete Course", MainFrame.DANGER_COLOR);
        refreshButton = createStyledButton("Refresh", MainFrame.SECONDARY_COLOR);
        
        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(refreshButton);
        
        setupButtonHandlers();
        
        return panel;
    }
    
    /**
     * Creates button with custom styling (rounded corners, hover effect)
     * @param text Button text to display
     * @param color Background color of the button
     * @return Styled JButton
     */
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Simple hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    /**
     * Sets up event handlers for all buttons
     * Connects buttons to their corresponding handler methods
     */
    private void setupButtonHandlers() {
        addButton.addActionListener(e -> showAddCourseDialog());
        editButton.addActionListener(e -> showEditCourseDialog());
        deleteButton.addActionListener(e -> deleteSelectedCourse());
        refreshButton.addActionListener(e -> refreshTable());
    }
    
    /**
     * Displays dialog for adding new course
     * Allows input: course code, name, credits, lab requirement
     * Performs validation and saves to database via repository
     */
    private void showAddCourseDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add New Course", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        
        // Form fields
        JLabel codeLabel = new JLabel("Course Code:");
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField codeField = new JTextField();
        codeField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel nameLabel = new JLabel("Course Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel creditsLabel = new JLabel("Credits:");
        creditsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField creditsField = new JTextField();
        creditsField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel labLabel = new JLabel("Requires Lab:");
        labLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JCheckBox labCheckBox = new JCheckBox();
        
        JLabel limitLabel = new JLabel("Enrollment Limit:");
        limitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField limitField = new JTextField();
        limitField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        formPanel.add(codeLabel);
        formPanel.add(codeField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(creditsLabel);
        formPanel.add(creditsField);
        formPanel.add(labLabel);
        formPanel.add(labCheckBox);
        formPanel.add(limitLabel);
        formPanel.add(limitField);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("Save", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            String creditsText = creditsField.getText().trim();
            String limitText = limitField.getText().trim();
            
            // Validation
            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter course code and name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double credits = 0;
            int limit = 0;
            try {
                credits = Double.parseDouble(creditsText);
                limit = Integer.parseInt(limitText);
                if (credits <= 0 || limit <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Credits and limit must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create new course (ID will be auto-generated by database)
            Course newCourse = new Course(0, name, code, "", credits, labCheckBox.isSelected(), 0, limit, true);
            boolean success = CourseRepositorySQL.addCourse(newCourse);
            
            if (success) {
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to add course!", "Error", JOptionPane.ERROR_MESSAGE);
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
     * Displays dialog for editing selected course information
     * Loads current data from database and allows editing
     * Performs validation and updates to database
     */
    private void showEditCourseDialog() {
        int selectedRow = classTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int courseId = (int) tableModel.getValueAt(selectedRow, 0);
        Course selectedCourse = CourseRepositorySQL.getCourseById(courseId);
        
        if (selectedCourse == null) {
            JOptionPane.showMessageDialog(this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Course", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        
        // Form fields with current data
        JLabel codeLabel = new JLabel("Course Code:");
        codeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField codeField = new JTextField(selectedCourse.getCourseCode());
        codeField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel nameLabel = new JLabel("Course Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField nameField = new JTextField(selectedCourse.getCourseName());
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel creditsLabel = new JLabel("Credits:");
        creditsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField creditsField = new JTextField(String.valueOf(selectedCourse.getCredits()));
        creditsField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel labLabel = new JLabel("Requires Lab:");
        labLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JCheckBox labCheckBox = new JCheckBox("", selectedCourse.isRequiresLab());
        
        JLabel limitLabel = new JLabel("Enrollment Limit:");
        limitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField limitField = new JTextField(String.valueOf(selectedCourse.getEnrollmentLimit()));
        limitField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        formPanel.add(codeLabel);
        formPanel.add(codeField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(creditsLabel);
        formPanel.add(creditsField);
        formPanel.add(labLabel);
        formPanel.add(labCheckBox);
        formPanel.add(limitLabel);
        formPanel.add(limitField);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("💾 Update", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("❌ Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            String creditsText = creditsField.getText().trim();
            String limitText = limitField.getText().trim();
            
            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter course code and name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double credits = 0;
            int limit = 0;
            try {
                credits = Double.parseDouble(creditsText);
                limit = Integer.parseInt(limitText);
                if (credits <= 0 || limit <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Credits and limit must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Update information
            selectedCourse.setCourseCode(code);
            selectedCourse.setCourseName(name);
            selectedCourse.setCredits(credits);
            selectedCourse.setRequiresLab(labCheckBox.isSelected());
            selectedCourse.setEnrollmentLimit(limit);
            
            boolean success = CourseRepositorySQL.updateCourse(selectedCourse);
            
            if (success) {
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Course updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to update course!", "Error", JOptionPane.ERROR_MESSAGE);
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
     * Deletes selected course from the table
     * Shows confirmation dialog before deletion
     */
    private void deleteSelectedCourse() {
        int selectedRow = classTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int courseId = (int) tableModel.getValueAt(selectedRow, 0);
        String courseName = (String) tableModel.getValueAt(selectedRow, 2);
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete course: " + courseName + "?\nThis will remove ALL enrollments!",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = CourseRepositorySQL.deleteCourse(courseId);
            
            if (success) {
                refreshTable();
                JOptionPane.showMessageDialog(this, "Course deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete course!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Refreshes the data table
     * Reloads entire list of courses from database
     * and displays them in the table
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        
        // Note: CourseRepositorySQL doesn't have getAllCourses method
        // For now, table will be empty until you add courses
        // You may need to add a getAllCourses() method to CourseRepositorySQL
    }
    
    /**
     * Custom border class for creating rounded borders on components
     * (Currently not used, can be removed or kept for future use)
     */
    static class RoundedBorder implements javax.swing.border.Border {
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }
        
        public boolean isBorderOpaque() {
            return false;
        }
        
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
}
