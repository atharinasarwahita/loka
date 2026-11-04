/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.PosisiPengelola;
import enums.RolePengguna;

public class Pengelola extends Pengguna {
    private PosisiPengelola posisi;

    public Pengelola() {}

    public Pengelola(int idPengguna, String namaLengkap, String email, String password, String noTelpon, RolePengguna role, PosisiPengelola posisi) {
        super(idPengguna, namaLengkap, email, password, noTelpon, role);
        setPosisi(posisi);
    }

    public PosisiPengelola getPosisi() { 
        return posisi; 
    }
    
    public void setPosisi(PosisiPengelola posisi) {
        if (validasiPosisi(posisi)) {
            this.posisi = posisi;
        }
    }

    public boolean validasiPosisi(PosisiPengelola posisi) {
        if (posisi != null) {
            return true;
        } else {
            System.out.println(" Posisi pengelola tidak boleh kosong!");
            return false;
        }
    }
}