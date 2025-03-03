package org.carrental.DAO;

import org.carrental.DBUtil.DBUtil;
import java.sql.*;

public class BookingDAO {
    public void viewAllBookings() {
        String sql = "SELECT b.booking_id, c.name AS customer_name, ca.model AS car_model, " +
                "b.rental_date, b.return_date, b.total_cost " +
                "FROM booking b " +
                "JOIN customer c ON b.customer_id = c.customer_id " +
                "JOIN car ca ON b.car_id = ca.car_id";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== All Bookings =====");
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("booking_id") +
                        " | Customer: " + rs.getString("customer_name") +
                        " | Car: " + rs.getString("car_model") +
                        " | Rental Date: " + rs.getDate("rental_date") +
                        " | Return Date: " + rs.getDate("return_date") +
                        " | Total Cost: ₹" + rs.getDouble("total_cost"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching bookings: " + e.getMessage());
        }
    }

    public boolean cancelBookingByAdmin(int bookingId) {
        String sql = "DELETE FROM booking WHERE booking_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error canceling booking: " + e.getMessage());
        }
        return false;
    }

    public void viewBookingsByCustomer(int customerId) {
        String sql = "SELECT * FROM booking WHERE customer_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n===== Your Bookings =====");
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("booking_id") +
                        ", Car ID: " + rs.getInt("car_id") +
                        ", Rental Date: " + rs.getDate("rental_date") +
                        ", Return Date: " + rs.getDate("return_date") +
                        ", Total Cost: ₹" + rs.getDouble("total_cost"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching bookings: " + e.getMessage());
        }
    }

    public boolean cancelBooking(int bookingId, int customerId) {
        String sql = "DELETE FROM booking WHERE booking_id = ? AND customer_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);
            stmt.setInt(2, customerId);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Returns true if booking was deleted successfully

        } catch (SQLException e) {
            System.out.println("❌ Error canceling booking: " + e.getMessage());
        }
        return false;
    }

    public boolean bookCar(int customerId, int carId, Date rentalDate, Date returnDate) {
        // Step 1: Check if the car is already booked in the given period
        String checkSql = "SELECT * FROM booking WHERE car_id = ? " +
                "AND ((rental_date BETWEEN ? AND ?) OR (return_date BETWEEN ? AND ?))";

        String insertSql = "INSERT INTO booking (customer_id, car_id, rental_date, return_date, total_cost) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkSql);
             PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {

            // Checking car availability
            checkStmt.setInt(1, carId);
            checkStmt.setDate(2, rentalDate);
            checkStmt.setDate(3, returnDate);
            checkStmt.setDate(4, rentalDate);
            checkStmt.setDate(5, returnDate);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("❌ Car is already booked for the selected dates! Please choose another car or dates.");
                return false;
            }
            // Calculate total cost (for now, assuming a fixed rate per day, e.g., 1000)
            long days = (returnDate.getTime() - rentalDate.getTime()) / (1000 * 60 * 60 * 24);
            double totalCost = days * 1000; // Example: ₹1000 per day
            // Proceed with booking
            insertStmt.setInt(1, customerId);
            insertStmt.setInt(2, carId);
            insertStmt.setDate(3, rentalDate);
            insertStmt.setDate(4, returnDate);
            insertStmt.setDouble(5, totalCost);

            int rowsInserted = insertStmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error booking car: " + e.getMessage());
        }
        return false;
    }

}
