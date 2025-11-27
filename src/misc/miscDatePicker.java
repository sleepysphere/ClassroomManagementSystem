package misc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class miscDatePicker {

    public static class PickedDateTime {
        public LocalDate date;
        public LocalTime startTime;
        public LocalTime endTime;
    }

    /**
     * Opens the date-time picker dialog and returns selected date + start/end times.
     */
    public static PickedDateTime pickDateTime(Component parent) {
        LocalDate initial = LocalDate.now();
        DateTimePicker dp = new DateTimePicker(
                SwingUtilities.getWindowAncestor(parent),
                initial
        );
        dp.setVisible(true);
        return dp.getPickedDateTime();
    }

    // ==============================
    // Internal DateTimePicker class
    // ==============================
    private static class DateTimePicker extends JDialog {

        private LocalDate selectedDate = null;
        private YearMonth showing;
        private JPanel daysPanel;
        private JLabel monthLabel;
        private final Locale locale = Locale.getDefault();

        // Time selectors
        private JComboBox<String> startHourBox;
        private JComboBox<String> startMinuteBox;
        private JComboBox<String> endHourBox;
        private JComboBox<String> endMinuteBox;

        private PickedDateTime result = null;

        private DateTimePicker(Window owner, LocalDate initial) {
            super(owner, "Select Date & Time", ModalityType.APPLICATION_MODAL);
            this.showing = YearMonth.from(initial);
            initUI(initial);
            pack();
            setResizable(false);
            setLocationRelativeTo(owner);
        }

        public PickedDateTime getPickedDateTime() {
            return result;
        }

        private void initUI(LocalDate initial) {
            JPanel mainPanel = new JPanel(new BorderLayout(10,10));
            mainPanel.setBorder(new EmptyBorder(10,10,10,10));

            // ------------- Calendar Header -------------
            JPanel header = new JPanel(new BorderLayout());
            JButton prev = new JButton("◀");
            JButton next = new JButton("▶");
            monthLabel = new JLabel("", SwingConstants.CENTER);
            monthLabel.setFont(monthLabel.getFont().deriveFont(Font.BOLD, 14f));
            header.add(prev, BorderLayout.WEST);
            header.add(monthLabel, BorderLayout.CENTER);
            header.add(next, BorderLayout.EAST);

            prev.addActionListener(e -> { showing = showing.minusMonths(1); rebuildCalendar(); });
            next.addActionListener(e -> { showing = showing.plusMonths(1); rebuildCalendar(); });

            mainPanel.add(header, BorderLayout.NORTH);

            // ------------- Day names -------------
            JPanel names = new JPanel(new GridLayout(1,7));
            for (int i = 0; i < 7; i++) {
                DayOfWeek d = DayOfWeek.of(((i + 1 - 1) % 7) + 1); // Monday-first
                JLabel lbl = new JLabel(
                        d.getDisplayName(TextStyle.SHORT_STANDALONE, locale),
                        SwingConstants.CENTER
                );
                lbl.setForeground(Color.DARK_GRAY);
                names.add(lbl);
            }
            mainPanel.add(names, BorderLayout.CENTER);

            // ------------- Calendar Grid -------------
            daysPanel = new JPanel(new GridLayout(6,7,4,4));
            rebuildCalendar();
            mainPanel.add(daysPanel, BorderLayout.SOUTH);

            // ------------- Time Picker Panel -------------
            JPanel timePanel = new JPanel(new GridLayout(2, 4, 5,5));
            timePanel.setBorder(BorderFactory.createTitledBorder("Select Time (24h)"));

            String[] hours = new String[24];
            String[] minutes = new String[60];
            for (int i=0;i<24;i++) hours[i] = String.format("%02d", i);
            for (int i=0;i<60;i++) minutes[i] = String.format("%02d", i);

            startHourBox = new JComboBox<>(hours);
            startMinuteBox = new JComboBox<>(minutes);
            endHourBox = new JComboBox<>(hours);
            endMinuteBox = new JComboBox<>(minutes);

            timePanel.add(new JLabel("Start Hour:"));
            timePanel.add(startHourBox);
            timePanel.add(new JLabel("Start Minute:"));
            timePanel.add(startMinuteBox);
            timePanel.add(new JLabel("End Hour:"));
            timePanel.add(endHourBox);
            timePanel.add(new JLabel("End Minute:"));
            timePanel.add(endMinuteBox);

            add(mainPanel, BorderLayout.CENTER);
            add(timePanel, BorderLayout.EAST);

            // ------------- Bottom Buttons -------------
            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton todayBtn = new JButton("Today");
            JButton clearBtn = new JButton("Clear");
            JButton cancelBtn = new JButton("Cancel");
            JButton okBtn = new JButton("Save");

            todayBtn.addActionListener(e -> {
                LocalDate t = LocalDate.now();
                showing = YearMonth.from(t);
                selectedDate = t;
                rebuildCalendar();
            });

            clearBtn.addActionListener(e -> {
                selectedDate = null;
                dispose();
            });

            cancelBtn.addActionListener(e -> {
                selectedDate = null;
                dispose();
            });

            okBtn.addActionListener(e -> saveDateTime());

            bottom.add(todayBtn);
            bottom.add(clearBtn);
            bottom.add(cancelBtn);
            bottom.add(okBtn);

            add(bottom, BorderLayout.SOUTH);

            // ESC cancels
            getRootPane().registerKeyboardAction(ev -> {
                selectedDate = null;
                dispose();
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), JComponent.WHEN_IN_FOCUSED_WINDOW);

            selectedDate = initial;
        }

        private void saveDateTime() {
            if (selectedDate == null) selectedDate = showing.atDay(1);

            LocalTime startTime = LocalTime.of(
                    startHourBox.getSelectedIndex(),
                    startMinuteBox.getSelectedIndex()
            );
            LocalTime endTime = LocalTime.of(
                    endHourBox.getSelectedIndex(),
                    endMinuteBox.getSelectedIndex()
            );

            if (endTime.isBefore(startTime)) {
                JOptionPane.showMessageDialog(this,
                        "End time cannot be before start time!",
                        "Invalid Time",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // CONFIRM DIALOG
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Confirm date: " + selectedDate.toString() +
                            "\nStart: " + startTime.toString() +
                            "\nEnd: " + endTime.toString(),
                    "Confirm Selection",
                    JOptionPane.YES_NO_OPTION
            ); 

            if (choice == JOptionPane.YES_OPTION) {
                result = new PickedDateTime();
                result.date = selectedDate;
                result.startTime = startTime;
                result.endTime = endTime;
                dispose();
            }
        }
        // CALENDAR FIELD
        private void rebuildCalendar() {
            daysPanel.removeAll();

            monthLabel.setText(
                    showing.getMonth().getDisplayName(TextStyle.FULL, locale) +
                            " " + showing.getYear()
            );

            LocalDate firstOfMonth = showing.atDay(1);
            DayOfWeek firstDow = firstOfMonth.getDayOfWeek();
            int shift = (firstDow.getValue() + 6) % 7; // Monday-first
            int daysInMonth = showing.lengthOfMonth();

            for (int i=0;i<shift;i++) daysPanel.add(new JLabel(""));

            for (int d=1; d<=daysInMonth; d++) {
                LocalDate day = showing.atDay(d);
                JButton dayBtn = new JButton(String.valueOf(d));
                dayBtn.setMargin(new Insets(2,2,2,2));
                dayBtn.setOpaque(true);
                dayBtn.setFocusPainted(false);

                if (day.equals(LocalDate.now()))
                    dayBtn.setFont(dayBtn.getFont().deriveFont(Font.BOLD));

                if (day.equals(selectedDate))
                    dayBtn.setBackground(new Color(173, 216, 230));
                else
                    dayBtn.setBackground(UIManager.getColor("Button.background"));

                dayBtn.addActionListener(e -> {
                    selectedDate = day;
                    rebuildCalendar();
                });

                daysPanel.add(dayBtn);
            }

            int total = shift + daysInMonth;
            for (int i=total;i<42;i++) daysPanel.add(new JLabel(""));

            daysPanel.revalidate();
            daysPanel.repaint();
            pack();
        }
    }
}
