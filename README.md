<div align="center">

# 🛍️🏪 LOKA
### Lapak Online Komunitas Lokal
<img width="400" height="400" alt="LOGO LOKA" src="https://github.com/user-attachments/assets/77946f2b-7084-4b8c-9f11-523481071734" />

*Marketplace digital untuk memberdayakan UMKM lokal di Kota Samarinda*

</div>

---

## Deskripsi Singkat ─ ★ ˙ ̟ !!

LOKA merupakan sebuah platform marketplace digital yang didedikasikan secara eksklusif untuk pemberdayaan Usaha Mikro, Kecil, dan Menengah (UMKM) lokal di wilayah Kota Samarinda. Platform ini dirancang untuk mengatasi keterbatasan jangkauan pemasaran dan mengelola operasional bisnis secara terstruktur melalui sistem digital. Dengan fitur dasbor penjual, pencarian katalog produk, integrasi pembayaran digital, hingga kalkulasi ongkos kirim berdasarkan jarak, LOKA memfasilitasi pertemuan antara penjual dan pembeli secara efisien.

Pengembangan sistem ini sejalan dengan komitmen terhadap *Sustainable Development Goals* (SDGs):

| SDG | Poin |
|:---:|------|
| 8 | Pekerjaan Layak & Pertumbuhan Ekonomi |
| 9 | Industri, Inovasi, & Infrastruktur |
| 12 | Konsumsi & Produksi yang Bertanggung Jawab |

---

## Fitur Utama Program ─ ★ ˙ ̟ !!

### 1. Autentikasi & Manajemen Hak Akses

Sistem ini mengimplementasikan keamanan berlapis dengan membagi pengguna ke dalam 3 entitas (aktor) utama yang memiliki hak akses (*privileges*) berbeda:

| Aktor | Peran | Hak Akses |
|-------|-------|-----------|
| **Pengelola** (Admin & Verifikator) | Pengawas ekosistem | Verifikator memverifikasi pendaftaran toko UMKM baru dan menyetujui/menolak izin jual produk. Admin melihat analitik laporan pendapatan (total bayar) seluruh transaksi berdasarkan periode waktu tertentu. |
| **Penjual** (UMKM) | Pemilik toko | Dasbor khusus untuk mendaftarkan nama toko & nomor izin usaha, menambahkan produk ke katalog, memperbarui stok, dan menangani pesanan masuk (status "Diproses", "Dikirim", "Selesai", "Pengembalian", "Dikembalikan"). |
| **Pembeli** | Konsumen | Akses penuh ke etalase produk, menambahkan barang ke keranjang, mengisi saldo dompet digital (Top-Up), dan melacak status barang yang dibeli. |

### 2. Sistem Transaksi Cerdas & Kalkulasi Logistik

Proses pemesanan barang (*checkout*) tidak dilakukan secara manual, melainkan diotomatisasi penuh oleh logika sistem:

- **Kalkulasi Ongkos Kirim Dinamis**
  Sistem otomatis mengalkulasi biaya kirim berdasarkan input jarak (km). Tarif yang ditetapkan adalah **Rp4.000 per kilometer** (jarak dibulatkan ke atas), dengan batas tarif ongkos kirim minimal sebesar **Rp8.000**.

- **Validasi End-to-End**
  Sebelum transaksi dicatat, sistem mengecek:
  1. Apakah stok produk mencukupi jumlah yang ingin dibeli?
  2. Apakah saldo LOKA-Pay pembeli cukup untuk membayar total belanja + ongkos kirim?

- **Sinkronisasi Otomatis**
  Apabila validasi berhasil, sistem langsung membuat data `Pesanan` dan `DetailPesanan`, lalu secara *real-time* memotong saldo pembeli dan mengurangi ketersediaan stok produk di database penjual.

### 3. Manajemen Data Interaktif Terpusat (CRUD System)

Sistem memberikan keleluasaan bagi pengguna untuk memanipulasi data operasional (*Create, Read, Update, Delete*) sesuai porsinya:

- **Manajemen Katalog (Penjual)**
  UMKM dapat mendaftarkan produk baru (Create), melihat daftar produk mereka (Read), mengubah harga/deskripsi/gambar/stok produk (Update), serta menghapus produk yang sudah tidak dijual (Delete).

- **Manajemen Keranjang (Pembeli)**
  Pembeli dapat menyimpan barang di keranjang, menelusuri isi keranjang, dan menghapus barang jika tidak jadi membeli. Setelah *checkout* dari keranjang berhasil, sistem otomatis membersihkan (Delete) produk tersebut dari keranjang pembeli.

### 4. Layanan Pasca-Pembelian (Pengembalian & Ulasan)

Untuk menjaga kepercayaan dan transparansi ekosistem jual-beli lokal:

- **Sistem Pengembalian**
  Jika barang rusak atau tidak sesuai, pembeli dapat mengajukan form retur yang berisi alasan komplain dan nominal dana yang diminta. Status pengembalian awalnya adalah "Diproses", kemudian penjual berwenang memperbarui statusnya menjadi "Pengembalian Disetuju" atau "Pengembalian Ditolak".

- **Rating & Ulasan**
  Setelah pesanan selesai, pembeli dapat memberikan umpan balik publik berupa teks komentar dan *rating* (skala divalidasi antara 1.0 hingga 5.0 bintang) untuk membangun reputasi toko UMKM terkait.

### 5. Komunikasi & Pembayaran Internal

- **Pesan Pribadi**
  Modul pertukaran pesan yang memungkinkan pembeli berkonsultasi langsung dengan penjual. Sistem mencatat waktu pengiriman dan melacak status pesan (Sudah Dibaca / Belum Dibaca).

- **Top-Up LOKA-Pay**
  Dompet digital internal tempat pembeli mengisi saldo (diinput dan divalidasi tidak boleh minus). Saldo ini menjadi satu-satunya metode pembayaran sah dan terintegrasi di dalam aplikasi LOKA.

---

## Tech Stack ─ ★ ˙ ̟ !!

| Kategori | Teknologi |
|----------|-----------|
| Bahasa Pemrograman | Java |
| Build Automation Tool | Apache Maven |
| Framework GUI | Java Swing |
| Database | MySQL |
| Library/Driver | MySQL Connector/J (JDBC) |
| Arsitektur Sistem | MVC + DAO Pattern |
| Tools Pengembangan | Apache NetBeans, Laragon, Figma, draw.io, Data Modeler |

---

## Perancangan Sistem ─ ★ ˙ ̟ !!

**ERD Logical:**

<img width="2057" height="1919" alt="LOKA_Logical" src="https://github.com/user-attachments/assets/aeee1712-01e1-466a-9726-e27587923f7e" />

**ERD Relational:**

<img width="5175" height="2707" alt="LOKA_Relational" src="https://github.com/user-attachments/assets/7b246e96-e853-427a-a95a-3ad59ab907ed" />

**Flowchart & Use Case:**

https://drive.google.com/drive/folders/1toUlE2J9xxgxuJ68TF_lEPJt3u2UXHNX?usp=drive_link

---

## Struktur Folder/Package ─ ★ ˙ ̟ !!


---

## Penerapan OOP ─ ★ ˙ ̟ !!

