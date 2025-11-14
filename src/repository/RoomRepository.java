package repository;

import model.Room;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Defines methods the database layer must implement for managing rooms.
 */

public interface RoomRepository {
    Room findById(int id);
    List<Room> findAll();
    /**
     * Returns rooms that are not occupied in the given time period.
     */
    List<Room> findAvailable(LocalDateTime start, LocalDateTime end);
    void save(Room room);
}
