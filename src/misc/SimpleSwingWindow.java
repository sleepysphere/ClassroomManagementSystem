package misc;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleSwingWindow {

    private static boolean darkMode = false;

    public static void main(String[] args) throws Exception {

        // Load content from text file
        String content = Files.readString(Path.of("src\\misc\\message.txt"));

        // Create window
        JFrame frame = new JFrame("Simple Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Text area
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Scrollable
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Toggle button
        JButton toggleBtn = new JButton("Switch Theme");
        toggleBtn.addActionListener(e -> {
            darkMode = !darkMode;
            if (darkMode) {
                frame.getContentPane().setBackground(Color.DARK_GRAY);
                textArea.setBackground(new Color(50, 50, 50));
                textArea.setForeground(Color.WHITE);
            } else {
                frame.getContentPane().setBackground(null);
                textArea.setBackground(Color.WHITE);
                textArea.setForeground(Color.BLACK);
            }
        });

        // Layout
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(toggleBtn, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
