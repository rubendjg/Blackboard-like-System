# Blackboard-like System

## Setup
Open the folder **“Blackboard Sim”** with *IntelliJ IDEA* and run the **main class**.

---

## Important Information

The system is **already populated**, so if you want to try it with no data, just remove the following files:

- `Admin.bin`
- `Course.bin`
- `Student.bin`
- `Teacher.bin`

Then, log in as the **Super Admin** and start populating the system.

---

### Preloaded Accounts

#### 🧑‍💼 Super Admin
- **ID:** `0`  
- **Password:** `admin`  
- Access the SuperAdmin view to **create and manage Admins**.

---

#### Admin
- **ID:** `3`  
- **Password:** `test`  
- Access an already created Admin account.  
  → You can **modify, create, and delete** Courses, Students, and Teachers.

---

#### Teacher
- **ID:** `1`  
- **Password:** `test`  
- Access an already created Teacher account with **courses and assignments**, including a **graded assignment**.

---

#### Student
- **ID:** `20`  
- **Password:** `password`  
- Access an already created Student account with **many courses**.  
  → If you access the course with **ID = 3**, you’ll see **already created assignments**.

---

### Additional Notes

- To access any **Student or Teacher**, log in as a **normal Admin** and check all Students or Teachers.  
- To access any **Admin**, log in as the **SuperAdmin** using the provided credentials and manage them from there.
- To create a Course, you first need to have at least one teacher, so take into account if cleaning the system
