package repository.sql;

import database.DBConnection;
import model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomRepositorySQL {
    
    private RoomRepositorySQL() {} // Prevent instantiation
    
    public static boolean addRoom(Room room) {
        // SQL query to insert a new room
        // We exclude RoomID assuming it is AUTO_INCREMENT in the database
        String sql = "INSERT INTO rooms (RoomNumber, RoomType, Capacity) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Map Room object fields to SQL parameters
            stmt.setString(1, room.getRoomNumber());
            stmt.setString(2, room.getRoomType());
            stmt.setInt(3, room.getCapacity());
            
            // Execute the insert
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding room: " + e.getMessage());
            return false;
        }
    }
}