package model;

import java.time.LocalDateTime;

public class ClassSchedule {
    private int id;
    private int classId;
    private int roomId;
    private LocalDateTime start;
    private LocalDateTime end;

    public ClassSchedule(int id, int classId, int roomId, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.classId = classId;
        this.roomId = roomId;
        this.start = start;
        this.end = end;
    }

    public int getId() { return id; }
    public int getClassId() { return classId; }
    public int getRoomId() { return roomId; }
    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
}