| Pilar | Penerapan | Lokasi |
|-------|-----------|--------|
| Encapsulation | Atribut `private` + setter dengan validasi | `Pengguna`, `Pembeli`, `Penjual`, `Pengelola` |
| Inheritance | `Pembeli`, `Penjual`, `Pengelola` mewarisi `Pengguna` | package `model` |
| Abstraction | *Abstract class* `Pengguna` dan tipe data `enum` | `Pengguna`, package `enums` |
| Polymorphism | *Method overriding* `getInfoDasbor()` dan *method overloading* `ambilSemuaProduk()` | `Pengguna` dan subclass, `LokaControl` |

### Encapsulation

Encapsulation adalah proses menyembunyikan data (atribut) melalui modifikator `private` dan hanya memperbolehkan akses melalui method khusus (getter dan setter). Pada LOKA, konsep ini diterapkan dengan menambahkan validasi di dalam setter (misalnya memastikan format email memiliki `@` atau saldo tidak boleh minus). Konstruktor juga memanggil setter, sehingga data selalu tervalidasi sejak objek dibuat dan integritas data terjaga sebelum diproses oleh sistem.

**Class `Pengguna`**: atribut `private`, setter memanggil validasi sebelum menyimpan nilai.

```java
public abstract class Pengguna {
    private String email;
    private String password;
    // ...

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (validasiEmail(email)) {
            this.email = email;
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
}
```

**Class `Pembeli`**: validasi saldo agar tidak bernilai minus.

```java
public class Pembeli extends Pengguna {
    private double saldo;
    // ...

    public double getSaldo() { return saldo; }

    public void setSaldo(double saldo) {
        if (validasiSaldoPembeli(saldo)) {
            this.saldo = saldo;
        }
    }

    public boolean validasiSaldoPembeli(double saldo) {
        if (saldo >= 0) {
            return true;
        } else {
            System.out.println("Peringatan: Saldo pembeli tidak boleh bernilai minus!");
            return false;
        }
    }
}
```

### Inheritance

Inheritance (Pewarisan) digunakan untuk mencegah duplikasi kode dengan mewariskan atribut dan method dasar dari *super-class* `Pengguna` (ID, nama, email, password, nomor telepon, dan role) kepada tiga *sub-class* spesifik: `Penjual`, `Pembeli`, dan `Pengelola`. Melalui pemanggilan konstruktor `super(...)` di dalam konstruktor masing-masing, *sub-class* menginisialisasi identitas dasarnya ke class induk sekaligus menambahkan atribut eksklusifnya sendiri, seperti `namaToko` untuk Penjual, `alamatPengiriman` untuk Pembeli, dan `posisi` (Admin/Verifikator) untuk Pengelola. Karena atribut induk bersifat `private`, *sub-class* mengaksesnya melalui getter, misalnya `getNamaLengkap()`.

#### Class Penjual

Class ini mewarisi atribut identitas dasar dari `Pengguna`, namun mendefinisikan atribut yang secara eksklusif dimiliki entitas bisnis, yaitu `namaToko`, `noIzinUsaha`, dan `saldo` operasional toko.

```java
public class Penjual extends Pengguna {
    private String namaToko;
    private String noIzinUsaha;
    private double saldo;
    // ...

    public Penjual(int idPengguna, String namaLengkap, String email, String password,
                   String noTelpon, RolePengguna role, String namaToko, String deskripsiToko,
                   String alamatToko, String kota, String kecamatan, String kodePos,
                   double saldo, String noIzinUsaha, StatusVerifikasi statusVerifikasi,
                   Date tanggalVerifikasi, StatusAkun statusAkun, Date tanggalDaftar) {

        super(idPengguna, namaLengkap, email, password, noTelpon, role); // atribut warisan dari Pengguna

        setNamaToko(namaToko);            // atribut khusus Penjual
        setSaldo(saldo);
        setNoIzinUsaha(noIzinUsaha);
        // ...
    }
}
```

#### Class Pembeli

Sama halnya dengan Penjual, `Pembeli` menggunakan `extends Pengguna`. Namun, alih-alih atribut bisnis, ia menambahkan atribut yang mendukung kegiatan belanja, seperti `alamatPengiriman`, saldo LOKA-Pay pribadi, dan `pin` keamanan transaksi.

```java
public class Pembeli extends Pengguna {
    private String alamatPengiriman;
    private double saldo;
    private String pin;
    // ...

    public Pembeli(int idPengguna, String namaLengkap, String email, String password,
                   String noTelpon, RolePengguna role, Date tanggalLahir,
                   JenisKelamin jenisKelamin, String kota, String kecamatan, String kodePos,
                   String alamatPengiriman, double saldo, String pin,
                   StatusAkun statusAkun, Date tanggalDaftar) {

        super(idPengguna, namaLengkap, email, password, noTelpon, role); // atribut warisan dari Pengguna

        this.alamatPengiriman = alamatPengiriman;   // atribut khusus Pembeli
        setSaldo(saldo);
        setPin(pin);
        // ...
    }
}
```

#### Class Pengelola (Admin/Verifikator)

Berbeda dengan Pembeli atau Penjual yang terlibat langsung dalam transaksi komersial, `Pengelola` didesain untuk manajemen sistem di balik layar. Setelah mewarisi kredensial dasar dari `Pengguna`, class ini menambahkan satu atribut otorisasi, yaitu `posisi` (tipe `enum PosisiPengelola`), yang membedakan wewenang Admin (pemantau analitik) dan Verifikator (pemberi validasi).

```java
public class Pengelola extends Pengguna {
    private PosisiPengelola posisi;   // atribut khusus Pengelola

    public Pengelola(int idPengguna, String namaLengkap, String email, String password,
                     String noTelpon, RolePengguna role, PosisiPengelola posisi) {

        super(idPengguna, namaLengkap, email, password, noTelpon, role); // atribut warisan dari Pengguna
        setPosisi(posisi);
    }
}
```

### Abstraction

Abstraction (Abstraksi) menyembunyikan detail implementasi dan hanya menampilkan kontrak yang esensial. Pada LOKA, abstraksi diterapkan dengan dua cara:

**1. *Abstract class* `Pengguna`.** `Pengguna` dideklarasikan `abstract` karena tidak ada pengguna yang "hanya pengguna": setiap pengguna pasti berperan sebagai Pembeli, Penjual, atau Pengelola. Class ini mendeklarasikan method abstrak `getInfoDasbor()` yang hanya menetapkan *apa* yang harus ada (informasi dasbor), sedangkan *bagaimana* isinya disusun diserahkan kepada masing-masing *sub-class*.

```java
public abstract class Pengguna {
    // ...

    // method abstrak: hanya kontrak, tanpa isi
    public abstract String getInfoDasbor();
}
```

**2. Tipe data `enum`.** Enum seperti `StatusPesanan` menyembunyikan penggunaan string mentah dan menjadi kontrak konstan yang mencegah kesalahan pengetikan (*human error*), sehingga status operasional di database selalu konsisten.

```java
package enums;

public enum StatusPesanan {
    Diproses,
    Dikirim,
    Selesai,
    Pengembalian,
    Dikembalikan
}
```

