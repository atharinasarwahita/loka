/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author user
 */
public enum StatusVerifikasi {
    Terverifikasi("Terverifikasi"),
    BelumDiverifikasi("Belum Diverifikasi"),
    Ditolak("Ditolak");

    private final String nilai;

    StatusVerifikasi(String nilai) {
        this.nilai = nilai;
    }

    public String getNilai() {
        return nilai;
    }

    public static StatusVerifikasi fromValue(String text) {
        if (text == null) return BelumDiverifikasi;
        for (StatusVerifikasi b : StatusVerifikasi.values()) {
            if (b.nilai.equalsIgnoreCase(text) || b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return BelumDiverifikasi; 
    }
}