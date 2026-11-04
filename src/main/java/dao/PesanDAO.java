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
        // Query tetap seperti semula
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
                p.setDibaca(StatusDibacaPesan.valueOf(rs.getString("dibaca")));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pesan: " + e.getMessage());
        }
        return list;
    }
}
 