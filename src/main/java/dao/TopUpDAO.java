/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import model.TopUp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopUpDAO {

    public void insertTopUp(TopUp topup) {
        String query = "INSERT INTO top_up (id_top_up, Pembeli_id_pengguna, nominal) VALUES (?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, topup.getIdTopUp());
            ps.setInt(2, topup.getPembeliIdPengguna());
            ps.setDouble(3, topup.getNominal());

            ps.executeUpdate();
            System.out.println("Berhasil mencatat top up!");
        } catch (SQLException e) {
            System.out.println("Gagal mencatat top up: " + e.getMessage());
        }
    }

    public List<TopUp> getAllTopUp() {
        List<TopUp> list = new ArrayList<>();
        String query = "SELECT * FROM top_up";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                TopUp t = new TopUp();
                t.setIdTopUp(rs.getInt("id_top_up"));
                t.setPembeliIdPengguna(rs.getInt("Pembeli_id_pengguna"));
                t.setNominal(rs.getDouble("nominal"));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data top up: " + e.getMessage());
        }
        return list;
    }
}
