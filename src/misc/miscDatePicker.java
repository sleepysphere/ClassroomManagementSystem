package misc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

public class miscDatePicker {

    /**
     * Call this to show a date picker dialog.
     * Example:
     * LocalDate date = miscDatePicker.pickDate(frame);
     */
    public static LocalDate pickDate(Component parent) {
        LocalDate initial = LocalDate.now();
        DatePicker dp = new DatePicker(
                SwingUtilities.getWindowAncestor(parent),
                initial
        );
        dp.setVisible(true);
        return dp.selectedDate;
    }

    // ===========================
    // Internal DatePicker class
    // ===========================
    private static class DatePicker extends JDialog {
        private LocalDate selectedDate = null;
        private YearMonth showing;
        private JPanel daysPanel;
        private JLabel monthLabel;
        private final Locale locale = Locale.getDefault();

        private DatePicker(Window owner, LocalDate initial) {
            super(owner, "Select Date", ModalityType.APPLICATION_MODAL);
            this.showing = YearMonth.from(initial);
            initUI(initial);
            pack();
            setResizable(false);
            setLocationRelativeTo(owner);
        }

        private void initUI(LocalDate initial) {
            JPanel content = new JPanel(new BorderLayout(10, 10));
            content.setBorder(new EmptyBorder(10,10,10,10));

            // ========== HEADER ==========
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

            content.add(header, BorderLayout.NORTH);

            // ========== DAY NAMES ==========
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
            content.add(names, BorderLayout.CENTER);

            // ========== DAYS GRID ==========
            daysPanel = new JPanel(new GridLayout(6,7,4,4));
            rebuildCalendar();
            content.add(daysPanel, BorderLayout.SOUTH);

            // ========== BOTTOM BUTTONS ==========
            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            JButton todayBtn = new JButton("Today");
            JButton clearBtn = new JButton("Clear");
            JButton cancelBtn = new JButton("Cancel");
            JButton okBtn = new JButton("OK");

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

            okBtn.addActionListener(e -> {
                if (selectedDate == null)
                    selectedDate = showing.atDay(1);  // default
                dispose();
            });

            bottom.add(todayBtn);
            bottom.add(clearBtn);
            bottom.add(cancelBtn);
            bottom.add(okBtn);

            setLayout(new BorderLayout());
            add(content, BorderLayout.CENTER);
            add(bottom, BorderLayout.SOUTH);

            // Allow ESC to cancel
            getRootPane().registerKeyboardAction(ev -> {
                selectedDate = null;
                dispose();
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

            selectedDate = initial;
        }

        private void rebuildCalendar() {
            daysPanel.removeAll();

            monthLabel.setText(
                    showing.getMonth().getDisplayName(TextStyle.FULL, locale)
                    + " " + showing.getYear()
            );

            LocalDate firstOfMonth = showing.atDay(1);
            DayOfWeek firstDow = firstOfMonth.getDayOfWeek();
            int shift = (firstDow.getValue() + 6) % 7; // Monday-first
            int daysInMonth = showing.lengthOfMonth();

            // Add blanks
            for (int i = 0; i < shift; i++) {
                daysPanel.add(new JLabel(""));
            }

            // Days
            for (int d = 1; d <= daysInMonth; d++) {
                LocalDate day = showing.atDay(d);
                JButton dayBtn = new JButton(String.valueOf(d));
                dayBtn.setMargin(new Insets(2,2,2,2));
                dayBtn.setOpaque(true);
                dayBtn.setFocusPainted(false);

                if (day.equals(LocalDate.now())) {
                    dayBtn.setFont(dayBtn.getFont().deriveFont(Font.BOLD));
                }

                if (day.equals(selectedDate)) {
                    dayBtn.setBackground(new Color(173, 216, 230));
                } else {
                    dayBtn.setBackground(UIManager.getColor("Button.background"));
                }

                dayBtn.addActionListener(e -> {
                    selectedDate = day;
                    rebuildCalendar();
                });

                daysPanel.add(dayBtn);
            }

            // Fill grid to 42 cells
            int total = shift + daysInMonth;
            for (int i = total; i < 42; i++) {
                daysPanel.add(new JLabel(""));
            }

            daysPanel.revalidate();
            daysPanel.repaint();
            pack();
        }
    }
}
