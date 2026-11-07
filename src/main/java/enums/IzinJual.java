/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author user
 */
public enum IzinJual {
    Diizinkan("Diizinkan"),
    Menunggu("Menunggu"),
    TidakDiizinkan("Tidak Diizinkan");

    private final String nilaiDb;

    IzinJual(String nilaiDb) {
        this.nilaiDb = nilaiDb;
    }

    public String getNilaiDb() {
        return nilaiDb;
    }

    public static IzinJual fromString(String text) {
        if (text == null) return null;
        for (IzinJual i : IzinJual.values()) {
            if (i.nilaiDb.equalsIgnoreCase(text) || i.name().equalsIgnoreCase(text)) {
                return i;
            }
        }
        return null;
    }
}
