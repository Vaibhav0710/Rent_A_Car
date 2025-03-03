package org.carrental.Car;

import org.carrental.DAO.CarDAO;
import org.carrental.DAO.BookingDAO;

import java.util.Scanner;
import java.sql.Date;

public class CustomerService {
    private final CarDAO carDAO;
    private final BookingDAO bookingDAO;
    private final int customerId; // Store logged-in customer ID

    public CustomerService(int customerId) {
        this.carDAO = new CarDAO();
        this.bookingDAO = new BookingDAO();
        this.customerId = customerId;
    }

    public void customerMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Customer Dashboard =====");
            System.out.println("1. View Available Cars");
            System.out.println("2. Book a Car");
            System.out.println("3. View My Bookings");
            System.out.println("4. Cancel Booking ❌");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableCars();
                    break;
                case 2:
                    bookCar();
                    break;
                case 3:
                    viewMyBookings();
                    break;
                case 4:
                    System.out.print("Enter Booking ID to cancel: ");
                    int bookingId = scanner.nextInt();

                    boolean isCancelled = bookingDAO.cancelBooking(bookingId, customerId);

                    if (isCancelled) {
                        System.out.println("✅ Booking canceled successfully!");
                    } else {
                        System.out.println("❌ Booking not found or already canceled.");
                    }
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }

    private void viewAvailableCars() {
        carDAO.viewAllCars();
    }

    private void bookCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Car ID to book: ");
        int carId = scanner.nextInt();
        System.out.print("Enter Rental Date (YYYY-MM-DD): ");
        String rentalDate = scanner.next();
        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        String returnDate = scanner.next();

        boolean success = bookingDAO.bookCar(customerId, carId, Date.valueOf(rentalDate), Date.valueOf(returnDate));
        if (success) {
            System.out.println("✅ Car booked successfully!");
        } else {
            System.out.println("❌ Booking failed! Car may not be available.");
        }
    }

    private void viewMyBookings() {
        bookingDAO.viewBookingsByCustomer(customerId);
    }
}
