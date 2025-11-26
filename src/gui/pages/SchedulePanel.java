package gui.pages;

import gui.MainFrame;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ClassSchedule;
import repository.ScheduleRepository;
import repository.mock.ScheduleRepositoryMOCK;

public class SchedulePanel extends JPanel {

    private JTable scheduleTable;
    private DefaultTableModel tableModel;
    private JButton addButton, deleteButton, refreshButton, todayButton;
    
    private ScheduleRepository scheduleRepository;
    private int nextId = 1;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public SchedulePanel() {
        scheduleRepository = new ScheduleRepositoryMOCK();
        
        setLayout(new BorderLayout(15, 15));
        setBackground(MainFrame.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        
        loadSampleData();
        refreshTable();
    }

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
        
        // Thêm hiệu ứng hover
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
                    // Hiệu ứng hover
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

    private void setupButtonHandlers() {
        addButton.addActionListener(e -> showAddScheduleDialog());
        deleteButton.addActionListener(e -> deleteSelectedSchedule());
        todayButton.addActionListener(e -> showTodaySchedule());
        refreshButton.addActionListener(e -> refreshTable());
    }
    
    private void showAddScheduleDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add New Schedule", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JLabel classIdLabel = new JLabel("Class ID:");
        classIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField classIdField = new JTextField();
        classIdField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel roomIdLabel = new JLabel("Room ID:");
        roomIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField roomIdField = new JTextField();
        roomIdField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel startLabel = new JLabel("Start (yyyy-MM-dd HH:mm):");
        startLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField startField = new JTextField("2024-01-15 09:00");
        startField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel endLabel = new JLabel("End (yyyy-MM-dd HH:mm):");
        endLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField endField = new JTextField("2024-01-15 11:00");
        endField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        formPanel.add(classIdLabel);
        formPanel.add(classIdField);
        formPanel.add(roomIdLabel);
        formPanel.add(roomIdField);
        formPanel.add(startLabel);
        formPanel.add(startField);
        formPanel.add(endLabel);
        formPanel.add(endField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("💾 Save", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("❌ Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            try {
                int classId = Integer.parseInt(classIdField.getText().trim());
                int roomId = Integer.parseInt(roomIdField.getText().trim());
                LocalDateTime start = LocalDateTime.parse(startField.getText().trim(), formatter);
                LocalDateTime end = LocalDateTime.parse(endField.getText().trim(), formatter);
                
                if (end.isBefore(start) || end.isEqual(start)) {
                    JOptionPane.showMessageDialog(dialog, "End time must be after start time!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                ClassSchedule newSchedule = new ClassSchedule(nextId++, classId, roomId, start, end);
                scheduleRepository.save(newSchedule);
                
                refreshTable();
                dialog.dispose();
                
                JOptionPane.showMessageDialog(this, "Schedule added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // call to function to add to database

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for IDs!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid date format! Use: yyyy-MM-dd HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
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
    
    private void showTodaySchedule() {
        JOptionPane.showMessageDialog(
            this, 
            "Today's schedule filter will be implemented with actual date filtering.",
            "Today's Schedule",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        
        // Giả sử scheduleRepository có phương thức findAll() (cần thêm vào interface)
        // Hiện tại chỉ load dữ liệu mẫu
        loadSampleData();
    }
    
    private void loadSampleData() {
        // Sample data for demo
        tableModel.setRowCount(0);
        
        LocalDateTime now = LocalDateTime.now();
        
        Object[][] sampleData = {
            {1, 1, 1, "2024-01-15 09:00", "2024-01-15 11:00", 2.0},
            {2, 2, 2, "2024-01-15 13:00", "2024-01-15 15:00", 2.0},
            {3, 3, 1, "2024-01-16 10:00", "2024-01-16 12:00", 2.0},
            {4, 1, 3, "2024-01-16 14:00", "2024-01-16 16:00", 2.0}
        };
        
        for (Object[] row : sampleData) {
            tableModel.addRow(row);
        }
    }
    
    // Rounded border for buttons
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

