package repository.sql;

import database.DBConnection;
import model.Schedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScheduleRepositorySQL {
    public ScheduleRepositorySQL() {} // Prevent instantiation
    
    public static boolean addSchedule(Schedule schedule) {
        // SQL query to insert a new schedule
        // We exclude ClassID assuming it is AUTO_INCREMENT in the database
        String sql = "INSERT INTO schedules (CourseID, RoomID, InstructorID, DayOfWeek, StartTime, EndTime, Semester) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Map Schedule object fields to SQL parameters
            stmt.setInt(1, schedule.getCourseId());
            stmt.setInt(2, schedule.getRoomId());
            stmt.setInt(3, schedule.getInstructorId());
            stmt.setString(4, schedule.getDayOfWeek());
            stmt.setObject(5, java.sql.Time.valueOf(schedule.getStartTime()));
            stmt.setObject(6, java.sql.Time.valueOf(schedule.getEndTime()));
            stmt.setString(7, schedule.getSemester());
            
            // Execute the insert
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding schedule: " + e.getMessage());
            return false;
        }
    }
}
