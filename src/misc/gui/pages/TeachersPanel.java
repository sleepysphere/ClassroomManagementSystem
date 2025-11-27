package gui.pages;

import gui.MainFrame;
import java.awt.*;
import javax.swing.*;

/**
 * Teachers Management Panel (Coming Soon)
 * Placeholder panel for future teacher management functionality
 * Will manage teacher information and assignments
 */
public class TeachersPanel extends JPanel {

    /**
     * Constructor - initializes layout with header and coming soon message
     */
    public TeachersPanel() {
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
     * Creates "Coming Soon" placeholder panel
     * Displays message that teacher management feature is not yet implemented
     * @return JPanel with centered coming soon message
     */
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
    }
}
