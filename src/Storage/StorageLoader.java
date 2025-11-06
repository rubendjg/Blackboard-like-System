package Storage;

import java.io.*;
import Educational.Course;
import Users.*;
import java.util.List;

public class StorageLoader {

    public static void loadStudents() throws IOException, ClassNotFoundException {
        File file = new File("Student.bin");
        if (!file.exists() || file.length() == 0) {
            System.out.println("File is empty or does not exist");
        } else {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("Student.bin"));
            DataControl.studentsList = (List<Student>) reader.readObject();
        }
    }

    public static void loadTeachers() throws IOException, ClassNotFoundException {
        File file = new File("Teacher.bin");
        if (!file.exists() || file.length() == 0) {
            System.out.println("File is empty or does not exist");
        } else {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("Teacher.bin"));
            DataControl.teachersList = (List<Teacher>) reader.readObject();
        }
    }

    public static void loadAdmins() throws IOException, ClassNotFoundException {
        File file = new File("Admin.bin");
        if (!file.exists() || file.length() == 0) {
            System.out.println("File is empty or does not exist");
            DataControl.adminsList.add(new Administrator("Super Admin", "superadmin@admin.com", "admin"));
        } else {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("Admin.bin"));
            DataControl.adminsList = (List<Administrator>) reader.readObject();
        }
    }

    public static void loadCourses() throws IOException, ClassNotFoundException {
        File file = new File("Course.bin");
        if (!file.exists() || file.length() == 0) {
            System.out.println("File is empty or does not exist");
        } else {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("Course.bin"));
            DataControl.coursesList = (List<Course>) reader.readObject();
        }
    }

    public static void initializeCoursesId(){
        if(DataControl.coursesList.isEmpty()){
            return;
        }
        for(Course course: DataControl.coursesList){
            DataControl.coursesId.add(course.getCourseId());
        }
    }
}
