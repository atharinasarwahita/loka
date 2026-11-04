/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Keranjang {
    private int idKeranjang;
    private int pembeliIdPengguna;
    private int jumlah;
    private Date tanggalDitambahkan;
    private int produkIdProduk;
    private int produkPenjualIdPengguna;

    public Keranjang() {}

    public Keranjang(int idKeranjang, int pembeliIdPengguna, int jumlah, Date tanggalDitambahkan, int produkIdProduk, int produkPenjualIdPengguna) {
        this.idKeranjang = idKeranjang;
        this.pembeliIdPengguna = pembeliIdPengguna;
        this.jumlah = jumlah;
        this.tanggalDitambahkan = tanggalDitambahkan;
        this.produkIdProduk = produkIdProduk;
        this.produkPenjualIdPengguna = produkPenjualIdPengguna;
    }

    public int getIdKeranjang() { 
        return idKeranjang; 
    }
    
    public void setIdKeranjang(int idKeranjang) { 
        this.idKeranjang = idKeranjang; 
    }

    public int getPembeliIdPengguna() { 
        return pembeliIdPengguna; 
    }
    
    public void setPembeliIdPengguna(int pembeliIdPengguna) { 
        this.pembeliIdPengguna = pembeliIdPengguna; 
    }

    public int getJumlah() { 
        return jumlah; 
    }
    
    public void setJumlah(int jumlah) { 
        this.jumlah = jumlah; 
    }

    public Date getTanggalDitambahkan() { 
        return tanggalDitambahkan; 
    }
    
    public void setTanggalDitambahkan(Date tanggalDitambahkan) { 
        this.tanggalDitambahkan = tanggalDitambahkan; 
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
    
    public boolean validasiJumlah(int jumlah) {
        if (jumlah > 0) {
            return true;
        } else {
            System.out.println("Peringatan: Jumlah produk di keranjang minimal harus 1!");
            return false;
        }
    }
    
    public boolean validasiId(int id) {
        if (id > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID tidak boleh bernilai negatif atau nol!");
            return false;
        }
    }
}
