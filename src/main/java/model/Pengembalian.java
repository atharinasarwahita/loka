/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.StatusPengembalian;
import java.util.Date;

public class Pengembalian {
    private int idPengembalian;
    private String alasanPengembalian;
    private double nominalPengembalian;
    private StatusPengembalian statusPengembalian;
    private Date tanggalPengembalian;

    public Pengembalian() {}

    public Pengembalian(int idPengembalian, String alasanPengembalian, double nominalPengembalian, StatusPengembalian statusPengembalian, Date tanggalPengembalian) {
        this.idPengembalian = idPengembalian;
        this.alasanPengembalian = alasanPengembalian;
        this.nominalPengembalian = nominalPengembalian;
        this.statusPengembalian = statusPengembalian;
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public int getIdPengembalian() { 
        return idPengembalian; 
    }
    
    public void setIdPengembalian(int idPengembalian) { 
        this.idPengembalian = idPengembalian; 
    }

    // --- ALIAS GETTER & SETTER UNTUK ALASAN ---
    public String getAlasanPengembalian() { 
        return alasanPengembalian; 
    }
    
    public void setAlasanPengembalian(String alasanPengembalian) { 
        this.alasanPengembalian = alasanPengembalian; 
    }

    // Tambahan method alias getAlasan() agar fleksibel dipanggil dari View/Controller manapun
    public String getAlasan() {
        return alasanPengembalian;
    }

    public void setAlasan(String alasan) {
        this.alasanPengembalian = alasan;
    }
    // ------------------------------------------

    public double getNominalPengembalian() { 
        return nominalPengembalian; 
    }
    
    public void setNominalPengembalian(double nominalPengembalian) { 
        this.nominalPengembalian = nominalPengembalian; 
    }

    public StatusPengembalian getStatusPengembalian() { 
        return statusPengembalian; 
    }
    
    public void setStatusPengembalian(StatusPengembalian statusPengembalian) { 
        this.statusPengembalian = statusPengembalian; 
    }

    public Date getTanggalPengembalian() { 
        return tanggalPengembalian; 
    }
    
    public void setTanggalPengembalian(Date tanggalPengembalian) { 
        this.tanggalPengembalian = tanggalPengembalian; 
    }

    public boolean validasiNominal(double nominal) {
        if (nominal > 0) {
            return true;
        } else {
            System.out.println("Peringatan: Nominal pengembalian dana harus lebih dari 0!");
            return false;
        }
    }

    public boolean validasiAlasan(String alasan) {
        if (alasan != null && !alasan.trim().isEmpty()) {
            return true;
        } else {
            System.out.println("Peringatan: Alasan pengembalian tidak boleh kosong!");
            return false;
        }
    }
}