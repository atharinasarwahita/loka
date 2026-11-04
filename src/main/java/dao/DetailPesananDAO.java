 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import model.DetailPesanan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailPesananDAO {

    public void insertDetailPesanan(DetailPesanan dp) {
        String query = "INSERT INTO detail_pesanan (id_detail, Pesanan_no_pesanan, Pesanan_Penjual_id_pengguna, Pesanan_Pembeli_id_pengguna, Produk_id_produk, Produk_Penjual_id_pengguna, Pengembalian_id_pengembalian, jumlah_produk, harga_satuan, sub_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, dp.getIdDetail());
            ps.setInt(2, dp.getPesananNoPesanan());
            ps.setInt(3, dp.getPesananPenjualIdPengguna());
            ps.setInt(4, dp.getPesananPembeliIdPengguna());
            ps.setInt(5, dp.getProdukIdProduk());
            ps.setInt(6, dp.getProdukPenjualIdPengguna());
            
            if (dp.getPengembalianIdPengembalian() != null) {
                ps.setInt(7, dp.getPengembalianIdPengembalian());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            ps.setInt(8, dp.getJumlahProduk());
            ps.setDouble(9, dp.getHargaSatuan());
            ps.setDouble(10, dp.getSubTotal());

            ps.executeUpdate();
            System.out.println("Berhasil mencatat detail pesanan!");
        } catch (SQLException e) {
            System.out.println("Gagal mencatat detail pesanan: " + e.getMessage());
        }
    }

    public List<DetailPesanan> getAllDetailPesanan() {
        List<DetailPesanan> list = new ArrayList<>();
        String query = "SELECT * FROM detail_pesanan";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                DetailPesanan dp = new DetailPesanan();
                dp.setIdDetail(rs.getInt("id_detail"));
                dp.setPesananNoPesanan(rs.getInt("Pesanan_no_pesanan"));
                dp.setPesananPenjualIdPengguna(rs.getInt("Pesanan_Penjual_id_pengguna"));
                dp.setPesananPembeliIdPengguna(rs.getInt("Pesanan_Pembeli_id_pengguna"));
                dp.setProdukIdProduk(rs.getInt("Produk_id_produk"));
                dp.setProdukPenjualIdPengguna(rs.getInt("Produk_Penjual_id_pengguna"));
                
                int pengembalianId = rs.getInt("Pengembalian_id_pengembalian");
                if (rs.wasNull()) {
                    dp.setPengembalianIdPengembalian(null);
                } else {
                    dp.setPengembalianIdPengembalian(pengembalianId);
                }

                dp.setJumlahProduk(rs.getInt("jumlah_produk"));
                dp.setHargaSatuan(rs.getDouble("harga_satuan"));
                dp.setSubTotal(rs.getDouble("sub_total"));
                list.add(dp);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data detail pesanan: " + e.getMessage());
        }
        return list;
    }
}
