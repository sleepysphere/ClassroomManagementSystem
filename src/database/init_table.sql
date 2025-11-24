-- MySQL dump 10.13  Distrib 8.0.44, for Win64
-- Database: class_management
-- ------------------------------------------------------

-- DROP & CREATE RoomType table
DROP TABLE IF EXISTS `roomtypes`;
CREATE TABLE `roomtypes` (
  `RoomTypeID` int NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(50) NOT NULL UNIQUE,
  PRIMARY KEY (`RoomTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Seed default room types
INSERT INTO `roomtypes` (`TypeName`) VALUES
('Normal Class'),
('Lab'),
('Gym');

-- DROP & CREATE Rooms table
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms` (
  `RoomID` int NOT NULL AUTO_INCREMENT,
  `RoomNumber` varchar(10) NOT NULL,
  `Capacity` int NOT NULL,
  `RoomTypeID` int NOT NULL,
  PRIMARY KEY (`RoomID`),
  UNIQUE KEY `RoomNumber` (`RoomNumber`),
  CONSTRAINT `FK_Room_RoomType` FOREIGN KEY (`RoomTypeID`) REFERENCES `roomtypes`(`RoomTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- DROP & CREATE Classes table
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `ClassID` int NOT NULL AUTO_INCREMENT,
  `CourseID` int NOT NULL,
  `InstructorID` int NOT NULL,
  `ClassTime` time NOT NULL,
  `Semester` varchar(20) NOT NULL,
  PRIMARY KEY (`ClassID`),
  KEY `CourseID` (`CourseID`),
  KEY `InstructorID` (`InstructorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- DROP & CREATE Courses table
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `CourseID` int NOT NULL AUTO_INCREMENT,
  `CourseName` varchar(100) NOT NULL,
  `CourseCode` varchar(10) NOT NULL,
  `Description` text,
  `Credits` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`CourseID`),
  UNIQUE KEY `CourseCode` (`CourseCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- DROP & CREATE Students table
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `StudentID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`StudentID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- DROP & CREATE Instructors table
DROP TABLE IF EXISTS `instructors`;
CREATE TABLE `instructors` (
  `InstructorID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `HireDate` date DEFAULT NULL,
  `Department` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`InstructorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- DROP & CREATE Enrollments table
DROP TABLE IF EXISTS `enrollments`;
CREATE TABLE `enrollments` (
  `EnrollmentID` int NOT NULL AUTO_INCREMENT,
  `StudentID` int NOT NULL,
  `ClassID` int NOT NULL,
  `EnrollmentDate` date NOT NULL,
  `IsActive` bit(1) DEFAULT NULL,
  PRIMARY KEY (`EnrollmentID`),
  KEY `FK_Enrollments_Student` (`StudentID`),
  KEY `FK_Enrollments_Class` (`ClassID`),
  CONSTRAINT `FK_Enrollments_Class` FOREIGN KEY (`ClassID`) REFERENCES `classes` (`ClassID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Enrollments_Student` FOREIGN KEY (`StudentID`) REFERENCES `students` (`StudentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- DROP & CREATE LabSessions table
DROP TABLE IF EXISTS `labsessions`;
CREATE TABLE `labsessions` (
  `LabSessionID` int NOT NULL AUTO_INCREMENT,
  `ClassID` int NOT NULL,
  `RoomID` int NOT NULL,
  `SessionDate` date NOT NULL,
  `StartTime` time NOT NULL,
  `EndTime` time NOT NULL,
  PRIMARY KEY (`LabSessionID`),
  KEY `ClassID` (`ClassID`),
  KEY `FK_LabSessions_Room` (`RoomID`),
  CONSTRAINT `FK_LabSessions_Room` FOREIGN KEY (`RoomID`) REFERENCES `rooms` (`RoomID`),
  CONSTRAINT `labsessions_ibfk_1` FOREIGN KEY (`ClassID`) REFERENCES `classes` (`ClassID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

