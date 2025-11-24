package repository.sql;

import database.DBConnection;
import model.Instructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
