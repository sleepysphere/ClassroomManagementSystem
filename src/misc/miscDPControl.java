package misc;

import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class miscDPControl {

    // Container for date + times
    public static class DateTimeEntry {
        public LocalDate date;
        public LocalTime startTime;
        public LocalTime endTime;
    }

    // Store date + start + end times into DB
    protected static void StoreDateTime(LocalDate date, LocalTime start, LocalTime end) {
        String query = String.format(
                "INSERT INTO misc (date_check, start_time, end_time) VALUES ('%s','%s','%s')",
                date.toString(),
                start.toString(),
                end.toString()
        );
        try {
            miscDBCONN.executeUpdate(query);
            System.out.println("Stored: " + date + " | Start: " + start + " | End: " + end);
        } catch (Exception e) {
            System.out.println("Failed to store date-time entry.");
            e.printStackTrace();
        }
    }

    // Retrieve future date-time entries, sorted by date ascending
    protected static List<DateTimeEntry> RetrieveUpcomingDateTimes() {
        List<DateTimeEntry> entries = new ArrayList<>();
        String query = "SELECT date_check, start_time, end_time FROM misc";

        try {
            ResultSet rs = miscDBCONN.executeQuery(query);
            LocalDateTime now = LocalDateTime.now();

            while (rs.next()) {
                LocalDate date = rs.getDate("date_check").toLocalDate();
                Time st = rs.getTime("start_time");
                Time et = rs.getTime("end_time");
                LocalTime startTime = (st != null) ? st.toLocalTime() : null;
                LocalTime endTime = (et != null) ? et.toLocalTime() : null;

                // Skip past entries
                if (date.isBefore(now.toLocalDate())) continue;
                if (date.isEqual(now.toLocalDate()) && endTime != null && endTime.isBefore(now.toLocalTime()))
                    continue;

                DateTimeEntry entry = new DateTimeEntry();
                entry.date = date;
                entry.startTime = startTime;
                entry.endTime = endTime;
                entries.add(entry);
            }

            // Sort ascending by date, then startTime
            entries.sort(Comparator.comparing((DateTimeEntry e) -> e.date)
                    .thenComparing(e -> e.startTime));

        } catch (Exception e) {
            System.out.println("Failed to retrieve upcoming date-time entries.");
            e.printStackTrace();
        }

        return entries;
    }

    // Optional legacy: retrieve all dates (unfiltered)
    protected static List<LocalDate> RetrieveAllDates() {
        List<LocalDate> dates = new ArrayList<>();
        String query = "SELECT date_check FROM misc ORDER BY id_check ASC";
        try {
            ResultSet rs = miscDBCONN.executeQuery(query);
            while (rs.next()) {
                dates.add(rs.getDate("date_check").toLocalDate());
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve dates.");
            e.printStackTrace();
        }
        return dates;
    }
}
