/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import config.Koneksi;
import model.Pengelola;
import enums.PosisiPengelola;
import enums.RolePengguna;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PengelolaDAO {

    public void insertPengelola(Pengelola pengelola) {
        String queryPengguna = "INSERT INTO pengguna (nama_lengkap, email, password, no_telpon, role) VALUES (?, ?, ?, ?, 'Pengelola')";
        String queryPengelola = "INSERT INTO pengelola (id_pengguna, posisi) VALUES (?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement psPengguna = conn.prepareStatement(queryPengguna, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            psPengguna.setString(1, pengelola.getNamaLengkap());
            psPengguna.setString(2, pengelola.getEmail());
            psPengguna.setString(3, pengelola.getPassword());
            psPengguna.setString(4, pengelola.getNoTelpon());
            psPengguna.executeUpdate();

            ResultSet rs = psPengguna.getGeneratedKeys();
            int idPenggunaBaru = 0;
            if (rs.next()) {
                idPenggunaBaru = rs.getInt(1);
            }

            try (PreparedStatement psPengelola = conn.prepareStatement(queryPengelola)) {
                psPengelola.setInt(1, idPenggunaBaru);
                psPengelola.setString(2, pengelola.getPosisi().name());
                psPengelola.executeUpdate();
            }

            conn.commit();
            System.out.println("Berhasil menyimpan data pengelola!");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan pengelola: " + e.getMessage());
        }
    }

    public List<Pengelola> getAllPengelola() {
        List<Pengelola> list = new ArrayList<>();
        String query = "SELECT * FROM pengelola p JOIN pengguna u ON p.id_pengguna = u.id_pengguna";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Pengelola p = new Pengelola();
                p.setIdPengguna(rs.getInt("id_pengguna"));
                p.setNamaLengkap(rs.getString("nama_lengkap"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setNoTelpon(rs.getString("no_telpon"));
                p.setRole(RolePengguna.valueOf(rs.getString("role")));
                p.setPosisi(PosisiPengelola.valueOf(rs.getString("posisi")));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pengelola: " + e.getMessage());
        }
        return list;
    }
}