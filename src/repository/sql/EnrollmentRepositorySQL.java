package repository.sql;

import database.DBConnection;
import model.Enrollment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class EnrollmentRepositorySQL {
    private EnrollmentRepositorySQL() {} // Prevent instantiation

    private static int getCurrentEnrollment(int courseId, int instructorId, String semester) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM enrollments WHERE CourseID = ? AND InstructorID = ? AND Semester = ? AND IsActive = TRUE")) {
            
            stmt.setInt(1, courseId);
            stmt.setInt(2, instructorId);
            stmt.setString(3, semester);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching current enrollment: " + e.getMessage());        
        }
        return -1;
    }

    private static int getLimit(int courseId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT EnrollmentLimit FROM Courses WHERE CourseID = ?")) {

            stmt.setInt(1, courseId);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("EnrollmentLimit");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching enrollment limit: " + e.getMessage());        
        }
        return -1;
    }

    public static boolean addEnrollment(Enrollment enrollment) {

        // Validate enrollment against course limit
        int currentCount = getCurrentEnrollment(enrollment.getCourseId(), enrollment.getInstructorId(), enrollment.getSemester());
        int limit = getLimit(enrollment.getCourseId());

        // If either fetch failed, we cannot validate
        if (currentCount == -1 || limit == -1) {
            System.err.println("Validation failed: Could not fetch enrollment data.");
            return false;
        }

        // Check if adding this enrollment would exceed the limit
        if (currentCount >= limit) {
            System.err.println("Enrollment failed: Course is full! (Limit: " + limit + ")");
            return false;
        }

        // SQL query to insert a new enrollment
        String sql = "INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
           
            // Set parameters from Enrollment object
            stmt.setInt(1, enrollment.getStudentId());
            stmt.setDate(2, java.sql.Date.valueOf(enrollment.getEnrollmentDate()));
            stmt.setBoolean(3, enrollment.isActive());
            stmt.setInt(4, enrollment.getCourseId());
            stmt.setInt(5, enrollment.getInstructorId());
            stmt.setString(6, enrollment.getSemester());
           
            // Execute insert
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error adding enrollment: " + e.getMessage());
            return false;
        }
    }
}