package org.carrental.DAO;

import org.carrental.DBUtil.DBUtil;
import java.sql.*;

public class CarDAO {
    public boolean addCar(String model, String brand, double rentPerDay) {
        String sql = "INSERT INTO car (model, brand, rent_per_day) VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, model);
            stmt.setString(2, brand);
            stmt.setDouble(3, rentPerDay);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding car: " + e.getMessage());
        }
        return false;
    }

    public boolean removeCar(int carId) {
        String sql = "DELETE FROM car WHERE car_id = ?";

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
        String sql = "SELECT * FROM car";

        try (Connection connection = DBUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n===== Available Cars =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("car_id") +
                        ", Model: " + rs.getString("model") +
                        ", Brand: " + rs.getString("brand") +
                        ", Rent Per Day: ₹" + rs.getDouble("rent_per_day"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching cars: " + e.getMessage());
        }
    }
}
