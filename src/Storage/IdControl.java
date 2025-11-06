package Storage;

import Educational.Course;
import Users.*;

public class IdControl {

    public static int getLastAdminId(){
        if(DataControl.adminsList.isEmpty()){
            return 0; // Super Admin's index
        }
        Administrator lastAdmin = DataControl.adminsList.getLast();
        return lastAdmin.getUserId() + 3;
    }

    public static int getLastTeacherId(){
        if(DataControl.teachersList.isEmpty()){
            return 1;
        }
        Teacher lastTeacher = DataControl.teachersList.getLast();
        return lastTeacher.getUserId() + 3;
    }

    public static int getLastStudentId(){
        if(DataControl.studentsList.isEmpty()){
            return 2;
        }
        Student lastStudent = DataControl.studentsList.getLast();
        return lastStudent.getUserId() + 3;
    }

    public static int getLastCourseId(){
        if(DataControl.coursesList.isEmpty()){
            return 1;
        }
        Course lastCourse = DataControl.coursesList.getLast();
        return lastCourse.getCourseId();
    }
}
