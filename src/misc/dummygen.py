import random

first_names = ["John", "Jane", "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hank"]
last_names = ["Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"]

f = open("src/misc/dummy.txt", "w")
for i in range(25):
    f. write("""insert into students (StudentID, FirstName, LastName, DateOfBirth, Email, Phone) values ({0},{1},{2},{3},{4},{5});""".format(10000+i, random.choice(first_names), random.choice(last_names),random.randint(1995,2005),'str(10000+i)+"@school.com"',"+555"+str(random.randint(1000000,9999999))) + "\n")