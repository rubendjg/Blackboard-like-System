package Educational;

import Users.Student;
import Users.Teacher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Assignment implements Serializable {
    private boolean isGraded;
    private String name;
    private Teacher teacher;
    private List<Grade> grades;

    public Assignment(String name, Teacher teacher, List<Student> students){
        this.name = name;
        this.teacher = teacher;
        this.grades = initializeGrades(students);
        this.isGraded = false;
    }

    private List<Grade> initializeGrades(List<Student> students){
        List<Grade> gradeList = new ArrayList<>();

        for(Student student : students){
            gradeList.add(new Grade(student));
        }
        return gradeList;
    }
    public boolean isGraded(){
        return this.isGraded;
    }
    public String getName(){
        return this.name;
    }
    public List<Grade> getListGrades(){
        return this.grades;
    }
    public Teacher getTeacher(){
        return this.teacher;
    }
    public double getGrade(int studentId){
        for(Grade grade : this.grades){
            if(grade.getStudent().getUserId() == studentId){
                return grade.getGrade();
            }
        }
        return -1;
    }
    public Grade getGradeInstance(int studentId){
        for(Grade grade : this.grades){
            if(grade.getStudent().getUserId() == studentId){
                return grade;
            }
        }
        return null;
    }
    public void setGraded(){
        this.isGraded = true;
    }
    public void setUnGraded(){
        this.isGraded = false;
    }
    public void submitAssignment(int studentId){
        for(Grade grade : this.grades){
            if(grade.getStudent().getUserId() == studentId){
                if(!grade.isSubmitted()){
                    grade.setSubmitted(true);
                    System.out.println("Assignment submitted successfully");
                }else{
                    System.out.println("Assignment already submitted");
                }
                return;
            }
        }
        System.out.println("Could not find user with user ID: " + studentId + " in this assignment");
    }
    public void unSubmitAssignment(int studentId){
        for(Grade grade : this.grades){
            if(grade.getStudent().getUserId() == studentId){
                if(grade.isSubmitted()){
                    grade.setSubmitted(false);
                    System.out.println("Assignment unsubmitted successfully");
                }else{
                    System.out.println("Assignment not submitted yet");
                }
                return;
            }
        }
        System.out.println("Could not find user with user ID: " + studentId + " in this assignment");
    }

    public void setTeacher(Teacher newTeacher){
        this.teacher = newTeacher;
    }
    public void addStudent(Student student){
        this.grades.add(new Grade(student));
    }
}
