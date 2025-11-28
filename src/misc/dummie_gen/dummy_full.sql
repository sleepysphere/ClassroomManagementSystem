-- STUDENTS
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Eve', 'Smith', '2002-09-13', 'eve.smith.0@school.com', '+5554113661');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Eve', 'Brown', '1998-04-19', 'eve.brown.1@school.com', '+5558747564');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Charlie', 'Jones', '2000-04-27', 'charlie.jones.2@school.com', '+5557418720');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Frank', 'Johnson', '1997-11-20', 'frank.johnson.3@school.com', '+5556786800');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Alice', 'Jones', '2000-08-02', 'alice.jones.4@school.com', '+5558284123');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Alice', 'Garcia', '2002-01-11', 'alice.garcia.5@school.com', '+5554688800');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Frank', 'Garcia', '1999-12-03', 'frank.garcia.6@school.com', '+5557618996');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Bob', 'Brown', '2002-03-25', 'bob.brown.7@school.com', '+5553420798');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Jane', 'Jones', '1995-06-20', 'jane.jones.8@school.com', '+5557027845');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('John', 'Johnson', '2004-07-13', 'john.johnson.9@school.com', '+5552064913');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Frank', 'Smith', '2001-06-06', 'frank.smith.10@school.com', '+5555089174');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Frank', 'Jones', '2002-06-21', 'frank.jones.11@school.com', '+5558001252');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('John', 'Brown', '2004-10-27', 'john.brown.12@school.com', '+5552306747');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Jane', 'Garcia', '2005-02-15', 'jane.garcia.13@school.com', '+5558650343');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Jane', 'Smith', '2003-07-21', 'jane.smith.14@school.com', '+5558688734');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Frank', 'Johnson', '2005-10-25', 'frank.johnson.15@school.com', '+5553945132');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Diana', 'Johnson', '2005-08-19', 'diana.johnson.16@school.com', '+5557622457');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Jane', 'Jones', '2005-08-05', 'jane.jones.17@school.com', '+5556976817');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Eve', 'Williams', '2000-09-17', 'eve.williams.18@school.com', '+5552244060');
INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) VALUES ('Frank', 'Garcia', '1999-09-09', 'frank.garcia.19@school.com', '+5557849756');