Pemakaian pada `LokaControl`, status diberikan lewat enum, bukan string mentah:

```java
buatPesanan(noPesanan, idPenjual, idPembeli, new Date(), jarak, ongkir, total,
            StatusPesanan.Diproses, null, null);
```

### Polymorphism

Polymorphism (Polimorfisme) memungkinkan satu method yang sama menghasilkan perilaku berbeda tergantung objek yang memanggilnya. Pada LOKA, polimorfisme diterapkan melalui **method overriding**: method abstrak `getInfoDasbor()` dari *super-class* `Pengguna` diimplementasikan ulang (`@Override`) oleh `Pembeli`, `Penjual`, dan `Pengelola` sesuai kebutuhan masing-masing aktor.

**Sub-class `Pembeli`**: menampilkan saldo LOKA-Pay.

```java
@Override
public String getInfoDasbor() {
    return "Selamat berbelanja, " + getNamaLengkap()
            + " | Saldo LOKA-Pay: Rp" + String.format("%,.0f", saldo);
}
```

**Sub-class `Penjual`**: menampilkan nama toko dan saldo toko.

```java
@Override
public String getInfoDasbor() {
    return "Toko " + namaToko + " | Saldo toko: Rp" + String.format("%,.0f", saldo);
}
```

**Sub-class `Pengelola`**: menampilkan posisi (Admin/Verifikator).

```java
@Override
public String getInfoDasbor() {
    return "Panel " + posisi + " | " + getNamaLengkap();
}
```

**Pemanfaatan polimorfisme**: method `login()` pada `LokaControl` mengembalikan tipe `Pengguna` (*super-class*), padahal objek aslinya bisa `Pembeli`, `Penjual`, atau `Pengelola`. Hasilnya ditampung dalam variabel bertipe `Pengguna`, lalu satu baris pemanggilan `getInfoDasbor()` otomatis menjalankan versi milik objek aslinya, tanpa pengecekan `instanceof`.

```java
Pengguna user = lokaControl.login(email, password);   // tipe referensi: Pengguna
if (user != null) {
    System.out.println(user.getInfoDasbor());         // versi yang jalan mengikuti objek aslinya
}
```

Selain *overriding*, LOKA juga memakai **method overloading** pada `LokaControl`: `ambilSemuaProduk()` mengambil seluruh produk, sedangkan `ambilSemuaProduk(int idPenjual)` hanya mengambil produk milik satu penjual.

```java
public List<Produk> ambilSemuaProduk() {
    return produkDAO.getAllProduk();
}

public List<Produk> ambilSemuaProduk(int idPenjual) {
    List<Produk> hasil = new ArrayList<>();
    for (Produk p : produkDAO.getAllProduk()) {
        if (p.getPenjualIdPengguna() == idPenjual) {
            hasil.add(p);
        }
    }
    return hasil;
}
```

---

## Cara Penggunaan Program ─ ★ ˙ ̟ !!

1. **Clone Repository**
```bash
   git clone ...
```
 
2. **Persiapan Database MySQL**
   - Jalankan layanan MySQL melalui Laragon (*Mulai Semua*).
   - Buat database baru bernama `loka` di phpMyAdmin.
   - Import file database `loka.sql` ke dalam database `loka`.
3. **Konfigurasi Koneksi Database**
   - Pastikan pengaturan `URL`, `USER`, dan `PASS` di dalam file `src/main/java/config/Koneksi.java` telah sesuai dengan konfigurasi MySQL lokal Anda (default: `jdbc:mysql://localhost:3306/loka`, user: `root`, password: `""`).
4. **Menjalankan Aplikasi**
   - Buka proyek LOKA menggunakan Apache NetBeans IDE.
   - Klik kanan pada proyek, pilih **Clean and Build**.
   - Tekan **Run Project** untuk menjalankan Program.
   
TAROH GUI DISINI YAAAA
<img width="1023" height="764" alt="image" src="https://github.com/user-attachments/assets/79352942-8376-4f02-9f48-3f49a96faa55" />

## ˚.🎀༘⋆ Tinjauan Antarmuka Beranda (Home Screen)

Halaman **Beranda** aplikasi LOKA UMKM dirancang dengan antarmuka yang modern, bersih, dan bertema lokal khas Kota Samarinda untuk memberikan pengalaman pengguna yang intuitif sejak pertama kali aplikasi dibuka.

### 𝄞⨾💿✮˚.⋆ Komponen Utama pada Halaman Beranda:

1. **Header & Navigasi Atas**
   * Menampilkan logo resmi **LOKA.** beserta tombol navigasi cepat seperti *Beranda*, *Tentang Kami*, serta tombol akses utama **Login** dan **Registrasi Akun** untuk memudahkan pengguna masuk ke sistem sesuai hak akses masing-masing.

2. **Banner Utama (Hero Section)**
   * Mengusung slogan *"Platform Resmi UMKM Kota Samarinda"*, bagian ini menyoroti misi utama aplikasi untuk mendukung pengrajin dan pelaku usaha lokal secara langsung dalam memasarkan produk khas seperti Amplang Segar, Batik Mahakam, hingga Kriya Rotan berkualitas tinggi.

3. **Fitur & Keunggulan Platform**
   * Menyediakan informasi singkat mengenai nilai jual aplikasi:
     * **Pusatnya Karya Lokal:** Menghubungkan UMKM terbaik untuk karya terbaik.
     * **100% Autentik:** Menjamin kualitas produk khas yang terverifikasi.
     * **Pengiriman Cepat:** Layanan logistik langsung dari wilayah Samarinda.

4. **Kategori Produk & Katalog Pilihan**
   * Dilengkapi panel navigasi kategori di sisi kiri (*Kuliner*, *Pakaian*, *Kriya*) serta daftar produk unggulan interaktif (seperti *Amplang Tenggiri Super*, *Baju Batik Motif Belanga*, dan *Tas Rotan Anjat Dayak*).
   * Setiap kartu produk menampilkan informasi lengkap mencakup nama produk, asal toko/pengrajin, harga satuan, kategori, serta rating produk secara *real-time*.

<img width="1013" height="671" alt="image" src="https://github.com/user-attachments/assets/09d620cd-3037-4a13-83c7-3d50de057ec8" />

## ✌︎㋡ Tim Pengembang (About Us) - LOKA UMKM

Halaman **Tentang Kami** menyajikan profil para pengembang mahasiswa yang merancang dan membangun platform **LOKA UMKM** untuk Proyek Akhir praktikum Capstone.

### 🎧ྀི♪⋆.✮ Struktur Tim & Peran Pengembang:

1. **Nazmi Ramadani**
   * **Peran:** Ketua
   * **NIM:** 2509116031
   * **Catatan:** Alhamdulillah Waras ( ˶°ㅁ°) !!

2. **Qonitah Khansa' Ayu Madani A.**
   * **Peran:** Anggota
   * **NIM:** 2509116113
   * **Catatan:** Desainer Gacor (˶ˆᗜˆ˵)

3. **Naisyla Auliya Rahmi Putri**
   * **Peran:** Anggota
   * **NIM:** 2509116078
   * **Catatan:** Pacar Martin (๑ᵔ⤙ᵔ๑)

