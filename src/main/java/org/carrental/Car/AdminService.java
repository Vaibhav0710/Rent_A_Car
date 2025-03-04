package org.carrental.Car;

import org.carrental.DAO.CarDAO;
import org.carrental.DAO.BookingDAO;
import org.carrental.DAO.CustomerDAO;
import java.util.Scanner;

public class AdminService {
    private final CarDAO carDAO;
    private final BookingDAO bookingDAO;
    private final CustomerDAO customerDAO;

    public AdminService() {
        this.carDAO = new CarDAO();
        this.bookingDAO = new BookingDAO();
        this.customerDAO = new CustomerDAO();
    }

    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Admin Dashboard =====");
            System.out.println("1. Add a Car");
            System.out.println("2. Remove a Car");
            System.out.println("3. View All Cars");
            System.out.println("4. View All Bookings");
            System.out.println("5. Cancel a Booking");
            System.out.println("6. View All Customers");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    removeCar();
                    break;
                case 3:
                    viewAllCars();
                    break;
                case 4:
                    viewAllBookings();
                    break;
                case 5:
                    cancelBooking();
                    break; // ✅ Added break statement to avoid fall-through
                case 6:
                    viewAllCustomers();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }

    private void addCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Car Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Car Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Manufacturing Year: "); // ✅ Added year input
        int year = scanner.nextInt();
        System.out.print("Enter Rent per Day: ");
        double rentPerDay = scanner.nextDouble();

        boolean success = carDAO.addCar(model, brand, year, rentPerDay);
        if (success) {
            System.out.println("✅ Car added successfully!");
        } else {
            System.out.println("❌ Failed to add car!");
        }
    }

    private void removeCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Car ID to Remove: ");
        int carId = scanner.nextInt();

        boolean success = carDAO.removeCar(carId);
        if (success) {
            System.out.println("✅ Car removed successfully!");
        } else {
            System.out.println("❌ Failed to remove car!");
        }
    }

    private void cancelBooking() { // ✅ Moved cancellation logic to a separate method
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Booking ID to cancel: ");
        int bookingId = scanner.nextInt();

        boolean isCancelled = bookingDAO.cancelBookingByAdmin(bookingId);
        if (isCancelled) {
            System.out.println("✅ Booking canceled successfully!");
        } else {
            System.out.println("❌ Booking not found.");
        }
    }

    private void viewAllCars() {
        carDAO.viewAllCars();
    }

    private void viewAllBookings() {
        bookingDAO.viewAllBookings();
    }

    private void viewAllCustomers() {
        customerDAO.viewAllCustomers();
    }
}
