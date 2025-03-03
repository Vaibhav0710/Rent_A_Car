package org.carrental.DAO;

import org.carrental.DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    // Validate Admin Login
    public boolean validateAdmin(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // ⚠️ Hashing recommended instead of plain text
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If data exists, admin login is valid
            }

        } catch (SQLException e) {
            System.out.println("❌ Error validating admin: " + e.getMessage());
        }
        return false;
    }
}