4. **Atha Rina Sarwahita**
   * **Peran:** Anggota
   * **NIM:** 2509116047
   * **Catatan:** Ngopi Everyday (๑>؂•̀๑)

<img width="1013" height="764" alt="image" src="https://github.com/user-attachments/assets/6b284eea-2cca-4fc3-a276-e14a78afb98e" />

## ૮₍ ˶ᵔ ᵕ ᵔ˶ ₎ა Mengenal LOKA (Tentang Kami)

Bagian ini merupakan kelanjutan dari halaman **Tentang Kami**, yang berfokus pada pengenalan identitas, visi, dan nilai tambah dari platform LOKA UMKM.

### Profil & Visi Platform ⋆˚🐾˖°
* **Mengenal LOKA:** Platform ini hadir sebagai "Platform Pemberdayaan UMKM Kota Samarinda". LOKA adalah wadah komprehensif bagi para pelaku Usaha Mikro, Kecil, dan Menengah (UMKM) di Samarinda untuk memamerkan dan memasarkan produk khas daerah secara mudah, transparan, dan terpercaya.
  
* **Visi Kami:** Memiliki visi untuk menjadikan produk UMKM Samarinda sebagai tuan rumah di daerah sendiri serta dikenal luas hingga skala nasional melalui digitalisasi yang inklusif.
  
* **Dampak Ekonomi:** Platform ini menargetkan estimasi nilai transaksi UMKM lokal hingga mencapai angka **Rp 250M+**

### Kenapa Memilih LOKA Samarinda? ( •̯́ ₃ •̯̀)
Terdapat tiga nilai utama (*core values*) yang ditawarkan kepada pengguna:
1. **100% Asli Produk Samarinda:** Semua produk yang ada di platform dikurasi langsung dari pengrajin dan UMKM lokal.
2. **100% Berdampak:** Setiap transaksi Anda langsung mendukung ekonomi keluarga pemilik usaha.
3. **100% Aman & Praktis:** Proses pemesanan berjalan cepat dengan dukungan pembayaran masa kini seperti QRIS dan transfer lokal.

<img width="1028" height="760" alt="image" src="https://github.com/user-attachments/assets/7b1eea31-c628-42b0-96a5-2337a666b509" />

## 📝 Halaman Registrasi Akun LOKA

Halaman **Registrasi** dirancang interaktif untuk memisahkan alur pendaftaran berdasarkan peran pengguna (*role*), memastikan setiap akun yang terdaftar memiliki hak akses dan struktur data yang sesuai di dalam sistem basis data.

### ⋆.˚✮🎧✮˚.⋆ Pilihan Peran Pendaftaran (Role-Based Registration)

1. **Pembeli**
   * **Deskripsi:** Ditujukan bagi pengguna yang ingin berbelanja produk khas Samarinda dan mendukung UMKM.     
   * **Aksi:** Pengguna dapat menekan tombol **"Daftar Sebagai Pembeli"** untuk membuka form registrasi yang memuat data pribadi, alamat pengiriman, serta pengaturan saldo LOKA-Pay.

2. **Penjual (Seller / UMKM)**
   * **Deskripsi:** Ditujukan bagi pelaku usaha untuk mendaftarkan toko UMKM andalan mereka dan memperluas jangkauan pasar.     
   * **Aksi:** Pengguna dapat menekan tombol **"Daftar Sebagai Penjual"** untuk memasukkan informasi toko, izin usaha, serta verifikasi kelayakan produk sebelum masuk ke sistem dasbor penjual.

<img width="1032" height="760" alt="image" src="https://github.com/user-attachments/assets/d1d36b11-4a1b-48fd-98cd-6caa127c5153" />

## 🔐 Halaman Login & Autentikasi Akun

Halaman **Login Akun LOKA** berfungsi sebagai gerbang utama bagi pengguna terdaftar (baik Pembeli, Penjual, maupun Pengelola) untuk masuk ke dalam sistem aplikasi.

### .✦ ݁˖ Fungsionalitas & Alur Autentikasi:
* **Input Kredensial:** Pengguna memasukkan alamat *Email* dan *Password* yang sesuai dengan data saat pendaftaran.
  
* **Validasi Sistem:** Tombol **"Masuk"** memicu fungsi kontrol untuk mencocokkan data masukan dengan basis data, termasuk memeriksa status keaktifan akun.
  
* **Notifikasi Berhasil:** Jika kredensial valid, sistem akan menampilkan jendela dialog pop-up bertuliskan *"Login Berhasil! Selamat datang, [Nama Pengguna]"* (seperti pada contoh *Rina Kartika*), sebelum mengarahkan pengguna ke dasbor yang sesuai.
  
* **Navigasi Pendaftaran:** Bagi pengguna yang belum memiliki akun, tersedia tautan dan tombol **"Registrasi di Sini"** untuk beralih langsung ke halaman pendaftaran.

<img width="1046" height="754" alt="image" src="https://github.com/user-attachments/assets/57fda699-b4c5-4f33-8fe1-0cb40d7c0df8" />

## ͙͘͡★ Dashboard Pembeli (LOKA Pembeli)

Setelah berhasil melakukan masuk (*login*), pengguna dengan peran sebagai pembeli akan diarahkan ke halaman **Dashboard LOKA Pembeli** yang menyediakan berbagai fitur belanja interaktif dan kemudahan akses produk khas Samarinda.

### ✮⋆˙ Fitur Utama pada Dashboard Pembeli:
* **Sapaan & Informasi Akun:** Menampilkan sapaan personal kepada pembeli (contoh: *"Halo, Rina Kartika!"*) beserta informasi saldo aktif *(Saldo: Rp 750.000)* dan tombol **Top Up** untuk pengisian saldo.
  
* **Pencarian Produk:** Dilengkapi kolom pencarian interaktif (*"Cari produk khas Samarinda...."*) yang terhubung langsung dengan fungsi *backend* pencarian barang.
  
* **Menu Navigasi Samping (Sidebar):** 
  * **Beranda:** Halaman utama katalog produk.
  * **Keranjang:** Mengelola produk yang akan dibeli.
  * **Pesanan:** Memantau status pesanan yang sedang diproses.
  * **Chat Penjual:** Menghubungkan pembeli langsung dengan penjual/UMKM.
  * **Profil:** Pengaturan data pribadi akun.
  * **Keluar:** Tombol untuk mengakhiri sesi dan kembali ke halaman utama.
    
* **Kategori Produk & Katalog Interaktif:** 
  * Menyediakan kategori pilihan seperti *Makanan* (olahan khas Samarinda), *Kerajinan* (kriya khas Dayak & Kaltim), *Pakaian*, *Minuman*, hingga *Perlengkapan* rumah tangga.    
  * Menampilkan kartu produk secara detail, lengkap dengan foto produk, nama toko asal, harga satuan, kategori, serta ulasan rating bintang (misalnya *Amplang Khas Samarinda* dan *Amplang Tenggiri* seharga Rp 35.000 hingga Rp 65.000).

<img width="1025" height="760" alt="image" src="https://github.com/user-attachments/assets/6cfe5f22-9176-4bdc-98e8-d29bf36364fc" />

## 🛒 Halaman Keranjang Produk (Cart)

