/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import model.Pengembalian;
import enums.StatusPengembalian;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PengembalianDAO {

    public void insertPengembalian(Pengembalian pengembalian) {

        String query = "INSERT INTO pengembalian (alasan_pengembalian, nominal_pengembalian, status_pengembalian, tanggal_pengembalian) VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, pengembalian.getAlasanPengembalian());
            ps.setDouble(2, pengembalian.getNominalPengembalian());
            

            String statusStr = "Diproses";
            if (pengembalian.getStatusPengembalian() != null) {
                switch (pengembalian.getStatusPengembalian()) {
                    case PengembalianDisetujui:
                        statusStr = "Pengembalian Disetujui"; 
                        break;
                    case PengembalianDitolak:
                        statusStr = "Pengembalian Ditolak";
                        break;
                    default:
                        statusStr = "Diproses";
                        break;
                }
            }
            ps.setString(3, statusStr);
            ps.setTimestamp(4, new java.sql.Timestamp(pengembalian.getTanggalPengembalian().getTime()));

            ps.executeUpdate();
            System.out.println("Berhasil mencatat pengembalian!");
        } catch (SQLException e) {
            System.out.println("Gagal mencatat pengembalian: " + e.getMessage());
        }
    }

    public List<Pengembalian> getAllPengembalian() {
        List<Pengembalian> list = new ArrayList<>();
        String query = "SELECT * FROM pengembalian";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Pengembalian p = new Pengembalian();
                p.setIdPengembalian(rs.getInt("id_pengembalian"));
                p.setAlasanPengembalian(rs.getString("alasan_pengembalian"));
                p.setNominalPengembalian(rs.getDouble("nominal_pengembalian"));
                

                String dbStatus = rs.getString("status_pengembalian");
                StatusPengembalian statusEnum = StatusPengembalian.Diproses; // Default
                
                if (dbStatus != null) {

                    String cleanedStatus = dbStatus.replace(" ", ""); 
                    try {
                        statusEnum = StatusPengembalian.valueOf(cleanedStatus);
                    } catch (IllegalArgumentException e) {

                        if (dbStatus.equalsIgnoreCase("Pengembalian Disetujui")) {
                            statusEnum = StatusPengembalian.PengembalianDisetujui;
                        } else if (dbStatus.equalsIgnoreCase("Pengembalian Ditolak")) {
                            statusEnum = StatusPengembalian.PengembalianDitolak;
                        }
                    }
                }
                p.setStatusPengembalian(statusEnum);

                p.setTanggalPengembalian(rs.getTimestamp("tanggal_pengembalian"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pengembalian: " + e.getMessage());
        }
        return list;
    }

    public void updateStatusPengembalian(int idPengembalian, StatusPengembalian statusPengembalian) {
        String query = "UPDATE pengembalian SET status_pengembalian = ? WHERE id_pengembalian = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            String statusStr = "Diproses";
            if (statusPengembalian == StatusPengembalian.PengembalianDisetujui) {
                statusStr = "Pengembalian Disetujui";
            } else if (statusPengembalian == StatusPengembalian.PengembalianDitolak) {
                statusStr = "Pengembalian Ditolak";
            }

            ps.setString(1, statusStr);
            ps.setInt(2, idPengembalian);

            ps.executeUpdate();
            System.out.println("Berhasil memperbarui status pengembalian!");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui status pengembalian: " + e.getMessage());
        }
    }
}