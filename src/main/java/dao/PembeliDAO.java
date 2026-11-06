/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import config.Koneksi;
import model.Pembeli;
import enums.JenisKelamin;
import enums.RolePengguna;
import enums.StatusAkun;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PembeliDAO {

    public void insertPembeli(Pembeli pembeli) {
        String queryPengguna = "INSERT INTO pengguna (nama_lengkap, email, password, no_telpon, role) VALUES (?, ?, ?, ?, 'Pembeli')";
        String queryPembeli = "INSERT INTO pembeli (id_pengguna, tanggal_lahir, jenis_kelamin, kota, kecamatan, kode_pos, alamat_pengiriman, saldo, pin, status_akun, tanggal_daftar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement psPengguna = conn.prepareStatement(queryPengguna, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            psPengguna.setString(1, pembeli.getNamaLengkap());
            psPengguna.setString(2, pembeli.getEmail());
            psPengguna.setString(3, pembeli.getPassword());
            psPengguna.setString(4, pembeli.getNoTelpon());
            psPengguna.executeUpdate();

            ResultSet rs = psPengguna.getGeneratedKeys();
            int idPenggunaBaru = 0;
            if (rs.next()) {
                idPenggunaBaru = rs.getInt(1);
            }

            try (PreparedStatement psPembeli = conn.prepareStatement(queryPembeli)) {
                psPembeli.setInt(1, idPenggunaBaru);
                psPembeli.setDate(2, new java.sql.Date(pembeli.getTanggalLahir().getTime()));
                psPembeli.setString(3, pembeli.getJenisKelamin().name());
                psPembeli.setString(4, pembeli.getKota());
                psPembeli.setString(5, pembeli.getKecamatan());
                psPembeli.setString(6, pembeli.getKodePos());
                psPembeli.setString(7, pembeli.getAlamatPengiriman());
                psPembeli.setDouble(8, pembeli.getSaldo());
                psPembeli.setString(9, pembeli.getPin());
                psPembeli.setString(10, pembeli.getStatusAkun().name());
                psPembeli.setTimestamp(11, new java.sql.Timestamp(pembeli.getTanggalDaftar().getTime()));
                psPembeli.executeUpdate();
            }

            conn.commit();
            System.out.println("Berhasil menyimpan data pembeli!");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan pembeli: " + e.getMessage());
        }
    }

    public List<Pembeli> getAllPembeli() {
        List<Pembeli> list = new ArrayList<>();
        String query = "SELECT * FROM pembeli p JOIN pengguna u ON p.id_pengguna = u.id_pengguna";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Pembeli p = new Pembeli();
                p.setIdPengguna(rs.getInt("id_pengguna"));
                p.setNamaLengkap(rs.getString("nama_lengkap"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setNoTelpon(rs.getString("no_telpon"));
                p.setRole(RolePengguna.valueOf(rs.getString("role")));
                p.setTanggalLahir(rs.getDate("tanggal_lahir"));
                p.setJenisKelamin(JenisKelamin.valueOf(rs.getString("jenis_kelamin")));
                p.setKota(rs.getString("kota"));
                p.setKecamatan(rs.getString("kecamatan"));
                p.setKodePos(rs.getString("kode_pos"));
                p.setAlamatPengiriman(rs.getString("alamat_pengiriman"));
                p.setSaldo(rs.getDouble("saldo"));
                p.setPin(rs.getString("pin"));
                p.setStatusAkun(StatusAkun.valueOf(rs.getString("status_akun")));
                p.setTanggalDaftar(rs.getTimestamp("tanggal_daftar"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pembeli: " + e.getMessage());
        }
        return list;
    }
    
    public void updateSaldo(int idPengguna, double saldoBaru) {
    String sql = "UPDATE pembeli SET saldo = ? WHERE id_pengguna = ?";
    try (Connection conn = Koneksi.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setDouble(1, saldoBaru);
        ps.setInt(2, idPengguna);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}