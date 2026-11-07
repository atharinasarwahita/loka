package dao;

import config.Koneksi;
import model.Keranjang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeranjangDAO {

    public void insertKeranjang(Keranjang keranjang) {

        String query = "INSERT INTO keranjang (Pembeli_id_pengguna, jumlah, tanggal_ditambahkan, Produk_id_produk, Produk_Penjual_id_pengguna) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, keranjang.getPembeliIdPengguna());
            ps.setInt(2, keranjang.getJumlah());
            ps.setTimestamp(3, new java.sql.Timestamp(keranjang.getTanggalDitambahkan().getTime()));
            ps.setInt(4, keranjang.getProdukIdProduk());
            ps.setInt(5, keranjang.getProdukPenjualIdPengguna());

            ps.executeUpdate();
            System.out.println("Berhasil menambahkan ke keranjang!");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan ke keranjang: " + e.getMessage());
        }
    }

    public List<Keranjang> getAllKeranjang() {
        List<Keranjang> list = new ArrayList<>();
        String query = "SELECT * FROM keranjang";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Keranjang k = new Keranjang();
                k.setIdKeranjang(rs.getInt("id_keranjang"));
                k.setPembeliIdPengguna(rs.getInt("Pembeli_id_pengguna"));
                k.setJumlah(rs.getInt("jumlah"));
                k.setTanggalDitambahkan(rs.getTimestamp("tanggal_ditambahkan"));
                k.setProdukIdProduk(rs.getInt("Produk_id_produk"));
                k.setProdukPenjualIdPengguna(rs.getInt("Produk_Penjual_id_pengguna"));
                list.add(k);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data keranjang: " + e.getMessage());
        }
        return list;
    }
    
    public void deleteKeranjang(int idProduk, int idPembeli) {
        String query = "DELETE FROM keranjang WHERE Produk_id_produk = ? AND Pembeli_id_pengguna = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idProduk);
            ps.setInt(2, idPembeli);

            ps.executeUpdate();
            System.out.println("Berhasil menghapus item dari keranjang!");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus keranjang: " + e.getMessage());
        }
    }
}