-- INSTRUCTORS
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Beth', 'Clark', '2018-09-15', 'Physics', '+5557096849', 'beth.clark.0@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('David', 'Foster', '2022-07-30', 'Computer Science', '+5552283390', 'david.foster.1@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('David', 'Bennett', '2021-03-17', 'Biology', '+5559421885', 'david.bennett.2@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Adam', 'Foster', '2020-01-16', 'Biology', '+5555436355', 'adam.foster.3@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Beth', 'Adams', '2010-04-27', 'Physics', '+5557152122', 'beth.adams.4@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Beth', 'Foster', '2021-11-15', 'Biology', '+5556309674', 'beth.foster.5@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('David', 'Foster', '2014-01-04', 'Biology', '+5552414487', 'david.foster.6@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Eleanor', 'Clark', '2012-11-26', 'Biology', '+5552349507', 'eleanor.clark.7@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Adam', 'Bennett', '2019-09-05', 'Physics', '+5552000107', 'adam.bennett.8@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Beth', 'Adams', '2002-04-12', 'Mathematics', '+5558210844', 'beth.adams.9@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Beth', 'Clark', '2003-06-01', 'Biology', '+5553300093', 'beth.clark.10@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('David', 'Evans', '2012-02-07', 'Mathematics', '+5557770027', 'david.evans.11@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Adam', 'Foster', '2003-01-15', 'Physics', '+5551058792', 'adam.foster.12@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Adam', 'Evans', '2014-03-04', 'Mathematics', '+5558527171', 'adam.evans.13@university.com');
INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) VALUES ('Felix', 'Adams', '2014-06-20', 'Physics', '+5557134508', 'felix.adams.14@university.com');

-- COURSES
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('CS Intro', 'MATH100', 'This is the course description for CS Intro.', 4.0, 1, 7, 84, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Psych Principles', 'CS101', 'This is the course description for Psych Principles.', 3.0, 0, 10, 188, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Chem Applications', 'BIO102', 'This is the course description for Chem Applications.', 2.0, 0, 11, 153, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('CS Advanced', 'CHEM103', 'This is the course description for CS Advanced.', 4.0, 1, 20, 157, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Phys Applications', 'PHYS104', 'This is the course description for Phys Applications.', 2.0, 1, 6, 137, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Chem Applications', 'ECON105', 'This is the course description for Chem Applications.', 2.0, 0, 13, 179, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Econ Lab', 'PSYCH106', 'This is the course description for Econ Lab.', 4.0, 1, 14, 75, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Econ Advanced', 'MATH107', 'This is the course description for Econ Advanced.', 2.5, 0, 7, 136, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Chem Applications', 'CS108', 'This is the course description for Chem Applications.', 2.5, 1, 13, 98, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Phys Lab', 'BIO109', 'This is the course description for Phys Lab.', 2.0, 1, 7, 189, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Chem Principles', 'CHEM110', 'This is the course description for Chem Principles.', 4.0, 0, 19, 145, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Phys Principles', 'PHYS111', 'This is the course description for Phys Principles.', 2.5, 0, 20, 159, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Math Intro', 'ECON112', 'This is the course description for Math Intro.', 4.0, 0, 5, 98, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Math Lab', 'PSYCH113', 'This is the course description for Math Lab.', 2.5, 1, 5, 75, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Psych Advanced', 'MATH114', 'This is the course description for Psych Advanced.', 4.0, 0, 8, 129, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Econ Intro', 'CS115', 'This is the course description for Econ Intro.', 3.0, 0, 8, 45, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Bio Intro', 'BIO116', 'This is the course description for Bio Intro.', 2.0, 1, 19, 174, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('CS Advanced', 'CHEM117', 'This is the course description for CS Advanced.', 4.0, 1, 14, 32, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Phys Advanced', 'PHYS118', 'This is the course description for Phys Advanced.', 3.0, 1, 20, 150, 1);
INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, SessionCount, EnrollmentLimit, IsActive) VALUES ('Math Lab', 'ECON119', 'This is the course description for Math Lab.', 4.0, 0, 8, 63, 1);

-- ROOMS
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('C100', 71, 'Auditorium');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('B101', 108, 'Lecture Hall');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('C102', 97, 'Lecture Hall');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('D103', 108, 'Lecture Hall');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('A104', 135, 'Computer Lab');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('D105', 91, 'Computer Lab');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('C106', 70, 'Computer Lab');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('C107', 35, 'Computer Lab');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('A108', 176, 'Auditorium');
INSERT INTO rooms (RoomNumber, Capacity, RoomType) VALUES ('A109', 142, 'Lab');

-- SCHEDULES
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (20, 8, 'Lab', '2025-10-16', '2025-12-21', 3, 'Thursday', '12:30:00', '12:00:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (1, 6, 'Lecture', '2025-10-03', '2025-01-27', 5, 'Tuesday', '15:30:00', '11:00:00', 'Fall 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (9, 9, 'Seminar', '2025-07-18', '2025-07-29', 6, 'Tuesday', '11:30:00', '17:00:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (20, 3, 'Seminar', '2025-08-30', '2026-06-17', 2, 'Tuesday', '14:30:00', '10:00:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (3, 8, 'Lab', '2025-02-13', '2026-09-30', 8, 'Friday', '12:00:00', '15:00:00', 'Fall 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (1, 8, 'Lab', '2025-05-31', '2026-11-16', 4, 'Monday', '17:30:00', '11:30:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (3, 8, 'Lecture', '2025-07-05', '2026-07-18', 8, 'Wednesday', '14:30:00', '14:30:00', 'Fall 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (9, 9, 'Lab', '2025-02-16', '2026-08-28', 10, 'Tuesday', '16:00:00', '13:00:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (6, 8, 'Lecture', '2025-08-28', '2025-03-10', 10, 'Thursday', '17:30:00', '14:00:00', 'Fall 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (3, 3, 'Lab', '2025-04-10', '2026-09-03', 8, 'Monday', '11:30:00', '16:30:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (13, 9, 'Lab', '2025-12-12', '2025-12-28', 10, 'Friday', '16:00:00', '11:00:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (7, 15, 'Seminar', '2025-09-23', '2025-01-25', 2, 'Tuesday', '12:00:00', '17:00:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (7, 15, 'Seminar', '2025-09-01', '2025-12-26', 1, 'Wednesday', '11:30:00', '15:00:00', 'Fall 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (1, 13, 'Seminar', '2025-01-18', '2025-09-18', 6, 'Monday', '11:00:00', '12:30:00', 'Spring 2025');
INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES (11, 5, 'Lab', '2025-05-25', '2025-02-09', 2, 'Thursday', '10:30:00', '16:00:00', 'Fall 2025');

-- SCHEDULES EXCEPTIONS
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (3, '2025-06-12', 'Cancelled', NULL, NULL, NULL, 6);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (15, '2026-06-13', 'Rescheduled', '2025-10-27', '17:00:00', '12:30:00', 9);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (6, '2026-06-11', 'Rescheduled', '2025-06-30', '09:30:00', '11:30:00', 7);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (13, '2026-02-04', 'Rescheduled', '2025-07-20', '11:00:00', '16:00:00', 1);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (8, '2026-10-12', 'Cancelled', NULL, NULL, NULL, 4);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (2, '2025-11-28', 'Rescheduled', '2025-10-28', '13:00:00', '11:00:00', 4);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (12, '2026-07-14', 'Cancelled', NULL, NULL, NULL, 10);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (13, '2026-05-05', 'Cancelled', NULL, NULL, NULL, 6);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (2, '2026-01-15', 'Rescheduled', '2026-05-27', '10:30:00', '14:30:00', 9);
INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) VALUES (4, '2025-04-14', 'Cancelled', NULL, NULL, NULL, 5);

-- ENROLLMENTS
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (1, '2025-04-05', b'0', 9, 15, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (1, '2025-01-14', b'1', 7, 7, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (14, '2025-07-10', b'0', 4, 7, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (2, '2025-10-19', b'1', 1, 10, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (7, '2025-04-22', b'0', 4, 1, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (2, '2025-09-27', b'1', 8, 5, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (17, '2024-04-18', b'0', 3, 10, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (19, '2025-12-18', b'0', 17, 14, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (17, '2025-09-12', b'1', 1, 15, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (14, '2024-01-07', b'0', 16, 5, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (16, '2025-01-08', b'1', 1, 3, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (10, '2025-10-17', b'1', 6, 1, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (18, '2024-08-26', b'0', 3, 12, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (15, '2025-07-06', b'1', 9, 11, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (4, '2024-09-06', b'1', 5, 5, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (17, '2025-02-02', b'0', 15, 6, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (14, '2025-02-20', b'1', 5, 3, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (19, '2024-03-05', b'1', 10, 3, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (6, '2025-05-21', b'0', 3, 10, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (2, '2024-07-03', b'1', 19, 13, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (15, '2025-11-05', b'1', 17, 7, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (1, '2024-12-18', b'0', 10, 12, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (17, '2024-03-15', b'1', 14, 12, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (20, '2025-09-06', b'0', 2, 8, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (5, '2024-10-20', b'0', 14, 14, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (8, '2025-01-01', b'1', 10, 2, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (8, '2024-06-14', b'1', 5, 4, 'Spring 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (18, '2024-11-02', b'1', 14, 15, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (17, '2025-04-15', b'0', 10, 15, 'Fall 2025');
INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) VALUES (10, '2024-09-28', b'1', 3, 13, 'Fall 2025');

