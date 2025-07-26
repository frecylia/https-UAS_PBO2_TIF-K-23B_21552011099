package database;

import java.sql.*;

public class DB {

    private static final String DB_URL = "jdbc:sqlite:asuransi.db";

    // Load driver SQLite saat class dipanggil
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver tidak ditemukan.");
        }
    }

    // Method untuk membuka koneksi ke database
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Method untuk membuat tabel jika belum ada
    public static void initDB() throws SQLException {
        String userSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT UNIQUE, "
                + "password TEXT)";

        String nasabahSQL = "CREATE TABLE IF NOT EXISTS nasabah ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nama TEXT, "
                + "umur INTEGER)";

        String polisSQL = "CREATE TABLE IF NOT EXISTS polis ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nasabah_id INTEGER, "
                + "jenis TEXT, "
                + "premi INTEGER)";

        String klaimSQL = "CREATE TABLE IF NOT EXISTS klaim ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "polis_id INTEGER, "
                + "tanggal TEXT, "
                + "status TEXT)";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(userSQL);
            stmt.execute(nasabahSQL);
            stmt.execute(polisSQL);
            stmt.execute(klaimSQL);
        } catch (SQLException e) {
            System.err.println("Gagal membuat tabel: " + e.getMessage());
            throw e;
        }
    }
}
