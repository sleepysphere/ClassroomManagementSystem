package repository.sql;

import database.DBConnection;
import model.Instructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorRepositorySQL {
    private InstructorRepositorySQL() {} // Prevent instantiation
    
    public static boolean addInstructor(Instructor instructor) {
        String sql = "INSERT INTO instructors (FirstName, LastName, Email, HireDate, Phone, Department) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set parameters from Instructor object
            stmt.setString(1, instructor.getFirstName());
            stmt.setString(2, instructor.getLastName());
            stmt.setString(3, instructor.getEmail());
            stmt.setDate(4, java.sql.Date.valueOf(instructor.getHireDate()));
            stmt.setString(5, instructor.getPhone());
            stmt.setString(6, instructor.getDepartment());
            
            // Execute insert
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding instructor: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // READ: Get a single instructor by ID
    // ---------------------------------------------------------
    public static Instructor getInstructorById(int instructorId) {
        String sql = "SELECT * FROM instructors WHERE InstructorID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, instructorId);
            
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new Instructor(
                    rs.getInt("InstructorID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("Phone"),
                    rs.getString("Department"),
                    // Handle Date conversion safely
                    rs.getDate("HireDate") != null ? rs.getDate("HireDate").toLocalDate() : null
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching instructor: " + e.getMessage());
        }
        return null;
    }

    // ---------------------------------------------------------
    // DELETE: Remove an instructor
    // (Warning: This will CASCADE delete all their classes!)
    // ---------------------------------------------------------
    public static boolean deleteInstructor(int instructorId) {
        String sql = "DELETE FROM instructors WHERE InstructorID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, instructorId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting instructor: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // UPDATE: Modify instructor details
    // ---------------------------------------------------------
    public static boolean updateInstructor(Instructor instructor) {
        String sql = "UPDATE instructors SET FirstName=?, LastName=?, Email=?, HireDate=?, Phone=?, Department=? WHERE InstructorID=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, instructor.getFirstName());
            stmt.setString(2, instructor.getLastName());
            stmt.setString(3, instructor.getEmail());
            stmt.setDate(4, java.sql.Date.valueOf(instructor.getHireDate()));
            stmt.setString(5, instructor.getPhone());
            stmt.setString(6, instructor.getDepartment());
            
            // Target ID
            stmt.setInt(7, instructor.getInstructorId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating instructor: " + e.getMessage());
            return false;
        }
    }

    public static List<Instructor> getAllInstructors() {
    List<Instructor> instructors = new ArrayList<>();
    String sql = "SELECT InstructorID, FirstName, LastName, Email, Phone, Department, HireDate FROM instructors";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Instructor instructor = new Instructor(
                rs.getInt("InstructorID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("Email"),
                rs.getString("Phone"),
                rs.getString("Department"),
                rs.getDate("HireDate").toLocalDate()
            );
            instructors.add(instructor);
        }

    } catch (SQLException e) {
        System.err.println("Error fetching instructors: " + e.getMessage());
    }

    return instructors;
    }
}
