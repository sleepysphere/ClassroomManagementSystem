# 🔌 Backend API Documentation
**Project:** Classroom Management System
**Version:** 1.0 (Final)

## Overview
This document outlines the public static methods available in the Backend `repository` package. The Frontend team should call these methods directly from event listeners (e.g., button clicks) to interact with the database.

---

## 👨‍🎓 Module 1: Student Management
**Package:** `repository.sql.StudentRepository`

| Action | Method Signature | Returns | Description |
| :--- | :--- | :--- | :--- |
| **Get Details** | `getStudentById(int id)` | `Student` | Returns null if ID not found. |
| **Register** | `addStudent(Student s)` | `boolean` | Creates a new student. ID is auto-generated. |
| **Update** | `updateStudent(Student s)` | `boolean` | Updates Name, Email, Phone, DOB based on ID. |
| **Expel** | `deleteStudent(int id)` | `boolean` | **Warning:** Automatically drops student from all classes. |

---

## 👨‍🏫 Module 2: Instructor Management
**Package:** `repository.sql.InstructorRepositorySQL`

| Action | Method Signature | Returns | Description |
| :--- | :--- | :--- | :--- |
| **Get Details** | `getInstructorById(int id)` | `Instructor` | Returns null if ID not found. |
| **Hire** | `addInstructor(Instructor i)` | `boolean` | Creates a new instructor. |
| **Update** | `updateInstructor(Instructor i)` | `boolean` | Updates contact info and department. |
| **Fire** | `deleteInstructor(int id)` | `boolean` | **Warning:** Cancels all classes taught by this instructor. |

---

## 📅 Module 3: Schedule Management (Admin)
**Package:** `repository.sql.ScheduleRepositorySQL`

| Action | Method Signature | Returns | Description |
| :--- | :--- | :--- | :--- |
| **Get Info** | `getScheduleById(int id)` | `Schedule` | Returns schedule details (Room, Time, Type). |
| **Schedule Class**| `addSchedule(Schedule s)` | `boolean` | Adds a weekly Lecture or Lab block. |
| **Edit Class** | `updateSchedule(Schedule s)` | `boolean` | Moves class to new Room/Time/Instructor. |
| **Cancel Sem.** | `deleteSchedule(int id)` | `boolean` | Cancels the class for the entire semester. |

> **Note:** Use `SessionType` ("Lecture"/"Lab") and `StartDate`/`EndDate` fields to define the duration.

---

## 📝 Module 4: Enrollment (Student)
**Package:** `repository.sql.EnrollmentRepositorySQL`

| Action | Method Signature | Returns | Description |
| :--- | :--- | :--- | :--- |
| **Enroll** | `addEnrollment(Enrollment e)` | `boolean` | **Logic:** Checks Course Limit. Returns `false` if full. |
| **Drop Class** | `deleteEnrollment(int id)` | `boolean` | Removes the student from the class. |

> **Frontend Logic:** If `addEnrollment` returns `false`, display a "Course is Full" popup to the user.

---

## ⚠️ Module 5: Exceptions (Cancellations)
**Package:** `repository.sql.ScheduleExceptionRepositorySQL`

| Action | Method Signature | Returns | Description |
| :--- | :--- | :--- | :--- |
| **Get Exception** | `getExceptionById(int id)` | `ScheduleException` | View details of a specific change. |
| **Cancel/Move** | `addException(ScheduleException ex)` | `boolean` | Cancels or Reschedules a specific date. |
| **Edit Change** | `updateException(ScheduleException ex)` | `boolean` | Modify the rescheduling details. |
| **Restore** | `deleteException(int id)` | `boolean` | Un-cancels the class (restores original schedule). |

> **Tip:** To Cancel a class, set `NewDate`, `StartTime`, `EndTime` to `null` and `RoomID` to `0`.

---

## 📘 Module 6: Course Catalog
**Package:** `repository.sql.CourseRepositorySQL`

| Action | Method Signature | Returns | Description |
| :--- | :--- | :--- | :--- |
| **Get Course** | `getCourseById(int id)` | `Course` | Returns credits, description, limits, etc. |
| **Create** | `addCourse(Course c)` | `boolean` | Defines a new subject (e.g., "Intro to CS"). |
| **Update** | `updateCourse(Course c)` | `boolean` | Updates description, credits, or limits. |
| **Archive** | `archiveCourse(int id)` | `boolean` | Soft Delete (Hides from future scheduling). |
| **Delete** | `deleteCourse(int id)` | `boolean` | **Nuclear Option:** Deletes ALL history/transcripts. |

---

## 🏢 Module 7: Facilities (Rooms)
**Package:** `repository.sql.RoomRepository`

| Action | Method Signature | Returns | Description |
| :--- | :--- | :--- | :--- |
| **Get Room** | `getRoomById(int id)` | `Room` | Returns capacity and type. |
| **Add Room** | `addRoom(Room r)` | `boolean` | add a new room. |
| **Update** | `updateRoom(Room r)` | `boolean` | Change capacity or room number. |
| **Demolish** | `deleteRoom(int id)` | `boolean` | **Warning:** Cancels all classes in this room. |

---

## 🖥️ Example Usage (Java Swing/JavaFX)

```java
// Example: Save Button Listener
saveButton.addActionListener(e -> {
    try {
        // 1. Create the Object
        Student newStudent = new Student(
            0, 
            nameField.getText(), 
            lastNameField.getText(), 
            phoneField.getText(), 
            emailField.getText(), 
            LocalDate.parse(dobField.getText())
        );

        // 2. Call Backend
        boolean success = StudentRepository.addStudent(newStudent);

        // 3. Update UI
        if (success) {
            JOptionPane.showMessageDialog(null,