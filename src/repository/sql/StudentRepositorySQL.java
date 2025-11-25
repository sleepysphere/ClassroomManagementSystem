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

    // ---------------------------------------------------------
    // READ: Get a single student by ID
    // ---------------------------------------------------------
    public static Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE StudentID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, studentId);
            
            var rs = stmt.executeQuery();
            if (rs.next()) {
                // Map the DB row back to a Java Object
                return new Student(
                    rs.getInt("StudentID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Phone"),
                    rs.getString("Email"),
                    rs.getDate("DateOfBirth") != null ? rs.getDate("DateOfBirth").toLocalDate() : null
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching student: " + e.getMessage());
        }
        return null; // Return null if not found
    }

    // ---------------------------------------------------------
    // DELETE: Remove a student (Automatically drops all their classes)
    // ---------------------------------------------------------
    public static boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE StudentID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, studentId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // UPDATE: Modify student details (Name, Email, Phone, etc.)
    // ---------------------------------------------------------
    public static boolean updateStudent(Student student) {
        // Logic: Overwrite all fields for the specific StudentID
        String sql = "UPDATE students SET FirstName=?, LastName=?, Email=?, DateOfBirth=?, Phone=? WHERE StudentID=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setDate(4, java.sql.Date.valueOf(student.getDateOfBirth()));
            stmt.setString(5, student.getPhone());
            
            // The Target ID (Where clause)
            stmt.setInt(6, student.getStudentID());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }
}
