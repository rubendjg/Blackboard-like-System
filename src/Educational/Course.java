package Educational;

import Storage.IdControl;
import Users.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private static int courseCount;
    private int id;
    private String name;
    private List<Assignment> listOfAssignments = new ArrayList<>();

    private Teacher teacher;

    private List<Student> studentList = new ArrayList<>();
    private List<Integer> studentIdInCourse = new ArrayList<>();

    public Course(String name, Teacher teacher){
        this.name = name;
        this.id = courseCount;
        courseCount += 1;
        this.teacher = teacher;
        teacher.addCourse(this);
    }

    public static void initializeCourseCount(){
        courseCount = IdControl.getLastCourseId();
    }

    public int getCourseId(){
        return this.id;
    }

    public String getCourseName(){
        return this.name;
    }

    public List<Student> getStudentList(){
        return this.studentList;
    }

    public Teacher getTeacher(){
        return this.teacher;
    }

    public List<Integer> getStudentIdInCourse(){
        return this.studentIdInCourse;
    }

    public long getNumberOfStudents(){
        return this.studentIdInCourse.stream().count();
    }

    public List<Assignment> getAssignmentsList(){
        return this.listOfAssignments;
    }
    public Assignment getAssignment(int assignmentPosition){
        return this.listOfAssignments.get(assignmentPosition);
    }

    public void changeCourseName(String newName){
        this.name = newName;
    }

    public void assignTeacher(Teacher teacher){
        if(this.teacher == null){
            this.teacher = teacher;
            teacher.addCourse(this);
        }else{
            this.teacher.removeCourse(this);
            this.teacher = teacher;
            teacher.addCourse(this);
            for(Assignment assignment : listOfAssignments){
                assignment.setTeacher(teacher);
            }
        }
    }

    public void addStudent(Student student){
        student.addCourse(this);
        this.studentList.add(student);
        this.studentIdInCourse.add(student.getUserId());
        if(listOfAssignments == null){
            return;
        }
        for(Assignment assignment : this.listOfAssignments){
            assignment.addStudent(student);
        }
    }

    public void removeStudent(Student student){
        student.removeCourse(this);
        this.studentIdInCourse.remove(Integer.valueOf(student.getUserId())); // Remove by value
        this.studentList.remove(student);
    }

    public void addAssignment(String assignmentName){
        Assignment assignment = new Assignment(assignmentName, this.getTeacher(), this.getStudentList());
        this.listOfAssignments.add(assignment);
    }

    public void removeAssignment(Assignment assignment){
        this.listOfAssignments.remove(assignment);
    }


}
