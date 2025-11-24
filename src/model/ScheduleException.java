package model;

import java.time.LocalTime;
import java.time.LocalDate;

public class ScheduleException {
    private int exceptionId;
    private int classId;
    private LocalDate exceptionDate;
    private String classStatus; // e.g., "Cancelled", "Rescheduled"
    private LocalDate newDate; // Optional, for rescheduling
    private LocalTime startTime; // Optional, for rescheduling
    private LocalTime endTime; // Optional, for rescheduling
    private int roomID;

    public ScheduleException(int exceptionId, int classId, LocalDate exceptionDate, String classStatus, LocalDate newDate, LocalTime startTime, LocalTime endTime, int roomID) {
        this.exceptionId = exceptionId;
        this.classId = classId;
        this.exceptionDate = exceptionDate;
        this.classStatus = classStatus;
        this.newDate = newDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomID = roomID;
    }

    public int getExceptionId() { return exceptionId; }
    public int getClassId() { return classId; }
    public LocalDate getExceptionDate() { return exceptionDate; }
    public String getClassStatus() { return classStatus; }
    public LocalDate getNewDate() { return newDate; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public int getRoomID() { return roomID; }
    
    public void setClassId(int classId) { this.classId = classId; }
    public void setExceptionDate(LocalDate exceptionDate) { this.exceptionDate = exceptionDate; }
    public void setClassStatus(String classStatus) { this.classStatus = classStatus; }
    public void setNewDate(LocalDate newDate) { this.newDate = newDate; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setRoomID(int roomID) { this.roomID = roomID; }
}
