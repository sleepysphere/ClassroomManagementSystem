package repository;

import model.ClassSchedule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository{

    private List<ClassSchedule> schedules = new ArrayList<>();

    public boolean isRoomFree(int roomId, LocalDateTime start, LocalDateTime end) {
        return schedules.stream().noneMatch(s ->
                s.getRoomId() == roomId &&
                !(end.isBefore(s.getStart()) || start.isAfter(s.getEnd()))
        );
    }

    public List<ClassSchedule> getRoomSchedule(int roomId, LocalDate date) {
        return schedules.stream()
                .filter(s -> s.getRoomId() == roomId && s.getStart().toLocalDate().equals(date))
                .toList();
    }

    public void save(ClassSchedule schedule) {
        schedules.add(schedule);
    }
}
