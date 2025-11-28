package repository.sql;

import database.DBConnection;
import model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositorySQL {
    private CourseRepositorySQL() {} // Prevent instantiation
    
    public static boolean addCourse(Course course) {
        // SQL query to insert a new course
        // We exclude CourseID assuming it is AUTO_INCREMENT in the database
        String sql = "INSERT INTO courses (CourseCode, CourseName, Credits, EnrollmentLimit, RequiresLab, SessionCount, IsActive ) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Map Room object fields to SQL parameters
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setDouble(3, course.getCredits());
            stmt.setInt(4, course.getEnrollmentLimit());
            stmt.setBoolean(5, course.isRequiresLab());
            stmt.setInt(6, course.getSessionCount());
            stmt.setBoolean(7, course.isActive());
            
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
        String sql = """
        SELECT CourseID, CourseName, CourseCode,
            Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive
        FROM courses
        WHERE CourseID = ?
        """;        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, courseId);
            
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                    rs.getInt("CourseID"),
                    rs.getString("CourseName"),
                    rs.getString("CourseCode"),
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
        String sql = "UPDATE courses SET CourseName=?, CourseCode=?, Credits=?, RequiresLab=?, SessionCount=?, EnrollmentLimit=? WHERE CourseID=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setDouble(3, course.getCredits());
            stmt.setBoolean(4, course.isRequiresLab());
            stmt.setInt(5, course.getSessionCount());
            stmt.setInt(6, course.getEnrollmentLimit());
            
            // Target ID
            stmt.setInt(7, course.getCourseId());
            
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

    public static List<Course> getAllCourses(){
    List<Course> courses = new ArrayList<>();
    String sql = """
        SELECT CourseID, CourseName, CourseCode,
            Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive
        FROM courses
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Course course = new Course(
                rs.getInt("CourseID"),
                rs.getString("CourseName"),
                rs.getString("CourseCode"),
                rs.getDouble("Credits"),
                rs.getBoolean("RequiresLab"),
                rs.getInt("SessionCount"),
                rs.getInt("EnrollmentLimit"),
                rs.getBoolean("IsActive")
            );
            courses.add(course);
        }

    } catch (SQLException e) {
        System.err.println("Error fetching courses: " + e.getMessage());
    }
    return courses;
}

}
