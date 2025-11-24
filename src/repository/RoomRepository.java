package repository;

import model.Room;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository{

    private List<Room> rooms = new ArrayList<>();

    public Room findById(int id) {
        return rooms.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public List<Room> findAll() {
        return rooms;
    }
    public List<Room> findAvailable(LocalDateTime start, LocalDateTime end) {
        // Mock version just returns all rooms
        return rooms;
    }

    public void save(Room room) {
        rooms.add(room);
    }
}
