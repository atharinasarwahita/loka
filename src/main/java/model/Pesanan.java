/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.StatusPesanan;
import java.util.Date;

public class Pesanan {
    private int noPesanan;
    private int penjualIdPengguna;
    private int pembeliIdPengguna;
    private Date tanggalPesanan;
    private double jarakKm;
    private double ongkosKirim;
    private double totalBayar;
    private StatusPesanan statusPesanan;
    private Date tanggalDikirim;
    private Date tanggalDiterima;

    public Pesanan() {}

    public Pesanan(int noPesanan, int penjualIdPengguna, int pembeliIdPengguna, Date tanggalPesanan, double jarakKm, double ongkosKirim, double totalBayar, StatusPesanan statusPesanan, Date tanggalDikirim, Date tanggalDiterima) {
        this.noPesanan = noPesanan;
        this.penjualIdPengguna = penjualIdPengguna;
        this.pembeliIdPengguna = pembeliIdPengguna;
        this.tanggalPesanan = tanggalPesanan;
        this.jarakKm = jarakKm;
        this.ongkosKirim = ongkosKirim;
        this.totalBayar = totalBayar;
        this.statusPesanan = statusPesanan;
        this.tanggalDikirim = tanggalDikirim;
        this.tanggalDiterima = tanggalDiterima;
    }

    // Getter dan Setter
    public int getNoPesanan() { 
        return noPesanan; 
    }
    
    public void setNoPesanan(int noPesanan) { 
        this.noPesanan = noPesanan; 
    }

    public int getPenjualIdPengguna() { 
        return penjualIdPengguna; 
    }
    
    public void setPenjualIdPengguna(int penjualIdPengguna) { 
        this.penjualIdPengguna = penjualIdPengguna; 
    }

    public int getPembeliIdPengguna() { 
        return pembeliIdPengguna; 
    }
    
    public void setPembeliIdPengguna(int pembeliIdPengguna) { 
        this.pembeliIdPengguna = pembeliIdPengguna; 
    }

    public Date getTanggalPesanan() { 
        return tanggalPesanan; 
    }
    
    public void setTanggalPesanan(Date tanggalPesanan) { 
        this.tanggalPesanan = tanggalPesanan; 
    }

    public double getJarakKm() { 
        return jarakKm; 
    }
    
    public void setJarakKm(double jarakKm) { 
        this.jarakKm = jarakKm; 
    }

    public double getOngkosKirim() { 
        return ongkosKirim; 
    }
    
    public void setOngkosKirim(double ongkosKirim) { 
        this.ongkosKirim = ongkosKirim; 
    }

    public double getTotalBayar() { 
        return totalBayar; 
    }
    
    public void setTotalBayar(double totalBayar) { 
        this.totalBayar = totalBayar; 
    }

    public StatusPesanan getStatusPesanan() { 
        return statusPesanan; 
    }
    
    public void setStatusPesanan(StatusPesanan statusPesanan) { 
        this.statusPesanan = statusPesanan; 
    }

    public Date getTanggalDikirim() { 
        return tanggalDikirim; 
    }
    
    public void setTanggalDikirim(Date tanggalDikirim) { 
        this.tanggalDikirim = tanggalDikirim; 
    }

    public Date getTanggalDiterima() { 
        return tanggalDiterima; 
    }
    
    public void setTanggalDiterima(Date tanggalDiterima) { 
        this.tanggalDiterima = tanggalDiterima; 
    }
    public boolean validasiJarak(double jarakKm) {
        if (jarakKm >= 0) {
            return true;
        } else {
            System.out.println("Peringatan: Jarak pengiriman tidak boleh bernilai minus!");
            return false;
        }
    }

    public boolean validasiOngkirDanTotal(double nilai) {
        if (nilai >= 0) {
            return true;
        } else {
            System.out.println("Peringatan: Ongkos kirim atau total bayar tidak boleh bernilai minus!");
            return false;
        }
    }
    
}
