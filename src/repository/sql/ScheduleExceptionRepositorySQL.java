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
    // READ: Get a single exception by ID
    // ---------------------------------------------------------
    public static ScheduleException getExceptionById(int exceptionId) {
        // Use the specific column name 'ClassStatus' based on your DB dump
        String sql = "SELECT * FROM ScheduleExceptions WHERE ExceptionID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, exceptionId);
            
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new ScheduleException(
                    rs.getInt("ExceptionID"),
                    rs.getInt("ClassID"),
                    rs.getDate("ExceptionDate").toLocalDate(),
                    rs.getString("ClassStatus"),
                    // Safe Null Checks for Optional Fields:
                    rs.getDate("NewDate") != null ? rs.getDate("NewDate").toLocalDate() : null,
                    rs.getTime("StartTime") != null ? rs.getTime("StartTime").toLocalTime() : null,
                    rs.getTime("EndTime") != null ? rs.getTime("EndTime").toLocalTime() : null,
                    rs.getInt("RoomID") // Returns 0 if NULL in DB, which works for us
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching exception: " + e.getMessage());
        }
        return null;
    }

    // ---------------------------------------------------------
    // UPDATE: Modify an existing exception
    // ---------------------------------------------------------
    public static boolean updateException(ScheduleException exception) {
        String sql = "UPDATE ScheduleExceptions SET ClassID=?, ExceptionDate=?, ClassStatus=?, NewDate=?, StartTime=?, EndTime=?, RoomID=? WHERE ExceptionID=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // 1. Mandatory Fields
            stmt.setInt(1, exception.getClassId());
            stmt.setDate(2, java.sql.Date.valueOf(exception.getExceptionDate()));
            stmt.setString(3, exception.getClassStatus());

            // 2. Optional Dates (Handle Nulls safely)
            if (exception.getNewDate() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(exception.getNewDate()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            // 3. Optional Start Time
            if (exception.getStartTime() != null) {
                stmt.setTime(5, java.sql.Time.valueOf(exception.getStartTime()));
            } else {
                stmt.setNull(5, java.sql.Types.TIME);
            }

            // 4. Optional End Time
            if (exception.getEndTime() != null) {
                stmt.setTime(6, java.sql.Time.valueOf(exception.getEndTime()));
            } else {
                stmt.setNull(6, java.sql.Types.TIME);
            }

            // 5. Optional RoomID
            if (exception.getRoomID() > 0) { // Assuming getRoomID returns int
                stmt.setInt(7, exception.getRoomID());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            // 6. The Target ID (WHERE clause)
            stmt.setInt(8, exception.getExceptionId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating exception: " + e.getMessage());
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
