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

    // ---------------------------------------------------------
    // READ: Get a single course by ID
    // ---------------------------------------------------------
    public static Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE CourseID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, courseId);
            
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                    rs.getInt("CourseID"),
                    rs.getString("CourseName"),
                    rs.getString("CourseCode"),
                    rs.getString("Description"),
                    rs.getDouble("Credits"),
                    rs.getBoolean("RequiresLab"),
                    rs.getInt("SessionCount"),
                    rs.getInt("EnrollmentLimit"),
                    rs.getBoolean("IsActive")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching course: " + e.getMessage());
        }
        return null;
    }

    // ---------------------------------------------------------
    // UPDATE: Modify course details
    // ---------------------------------------------------------
    public static boolean updateCourse(Course course) {
        String sql = "UPDATE courses SET CourseName=?, CourseCode=?, Description=?, Credits=?, RequiresLab=?, SessionCount=?, EnrollmentLimit=? WHERE CourseID=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setString(3, course.getDescription());
            stmt.setDouble(4, course.getCredits());
            stmt.setBoolean(5, course.isRequiresLab());
            stmt.setInt(6, course.getSessionCount());
            stmt.setInt(7, course.getEnrollmentLimit());
            
            // Target ID
            stmt.setInt(8, course.getCourseId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating course: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // DELETE: Remove a course
    // (Warning: Deletes ALL history, classes, and enrollments for this subject!)
    // ---------------------------------------------------------
    public static boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE CourseID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, courseId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting course: " + e.getMessage());
            return false;
        }
    }

    // ---------------------------------------------------------
    // ARCHIVE: "Soft Delete" a course (Hides it, but keeps history)
    // ---------------------------------------------------------
    public static boolean archiveCourse(int courseId) {
        // We update the flag instead of deleting the row
        String sql = "UPDATE courses SET IsActive = FALSE WHERE CourseID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, courseId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error archiving course: " + e.getMessage());
            return false;
        }
    }
}
