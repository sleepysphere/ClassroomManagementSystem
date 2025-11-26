package controller;

import javax.swing.SwingUtilities;
import gui.*;
import database.*;
import repository.*;

/* 
public class RoomController {
    // add 3 functions, add, edit and delete, connect to database
    public void addRoom(String roomNumber, int capacity, String type) {
        try (var conn = DBConnection.getConnection()) {
            RoomRepository roomRepo = new RoomRepository(conn);
            roomRepo.addRoom(roomNumber, capacity, type);
        } catch (Exception e) {
            e.printStackTrace();
}
*/