Halaman **Keranjang** memungkinkan pembeli untuk mengelola dan meninjau daftar produk pilihan sebelum melanjutkan ke proses pembayaran akhir.

### ⋆｡‧˚ʚ🍓ɞ˚‧｡⋆ Komponen & Fungsionalitas Utama:
* **Tabel Daftar Belanja:** Menyajikan rincian barang yang dimasukkan ke dalam keranjang secara terstruktur, mencakup:
  * **Produk:** Nama barang yang dipilih (seperti *Amplang Tenggiri*, *Baju Batik Pria*, dan *Sapu Lidi Kelapa*).
  * **Harga & Jumlah:** Informasi harga satuan dan kuantitas unit yang dibeli (misalnya 1 Pcs atau 2 Pcs).
  * **Subtotal:** Hasil perkalian harga satuan dengan jumlah barang.
  * **Aksi Hapus:** Tombol **"Hapus"** pada setiap baris produk untuk membatalkan atau mengeluarkan item dari daftar keranjang.
    
* **Ringkasan & Aksi Lanjutan:** Bagian bawah halaman menampilkan informasi **Total tagihan keranjang** serta tombol **"Lanjut ke Checkout"** untuk memproses pesanan ke tahap transaksi berikutnya.

<img width="1037" height="759" alt="image" src="https://github.com/user-attachments/assets/9f03cf99-a40f-4228-a71b-dcc8103ea0d0" />

## 📦 Halaman Daftar Pesanan (Pesanan Pembeli)

Halaman **Pesanan** memungkinkan pembeli untuk memantau riwayat dan status transaksi produk yang telah dipesan dari berbagai toko UMKM secara terorganisir.

### ˙ . ꒷ 🍰 . 𖦹˙— Fitur & Navigasi Utama:
* **Filter Status Pesanan (Tab Kategori):** Dilengkapi tombol navigasi status pesanan untuk menyaring tampilan berdasarkan kondisi transaksi:
  * **Semua**
  * **Belum Bayar**
  * **Diproses**
  * **Dikirim**
  * **Selesai**
  * **Pengembalian**
    
* **Rincian Kartu Pesanan:** Setiap item pesanan dikelompokkan berdasarkan toko asal (misalnya *Toko Amplang Samarinda*, *Batik Mahakam Kaltim*, dan *Alat Rumah Tangga Lokal*) lengkap dengan informasi:
  * Status transaksi terkini pada bagian sudut kanan atas kartu (seperti status **Selesai**).    
  * Foto produk, nama barang yang dibeli (*Amplang Tenggiri*, *Baju Batik Pria*, *Sapu Lidi Kelapa*), jumlah kuantitas unit, serta total harga pembelian.

<img width="1035" height="754" alt="image" src="https://github.com/user-attachments/assets/3f4d7ffa-2d9c-4dd3-818f-02838a5f2ffa" />

## 💬 Halaman Chat Penjual (Messaging System)

Halaman **Chat Penjual** menyediakan sarana komunikasi langsung secara dua arah antara pembeli dan penjual/UMKM untuk menanyakan ketersediaan produk, spesifikasi barang, maupun koordinasi pemesanan.

### (˶˃ ᵕ ˂˶) .ᐟ.ᐟ Fitur & Komponen Antarmuka:
* **Daftar Percakapan (Daftar Pesan Masuk):** 
  * Menampilkan riwayat percakapan interaktif di panel kiri dengan toko-toko UMKM (seperti *Batik Mahakam Kaltim* dan *Toko Amplang Samarinda*).
  * Dilengkapi indikator status pesan terkini, seperti label **Sudah Dibalas** beserta potongan pesan terakhir.
    
* **Panel Jendela Obrolan (Chat Room):**
  * Menampilkan nama toko aktif di bagian header percakapan.
  * Menyajikan riwayat pesan secara kronologis lengkap dengan stempel waktu (*timestamp*), memisahkan antara pertanyaan pembeli dan tanggapan dari pihak penjual.
    
* **Form Pengiriman Pesan:**
  * Menyediakan kolom input *Balas pesan...* di bagian bawah panel obrolan serta tombol **Kirim** untuk mengirimkan pesan baru secara instan.

<img width="1029" height="759" alt="image" src="https://github.com/user-attachments/assets/21e91a02-fa06-4480-a36a-c88108dd39eb" />

## 🛒 Halaman Keranjang Produk dengan Simulasi Checkbox

Tampilan ini memperlihatkan interaksi lanjutan pada **Halaman Keranjang**, di mana pembeli dapat memilih produk tertentu menggunakan kotak centang (*checkbox*) sebelum melanjutkan transaksi.

### ◝(ᵔᗜᵔ)◜ Komponen & Fitur Interaktif:
* **Seleksi Produk (Checkbox):** 
  * Baris produk pertama (*Amplang Tenggiri*) dicentang aktif (`[✔]`), sementara produk lainnya (*Baju Batik Pria* dan *Sapu Lidi Kelapa*) berada dalam kondisi tidak dicentang (`[ ]`).
    
* **Dinamika Total Tagihan:** 
  * Sistem secara otomatis menghitung ulang nilai **Total tagihan keranjang** di bagian bawah berdasarkan item yang dicentang saja—dalam hal ini hanya produk *Amplang Tenggiri* senilai **Rp 65.000**.
    
* **Tombol Aksi Lanjutan:** 
  * Tombol **Lanjut ke Checkout** di sudut kanan bawah akan memproses item terpilih tersebut ke tahap pembayaran berikutnya.

<img width="1029" height="759" alt="image" src="https://github.com/user-attachments/assets/fbd6f17f-4c52-4236-afca-3515ab164b14" />

## ╰┈➤ Pop-up Input Jarak Pengiriman (Checkout Flow)

Tampilan ini memperlihatkan interaksi lanjutan saat pembeli menekan tombol **Lanjut ke Checkout** dari menu keranjang.

### 📋 Fitur & Alur Dialog Interaktif:
* **Dialog Input Jarak:** Sistem menampilkan jendela pop-up dialog (*Input Dialog*) dengan judul **Jarak Pengiriman**.
  
* **Form Masukan Jarak:** Meminta pengguna memasukkan data numerik dengan instruksi *"Masukkan jarak pengiriman (km):"* beserta kolom teks input kosong untuk perhitungan ongkos kirim.
  
* **Tombol Aksi Dialog:** Dilengkapi tombol **OK** untuk memproses nilai jarak yang dimasukkan dan tombol **Cancel** untuk membatalkan proses *checkout*.

<img width="1032" height="750" alt="image" src="https://github.com/user-attachments/assets/0704fc86-3fb7-48f5-8157-2613baaed263" />

## 💳 Halaman Checkout & Konfirmasi Pembayaran

Tampilan ini memperlihatkan tahap akhir transaksi pembelian setelah pengguna memasukkan jarak pengiriman pada sistem.

### 𓍢ִ໋🌷͙֒  Komponen & Informasi Transaksi:
* **Alamat Pengiriman:** Menampilkan alamat tujuan pembeli (contoh: *Jl. Mulawarman Kota Samarinda*) beserta hasil kalkulasi jarak (seperti *Jarak 4.0 km*).
  
