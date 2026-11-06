/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controll;

import dao.*;
import model.*;
import enums.*;

import java.util.Date;
import java.util.List;

public class LokaControl {
    
    private final PembeliDAO pembeliDAO;
    private final PenjualDAO penjualDAO;
    private final PengelolaDAO pengelolaDAO;
    private final ProdukDAO produkDAO;
    private final KeranjangDAO keranjangDAO;
    private final PesananDAO pesananDAO;
    private final DetailPesananDAO detailPesananDAO;
    private final PengembalianDAO pengembalianDAO;
    private final PesanDAO pesanDAO;
    private final TopUpDAO topUpDAO;
    private final UlasanDAO ulasanDAO;

    public LokaControl() {
        this.pembeliDAO = new PembeliDAO();
        this.penjualDAO = new PenjualDAO();
        this.pengelolaDAO = new PengelolaDAO();
        this.produkDAO = new ProdukDAO();
        this.keranjangDAO = new KeranjangDAO();
        this.pesananDAO = new PesananDAO();
        this.detailPesananDAO = new DetailPesananDAO();
        this.pengembalianDAO = new PengembalianDAO();
        this.pesanDAO = new PesanDAO();
        this.topUpDAO = new TopUpDAO();
        this.ulasanDAO = new UlasanDAO();
    }

    // ==========================================
    // 1. MANAJEMEN PENGGUNA (PEMBELI, PENJUAL, PENGELOLA)
    // ==========================================
    
    public Pengguna login(String email, String password) {
    if (pembeliDAO.getAllPembeli() != null) {
        for (Pembeli p : pembeliDAO.getAllPembeli()) {
            if (p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password)) {
                return p;
            }
        }
    }

