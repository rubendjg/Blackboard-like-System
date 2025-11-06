package Storage;

import Users.*;

public class DataUtils {

    public static Teacher getTeacher(int teacherId){
        for(Teacher teacher : DataControl.teachersList){
            if(teacher.getUserId() == teacherId){
                return teacher;
            }
        }
        return null;
    }

    public static Student getStudent(int studentId){
        for(Student student : DataControl.studentsList){
            if(student.getUserId() == studentId){
                return student;
            }
        }
        return null;
    }
}
