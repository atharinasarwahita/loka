/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import model.Ulasan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UlasanDAO {

    public void insertUlasan(Ulasan ulasan) {
        String query = "INSERT INTO ulasan (id_ulasan, Pembeli_id_pengguna, komentar, rating, tanggal_ulasan, Produk_id_produk, Produk_Penjual_id_pengguna) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, ulasan.getIdUlasan());
            ps.setInt(2, ulasan.getPembeliIdPengguna());
            ps.setString(3, ulasan.getKomentar());
            ps.setDouble(4, ulasan.getRating());
            ps.setTimestamp(5, new java.sql.Timestamp(ulasan.getTanggalUlasan().getTime()));
            ps.setInt(6, ulasan.getProdukIdProduk());
            ps.setInt(7, ulasan.getProdukPenjualIdPengguna());

            ps.executeUpdate();
            System.out.println("Berhasil menambahkan ulasan!");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan ulasan: " + e.getMessage());
        }
    }

    public List<Ulasan> getAllUlasan() {
        List<Ulasan> list = new ArrayList<>();
        String query = "SELECT * FROM ulasan";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Ulasan u = new Ulasan();
                u.setIdUlasan(rs.getInt("id_ulasan"));
                u.setPembeliIdPengguna(rs.getInt("Pembeli_id_pengguna"));
                u.setKomentar(rs.getString("komentar"));
                u.setRating(rs.getDouble("rating"));
                u.setTanggalUlasan(rs.getTimestamp("tanggal_ulasan"));
                u.setProdukIdProduk(rs.getInt("Produk_id_produk"));
                u.setProdukPenjualIdPengguna(rs.getInt("Produk_Penjual_id_pengguna"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data ulasan: " + e.getMessage());
        }
        return list;
    }
}