/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import enums.JenisKelamin;
import enums.RolePengguna;
import enums.StatusAkun;
import java.util.Date;

public class Pembeli extends Pengguna {
    private Date tanggalLahir;
    private JenisKelamin jenisKelamin;
    private String kota;
    private String kecamatan;
    private String kodePos;
    private String alamatPengiriman;
    private double saldo;
    private String pin;
    private StatusAkun statusAkun;
    private Date tanggalDaftar;

    public Pembeli() {}

    public Pembeli(int idPengguna, String namaLengkap, String email, String password, String noTelpon, RolePengguna role, Date tanggalLahir, JenisKelamin jenisKelamin, String kota, String kecamatan, String kodePos, String alamatPengiriman, double saldo, String pin, StatusAkun statusAkun, Date tanggalDaftar) {
        super(idPengguna, namaLengkap, email, password, noTelpon, role);
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kodePos = kodePos;
        this.alamatPengiriman = alamatPengiriman;
        setSaldo(saldo);
        setPin(pin);
        this.statusAkun = statusAkun;
        this.tanggalDaftar = tanggalDaftar;
    }

    public Date getTanggalLahir() { 
        return tanggalLahir; 
    }
    
    public void setTanggalLahir(Date tanggalLahir) { 
        this.tanggalLahir = tanggalLahir; 
    }

    public JenisKelamin getJenisKelamin() { 
        return jenisKelamin; 
    }
    
    public void setJenisKelamin(JenisKelamin jenisKelamin) { 
        this.jenisKelamin = jenisKelamin; 
    }

    public String getKota() { 
        return kota; 
    }
    
    public void setKota(String kota) { 
        this.kota = kota; 
    }

    public String getKecamatan() { 
        return kecamatan; 
    }
    
    public void setKecamatan(String kecamatan) { 
        this.kecamatan = kecamatan; 
    }

    public String getKodePos() { 
        return kodePos; 
    }
    
    public void setKodePos(String kodePos) { 
        this.kodePos = kodePos; 
    }

    public String getAlamatPengiriman() { 
        return alamatPengiriman; 
    }
    
    public void setAlamatPengiriman(String alamatPengiriman) { 
        this.alamatPengiriman = alamatPengiriman; 
    }

    public double getSaldo() { 
        return saldo; 
    }
    
    public void setSaldo(double saldo) {
        if (validasiSaldoPembeli(saldo)) {
            this.saldo = saldo;
        }
    }

    public String getPin() { 
        return pin; 
    }
    
    public void setPin(String pin) {
        if (validasiPin(pin)) {
            this.pin = pin;
        }
    }

    public StatusAkun getStatusAkun() { 
        return statusAkun; 
    }
    
    public void setStatusAkun(StatusAkun statusAkun) { 
        this.statusAkun = statusAkun; 
    }

    public Date getTanggalDaftar() { 
        return tanggalDaftar; 
    }
    
    public void setTanggalDaftar(Date tanggalDaftar) { 
        this.tanggalDaftar = tanggalDaftar; 
    }

   
    public boolean validasiSaldoPembeli(double saldo) {
        if (saldo >= 0) {
            return true;
        } else {
            System.out.println("Peringatan: Saldo pembeli tidak boleh bernilai minus!");
            return false;
        }
    }

    public boolean validasiPin(String pin) {
        if (pin != null && pin.matches("[0-9]{6}")) {
            return true;
        } else {
            System.out.println("Peringatan: PIN harus berupa 6 digit angka!");
            return false;
        }
    }
}