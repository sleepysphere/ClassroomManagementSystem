package repository.sql;

import database.DBConnection;
import model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    // ---------------------------------------------------------
    // READ: Get a single room by ID
    // ---------------------------------------------------------
    public static Room getRoomById(int roomId) {
        String sql = "SELECT * FROM rooms WHERE RoomID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, roomId);
            
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new Room(
                    rs.getInt("RoomID"),
                    rs.getString("RoomNumber"),
                    rs.getString("RoomType"),
                    rs.getInt("Capacity")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching room: " + e.getMessage());
        }
        return null;
    }

    // ---------------------------------------------------------
    // UPDATE: Modify room details
    // ---------------------------------------------------------
    public static boolean updateRoom(Room room) {
        String sql = "UPDATE rooms SET RoomNumber=?, Capacity=?, RoomType=? WHERE RoomID=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, room.getRoomNumber());
            stmt.setInt(2, room.getCapacity());
            stmt.setString(3, room.getRoomType());
            
            // Target ID
            stmt.setInt(4, room.getRoomID());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating room: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // DELETE: Remove a room
    // (Warning: Automatically cancels all schedules in this room!)
    // ---------------------------------------------------------
    public static boolean deleteRoom(int roomId) {
        String sql = "DELETE FROM rooms WHERE RoomID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, roomId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting room: " + e.getMessage());
            return false;
        }
    }

    public static List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT RoomID, RoomNumber, RoomType, Capacity FROM rooms";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("RoomID"),
                    rs.getString("RoomNumber"),
                    rs.getString("RoomType"),
                    rs.getInt("Capacity")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching rooms: " + e.getMessage());
        }
        return rooms;
    }
}