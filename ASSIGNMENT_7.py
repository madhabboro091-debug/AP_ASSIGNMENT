from typing import List, Dict, Set
from collections import defaultdict
from functools import reduce


# ---------- FUNCTION: TOTAL TIME PER USER ----------

def total_time_per_user(logs: List[Dict[str, object]]) -> Dict[str, float]:

    def reducer(acc: Dict[str, float], log: Dict[str, object]) -> Dict[str, float]:
        acc[log["user"]] += log["duration"]
        return acc

    return dict(reduce(reducer, logs, defaultdict(float)))


# ---------- FUNCTION: TOP K MOST ACTIVE USERS ----------

def most_active_users(logs: List[Dict[str, object]], k: int) -> List[str]:

    totals = total_time_per_user(logs)

    return [
        user
        for user, _ in sorted(
            totals.items(),
            key=lambda item: item[1],
            reverse=True
        )[:k]
    ]


# ---------- FUNCTION: UNIQUE ACTIONS ----------

def unique_actions(logs: List[Dict[str, object]]) -> Set[str]:

    return {log["action"] for log in logs}


# ---------- FUNCTION: DISPLAY RECORDS ----------

def display_logs(logs: List[Dict[str, object]]) -> None:

    if not logs:
        print("No records available.")
        return

    print("\n========== STUDENT ACTIVITY RECORDS ==========")

    current_user = None

    for log in logs:

        if log["user"] != current_user:

            current_user = log["user"]

            print("\n---------------------------------------------")
            print(f"Roll Number : {current_user}")
            print("---------------------------------------------")

        print(f"Action   : {log['action']}")
        print(f"Duration : {log['duration']} hours")

    print("\n---------------------------------------------")


# ---------- PREDEFINED DATA ----------

logs = [
    {"user": "CSB24035", "action": "YouTube", "duration": 1.5},
    {"user": "CSB24035", "action": "LeetCode", "duration": 2.0},

    {"user": "CSB24036", "action": "Instagram", "duration": 1.0},
    {"user": "CSB24036", "action": "WhatsApp", "duration": 0.8},

    {"user": "CSB24037", "action": "YouTube", "duration": 3.5}
]


# ---------- MAIN PROGRAM ----------

while True:

    print("\n========== Activity Log Analyzer ==========")
    print("1. View all records")
    print("2. Add new student activity")
    print("3. Show total time per user")
    print("4. Show most active users (Top K)")
    print("5. Show unique actions")
    print("6. Exit")

    choice = input("Enter your choice: ")


    if choice == "1":

        display_logs(logs)


    elif choice == "2":

        print("\n---------- ADD NEW STUDENT ACTIVITY ----------")

        user = input("Enter roll number: ")

        while True:

            action = input("Enter action: ")
            duration = float(input("Enter duration (hours): "))

            logs.append({
                "user": user,
                "action": action,
                "duration": duration
            })

            print("---------------------------------------------")

            more = input("Add another activity? (y/n): ")

            if more.lower() != "y":
                break


    elif choice == "3":

        print("\n---------- TOTAL TIME PER USER ----------")

        totals = total_time_per_user(logs)

        for user, time in totals.items():
            print(f"{user} : {time} hours")

        print("----------------------------------------")


    elif choice == "4":

        k = int(input("Enter value of k: "))

        top_users = most_active_users(logs, k)

        print("\n---------- TOP K MOST ACTIVE USERS ----------")

        for i, user in enumerate(top_users, 1):
            print(f"{i}. {user}")

        print("---------------------------------------------")


    elif choice == "5":

        print("\n---------- UNIQUE ACTIONS ----------")

        actions = unique_actions(logs)

        for action in actions:
            print(action)

        print("-----------------------------------")


    elif choice == "6":

        print("Exiting program.")
        break


    else:

        print("Invalid choice.")