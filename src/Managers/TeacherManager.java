package Managers;

import Educational.Assignment;
import Educational.Course;
import Educational.Grade;
import Users.Student;
import Users.Teacher;

import java.util.List;
import java.util.Scanner;

import static Storage.DataControl.coursesList;

public class TeacherManager implements ClassroomManager<Teacher>{

    @Override
    public void checkPersonalInfo(Teacher teacher){
        System.out.println("ID: " + teacher.getUserId() +
                " || " + "Name: " + teacher.getUserName() +
                " || " + "Email: " + teacher.getUserEmail() +
                " || " + "Password: " + teacher.getUserPassword());
    }
    @Override
    public void modifyPersonalInfo(Teacher teacher, String newPassword){
        teacher.updateUser("","", newPassword);
    }
    @Override
    public void showCourses(Teacher teacher){
        List<Course> courseList = teacher.getListOfCourses();
        for(Course course : courseList){
            System.out.println("Course ID: " + course.getCourseId() +
            " || " + "Course Name: " + course.getCourseName() +
            " || " + "Number of Students: " + course.getNumberOfStudents());
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
                System.out.println("ID: " + student.getUserId() +
                        " || " + "Name: " + student.getUserName() +
                        " || " + "Email: " + student.getUserEmail());
                }
            }
    }
    @Override
    public void showAssignments(Course course, Teacher teacher){
        int number = 1;
        if(course.getAssignmentsList().isEmpty()){
            System.out.println("The course has no assignments yet");
        }
        for(Assignment assignment : course.getAssignmentsList()){
            if(!assignment.getTeacher().equals(teacher)){
                continue;
            }
            System.out.print("ID: " + number + " || " +
            "Assignment: " + assignment.getName() +
            " || " + "Graded: ");
            if(assignment.isGraded()){
                System.out.println("True");
            }else{
                System.out.println("False");
            }
            number += 1;
        }
    }
    public void createAssignment(Course course, String assignmentName){
        course.addAssignment(assignmentName);
        System.out.println("\nAssignment added successfully!");
    }

    public void viewGrades(Assignment assignment){
        List<Grade> gradeList = assignment.getListGrades();
        for(Grade grade : gradeList){
            Student student = grade.getStudent();
            System.out.print("ID: " + student.getUserId() +
            " || " + "Name: " + student.getUserName() +
            " || " + "Submitted: " + grade.isSubmitted() +
            " || " + "Grade: ");
            if(grade.getGrade() == -1){
                System.out.println("Not graded");
            }else{
                System.out.println(grade.getGrade());
            }
        }
    }
    public void gradeAssignment(Assignment assignment){
        if(assignment.isGraded()){
            System.out.println("\n|ERROR| - Assignment already graded! (Now you can only modify individual grades)");
            return;
        }
        double score;
        Scanner scanner = new Scanner(System.in);
        for(Grade grade : assignment.getListGrades()){
            Student student = grade.getStudent();
            System.out.println("\nStudent: " + student.getUserName() + " with ID: " + student.getUserId());
            System.out.println("Submitted: " + grade.isSubmitted());
            System.out.print("\nInput the grade: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                System.out.print("\nInput the grade (format: use , for the decimals): ");
                scanner.next();
            }
            score = scanner.nextDouble();
            while(score < 0){
                System.out.println("\n|ERROR| - Invalid grade");
                System.out.print("\nInput a valid grade: ");
                score = scanner.nextDouble();
            }
            grade.setGrade(score);
            System.out.println("\nStudent graded successfully!");
        }
        assignment.setGraded();
    }
    public void updateStudentGrade(Course course, Assignment assignment, int studentId){
        int numberOfUngraded = 0;
        if(!assignment.isGraded()){
            System.out.println("\n|ERROR| - Assignment needs to be graded for all students!(First grade all)");
            return;
        }
        System.out.println("\nGRADING ASSIGNMENT " + assignment.getName().toUpperCase() + "\n");

        if(!course.getStudentIdInCourse().contains(studentId)){
            System.out.println("Student with ID: " + studentId + " was not found in the course");
            return;
        }
        double score;
        Scanner scanner = new Scanner(System.in);
        for(Grade grade : assignment.getListGrades()){
            Student student = grade.getStudent();
            if(student.getUserId() == studentId) {
                System.out.println("\nStudent: " + student.getUserName() + " with ID: " + student.getUserId());
                System.out.println("Submitted: " + grade.getGrade());
                System.out.print("\nInput the new grade (-1 for not graded): ");
                score = scanner.nextDouble();
                grade.setGrade(score);
                System.out.println("\nStudent graded successfully!");
            }
            if(grade.getGrade() == -1){
                numberOfUngraded += 1;
            }
        }
        if(numberOfUngraded == assignment.getListGrades().size()){
            assignment.setUnGraded();
        }

    }

    public void removeAssignment(Course course, Assignment assignment){
        System.out.println("\n\nRemoving assignment ... ");
        course.removeAssignment(assignment);
        System.out.println("\nAssignment removed successfully!");
    }

}
