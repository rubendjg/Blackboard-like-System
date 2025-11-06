package UserInterface;

import Educational.Course;
import Managers.StudentManager;
import Storage.DataUtils;
import Users.Student;

import java.util.Scanner;

public class StudentUI {

    private Scanner scanner = new Scanner(System.in);

    protected void studentMenu(int userId){
        int choice;
        String newPassword;
        StudentManager studentManager = new StudentManager();
        Student student = DataUtils.getStudent(userId);

        do {
            System.out.println("\n\n" + student.getUserName().toUpperCase() + " PAGE");
            System.out.println("-".repeat(student.getUserName().length()) + " ----");
            System.out.println("1. Check Personal Info");
            System.out.println("2. Modify Password");
            System.out.println("3. View my Courses");
            System.out.println("4. View Individual Course");
            System.out.println("0. Main Menu");
            System.out.print("\nSelect an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                System.out.print("\nSelect an option: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    System.out.println("\nYOUR PERSONAL INFO");
                    System.out.println("---- -------- ---- ");
                    studentManager.checkPersonalInfo(student);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\nMODIFYING YOUR PASSWORD");
                    System.out.println("--------- ---- --------");
                    studentManager.checkPersonalInfo(student);
                    System.out.print("\nInput your new password: ");
                    newPassword = scanner.nextLine();
                    studentManager.modifyPersonalInfo(student, newPassword);
                    break;
                case 3:
                    System.out.println("\nYOUR COURSES");
                    System.out.println("---- ------- ");
                    studentManager.showCourses(student);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("\nYOUR COURSES");
                    System.out.println("---- ------- ");
                    studentManager.showCourses(student);
                    System.out.print("\nSelect a Course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\nSelect a Course ID: ");
                        scanner.next();
                    }
                    choice = scanner.nextInt();
                    studentCourseMenu(student, choice);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n|ERROR| - Invalid input");
            }
        } while(choice != 0);
    }

    private void studentCourseMenu(Student student, int courseId){
        int choice, assignmentId;
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        Course course = student.getCourse(courseId);
        if(course == null){
            System.out.println("\n\n|ERROR| - Invalid course ID");
            return;
        }
        do{
            System.out.println("\n\n" + course.getCourseName().toUpperCase());
            System.out.println("-".repeat(course.getCourseName().length()));
            System.out.println("1. View Roster of the Course");
            System.out.println("2. View All Assignments in the Course");
            System.out.println("3. Manage a Individual Assignments in the Course");
            System.out.println("0. Return to your Main Page");
            System.out.print("\nSelect an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    System.out.println("\n\n" + course.getCourseName().toUpperCase()+"'S" + " ROSTER" );
                    System.out.println("-".repeat(course.getCourseName().length()) + "-- ------");
                    studentManager.showRoster(course);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\n\n" + course.getCourseName().toUpperCase() + " ASSIGNMENTS" );
                    System.out.println("-".repeat(course.getCourseName().length()) + " -----------");
                    studentManager.showAssignments(course, student);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\n\n" + course.getCourseName().toUpperCase() + " ASSIGNMENTS" );
                    System.out.println("-".repeat(course.getCourseName().length()) + " -----------");
                    studentManager.showAssignments(course, student);
                    if(course.getAssignmentsList().isEmpty()){
                        break;
                    }
                    System.out.print("\nSelect an ID to submit or unsubmit your assignment (0 for none): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\nSelect an ID to submit or unsubmit your assignment (0 for none): ");
                        scanner.next();
                    }
                    assignmentId = scanner.nextInt();
                    studentManager.submissionManagement(course, student, assignmentId);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n|ERROR| - Invalid input");
            }
        }while(choice != 0);
    }
}
