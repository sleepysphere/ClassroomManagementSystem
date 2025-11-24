package model;

public class Room {
    private int RoomID;
    private String RoomNumber;
    private String RoomType; // CLASSROOM, LAB, GYM
    private int Capacity;

    public Room(int RoomID, String RoomNumber, String RoomType, int Capacity) {
        this.RoomID = RoomID;
        this.RoomNumber = RoomNumber;
        this.RoomType = RoomType;
        this.Capacity = Capacity;
    }

    public enum RoomType {
        CLASSROOM,
        LAB,
        GYM
    }

    public int getRoomID() { return RoomID; }
    public String getRoomNumber() { return RoomNumber; }
    public String getRoomType() { return RoomType; }
    public int getCapacity() { return Capacity; }

    public void setRoomID(int RoomID) { this.RoomID = RoomID; }
    public void setRoomNumber(String RoomNumber) { this.RoomNumber = RoomNumber; }
    public void setRoomType(String RoomType) { this.RoomType = RoomType; }
    public void setCapacity(int Capacity) { this.Capacity = Capacity; }
}

