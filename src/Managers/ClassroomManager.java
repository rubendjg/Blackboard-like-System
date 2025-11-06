package Managers;

import Educational.Course;
import Users.User;


public interface ClassroomManager<T extends User>{
    void checkPersonalInfo(T user);
    void modifyPersonalInfo(T user, String newPassword);
    void showCourses(T user);
    void showAssignments(Course course, T user);
    void showRoster(Course course);
}
