import random

f = open("src/misc/dummy.txt", "w")

i = 400
name = ["Intro to Programming", "Data Structures", "Algorithms", "Database Systems", "Operating Systems",
        "Computer Networks", "Software Engineering", "Web Development", "Mobile App Development"]
code = ["CS101", "CS102", "CS201", "CS202", "CS301", "CS302", "CS401", "CS402", "CS403"]
desc = " no description "
credits = [3, 4, 5]
requireslab = [False, True]
isactive = [False, True]
sessioncount = [10, 15, 20]
enrollmentlimit = [30, 50, 100]

for i in range(25):
    courseid = i + 1
    coursecode = random.choice(code)+str(random.randint(100, 499))


    query = (
            "INSERT INTO courses "
            "(courseid, coursename, coursecode, description, credits, requireslab, isactive, sessioncount, enrollmentlimit)"
            f"VALUES   ('{courseid}','{coursecode}','{desc}','{random.choice(credits)}','{random.choice(requireslab)}','{random.choice(isactive)}','{random.choice(sessioncount)}','{random.choice(enrollmentlimit)}');"
)
    f.write(query + "\n")