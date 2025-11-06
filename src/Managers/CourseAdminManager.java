package Managers;

import Educational.Course;
import Users.Student;
import Users.Teacher;

import java.util.List;

import static Storage.DataControl.*;

public class CourseAdminManager {
    public void createCourse(String courseName, int teacherId) {
        UserAdminManager userManager = new UserAdminManager();
        int index = userManager.getTeacherIndex(teacherId);
        if(index >= 0) {
            Teacher teacher = teachersList.get(index);
            Course newCourse = new Course(courseName, teacher);
            coursesList.add(newCourse);
            coursesId.add(newCourse.getCourseId());
        }else{
            System.out.println("\n |ERROR| - Invalid professor id");
        }
    }

    public boolean checkValidCourseId(int courseId){
        if(coursesId.isEmpty()){
            return false;
        }
        return coursesId.contains(courseId);
    }

    public String getCourseName(int courseId){
        for (Course course : coursesList) {
            if (course.getCourseId() == courseId) {
                return course.getCourseName();
            }
        }
        return "none";
    }

    private int findCourse(int courseId) {
        int index = 0;
        if(!checkValidCourseId(courseId)){
            return -1;
        }
        for (Course course : coursesList) {
            if (course.getCourseId() == courseId) {
                return index;
            }
            index += 1;
        }
        System.out.println("Course not found");
        return -1;
    }

    public void deleteCourse(int id){
        int index = findCourse(id);
        Course findedCourse = coursesList.get(index);
        if(index >= 0){
            for(Student student: findedCourse.getStudentList()){
                student.removeCourse(findedCourse);
            }
            findedCourse.getTeacher().removeCourse(findedCourse);
            coursesId.remove(Integer.valueOf(coursesList.get(index).getCourseId()));
            coursesList.remove(index);
        }else{
            System.out.println("|ERROR| - Course do not exist");
        }
    }

    public void showCourses(){
        for(Course course : coursesList){
            System.out.println("Course name: " + course.getCourseName() +
                    " || " + "ID: " + course.getCourseId() +
                    " || " + "Professor: " + course.getTeacher().getUserName() +
                    " || " + "Number of Students: " + course.getNumberOfStudents());
        }
    }

    public void changeCourseName(int id, String newName){
        int index = findCourse(id);
        if(id >= 0) {
            Course toChange = coursesList.get(index);
            toChange.changeCourseName(newName);
        }
    }

    public void addStudents(int courseId, String stringIds){
        UserAdminManager userManager = new UserAdminManager();
        int index = findCourse(courseId);
        List<Student> listOfStudents = userManager.findStudents(stringIds);

        if(index >= 0){
            Course findedCourse = coursesList.get(index);
            List<Integer> listOfIds = findedCourse.getStudentIdInCourse();

            for(Student student : listOfStudents){
                if(listOfIds.contains(student.getUserId())){
                    System.out.println("\nUser with id: " + student.getUserId() + " is already in the course");
                }else{
                    findedCourse.addStudent(student);
                }
            }
        }
    }

    public void removeStudents(int courseId, String stringIds){
        UserAdminManager userManager = new UserAdminManager();
        int index = findCourse(courseId);
        List<Student> listOfStudents = userManager.findStudents(stringIds);

        if(index >= 0){
            Course findedCourse = coursesList.get(index);
            if(findedCourse.getStudentList().isEmpty() || findedCourse.getStudentList() == null){
                System.out.println("\n|ERROR| - The course has no students");
                return;
            }
            List<Integer> listOfIds = findedCourse.getStudentIdInCourse();
            for(Student student : listOfStudents){
                if(listOfIds.contains(student.getUserId())){
                    findedCourse.removeStudent(student);
                }else{
                    System.out.println("\nUser with id: " + student.getUserId() + " is not in this course");
                }
            }
        }

        if(index >= 0){
            Course findedCourse = coursesList.get(index);
            for(Student student : listOfStudents) {
                findedCourse.removeStudent(student);
            }
        }
    }

    public void showRoster(int courseId){
        int index = findCourse(courseId);
        if(index >= 0){
            Course findedCourse = coursesList.get(index);
            if(findedCourse.getTeacher() == null){
                System.out.println("This course has no professor assigned");
            }else {
                Teacher teacher = findedCourse.getTeacher();
                System.out.println("ID: " + teacher.getUserId() +
                        " || " + "Professor: " + teacher.getUserName() +
                        " || " + "Email: " + teacher.getUserEmail() +
                        " || " + "Password: " + teacher.getUserPassword());
            }
            if(findedCourse.getStudentList().isEmpty()){
                System.out.println("This course has no students assigned");
            }else {
                System.out.println("\nSTUDENTS");
                System.out.println("--------");
                for (Student student : findedCourse.getStudentList()) {
                    System.out.println("ID: " + student.getUserId() +
                            " || " + "Name: " + student.getUserName() +
                            " || " + "Email: " + student.getUserEmail() +
                            " || " + "Password: " + student.getUserPassword());
                }
            }
        }
    }

    public void changeTeacher(int courseId, int teacherId){
        UserAdminManager userManager = new UserAdminManager();
        int courseIndex = findCourse(courseId);
        int teacherIndex = userManager.getTeacherIndex(teacherId);

        if(courseIndex >= 0 && teacherIndex >= 0){
            Course findedCourse = coursesList.get(courseIndex);
            Teacher teacher = teachersList.get(teacherIndex);

            findedCourse.assignTeacher(teacher);
        }
    }
}