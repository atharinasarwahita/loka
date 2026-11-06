package model;

import enums.RolePengguna;

public abstract class Pengguna {
    private int idPengguna;
    private String namaLengkap;
    private String email;
    private String password;
    private String noTelpon;
    private RolePengguna role;

    public Pengguna() {}

    public Pengguna(int idPengguna, String namaLengkap, String email, String password, String noTelpon, RolePengguna role) {
        setIdPengguna(idPengguna);
        setNamaLengkap(namaLengkap);
        setEmail(email);
        setPassword(password);
        setNoTelpon(noTelpon);
        this.role = role;
    }

    public abstract void tampilkanInfoRole();

    public int getIdPengguna() { 
        return idPengguna; 
    }
    
    public void setIdPengguna(int idPengguna) {
        if (validasiIdPengguna(idPengguna)) {
            this.idPengguna = idPengguna;
        }
    }

    public String getNamaLengkap() { 
        return namaLengkap; 
    }
    
    public void setNamaLengkap(String namaLengkap) {
        if (validasiNamaLengkap(namaLengkap)) {
            this.namaLengkap = namaLengkap.trim();
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (validasiEmail(email)) {
            this.email = email;
        }
    }

    public String getPassword() { 
        return password; 
    }
    
    public void setPassword(String password) {
        if (validasiPassword(password)) {
            this.password = password;
        }
    }

    public String getNoTelpon() { 
        return noTelpon; 
    }
    
    public void setNoTelpon(String noTelpon) {
        if (validasiNoTelpon(noTelpon)) {
            this.noTelpon = noTelpon;
        }
    }

    public RolePengguna getRole() { 
        return role; 
    }
    
    public void setRole(RolePengguna role) { 
        this.role = role; 
    }

    public boolean validasiIdPengguna(int id) {
        if (id > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID pengguna harus lebih dari 0!");
            return false;
        }
    }

    public boolean validasiNamaLengkap(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            return true;
        } else {
            System.out.println("Peringatan: Nama lengkap tidak boleh kosong!");
            return false;
        }
    }

    public boolean validasiEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            return true;
        } else {
            System.out.println("Peringatan: Format email tidak valid (harus mengandung '@' dan '.')!");
            return false;
        }
    }

    public boolean validasiPassword(String password) {
        if (password != null && password.length() >= 8) {
            return true;
        } else {
            System.out.println("Peringatan: Password minimal harus 8 karakter!");
            return false;
        }
    }

    public boolean validasiNoTelpon(String noTelpon) {
        if (noTelpon != null && noTelpon.matches("[0-9]+") && noTelpon.length() >= 11) {
            return true;
        } else {
            System.out.println("Peringatan: Nomor telepon hanya boleh berisi angka dan minimal 11 digit!");
            return false;
        }
    }
}