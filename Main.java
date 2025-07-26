
import database.DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import model.*;
import service.AuthService;
import service.Kasir;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            // Ambil koneksi dari DB.java
            Connection connection = DB.connect();

            // Inisialisasi database: buat tabel jika belum ada
            DB.initDB();

            // Kirim connection ke AuthService dan Kasir
            AuthService authService = new AuthService(connection);
            Kasir kasir = new Kasir(connection);

            boolean loggedIn = false;

            // Loop login/register sampai berhasil login
            while (!loggedIn) {
                System.out.println("=== Menu Login ===");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.print("Pilih: ");
                String pilihan = input.nextLine();

                if (pilihan.equals("1")) {
                    // Register
                    System.out.print("Username baru: ");
                    String username = input.nextLine();
                    System.out.print("Password baru: ");
                    String password = input.nextLine();

                    boolean registered = authService.register(username, password);
                    if (registered) {
                        System.out.println("Registrasi berhasil! Silakan login.");
                    } else {
                        System.out.println("Username sudah digunakan, coba yang lain.");
                    }

                } else if (pilihan.equals("2")) {
                    // Login
                    System.out.print("Username: ");
                    String username = input.nextLine();
                    System.out.print("Password: ");
                    String password = input.nextLine();

                    if (authService.login(username, password)) {
                        System.out.println("Login berhasil. Selamat datang, " + username + "!");
                        loggedIn = true;
                    } else {
                        System.out.println("Username atau password salah, coba lagi.");
                    }

                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            }

            // Setelah login sukses, tampilkan menu tambah nasabah
            System.out.println("\n=== Tambah Nasabah ===");
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Umur: ");
            int umur = Integer.parseInt(input.nextLine());

            int nasabahId = kasir.tambahNasabah(nama, umur);

            System.out.print("Jenis Asuransi (kesehatan/jiwa): ");
            String jenis = input.nextLine();
            Asuransi asuransi;

            if (jenis.equalsIgnoreCase("kesehatan")) {
                asuransi = new AsuransiKesehatan(nama, umur);
            } else {
                asuransi = new AsuransiJiwa(nama, umur);
            }

            int premi = asuransi.hitungPremi();
            int polisId = kasir.tambahPolis(nasabahId, jenis, premi);

            System.out.println("\n== Informasi Nasabah ==");
            System.out.println(asuransi.getInfoNasabah());
            System.out.println("Premi " + jenis + ": Rp " + premi);

            System.out.print("\nInput klaim? (y/n): ");
            if (input.nextLine().equalsIgnoreCase("y")) {
                System.out.print("Tanggal klaim (YYYY-MM-DD): ");
                String tanggal = input.nextLine();
                System.out.print("Status klaim: ");
                String status = input.nextLine();
                kasir.tambahKlaim(polisId, tanggal, status);
                System.out.println("\n== Klaim ==");
                kasir.tampilKlaim(polisId);
            }

            connection.close(); // tutup koneksi saat selesai
        } catch (SQLException e) {
            System.err.println("Koneksi database gagal: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Terjadi error: " + e.getMessage());
        }

        input.close();
    }
}
