package Managers;

import Users.Administrator;
import Users.*;

import static Storage.DataControl.*;

public class AuthenticationManager{
    // First int is the User Class, second int is its location in its respective list ==> {UserClass, PositionInList}
    public static int[] validateUser(int userId, String password){ // 0 = SUPERADMIN || 1 = ADMIN || 2 = TEACHER || 3 = STUDENT || -1 = FAILURE
        int[] failureOutput = {-1, -1};

        if(userId == 0){
            Administrator superAdmin = adminsList.getFirst();
            if(superAdmin.getUserPassword().equals(password)){
                return new int[] {0,0};
            }
            return failureOutput;
        }

        int userType = (userId % 3); // mod = 0 => ADMIN || mod = 1 => TEACHER || mod = 2 => STUDENT
        switch(userType){
            case 0:
                for(int i = 0; i < adminsList.size(); i++){
                    Administrator admin = adminsList.get(i);
                    if(admin.getUserId() == userId){
                        if(admin.getUserPassword().equals(password)){
                            return new int[] {1, i};
                        }
                    }
                }
                break;
            case 1:
                for(int i = 0; i < teachersList.size(); i++){
                    Teacher teacher = teachersList.get(i);
                    if(teacher.getUserId() == userId){
                        if(teacher.getUserPassword().equals(password)){
                            return new int[] {2, i};
                        }
                    }
                }
                break;
            case 2:
                for(int i = 0; i < studentsList.size(); i++){
                    Student student = studentsList.get(i);
                    if(student.getUserId() == userId){
                        if(student.getUserPassword().equals(password)){
                            return new int[] {3, i};
                        }
                    }
                }
                break;
            default:
                return failureOutput;
        }
        return failureOutput;
    }


}
