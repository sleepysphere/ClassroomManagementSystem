package model;

public class Course {
    private int courseId;
    private String courseName;
    private String courseCode;
    private double credits;
    private boolean requiresLab;
    private int sessionCount;
    private int enrollmentLimit;
    private boolean isActive;

    public Course(int courseId, String courseName, String courseCode,
                  double credits, boolean requiresLab, int sessionCount, int enrollmentLimit, boolean isActive) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.requiresLab = requiresLab;
        this.sessionCount = sessionCount;
        this.enrollmentLimit = enrollmentLimit;
        this.isActive = isActive;
    }

    // Getters
    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public double getCredits() { return credits; }
    public boolean isRequiresLab() { return requiresLab; }
    public int getSessionCount() { return sessionCount; }
    public int getEnrollmentLimit() { return enrollmentLimit; }
    public boolean isActive() { return isActive; }

    // Setters
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setCredits(double credits) { this.credits = credits; }
    public void setRequiresLab(boolean requiresLab) { this.requiresLab = requiresLab; }
    public void setSessionCount(int sessionCount) { this.sessionCount = sessionCount; }
    public void setEnrollmentLimit(int enrollmentLimit) { this.enrollmentLimit = enrollmentLimit; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
}

