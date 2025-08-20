package com.example.util;

import com.example.dao.EmployeeDAO;
import com.example.dao.EmployeeDetailDAO;
import com.example.dao.ROLE;
import com.example.model.Employee;
import com.example.model.EmployeeDetail;

import java.util.Scanner;

public class EmployeeManager {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final EmployeeDetailDAO detailDAO = new EmployeeDetailDAO();

    public static void createEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Role: ");
            String role = scanner.nextLine().toUpperCase();

            System.out.print("Enter Reporting To (Manager ID): ");
            String reportingTo = scanner.nextLine();

            Employee employee = new Employee(id, name, ROLE.valueOf(role.toUpperCase()), reportingTo);
            employeeDAO.insert(employee);

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Experience Years: ");
            int experienceYears = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Department Name: ");
            String department = scanner.nextLine();

            System.out.print("Enter Project: ");
            String project = scanner.nextLine();

            EmployeeDetail detail = new EmployeeDetail(id, age, experienceYears, department, project);
            detailDAO.create(detail);

            System.out.println("✅ Employee created successfully.");
        } catch (Exception e) {
            System.err.println("❌ Error creating employee: " + e.getMessage());
        }
    }

    public static void readEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID: ");
            String id = scanner.nextLine();

            Employee employee = employeeDAO.getById(id);
            EmployeeDetail detail = detailDAO.getById(id);

            if (employee == null) {
                System.out.println("⚠️ Employee not found.");
                return;
            }

            System.out.println("\n-- Employee Info --");
            System.out.println("ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Role: " + employee.getRole());
            System.out.println("Reporting To: " + employee.getReportingTo());

            if (detail != null) {
                System.out.println("\n-- Employee Detail --");
                System.out.println("Age: " + detail.getAge());
                System.out.println("Experience: " + detail.getExperienceYears() + " years");
                System.out.println("Department: " + detail.getDepartmentName());
                System.out.println("Project: " + detail.getProject());
            } else {
                System.out.println("No additional details found.");
            }

        } catch (Exception e) {
            System.err.println("❌ Error reading employee: " + e.getMessage());
        }
    }

    public static void updateEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID to update: ");
            String id = scanner.nextLine();

            Employee employee = employeeDAO.getById(id);
            EmployeeDetail detail = detailDAO.getById(id);

            if (employee == null || detail == null) {
                System.out.println("⚠️ Employee not found.");
                return;
            }

            System.out.print("Enter New Name (" + employee.getName() + "): ");
            String name = scanner.nextLine();

            System.out.print("Enter New Role (" + employee.getRole() + "): ");
            String role = scanner.nextLine().toUpperCase();

            System.out.print("Enter New Reporting To (" + employee.getReportingTo() + "): ");
            String reportingTo = scanner.nextLine();

            System.out.print("Enter New Age (" + detail.getAge() + "): ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter New Experience (" + detail.getExperienceYears() + "): ");
            int experience = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter New Department (" + detail.getDepartmentName() + "): ");
            String department = scanner.nextLine();

            System.out.print("Enter New Project (" + detail.getProject() + "): ");
            String project = scanner.nextLine();

            employee.setName(name);
            employee.setRole(ROLE.valueOf(role.toUpperCase()));
            employee.setReportingTo(reportingTo);

            detail.setAge(age);
            detail.setExperienceYears(experience);
            detail.setDepartmentName(department);
            detail.setProject(project);

            employeeDAO.update(employee);
            detailDAO.update(detail);

            System.out.println("✅ Employee updated successfully.");
        } catch (Exception e) {
            System.err.println("❌ Error updating employee: " + e.getMessage());
        }
    }

    public static void deleteEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID to delete: ");
            String id = scanner.nextLine();

            employeeDAO.delete(id);
            detailDAO.delete(id);

            System.out.println("✅ Employee deleted successfully.");
        } catch (Exception e) {
            System.err.println("❌ Error deleting employee: " + e.getMessage());
        }
    }
}
