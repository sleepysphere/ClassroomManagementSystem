package controller;

import javax.swing.SwingUtilities;
import gui.*;
import database.*;
import repository.*;

/*
public class ClassController {
    // Create 3 functions, add edit and delete, connect to database
    public void addClass(String className, String instructor, String schedule) {
        try (var conn = DBConnection.getConnection()) {
            ClassRepository classRepo = new ClassRepository(conn);
            classRepo.addClass(className, instructor, schedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/