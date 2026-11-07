/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import model.Pesan;
import enums.StatusDibacaPesan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesanDAO {

    public void insertPesan(Pesan pesan) {
        String query = "INSERT INTO pesan (Penjual_id_pengguna, Pembeli_id_pengguna, isi_pesan, waktu_kirim, dibaca) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, pesan.getPenjualIdPengguna());
            ps.setInt(2, pesan.getPembeliIdPengguna());
            ps.setString(3, pesan.getIsiPesan());
            ps.setTimestamp(4, new java.sql.Timestamp(pesan.getWaktuKirim().getTime()));

            String statusDibaca = (pesan.getDibaca() == StatusDibacaPesan.SudahDibaca) ? "Sudah Dibaca" : "Belum Dibaca";
            ps.setString(5, statusDibaca);

            ps.executeUpdate();
            System.out.println("Berhasil mengirim pesan!");
        } catch (SQLException e) {
            System.out.println("Gagal mengirim pesan: " + e.getMessage());
        }
    }

    public List<Pesan> getAllPesan() {
        List<Pesan> list = new ArrayList<>();
        String query = "SELECT * FROM pesan";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Pesan p = new Pesan();
                p.setIdPesan(rs.getInt("id_pesan"));
                p.setPenjualIdPengguna(rs.getInt("Penjual_id_pengguna"));
                p.setPembeliIdPengguna(rs.getInt("Pembeli_id_pengguna"));
                p.setIsiPesan(rs.getString("isi_pesan"));
                p.setWaktuKirim(rs.getTimestamp("waktu_kirim"));
                String statusDb = rs.getString("dibaca");
                if ("Sudah Dibaca".equals(statusDb)) {
                    p.setDibaca(StatusDibacaPesan.SudahDibaca);
                } else {
                    p.setDibaca(StatusDibacaPesan.BelumDibaca);
                }
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pesan: " + e.getMessage());
        }
        return list;
    }
    
    public List<Pesan> getInboxByPenjual(int idPenjual) {
        List<Pesan> list = new ArrayList<>();
        String query = "SELECT p.id_pesan, p.Penjual_id_pengguna, p.Pembeli_id_pengguna, " +
                        "       p.isi_pesan, p.waktu_kirim, p.dibaca, " +
                        "       pg.nama_lengkap AS nama_pembeli " +
                        "FROM pesan p " +
                        "JOIN pengguna pg ON pg.id_pengguna = p.Pembeli_id_pengguna " +
                        "WHERE p.Penjual_id_pengguna = ? " +
                        "  AND p.id_pesan = ( " +
                        "        SELECT MAX(p2.id_pesan) FROM pesan p2 " +
                        "        WHERE p2.Penjual_id_pengguna = p.Penjual_id_pengguna " +
                        "          AND p2.Pembeli_id_pengguna = p.Pembeli_id_pengguna ) " +
                        "ORDER BY p.id_pesan DESC";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idPenjual);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pesan p = new Pesan();
                p.setIdPesan(rs.getInt("id_pesan"));
                p.setPenjualIdPengguna(rs.getInt("Penjual_id_pengguna"));
                p.setPembeliIdPengguna(rs.getInt("Pembeli_id_pengguna"));
                p.setNamaPembeli(rs.getString("nama_pembeli"));
                p.setIsiPesan(rs.getString("isi_pesan"));
                p.setWaktuKirim(rs.getTimestamp("waktu_kirim"));
                
                String statusDb = rs.getString("dibaca");
                if ("Sudah Dibaca".equals(statusDb)) {
                    p.setDibaca(StatusDibacaPesan.SudahDibaca);
                } else {
                    p.setDibaca(StatusDibacaPesan.BelumDibaca);
                }
                
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal ambil inbox: " + e.getMessage());
        }
        return list;
    }

    public List<Pesan> getChatHistory(int idPenjual, int idPembeli) {
        List<Pesan> list = new ArrayList<>();
        String query = "SELECT * FROM pesan WHERE Penjual_id_pengguna = ? AND Pembeli_id_pengguna = ? ORDER BY waktu_kirim ASC, id_pesan ASC";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idPenjual);
            ps.setInt(2, idPembeli);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pesan p = new Pesan();
                p.setIdPesan(rs.getInt("id_pesan"));
                p.setPenjualIdPengguna(rs.getInt("Penjual_id_pengguna"));
                p.setPembeliIdPengguna(rs.getInt("Pembeli_id_pengguna"));
                p.setIsiPesan(rs.getString("isi_pesan"));
                p.setWaktuKirim(rs.getTimestamp("waktu_kirim"));
                p.setDariPenjual(false); // Sesuaikan arah pengirim
                
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal ambil detail chat: " + e.getMessage());
        }
        return list;
    }
}
 