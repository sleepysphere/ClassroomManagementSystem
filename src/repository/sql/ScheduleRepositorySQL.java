package repository.sql;

import database.DBConnection;
import model.Schedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScheduleRepositorySQL {
    private ScheduleRepositorySQL() {} // Prevent instantiation
    
    public static boolean addSchedule(Schedule schedule) {
        // SQL query to insert a new schedule
        // We exclude ClassID assuming it is AUTO_INCREMENT in the database
        String sql = "INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Map Schedule object fields to SQL parameters
            stmt.setInt(1, schedule.getCourseId());
            stmt.setInt(2, schedule.getInstructorId());
            stmt.setString(3, schedule.getSessionType());
            stmt.setObject(4, java.sql.Date.valueOf(schedule.getStartDate()));
            stmt.setObject(5, java.sql.Date.valueOf(schedule.getEndDate()));
            stmt.setInt(6, schedule.getRoomId());
            stmt.setString(7, schedule.getDayOfWeek());
            stmt.setObject(8, java.sql.Time.valueOf(schedule.getStartTime()));
            stmt.setObject(9, java.sql.Time.valueOf(schedule.getEndTime()));
            stmt.setString(10, schedule.getSemester());
            
            // Execute the insert
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding schedule: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // READ: Get a single schedule by ID
    // ---------------------------------------------------------
    public static Schedule getScheduleById(int classId) {
        String sql = "SELECT * FROM schedules WHERE ClassID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, classId);
            
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new Schedule(
                    rs.getInt("ClassID"),
                    rs.getInt("CourseID"),
                    rs.getInt("InstructorID"),
                    rs.getString("SessionType"),
                    rs.getDate("StartDate").toLocalDate(),
                    rs.getDate("EndDate").toLocalDate(),
                    rs.getInt("RoomID"),
                    rs.getString("DayOfWeek"),
                    rs.getTime("StartTime").toLocalTime(),
                    rs.getTime("EndTime").toLocalTime(),
                    rs.getString("Semester")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching schedule: " + e.getMessage());
        }
        return null;
    }

    // ---------------------------------------------------------
    // DELETE: Remove a schedule (and its exceptions automatically)
    // ---------------------------------------------------------
    public static boolean deleteSchedule(int classId) {
        String sql = "DELETE FROM schedules WHERE ClassID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, classId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if a row was actually deleted
            
        } catch (SQLException e) {
            System.err.println("Error deleting schedule: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // UPDATE: Modify an existing schedule
    // ---------------------------------------------------------
    public static boolean updateSchedule(Schedule schedule) {
        // Logic: Overwrite every field for the specific ClassID
        String sql = "UPDATE schedules SET CourseID=?, InstructorID=?, SessionType=?, StartDate=?, EndDate=?, RoomID=?, DayOfWeek=?, StartTime=?, EndTime=?, Semester=? WHERE ClassID=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // 1. Set the new values
            stmt.setInt(1, schedule.getCourseId());
            stmt.setInt(2, schedule.getInstructorId());
            stmt.setString(3, schedule.getSessionType());
            stmt.setDate(4, java.sql.Date.valueOf(schedule.getStartDate()));
            stmt.setDate(5, java.sql.Date.valueOf(schedule.getEndDate()));
            stmt.setInt(6, schedule.getRoomId());
            stmt.setString(7, schedule.getDayOfWeek());
            stmt.setTime(8, java.sql.Time.valueOf(schedule.getStartTime()));
            stmt.setTime(9, java.sql.Time.valueOf(schedule.getEndTime()));
            stmt.setString(10, schedule.getSemester());
            
            // 2. Identify WHICH row to update (The ID)
            stmt.setInt(11, schedule.getClassId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating schedule: " + e.getMessage());
            return false;
        }
    }
}
