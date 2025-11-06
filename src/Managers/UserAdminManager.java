package Managers;

import Users.Administrator;
import Users.Student;
import Users.Teacher;

import java.util.ArrayList;
import java.util.List;

import static Storage.DataControl.*;

public class UserAdminManager {

    public void createUser(String name, String email, String password, String userType){
        if(userType.equals("teacher")){
            Teacher newTeacher = new Teacher(name, email, password);
            teachersList.add(newTeacher);

        }else if(userType.equals("student")){
            Student newStudent = new Student(name, email, password);
            studentsList.add(newStudent);

        }else if(userType.equals("admin")){
            Administrator newAdmin = new Administrator(name, email, password);
            adminsList.add(newAdmin);

        }
    }

    public void deleteUser(int id, String userType){
        int index = 0;
        if(userType.equals("teacher")){
            for(Teacher teacher : teachersList){
                if (teacher.getUserId() == id){
                    teachersList.remove(index);
                    return;
                }
                index += 1;
            }
        }else if(userType.equals("student")){
            for(Student student : studentsList){
                if (student.getUserId() == id){
                    studentsList.remove(index);
                    return;
                }
                index += 1;
            }
        }else if(userType.equals("admin")){
        for(Administrator admin : adminsList){
            if (admin.getUserId() == id){
                adminsList.remove(index);
                return;
            }
            index += 1;
        }
        System.out.println("|ERROR| - User not found");
    }
    }

    public void changeUser(int id, String newName, String newEmail, String newPassword, String userType){
        if(userType.equals("teacher")){
            for(Teacher teacher : teachersList){
                if (teacher.getUserId() == id){
                    teacher.updateUser(newName, newEmail, newPassword);
                    return;
                }
            }
        }else if(userType.equals("student")){
            for(Student student : studentsList){
                if (student.getUserId() == id){
                    student.updateUser(newName, newEmail, newPassword);
                    return;
                }
            }
        }else if(userType.equals("admin")){
            for(Administrator admin : adminsList){
                if (admin.getUserId() == id){
                    admin.updateUser(newName, newEmail, newPassword);
                    return;
                }
            }
        }
        System.out.println("\n|ERROR| - User not found");
    }

    public void showUsers(String userType){
        if(userType.equals("teacher")){
            for(Teacher teacher : teachersList){
                System.out.println("TeacherID: " + teacher.getUserId() + " || " + "Name: " + teacher.getUserName() + " || " + "Email: " + teacher.getUserEmail() + " || " + " Password: " + teacher.getUserPassword());
            }
        }else if(userType.equals("student")){
            for(Student student : studentsList){
                System.out.println("StudentID: " + student.getUserId() + " || " + "Name: " + student.getUserName() + " || " + "Email: " + student.getUserEmail() + " || " + " Password: " + student.getUserPassword());
            }
        }else if(userType.equals("admin")){
            for(Administrator admin : adminsList){
                System.out.println("AdminID: " + admin.getUserId() + " || " + "Name: " + admin.getUserName() + " || " + "Email: " + admin.getUserEmail() + " || " + " Password: " + admin.getUserPassword());
            }
        }
    }

    public List<Student> findStudents(String listOfIds){
        String[] ids = listOfIds.split(",");
        List<Integer> intIds = new ArrayList<>();
        int numeric_id;
        for(String id : ids){
            try{
                numeric_id = Integer.parseInt(id);
            }catch(NumberFormatException e){
                System.out.println("Input " + id + " has an incorrect format");
                continue;
            }
            intIds.add(numeric_id);
        }
        return getStudents(intIds);
    }

    private List<Student> getStudents(List<Integer> listIds) {
        List<Student> students = new ArrayList<>();

        for (Student student : studentsList){
            if (listIds.contains(student.getUserId())){
                students.add(student);
                listIds.remove(Integer.valueOf(student.getUserId())); // To be able to eliminate by value
            }
        }
        if(!listIds.isEmpty()){
            for (int id : listIds){
                System.out.println("User with id: " + id + " does not exist");
            }
        }
        return students;
    }

    public int getTeacherIndex(int teacherId){
        int index = 0;
        for(Teacher teacher : teachersList){
            if(teacher.getUserId() == teacherId){
                return index;
            }
            index += 1;
        }
        return -1;
    }
}
