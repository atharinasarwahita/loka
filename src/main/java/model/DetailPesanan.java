/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class DetailPesanan {
    private int idDetail;
    private int pesananNoPesanan;
    private int pesananPenjualIdPengguna;
    private int pesananPembeliIdPengguna;
    private int produkIdProduk;
    private int produkPenjualIdPengguna;
    private Integer pengembalianIdPengembalian; // Menggunakan Integer agar bisa bernilai NULL
    private int jumlahProduk;
    private double hargaSatuan;
    private double subTotal;

    public DetailPesanan() {}

    public DetailPesanan(int idDetail, int pesananNoPesanan, int pesananPenjualIdPengguna, int pesananPembeliIdPengguna, int produkIdProduk, int produkPenjualIdPengguna, Integer pengembalianIdPengembalian, int jumlahProduk, double hargaSatuan, double subTotal) {
        this.idDetail = idDetail;
        this.pesananNoPesanan = pesananNoPesanan;
        this.pesananPenjualIdPengguna = pesananPenjualIdPengguna;
        this.pesananPembeliIdPengguna = pesananPembeliIdPengguna;
        this.produkIdProduk = produkIdProduk;
        this.produkPenjualIdPengguna = produkPenjualIdPengguna;
        this.pengembalianIdPengembalian = pengembalianIdPengembalian;
        this.jumlahProduk = jumlahProduk;
        this.hargaSatuan = hargaSatuan;
        this.subTotal = subTotal;
    }

    // Getter dan Setter
    public int getIdDetail() { 
        return idDetail; 
    }
    
    public void setIdDetail(int idDetail) { 
        this.idDetail = idDetail; 
    }

    public int getPesananNoPesanan() { 
        return pesananNoPesanan; 
    }
    
    public void setPesananNoPesanan(int pesananNoPesanan) { 
        this.pesananNoPesanan = pesananNoPesanan; 
    }

    public int getPesananPenjualIdPengguna() { 
        return pesananPenjualIdPengguna; 
    }
    
    public void setPesananPenjualIdPengguna(int pesananPenjualIdPengguna) { 
        this.pesananPenjualIdPengguna = pesananPenjualIdPengguna; 
    }

    public int getPesananPembeliIdPengguna() { 
        return pesananPembeliIdPengguna; 
    }
    
    public void setPesananPembeliIdPengguna(int pesananPembeliIdPengguna) { 
        this.pesananPembeliIdPengguna = pesananPembeliIdPengguna; 
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

    public Integer getPengembalianIdPengembalian() { 
        return pengembalianIdPengembalian; 
    }
    
    public void setPengembalianIdPengembalian(Integer pengembalianIdPengembalian) { 
        this.pengembalianIdPengembalian = pengembalianIdPengembalian; 
    }

    public int getJumlahProduk() { 
        return jumlahProduk; 
    }
    
    public void setJumlahProduk(int jumlahProduk) { 
        this.jumlahProduk = jumlahProduk; 
    }

    public double getHargaSatuan() { 
        return hargaSatuan; 
    }
    
    public void setHargaSatuan(double hargaSatuan) { 
        this.hargaSatuan = hargaSatuan; 
    }

    public double getSubTotal() { 
        return subTotal; 
    }
    
    public void setSubTotal(double subTotal) { 
        this.subTotal = subTotal; 
    }

    public boolean validasiJumlahProduk(int jumlah) {
        if (jumlah > 0) {
            return true;
        } else {
            System.out.println("Peringatan: Jumlah produk harus lebih dari 0!");
            return false;
        }
    }

    public boolean validasiHargaDanSubtotal(double nilai) {
        if (nilai >= 0) {
            return true;
        } else {
            System.out.println("Peringatan: Nilai harga/subtotal tidak boleh minus!");
            return false;
        }
    }
}