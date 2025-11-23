package model;

import java.time.LocalTime;

public class Schedule {
    private int classId;
    private int courseId;
    private int roomId;
    private int instructorId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String semester;

    public Schedule(int classId, int courseId, int roomId, int instructorId, String dayOfWeek, LocalTime startTime, LocalTime endTime, String semester) {
        this.classId = classId;
        this.courseId = courseId;
        this.roomId = roomId;
        this.instructorId = instructorId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.semester = semester;
    }

    public int getClassId() { return classId; }
    public int getCourseId() { return courseId; }
    public int getRoomId() { return roomId; }
    public int getInstructorId() { return instructorId; }
    public String getDayOfWeek() { return dayOfWeek; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getSemester() { return semester; }

    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public void setInstructorId(int instructorId) { this.instructorId = instructorId; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setSemester(String semester) { this.semester = semester; }
}

