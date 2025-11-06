package Storage;

import java.io.*;
import Educational.Course;
import Users.*;
import java.util.ArrayList;
import java.util.List;

public class DataControl {
    public static List<Course> coursesList = new ArrayList<>();
    public static List<Student> studentsList = new ArrayList<>();
    public static List<Teacher> teachersList = new ArrayList<>();
    public static List<Administrator> adminsList = new ArrayList<>();

    // Lists to check if an id exists
    public static List<Integer> coursesId = new ArrayList<>();

    public static void initializeStorage() throws IOException, ClassNotFoundException {
        // Create files if they don't exist
        StorageCreator.createStudentStorage();
        StorageCreator.createTeacherStorage();
        StorageCreator.createAdminStorage();
        StorageCreator.createCourseStorage();

        // Load files
        StorageLoader.loadStudents();
        StorageLoader.loadTeachers();
        StorageLoader.loadAdmins();
        StorageLoader.loadCourses();

        // Initialize global IDs
        StorageLoader.initializeCoursesId();
        Student.initializeStudentCount();
        Teacher.initializeTeacherCount();
        Administrator.initializeAdminCount();
        Course.initializeCourseCount();

        for (Teacher teacher : teachersList) {
            teacher.clearListOfCourses();
        }
        for (Student student : studentsList) {
            student.clearListOfCourses();
        }

        // Make that all objects have the same references to each other
        for (Course course : coursesList) {

            Teacher realTeacher = DataUtils.getTeacher(course.getTeacher().getUserId());
            if (realTeacher != null) {
                course.assignTeacher(realTeacher);

                if (!realTeacher.getListOfCourses().contains(course)) {
                    realTeacher.addCourse(course);
                }
            }

            List<Student> realStudents = new ArrayList<>();
            for (Student s : course.getStudentList()) {
                Student realStudent = DataUtils.getStudent(s.getUserId());
                if (realStudent != null) {
                    realStudents.add(realStudent);

                    if (!realStudent.getListOfCourses().contains(course)) {
                        realStudent.addCourse(course);
                    }
                }
            }

            course.getStudentList().clear();
            course.getStudentList().addAll(realStudents);
        }
    }

    public static void saveData() throws IOException {
        StorageSaver.saveCourses();
        StorageSaver.saveStudents();
        StorageSaver.saveAdmins();
        StorageSaver.saveTeachers();
    }

    public static void registerAutoSaveOnExit() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Saving before exiting...");
                saveData();
                System.out.println("Data was correctly saved!");
            } catch (IOException e) {
                System.err.println("|ERROR| - Data was not saved correctly: " + e.getMessage());
            }
        }));
    }
}

