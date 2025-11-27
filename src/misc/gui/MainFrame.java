package gui;

import gui.pages.*;
import java.awt.*;
import javax.swing.*;
import repository.ClassRepository;
import repository.RoomRepository;
import repository.ScheduleRepository;

/**
 * Main Application Frame
 * Contains tabbed interface for all management panels
 * Manages repository injection to child panels
 */
public class MainFrame extends JFrame {

    // Color scheme constants for consistent UI styling
    public static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    public static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    public static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    public static final Color DANGER_COLOR = new Color(231, 76, 60);
    public static final Color WARNING_COLOR = new Color(241, 196, 15);
    public static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    public static final Color PANEL_COLOR = Color.WHITE;
    public static final Color TEXT_COLOR = new Color(44, 62, 80);

    // Repositories for database interaction
    private ClassRepository classRepository;
    private RoomRepository roomRepository;
    private ScheduleRepository scheduleRepository;

    /**
     * Constructor with dependency injection
     * @param classRepository Repository for class data management
     * @param roomRepository Repository for room data management
     * @param scheduleRepository Repository for schedule data management
     */
    public MainFrame(ClassRepository classRepository, RoomRepository roomRepository, ScheduleRepository scheduleRepository) {
        this.classRepository = classRepository;
        this.roomRepository = roomRepository;
        this.scheduleRepository = scheduleRepository;
        
        setTitle("Classroom Management System");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        createUI();
    }
    
    /**
     * Creates the main user interface
     * Sets up tabbed pane with Dashboard, Classes, Rooms, Schedule, Teachers tabs
     */
    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Arial", Font.PLAIN, 14));
        tabs.addTab("Dashboard", new DashboardPanel());
        tabs.addTab("Classes", new ClassesPanel(classRepository));
        tabs.addTab("Rooms", new RoomsPanel(roomRepository));
        tabs.addTab("Schedule", new SchedulePanel(scheduleRepository));
        tabs.addTab("Teachers", new TeachersPanel());
        
        mainPanel.add(tabs, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(30, 87, 153), w, 0, new Color(41, 128, 185));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        header.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        JLabel title = new JLabel("Classroom Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        return header;
    }
}
