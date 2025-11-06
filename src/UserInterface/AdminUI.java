package UserInterface;

import Managers.CourseAdminManager;
import Managers.UserAdminManager;
import Storage.DataControl;
import Users.Administrator;
import Users.Teacher;

import java.util.Scanner;

public class AdminUI {

    private Scanner scanner = new Scanner(System.in);
    private CourseAdminManager courseManager = new CourseAdminManager();
    private UserAdminManager userManager = new UserAdminManager();

    protected void superAdminMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice, adminId;

        do{
            System.out.println("\n\nSUPER ADMIN");
            System.out.println("----- -----");
            System.out.println("1. View All Admins");
            System.out.println("2. Create Admin");
            System.out.println("3. Delete Admin");
            System.out.println("4. Modify Admin");
            System.out.println("0. Main Menu");
            System.out.print("\nSelect an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("|ERROR| - Invalid input, enter a number");
                System.out.print("\nSelect an option: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\nLIST OF ALL ADMINS");
                    System.out.println("---- -- --- --------");
                    userManager.showUsers("admin");
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\nADMIN CREATION");
                    System.out.println("------- --------");
                    System.out.print("\n Admin name: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("\n Admin email: ");
                    String teacherEmail = scanner.nextLine();
                    System.out.print("\n Admin password: ");
                    String teacherPassword = scanner.nextLine();
                    userManager.createUser(teacherName, teacherEmail, teacherPassword, "admin");
                    break;
                case 3:
                    System.out.println("\nADMIN DELETION");
                    System.out.println("------- --------");
                    userManager.showUsers("admin");
                    System.out.print("\n Admin ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n Admin ID: ");
                        scanner.next();
                    }
                    adminId = scanner.nextInt();
                    userManager.deleteUser(adminId, "admin");
                    break;
                case 4:
                    System.out.println("\nADMIN MODIFICATION");
                    System.out.println("------ ---- ------------");
                    userManager.showUsers("admin");
                    System.out.print("\n Admin ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n Admin ID: ");
                        scanner.next();
                    }
                    adminId = scanner.nextInt();
                    System.out.println(" |IMPORTANT| - If you don't want to modify a field just press enter");
                    System.out.print("\n New admin name: ");
                    scanner.nextLine();
                    String newName = scanner.nextLine();
                    System.out.print("\n New admin email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("\n New admin password: ");
                    String newPassword = scanner.nextLine();
                    userManager.changeUser(adminId, newName, newEmail, newPassword, "admin");
                    break;
                case 0:
                    System.out.println("\nReturning to Main Page ...");
                    break;
                default:
                    System.out.println("|ERROR| - Invalid input");
            }
        }while(choice != 0);
    }

    protected void adminMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n\nADMIN");
            System.out.println("-----");
            System.out.println("1. Courses");
            System.out.println("2. Teachers");
            System.out.println("3. Students");
            System.out.println("0. Main Menu");
            System.out.print("\nSelect an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                System.out.print("\nSelect an option: ");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> courseManagerMenu();
                case 2 -> teacherManagerMenu();
                case 3 -> studentManagerMenu();
                case 0 -> System.out.println("\nGoing back to the Home Page ...");
                default -> System.out.println("\n|ERROR| - Invalid input");
            }
        }while(choice != 0);
    }

    private void courseManagerMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice, courseId;

        do {
            System.out.println("\n\nCOURSES");
            System.out.println("-------");
            System.out.println("1. View All Courses");
            System.out.println("2. Create Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Modify Course Name");
            System.out.println("5. Course Users Management");
            System.out.println("0. Admin Page");
            System.out.print("\nSelect an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                System.out.print("\nSelect an option: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\nLIST OF ALL COURSES");
                    System.out.println("---- -- --- -------");
                    courseManager.showCourses();
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\nCOURSE CREATION");
                    System.out.println("------ --------");
                    System.out.print("\n Course name: ");
                    String courseName = scanner.nextLine();
                    userManager.showUsers("teacher");
                    System.out.print("\n ID of the professor teaching the course: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n ID of the professor teaching the course: ");
                        scanner.next();
                    }
                    int teacherId = scanner.nextInt();
                    courseManager.createCourse(courseName, teacherId);
                    break;
                case 3:
                    System.out.println("\nCOURSE DELETION");
                    System.out.println("------ --------");
                    courseManager.showCourses();
                    System.out.print("\n Course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n Course ID: ");
                        scanner.next();
                    }
                    courseId = scanner.nextInt();
                    courseManager.deleteCourse(courseId);
                    break;
                case 4:
                    System.out.println("\nCOURSE NAME MODIFICATION");
                    System.out.println("------ ---- ------------");
                    courseManager.showCourses();
                    System.out.print("\n Course ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n Course ID: ");
                        scanner.next();
                    }
                    courseId = scanner.nextInt();
                    System.out.print("\n New course name: ");
                    scanner.nextLine();
                    String newCourseName = scanner.nextLine();
                    courseManager.changeCourseName(courseId, newCourseName);
                    break;
                case 5:
                    courseManager.showCourses();
                    courseUsersManagement();
                    break;
                case 0:
                    System.out.println("\nReturning to Admin Page ...");
                    break;
                default:
                    System.out.println("\n|ERROR| - Invalid input");
            }
        }while(choice != 0);
    }

    private void teacherManagerMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice, teacherId;

        do{
            System.out.println("\nTEACHERS");
            System.out.println("--------");
            System.out.println("1. View All Teachers");
            System.out.println("2. Create Teacher");
            System.out.println("3. Delete Teacher");
            System.out.println("4. Modify Teacher");
            System.out.println("0. Main Menu");
            System.out.print("\nSelect an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\nLIST OF ALL TEACHERS");
                    System.out.println("---- -- --- --------");
                    userManager.showUsers("teacher");
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\nTEACHER CREATION");
                    System.out.println("------- --------");
                    System.out.print("\n Teacher name: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("\n Teacher email: ");
                    String teacherEmail = scanner.nextLine();
                    System.out.print("\n Teacher password: ");
                    String teacherPassword = scanner.nextLine();
                    userManager.createUser(teacherName, teacherEmail, teacherPassword, "teacher");
                    break;
                case 3:
                    System.out.println("\nTEACHER DELETION");
                    System.out.println("------- --------");
                    userManager.showUsers("teacher");
                    System.out.print("\n Teacher ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n Teacher ID: ");
                        scanner.next();
                    }
                    teacherId = scanner.nextInt();
                    userManager.deleteUser(teacherId, "teacher");
                    break;
                case 4:
                    System.out.println("\nTEACHER MODIFICATION");
                    System.out.println("------ ------------");
                    userManager.showUsers("teacher");
                    System.out.print("\n Teacher ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n Teacher ID: ");
                        scanner.next();
                    }
                    teacherId = scanner.nextInt();
                    System.out.println(" |IMPORTANT| - If you don't want to modify a field just press enter");
                    System.out.print("\n New teacher name: ");
                    scanner.nextLine();
                    String newName = scanner.nextLine();
                    System.out.print("\n New teacher email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("\n New teacher password: ");
                    String newPassword = scanner.nextLine();
                    userManager.changeUser(teacherId, newName, newEmail, newPassword, "teacher");
                    break;
                case 0:
                    System.out.println("\nReturning to Home Page ...");
                    break;
                default:
                    System.out.println("\n|ERROR| - Invalid input");
            }
        }while(choice != 0);
    }

    private void studentManagerMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice, studentId;

        do{
            System.out.println("\nSTUDENT");
            System.out.println("--------");
            System.out.println("1. View All Students");
            System.out.println("2. Create Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Modify Student");
            System.out.println("0. Main Menu");
            System.out.print("\nSelect an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                System.out.print("\nSelect an option: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\nLIST OF ALL STUDENTS");
                    System.out.println("---- -- --- --------");
                    userManager.showUsers("student");
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\nSTUDENT CREATION");
                    System.out.println("------- --------");
                    System.out.print("\n Student name: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("\n Student email: ");
                    String teacherEmail = scanner.nextLine();
                    System.out.print("\n Student password: ");
                    String teacherPassword = scanner.nextLine();
                    userManager.createUser(teacherName, teacherEmail, teacherPassword, "student");
                    break;
                case 3:
                    System.out.println("\nSTUDENT DELETION");
                    System.out.println("------- --------");
                    userManager.showUsers("student");
                    System.out.print("\n Student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n Student ID: ");
                        scanner.next();
                    }
                    studentId = scanner.nextInt();
                    userManager.deleteUser(studentId, "student");
                    break;
                case 4:
                    System.out.println("\nSTUDENT MODIFICATION");
                    System.out.println("------ ------------");
                    userManager.showUsers("student");
                    System.out.print("\n Student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\n Student ID: ");
                        scanner.next();
                    }
                    studentId = scanner.nextInt();
                    System.out.println(" |IMPORTANT| - If you don't want to modify a field just press enter");
                    System.out.print("\n New student name: ");
                    scanner.nextLine();
                    String newName = scanner.nextLine();
                    System.out.print("\n New student email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("\n New student password: ");
                    String newPassword = scanner.nextLine();
                    userManager.changeUser(studentId, newName, newEmail, newPassword, "student");
                    break;
                case 0:
                    System.out.println("\nReturning to Home Page ...");
                    break;
                default:
                    System.out.println("\n|ERROR| - Invalid input");
            }
        }while(choice != 0);
    }

    private void courseUsersManagement(){
        Scanner scanner = new Scanner(System.in);
        CourseAdminManager courseAdminManager = new CourseAdminManager();
        String courseName, stringIds;
        int choice, courseId;

        do {
            System.out.print("\nInput a valid course ID (-1 to exit): ");
            while (!scanner.hasNextInt()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                scanner.next();
            }
            courseId = scanner.nextInt();
            if(courseId == -1){
                return;
            }
        }while(!courseAdminManager.checkValidCourseId(courseId));

        courseName = courseAdminManager.getCourseName(courseId);

        do {
            System.out.println("\n\n" + courseName.toUpperCase() + " USERS MANAGEMENT");
            System.out.println("-".repeat(courseName.length()) + " ----- ----------");
            System.out.println("1. Roster");
            System.out.println("2. Add Students");
            System.out.println("3. Change Professor");
            System.out.println("4. Remove Students");
            System.out.println("5. Change Course");
            System.out.println("0. Courses Page");
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
                    System.out.println("\nROSTER");
                    System.out.println("------");
                    courseManager.showRoster(courseId);
                    System.out.print("\nPress enter to continue: ");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\nADD STUDENTS");
                    System.out.println("--- --------");
                    userManager.showUsers("student");
                    System.out.print("\nInput the ID of each student separated by comas (Ex: 1,2,3): ");
                    stringIds = scanner.nextLine();
                    System.out.println("\nAdding Students ...");
                    courseManager.addStudents(courseId, stringIds);
                    break;
                case 3:
                    System.out.println("\nCHANGE PROFESSOR");
                    System.out.println("------ ---------");
                    userManager.showUsers("teacher");
                    System.out.print("\nInput the ID of the professor to teach this course: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\nInput the ID of the professor to teach this course: ");
                        scanner.next();
                    }
                    int teacherId = scanner.nextInt();
                    System.out.println("\nChanging the Professor ...");
                    courseManager.changeTeacher(courseId, teacherId);
                    break;
                case 4:
                    System.out.println("\nREMOVE STUDENTS");
                    System.out.println("------ --------");
                    courseManager.showRoster(courseId);
                    System.out.print("Input the ID of each student separated by comas (Ex: 1,2,3): ");
                    stringIds = scanner.nextLine();
                    System.out.println("\nRemoving Students ...");
                    courseManager.removeStudents(courseId, stringIds);
                    break;
                case 5:
                    System.out.println("\nCHANGE COURSE TO MODIFY");
                    System.out.println("------ ------ -- ------");
                    courseManager.showCourses();
                    int previous_id = courseId;
                    do {
                        System.out.print("\nInput a valid course ID (-1 to exit): ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("\n|ERROR| - Invalid input, enter a number");
                            System.out.print("\nInput a valid course ID (-1 to exit): ");
                            scanner.next();
                        }
                        courseId = scanner.nextInt();

                        if(courseId == -1){
                            courseId = previous_id;
                            break;
                        }
                    }while(!courseManager.checkValidCourseId(courseId));
                    courseName = courseManager.getCourseName(courseId);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n\n|ERROR| - Invalid input");
            }

        }while(choice != 0);
    }
}
