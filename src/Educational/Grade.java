package Educational;

import Users.Student;

import java.io.Serializable;

public class Grade implements Serializable {
    private Student student;
    private double grade;
    private boolean submitted;

    public Grade(Student student){
        this.student = student;
        this.grade = -1; // This meeans that the assignment for this student is NOT GRADED
        this.submitted = false;
    }
    public Student getStudent(){
        return this.student;
    }
    public double getGrade(){
        return this.grade;
    }
    public boolean isSubmitted(){
        return this.submitted;
    }

    public void setGrade(double grade){
        this.grade = grade;
    }
    public void setSubmitted(boolean submitted){
        this.submitted = submitted;
    }
}
