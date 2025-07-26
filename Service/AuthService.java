package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AuthService {

    private Connection connection;

    public AuthService(Connection connection) {
        this.connection = connection;
    }

    // Method untuk hash password dengan SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm tidak ditemukan.", e);
        }
    }

    public boolean register(String username, String password) throws SQLException {
        // Cek apakah username sudah ada
        String cekUser = "SELECT id FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(cekUser)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Username sudah ada
                return false;
            }
        }

        // Hash password sebelum disimpan
        String hashedPassword = hashPassword(password);

        // Insert user baru dengan password yang sudah di-hash
        String insertUser = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertUser)) {
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.executeUpdate();
            return true;
        }
    }

    public boolean login(String username, String password) throws SQLException {
        // Hash password input untuk dibandingkan dengan yang di DB
        String hashedPassword = hashPassword(password);

        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true jika ada user dengan username & hashed password ini
        }
    }
}
