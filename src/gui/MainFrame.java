package gui;

import javax.swing.*;
import java.awt.*;
import gui.pages.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("School Class Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // Each tab is a separate file now ✔
        tabs.addTab("Dashboard", new DashboardPanel());
        tabs.addTab("Classes", new ClassesPanel());
        tabs.addTab("Rooms", new RoomsPanel());
        tabs.addTab("Teachers", new TeachersPanel());
        tabs.addTab("Schedule", new SchedulePanel());

        add(tabs, BorderLayout.CENTER);
    }
}
