package repository.sql;

import database.DBConnection;
import model.ScheduleException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScheduleExceptionRepositorySQL {
    private ScheduleExceptionRepositorySQL() {} // Prevent instantiation

    public static boolean addScheduleException(ScheduleException exception) {
        // SQL query to insert a new schedule exception
        String sql = "INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Map ScheduleException object fields to SQL parameters
            stmt.setInt(1, exception.getClassId());
            stmt.setObject(2, java.sql.Date.valueOf(exception.getExceptionDate()));
            stmt.setString(3, exception.getClassStatus());
            if (exception.getNewDate() != null) {
                stmt.setObject(4, java.sql.Date.valueOf(exception.getNewDate()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }
            if (exception.getStartTime() != null) {
                stmt.setObject(5, java.sql.Time.valueOf(exception.getStartTime()));
            } else {
                stmt.setNull(5, java.sql.Types.TIME);
            }
            if (exception.getEndTime() != null) {
                stmt.setObject(6, java.sql.Time.valueOf(exception.getEndTime()));
            } else {
                stmt.setNull(6, java.sql.Types.TIME);
            }
            if (exception.getRoomID() != 0) {
                stmt.setInt(7, exception.getRoomID());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            // Execute the insert
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding schedule exception: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // DELETE: Remove an exception (Restores the original schedule)
    // ---------------------------------------------------------
    public static boolean deleteException(int exceptionId) {
        String sql = "DELETE FROM ScheduleExceptions WHERE ExceptionID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, exceptionId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error removing exception: " + e.getMessage());
            return false;
        }
    }
}
