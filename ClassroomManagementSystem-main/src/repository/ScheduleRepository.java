package repository;

import model.ClassSchedule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Handles reading/writing scheduled classes in the database.
 */
public interface ScheduleRepository {

    boolean isRoomFree(int roomId, LocalDateTime start, LocalDateTime end);

    List<ClassSchedule> getRoomSchedule(int roomId, LocalDate date);

    void save(ClassSchedule schedule);
}
