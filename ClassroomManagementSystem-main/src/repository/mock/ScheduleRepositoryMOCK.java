package repository.mock;

import repository.ScheduleRepository;
import model.ClassSchedule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock version of ScheduleRepository using in-memory list.
 */
public class ScheduleRepositoryMOCK implements ScheduleRepository {

    private List<ClassSchedule> schedules = new ArrayList<>();

    @Override
    public boolean isRoomFree(int roomId, LocalDateTime start, LocalDateTime end) {
        return schedules.stream().noneMatch(s ->
                s.getRoomId() == roomId &&
                !(end.isBefore(s.getStart()) || start.isAfter(s.getEnd()))
        );
    }

    @Override
    public List<ClassSchedule> getRoomSchedule(int roomId, LocalDate date) {
        return schedules.stream()
                .filter(s -> s.getRoomId() == roomId && s.getStart().toLocalDate().equals(date))
                .toList();
    }

    @Override
    public void save(ClassSchedule schedule) {
        schedules.add(schedule);
    }
}
