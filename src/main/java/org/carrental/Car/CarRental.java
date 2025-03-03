package org.carrental.Car;

import java.util.Scanner;

public class CarRental {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginService loginService = new LoginService();

        while (true) {
            System.out.println("===== Welcome to Rent_A_Car System =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Registration");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (loginService.adminLogin()) {
                        System.out.println("Redirecting to Admin Dashboard...");
                        // TODO: Call Admin Menu here
                        AdminService adminService = new AdminService();
                        adminService.adminMenu();
                    }
                    break;
                case 2:
                    int customerId = loginService.customerLogin();
                    if (customerId != -1) {
                        System.out.println("Redirecting to Customer Dashboard...");
                        // TODO: Call Customer Menu here
                        CustomerService customerService = new CustomerService(customerId);
                        customerService.customerMenu();
                    }
                    break;
                case 3:
                    loginService.registerCustomer();
                    break;
                case 4:
                    System.out.println("Thank you for using Rent_A_Car System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
}
