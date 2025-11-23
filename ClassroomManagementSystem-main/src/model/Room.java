package model;

public class Room {
    private int id;
    private String name;
    private RoomType type; // CLASSROOM, LAB, GYM
    private int capacity;

    public Room(int id, String name, RoomType type, int capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public RoomType getType() { return type; }
    public int getCapacity() { return capacity; }

    public void setName(String name) { this.name = name; }
    public void setType(RoomType type) { this.type = type; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}

