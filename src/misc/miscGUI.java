package misc;

import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;

public class miscGUI extends JFrame{
    public miscGUI() {
        setTitle("Misc GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add components here
        JLabel label = new JLabel("This is a miscellaneous GUI window.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        LocalDate date = miscDatePicker.pickDate(this);
        if (date != null) {
            System.out.println("User picked: " + date);
        }
    }
    
}
