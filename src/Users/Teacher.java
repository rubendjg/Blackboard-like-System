package Users;

import Educational.Course;
import Storage.IdControl;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User implements ClassroomFunctions {
    static private int teacherCount;
    private List<Course> courseList = new ArrayList<>();

    public Teacher(String name, String email, String password){
        super(name, email, password, teacherCount);
        teacherCount += 3;
    }

    public static void initializeTeacherCount(){
        teacherCount = IdControl.getLastTeacherId();
    }
    @Override
    public List<Course> getListOfCourses(){
        return this.courseList;
    }
    @Override
    public Course getCourse(int courseId){
        for(Course course : courseList){
            if(course.getCourseId() == courseId){
                return course;
            }
        }
        return null;
    }
    @Override
    public void addCourse(Course course){
        this.courseList.add(course);
    }
    @Override
    public void removeCourse(Course course){
        this.courseList.remove(course);
    }
    @Override
    public void clearListOfCourses(){
        this.courseList.clear();
    }


}
