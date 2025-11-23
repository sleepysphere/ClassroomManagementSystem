package model;

import java.time.LocalDate;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private LocalDate enrollmentDate;
    private boolean isActive;
    private int courseId;
    private int instructorId;
    private String semester;

    public Enrollment(int enrollmentId, int studentId, LocalDate enrollmentDate, boolean isActive, int courseId, int instructorId, String semester) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.enrollmentDate = enrollmentDate;
        this.isActive = isActive;
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.semester = semester;
    }

    public int getEnrollmentId() { return enrollmentId; }
    public int getStudentId() { return studentId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public boolean isActive() { return isActive; }
    public int getCourseId() { return courseId; }
    public int getInstructorId() { return instructorId; }
    public String getSemester() { return semester; }

    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setInstructorId(int instructorId) { this.instructorId = instructorId; }
    public void setSemester(String semester) { this.semester = semester; }
}