* **Kurir LOKA (Ongkir):** Menampilkan rincian biaya pengiriman berdasarkan jarak yang dihitung otomatis oleh sistem (contoh: *Rp 16.000*).
  
* **Ringkasan Pesanan:** 
  * Menampilkan rincian nominal mulai dari **Subtotal** harga produk (*Rp 65.000*), biaya **Ongkir** (*Rp 16.000*), hingga kalkulasi **Total** keseluruhan tagihan (*Rp 81.000*).
    
* **Metode Pembayaran (Saldo LOKA-Pay):** Menampilkan informasi saldo aktif pengguna (*Rp 750.000*) untuk memotong total tagihan secara langsung.
  
* **Tombol Aksi Pembayaran:** Tombol **Konfirmasi & Bayar Pesanan** untuk menyelesaikan transaksi dan memotong saldo LOKA-Pay.

<img width="1032" height="752" alt="image" src="https://github.com/user-attachments/assets/b7e8f4c1-09ac-4d50-bbcd-1c3cd95957db" />

## ✅ Pop-up Konfirmasi Sukses Pembayaran

Tampilan ini memperlihatkan notifikasi pop-up penutup setelah pembeli berhasil menyelesaikan proses transaksi di halaman konfirmasi.

### 🎧ྀི♪⋆.✮ Fitur & Alur Dialog Konfirmasi:
* **Dialog Pesan Sukses:** Sistem menampilkan jendela dialog notifikasi bertuliskan judul **Sukses** beserta pesan konfirmasi *"Pesanan berhasil dibuat!"*.
  
* **Tombol Aksi:** Dilengkapi tombol **OK** untuk menutup dialog dan mengarahkan pengguna kembali ke sistem atau menu riwayat pesanan.

<img width="1037" height="762" alt="image" src="https://github.com/user-attachments/assets/3a0109f3-8478-4c81-aa18-1b599290bda8" />

## 📦 Pembaruan Daftar Pesanan & Saldo (Update Pesanan Pembeli)

Tampilan ini memperlihatkan pembaruan riwayat transaksi pada halaman **Daftar Pesanan** setelah transaksi baru berhasil diproses dan dicatat oleh sistem.

### (๑ > ᴗ < ๑) Fitur & Perubahan Utama:
* **Pembaruan Saldo Otomatis:** Informasi saldo aktif di sudut kanan atas berubah secara *real-time* setelah pemotongan dana transaksi (dari *Rp 750.000* menjadi **Rp 669.000**).
  
* **Penambahan Item Pesanan Baru:** 
  * Daftar pesanan bertambah satu item baru di bagian bawah dari *Toko Amplang Samarinda* yang memuat produk *Amplang Tenggiri* (jumlah 1 Pcs seharga Rp 65.000).
    
  * Menampilkan status transaksi terkini pada sudut kanan atas kartu pesanan dengan label status **Diproses**.

<img width="1032" height="763" alt="image" src="https://github.com/user-attachments/assets/1830f683-9af7-49a9-b1d9-a4e71eb545a7" />

## 💳 Halaman Top Up LOKA-Pay

Halaman **Top Up LOKA-Pay** memungkinkan pembeli untuk mengisi ulang saldo dompet digital mereka di dalam aplikasi secara aman dan cepat.

### °❀.ೃ࿔* Komponen & Fitur Utama:
* **Informasi Saldo Aktif:** 
  * Menampilkan panel ringkasan **Dompet Digital LOKA** yang menunjukkan nominal **Saldo Anda** saat ini secara *real-time* (contoh: *Rp 669.000*).
    
* **Formulir Pengisian Saldo:**
  * **Nominal Top Up:** Kolom masukan bertuliskan *"Masukkan Nominal Top Up"* bagi pengguna untuk menentukan jumlah saldo yang ingin ditambahkan.
    
  * **PIN Akun (6 digit):** Kolom keamanan berupa karakter tersembunyi (*"******"*) untuk verifikasi identitas pemilik akun sebelum transaksi diproses.
    
* **Tombol Aksi Eksekusi:**
  * Tombol hijau **"Top Up Saldo Sekarang!"** di bagian bawah untuk mengirimkan permintaan penambahan saldo ke basis data.

<img width="1032" height="761" alt="image" src="https://github.com/user-attachments/assets/3bc29e8d-a355-48a9-a88d-d5cae526a849" />

## 💳 Hasil Pembaruan Saldo Top Up LOKA-Pay

Tampilan ini memperlihatkan perubahan langsung pada nilai **Saldo Anda** di halaman Top Up setelah proses pengisian saldo berhasil dieksekusi dan divalidasi oleh sistem.

### (ᵕ⸝⸝• ᴗ •) Komponen & Perubahan Utama:
* **Pembaruan Saldo Dompet Digital:** 
  * Informasi nominal **Saldo Anda** pada panel *Dompet Digital LOKA* bertambah secara otomatis dan *real-time* (dari *Rp 669.000* menjadi **Rp 769.000**).
    
* **Formulir Input Siap Saji:**
  * Kolom masukan *Nominal Top Up* dan *PIN Akun (6 digit)* kembali kosong dan siap digunakan kembali untuk transaksi pengisian saldo berikutnya.

## PENJUAL

<img width="1035" height="762" alt="image" src="https://github.com/user-attachments/assets/a77a052e-d4ed-430d-abc2-41d3ea191461" />

## 📊 Halaman Dashboard Penjual (LOKA Penjual)

Halaman **Dashboard Penjual** menampilkan ringkasan data analitik dan performa toko secara *real-time* bagi mitra UMKM (seperti toko *Kerajinan Rotan Borneo*) untuk memantau aktivitas bisnis mereka.

### ⋆.ೃ࿔🌸*:･ Fitur & Komponen Utama:
* **Panel Ringkasan Toko:**
  * **Pesanan Masuk:** Menunjukkan jumlah pesanan aktif yang belum diproses (sebanyak **0 Pesanan**).
    
  * **Produk Aktif:** Menampilkan jumlah katalog barang yang aktif ditawarkan di platform (sebanyak **6 produk**).
    
  * **Pendapatan Bulan Ini:** Memuat total akumulasi pendapatan penjualan selama bulan berjalan (sebesar **Rp 190.000,00**).
    
  * **Pelanggan Ter-Royal:** Menampilkan informasi pembeli dengan kontribusi transaksi terbesar (contoh: *Fajar Nugraha (Email: fajar@gmail.com)*).

<img width="1037" height="755" alt="image" src="https://github.com/user-attachments/assets/a142b1b1-30c4-40a9-bd45-86defeef48c5" />

## ˚ʚ♡ɞ˚ Halaman Kelola Produk & Stok (LOKA Penjual)

Halaman **Kelola Produk & Stok** memungkinkan mitra penjual atau pelaku UMKM untuk mengelola inventaris produk mereka secara terstruktur dan transparan di dalam sistem.

### ͙͘͡★ Fitur & Komponen Utama:
* **Tombol Aksi Utama:**
  * **Tambah Produk:** Tombol hijau untuk memasukkan item barang baru ke dalam katalog toko.
    
  * **Update Stok:** Tombol kuning untuk memperbarui jumlah ketersediaan stok barang.
    
  * **Hapus Produk:** Tombol merah untuk menghapus barang dari daftar penawaran.
    
