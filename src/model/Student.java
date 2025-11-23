package model;

import java.time.LocalDate;

public class Student {
    private int StudentID;
    private String FirstName;
    private String LastName;
    private String Phone;
    private String Email;
    private LocalDate DateOfBirth;

    public Student(int StudentID, String FirstName, String LastName, String Phone, String Email, LocalDate DateOfBirth) {
        this.StudentID = StudentID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Phone = Phone;
        this.Email = Email;
        this.DateOfBirth = DateOfBirth;
    }

    public int getStudentID() { return StudentID; }
    public String getFirstName() { return FirstName; }
    public String getLastName() { return LastName; }
    public String getPhone() { return Phone; }
    public String getEmail() { return Email; }
    public LocalDate getDateOfBirth() { return DateOfBirth; }

    public void setFirstName(String FirstName) { this.FirstName = FirstName; }
    public void setLastName(String LastName) { this.LastName = LastName; }
    public void setPhone(String Phone) { this.Phone = Phone; }
    public void setEmail(String Email) { this.Email = Email; }
    public void setDateOfBirth(LocalDate DateOfBirth) { this.DateOfBirth = DateOfBirth; }
}
