package org.carrental.Car;

import org.carrental.DAO.AdminDAO;
import org.carrental.DAO.CustomerDAO;

import java.util.Scanner;

public class LoginService {
    private final AdminDAO adminDAO;
    private final CustomerDAO customerDAO;

    public LoginService() {
        this.adminDAO = new AdminDAO();
        this.customerDAO = new CustomerDAO();
    }

    public boolean adminLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        boolean isValid = adminDAO.validateAdmin(username, password);

        if (isValid) {
            System.out.println("✅ Admin Login Successful! Welcome, " + username);
            return true;
        } else {
            System.out.println("❌ Invalid Admin Credentials! Please try again.");
            return false;
        }
    }

    public void registerCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter your Password: ");
        String password = scanner.nextLine(); // ⚠️ Store securely in real projects!

        boolean isRegistered = customerDAO.registerCustomer(name, email, phone, password);

        if (isRegistered) {
            System.out.println("✅ Registration successful! You can now log in.");
        } else {
            System.out.println("❌ Registration failed! Email might already exist.");
        }
    }

    public int customerLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        int id = customerDAO.validateCustomer(email, password);
        return id;
    }


}