* **Tabel Inventaris Produk:** 
  * Menampilkan informasi terperinci mengenai daftar barang, mencakup **ID Produk**, **Nama Produk**, **Kategori** (seperti *Pakaian* dan *Kerajinan*), **Harga** satuan, jumlah **Stok** fisik, **Status Stok** (*Aman* atau *Waspada*), status **Izin Jual** (*Diizinkan* atau *Menunggu*), hingga metrik **Share** produk.

<img width="1041" height="763" alt="image" src="https://github.com/user-attachments/assets/d81171a8-dd83-4c3c-8b5d-91faeb5b54ea" />

## ➕ Form Tambah Produk Baru (LOKA Penjual)

Tampilan ini memperlihatkan jendela modal atau *form* interaktif **Tambah Produk Baru** yang terbuka ketika penjual menekan tombol tambah produk dari halaman inventaris.

### 🧸ྀི Komponen & Input Formulir:
* **Informasi Dasar Produk:**
  * **Nama Produk:** Kolom teks input untuk memasukkan nama barang (*"Masukkan Nama Produk"*).
    
  * **Harga:** Kolom numerik untuk menetapkan harga satuan produk.
    
  * **Stok:** Kolom masukan untuk jumlah ketersediaan unit awal barang.
    
* **Klasifikasi & Spesifikasi Fisik:**
  * **Kategori:** Menu dropdown pilihan kategori (seperti *Makanan & Minuman*).
    
  * **Berat (gram):** Kolom untuk mencantumkan berat fisik barang dalam satuan gram.
    
  * **Deskripsi Produk:** Kolom teks untuk memberikan penjelasan detail mengenai produk.
    
* **Unggah Media & Aksi Tombol:**
  * **Foto Produk:** Tombol **Choose File** untuk memilih dan melampirkan berkas gambar produk dari direktori lokal.
    
  * **Tombol Aksi Bawah:** Tombol **Batal** untuk menutup dialog (*cancel*) dan tombol hijau **Simpan Produk** untuk merekam data produk baru ke dalam sistem basis data.

<img width="1035" height="760" alt="image" src="https://github.com/user-attachments/assets/d80b2127-53fc-4b76-a2ab-389e57991c9a" />

## ૮ ྀིᴗ͈ . ᴗ͈ ྀིა Pop-up Update Stok Produk (LOKA Penjual)

Tampilan ini memperlihatkan dialog interaktif pembaruan stok yang muncul ketika penjual memilih item dari tabel inventaris dan menekan tombol **Update Stok**.

### ❄︎ Fitur & Alur Dialog Input:
* **Dialog Input Stok:** Memunculkan jendela dialog (*Input Dialog*) dengan judul **Update Stok Produk**.
  
* **Form Masukan Angka:** Menyediakan instruksi *"Masukkan Stok Baru:"* beserta kolom teks kosong untuk memasukkan nilai kuantitas inventaris yang diperbarui.
  
* **Tombol Aksi Dialog:** Dilengkapi tombol **OK** untuk menyimpan perubahan nilai stok ke dalam basis data dan tombol **Cancel** untuk membatalkan proses pembaruan.

<img width="1041" height="755" alt="image" src="https://github.com/user-attachments/assets/d0d57fde-33a7-4063-a288-b9b5f5b54301" />

## ☁︎ Hasil Pembaruan Stok Produk (LOKA Penjual)

Tampilan ini memperlihatkan perubahan langsung pada data inventaris di tabel **Kelola Produk & Stok** setelah proses pembaruan stok berhasil divalidasi dan disimpan oleh sistem.

### ( •̯́ ₃ •̯̀) Komponen & Perubahan Data:
* **Pembaruan Jumlah Stok Berhasil:** 
  * Nilai stok pada produk dengan **ID 5** (*Baju Batik Pria*) yang sebelumnya berjumlah 15 telah diperbarui secara *real-time* menjadi **20** stok.
    
* **Penyesuaian Status Stok Otomatis:** 
  * Bersamaan dengan bertambahnya kuantitas, sistem secara otomatis memperbarui **Status Stok** pada baris tersebut dari *Waspada* menjadi **Aman**.

<img width="1039" height="765" alt="image" src="https://github.com/user-attachments/assets/aa73c744-1f0a-44d9-81ea-350002a052d7" />

## 📋 Halaman Daftar Pesanan (LOKA Penjual)

Halaman **Daftar Pesanan** memungkinkan mitra penjual atau pelaku UMKM untuk memantau transaksi masuk, riwayat penjualan, serta mengelola status pemesanan dari para pembeli secara terpusat.

### ˚₊‧꒰ა 𓂋 ໒꒱ ‧₊˚ Fitur & Komponen Utama:
* **Tombol Aksi Utama:**
  * **Update Status:** Tombol hijau di bagian atas untuk memperbarui status pemesanan dari pembeli.
    
* **Tabel Riwayat Pesanan:** 
  * Menampilkan data transaksi secara terstruktur, mencakup **No. Pesanan** (seperti 102, 105, dan 115), ID **Pembeli**, **Tanggal Pesan** lengkap dengan *timestamp* (misalnya 2026-02-12 08:30:00.0), **Total Bayar** dalam rupiah (seperti Rp 165.000, Rp 368.000, dan Rp 202.000), serta **Status Pesanan** terkini (seperti status *Selesai*).

<img width="1032" height="755" alt="image" src="https://github.com/user-attachments/assets/b33499ce-75b9-45cf-9cc0-a1e6c9a83743" />

## 💬 Halaman Chat Pelanggan (LOKA Penjual)

Halaman **Chat Pelanggan** menyediakan sarana komunikasi dua arah bagi mitra penjual untuk menerima dan membalas pesan dari para pembeli secara interaktif.

### ૮₍ ˶ᵔ ᵕ ᵔ˶ ₎ა Fitur & Komponen Utama:
* **Daftar Pesan Masuk (Panel Kiri):** 
  * Menampilkan daftar kontak pembeli yang mengirimkan pesan (seperti *Rina Kartika*).
    
  * Dilengkapi label indikator status pesan bertuliskan **Sudah Dibalas** beserta cuplikan teks pesan terakhir.
    
* **Jendela Obrolan (Panel Kanan):** 
  * Menampilkan nama pembeli di bagian header jendela obrolan.
    
  * Memuat riwayat percakapan secara kronologis lengkap dengan stempel waktu (*timestamp*), memisahkan pertanyaan pembeli dan tanggapan yang dikirimkan penjual.
    
* **Formulir Kirim Pesan:**
  * Menyediakan kolom input teks *"Balas pesan..."* di bagian bawah serta tombol **Kirim** untuk mengirimkan tanggapan secara instan.

 ## PENGELOLA

<img width="1023" height="757" alt="image" src="https://github.com/user-attachments/assets/dc6ed56b-0811-4b01-81c9-755552187541" />

## 📊 Halaman Dashboard Admin (LOKA Admin)

Halaman **Dashboard Admin** menyajikan ringkasan informasi analitik dan metrik utama operasional sistem secara terpusat bagi pengelola platform LOKA.

