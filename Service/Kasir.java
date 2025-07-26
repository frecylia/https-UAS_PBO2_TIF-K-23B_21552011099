package service;

import java.sql.*;

public class Kasir {

    private Connection connection;

    public Kasir(Connection connection) {
        this.connection = connection;
    }

    public int tambahNasabah(String nama, int umur) throws SQLException {
        String sql = "INSERT INTO nasabah (nama, umur) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nama);
            stmt.setInt(2, umur);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // ID nasabah
            } else {
                throw new SQLException("Gagal mengambil ID nasabah.");
            }
        }
    }

    public int tambahPolis(int nasabahId, String jenis, int premi) throws SQLException {
        String sql = "INSERT INTO polis (nasabah_id, jenis, premi) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, nasabahId);
            stmt.setString(2, jenis);
            stmt.setInt(3, premi);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // ID polis
            } else {
                throw new SQLException("Gagal mengambil ID polis.");
            }
        }
    }

    public void tambahKlaim(int polisId, String tanggal, String status) throws SQLException {
        String sql = "INSERT INTO klaim (polis_id, tanggal, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, polisId);
            stmt.setString(2, tanggal);
            stmt.setString(3, status);
            stmt.executeUpdate();
        }
    }

    public void tampilKlaim(int polisId) throws SQLException {
        String sql = "SELECT * FROM klaim WHERE polis_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, polisId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Tanggal: " + rs.getString("tanggal"));
                System.out.println("Status : " + rs.getString("status"));
            }
        }
    }
}
