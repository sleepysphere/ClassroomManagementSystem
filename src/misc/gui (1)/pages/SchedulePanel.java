package gui.pages;

import gui.MainFrame;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Schedule;
import repository.sql.ScheduleRepositorySQL;

/**
 * Schedule Management Panel
 * Provides interface to add, delete and view class schedules
 * Manages class-room-time assignments
 */
public class SchedulePanel extends JPanel {

    // UI Components
    private JTable scheduleTable;           // Table displaying list of schedules
    private DefaultTableModel tableModel;   // Data model for the table
    private JButton addButton, deleteButton, refreshButton, todayButton;  // Action buttons

    /**
     * Constructor - initializes the panel
     */
    public SchedulePanel() {
        
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
     * @return JPanel containing "Schedule Management" title
     */
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MainFrame.PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        JLabel titleLabel = new JLabel("Schedule Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(52, 73, 94));
        
        JLabel descLabel = new JLabel("View and manage class schedules");
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
     * Creates panel containing table displaying list of schedules
     * Table has columns: ID, Class ID, Room ID, Start Time, End Time, Duration
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
        
        String[] columns = {"Class ID", "Course ID", "Instructor ID", "Room ID", "Day", "Start Time", "End Time", "Semester"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        scheduleTable = new JTable(tableModel);
        scheduleTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        scheduleTable.setRowHeight(30);
        scheduleTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        scheduleTable.getTableHeader().setBackground(MainFrame.WARNING_COLOR);
        scheduleTable.getTableHeader().setForeground(Color.WHITE);
        scheduleTable.setSelectionBackground(new Color(241, 196, 15, 100));
        
        scheduleTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            private int hoveredRow = -1;
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                int row = scheduleTable.rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    scheduleTable.repaint();
                }
            }
        });
        scheduleTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
                        c.setBackground(new Color(255, 250, 230));
                    }
                }
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates panel containing action buttons
     * Includes: Add Schedule, Delete Schedule, Today's Schedule, Refresh buttons
     * @return JPanel containing action buttons
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panel.setBackground(MainFrame.BACKGROUND_COLOR);
        
        addButton = createStyledButton("Add Schedule", MainFrame.SUCCESS_COLOR);
        deleteButton = createStyledButton("Delete Schedule", MainFrame.DANGER_COLOR);
        todayButton = createStyledButton("Today's Schedule", MainFrame.PRIMARY_COLOR);
        refreshButton = createStyledButton("Refresh", MainFrame.SECONDARY_COLOR);
        
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(todayButton);
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
        addButton.addActionListener(e -> showAddScheduleDialog());
        deleteButton.addActionListener(e -> deleteSelectedSchedule());
        todayButton.addActionListener(e -> showTodaySchedule());
        refreshButton.addActionListener(e -> refreshTable());
    }
    
    /**
     * Displays dialog for adding new schedule
     * Allows input: class ID, room ID, start date/time, end date/time
     * Uses spinners for date/time input and performs validation
     * Saves to database via repository
     */
    private void showAddScheduleDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add New Schedule", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 550);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel courseIdLabel = new JLabel("Course ID:");
        courseIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField courseIdField = new JTextField();
        courseIdField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel instructorIdLabel = new JLabel("Instructor ID:");
        instructorIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField instructorIdField = new JTextField();
        instructorIdField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel roomIdLabel = new JLabel("Room ID:");
        roomIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField roomIdField = new JTextField();
        roomIdField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel sessionTypeLabel = new JLabel("Session Type:");
        sessionTypeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        String[] sessionTypes = {"Lecture", "Lab"};
        JComboBox<String> sessionTypeCombo = new JComboBox<>(sessionTypes);
        sessionTypeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel dayOfWeekLabel = new JLabel("Day of Week:");
        dayOfWeekLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        JComboBox<String> dayCombo = new JComboBox<>(days);
        dayCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel semesterLabel = new JLabel("Semester:");
        semesterLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField semesterField = new JTextField("Fall 2024");
        semesterField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        // Add course ID fields to form
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(courseIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(courseIdField, gbc);
        
        // Add instructor ID fields
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(instructorIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(instructorIdField, gbc);
        
        // Add room ID fields
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(roomIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(roomIdField, gbc);
        
        // Add session type fields
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(sessionTypeLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(sessionTypeCombo, gbc);
        
        // Add day of week fields
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(dayOfWeekLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(dayCombo, gbc);
        
        // Add semester fields
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(semesterLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(semesterField, gbc);
        
        JLabel startLabel = new JLabel("Start Date:");
        startLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        formPanel.add(startLabel, gbc);
        
        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        startPanel.setBackground(MainFrame.PANEL_COLOR);
        
        SpinnerNumberModel startYearModel = new SpinnerNumberModel(2024, 2020, 2030, 1);
        JSpinner startYearSpinner = new JSpinner(startYearModel);
        startYearSpinner.setPreferredSize(new Dimension(70, 25));
        
        SpinnerNumberModel startMonthModel = new SpinnerNumberModel(1, 1, 12, 1);
        JSpinner startMonthSpinner = new JSpinner(startMonthModel);
        startMonthSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel startDayModel = new SpinnerNumberModel(1, 1, 31, 1);
        JSpinner startDaySpinner = new JSpinner(startDayModel);
        startDaySpinner.setPreferredSize(new Dimension(50, 25));
        
        startPanel.add(new JLabel("Year:"));
        startPanel.add(startYearSpinner);
        startPanel.add(new JLabel("Month:"));
        startPanel.add(startMonthSpinner);
        startPanel.add(new JLabel("Day:"));
        startPanel.add(startDaySpinner);
        
        gbc.gridy = 7; gbc.gridwidth = 2;
        formPanel.add(startPanel, gbc);
        
        JLabel endLabel = new JLabel("End Date:");
        endLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridy = 8;
        formPanel.add(endLabel, gbc);
        
        JPanel endPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        endPanel.setBackground(MainFrame.PANEL_COLOR);
        
        SpinnerNumberModel endYearModel = new SpinnerNumberModel(2024, 2020, 2030, 1);
        JSpinner endYearSpinner = new JSpinner(endYearModel);
        endYearSpinner.setPreferredSize(new Dimension(70, 25));
        
        SpinnerNumberModel endMonthModel = new SpinnerNumberModel(6, 1, 12, 1);
        JSpinner endMonthSpinner = new JSpinner(endMonthModel);
        endMonthSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel endDayModel = new SpinnerNumberModel(1, 1, 31, 1);
        JSpinner endDaySpinner = new JSpinner(endDayModel);
        endDaySpinner.setPreferredSize(new Dimension(50, 25));
        
        endPanel.add(new JLabel("Year:"));
        endPanel.add(endYearSpinner);
        endPanel.add(new JLabel("Month:"));
        endPanel.add(endMonthSpinner);
        endPanel.add(new JLabel("Day:"));
        endPanel.add(endDaySpinner);
        
        gbc.gridy = 9;
        formPanel.add(endPanel, gbc);
        
        JLabel timeLabel = new JLabel("Time Slots:");
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridy = 10;
        formPanel.add(timeLabel, gbc);
        
        // Start time panel
        JPanel startTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        startTimePanel.setBackground(MainFrame.PANEL_COLOR);
        
        SpinnerNumberModel startHourModel = new SpinnerNumberModel(8, 0, 23, 1);
        JSpinner startHourSpinner = new JSpinner(startHourModel);
        startHourSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel startMinuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner startMinuteSpinner = new JSpinner(startMinuteModel);
        startMinuteSpinner.setPreferredSize(new Dimension(50, 25));
        
        startTimePanel.add(new JLabel("Start Time:"));
        startTimePanel.add(startHourSpinner);
        startTimePanel.add(new JLabel(":"));
        startTimePanel.add(startMinuteSpinner);
        
        gbc.gridy = 11;
        formPanel.add(startTimePanel, gbc);
        
        // End time panel
        JPanel endTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        endTimePanel.setBackground(MainFrame.PANEL_COLOR);
        
        SpinnerNumberModel endHourModel = new SpinnerNumberModel(10, 0, 23, 1);
        JSpinner endHourSpinner = new JSpinner(endHourModel);
        endHourSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel endMinuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner endMinuteSpinner = new JSpinner(endMinuteModel);
        endMinuteSpinner.setPreferredSize(new Dimension(50, 25));
        
        endTimePanel.add(new JLabel("End Time:"));
        endTimePanel.add(endHourSpinner);
        endTimePanel.add(new JLabel(":"));
        endTimePanel.add(endMinuteSpinner);
        
        gbc.gridy = 12;
        formPanel.add(endTimePanel, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("Save", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            String courseIdText = courseIdField.getText().trim();
            String instructorIdText = instructorIdField.getText().trim();
            String roomIdText = roomIdField.getText().trim();
            String semester = semesterField.getText().trim();
            
            if (courseIdText.isEmpty() || instructorIdText.isEmpty() || roomIdText.isEmpty() || semester.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int courseId, instructorId, roomId;
            try {
                courseId = Integer.parseInt(courseIdText);
                instructorId = Integer.parseInt(instructorIdText);
                roomId = Integer.parseInt(roomIdText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for IDs!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                LocalDate startDate = LocalDate.of(
                    (Integer) startYearSpinner.getValue(),
                    (Integer) startMonthSpinner.getValue(),
                    (Integer) startDaySpinner.getValue()
                );
                
                LocalDate endDate = LocalDate.of(
                    (Integer) endYearSpinner.getValue(),
                    (Integer) endMonthSpinner.getValue(),
                    (Integer) endDaySpinner.getValue()
                );
                
                LocalTime startTime = LocalTime.of(
                    (Integer) startHourSpinner.getValue(),
                    (Integer) startMinuteSpinner.getValue()
                );
                
                LocalTime endTime = LocalTime.of(
                    (Integer) endHourSpinner.getValue(),
                    (Integer) endMinuteSpinner.getValue()
                );
                
                if (endDate.isBefore(startDate)) {
                    JOptionPane.showMessageDialog(dialog, "End date must be after start date!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
                    JOptionPane.showMessageDialog(dialog, "End time must be after start time!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // ClassID will be auto-generated by database
                Schedule newSchedule = new Schedule(
                    0,
                    courseId,
                    instructorId,
                    (String) sessionTypeCombo.getSelectedItem(),
                    startDate,
                    endDate,
                    roomId,
                    (String) dayCombo.getSelectedItem(),
                    startTime,
                    endTime,
                    semester
                );
                
                boolean success = ScheduleRepositorySQL.addSchedule(newSchedule);
                
                if (success) {
                    refreshTable();
                    dialog.dispose();
                    JOptionPane.showMessageDialog(this, "Schedule added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to add schedule!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid date/time values: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
     * Deletes selected schedule from the table
     * Shows confirmation dialog before deletion
     */
    private void deleteSelectedSchedule() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a schedule to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int classId = (int) tableModel.getValueAt(selectedRow, 0);
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this schedule?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = ScheduleRepositorySQL.deleteSchedule(classId);
            
            if (success) {
                refreshTable();
                JOptionPane.showMessageDialog(this, "Schedule deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete schedule!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Shows today's schedule (placeholder for future implementation)
     * TODO: Implement actual filtering by today's date
     */
    private void showTodaySchedule() {
        JOptionPane.showMessageDialog(
            this, 
            "Today's schedule filter will be implemented with actual date filtering.",
            "Today's Schedule",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Refreshes the data table
     * Reloads entire list of schedules from database
     * and displays them in the table
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        
        // Note: ScheduleRepositorySQL doesn't have getAllSchedules method
        // For now, table will be empty until you add schedules
        // You may need to add a getAllSchedules() method to ScheduleRepositorySQL
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
