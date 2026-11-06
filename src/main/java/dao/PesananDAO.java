package dao;

import config.Koneksi;
import model.Pesanan;
import enums.StatusPesanan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesananDAO {

    public void insertPesanan(Pesanan pesanan) {
        String query = "INSERT INTO pesanan (no_pesanan, Penjual_id_pengguna, Pembeli_id_pengguna, tanggal_pesanan, jarak_km, ongkos_kirim, total_bayar, status_pesanan, tanggal_dikirim, tanggal_diterima) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, pesanan.getNoPesanan());
            ps.setInt(2, pesanan.getPenjualIdPengguna());
            ps.setInt(3, pesanan.getPembeliIdPengguna());
            ps.setTimestamp(4, new java.sql.Timestamp(pesanan.getTanggalPesanan().getTime()));
            ps.setDouble(5, pesanan.getJarakKm());
            ps.setDouble(6, pesanan.getOngkosKirim());
            ps.setDouble(7, pesanan.getTotalBayar());
            ps.setString(8, pesanan.getStatusPesanan().name());
            
            if (pesanan.getTanggalDikirim() != null) {
                ps.setTimestamp(9, new java.sql.Timestamp(pesanan.getTanggalDikirim().getTime()));
            } else {
                ps.setNull(9, Types.TIMESTAMP);
            }

            if (pesanan.getTanggalDiterima() != null) {
                ps.setTimestamp(10, new java.sql.Timestamp(pesanan.getTanggalDiterima().getTime()));
            } else {
                ps.setNull(10, Types.TIMESTAMP);
            }

            ps.executeUpdate();
            System.out.println("Berhasil mencatat pesanan!");
        } catch (SQLException e) {
            System.out.println("Gagal mencatat pesanan: " + e.getMessage());
        }
    }

    public List<Pesanan> getAllPesanan() {
        List<Pesanan> list = new ArrayList<>();
        String query = "SELECT * FROM pesanan";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Pesanan p = new Pesanan();
                p.setNoPesanan(rs.getInt("no_pesanan"));
                p.setPenjualIdPengguna(rs.getInt("Penjual_id_pengguna"));
                p.setPembeliIdPengguna(rs.getInt("Pembeli_id_pengguna"));
                p.setTanggalPesanan(rs.getTimestamp("tanggal_pesanan"));
                p.setJarakKm(rs.getDouble("jarak_km"));
                p.setOngkosKirim(rs.getDouble("ongkos_kirim"));
                p.setTotalBayar(rs.getDouble("total_bayar"));
                p.setStatusPesanan(StatusPesanan.valueOf(rs.getString("status_pesanan")));
                p.setTanggalDikirim(rs.getTimestamp("tanggal_dikirim"));
                p.setTanggalDiterima(rs.getTimestamp("tanggal_diterima"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pesanan: " + e.getMessage());
        }
        return list;
    }

    public void updateStatusPesanan(int noPesanan, StatusPesanan statusPesanan, java.util.Date tglDikirim, java.util.Date tglDiterima) {
        String query = "UPDATE pesanan SET status_pesanan = ?, tanggal_dikirim = ?, tanggal_diterima = ? WHERE no_pesanan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, statusPesanan.name());
            
            if (tglDikirim != null) {
                ps.setTimestamp(2, new java.sql.Timestamp(tglDikirim.getTime()));
            } else {
                ps.setNull(2, Types.TIMESTAMP);
            }

            if (tglDiterima != null) {
                ps.setTimestamp(3, new java.sql.Timestamp(tglDiterima.getTime()));
            } else {
                ps.setNull(3, Types.TIMESTAMP);
            }

            ps.setInt(4, noPesanan);
            ps.executeUpdate();
            System.out.println("Berhasil memperbarui status pesanan!");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui status pesanan: " + e.getMessage());
        }
    }
}