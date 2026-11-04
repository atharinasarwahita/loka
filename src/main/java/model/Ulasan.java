/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Ulasan {
    private int idUlasan;
    private int pembeliIdPengguna;
    private String komentar;
    private double rating;
    private Date tanggalUlasan;
    private int produkIdProduk;
    private int produkPenjualIdPengguna;

    public Ulasan() {}

    public Ulasan(int idUlasan, int pembeliIdPengguna, String komentar, double rating, Date tanggalUlasan, int produkIdProduk, int produkPenjualIdPengguna) {
        this.idUlasan = idUlasan;
        this.pembeliIdPengguna = pembeliIdPengguna;
        this.komentar = komentar;
        this.rating = rating;
        this.tanggalUlasan = tanggalUlasan;
        this.produkIdProduk = produkIdProduk;
        this.produkPenjualIdPengguna = produkPenjualIdPengguna;
    }

    public int getIdUlasan() { 
        return idUlasan; 
    }
    
    public void setIdUlasan(int idUlasan) { 
        this.idUlasan = idUlasan; 
    }

    public int getPembeliIdPengguna() { 
        return pembeliIdPengguna; 
    }
    
    public void setPembeliIdPengguna(int pembeliIdPengguna) { 
        this.pembeliIdPengguna = pembeliIdPengguna; 
    }

    public String getKomentar() { 
        return komentar; 
    }
    
    public void setKomentar(String komentar) { 
        this.komentar = komentar; 
    }

    public double getRating() { 
        return rating; 
    }
    
    public void setRating(double rating) { 
        this.rating = rating; 
    }

    public Date getTanggalUlasan() { 
        return tanggalUlasan; 
    }
    
    public void setTanggalUlasan(Date tanggalUlasan) { 
        this.tanggalUlasan = tanggalUlasan; 
    }

    public int getProdukIdProduk() { 
        return produkIdProduk; 
    }
    
    public void setProdukIdProduk(int produkIdProduk) { 
        this.produkIdProduk = produkIdProduk; 
    }

    public int getProdukPenjualIdPengguna() { 
        return produkPenjualIdPengguna; 
    }
    
    public void setProdukPenjualIdPengguna(int produkPenjualIdPengguna) { 
        this.produkPenjualIdPengguna = produkPenjualIdPengguna; 
    }
    
    public boolean validasiIdUlasan(int idUlasan) {
        if (idUlasan > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID ulasan harus lebih dari 0!");
            return false;
        }
    }

    public boolean validasiPembeliIdPengguna(int pembeliIdPengguna) {
        if (pembeliIdPengguna > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID pembeli harus lebih dari 0!");
            return false;
        }
    }

    public boolean validasiKomentar(String komentar) {
        if (komentar != null && !komentar.trim().isEmpty()) {
            return true;
        } else {
            System.out.println("Peringatan: Komentar ulasan tidak boleh kosong!");
            return false;
        }
    }

    public boolean validasiRating(double rating) {
        if (rating >= 1 && rating <= 5) {
            return true;
        } else {
            System.out.println("Peringatan: Rating harus berada antara 1 sampai 5!");
            return false;
        }
    }

    public boolean validasiTanggalUlasan(Date tanggalUlasan) {
        if (tanggalUlasan != null && !tanggalUlasan.after(new Date())) {
            return true;
        } else {
            System.out.println("Peringatan: Tanggal ulasan tidak valid!");
            return false;
        }
    }

    public boolean validasiProdukIdProduk(int produkIdProduk) {
        if (produkIdProduk > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID produk harus lebih dari 0!");
            return false;
        }
    }
    
    public boolean validasiProdukPenjualIdPengguna(int produkPenjualIdPengguna) {
        if (produkPenjualIdPengguna > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID penjual harus lebih dari 0!");
            return false;
        }
    }
}