    if (penjualDAO.getAllPenjual() != null) {
        for (Penjual p : penjualDAO.getAllPenjual()) {
            if (p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password)) {
                return p;
            }
        }
    }

    if (pengelolaDAO.getAllPengelola() != null) {
        for (Pengelola p : pengelolaDAO.getAllPengelola()) {
            if (p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password)) {
                return p;
            }
        }
    }

    return null;
}
    
    public void registerPembeli(int idPengguna, String nama, String email, String pass, String telp, 
                                Date tglLahir, JenisKelamin jk, String kota, String kec, String pos, 
                                String alamat, double saldo, String pin, StatusAkun status, Date tglDaftar) {
        Pembeli pembeli = new Pembeli(idPengguna, nama, email, pass, telp, RolePengguna.Pembeli, 
                                    tglLahir, jk, kota, kec, pos, alamat, saldo, pin, status, tglDaftar);
        pembeliDAO.insertPembeli(pembeli);
    }

    public List<Pembeli> ambilSemuaPembeli() {
        return pembeliDAO.getAllPembeli();
    }

    public void registerPenjual(int idPengguna, String nama, String email, String pass, String telp, 
                                String namaToko, String deskripsi, String alamat, String kota, String kec, 
                                String pos, double saldo, String noIzin, StatusVerifikasi verif, 
                                Date tglVerif, StatusAkun status, Date tglDaftar) {
        Penjual penjual = new Penjual(idPengguna, nama, email, pass, telp, RolePengguna.Penjual, 
                                      namaToko, deskripsi, alamat, kota, kec, pos, saldo, noIzin, 
                                      verif, tglVerif, status, tglDaftar);
        penjualDAO.insertPenjual(penjual);
    }

    public List<Penjual> ambilSemuaPenjual() {
        return penjualDAO.getAllPenjual();
    }

    public void registerPengelola(int idPengguna, String nama, String email, String pass, String telp, PosisiPengelola posisi) {
        Pengelola pengelola = new Pengelola(idPengguna, nama, email, pass, telp, RolePengguna.Pengelola, posisi);
        pengelolaDAO.insertPengelola(pengelola);
    }

    public List<Pengelola> ambilSemuaPengelola() {
        return pengelolaDAO.getAllPengelola();
    }

    // ==========================================
    // 2. MANAJEMEN KATALOG PRODUK
    // ==========================================
    
    public void tambahProduk(int idProduk, int idPenjual, String nama, String deskripsi, String kategori, 
                           String gambar, double harga, int berat, int stok, StatusProduk status, 
                           IzinJual izin, int share, Date tglDibuat) {
        Produk produk = new Produk(idProduk, idPenjual, nama, deskripsi, kategori, gambar, 
                                  harga, berat, stok, status, izin, share, tglDibuat);
        produkDAO.insertProduk(produk);
    }

    public List<Produk> ambilSemuaProduk() {
        return produkDAO.getAllProduk();
    }

    // ==========================================
    // 3. KERANJANG BELANJA
    // ==========================================
    
    public void tambahKeranjang(int idKeranjang, int idPembeli, int jumlah, Date tgl, int idProduk, int idPenjualProd) {
        Keranjang keranjang = new Keranjang(idKeranjang, idPembeli, jumlah, tgl, idProduk, idPenjualProd);
        keranjangDAO.insertKeranjang(keranjang);
    }

    public List<Keranjang> ambilSemuaKeranjang() {
        return keranjangDAO.getAllKeranjang();
    }

    // ==========================================
    // 4. PESANAN & DETAIL PESANAN
    // ==========================================
    
    public void buatPesanan(int noPesanan, int idPenjual, int idPembeli, Date tglPesanan, double jarak, 
                            double ongkir, double total, StatusPesanan status, Date tglKirim, Date tglTerima) {
        Pesanan pesanan = new Pesanan(noPesanan, idPenjual, idPembeli, tglPesanan, jarak, 
                                      ongkir, total, status, tglKirim, tglTerima);
        pesananDAO.insertPesanan(pesanan);
    }

    public List<Pesanan> ambilSemuaPesanan() {
        return pesananDAO.getAllPesanan();
    }

    public void buatDetailPesanan(int idDetail, int noPesanan, int idPenjualPesanan, int idPembeliPesanan, 
                                  int idProduk, int idPenjualProduk, Integer idPengembalian, 
                                  int jumlah, double hargaSatuan, double subTotal) {
        DetailPesanan detail = new DetailPesanan(idDetail, noPesanan, idPenjualPesanan, idPembeliPesanan, 
                                                 idProduk, idPenjualProduk, idPengembalian, 
                                                 jumlah, hargaSatuan, subTotal);
        detailPesananDAO.insertDetailPesanan(detail);
    }

    public List<DetailPesanan> ambilSemuaDetailPesanan() {
        return detailPesananDAO.getAllDetailPesanan();
    }

    // ==========================================
    // 5. FITUR PENDUKUNG (PENGEMBALIAN, PESAN, TOP UP, ULASAN)
    // ==========================================
    
    public void ajukanPengembalian(int idPengembalian, String alasan, double nominal, StatusPengembalian status, Date tgl) {
        Pengembalian pengembalian = new Pengembalian(idPengembalian, alasan, nominal, status, tgl);
        pengembalianDAO.insertPengembalian(pengembalian);
    }

    public List<Pengembalian> ambilSemuaPengembalian() {
        return pengembalianDAO.getAllPengembalian();
    }

    public void kirimPesan(int idPesan, int idPenjual, int idPembeli, String isi, Date waktu, StatusDibacaPesan dibaca) {
        Pesan pesan = new Pesan(idPesan, idPenjual, idPembeli, isi, waktu, dibaca);
        pesanDAO.insertPesan(pesan);
    }

    public List<Pesan> ambilSemuaPesan() {
        return pesanDAO.getAllPesan();
    }

    public void prosesTopUp(int idTopUp, int idPembeli, double nominal) {
        TopUp topUp = new TopUp(idTopUp, idPembeli, nominal);
        topUpDAO.insertTopUp(topUp);
    }

    public List<TopUp> ambilSemuaTopUp() {
        return topUpDAO.getAllTopUp();
    }

    public void beriUlasan(int idUlasan, int idPembeli, String komentar, double rating, Date tgl, int idProduk, int idPenjualProd) {
        Ulasan ulasan = new Ulasan(idUlasan, idPembeli, komentar, rating, tgl, idProduk, idPenjualProd);
        ulasanDAO.insertUlasan(ulasan);
    }

    public List<Ulasan> ambilSemuaUlasan() {
        return ulasanDAO.getAllUlasan();
    }

    // ==========================================
    // 6. METODE PELENGKAP (UPDATE & DELETE DARI DAO)
    // ==========================================
    
    public void ubahProduk(int idProduk, int idPenjual, String nama, String deskripsi, String kategori, 
                           String gambar, double harga, int berat, int stok, StatusProduk status, 
                           IzinJual izin, int share) {
        Produk produk = new Produk(idProduk, idPenjual, nama, deskripsi, kategori, gambar, 
                                  harga, berat, stok, status, izin, share, new Date());
        produkDAO.updateProduk(produk);
    }

    public void hapusProduk(int idProduk) {
        produkDAO.deleteProduk(idProduk);
    }

    public void updateStatusPesanan(int noPesanan, StatusPesanan statusBaru, Date tglKirim, Date tglTerima) {
        pesananDAO.updateStatusPesanan(noPesanan, statusBaru, tglKirim, tglTerima);
    }

    public void verifikasiPenjual(int idPengguna, StatusVerifikasi statusVerifikasi, Date tglVerifikasi) {
        penjualDAO.updateStatusVerifikasi(idPengguna, statusVerifikasi, tglVerifikasi);
    }

    public void updateStatusPengembalian(int idPengembalian, StatusPengembalian statusBaru) {
        pengembalianDAO.updateStatusPengembalian(idPengembalian, statusBaru);
    }
    
    public void hapusKeranjang(int idProduk, int idPembeli) {
        keranjangDAO.deleteKeranjang(idProduk, idPembeli);
    }
}