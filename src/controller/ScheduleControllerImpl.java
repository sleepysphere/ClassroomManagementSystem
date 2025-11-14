package controller;

import model.*;
import repository.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implements the business logic for assigning classes and checking availability.
 */
public class ScheduleControllerImpl implements ScheduleController {

    private RoomRepository roomRepo;
    private ClassRepository classRepo;
    private ScheduleRepository schedRepo;

    public ScheduleControllerImpl(RoomRepository roomRepo, ClassRepository classRepo, ScheduleRepository schedRepo) {
        this.roomRepo = roomRepo;
        this.classRepo = classRepo;
        this.schedRepo = schedRepo;
    }

    @Override
    public List<Room> getAvailableRooms(LocalDateTime start, LocalDateTime end) {
        return roomRepo.findAvailable(start, end);
    }

    @Override
    public boolean assignClassToRoom(int classId, int roomId, LocalDateTime start, LocalDateTime end) {

        // 1. Check if room is free
        if (!schedRepo.isRoomFree(roomId, start, end)) {
            return false;
        }

        // 2. Validate room matches class requirement
        SchoolClass c = classRepo.findById(classId);
        Room r = roomRepo.findById(roomId);

        if (c.requiresLab() && r.getType() != RoomType.LAB) {
            return false;
        }

        // 3. Check capacity
        if (c.getStudentCount() > r.getCapacity()) {
            return false;
        }

        // 4. Save schedule entry
        ClassSchedule schedule = new ClassSchedule(0, classId, roomId, start, end);
        schedRepo.save(schedule);

        return true;
    }

    @Override
    public List<ClassSchedule> getRoomSchedule(int roomId, LocalDate date) {
        return schedRepo.getRoomSchedule(roomId, date);
    }

    @Override
    public void addClass(SchoolClass c) {
        classRepo.save(c);
    }

    @Override
    public void addRoom(Room r) {
        roomRepo.save(r);
    }
}