### ⋆˚✿🍒𐙚⋆˚ Fitur & Komponen Utama:
* **Panel Ringkasan Pengguna & Mitra:**
  * **Total Pembeli LOKA:** Menampilkan jumlah keseluruhan konsumen terdaftar di dalam platform (sebanyak **8 Pembeli**).
    
  * **Total UMKM Aktif di LOKA:** Menampilkan jumlah mitra UMKM yang aktif menawarkan produk di aplikasi (sebanyak **5 UMKM**).
    
* **Panel Ringkasan Finansial:**
  * **Total Pendapatan LOKA:** Menampilkan kalkulasi pendapatan akumulatif platform dalam format mata uang rupiah secara transparan (sebesar **Rp 1.824.000,00**).

<img width="1039" height="760" alt="image" src="https://github.com/user-attachments/assets/73318485-5de3-4b91-82b2-10eaf0d4312e" />

## 📈 Halaman Statistik & Laporan (LOKA Admin)

Halaman **Statistik & Laporan** menyajikan visualisasi data analitik komprehensif bagi pengelola platform untuk memantau performa penjualan wilayah dan kategori produk terlaris.

### (๑•᎑•๑) Fitur & Komponen Grafik:
* **Grafik Statistik Transaksi Berdasarkan Kecamatan:**
  * Diagram batang (*bar chart*) vertikal yang menampilkan perbandingan **Total Transaksi** di berbagai wilayah administratif di Samarinda, meliputi *Samarinda Utara*, *Samarinda Ulu*, *Sungai Kunjang*, *Samarinda Ilir*, dan *Palaran*.
    
* **Grafik Persentase Kategori Produk Terlaris:**
  * Diagram lingkaran (*pie chart*) interaktif yang memetakan pangsa penjualan berdasarkan kategori produk, mencakup kategori **Makanan**, **Pakaian**, **Kerajinan**, dan **Perlengkapan**.

<img width="1023" height="758" alt="image" src="https://github.com/user-attachments/assets/c450de43-7a7d-4c05-80bb-76de3e8369e5" />

## 🍥⋆ Halaman Dashboard Verifikator (LOKA Verifikator)

Halaman **Dashboard Verifikator** menyajikan ringkasan informasi dan daftar antrean validasi bagi pihak verifikator untuk meninjau serta menyetujui produk maupun penjual baru di dalam platform LOKA.

### ˚˖𓍢ִ🍓✧˚.🎀༘⋆ﾟ＊ Fitur & Komponen Utama:
* **Panel Ringkasan Data Aktif:**
  * **Total Produk Aktif di LOKA:** Menampilkan jumlah keseluruhan produk yang telah disetujui dan aktif (sebanyak **30 produk**).
    
  * **Total UMKM Aktif di LOKA:** Menampilkan jumlah mitra UMKM yang aktif di dalam sistem (sebanyak **5 UMKM**).
    
* **Antrean Produk Menunggu Verifikasi:**
  * Menampilkan daftar item produk yang diajukan oleh mitra UMKM dengan status **Menunggu** (seperti produk dari *Toko Amplang Samari...* dan *Batik Mahakam Kaltim*).
    
  * Dilengkapi tombol **Selengkapnya** untuk mengakses panel verifikasi detail produk secara menyeluruh.
    
* **Antrean Penjual Menunggu Verifikasi:**
  * Menampilkan daftar calon mitra penjual atau UMKM baru yang mendaftar dan menunggu proses validasi akun.
    
  * Dilengkapi tombol **Selengkapnya** untuk memeriksa dokumen dan informasi pendaftaran penjual.

<img width="1035" height="759" alt="image" src="https://github.com/user-attachments/assets/a8c042b4-d724-421b-8f34-a4463efa11c4" />

## 𐙚⋆°｡⋆♡ Halaman Verifikasi Penjual (LOKA Verifikator)

Halaman **Verifikasi Penjual** memungkinkan pihak verifikator untuk memeriksa, menyetujui, atau menolak pendaftaran calon mitra UMKM baru sebelum toko mereka dapat beroperasi aktif di platform.

### ✩⋆⸜ 🍵✮˚ Fitur & Komponen Utama:
* **Tabel Antrean Verifikasi Penjual:**
  * Menampilkan daftar pendaftar mitra dengan kolom **Nama Pemilik** (*Budi Santoro*), **Nama Toko** (*Toko Kerajinan Mahak...*), **Alamat** (*Jl. Makroman*), serta **Status** (*BelumDiverifikasi*).
    
  * Dilengkapi tombol aksi langsung berupa tombol hijau **Setujui** untuk memvalidasi pendaftaran dan tombol **Tolak** untuk menolak pengajuan toko.
    
* **Panel Detail Toko:**
  * Menampilkan rincian identitas dan legalitas calon mitra toko secara komprehensif, meliputi:
    * **Nama Pemilik:** Budi Santoro
    * **Nama Toko:** Toko Kerajinan Mahakam
    * **Deskripsi Toko:** Kualitasnya keren
    * **Alamat Lengkap Toko:** Jl. Makroman
    * **Tanggal Daftar:** 2026-11-07
    * **Nomor Izin Usaha:** 0008998
    * **Label Status:** Terpampang di sudut kanan atas panel (*BelumDiverifikasi*).

<img width="1029" height="758" alt="image" src="https://github.com/user-attachments/assets/f8711114-7776-4985-8a47-a867eba0d778" />

## (˶ᵔ ᵕ ᵔ˶) ‹𝟹 Halaman Verifikasi Produk (LOKA Verifikator)

Halaman **Verifikasi Produk** memungkinkan pihak verifikator untuk meninjau, menyetujui, atau menolak produk-produk baru yang diajukan oleh mitra UMKM sebelum dipublikasikan di dalam aplikasi.

### 🍂ೃ࿔* Fitur & Komponen Utama:
* **Tabel Antrean Verifikasi:** 
  * Menampilkan daftar produk yang berstatus **Menunggu**, mencakup **Id Produk** (seperti 31 dan 32), **Nama Toko**, **Nama Produk**, **Kategori**, serta tombol aksi validasi untuk mengubah status verifikasi.
    
* **Panel Detail Produk:** 
  * Menampilkan informasi lengkap dari produk yang dipilih pada tabel, meliputi **Nama Toko** (*Toko Amplang Samarinda*), **Nama Produk** (*Amplang Varian Super Pedas*), **Kategori** (*Makanan*), **Deskripsi Produk** (*Varian rasa baru amplang pedas level dewa*), **Harga Produk** (*Rp 40.000*), **Berat Produk** (*250 gram*), serta label status di sudut kanan atas.

---

## Kontributor ─ ★ ˙ ̟ !!

| No | Nama | NIM | Kelas |
|:--:|------|:---:|:-----:|
| 1 | Nazmi Ramadani | 2509116031 | A |
| 2 | Atha Rina Sarwahita | 2509116047 | B |
| 3 | Naisyla Auliya Rahmi Putri | 2509116078 | B |
| 4 | Qonitah Khansa' Ayu Madani A | 2509116113 | C |

---

<div align="center">

Terima kasih sudah mampir di LOKA! 🛍️🏪

</div>
