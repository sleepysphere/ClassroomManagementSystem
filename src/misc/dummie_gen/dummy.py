import random
from datetime import datetime, timedelta

# --------------------------
# Helper functions
# --------------------------

def random_date(start_year=1990, end_year=2022):
    start = datetime(start_year, 1, 1)
    end = datetime(end_year, 12, 31)
    delta = (end - start).days
    return (start + timedelta(days=random.randint(0, delta))).strftime("%Y-%m-%d")

def random_time():
    hour = random.randint(8, 17)
    minute = random.choice([0, 30])
    return f"{hour:02d}:{minute:02d}:00"

# --------------------------
# Students
# --------------------------
def generate_students(n=20):
    first = ["John", "Jane", "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank"]
    last = ["Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia"]

    data = []
    for i in range(n):
        fn = random.choice(first)
        ln = random.choice(last)
        dob = random_date(1995, 2005)
        email = f"{fn.lower()}.{ln.lower()}.{i}@school.com"
        phone = "+555" + str(random.randint(1000000, 9999999))

        data.append(
            f"INSERT INTO students (FirstName, LastName, DateOfBirth, Email, Phone) "
            f"VALUES ('{fn}', '{ln}', '{dob}', '{email}', '{phone}');"
        )
    return data

# --------------------------
# Instructors
# --------------------------
def generate_instructors(n=15):
    fnames = ["Adam", "Beth", "Clara", "David", "Eleanor", "Felix"]
    lnames = ["Adams", "Bennett", "Clark", "Diaz", "Evans", "Foster"]
    depts = ["Computer Science", "Physics", "Mathematics", "Biology"]

    data = []
    for i in range(n):
        fn = random.choice(fnames)
        ln = random.choice(lnames)
        hire = random_date(2000, 2022)
        dept = random.choice(depts)
        phone = "+555" + str(random.randint(1000000, 9999999))
        email = f"{fn.lower()}.{ln.lower()}.{i}@university.com"

        data.append(
            f"INSERT INTO instructors (FirstName, LastName, HireDate, Department, Phone, Email) "
            f"VALUES ('{fn}', '{ln}', '{hire}', '{dept}', '{phone}', '{email}');"
        )
    return data

# --------------------------
# Courses
# --------------------------
def generate_courses(n=20):
    subjects = ["Math", "CS", "Bio", "Chem", "Phys", "Econ", "Psych"]
    topics = ["Intro", "Advanced", "Principles", "Applications", "Lab"]

    data = []
    for i in range(n):
        name = f"{random.choice(subjects)} {random.choice(topics)}"
        code = f"{subjects[i % len(subjects)].upper()}{100+i}"
        desc = f"This is the course description for {name}."
        credits = random.choice([2.00, 2.50, 3.00, 4.00])
        requires_lab = random.choice([0, 1])
        sessions = random.randint(5, 20)
        limit = random.randint(20, 200)

        data.append(
            "INSERT INTO courses (CourseName, CourseCode, Description, Credits, RequiresLab, "
            f"SessionCount, EnrollmentLimit, IsActive) VALUES ('{name}', '{code}', '{desc}', "
            f"{credits}, {requires_lab}, {sessions}, {limit}, 1);"
        )

    return data

# --------------------------
# Rooms
# --------------------------
def generate_rooms(n=10):
    types = ["Lecture Hall", "Lab", "Computer Lab", "Auditorium"]
    
    data = []
    for i in range(n):
        building = random.choice(["A", "B", "C", "D"])
        number = f"{building}{100 + i}"
        cap = random.randint(20, 200)
        rtype = random.choice(types)

        data.append(
            f"INSERT INTO rooms (RoomNumber, Capacity, RoomType) "
            f"VALUES ('{number}', {cap}, '{rtype}');"
        )

    return data

# --------------------------
# Schedules
# --------------------------
def generate_schedules(course_count, instructor_count, room_count, n=15):
    days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"]
    types = ["Lecture", "Lab", "Seminar"]

    data = []
    for i in range(n):
        course_id = random.randint(1, course_count)
        inst_id = random.randint(1, instructor_count)
        room_id = random.randint(1, room_count)
        stype = random.choice(types)
        start_date = random_date(2025, 2025)
        end_date = random_date(2025, 2026)
        day = random.choice(days)
        start_time = random_time()
        end_time = random_time()
        sem = random.choice(["Spring 2025", "Fall 2025"])

        data.append(
            "INSERT INTO schedules (CourseID, InstructorID, SessionType, StartDate, EndDate, "
            f"RoomID, DayOfWeek, StartTime, EndTime, Semester) VALUES "
            f"({course_id}, {inst_id}, '{stype}', '{start_date}', '{end_date}', "
            f"{room_id}, '{day}', '{start_time}', '{end_time}', '{sem}');"
        )

    return data

# --------------------------
# Schedule Exceptions
# --------------------------
def generate_exceptions(schedule_count, room_count, n=10):
    statuses = ["Cancelled", "Rescheduled"]

    data = []
    for i in range(n):
        class_id = random.randint(1, schedule_count)
        ex_date = random_date(2025, 2026)
        status = random.choice(statuses)
        new_date = random_date(2025, 2026) if status == "Rescheduled" else "NULL"
        start_t = random_time() if status == "Rescheduled" else "NULL"
        end_t = random_time() if status == "Rescheduled" else "NULL"
        room_id = random.randint(1, room_count)

        data.append(
            "INSERT INTO schedulesexceptions (ClassID, ExceptionDate, ClassStatus, NewDate, StartTime, EndTime, RoomID) "
            f"VALUES ({class_id}, '{ex_date}', '{status}', "
            f"{f"'{new_date}'" if new_date!='NULL' else 'NULL'}, "
            f"{f"'{start_t}'" if start_t!='NULL' else 'NULL'}, "
            f"{f"'{end_t}'" if end_t!='NULL' else 'NULL'}, "
            f"{room_id});"
        )

    return data

# --------------------------
# Enrollments
# --------------------------
def generate_enrollments(student_count, course_count, instructor_count, n=30):
    semesters = ["Spring 2025", "Fall 2025"]

    data = []
    for i in range(n):
        sid = random.randint(1, student_count)
        cid = random.randint(1, course_count)
        inst = random.randint(1, instructor_count)
        date = random_date(2024, 2025)
        active = random.choice([0, 1])
        sem = random.choice(semesters)

        data.append(
            f"INSERT INTO enrollments (StudentID, EnrollmentDate, IsActive, CourseID, InstructorID, Semester) "
            f"VALUES ({sid}, '{date}', b'{active}', {cid}, {inst}, '{sem}');"
        )

    return data


# --------------------------
# File Output
# --------------------------

with open("src/misc/dummie_gen/dummy_full.sql", "w") as f:
    f.write("-- STUDENTS\n")
    f.write("\n".join(generate_students(20)) + "\n\n")

    f.write("-- INSTRUCTORS\n")
    f.write("\n".join(generate_instructors(15)) + "\n\n")

    f.write("-- COURSES\n")
    f.write("\n".join(generate_courses(20)) + "\n\n")

    f.write("-- ROOMS\n")
    f.write("\n".join(generate_rooms(10)) + "\n\n")

    f.write("-- SCHEDULES\n")
    f.write("\n".join(generate_schedules(20, 15, 10, 15)) + "\n\n")

    f.write("-- SCHEDULES EXCEPTIONS\n")
    f.write("\n".join(generate_exceptions(15, 10, 10)) + "\n\n")

    f.write("-- ENROLLMENTS\n")
    f.write("\n".join(generate_enrollments(20, 20, 15, 30)) + "\n\n")
