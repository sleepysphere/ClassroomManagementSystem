import random
from datetime import datetime, timedelta

first_names = ["Adam", "Beth", "Catherine", "David", "Eleanor", "Felix",
               "George", "Helen", "Ian", "Julia", "Karl", "Laura"]

last_names = ["Anderson", "Bennett", "Clark", "Diaz", "Edwards", "Foster",
              "Gibson", "Hughes", "King", "Lopez", "Morgan", "Nelson"]

departments = [
    "Mathematics", "Computer Science", "Physics", "Chemistry",
    "Biology", "History", "English", "Psychology",
    "Economics", "Philosophy"
]

def random_date(start_year=1990, end_year=2022):
    """Generate a valid random date between Jan 1 of start_year and Dec 31 of end_year."""
    start = datetime(start_year, 1, 1)
    end = datetime(end_year, 12, 31)
    delta = end - start
    random_days = random.randrange(delta.days)
    return (start + timedelta(days=random_days)).strftime("%Y-%m-%d")

with open("src/misc/dummy_instructors.txt", "w") as f:
    for i in range(25):
        id = i + 50000
        first = random.choice(first_names)
        last = random.choice(last_names)
        hire_date = random_date(1990, 2022)
        dept = random.choice(departments)
        email = f"{first.lower()}.{last.lower()}@university.com"
        phone = str(random.randint(1000000000, 9999999999))  # 10-digit phone

        query = (
            "insert into instructors "
            "(InstructorID, FirstName, LastName, HireDate, Department, Email, Phone) "
            f"values ('{id}','{first}', '{last}', '{hire_date}', '{dept}', '{email}', '{phone}');"
        )

        f.write(query + "\n")
