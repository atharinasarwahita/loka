/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class TopUp {
    private int idTopUp;
    private int pembeliIdPengguna;
    private double nominal;

    public TopUp() {}

    public TopUp(int idTopUp, int pembeliIdPengguna, double nominal) {
        this.idTopUp = idTopUp;
        this.pembeliIdPengguna = pembeliIdPengguna;
        this.nominal = nominal;
    }

    public int getIdTopUp() { 
        return idTopUp; 
    }
    public void setIdTopUp(int idTopUp) { 
        this.idTopUp = idTopUp; 
    }

    public int getPembeliIdPengguna() { 
        return pembeliIdPengguna; 
    }
    
    public void setPembeliIdPengguna(int pembeliIdPengguna) { 
        this.pembeliIdPengguna = pembeliIdPengguna; 
    }

    public double getNominal() { 
        return nominal; 
    }
    public void setNominal(double nominal) { 
        this.nominal = nominal; 
    }
 
    public boolean validasiIdTopUp(int idTopUp) {
        if (idTopUp > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID top-up harus lebih dari 0!");
            return false;
        }
    }

    public boolean validasiPembeliIdPengguna(int pembeliIdPengguna) {
        if (pembeliIdPengguna > 0) {
            return true;
        } else {
            System.out.println("Peringatan: ID pembeli harus lebih dari 0!");
            return false;
        }
    }

    public boolean validasiNominalTopUp(double nominal) {
        if (nominal > 0) {
            return true;
        } else {
            System.out.println("Peringatan: Nominal top-up saldo harus lebih besar dari 0!");
            return false;
        }
    }
}
