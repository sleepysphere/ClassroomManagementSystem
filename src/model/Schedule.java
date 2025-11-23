package model;

import java.time.LocalTime;
import java.time.LocalDate;

public class Schedule {
    private int classId;
    private int courseId;
    private int instructorId;
    private String sessionType;
    private LocalDate startDate;
    private LocalDate endDate;
    private int roomId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String semester;

    public Schedule(int classId, int courseId, int instructorId, String sessionType, LocalDate startDate, LocalDate endDate, int roomId, String dayOfWeek, LocalTime startTime, LocalTime endTime, String semester) {
        this.classId = classId;
        this.courseId = courseId;
        this.roomId = roomId;
        this.instructorId = instructorId;
        this.sessionType = sessionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.semester = semester;
    }

    public int getClassId() { return classId; }
    public int getCourseId() { return courseId; }
    public int getInstructorId() { return instructorId; }
    public String getSessionType() { return sessionType; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public int getRoomId() { return roomId; }
    public String getDayOfWeek() { return dayOfWeek; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getSemester() { return semester; }

    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setInstructorId(int instructorId) { this.instructorId = instructorId; }
    public void setSessionType(String sessionType) { this.sessionType = sessionType; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setSemester(String semester) { this.semester = semester; }
}

