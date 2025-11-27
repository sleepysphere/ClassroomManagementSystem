package controller;

import javax.swing.SwingUtilities;
import gui.*;
import database.*;
import repository.*;


public class RoomController {

    //container for room entries
    public static class RoomEntry{
        public String roomID;
        public String roomNumber;
        public int capacity;
        public String type;
    }
    // add 3 functions, add, edit and delete, connect to database
    public void addRoom(String roomID,String roomNumber, int capacity, String type) {
        String insertQuery = String.format("INSERT INTO rooms (RoomID, RoomNumber, Capacity, Type) VALUES ('%s', '%s', %d, '%s')",
                roomID.toString(), roomNumber.toString(), capacity, type.toString());
        
    }
}