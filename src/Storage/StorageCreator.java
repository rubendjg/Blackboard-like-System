package Storage;

import java.io.File;
import java.io.IOException;

public class StorageCreator {

    public static void createStudentStorage() throws IOException {
        File studentStorage = new File("Student.bin");
        if(!studentStorage.isFile()){
            if(studentStorage.createNewFile()){
                System.out.println("studentStorage created!");
            }else{
                System.out.println("Error creating studentStorage");
            }
        }else{
            System.out.println("studentStorage already exist");
        }
    }

    public static void createTeacherStorage() throws IOException {
        File teacherStorage = new File("Teacher.bin");
        if(!teacherStorage.isFile()){
            if(teacherStorage.createNewFile()){
                System.out.println("teacherStorage created!");
            }else{
                System.out.println("Error creating teacherStorage");
            }
        }else{
            System.out.println("teacherStorage already exist");
        }
    }

    public static void createAdminStorage() throws IOException {
        File adminStorage = new File("Admin.bin");
        if(!adminStorage.isFile()){
            if(adminStorage.createNewFile()){
                System.out.println("adminStorage created!");
            }else{
                System.out.println("Error creating adminStorage");
            }
        }else{
            System.out.println("adminStorage already exist");
        }
    }

    public static void createCourseStorage() throws IOException {
        File courseStorage = new File("Course.bin");
        if(!courseStorage.isFile()){
            if(courseStorage.createNewFile()){
                System.out.println("courseStorage created!");
            }else{
                System.out.println("Error creating courseStorage");
            }
        }else{
            System.out.println("courseStorage already exist");
        }
    }
}
