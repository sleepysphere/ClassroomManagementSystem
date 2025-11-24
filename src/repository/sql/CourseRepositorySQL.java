package repository.sql;

import database.DBConnection;
import model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseRepositorySQL {
    private CourseRepositorySQL() {} // Prevent instantiation
    
    public static boolean addCourse(Course course) {
        // SQL query to insert a new course
        // We exclude CourseID assuming it is AUTO_INCREMENT in the database
        String sql = "INSERT INTO courses (CourseCode, CourseName, Credits, Description, EnrollmentLimit, RequiresLab, SessionCount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Map Room object fields to SQL parameters
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setDouble(3, course.getCredits());
            stmt.setString(4, course.getDescription());
            stmt.setInt(5, course.getEnrollmentLimit());
            stmt.setBoolean(6, course.isRequiresLab());
            stmt.setInt(7, course.getSessionCount());
            
            // Execute the insert
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
            return false;
        }
    }
}
