package controller;

import model.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Defines the communication between the GUI layer and the business logic.
 */
public interface ScheduleController {

    /**
     * Returns a list of rooms available during a specific time period.
     */
    List<Room> getAvailableRooms(LocalDateTime start, LocalDateTime end);

    /**
     * Attempts to assign a class to a room during the given time.
     * Returns true if successful, false if conflicts or invalid.
     */
    boolean assignClassToRoom(int classId, int roomId, LocalDateTime start, LocalDateTime end);

    /**
     * Returns the schedule of a room for a specific day.
     */
    List<ClassSchedule> getRoomSchedule(int roomId, LocalDate date);

    void addClass(SchoolClass c);
    void addRoom(Room r);
}
