package com.example;

import java.util.Scanner;
import com.example.util.EmployeeManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> EmployeeManager.createEmployee(scanner);
                case "2" -> EmployeeManager.readEmployee(scanner);
                case "3" -> EmployeeManager.updateEmployee(scanner);
                case "4" -> EmployeeManager.deleteEmployee(scanner);
                case "5" -> {
                    System.out.println("👋 Exiting... Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n====== Employee Management Menu ======");
        System.out.println("1. Create Employee and Details");
        System.out.println("2. Read Employee and Details");
        System.out.println("3. Update Employee and Details");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
        System.out.print("Select option: ");
    }
}
