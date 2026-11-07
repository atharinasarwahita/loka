/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.StatusDibacaPesan;
import java.util.Date;

public class Pesan {
    private int idPesan;
    private int penjualIdPengguna;
    private int pembeliIdPengguna;
    private String namaToko;
    private String namaPembeli; 
    private String isiPesan;
    private Date waktuKirim;
    private StatusDibacaPesan dibaca;
    private boolean dariPenjual; 

    public Pesan() {}

    public Pesan(int idPesan, int penjualIdPengguna, int pembeliIdPengguna, String isiPesan, Date waktuKirim, StatusDibacaPesan dibaca) {
        this.idPesan = idPesan;
        this.penjualIdPengguna = penjualIdPengguna;
        this.pembeliIdPengguna = pembeliIdPengguna;
        this.isiPesan = isiPesan;
        this.waktuKirim = waktuKirim;
        this.dibaca = dibaca;
    }

    public int getIdPesan() { 
        return idPesan; 
    }
    
    public void setIdPesan(int idPesan) { 
        this.idPesan = idPesan; 
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

    public String getIsiPesan() { 
        return isiPesan; 
    }
    
    public void setIsiPesan(String isiPesan) { 
        this.isiPesan = isiPesan; 
    }

    public Date getWaktuKirim() { 
        return waktuKirim; 
    }
    
    public void setWaktuKirim(Date waktuKirim) { 
        this.waktuKirim = waktuKirim; 
    }

    public StatusDibacaPesan getDibaca() { 
        return dibaca; 
    }
    
    public void setDibaca(StatusDibacaPesan dibaca) { 
        this.dibaca = dibaca; 
    }
    
    public String getNamaToko() {
        return (namaToko != null) ? namaToko : "Toko Saya";
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }
    
    public int getIdPembeli() {
        return pembeliIdPengguna;
    }

    public String getNamaPembeli() {
        return (namaPembeli != null) ? namaPembeli : "Pelanggan";
    }
    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getStatusDibaca() {
        return (dibaca != null) ? dibaca.toString() : "Belum Dibalas";
    }

    public boolean isDariPenjual() {
        return dariPenjual;
    }
    public void setDariPenjual(boolean dariPenjual) {
        this.dariPenjual = dariPenjual;
    }

    public boolean validasiIsiPesan(String isiPesan) {
        if (isiPesan != null && !isiPesan.trim().isEmpty()) {
            return true;
        } else {
            System.out.println("Peringatan: Isi pesan tidak boleh kosong!");
            return false;
        }
    }

    public boolean validasiIdPenggunaPesan(int id) {
        if (id > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID Pengguna pada pesan tidak valid!");
            return false;
        }
    }
}
