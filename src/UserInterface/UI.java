package UserInterface;

import Managers.*;
import Storage.DataControl;
import java.io.IOException;
import java.util.Scanner;

public class UI {

    private Scanner scanner = new Scanner(System.in);
    private int userId, userPosition; // Position in their respective list
    private int userType; // If the user is admin, teacher or student

    private AdminUI adminUI = new AdminUI();
    private TeacherUI teacherUI = new TeacherUI();
    private StudentUI studentUI = new StudentUI();

    public void runInterface() throws IOException, ClassNotFoundException {
        int choice, auxChoice;
        DataControl.initializeStorage();
        do {
            System.out.println("\nWELCOME!");
            System.out.println("--------");
            System.out.println("\n1. Login");
            System.out.println("0. Exit and save the changes");
            System.out.print("\nSelect an option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("\n|ERROR| - Invalid input, enter a number");
                System.out.print("\nSelect an option: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println("\nLOGIN");
                    System.out.println("------");
                    System.out.print("\nInput your ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("\n|ERROR| - Invalid input, enter a number");
                        System.out.print("\nInput your ID: ");
                        scanner.next();
                    }
                    userId = scanner.nextInt();
                    System.out.print("\nInput your Password: ");
                    scanner.nextLine();
                    String password = scanner.nextLine();

                    int[] result = AuthenticationManager.validateUser(userId, password);
                    userType = result[0];
                    userPosition = result[1];
                    choice = userType;

                    switch (choice) {
                        case 0 -> adminUI.superAdminMenu();
                        case 1 -> adminUI.adminMenu();
                        case 2 -> teacherUI.teacherMenu(userId);
                        case 3 -> studentUI.studentMenu(userId);
                        default -> System.out.println("\n|ERROR| - Invalid User Combination");
                    }
                    choice = 1;
                    break;
                case 0:
                    System.out.println("\nSaving the data ... \n");
                    break;
                default:
                    System.out.println("|ERROR| - Invalid Option");
            }
        } while (choice != 0);
    }
}
