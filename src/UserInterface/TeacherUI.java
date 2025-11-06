package UserInterface;

import Educational.Assignment;
import Educational.Course;
import Managers.TeacherManager;
import Storage.DataUtils;
import Users.Teacher;

import java.util.Scanner;

public class TeacherUI {

    private Scanner scanner = new Scanner(System.in);

    protected void teacherMenu(int userId){
        int choice, courseId;
        String newPassword;
        TeacherManager teacherManager = new TeacherManager();
        Teacher teacher = DataUtils.getTeacher(userId);

        do {
            System.out.println("\n\n" + teacher.getUserName().toUpperCase() + " PAGE");
            System.out.println("-".repeat(teacher.getUserName().length()) + " ----");
            System.out.println("1. Check Personal Info");
            System.out.println("2. Modify Password");
            System.out.println("3. Manage Individual Course");
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
                    teacherManager.checkPersonalInfo(teacher);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\nMODIFYING YOUR PASSWORD");
                    System.out.println("--------- ---- --------");
                    teacherManager.checkPersonalInfo(teacher);
                    System.out.print("\nInput your new password: ");
                    newPassword = scanner.nextLine();
                    teacherManager.modifyPersonalInfo(teacher, newPassword);
                    break;
                case 3:
                    System.out.println("\nYOUR COURSES");
                    System.out.println("---- ------- ");
                    teacherManager.showCourses(teacher);
                    System.out.print("\nSelect a Course ID (0 for none): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\nSelect a Course ID (0 for none): ");
                        scanner.next();
                    }
                    courseId = scanner.nextInt();
                    if(courseId != 0){
                        teacherCourseMenu(teacher, courseId);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n|ERROR| - Invalid input");
            }
        } while(choice != 0);
    }

    private void teacherCourseMenu(Teacher teacher, int courseId){
        int choice, assignmentId;
        String assignmentName;
        TeacherManager teacherManager = new TeacherManager();
        Scanner scanner = new Scanner(System.in);
        Course course = teacher.getCourse(courseId);
        if(course == null){
            System.out.println("\n\n|ERROR| - Invalid course ID");
            return;
        }
        do{
            System.out.println("\n\n" + course.getCourseName().toUpperCase());
            System.out.println("-".repeat(course.getCourseName().length()));
            System.out.println("1. View Roster of the Course");
            System.out.println("2. View All Assignments in the Course");
            System.out.println("3. Create an Assignment");
            System.out.println("4. Manage Assignments");
            System.out.println("0. Return to the Courses View");
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
                    System.out.println("\n\n" + course.getCourseName().toUpperCase()+"'S" + " ROSTER" );
                    System.out.println("-".repeat(course.getCourseName().length()) + "-- ------");
                    teacherManager.showRoster(course);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\n\n" + course.getCourseName().toUpperCase() + " ASSIGNMENTS" );
                    System.out.println("-".repeat(course.getCourseName().length()) + " -----------");
                    teacherManager.showAssignments(course, teacher);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\n\nADDING ASSIGNMENT");
                    System.out.println("------ ----------");
                    System.out.print("\nWrite the new assignment's name: ");
                    assignmentName = scanner.nextLine();
                    teacherManager.createAssignment(course, assignmentName);
                    break;
                case 4:
                    System.out.println("\n\nASSIGNMENT MANAGEMENT");
                    System.out.println("---------- ----------");
                    teacherManager.showAssignments(course, teacher);
                    System.out.print("\nInput the assignment ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\nInput the assignment ID: ");
                        scanner.next();
                    }
                    assignmentId = scanner.nextInt();
                    teacherAssignmentMenu(course, course.getAssignment(assignmentId -1));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n|ERROR| - Invalid input");
            }
        }while(choice != 0);
    }

    private void teacherAssignmentMenu(Course course, Assignment assignment){
        int choice, studentId;
        String decision;
        TeacherManager teacherManager = new TeacherManager();
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println("\n\n" + assignment.getName().toUpperCase());
            System.out.println("-".repeat(assignment.getName().length()));
            System.out.println("1. View the Grades of the Assignment");
            System.out.println("2. Grade the Assignment");
            System.out.println("3. Modify the Grade of a Student");
            System.out.println("4. Remove Assignment");
            System.out.println("0. Return to your Main Page");
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
                    System.out.println("\n\nGRADES");
                    System.out.println("------");
                    teacherManager.viewGrades(assignment);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\n\nGRADING ASSIGNMENT");
                    System.out.println("------- ----------");
                    teacherManager.gradeAssignment(assignment);
                    break;
                case 3:
                    System.out.println("\n\nMODIFYING INDIVIDUAL GRADE");
                    System.out.println("\n\n--------- ---------- -----");
                    teacherManager.viewGrades(assignment);
                    System.out.print("\nInput the ID of the student to modify: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\nInput the ID of the student to modify: ");
                        scanner.next();
                    }
                    studentId = scanner.nextInt();
                    teacherManager.updateStudentGrade(course, assignment, studentId);
                    break;
                case 4:
                    System.out.println("\n\nREMOVING ASSIGNMENT");
                    System.out.println("-------- ----------");
                    System.out.print("\nAre you sure you want to delete it permanently? (y/n): ");
                    scanner.nextLine();
                    decision = scanner.nextLine();
                    if(decision.equals("y")){
                        teacherManager.removeAssignment(course, assignment);
                        choice = 0;
                    } else {
                        System.out.println("\n\nGoing back to the assignment page");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n|ERROR| - Invalid input");
            }
        }while(choice != 0);
    }
}
