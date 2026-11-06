/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import model.Penjual;
import enums.RolePengguna;
import enums.StatusAkun;
import enums.StatusVerifikasi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PenjualDAO {

    public void insertPenjual(Penjual penjual) {
        String queryPengguna = "INSERT INTO pengguna (nama_lengkap, email, password, no_telpon, role) VALUES (?, ?, ?, ?, 'Penjual')";
        String queryPenjual = "INSERT INTO penjual (id_pengguna, nama_toko, deskripsi_toko, alamat_toko, kota, kecamatan, kode_pos, saldo, no_izin_usaha, status_verifikasi, tanggal_verifikasi, status_akun, tanggal_daftar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement psPengguna = conn.prepareStatement(queryPengguna, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            psPengguna.setString(1, penjual.getNamaLengkap());
            psPengguna.setString(2, penjual.getEmail());
            psPengguna.setString(3, penjual.getPassword());
            psPengguna.setString(4, penjual.getNoTelpon());
            psPengguna.executeUpdate();

            ResultSet rs = psPengguna.getGeneratedKeys();
            int idPenggunaBaru = 0;
            if (rs.next()) {
                idPenggunaBaru = rs.getInt(1);
            }

            try (PreparedStatement psPenjual = conn.prepareStatement(queryPenjual)) {
                psPenjual.setInt(1, idPenggunaBaru);
                psPenjual.setString(2, penjual.getNamaToko());
                psPenjual.setString(3, penjual.getDeskripsiToko());
                psPenjual.setString(4, penjual.getAlamatToko());
                psPenjual.setString(5, penjual.getKota());
                psPenjual.setString(6, penjual.getKecamatan());
                psPenjual.setString(7, penjual.getKodePos());
                psPenjual.setDouble(8, penjual.getSaldo());
                psPenjual.setString(9, penjual.getNoIzinUsaha());
                
                // === PERBAIKAN DI SINI (Menyesuaikan dengan ENUM database MySQL) ===
                String statusVerifStr = "Belum Diverifikasi"; // Default sesuai database
                if (penjual.getStatusVerifikasi() != null) {
                    if (penjual.getStatusVerifikasi().name().equalsIgnoreCase("Terverifikasi")) {
                        statusVerifStr = "Terverifikasi";
                    } else {
                        statusVerifStr = "Belum Diverifikasi";
                    }
                }
                psPenjual.setString(10, statusVerifStr);
                // ================================================================

                if (penjual.getTanggalVerifikasi() != null) {
                    psPenjual.setTimestamp(11, new java.sql.Timestamp(penjual.getTanggalVerifikasi().getTime()));
                } else {
                    psPenjual.setNull(11, Types.TIMESTAMP);
                }
                psPenjual.setString(12, penjual.getStatusAkun().name());
                psPenjual.setTimestamp(13, new java.sql.Timestamp(penjual.getTanggalDaftar().getTime()));
                psPenjual.executeUpdate();
            }

            conn.commit();
            System.out.println("Berhasil menyimpan data penjual!");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan penjual: " + e.getMessage());
        }
    }

    public List<Penjual> getAllPenjual() {
    List<Penjual> list = new ArrayList<>();
    String sql = "SELECT p.*, pen.* FROM pengguna p JOIN penjual pen ON p.id_pengguna = pen.id_pengguna WHERE p.role = 'Penjual'";
    
    try (Connection conn = Koneksi.getKoneksi();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            Penjual pen = new Penjual(
                rs.getInt("id_pengguna"),
                rs.getString("nama_lengkap"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("no_telpon"),
                RolePengguna.Penjual,
                rs.getString("nama_toko"),
                rs.getString("deskripsi_toko"),
                rs.getString("alamat_toko"),
                rs.getString("kota"),
                rs.getString("kecamatan"),
                rs.getString("kode_pos"),
                rs.getDouble("saldo"),
                rs.getString("no_izin_usaha"),
                StatusVerifikasi.valueOf(rs.getString("status_verifikasi")),
                rs.getDate("tanggal_verifikasi"),
                StatusAkun.valueOf(rs.getString("status_akun")),
                rs.getDate("tanggal_daftar")
            );
            list.add(pen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // --- TAMBAHAN PELENGKAP USE CASE: UPDATE STATUS VERIFIKASI PENJUAL ---
    public void updateStatusVerifikasi(int idPengguna, StatusVerifikasi statusVerifikasi, java.util.Date tglVerifikasi) {
        String query = "UPDATE penjual SET status_verifikasi = ?, tanggal_verifikasi = ? WHERE id_pengguna = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Konversi manual agar teks yang dikirim cocok dengan ENUM 'Belum Diverifikasi'
            String statusVerifStr = "Belum Diverifikasi";
            if (statusVerifikasi != null && statusVerifikasi.name().equalsIgnoreCase("Terverifikasi")) {
                statusVerifStr = "Terverifikasi";
            }

            ps.setString(1, statusVerifStr);
            if (tglVerifikasi != null) {
                ps.setTimestamp(2, new java.sql.Timestamp(tglVerifikasi.getTime()));
            } else {
                ps.setNull(2, Types.TIMESTAMP);
            }
            ps.setInt(3, idPengguna);

            ps.executeUpdate();
            System.out.println("Berhasil memperbarui status verifikasi penjual!");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui status verifikasi: " + e.getMessage());
        }
    }
}