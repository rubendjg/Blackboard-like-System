package Storage;

import java.io.*;
import Users.*;

public class StorageSaver {

    public static void saveStudents() throws IOException {
        File file = new File("Student.bin");
        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
        writer.writeObject(DataControl.studentsList);
        writer.close();

        System.out.println("Students saved: " + DataControl.studentsList.size());
    }

    public static void saveTeachers() throws IOException {
        File file = new File("Teacher.bin");
        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
        writer.writeObject(DataControl.teachersList);
        writer.close();

        System.out.println("Teachers saved: " + DataControl.teachersList.size());
    }

    public static void saveAdmins() throws IOException {
        File file = new File("Admin.bin");
        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
        writer.writeObject(DataControl.adminsList);
        writer.close();

        System.out.println("Admins saved: " + DataControl.adminsList.size());
    }

    public static void saveCourses() throws IOException {
        File file = new File("Course.bin");
        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
        writer.writeObject(DataControl.coursesList);
        writer.close();

        System.out.println("Courses saved: " + DataControl.coursesList.size());
    }
}
