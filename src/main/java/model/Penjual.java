package model;

import enums.RolePengguna;
import enums.StatusAkun;
import enums.StatusVerifikasi;
import java.util.Date;

public class Penjual extends Pengguna {
    private String namaToko;
    private String deskripsiToko;
    private String alamatToko;
    private String kota;
    private String kecamatan;
    private String kodePos;
    private double saldo;
    private String noIzinUsaha;
    private StatusVerifikasi statusVerifikasi;
    private Date tanggalVerifikasi;
    private StatusAkun statusAkun;
    private Date tanggalDaftar;

    public Penjual() {
    }

    public Penjual(int idPengguna, String namaLengkap, String email, String password, String noTelpon, RolePengguna role, String namaToko, String deskripsiToko, String alamatToko, String kota, String kecamatan, String kodePos, double saldo, String noIzinUsaha, StatusVerifikasi statusVerifikasi, Date tanggalVerifikasi, StatusAkun statusAkun, Date tanggalDaftar) {
        
        super(idPengguna, namaLengkap, email, password, noTelpon, role);
        
        setNamaToko(namaToko);
        this.deskripsiToko = deskripsiToko;
        this.alamatToko = alamatToko;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kodePos = kodePos;
        setSaldo(saldo);
        setNoIzinUsaha(noIzinUsaha);
        this.statusVerifikasi = statusVerifikasi;
        this.tanggalVerifikasi = tanggalVerifikasi;
        this.statusAkun = statusAkun;
        this.tanggalDaftar = tanggalDaftar;
    }

    // POLYMORPHISM: Implementasi method abstrak dari Pengguna
    @Override
    public void tampilkanInfoRole() {
        System.out.println("Role: Penjual | Nama Toko: " + namaToko + " | Status Verifikasi: " + statusVerifikasi);
    }
    
    public String getNamaPemilik() {
        return getNamaLengkap();
    }

    public String getNamaToko() { 
        return namaToko; 
    }
    
    public void setNamaToko(String namaToko) {
        if (validasiNamaToko(namaToko)) {
            this.namaToko = namaToko;
        }
    }

    public String getDeskripsiToko() { 
        return deskripsiToko; 
    }
    
    public void setDeskripsiToko(String deskripsiToko) { 
        this.deskripsiToko = deskripsiToko; 
    }

    public String getAlamatToko() { 
        return alamatToko; 
    }
    
    public void setAlamatToko(String alamatToko) { 
        this.alamatToko = alamatToko; 
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

    public double getSaldo() { 
        return saldo; 
    }
    
    public void setSaldo(double saldo) {
        if (validasiSaldoPenjual(saldo)) {
            this.saldo = saldo;
        }
    }

    public String getNoIzinUsaha() { 
        return noIzinUsaha; 
    }
    
    public void setNoIzinUsaha(String noIzinUsaha) {
        if (validasiNoIzinUsaha(noIzinUsaha)) {
            this.noIzinUsaha = noIzinUsaha;
        }
    }

    public StatusVerifikasi getStatusVerifikasi() { 
        return statusVerifikasi; 
    }
    
    public void setStatusVerifikasi(StatusVerifikasi statusVerifikasi) { 
        this.statusVerifikasi = statusVerifikasi; 
    }

    public Date getTanggalVerifikasi() { 
        return tanggalVerifikasi; 
    }
    
    public void setTanggalVerifikasi(Date tanggalVerifikasi) { 
        this.tanggalVerifikasi = tanggalVerifikasi; 
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

    public boolean validasiNamaToko(String namaToko) {
        if (namaToko != null && !namaToko.trim().isEmpty()) {
            return true;
        } else {
            System.out.println("Peringatan: Nama toko tidak boleh kosong!");
            return false;
        }
    }

    public boolean validasiSaldoPenjual(double saldo) {
        if (saldo >= 0) {
            return true;
        } else {
            System.out.println("Peringatan: Saldo penjual tidak boleh bernilai minus!");
            return false;
        }
    }

    public boolean validasiNoIzinUsaha(String noIzinUsaha) {
        if (noIzinUsaha != null && !noIzinUsaha.trim().isEmpty()) {
            return true;
        } else {
            System.out.println("Peringatan: Nomor izin usaha wajib diisi!");
            return false;
        }
    }
    
    public String getTanggalDaftarString() { return getTanggalDaftar() != null ? getTanggalDaftar().toString() : "-"; }
    public String getStatusVerifikasiString() { return getStatusVerifikasi() != null ? getStatusVerifikasi().toString() : "-"; }
}