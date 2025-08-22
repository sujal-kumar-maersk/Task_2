package com.example.controller;

import com.example.service.EmployeeManagerService;

import java.util.Scanner;

public class EmployeeController {
    public static void start(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            EmployeeManagerService employeeManagerService = new EmployeeManagerService();

            switch (choice) {
                case "1" -> employeeManagerService.createEmployee();
                case "2" -> employeeManagerService.readEmployee();
                case "3" -> employeeManagerService.updateEmployee();
                case "4" -> employeeManagerService.deleteEmployee();
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
