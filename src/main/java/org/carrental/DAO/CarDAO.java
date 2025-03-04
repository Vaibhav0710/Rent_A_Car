package org.carrental.DAO;

import org.carrental.DBUtil.DBUtil;
import java.sql.*;

public class CarDAO {
    public boolean addCar(String model, String brand, int year, double rentalPrice) {
        String sql = "INSERT INTO car (model, brand, year, rental_price, is_available) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, model);
            stmt.setString(2, brand);
            stmt.setInt(3, year);
            stmt.setDouble(4, rentalPrice);
            stmt.setBoolean(5, true); // Newly added cars are available by default

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding car: " + e.getMessage());
        }
        return false;
    }

    public boolean removeCar(int carId) {
        String sql = "DELETE FROM car WHERE car_id = ?"; // Permanently delete the car

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, carId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error removing car: " + e.getMessage());
        }
        return false;
    }

    public void viewAllCars() {
        String sql = "SELECT * FROM car"; // Fetch all cars

        try (Connection connection = DBUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n===== All Cars =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("car_id") +
                        ", Model: " + rs.getString("model") +
                        ", Brand: " + rs.getString("brand") +
                        ", Year: " + rs.getInt("year") +
                        ", Rent Per Day: ₹" + rs.getDouble("rental_price") +
                        ", Available: " + (rs.getBoolean("is_available") ? "Yes" : "No"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching cars: " + e.getMessage());
        }
    }
}
