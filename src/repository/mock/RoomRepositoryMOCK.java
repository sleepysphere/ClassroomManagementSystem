package repository.mock;

import repository.RoomRepository;
import model.Room;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock version of RoomRepository for testing without a database.
 */
public class RoomRepositoryMOCK implements RoomRepository {

    private List<Room> rooms = new ArrayList<>();

    @Override
    public Room findById(int id) {
        return rooms.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Room> findAll() {
        return rooms;
    }

    @Override
    public List<Room> findAvailable(LocalDateTime start, LocalDateTime end) {
        // Mock version just returns all rooms
        return rooms;
    }

    @Override
    public void save(Room room) {
        rooms.add(room);
    }
}
