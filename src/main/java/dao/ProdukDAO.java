package dao;

import config.Koneksi;
import model.Produk;
import enums.IzinJual;
import enums.StatusProduk;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukDAO {

    public void insertProduk(Produk produk) {
        String query = "INSERT INTO produk (Penjual_id_pengguna, nama_produk, deskripsi, kategori, gambar_produk, harga, berat_gram, stok, status, izin_jual, jumlah_share, tanggal_dibuat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, produk.getPenjualIdPengguna());
            ps.setString(2, produk.getNamaProduk());
            ps.setString(3, produk.getDeskripsi());
            ps.setString(4, produk.getKategori());
            ps.setString(5, produk.getGambarProduk());
            ps.setDouble(6, produk.getHarga());
            ps.setInt(7, produk.getBeratGram());
            ps.setInt(8, produk.getStok());
            ps.setString(9, produk.getStatus().name());
            ps.setString(10, produk.getIzinJual().name());
            ps.setInt(11, produk.getJumlahShare());
            ps.setTimestamp(12, new java.sql.Timestamp(produk.getTanggalDibuat().getTime()));

            ps.executeUpdate();
            System.out.println("Berhasil menambahkan produk!");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan produk: " + e.getMessage());
        }
    }

    public List<Produk> getAllProduk() {
        List<Produk> list = new ArrayList<>();
        String query = "SELECT * FROM produk";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Produk p = new Produk();
                p.setIdProduk(rs.getInt("id_produk"));
                p.setPenjualIdPengguna(rs.getInt("Penjual_id_pengguna"));
                p.setNamaProduk(rs.getString("nama_produk"));
                p.setDeskripsi(rs.getString("deskripsi"));
                p.setKategori(rs.getString("kategori"));
                p.setGambarProduk(rs.getString("gambar_produk"));
                p.setHarga(rs.getDouble("harga"));
                p.setBeratGram(rs.getInt("berat_gram"));
                p.setStok(rs.getInt("stok"));
                p.setStatus(StatusProduk.valueOf(rs.getString("status")));
                p.setIzinJual(IzinJual.valueOf(rs.getString("izin_jual")));
                p.setJumlahShare(rs.getInt("jumlah_share"));
                p.setTanggalDibuat(rs.getTimestamp("tanggal_dibuat"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data produk: " + e.getMessage());
        }
        return list;
    }

    // --- TAMBAHAN PELENGKAP USE CASE: UPDATE & DELETE PRODUK ---
    public void updateProduk(Produk produk) {
        String sql = "UPDATE produk SET nama_produk=?, deskripsi=?, kategori=?, gambar_produk=?, "
               + "harga=?, berat_gram=?, stok=?, status=?, izin_jual=?, jumlah_share=? "
               + "WHERE id_produk=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, produk.getNamaProduk());
            ps.setString(2, produk.getDeskripsi());
            ps.setString(3, produk.getKategori());
            ps.setString(4, produk.getGambarProduk());
            ps.setDouble(5, produk.getHarga());
            ps.setInt(6, produk.getBeratGram());
            ps.setInt(7, produk.getStok());            
            ps.setString(8, produk.getStatus().name());          
            ps.setString(9, produk.getIzinJual().name());
            ps.setInt(10, produk.getJumlahShare());
            ps.setInt(11, produk.getIdProduk());    

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Berhasil update produk ID: " + produk.getIdProduk());
            }

        } catch (SQLException e) {
            System.err.println("Gagal update produk: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteProduk(int idProduk) {
        String query = "DELETE FROM produk WHERE id_produk = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idProduk);
            ps.executeUpdate();
            System.out.println("Berhasil menghapus produk!");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus produk: " + e.getMessage());
        }
    }
    
    public void updateStok(int idProduk, int stokBaru) {
        String sql = "UPDATE produk SET stok = ? WHERE id_produk = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stokBaru);
            ps.setInt(2, idProduk);
            ps.executeUpdate();
            System.out.println("Berhasil memperbarui stok produk!");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui stok produk: " + e.getMessage());
        }
    }
}