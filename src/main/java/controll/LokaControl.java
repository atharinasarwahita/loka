package controll;

import dao.*;
import model.*;
import enums.*;
import java.util.ArrayList;

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
        // Cek Login Pembeli
        if (pembeliDAO.getAllPembeli() != null) {
            for (Pembeli p : pembeliDAO.getAllPembeli()) {
                if (p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password)) {
                    if (p.getStatusAkun() != null && p.getStatusAkun() != StatusAkun.Aktif) {
                        System.out.println("Login Gagal: Akun Pembeli tidak aktif.");
                        return null;
                    }
                    return p;
                }
            }
        }

        // Cek Login Penjual
        if (penjualDAO.getAllPenjual() != null) {
            for (Penjual p : penjualDAO.getAllPenjual()) {
                if (p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password)) {
                    if (p.getStatusAkun() != null && p.getStatusAkun() != StatusAkun.Aktif) {
                        System.out.println("Login Gagal: Akun Toko belum aktif atau diblokir.");
                        return null;
                    }
                    return p;
                }
            }
        }

        // Cek Login Pengelola (Admin / Verifikator)
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
    // OVERLOADING METHOD PENCARIAN PRODUK
    // ==========================================
    
    public Produk cariProduk(int idProduk) {
        for (Produk p : produkDAO.getAllProduk()) {
            if (p.getIdProduk() == idProduk) {
                return p;
            }
        }
        return null;
    }

    public Produk cariProduk(String namaProduk) {
        for (Produk p : produkDAO.getAllProduk()) {
            if (p.getNamaProduk().equalsIgnoreCase(namaProduk)) {
                return p;
            }
        }
        return null;
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

    // --- FITUR 7: SORTIR CHAT BELUM DIBALAS (UNREAD) ---
    public List<Pesan> getPesanBelumDibalas(int idPenjual) {
        List<Pesan> listUnread = new java.util.ArrayList<>();
        List<Pesan> allPesan = pesanDAO.getAllPesan();
        if (allPesan != null) {
            for (Pesan p : allPesan) {
                if (p.getPenjualIdPengguna() == idPenjual && p.getDibaca() == StatusDibacaPesan.BelumDibaca) {
                    listUnread.add(p);
                }
            }
        }
        return listUnread;
    }

    // ==========================================
    // KONSTANTA & VALIDASI BATASAN TOP UP (Maks 1 Juta)
    // ==========================================
    public static final double MIN_TOPUP = 10000;          // Minimal Rp 10.000
    public static final double MAX_TOPUP_SINGLE = 1000000; // Maksimal sekali top-up Rp 1.000.000
    public static final double MAX_TOPUP_HARIAN = 1000000; // Maksimal total harian per orang Rp 1.000.000

    public String prosesTopUp(int idTopUp, int idPembeli, double nominal) {
        if (nominal < MIN_TOPUP) {
            return "Gagal: Minimal top-up adalah Rp " + String.format("%,.0f", MIN_TOPUP);
        }
        if (nominal > MAX_TOPUP_SINGLE) {
            return "Gagal: Maksimal sekali top-up adalah Rp " + String.format("%,.0f", MAX_TOPUP_SINGLE);
        }

        double totalTopUpHariIni = 0;
        java.util.Calendar cal1 = java.util.Calendar.getInstance();

        List<TopUp> daftarTopUp = topUpDAO.getAllTopUp();
        if (daftarTopUp != null) {
            for (TopUp tu : daftarTopUp) {
                if (tu.getPembeliIdPengguna() == idPembeli && tu.getTanggalTopUp() != null) {
                    java.util.Calendar cal2 = java.util.Calendar.getInstance();
                    cal2.setTime(tu.getTanggalTopUp());

                    boolean sameDay = cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR) &&
                                      cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR);

                    if (sameDay) {
                        totalTopUpHariIni += tu.getNominal();
                    }
                }
            }
        }

        if ((totalTopUpHariIni + nominal) > MAX_TOPUP_HARIAN) {
            double sisaKuota = MAX_TOPUP_HARIAN - totalTopUpHariIni;
            if (sisaKuota <= 0) {
                return "Gagal: Anda telah mencapai batas maksimal top-up harian sebesar Rp " + String.format("%,.0f", MAX_TOPUP_HARIAN) + "!";
            } else {
                return "Gagal: Nominal melebihi sisa kuota top-up harian Anda hari ini (Sisa kuota: Rp " + String.format("%,.0f", sisaKuota) + ").";
            }
        }

        TopUp topUpBaru = new TopUp(idTopUp, idPembeli, nominal, new Date());
        topUpDAO.insertTopUp(topUpBaru);

        for (Pembeli pb : pembeliDAO.getAllPembeli()) {
            if (pb.getIdPengguna() == idPembeli) {
                double saldoBaru = pb.getSaldo() + nominal;
                pembeliDAO.updateSaldo(idPembeli, saldoBaru);
                break;
            }
        }

        return null;
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
        
        if (statusBaru == StatusPesanan.Dikembalikan) {
            Pesanan pesananTarget = cariPesanan(noPesanan);
            if (pesananTarget != null) {
                int idPembeli = pesananTarget.getPembeliIdPengguna();
                double nominalRefund = pesananTarget.getTotalBayar();

                for (Pembeli pb : pembeliDAO.getAllPembeli()) {
                    if (pb.getIdPengguna() == idPembeli) {
                        double saldoBaru = pb.getSaldo() + nominalRefund;
                        pembeliDAO.updateSaldo(idPembeli, saldoBaru);
                        break;
                    }
                }
            }
        } 
        else if (statusBaru == StatusPesanan.Selesai) {
        }
    }

    public void verifikasiPenjual(int idPengguna, StatusVerifikasi statusVerifikasi, Date tglVerifikasi) {
        penjualDAO.updateStatusVerifikasi(idPengguna, statusVerifikasi, tglVerifikasi);
    }

    public void updateStatusPengembalian(int idPengembalian, StatusPengembalian statusBaru) {
        pengembalianDAO.updateStatusPengembalian(idPengembalian, statusBaru);

        if (statusBaru == StatusPengembalian.PengembalianDisetujui) {
            Pengembalian targetRetur = null;
            for (Pengembalian p : pengembalianDAO.getAllPengembalian()) {
                if (p.getIdPengembalian() == idPengembalian) {
                    targetRetur = p;
                    break;
                }
            }

            if (targetRetur != null) {
                for (DetailPesanan dp : detailPesananDAO.getAllDetailPesanan()) {
                    if (dp.getPengembalianIdPengembalian() != null && dp.getPengembalianIdPengembalian() == idPengembalian) {
                        int idPembeli = dp.getPesananPembeliIdPengguna();
                        double nominalRefund = targetRetur.getNominalPengembalian();

                        for (Pembeli pb : pembeliDAO.getAllPembeli()) {
                            if (pb.getIdPengguna() == idPembeli) {
                                double saldoBaru = pb.getSaldo() + nominalRefund;
                                pembeliDAO.updateSaldo(idPembeli, saldoBaru);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    
    public void hapusKeranjang(int idProduk, int idPembeli) {
        keranjangDAO.deleteKeranjang(idProduk, idPembeli);
    }
    
    // ==========================================
    // 7. CHECKOUT
    // ==========================================
    public static final double TARIF_PER_KM = 4000;
    public static final double ONGKIR_MINIMAL = 8000;

    public double hitungOngkir(double jarakKm) {
        if (jarakKm <= 0) return 0;
        return Math.max(Math.ceil(jarakKm) * TARIF_PER_KM, ONGKIR_MINIMAL);
    }

    public String prosesCheckout(int idPembeli, int idProduk, int jumlah,
                                 double jarak, boolean dariKeranjang) {
        if (jarak <= 0) return "Jarak pengiriman harus diisi!";
        if (jumlah <= 0) return "Jumlah tidak valid!";

        Produk produk = null;
        for (Produk p : produkDAO.getAllProduk()) {
            if (p.getIdProduk() == idProduk) { produk = p; break; }
        }
        if (produk == null) return "Produk tidak ditemukan!";
        if (produk.getStok() < jumlah) return "Stok tidak cukup! Sisa stok: " + produk.getStok();

        Pembeli pembeli = null;
        for (Pembeli pb : pembeliDAO.getAllPembeli()) {
            if (pb.getIdPengguna() == idPembeli) { pembeli = pb; break; }
        }
        if (pembeli == null) return "Pembeli tidak ditemukan!";

        double subtotal = produk.getHarga() * jumlah;
        double ongkir = hitungOngkir(jarak);
        double total = subtotal + ongkir;

        if (pembeli.getSaldo() < total) return "Saldo LOKA-Pay tidak cukup!";

        int noPesanan = 1;
        for (Pesanan ps : pesananDAO.getAllPesanan()) {
            if (ps.getNoPesanan() >= noPesanan) noPesanan = ps.getNoPesanan() + 1;
        }
        int idPenjual = produk.getPenjualIdPengguna();

        buatPesanan(noPesanan, idPenjual, idPembeli, new Date(), jarak, ongkir, total,
                    StatusPesanan.Diproses, null, null);
        buatDetailPesanan(0, noPesanan, idPenjual, idPembeli, idProduk, idPenjual,
                          null, jumlah, produk.getHarga(), subtotal);
        pembeliDAO.updateSaldo(idPembeli, pembeli.getSaldo() - total);
        produkDAO.updateStok(idProduk, produk.getStok() - jumlah);

        if (dariKeranjang) {
            keranjangDAO.deleteKeranjang(idProduk, idPembeli);
        }
        return null;
    }
    
    // ==========================================
    // 8. FITUR PENDAPATAN & TRACKING PELANGGAN (NOMOR 3)
    // ==========================================

    // A. Filter Pendapatan Penjual Berdasarkan Tanggal Tertentu
    public double hitungPendapatanPenjualByTanggal(int idPenjual, Date targetTanggal) {
        double totalPendapatan = 0;
        List<Pesanan> listPesanan = pesananDAO.getAllPesanan();
        
        if (listPesanan != null && targetTanggal != null) {
            java.util.Calendar calTarget = java.util.Calendar.getInstance();
            calTarget.setTime(targetTanggal);

            for (Pesanan p : listPesanan) {
                if (p.getPenjualIdPengguna() == idPenjual && p.getTanggalPesanan() != null) {
                    java.util.Calendar calPesanan = java.util.Calendar.getInstance();
                    calPesanan.setTime(p.getTanggalPesanan());

                    boolean isSameDay = calTarget.get(java.util.Calendar.YEAR) == calPesanan.get(java.util.Calendar.YEAR) &&
                                        calTarget.get(java.util.Calendar.DAY_OF_YEAR) == calPesanan.get(java.util.Calendar.DAY_OF_YEAR);

                    if (isSameDay) {
                        totalPendapatan += p.getTotalBayar();
                    }
                }
            }
        }
        return totalPendapatan;
    }

    // B. Tracking Pelanggan yang Sering Beli (Mengembalikan ID Pembeli dengan frekuensi terbanyak)
    public int getPelangganTerloyal(int idPenjual) {
        java.util.Map<Integer, Integer> mapFrekuensi = new java.util.HashMap<>();

        List<Pesanan> listPesanan = pesananDAO.getAllPesanan();
        if (listPesanan != null) {
            for (Pesanan p : listPesanan) {
                if (p.getPenjualIdPengguna() == idPenjual) {
                    int idPembeli = p.getPembeliIdPengguna();
                    mapFrekuensi.put(idPembeli, mapFrekuensi.getOrDefault(idPembeli, 0) + 1);
                }
            }
        }

        int idPelangganTersetia = -1;
        int maxBeli = 0;
        for (java.util.Map.Entry<Integer, Integer> entry : mapFrekuensi.entrySet()) {
            if (entry.getValue() > maxBeli) {
                maxBeli = entry.getValue();
                idPelangganTersetia = entry.getKey();
            }
        }

        return idPelangganTersetia;
    }

    // C. Mendapatkan Info Pembeli Terloyal
    public String getInfoPelangganTerloyal(int idPenjual) {
        int idPembeli = getPelangganTerloyal(idPenjual);
        if (idPembeli == -1) return "Belum ada pelanggan.";

        List<Pembeli> listPembeli = pembeliDAO.getAllPembeli();
        if (listPembeli != null) {
            for (Pembeli pb : listPembeli) {
                if (pb.getIdPengguna() == idPembeli) {
                    return pb.getNamaLengkap() + " (Email: " + pb.getEmail() + ")";
                }
            }
        }
        return "Pelanggan ID: " + idPembeli;
    }

    // ==========================================
    // 9. INSIGHT DATA DASHBOARD PENGELOLA (NOMOR 8)
    // ==========================================

    // A. Total UMKM Terdaftar (Penjual Aktif)
    public int hitungTotalUMKM() {
        List<Penjual> listPenjual = penjualDAO.getAllPenjual();
        int total = 0;
        if (listPenjual != null) {
            for (Penjual p : listPenjual) {
                if (p.getStatusAkun() == StatusAkun.Aktif) {
                    total++;
                }
            }
        }
        return total;
    }

    // B. Total Keuntungan Keseluruhan Marketplace (Pesanan Selesai)
    public double hitungTotalKeuntunganMarketplace() {
        double totalKeuntungan = 0;
        List<Pesanan> listPesanan = pesananDAO.getAllPesanan();
        if (listPesanan != null) {
            for (Pesanan p : listPesanan) {
                if (p.getStatusPesanan() == StatusPesanan.Selesai) {
                    totalKeuntungan += p.getTotalBayar();
                }
            }
        }
        return totalKeuntungan;
    }

    // C. Kategori Produk Terlaris
    public String getKategoriProdukTerlaris() {
        java.util.Map<String, Integer> mapKategori = new java.util.HashMap<>();
        List<DetailPesanan> listDetail = detailPesananDAO.getAllDetailPesanan();
        List<Produk> listProduk = produkDAO.getAllProduk();

        if (listDetail != null && listProduk != null) {
            for (DetailPesanan dp : listDetail) {
                for (Produk pr : listProduk) {
                    if (pr.getIdProduk() == dp.getProdukIdProduk()) {
                        String kat = pr.getKategori();
                        mapKategori.put(kat, mapKategori.getOrDefault(kat, 0) + dp.getJumlahProduk());
                    }
                }
            }
        }

        String kategoriTerlaris = "-";
        int maxTerjual = 0;
        for (java.util.Map.Entry<String, Integer> entry : mapKategori.entrySet()) {
            if (entry.getValue() > maxTerjual) {
                maxTerjual = entry.getValue();
                kategoriTerlaris = entry.getKey();
            }
        }
        return kategoriTerlaris + " (" + maxTerjual + " terjual)";
    }

    // D. Kecamatan Pembeli dengan Transaksi Terbanyak
    public String getKecamatanTransaksiTerbanyak() {
        java.util.Map<String, Integer> mapKecamatan = new java.util.HashMap<>();
        List<Pesanan> listPesanan = pesananDAO.getAllPesanan();
        List<Pembeli> listPembeli = pembeliDAO.getAllPembeli();

        if (listPesanan != null && listPembeli != null) {
            for (Pesanan p : listPesanan) {
                for (Pembeli pb : listPembeli) {
                    if (pb.getIdPengguna() == p.getPembeliIdPengguna()) {
                        String kec = pb.getKecamatan();
                        if (kec != null && !kec.isEmpty()) {
                            mapKecamatan.put(kec, mapKecamatan.getOrDefault(kec, 0) + 1);
                        }
                    }
                }
            }
        }

        String kecamatanTop = "-";
        int maxTransaksi = 0;
        for (java.util.Map.Entry<String, Integer> entry : mapKecamatan.entrySet()) {
            if (entry.getValue() > maxTransaksi) {
                maxTransaksi = entry.getValue();
                kecamatanTop = entry.getKey();
            }
        }
        return kecamatanTop + " (" + maxTransaksi + " transaksi)";
    }
    
    public List<Pesan> getInboxPenjual(int idPenjual) {
        List<Pesan> list = new ArrayList<>();
        list = pesanDAO.getInboxByPenjual(idPenjual);
        // Contoh: list = pesanDao.getInboxByPenjual(idPenjual);
        return list;
    }

    public List<Pesan> getDetailChat(int idPenjual, int idPembeli) {
        List<Pesan> list = new ArrayList<>();
        list = pesanDAO.getChatHistory(idPenjual, idPembeli);
        return list;
    }

    public void kirimPesan(int idPenjual, int idPembeli, String isiPesan) {
        Pesan pesanBaru = new Pesan();
        pesanBaru.setPenjualIdPengguna(idPenjual);
        pesanBaru.setPembeliIdPengguna(idPembeli);
        pesanBaru.setIsiPesan(isiPesan);
        pesanBaru.setWaktuKirim(new Date());
        pesanBaru.setDibaca(StatusDibacaPesan.BelumDibaca);
        pesanBaru.setDariPenjual(true); 
        
        pesanDAO.insertPesan(pesanBaru);
    }
    
    public List<Penjual> getListPenjualBelumVerifikasi() {
    List<Penjual> list = new ArrayList<>();
    List<Penjual> all = penjualDAO.getAllPenjual();
    if (all != null) {
        for (Penjual p : all) {

            if (p.getStatusVerifikasi() == StatusVerifikasi.BelumDiverifikasi ) {
                list.add(p);
            }
        }
    }
    return list;
}

    public Penjual getDetailPenjualById(int idPenjual) {
        List<Penjual> all = penjualDAO.getAllPenjual();
        if (all != null) {
            for (Penjual p : all) {
                if (p.getIdPengguna() == idPenjual) {
                    return p;
                }
            }
        }
        return null;
    }
    
    public void kirimPesanDariPembeli(int idPenjual, int idPembeli, String isiPesan) {
        Pesan pesanBaru = new Pesan();
        pesanBaru.setPenjualIdPengguna(idPenjual);
        pesanBaru.setPembeliIdPengguna(idPembeli);
        pesanBaru.setIsiPesan(isiPesan);
        pesanBaru.setWaktuKirim(new Date());
        pesanBaru.setDibaca(StatusDibacaPesan.BelumDibaca);
        pesanBaru.setDariPenjual(false);
        
        pesanDAO.insertPesan(pesanBaru);
    }
    
    public void tambahJumlahShare(int idProduk) {
        Produk p = cariProduk(idProduk);
        if (p != null) {
            int shareBaru = p.getJumlahShare() + 1;

            ubahProduk(p.getIdProduk(), p.getPenjualIdPengguna(), p.getNamaProduk(), 
                       p.getDeskripsi(), p.getKategori(), p.getGambarProduk(), 
                       p.getHarga(), p.getBeratGram(), p.getStok(), p.getStatus(), 
                       p.getIzinJual(), shareBaru);
        }
    }
    
    public Pesanan cariPesanan(int noPesanan) {
        List<Pesanan> allPesanan = pesananDAO.getAllPesanan();
        if (allPesanan != null) {
            for (Pesanan p : allPesanan) {
                if (p.getNoPesanan() == noPesanan) {
                    return p;
                }
            }
        }
        return null;
    }
    
    // ==========================================
    // TAMBAHAN METHOD VERIFIKASI PENGELOLA
    // ==========================================

    // Hitung total produk yang sudah diizinkan (aktif)
    public int hitungTotalProdukAktif() {
        int total = 0;
        List<Produk> all = produkDAO.getAllProduk();
        if (all != null) {
            for (Produk p : all) {
                if (p.getIzinJual() == IzinJual.Diizinkan) {
                    total++;
                }
            }
        }
        return total;
    }

    // Hitung total UMKM/Penjual yang akunnya aktif
    public int hitungTotalUMKMAktif() {
        int total = 0;
        List<Penjual> all = penjualDAO.getAllPenjual();
        if (all != null) {
            for (Penjual p : all) {
                if (p.getStatusAkun() == StatusAkun.Aktif) {
                    total++;
                }
            }
        }
        return total;
    }

    // List Produk yang Menunggu Verifikasi (BelumDiizinkan)
    public List<Produk> getListProdukMenunggu() {
        List<Produk> list = new ArrayList<>();
        List<Produk> all = produkDAO.getAllProduk();
        if (all != null) {
            for (Produk p : all) {
                if (p.getIzinJual() == IzinJual.Menunggu || p.getIzinJual() == null) {
                    list.add(p);
                }
            }
        }
        return list;
    }

    // Update Status Verifikasi Penjual
    public void updateStatusVerifikasiPenjual(int idPenjual, StatusVerifikasi status) {
        penjualDAO.updateStatusVerifikasi(idPenjual, status, new Date());
    }

    // Update Izin Jual Produk
    public void updateIzinJualProduk(int idProduk, IzinJual izin) {
        Produk p = cariProduk(idProduk);
        if (p != null) {
            ubahProduk(p.getIdProduk(), p.getPenjualIdPengguna(), p.getNamaProduk(), 
                       p.getDeskripsi(), p.getKategori(), p.getGambarProduk(), 
                       p.getHarga(), p.getBeratGram(), p.getStok(), p.getStatus(), 
                       izin, p.getJumlahShare());
        }
    }
}