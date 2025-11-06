package Managers;

import Educational.Assignment;
import Educational.Course;
import Educational.Grade;
import Users.Student;
import Users.Teacher;

import java.util.List;
import java.util.Scanner;

public class StudentManager implements ClassroomManager<Student>{
    @Override
    public void checkPersonalInfo(Student student){
        System.out.println("ID: " + student.getUserId() +
                " || " + "Name: " + student.getUserName() +
                " || " + "Email: " + student.getUserEmail() +
                " || " + "Password: " + student.getUserPassword());
    }
    @Override
    public void modifyPersonalInfo(Student student, String newPassword){
        student.updateUser("","", newPassword);
    }
    @Override
    public void showCourses(Student student){
        List<Course> courseList = student.getListOfCourses();
        for(Course course : courseList){
            System.out.println("Course ID: " + course.getCourseId() +
                    " || " + "Course Name: " + course.getCourseName() +
                    " || " + "Professor Name: " + course.getTeacher().getUserName());
        }
    }
    @Override
    public void showRoster(Course course){
        Teacher teacher = course.getTeacher();
        System.out.println("ID: " + teacher.getUserId() +
                " || " + "Professor: " + teacher.getUserName() +
                " || " + "Email: " + teacher.getUserEmail());

        if(course.getStudentList().isEmpty()){
            System.out.println("This course has no students assigned");
        }else {
            System.out.println("\nSTUDENTS");
            System.out.println("--------");
            for (Student student : course.getStudentList()) {
                System.out.println("Name: " + student.getUserName() +
                        " || " + "Email: " + student.getUserEmail());
            }
        }
    }
    @Override
    public void showAssignments(Course course, Student student){
        int number = 1;
        Grade grade;
        if(course.getAssignmentsList().isEmpty()){
            System.out.println("The course has no assignments yet");
        }
        for(Assignment assignment : course.getAssignmentsList()){
            System.out.print("ID: " + number  + " || " +
                    "Assignment: " + assignment.getName() +
                    " || " + "Submitted: ");
            grade = assignment.getGradeInstance(student.getUserId());
            if(grade.isSubmitted()){
                System.out.print("True");
            }else{
                System.out.print("False");
            }
            System.out.print(" || " + "Grade: ");
            if(grade.getGrade() == -1){
                System.out.println("Not graded");
            }else{
                System.out.println(grade.getGrade());
            }
            number += 1;
        }
    }

    public void submissionManagement(Course course, Student student, int assignmentId){
        if((assignmentId < 1) || (assignmentId > course.getAssignmentsList().size())) {
            System.out.println("|ERROR| - Invalid Input");
        }
        Assignment assignment = course.getAssignment(assignmentId - 1);
        Grade grade = assignment.getGradeInstance(student.getUserId());

        Scanner scanner = new Scanner(System.in);
        String choice;

        if(assignment == null){
            System.out.println("|ERROR| - Invalid Assignment ID");
        }
        if(grade.isSubmitted()){
            System.out.println("\n  Assignment already submitted!");
            System.out.print("\n  Do you want to unsubmit the assignment?(y/n): ");
            choice = scanner.nextLine();
            if(choice.equals("y")){
                System.out.println("\n  Unsubmitting assignment ...");
                assignment.unSubmitAssignment(student.getUserId());
                System.out.println("\n  Assignment unsubmitted successfully");
            }else{
                System.out.println("\n Cancelling the action ...");
            }
        }else{
            System.out.println("\n  Assignment not submitted yet");
            System.out.print("\n  Do you want to submit the assignment?(y/n): ");
            choice = scanner.nextLine();
            if(choice.equals("y")){
                System.out.println("\n  Submitting assignment ...");
                assignment.submitAssignment(student.getUserId());
                System.out.println("\n  Assignment submitted successfully");
            }else{
                System.out.println("\n Cancelling the action ...");
            }
        }
    }
}
