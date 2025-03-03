package org.carrental.DAO;

import java.sql.*;

import org.carrental.DBUtil.DBUtil;

public class CustomerDAO {

    // Validate Customer Login using Email & Password
    public int validateCustomer(String email, String password) {
        String sql = "SELECT customer_id FROM customer WHERE email = ? AND password = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("customer_id"); // Return customer ID
            }
        } catch (SQLException e) {
            System.out.println("❌ Error validating customer: " + e.getMessage());
        }
        return -1; // Return -1 if login fails
    }


    // Register a new Customer
    public boolean registerCustomer(String name, String email, String phone, String password) {
        String sql = "INSERT INTO customer (name, email, phone, password) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password); // ⚠️ Hashing recommended instead of plain text

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Return true if insertion was successful

        } catch (SQLException e) {
            System.out.println("❌ Error registering customer: " + e.getMessage());
        }
        return false;
    }

    public void viewAllCustomers() {
        String sql = "SELECT * FROM customer";

        try (Connection connection = DBUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n===== Registered Customers =====");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("customer_id") +
                        ", Name: " + rs.getString("name") +
                        ", Email: " + rs.getString("email") +
                        ", Phone: " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching customers: " + e.getMessage());
        }
    }

}
