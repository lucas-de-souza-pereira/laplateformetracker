package com.example.DAO;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.*;

public class UserDAO {

    public static boolean register(String username, String password) {
        String hashed = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, hashed);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean login(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hash = rs.getString("password");
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
                return result.verified;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
