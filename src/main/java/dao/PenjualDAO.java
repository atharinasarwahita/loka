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
        String query = "SELECT * FROM penjual p JOIN pengguna u ON p.id_pengguna = u.id_pengguna";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Penjual p = new Penjual();
                p.setIdPengguna(rs.getInt("id_pengguna"));
                p.setNamaLengkap(rs.getString("nama_lengkap"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setNoTelpon(rs.getString("no_telpon"));
                p.setRole(RolePengguna.valueOf(rs.getString("role")));
                p.setNamaToko(rs.getString("nama_toko"));
                p.setDeskripsiToko(rs.getString("deskripsi_toko"));
                p.setAlamatToko(rs.getString("alamat_toko"));
                p.setKota(rs.getString("kota"));
                p.setKecamatan(rs.getString("kecamatan"));
                p.setKodePos(rs.getString("kode_pos"));
                p.setSaldo(rs.getDouble("saldo"));
                p.setNoIzinUsaha(rs.getString("no_izin_usaha"));
                
                // Mencegah error saat membaca data ENUM dari database ke Java Enum
                String statusVerifDb = rs.getString("status_verifikasi");
                if ("Terverifikasi".equalsIgnoreCase(statusVerifDb)) {
                    p.setStatusVerifikasi(StatusVerifikasi.Terverifikasi);
                } else {
                    // Menyesuaikan jika di enum Java dinamai BelumTerverifikasi atau BelumDiverifikasi
                    try {
                        p.setStatusVerifikasi(StatusVerifikasi.valueOf("BelumDiverifikasi"));
                    } catch (Exception ex) {
                        p.setStatusVerifikasi(StatusVerifikasi.valueOf("BelumTerverifikasi"));
                    }
                }

                p.setTanggalVerifikasi(rs.getTimestamp("tanggal_verifikasi"));
                p.setStatusAkun(StatusAkun.valueOf(rs.getString("status_akun")));
                p.setTanggalDaftar(rs.getTimestamp("tanggal_daftar"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data penjual: " + e.getMessage());
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