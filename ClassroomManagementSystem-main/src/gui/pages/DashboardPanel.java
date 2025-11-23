package gui.pages;

import gui.MainFrame;
import java.awt.*;
import javax.swing.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Welcome title
        add(createWelcomePanel(), BorderLayout.NORTH);
        
        // Statistics
        add(createStatsPanel(), BorderLayout.CENTER);
    }
    
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(MainFrame.PRIMARY_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        
        JLabel welcome = new JLabel("Welcome to Classroom Management System");
        welcome.setFont(new Font("Arial", Font.BOLD, 26));
        welcome.setForeground(Color.WHITE);
        panel.add(welcome);
        
        return panel;
    }
    
    private JPanel createStatsPanel() {
        // Tạo lưới 2x2 cho thống kê
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // thêm các thẻ thống kê
        panel.add(createStatCard("Classes", "12", MainFrame.PRIMARY_COLOR));
        panel.add(createStatCard("Rooms", "8", MainFrame.SUCCESS_COLOR));
        panel.add(createStatCard("Schedules", "25", MainFrame.WARNING_COLOR));
        panel.add(createStatCard("Teachers", "15", MainFrame.DANGER_COLOR));
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                // Gradient nền
                GradientPaint gp = new GradientPaint(0, 0, color, 0, getHeight(), color.darker());
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                // Bóng đổ
                g2.setColor(new Color(0, 0, 0, 30));
                g2.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 15, 15);
                g2.dispose();
            }
        };
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 48));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        card.add(valueLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(titleLabel);
        
        return card;
    }
}
