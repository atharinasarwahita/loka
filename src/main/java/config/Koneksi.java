package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;
    
    public static Connection getKoneksi() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/lokanew";
                String user = "root";
                String pass = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                conn = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }
    
    public static Connection getConnection() {
        return getKoneksi();
    }
}