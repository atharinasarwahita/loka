/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.IzinJual;
import enums.StatusProduk;
import java.util.Date;

public class Produk {
    private int idProduk;
    private int penjualIdPengguna;
    private String namaProduk;
    private String deskripsi;
    private String kategori;
    private String gambarProduk; // Path/Link string penyimpanan gambar
    private double harga;
    private int beratGram;
    private int stok;
    private StatusProduk status;
    private IzinJual izinJual;
    private int jumlahShare;
    private Date tanggalDibuat;

    public Produk() {}

    public Produk(int idProduk, int penjualIdPengguna, String namaProduk, String deskripsi, String kategori, String gambarProduk, double harga, int beratGram, int stok, StatusProduk status, IzinJual izinJual, int jumlahShare, Date tanggalDibuat) {
        this.idProduk = idProduk;
        this.penjualIdPengguna = penjualIdPengguna;
        this.namaProduk = namaProduk;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
        this.gambarProduk = gambarProduk;
        this.harga = harga;
        this.beratGram = beratGram;
        this.stok = stok;
        this.status = status;
        this.izinJual = izinJual;
        this.jumlahShare = jumlahShare;
        this.tanggalDibuat = tanggalDibuat;
    }

    // Getter dan Setter
    public int getIdProduk() { 
        return idProduk; 
    }
    
    public void setIdProduk(int idProduk) { 
        this.idProduk = idProduk; 
    }

    public int getPenjualIdPengguna() { 
        return penjualIdPengguna; 
    }
    
    public void setPenjualIdPengguna(int penjualIdPengguna) { 
        this.penjualIdPengguna = penjualIdPengguna; 
    }

    public String getNamaProduk() { 
        return namaProduk; 
    }
    
    public void setNamaProduk(String namaProduk) { 
        this.namaProduk = namaProduk; 
    }

    public String getDeskripsi() { 
        return deskripsi; 
    }
    
    public void setDeskripsi(String deskripsi) { 
        this.deskripsi = deskripsi; 
    }

    public String getKategori() { 
        return kategori; 
    }
    
    public void setKategori(String kategori) { 
        this.kategori = kategori; 
    }

    public String getGambarProduk() { 
        return gambarProduk; 
    }
    
    public void setGambarProduk(String gambarProduk) { 
        this.gambarProduk = gambarProduk; 
    }

    public double getHarga() { 
        return harga; 
    }
    
    public void setHarga(double harga) { 
        if (validasiHarga(harga)) {
            this.harga = harga; 
        }
    }

    public int getBeratGram() { 
        return beratGram; 
    }
    
    public void setBeratGram(int beratGram) { 
        if (validasiBerat(beratGram)) {
            this.beratGram = beratGram; 
        }
    }

    public int getStok() { 
        return stok; 
    }
    
    public void setStok(int stok) { 
        if (validasiStok(stok)) {
            this.stok = stok; 
        }
    }

    public StatusProduk getStatus() { 
        return status; 
    }
    
    public void setStatus(StatusProduk status) { 
        this.status = status; 
    }

    public IzinJual getIzinJual() { 
        return izinJual; 
    }
    
    public void setIzinJual(IzinJual izinJual) { 
        this.izinJual = izinJual; 
    }

    public int getJumlahShare() { 
        return jumlahShare; 
    }
    
    public void setJumlahShare(int jumlahShare) { 
        this.jumlahShare = jumlahShare; 
    }

    public Date getTanggalDibuat() { 
        return tanggalDibuat; 
    }
    
    public void setTanggalDibuat(Date tanggalDibuat) { 
        this.tanggalDibuat = tanggalDibuat; 
    }

    public boolean validasiHarga(double harga) {
        if (harga > 0) {
            return true;
        } else {
            System.out.println("Peringatan: Harga produk harus lebih besar dari 0!");
            return false;
        }
    }

    public boolean validasiBerat(int beratGram) {
        if (beratGram > 0) {
            return true;
        } else {
            System.out.println("Peringatan: Berat produk harus lebih dari 0 gram!");
            return false;
        }
    }

    public boolean validasiStok(int stok) {
        if (stok >= 0) {
            return true;
        } else {
            System.out.println("Peringatan: Stok produk tidak boleh bernilai minus!");
            return false;
        }
    }
}