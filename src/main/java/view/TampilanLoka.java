/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to edit this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controll.LokaControl;
import model.*;
import enums.*;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class TampilanLoka {
    
    private final LokaControl control;
    private final Scanner scanner;

    public TampilanLoka() {
        this.control = new LokaControl();
        this.scanner = new Scanner(System.in);
    }

    // ==========================================
    // METODE VALIDASI INPUT KETAT (TIDAK BOLEH KOSONG, 0, ATAU MINUS)
    // ==========================================
    
    // Validasi untuk bilangan bulat positif (ID, jumlah, stok, dll)
    private int inputIntValid(String pesan) {
        int nilai;
        while (true) {
            System.out.print(pesan);
            if (scanner.hasNextInt()) {
                nilai = scanner.nextInt();
                scanner.nextLine();
                if (nilai > 0) {
                    return nilai;
                } else {
                    System.out.println("Validasi Error: Nilai tidak boleh 0 atau minus.");
                }
            } else {
                System.out.println("Validasi Error: Masukkan angka bulat yang valid.");
                scanner.next();
            }
        }
    }

    // Validasi untuk bilangan desimal positif (harga, saldo, jarak, ongkir, dll)
    private double inputDoubleValid(String pesan) {
        double nilai;
        while (true) {
            System.out.print(pesan);
            if (scanner.hasNextDouble()) {
                nilai = scanner.nextDouble();
                scanner.nextLine();
                if (nilai > 0) {
                    return nilai;
                } else {
                    System.out.println("Validasi Error: Nilai tidak boleh 0 atau minus.");
                }
            } else {
                System.out.println("Validasi Error: Masukkan angka desimal yang valid.");
                scanner.next();
            }
        }
    }

    // Validasi khusus rating (harus di antara 1 sampai 5)
    private double inputRatingValid(String pesan) {
        double nilai;
        while (true) {
            System.out.print(pesan);
            if (scanner.hasNextDouble()) {
                nilai = scanner.nextDouble();
                scanner.nextLine();
                if (nilai >= 1.0 && nilai <= 5.0) {
                    return nilai;
                } else {
                    System.out.println("Validasi Error: Rating harus berada di antara 1.0 sampai 5.0.");
                }
            } else {
                System.out.println("Validasi Error: Masukkan angka rating yang valid.");
                scanner.next();
            }
        }
    }

    // Validasi teks agar tidak boleh kosong atau spasi doang
    private String inputStringValid(String pesan) {
        String teks;
        while (true) {
            System.out.print(pesan);
            teks = scanner.nextLine().trim();
            if (!teks.isEmpty()) {
                return teks;
            } else {
                System.out.println("Validasi Error: Input tidak boleh kosong.");
            }
        }
    }

    public void tampilkanMenuUtama() {
        int pilihan = 0;
        do {
            System.out.println("\n==========================================");
            System.out.println("    PLATFORM MARKETPLACE DIGITAL LOKA     ");
            System.out.println("==========================================");
            System.out.println("Pilih Role Akses:");
            System.out.println("1. Menu Pembeli");
            System.out.println("2. Menu Penjual");
            System.out.println("3. Menu Pengelola");
            System.out.println("4. Keluar Aplikasi");
            
            pilihan = inputIntValid("Pilih menu (1-4): ");
            
            switch (pilihan) {
                case 1:
                    menuPembeli();
                    break;
                case 2:
                    menuPenjual();
                    break;
                case 3:
                    menuPengelola();
                    break;
                case 4:
                    System.out.println("Keluar dari program. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 4);
    }

    // ==========================================
    // 1. MENU UTAMA PEMBELI
    // ==========================================
    private void menuPembeli() {
        int pilih;
        do {
            System.out.println("\n--- MENU UTAMA PEMBELI ---");
            System.out.println("1. Cari & Lihat Katalog Produk");
            System.out.println("2. Kelola Keranjang Belanja");
            System.out.println("3. Kelola Profil & Pendaftaran Akun Pembeli");
            System.out.println("4. Membeli Barang & Melakukan Pembayaran");
            System.out.println("5. Melacak Status Pengiriman");
            System.out.println("6. Memberikan Rating & Ulasan Produk");
            System.out.println("7. Mengajukan Pengembalian (Retur)");
            System.out.println("8. Kelola Saldo (Top Up)");
            System.out.println("9. Kirim & Balas Pesan (Chat)");
            System.out.println("10. Kembali ke Menu Utama");
            
            pilih = inputIntValid("Pilih opsi Pembeli (1-10): ");
            
            switch (pilih) {
                case 1:
                    tampilkanDataProduk();
                    break;
                case 2:
                    interaksiKelolaKeranjang();
                    break;
                case 3:
                    interaksiKelolaProfilPembeli();
                    break;
                case 4:
                    interaksiBuatPesanan();
                    break;
                case 5:
                    tampilkanStatusPengiriman();
                    break;
                case 6:
                    interaksiBeriUlasan();
                    break;
                case 7:
                    interaksiAjukanPengembalian();
                    break;
                case 8:
                    interaksiTopUpSaldo();
                    break;
                case 9:
                    interaksiKirimPesan();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (true);
    }

    // ==========================================
    // 2. MENU UTAMA PENJUAL
    // ==========================================
    private void menuPenjual() {
        int pilih;
        do {
            System.out.println("\n--- MENU UTAMA PENJUAL ---");
            System.out.println("1. Kelola Profil Toko");
            System.out.println("2. Kelola Produk");
            System.out.println("3. Kelola Pesanan Masuk");
            System.out.println("4. Lihat Laporan Penjualan");
            System.out.println("5. Menangani Pengembalian (Retur)");
            System.out.println("6. Kirim & Balas Pesan");
            System.out.println("7. Kembali ke Menu Utama");
            
            pilih = inputIntValid("Pilih opsi Penjual (1-7): ");
            
            switch (pilih) {
                case 1:
                    interaksiKelolaProfilPenjual();
                    break;
                case 2:
                    interaksiKelolaProdukPenjual();
                    break;
                case 3:
                    interaksiKelolaPesananPenjual();
                    break;
                case 4:
                    System.out.println("\n--- LAPORAN PENJUALAN ---");
                    tampilkanDataProduk();
                    break;
                case 5:
                    interaksiKelolaReturPenjual();
                    break;
                case 6:
                    interaksiKirimPesan();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (true);
    }

    // ==========================================
    // 3. MENU PENGELOLA
    // ==========================================
    private void menuPengelola() {
        int subPeran = 0;
        do {
            System.out.println("\n--- PILIH SUB-PERAN PENGELOLA ---");
            System.out.println("1. Masuk sebagai Verifikator");
            System.out.println("2. Masuk sebagai Admin / Pengelola Layanan");
            System.out.println("3. Kembali ke Menu Utama");
            
            subPeran = inputIntValid("Pilih sub-peran (1-3): ");
            
            switch (subPeran) {
                case 1:
                    menuVerifikator();
                    break;
                case 2:
                    menuAdminLayanan();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (true);
    }
    
    private void menuVerifikator() {
        int pilih;
        do {
            System.out.println("\n--- MENU VERIFIKATOR ---");
            System.out.println("1. Verifikasi Pendaftaran Penjual");
            System.out.println("2. Verifikasi Produk Katalog (Setujui / Tolak)"); // Diperbarui agar jelas fungsinya
            System.out.println("3. Kembali");
            
            pilih = inputIntValid("Pilih opsi (1-3): ");
            
            if (pilih == 1) {
                interaksiVerifikasiPenjual();
            } else if (pilih == 2) {
                interaksiVerifikasiProduk(); // Memanggil method verifikasi produk interaktif
            } else if (pilih == 3) {
                return;
            }
        } while (true);
    }

    private void menuAdminLayanan() {
        int pilih;
        do {
            System.out.println("\n--- MENU ADMIN & PENGELOLA LAYANAN ---");
            System.out.println("1. Monitoring Analitik Utama"); 
            System.out.println("2. Monitoring Data Pengembalian (Retur)"); 
            System.out.println("3. Kembali");
            
            pilih = inputIntValid("Pilih opsi (1-3): ");
            
            if (pilih == 1) {
                tampilkanAnalitikUtama(); 
            } else if (pilih == 2) {
                tampilkanDataPengembalian(); 
            } else if (pilih == 3) {
                return;
            }
        } while (true);
    }

    // ==========================================
    // INTERAKSI AKSI PEMBELI
    // ==========================================
    private void interaksiKelolaKeranjang() {
            System.out.println("\n--- KELOLA KERANJANG BELANJA ---");
            System.out.println("1. Lihat Daftar Keranjang");
            System.out.println("2. Tambah Produk ke Keranjang");

            int aksi = inputIntValid("Pilih aksi (1-2): ");

            if (aksi == 1) {
                tampilkanDataKeranjang();
            } else if (aksi == 2) {
                int idPB = inputIntValid("Masukkan ID Pembeli: ");
                int jml = inputIntValid("Masukkan Jumlah Produk: ");
                int idPrd = inputIntValid("Masukkan ID Produk: ");
                int idPenj = inputIntValid("Masukkan ID Penjual Produk: ");

                control.tambahKeranjang(0, idPB, jml, new Date(), idPrd, idPenj);
                System.out.println("Berhasil memasukkan produk ke keranjang.");
            }
        }

    private void interaksiKelolaProfilPembeli() {
        System.out.println("\n--- KELOLA PROFIL / AKUN PEMBELI ---");
        System.out.println("1. Lihat Profil Pembeli");
        System.out.println("2. Daftar Akun Pembeli Baru");
        
        int aksi = inputIntValid("Pilih aksi (1-2): ");

        if (aksi == 1) {
            tampilkanDataPembeli();
        } else if (aksi == 2) {
            String nama = inputStringValid("Nama Lengkap: ");
            String email = inputStringValid("Email: ");
            String pass = inputStringValid("Password: ");
            String telp = inputStringValid("No Telepon: ");
            
            int jkChoice = inputIntValid("Jenis Kelamin (1. L | 2. P): ");
            JenisKelamin jk = (jkChoice == 1) ? JenisKelamin.L : JenisKelamin.P;

            String kota = inputStringValid("Kota: ");
            String kec = inputStringValid("Kecamatan: ");
            String pos = inputStringValid("Kode Pos: ");
            String alamat = inputStringValid("Alamat Pengiriman: ");
            double saldo = inputDoubleValid("Saldo Awal (Rp): ");
            String pin = inputStringValid("PIN: ");

            control.registerPembeli(0, nama, email, pass, telp, new Date(), jk, 
                                    kota, kec, pos, alamat, saldo, pin, StatusAkun.Aktif, new Date());
            System.out.println("Akun Pembeli berhasil didaftarkan.");
        }
    }

    private void interaksiBuatPesanan() {
        System.out.println("\n--- MEMBELI BARANG & PEMBAYARAN ---");
        int noPes = inputIntValid("No Pesanan: ");
        int idPemb = inputIntValid("ID Pembeli: ");
        int idPrd = inputIntValid("ID Produk yang dibeli: ");
        int jumlahBeli = inputIntValid("Jumlah beli: ");
        double jarak = inputDoubleValid("Jarak pengiriman (Km): ");

        List<Produk> listProduk = control.ambilSemuaProduk();
        Produk produkPilihan = null;
        for (Produk p : listProduk) {
            if (p.getIdProduk() == idPrd) {
                produkPilihan = p;
                break;
            }
        }

        if (produkPilihan == null) {
            System.out.println("Gagal: ID Produk tidak ditemukan di katalog!");
            return;
        }

        int idPenj = produkPilihan.getPenjualIdPengguna(); 
        double hargaSatuan = produkPilihan.getHarga();
        
        double totalBelanja = hargaSatuan * jumlahBeli;
        
        double tarifPerKm = 5000.0; 
        double ongkir = jarak * tarifPerKm;
        
        double totalBayar = totalBelanja + ongkir;

        System.out.println("\n--- RINCIAN PEMBAYARAN OTOMATIS ---");
        System.out.println("Nama Produk  : " + produkPilihan.getNamaProduk());
        System.out.println("Harga Satuan : Rp " + hargaSatuan);
        System.out.println("Total Belanja: Rp " + totalBelanja);
        System.out.println("Ongkir (" + jarak + " km): Rp " + ongkir);
        System.out.println("Total Bayar  : Rp " + totalBayar);

        control.buatPesanan(noPes, idPenj, idPemb, new Date(), jarak, ongkir, totalBayar, StatusPesanan.Diproses, null, null);
        System.out.println("Pesanan berhasil dibuat dengan perhitungan otomatis!");
    }

    private void interaksiBeriUlasan() {
        System.out.println("\n--- BERIKAN RATING & ULASAN ---");
        int idPemb = inputIntValid("ID Pembeli: ");
        String kom = inputStringValid("Komentar: ");
        double rating = inputRatingValid("Rating (1.0 - 5.0): ");
        int idProd = inputIntValid("ID Produk: ");
        int idPenj = inputIntValid("ID Penjual: ");

        control.beriUlasan(0, idPemb, kom, rating, new Date(), idProd, idPenj);
        System.out.println("Ulasan berhasil dikirim.");
    }

    private void interaksiAjukanPengembalian() {
        System.out.println("\n--- AJUKAN PENGEMBALIAN (RETUR) ---");
        String alasan = inputStringValid("Alasan pengembalian: ");
        double nom = inputDoubleValid("Nominal pengembalian (Rp): ");

        control.ajukanPengembalian(0, alasan, nom, StatusPengembalian.Diproses, new Date());
        System.out.println("Pengajuan retur terkirim.");
    }
    
    private void interaksiTopUpSaldo() {
        System.out.println("\n--- TOP UP SALDO ---");
        int idPemb = inputIntValid("Masukkan ID Pembeli: ");

        List<Pembeli> listPembeli = control.ambilSemuaPembeli();
        Pembeli pembeliDitemukan = null;
        
        for (Pembeli pb : listPembeli) {
            if (pb.getIdPengguna() == idPemb) {
                pembeliDitemukan = pb;
                break;
            }
        }

        if (pembeliDitemukan == null) {
            System.out.println("Gagal: ID Pembeli tidak ditemukan di database!");
            return;
        }

        System.out.println("\n--- INFORMASI AKUN PEMBELI ---");
        System.out.println("Nama Lengkap  : " + pembeliDitemukan.getNamaLengkap());
        System.out.println("Saldo Saat Ini: Rp " + pembeliDitemukan.getSaldo());
        System.out.println("--------------------------------");

        double nom = inputDoubleValid("Masukkan Nominal Top Up (Rp): ");

        control.prosesTopUp(0, idPemb, nom);
        System.out.println("Top up berhasil dicatat.");
    }

    private void interaksiKirimPesan() {
        System.out.println("\n--- KIRIM PESAN (CHAT) ---");
        int idPenj = inputIntValid("ID Penjual Tujuan: ");
        int idPemb = inputIntValid("ID Pembeli Pengirim: ");
        String isi = inputStringValid("Isi Pesan: ");

        control.kirimPesan(0, idPenj, idPemb, isi, new Date(), StatusDibacaPesan.BelumDibaca);
        System.out.println("Pesan terkirim.");
    }

    // ==========================================
    // INTERAKSI AKSI PENJUAL
    // ==========================================
    private void interaksiKelolaProfilPenjual() {
        System.out.println("\n--- KELOLA PROFIL TOKO PENJUAL ---");
        System.out.println("1. Lihat Daftar Toko Penjual");
        System.out.println("2. Daftar Penjual / Toko Baru");
        
        int op = inputIntValid("Pilih opsi (1-2): ");

        if (op == 1) {
            tampilkanDataPenjual();
        } else if (op == 2) {
            String nama = inputStringValid("Nama Lengkap Pemilik: ");
            String email = inputStringValid("Email: ");
            String pass = inputStringValid("Password: ");
            String telp = inputStringValid("No Telepon: ");
            String namaToko = inputStringValid("Nama Toko: ");
            String desc = inputStringValid("Deskripsi Toko: ");
            String alamat = inputStringValid("Alamat Toko: ");
            String kota = inputStringValid("Kota: ");
            String kec = inputStringValid("Kecamatan: ");
            String pos = inputStringValid("Kode Pos: ");
            double saldo = inputDoubleValid("Saldo Toko: ");
            String noIzin = inputStringValid("No Izin Usaha: ");

            control.registerPenjual(0, nama, email, pass, telp, namaToko, desc, alamat, kota, kec, pos, saldo, noIzin, StatusVerifikasi.BelumDiverifikasi, null, StatusAkun.Aktif, new Date());
            System.out.println("Toko penjual berhasil didaftarkan.");
        }
    }

    private void interaksiKelolaProdukPenjual() {
        System.out.println("\n--- KELOLA KATALOG PRODUK ---");
        System.out.println("1. Lihat Katalog Produk");
        System.out.println("2. Tambah Produk Baru");
        System.out.println("3. Ubah Produk");
        System.out.println("4. Hapus Produk");
        
        int op = inputIntValid("Pilih opsi (1-4): ");

        if (op == 1) {
            tampilkanDataProduk();
        } else if (op == 2) {
            int idPenj = inputIntValid("ID Penjual: ");
            String nama = inputStringValid("Nama Produk: ");
            String desc = inputStringValid("Deskripsi: ");
            String kat = inputStringValid("Kategori: ");
            String img = inputStringValid("Gambar (Path): ");
            double hrg = inputDoubleValid("Harga (Rp): ");
            int brt = inputIntValid("Berat (Gram): ");
            int stk = inputIntValid("Stok: ");
            int shr = inputIntValid("Jumlah Share: ");

            control.tambahProduk(0, idPenj, nama, desc, kat, img, hrg, brt, stk, StatusProduk.Aman, IzinJual.Diizinkan, shr, new Date());
            System.out.println("Produk berhasil ditambahkan.");
        } else if (op == 3) {
            int idPrd = inputIntValid("Masukkan ID Produk yang mau diubah: ");
            int idPenj = inputIntValid("ID Penjual: ");
            String nama = inputStringValid("Nama Produk Baru: ");
            String desc = inputStringValid("Deskripsi Baru: ");
            String kat = inputStringValid("Kategori Baru: ");
            String img = inputStringValid("Gambar Baru: ");
            double hrg = inputDoubleValid("Harga Baru (Rp): ");
            int brt = inputIntValid("Berat Baru (Gram): ");
            int stk = inputIntValid("Stok Baru: ");
            int shr = inputIntValid("Jumlah Share: ");

            control.ubahProduk(idPrd, idPenj, nama, desc, kat, img, hrg, brt, stk, StatusProduk.Aman, IzinJual.Diizinkan, shr);
            System.out.println("Produk berhasil diperbarui.");
        } else if (op == 4) {
            int idPrd = inputIntValid("Masukkan ID Produk yang akan dihapus: ");
            control.hapusProduk(idPrd);
            System.out.println("Produk berhasil dihapus.");
        }
    }

    private void interaksiKelolaPesananPenjual() {
        System.out.println("\n--- KELOLA PESANAN MASUK ---");
        System.out.println("1. Lihat Semua Pesanan Masuk");
        System.out.println("2. Update Status Pesanan");
        
        int op = inputIntValid("Pilih opsi (1-2): ");

        if (op == 1) {
            tampilkanDataPesanan();
        } else if (op == 2) {
            int noPes = inputIntValid("Masukkan No Pesanan yang ingin diupdate: ");
            System.out.println("Pilih Status Baru: 1. Dikirim | 2. Selesai");
            int st = inputIntValid("Pilih status (1-2): ");

            StatusPesanan statusP = (st == 1) ? StatusPesanan.Dikirim : StatusPesanan.Selesai;
            control.updateStatusPesanan(noPes, statusP, new Date(), (st == 2 ? new Date() : null));
            System.out.println("Status pesanan berhasil diperbarui.");
        }
    }

    private void interaksiKelolaReturPenjual() {
        System.out.println("\n--- MENANGANI PENGEMBALIAN (RETUR) ---");
        tampilkanDataPengembalian();
        int idRet = inputIntValid("Masukkan ID Pengembalian yang ingin diproses: ");
        System.out.println("Pilih Status Retur: 1. Disetujui | 2. Ditolak");
        int st = inputIntValid("Pilih status (1-2): ");

        StatusPengembalian statusR = (st == 1) ? StatusPengembalian.PengembalianDisetujui : StatusPengembalian.PengembalianDitolak;
        control.updateStatusPengembalian(idRet, statusR);
        System.out.println("Status pengembalian berhasil diperbarui.");
    }

    // ==========================================
    // INTERAKSI AKSI PENGELOLA (VERIFIKATOR & ADMIN)
    // ==========================================
    private void interaksiVerifikasiPenjual() {
        tampilkanDataPenjual();
        int idPenj = inputIntValid("Masukkan ID Pengguna Penjual yang ingin diverifikasi: ");
        System.out.println("Pilih Status Verifikasi: 1. Terverifikasi | 2. Belum Diverifikasi");
        int st = inputIntValid("Pilih status (1-2): ");

        StatusVerifikasi verif = (st == 1) ? StatusVerifikasi.Terverifikasi : StatusVerifikasi.BelumDiverifikasi;
        control.verifikasiPenjual(idPenj, verif, new Date());
        System.out.println("Status verifikasi toko penjual berhasil diperbarui.");
    }

    private void interaksiVerifikasiProduk() {
        tampilkanDataProduk();
        int idPrd = inputIntValid("Masukkan ID Produk yang ingin diverifikasi: ");
        System.out.println("Pilih Status Izin Jual Produk: 1. Diizinkan (Disetujui) | 2. Ditolak / Non-aktif");
        int st = inputIntValid("Pilih status (1-2): ");

        // Menggunakan IzinJual.Diizinkan atau menyesuaikan status produk/izin yang tersedia
        IzinJual izin = (st == 1) ? IzinJual.Diizinkan : null; 
        
        // Cari produk berdasarkan ID untuk mengambil data pendukung lainnya saat update
        List<Produk> listProduk = control.ambilSemuaProduk();
        Produk target = null;
        for (Produk p : listProduk) {
            if (p.getIdProduk() == idPrd) {
                target = p;
                break;
            }
        }

        if (target != null) {
            control.ubahProduk(target.getIdProduk(), target.getPenjualIdPengguna(), target.getNamaProduk(), 
                               target.getDeskripsi(), target.getKategori(), "", 
                               target.getHarga(), 0, target.getStok(), 
                               target.getStatus(), izin, target.getJumlahShare());
            System.out.println("Status izin jual produk berhasil diperbarui oleh Verifikator.");
        } else {
            System.out.println("Gagal: ID Produk tidak ditemukan.");
        }
    }

    // ==========================================
    // METODE TAMPILAN DATA (READ)
    // ==========================================
    private void tampilkanDataProduk() {
        System.out.println("\n--- DAFTAR KATALOG PRODUK LOKA ---");
        List<Produk> listProduk = control.ambilSemuaProduk();
        if (listProduk.isEmpty()) {
            System.out.println("Belum ada data produk di database.");
        } else {
            for (Produk p : listProduk) {
                System.out.println("ID Produk : " + p.getIdProduk() 
                                   + " | Nama: " + p.getNamaProduk() 
                                   + " | Harga: Rp " + p.getHarga() 
                                   + " | Stok: " + p.getStok()
                                   + " | Status: " + p.getStatus()
                                   + " | Izin: " + p.getIzinJual());
            }
        }
    }

    private void tampilkanDataKeranjang() {
        System.out.println("\n--- DAFTAR KERANJANG BELANJA ---");
        List<Keranjang> listKeranjang = control.ambilSemuaKeranjang();
        if (listKeranjang.isEmpty()) {
            System.out.println("Keranjang belanja masih kosong.");
        } else {
            for (Keranjang k : listKeranjang) {
                System.out.println("ID Keranjang: " + k.getIdKeranjang() 
                                   + " | ID Pembeli: " + k.getPembeliIdPengguna() 
                                   + " | ID Produk: " + k.getProdukIdProduk() 
                                   + " | Jumlah: " + k.getJumlah());
            }
        }
    }

    private void tampilkanDataPembeli() {
        System.out.println("\n--- PROFIL & AKUN PEMBELI TERDAFTAR ---");
        List<Pembeli> listPembeli = control.ambilSemuaPembeli();
        if (listPembeli.isEmpty()) {
            System.out.println("Belum ada data pembeli terdaftar.");
        } else {
            for (Pembeli pb : listPembeli) {
                System.out.println("ID: " + pb.getIdPengguna() 
                                   + " | Nama: " + pb.getNamaLengkap() 
                                   + " | Kota: " + pb.getKota() 
                                   + " | Saldo: Rp " + pb.getSaldo());
            }
        }
    }

    private void tampilkanDataPesanan() {
        System.out.println("\n--- DAFTAR TRANSAKSI & PESANAN ---");
        List<Pesanan> listPesanan = control.ambilSemuaPesanan();
        if (listPesanan.isEmpty()) {
            System.out.println("Belum ada data pesanan.");
        } else {
            for (Pesanan ps : listPesanan) {
                System.out.println("No Pesanan: " + ps.getNoPesanan() 
                                   + " | Total Bayar: Rp " + ps.getTotalBayar() 
                                   + " | Status: " + ps.getStatusPesanan());
            }
        }
    }

    private void tampilkanStatusPengiriman() {
        System.out.println("\n--- PELACAKAN STATUS PENGIRIMAN ---");
        List<Pesanan> listPesanan = control.ambilSemuaPesanan();
        if (listPesanan.isEmpty()) {
            System.out.println("Belum ada riwayat pengiriman.");
        } else {
            for (Pesanan ps : listPesanan) {
                System.out.println("No Pesanan: " + ps.getNoPesanan() 
                                   + " | Status: " + ps.getStatusPesanan());
            }
        }
    }

    private void tampilkanDataPenjual() {
        System.out.println("\n--- DAFTAR PENJUAL TERDAFTAR ---");
        List<Penjual> listPenjual = control.ambilSemuaPenjual();
        if (listPenjual.isEmpty()) {
            System.out.println("Belum ada data penjual.");
        } else {
            for (Penjual p : listPenjual) {
                System.out.println("ID: " + p.getIdPengguna() 
                                   + " | Toko: " + p.getNamaToko() 
                                   + " | Verifikasi: " + p.getStatusVerifikasi());
            }
        }
    }

    // Method Analitik Utama disesuaikan persis dengan Flowchart Admin
    private void tampilkanAnalitikUtama() {
        boolean ulangAnalitik = true;
        while (ulangAnalitik) {
            System.out.println("\n--- MONITORING ANALITIK UTAMA ---");
            String periode = inputStringValid("Pilih periode laporan (Contoh: 2026-11 atau Bulanan): ");

            List<Pesanan> listPesanan = control.ambilSemuaPesanan();
            
            boolean dataAda = false;
            double totalPendapatanPeriode = 0;
            int jumlahTransaksiPeriode = 0;

            for (Pesanan p : listPesanan) {
                if (p.getTanggalPesanan() != null && p.getTanggalPesanan().toString().contains(periode)) {
                    dataAda = true;
                    totalPendapatanPeriode += p.getTotalBayar();
                    jumlahTransaksiPeriode++;
                }
            }
            
            if (!listPesanan.isEmpty() && (dataAda || periode.equalsIgnoreCase("semua"))) {
                System.out.println("\n[Berhasil] Data tersedia pada periode tersebut.");
                System.out.println("========================================");
                System.out.println("--- GRAFIK / LAPORAN ANALITIK UTAMA ---");
                System.out.println("Periode Laporan       : " + periode);
                System.out.println("Total Transaksi       : " + (periode.equalsIgnoreCase("semua") ? listPesanan.size() : jumlahTransaksiPeriode));
                System.out.println("Total Pendapatan      : Rp " + (periode.equalsIgnoreCase("semua") ? hitungTotalSemua(listPesanan) : totalPendapatanPeriode));
                System.out.println("Status Sistem LOKA    : Stabil & Optimal");
                System.out.println("========================================");
            } else {
                System.out.println("\n[Informasi] Tampilan data kosong. Tidak ada data transaksi pada periode " + periode + ".");
            }

            String jawab = inputStringValid("Apakah admin ingin monitoring analitik utama lagi? (y/t): ");
            if (jawab.equalsIgnoreCase("t") || jawab.equalsIgnoreCase("tidak")) {
                ulangAnalitik = false;
                System.out.println("Kembali ke Halaman Utama Pengelola...");
            }
        }
    }

    private double hitungTotalSemua(List<Pesanan> list) {
        double total = 0;
        for (Pesanan p : list) {
            total += p.getTotalBayar();
        }
        return total;
    }

    private void tampilkanDataPengembalian() {
        System.out.println("\n--- MONITORING DATA PENGEMBALIAN (RETUR) ---");
        List<Pengembalian> listPengembalian = control.ambilSemuaPengembalian();
        if (listPengembalian.isEmpty()) {
            System.out.println("Belum ada data pengembalian.");
        } else {
            for (Pengembalian pg : listPengembalian) {
                System.out.println("ID Retur: " + pg.getIdPengembalian() 
                                   + " | Alasan: " + pg.getAlasanPengembalian() 
                                   + " | Status: " + pg.getStatusPengembalian());
            }
        }
    }
}