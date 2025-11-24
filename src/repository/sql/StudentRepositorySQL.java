package repository.sql;

import database.DBConnection;
import model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepositorySQL {
    private StudentRepositorySQL() {} // Prevent instantiation
    
    public static boolean addStudent(Student student) {
        String sql = "INSERT INTO students (FirstName, LastName, Email, DateOfBirth, Phone) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set parameters from Student object
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setDate(4, java.sql.Date.valueOf(student.getDateOfBirth()));
            stmt.setString(5, student.getPhone());
            
            // Execute insert
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }
}
