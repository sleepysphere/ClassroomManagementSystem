package controller;

import javax.swing.SwingUtilities;
import gui.*;
import database.*;
import repository.sql.*;

public class InstructorController {
    // add, edit, delete teacher from database
    protected void addInstructor(String InstructorID, String firstName, String lastName, String hireDate, String department, String email, String phone) {
        try (var conn = DBConnection.getConnection()) {
            InstructorRepositorySQL instructorRepo = new InstructorRepositorySQL(conn);
            instructorRepo.addInstructor(InstructorID, firstName, lastName, hireDate, department, email, phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
