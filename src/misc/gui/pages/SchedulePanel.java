package gui.pages;

import gui.MainFrame;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ClassSchedule;
import repository.ScheduleRepository;

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
    
    // Repository for database interaction
    private ScheduleRepository scheduleRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor with dependency injection
     * @param scheduleRepository Repository for managing schedule data
     */
    public SchedulePanel(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
        
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
        
        String[] columns = {"ID", "Class ID", "Room ID", "Start Time", "End Time", "Duration (hrs)"};
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
        dialog.setSize(500, 450);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel classIdLabel = new JLabel("Class ID:");
        classIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField classIdField = new JTextField();
        classIdField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel roomIdLabel = new JLabel("Room ID:");
        roomIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField roomIdField = new JTextField();
        roomIdField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(classIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(classIdField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(roomIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(roomIdField, gbc);
        
        JLabel startLabel = new JLabel("Start Date & Time:");
        startLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        formPanel.add(startLabel, gbc);
        
        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        startPanel.setBackground(MainFrame.PANEL_COLOR);
        
        SpinnerNumberModel startYearModel = new SpinnerNumberModel(2024, 2020, 2030, 1);
        JSpinner startYearSpinner = new JSpinner(startYearModel);
        startYearSpinner.setPreferredSize(new Dimension(70, 25));
        
        SpinnerNumberModel startMonthModel = new SpinnerNumberModel(1, 1, 12, 1);
        JSpinner startMonthSpinner = new JSpinner(startMonthModel);
        startMonthSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel startDayModel = new SpinnerNumberModel(15, 1, 31, 1);
        JSpinner startDaySpinner = new JSpinner(startDayModel);
        startDaySpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel startHourModel = new SpinnerNumberModel(9, 0, 23, 1);
        JSpinner startHourSpinner = new JSpinner(startHourModel);
        startHourSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel startMinuteModel = new SpinnerNumberModel(0, 0, 59, 15);
        JSpinner startMinuteSpinner = new JSpinner(startMinuteModel);
        startMinuteSpinner.setPreferredSize(new Dimension(50, 25));
        
        startPanel.add(new JLabel("Year:"));
        startPanel.add(startYearSpinner);
        startPanel.add(new JLabel("Month:"));
        startPanel.add(startMonthSpinner);
        startPanel.add(new JLabel("Day:"));
        startPanel.add(startDaySpinner);
        startPanel.add(new JLabel("Hour:"));
        startPanel.add(startHourSpinner);
        startPanel.add(new JLabel("Min:"));
        startPanel.add(startMinuteSpinner);
        
        gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(startPanel, gbc);
        
        JLabel endLabel = new JLabel("End Date & Time:");
        endLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        gbc.gridy = 4;
        formPanel.add(endLabel, gbc);
        
        JPanel endPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        endPanel.setBackground(MainFrame.PANEL_COLOR);
        
        SpinnerNumberModel endYearModel = new SpinnerNumberModel(2024, 2020, 2030, 1);
        JSpinner endYearSpinner = new JSpinner(endYearModel);
        endYearSpinner.setPreferredSize(new Dimension(70, 25));
        
        SpinnerNumberModel endMonthModel = new SpinnerNumberModel(1, 1, 12, 1);
        JSpinner endMonthSpinner = new JSpinner(endMonthModel);
        endMonthSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel endDayModel = new SpinnerNumberModel(15, 1, 31, 1);
        JSpinner endDaySpinner = new JSpinner(endDayModel);
        endDaySpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel endHourModel = new SpinnerNumberModel(11, 0, 23, 1);
        JSpinner endHourSpinner = new JSpinner(endHourModel);
        endHourSpinner.setPreferredSize(new Dimension(50, 25));
        
        SpinnerNumberModel endMinuteModel = new SpinnerNumberModel(0, 0, 59, 15);
        JSpinner endMinuteSpinner = new JSpinner(endMinuteModel);
        endMinuteSpinner.setPreferredSize(new Dimension(50, 25));
        
        endPanel.add(new JLabel("Year:"));
        endPanel.add(endYearSpinner);
        endPanel.add(new JLabel("Month:"));
        endPanel.add(endMonthSpinner);
        endPanel.add(new JLabel("Day:"));
        endPanel.add(endDaySpinner);
        endPanel.add(new JLabel("Hour:"));
        endPanel.add(endHourSpinner);
        endPanel.add(new JLabel("Min:"));
        endPanel.add(endMinuteSpinner);
        
        gbc.gridy = 5;
        formPanel.add(endPanel, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("Save", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            String classIdText = classIdField.getText().trim();
            String roomIdText = roomIdField.getText().trim();
            
            if (classIdText.isEmpty() || roomIdText.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int classId, roomId;
            try {
                classId = Integer.parseInt(classIdText);
                roomId = Integer.parseInt(roomIdText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for IDs!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                LocalDateTime start = LocalDateTime.of(
                    (Integer) startYearSpinner.getValue(),
                    (Integer) startMonthSpinner.getValue(),
                    (Integer) startDaySpinner.getValue(),
                    (Integer) startHourSpinner.getValue(),
                    (Integer) startMinuteSpinner.getValue()
                );
                
                LocalDateTime end = LocalDateTime.of(
                    (Integer) endYearSpinner.getValue(),
                    (Integer) endMonthSpinner.getValue(),
                    (Integer) endDaySpinner.getValue(),
                    (Integer) endHourSpinner.getValue(),
                    (Integer) endMinuteSpinner.getValue()
                );
                
                if (end.isBefore(start) || end.isEqual(start)) {
                    JOptionPane.showMessageDialog(dialog, "End time must be after start time!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // ID will be auto-generated by database
                ClassSchedule newSchedule = new ClassSchedule(0, classId, roomId, start, end);
                scheduleRepository.save(newSchedule);
                
                refreshTable();
                dialog.dispose();
                
                JOptionPane.showMessageDialog(this, "Schedule added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid date/time values!", "Error", JOptionPane.ERROR_MESSAGE);
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
     * TODO: Need to call scheduleRepository.delete(id) to remove from database
     */
    private void deleteSelectedSchedule() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a schedule to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int scheduleId = (int) tableModel.getValueAt(selectedRow, 0);
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this schedule?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Schedule deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
     * Reloads entire list of schedules from database via repository
     * and displays them in the table with calculated duration
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        
        for (ClassSchedule schedule : scheduleRepository.findAll()) {
            long hours = java.time.Duration.between(schedule.getStartTime(), schedule.getEndTime()).toHours();
            Object[] row = {
                schedule.getId(),
                schedule.getClassId(),
                schedule.getRoomId(),
                schedule.getStartTime().format(formatter),
                schedule.getEndTime().format(formatter),
                hours
            };
            tableModel.addRow(row);
        }
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
