import random

room_types = [
    "Lecture Hall",
    "Laboratory",
    "Seminar Room",
    "Computer Lab",
    "Auditorium",
    "Studio",
    "Workshop",
    "Conference Room"
]

def generate_room_number(index):
    # Creates things like A101, B203, D450, etc.
    building = random.choice(["A", "B", "C", "D", "E"])
    number = 100 + index
    return f"{building}{number}"

with open("src/misc/dummy_rooms.txt", "w") as f:
    for i in range(25):  # Generate 25 rooms
        room_number = generate_room_number(i)
        capacity = random.randint(20, 200)             # Typical room sizes
        room_type = random.choice(room_types)

        query = (
            "insert into rooms (RoomNumber, Capacity, RoomType) "
            f"values ('{room_number}', {capacity}, '{room_type}');"
        )

        f.write(query + "\n")
