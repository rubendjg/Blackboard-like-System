package Users;

import Educational.Course;

import java.util.List;

public interface ClassroomFunctions {
    public List<Course> getListOfCourses();
    public Course getCourse(int courseId);
    public void addCourse(Course course);
    public void removeCourse(Course course);
    public void clearListOfCourses();
}
