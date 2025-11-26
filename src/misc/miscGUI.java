package misc;

import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import java.util.List;

public class miscGUI extends JFrame {

    private JTextArea textArea;

    public miscGUI() {
        setTitle("Misc GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ------------------------------------------------------------
        // TOP PANEL: Buttons
        // ------------------------------------------------------------
        JPanel topPanel = new JPanel(new FlowLayout());
        JButton btnShowAll = new JButton("Show All Dates");
        JButton btnPickDate = new JButton("Pick a Date");

        topPanel.add(btnShowAll);
        topPanel.add(btnPickDate);
        add(topPanel, BorderLayout.NORTH);

        // ------------------------------------------------------------
        // CENTER: Scrollable text area (log area)
        // ------------------------------------------------------------
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(
            textArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        add(scrollPane, BorderLayout.CENTER);

        // ------------------------------------------------------------
        // ACTION: Show all DB dates
        // ------------------------------------------------------------
        btnShowAll.addActionListener(e -> {
            List<miscDPControl.DateTimeEntry> entries = miscDPControl.RetrieveUpcomingDateTimes();
            textArea.setText(""); // clear

            if (entries.isEmpty()) {
                textArea.append("No dates found in database.\n");
            } else {
                // Display all dates and times
                for (miscDPControl.DateTimeEntry entry : entries) {
                    textArea.append("Date: " + entry.date.toString()
                            + " | Start: " + (entry.startTime != null ? entry.startTime.toString() : "N/A")
                            + " | End: " + (entry.endTime != null ? entry.endTime.toString() : "N/A") + "\n");
                }

            }
        });

        // ------------------------------------------------------------
        // ACTION: Pick date and time
        // ------------------------------------------------------------
        btnPickDate.addActionListener(e -> {
            miscDatePicker.PickedDateTime dt = miscDatePicker.pickDateTime(this);
            if (dt != null) {
                // Store the date (you may expand DB to store times as well)
                miscDPControl.StoreDateTime(dt.date,dt.startTime,dt.endTime); // currently stores only the date

                // Append detailed info to the text area
                textArea.append("Stored: " + dt.date
                        + " | Start: " + dt.startTime
                        + " | End: " + dt.endTime + "\n");
            }
        });
    }
}
