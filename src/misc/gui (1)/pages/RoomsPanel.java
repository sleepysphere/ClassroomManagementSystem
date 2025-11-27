package gui.pages;

import gui.MainFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Room;
import model.RoomType;
import repository.sql.RoomRepositorySQL;

/**
 * Rooms Management Panel
 * Provides interface to add, edit, delete and view list of rooms
 * Manages classrooms, labs, and other facilities
 */
public class RoomsPanel extends JPanel {

    // UI Components
    private JTable roomTable;               // Table displaying list of rooms
    private DefaultTableModel tableModel;   // Data model for the table
    private JButton addButton, editButton, deleteButton, refreshButton;  // Action buttons

    /**
     * Constructor - initializes the panel
     */
    public RoomsPanel() {
        
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
     * @return JPanel containing "Room Management" title
     */
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MainFrame.PANEL_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        JLabel titleLabel = new JLabel("Room Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(52, 73, 94));
        
        JLabel descLabel = new JLabel("Manage classrooms, labs, and other facilities");
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
     * Creates panel containing table displaying list of rooms
     * Table has columns: ID, Room Name, Type, Capacity
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
        
        String[] columns = {"ID", "Room Name", "Type", "Capacity"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        roomTable = new JTable(tableModel);
        roomTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        roomTable.setRowHeight(30);
        roomTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        roomTable.getTableHeader().setBackground(MainFrame.SUCCESS_COLOR);
        roomTable.getTableHeader().setForeground(Color.WHITE);
        roomTable.setSelectionBackground(new Color(46, 204, 113, 100));
        
        roomTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            private int hoveredRow = -1;
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                int row = roomTable.rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    roomTable.repaint();
                }
            }
        });
        roomTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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
                        c.setBackground(new Color(230, 255, 240));
                    }
                }
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates panel containing action buttons
     * Includes: Add Room, Edit Room, Delete Room, Refresh buttons
     * @return JPanel containing action buttons
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panel.setBackground(MainFrame.BACKGROUND_COLOR);
        
        addButton = createStyledButton("Add Room", MainFrame.SUCCESS_COLOR);
        editButton = createStyledButton("Edit Room", MainFrame.WARNING_COLOR);
        deleteButton = createStyledButton("Delete Room", MainFrame.DANGER_COLOR);
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
        addButton.addActionListener(e -> showAddRoomDialog());
        editButton.addActionListener(e -> showEditRoomDialog());
        deleteButton.addActionListener(e -> deleteSelectedRoom());
        refreshButton.addActionListener(e -> refreshTable());
    }
    
    /**
     * Displays dialog for adding new room
     * Allows input: room name, room type (CLASSROOM/LAB/etc), capacity
     * Performs validation and saves to database via repository
     */
    private void showAddRoomDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add New Room", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JLabel nameLabel = new JLabel("Room Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JComboBox<RoomType> typeCombo = new JComboBox<>(RoomType.values());
        typeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel capacityLabel = new JLabel("Capacity:");
        capacityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField capacityField = new JTextField();
        capacityField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(typeLabel);
        formPanel.add(typeCombo);
        formPanel.add(capacityLabel);
        formPanel.add(capacityField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("Save", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String capacityText = capacityField.getText().trim();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter room name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int capacity = 0;
            try {
                capacity = Integer.parseInt(capacityText);
                if (capacity <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Capacity must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid number for capacity!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            RoomType type = (RoomType) typeCombo.getSelectedItem();
            // ID will be auto-generated by database
            Room newRoom = new Room(0, name, type.name(), capacity);
            boolean success = RoomRepositorySQL.addRoom(newRoom);
            
            if (success) {
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Room added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to add room!", "Error", JOptionPane.ERROR_MESSAGE);
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
     * Displays dialog for editing selected room information
     * Loads current data from database and allows editing
     * Performs validation and updates to database
     */
    private void showEditRoomDialog() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int roomId = (int) tableModel.getValueAt(selectedRow, 0);
        Room selectedRoom = RoomRepositorySQL.getRoomById(roomId);
        
        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(this, "Room not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Room", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JLabel nameLabel = new JLabel("Room Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField nameField = new JTextField(selectedRoom.getRoomNumber());
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JComboBox<RoomType> typeCombo = new JComboBox<>(RoomType.values());
        typeCombo.setSelectedItem(RoomType.fromString(selectedRoom.getRoomType()));
        typeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JLabel capacityLabel = new JLabel("Capacity:");
        capacityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField capacityField = new JTextField(String.valueOf(selectedRoom.getCapacity()));
        capacityField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(typeLabel);
        formPanel.add(typeCombo);
        formPanel.add(capacityLabel);
        formPanel.add(capacityField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(MainFrame.PANEL_COLOR);
        
        JButton saveBtn = createStyledButton("💾 Update", MainFrame.SUCCESS_COLOR);
        JButton cancelBtn = createStyledButton("❌ Cancel", MainFrame.DANGER_COLOR);
        
        saveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String capacityText = capacityField.getText().trim();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter room name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int capacity = 0;
            try {
                capacity = Integer.parseInt(capacityText);
                if (capacity <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Capacity must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid number for capacity!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            selectedRoom.setRoomNumber(name);
            selectedRoom.setRoomType(((RoomType) typeCombo.getSelectedItem()).name());
            selectedRoom.setCapacity(capacity);
            
            boolean success = RoomRepositorySQL.updateRoom(selectedRoom);
            
            if (success) {
                refreshTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Room updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to update room!", "Error", JOptionPane.ERROR_MESSAGE);
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
     * Deletes selected room from the table
     * Shows confirmation dialog before deletion
     */
    private void deleteSelectedRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int roomId = (int) tableModel.getValueAt(selectedRow, 0);
        String roomName = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete room: " + roomName + "?\nThis will cancel all schedules in this room!",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = RoomRepositorySQL.deleteRoom(roomId);
            
            if (success) {
                refreshTable();
                JOptionPane.showMessageDialog(this, "Room deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete room!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Refreshes the data table
     * Reloads entire list of rooms from database
     * and displays them in the table
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        
        // Note: RoomRepositorySQL doesn't have getAllRooms method
        // For now, table will be empty until you add rooms
        // You may need to add a getAllRooms() method to RoomRepositorySQL
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
