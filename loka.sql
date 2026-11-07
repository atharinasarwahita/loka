-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 07, 2026 at 07:51 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `loka`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_pesanan`
--

CREATE TABLE `detail_pesanan` (
  `id_detail` int NOT NULL,
  `Pesanan_no_pesanan` int NOT NULL,
  `Pesanan_Penjual_id_pengguna` int NOT NULL,
  `Pesanan_Pembeli_id_pengguna` int NOT NULL,
  `Produk_id_produk` int NOT NULL,
  `Produk_Penjual_id_pengguna` int NOT NULL,
  `Pengembalian_id_pengembalian` int DEFAULT NULL,
  `jumlah_produk` int NOT NULL,
  `harga_satuan` decimal(12,2) NOT NULL,
  `sub_total` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `detail_pesanan`
--

INSERT INTO `detail_pesanan` (`id_detail`, `Pesanan_no_pesanan`, `Pesanan_Penjual_id_pengguna`, `Pesanan_Pembeli_id_pengguna`, `Produk_id_produk`, `Produk_Penjual_id_pengguna`, `Pengembalian_id_pengembalian`, `jumlah_produk`, `harga_satuan`, `sub_total`) VALUES
(1, 101, 10, 5, 1, 10, 1, 2, '35000.00', '70000.00'),
(2, 102, 11, 5, 4, 11, 2, 1, '150000.00', '150000.00'),
(3, 103, 12, 5, 8, 12, 3, 3, '30000.00', '90000.00'),
(4, 104, 10, 6, 2, 10, 4, 1, '65000.00', '65000.00'),
(5, 105, 11, 6, 5, 11, 5, 2, '175000.00', '350000.00'),
(6, 106, 14, 6, 13, 14, 6, 1, '15000.00', '15000.00'),
(7, 107, 10, 7, 3, 10, 7, 4, '25000.00', '100000.00'),
(8, 108, 13, 7, 10, 13, 8, 1, '180000.00', '180000.00'),
(9, 109, 14, 7, 14, 14, 9, 2, '18000.00', '36000.00'),
(10, 110, 13, 8, 11, 13, 10, 1, '210000.00', '210000.00'),
(11, 111, 12, 8, 9, 12, 11, 2, '220000.00', '440000.00'),
(12, 112, 14, 8, 15, 14, 12, 1, '30000.00', '30000.00'),
(13, 113, 13, 9, 12, 13, 13, 1, '160000.00', '160000.00'),
(14, 114, 12, 9, 7, 12, 14, 2, '200000.00', '400000.00'),
(15, 115, 11, 9, 6, 11, 15, 1, '190000.00', '190000.00');

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `id_keranjang` int NOT NULL,
  `Pembeli_id_pengguna` int NOT NULL,
  `jumlah` int NOT NULL,
  `tanggal_ditambahkan` datetime NOT NULL,
  `Produk_id_produk` int NOT NULL,
  `Produk_Penjual_id_pengguna` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `keranjang`
--

INSERT INTO `keranjang` (`id_keranjang`, `Pembeli_id_pengguna`, `jumlah`, `tanggal_ditambahkan`, `Produk_id_produk`, `Produk_Penjual_id_pengguna`) VALUES
(1, 5, 2, '2026-02-10 10:00:00', 1, 10),
(2, 5, 1, '2026-02-10 10:05:00', 4, 11),
(3, 5, 3, '2026-02-10 10:10:00', 8, 12),
(4, 6, 1, '2026-02-11 11:00:00', 2, 10),
(5, 6, 2, '2026-02-11 11:05:00', 5, 11),
(6, 6, 1, '2026-02-11 11:10:00', 13, 14),
(7, 7, 4, '2026-02-12 12:00:00', 3, 10),
(8, 7, 1, '2026-02-12 12:05:00', 10, 13),
(9, 7, 2, '2026-02-12 12:10:00', 14, 14),
(10, 8, 1, '2026-02-13 13:00:00', 11, 13),
(11, 8, 2, '2026-02-13 13:05:00', 9, 12),
(12, 8, 1, '2026-02-13 13:10:00', 15, 14),
(13, 9, 5, '2026-02-14 14:00:00', 12, 13),
(14, 9, 2, '2026-02-14 14:05:00', 7, 12);

-- --------------------------------------------------------

--
-- Table structure for table `pembeli`
--

CREATE TABLE `pembeli` (
  `id_pengguna` int NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `kota` varchar(50) NOT NULL,
  `kecamatan` varchar(50) NOT NULL,
  `kode_pos` varchar(5) NOT NULL,
  `alamat_pengiriman` text NOT NULL,
  `saldo` decimal(12,2) NOT NULL,
  `pin` varchar(6) NOT NULL,
  `status_akun` enum('Aktif','Nonaktif') NOT NULL,
  `tanggal_daftar` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pembeli`
--

INSERT INTO `pembeli` (`id_pengguna`, `tanggal_lahir`, `jenis_kelamin`, `kota`, `kecamatan`, `kode_pos`, `alamat_pengiriman`, `saldo`, `pin`, `status_akun`, `tanggal_daftar`) VALUES
(5, '1995-05-12', 'L', 'Samarinda', 'Samarinda Utara', '75119', 'Jl. M Yamin No. 12', '336000.00', '123456', 'Aktif', '2026-01-10 08:00:00'),
(6, '1998-08-20', 'P', 'Samarinda', 'Samarinda Ulu', '75123', 'Jl. Juanda No. 45', '750000.00', '654321', 'Aktif', '2026-01-11 09:30:00'),
(7, '2000-01-15', 'L', 'Samarinda', 'Sungai Kunjang', '75126', 'Jl. Untung Suropati No. 8', '300000.00', '112233', 'Aktif', '2026-01-12 10:15:00'),
(8, '1997-11-03', 'P', 'Samarinda', 'Samarinda Seberang', '75131', 'Jl. Hos Cokroaminoto No. 99', '1000000.00', '445566', 'Aktif', '2026-01-13 14:00:00'),
(9, '1993-04-25', 'L', 'Samarinda', 'Palaran', '75241', 'Jl. Ampera No. 17', '250000.00', '998877', 'Aktif', '2026-01-14 16:45:00'),
(15, '2001-03-12', 'P', 'Samarinda', 'Samarinda Utara', '75119', 'Jl. AW Syahranie No. 10', '150000.00', '151515', 'Aktif', '2026-10-30 08:00:00'),
(16, '1999-07-22', 'L', 'Samarinda', 'Sungai Pinang', '75117', 'Jl. Kemakmuran No. 44', '200000.00', '161616', 'Aktif', '2026-10-30 08:30:00'),
(17, '2002-11-05', 'P', 'Samarinda', 'Samarinda Ulu', '75124', 'Jl. Kadrie Oening No. 9', '350000.00', '171717', 'Aktif', '2026-10-30 09:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `pengelola`
--

CREATE TABLE `pengelola` (
  `id_pengguna` int NOT NULL,
  `posisi` enum('Verifikator','Admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pengelola`
--

INSERT INTO `pengelola` (`id_pengguna`, `posisi`) VALUES
(1, 'Admin'),
(2, 'Verifikator'),
(3, 'Admin'),
(4, 'Verifikator');

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id_pengembalian` int NOT NULL,
  `alasan_pengembalian` text NOT NULL,
  `nominal_pengembalian` decimal(12,2) NOT NULL,
  `status_pengembalian` enum('Diproses','Pengembalian Disetujui','Pengembalian Ditolak') NOT NULL,
  `tanggal_pengembalian` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pengembalian`
--

INSERT INTO `pengembalian` (`id_pengembalian`, `alasan_pengembalian`, `nominal_pengembalian`, `status_pengembalian`, `tanggal_pengembalian`) VALUES
(1, 'Kemasan amplang bocor dan melempem', '35000.00', 'Pengembalian Disetujui', '2026-02-20 10:00:00'),
(2, 'Baju batik salah ukuran', '150000.00', 'Pengembalian Disetujui', '2026-02-21 11:00:00'),
(3, 'Kue keminting hancur saat pengiriman', '25000.00', 'Pengembalian Ditolak', '2026-02-22 12:00:00'),
(4, 'Amplang tenggiri remuk sebagian', '65000.00', 'Pengembalian Disetujui', '2026-02-23 13:00:00'),
(5, 'Baju batik pria cacat benang', '175000.00', 'Diproses', '2026-02-24 14:00:00'),
(6, 'Sapu lidi ada yang patah', '15000.00', 'Pengembalian Ditolak', '2026-02-25 15:00:00'),
(7, 'Kue keminting tidak sesuai', '25000.00', 'Diproses', '2026-02-26 16:00:00'),
(8, 'Tas rotan anyaman tali putus', '180000.00', 'Pengembalian Disetujui', '2026-02-27 17:00:00'),
(9, 'Sapu lidi halaman rusak', '18000.00', 'Pengembalian Disetujui', '2026-02-28 18:00:00'),
(10, 'Tas rotan dayak cacat', '210000.00', 'Pengembalian Ditolak', '2026-03-01 09:00:00'),
(11, 'Sepatu kulit terkelupas', '220000.00', 'Pengembalian Ditolak', '2026-03-02 10:00:00'),
(12, 'Amplang stik kemasan bocor', '30000.00', 'Diproses', '2026-03-03 11:00:00'),
(13, 'Baju batik wanita robek', '160000.00', 'Pengembalian Disetujui', '2026-03-04 12:00:00'),
(14, 'Sepatu kasual kebesaran', '200000.00', 'Pengembalian Disetujui', '2026-03-05 13:00:00'),
(15, 'Tas rotan etnik luntur', '190000.00', 'Diproses', '2026-03-06 14:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `no_telpon` varchar(20) NOT NULL,
  `role` enum('Pengelola','Pembeli','Penjual') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_lengkap`, `email`, `password`, `no_telpon`, `role`) VALUES
(1, 'Budi Santoso', 'budi@loka.com', 'budiAdmin123', '081234567801', 'Pengelola'),
(2, 'Siti Rahmah', 'siti@loka.com', 'sitiVerif456', '081234567802', 'Pengelola'),
(3, 'Andi Wijaya', 'andi@loka.com', 'andiAdmin789', '081234567803', 'Pengelola'),
(4, 'Dewi Lestari', 'dewi@loka.com', 'dewiVerif321', '081234567804', 'Pengelola'),
(5, 'Rahmat Hidayat', 'rahmat@gmail.com', 'rahmatPass11', '081234567805', 'Pembeli'),
(6, 'Rina Kartika', 'rina@gmail.com', 'rinaPass22', '081234567806', 'Pembeli'),
(7, 'Fajar Nugraha', 'fajar@gmail.com', 'fajarPass33', '081234567807', 'Pembeli'),
(8, 'Nisa Ananda', 'nisa@gmail.com', 'nisaPass44', '081234567808', 'Pembeli'),
(9, 'Eko Prasetyo', 'eko@gmail.com', 'ekoPass55', '081234567809', 'Pembeli'),
(10, 'Bambang Pratama', 'amplang@loka.com', 'amplangPass10', '081234567810', 'Penjual'),
(11, 'Sri Wahyuni', 'batik@loka.com', 'batikPass11', '081234567811', 'Penjual'),
(12, 'Hendra Saputra', 'serbaada@loka.com', 'serbaAdaPass12', '081234567812', 'Penjual'),
(13, 'Hidayatullah', 'rotan@loka.com', 'rotanPass13', '081234567813', 'Penjual'),
(14, 'Agus Setiawan', 'rumahtangga@loka.com', 'rumahTanggaPass14', '081234567814', 'Penjual'),
(15, 'Sinta Maharani', 'sinta@gmail.com', 'sintaPass15', '081234567815', 'Pembeli'),
(16, 'Rizky Fadillah', 'rizky@gmail.com', 'rizkyPass16', '081234567816', 'Pembeli'),
(17, 'Alya Putri', 'alya@gmail.com', 'alyaPass17', '081234567817', 'Pembeli');

-- --------------------------------------------------------

--
-- Table structure for table `penjual`
--

CREATE TABLE `penjual` (
  `id_pengguna` int NOT NULL,
  `nama_toko` varchar(100) NOT NULL,
  `deskripsi_toko` text NOT NULL,
  `alamat_toko` text NOT NULL,
  `kota` varchar(50) NOT NULL,
  `kecamatan` varchar(50) NOT NULL,
  `kode_pos` varchar(5) NOT NULL,
  `saldo` decimal(12,2) NOT NULL,
  `no_izin_usaha` varchar(30) NOT NULL,
  `status_verifikasi` enum('Terverifikasi','Belum Diverifikasi','Ditolak') NOT NULL,
  `tanggal_verifikasi` datetime DEFAULT NULL,
  `status_akun` enum('Aktif','Nonaktif') NOT NULL,
  `tanggal_daftar` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `penjual`
--

INSERT INTO `penjual` (`id_pengguna`, `nama_toko`, `deskripsi_toko`, `alamat_toko`, `kota`, `kecamatan`, `kode_pos`, `saldo`, `no_izin_usaha`, `status_verifikasi`, `tanggal_verifikasi`, `status_akun`, `tanggal_daftar`) VALUES
(10, 'Toko Amplang Samarinda', 'Pusat oleh-oleh amplang khas Samarinda', 'Jl. Pangeran Antasari No. 10', 'Samarinda', 'Samarinda Ulu', '75124', '2582000.00', 'NIB1234567890', 'Terverifikasi', '2026-01-05 10:00:00', 'Aktif', '2026-01-01 08:00:00'),
(11, 'Batik Mahakam Kaltim', 'Produsen baju batik khas Kalimantan', 'Jl. Kebudayaan No. 5', 'Samarinda', 'Samarinda Seberang', '75132', '5000000.00', 'NIB1234567891', 'Terverifikasi', '2026-01-06 11:00:00', 'Aktif', '2026-01-02 09:00:00'),
(12, 'Serba Ada Samarinda', 'Toko cemilan dan perlengkapan lokal', 'Jl. Ahmad Yani No. 88', 'Samarinda', 'Samarinda Utara', '75117', '1500000.00', 'NIB1234567892', 'Terverifikasi', '2026-01-07 13:00:00', 'Aktif', '2026-01-03 10:00:00'),
(13, 'Kerajinan Rotan Borneo', 'Pengrajin tas dan kerajinan rotan', 'Jl. Dr. Soetomo No. 22', 'Samarinda', 'Samarinda Ulu', '75123', '3200000.00', 'NIB1234567893', 'Terverifikasi', '2026-01-08 14:30:00', 'Aktif', '2026-01-04 11:00:00'),
(14, 'Alat Rumah Tangga Lokal', 'Penyedia kebutuhan alat rumah tangga', 'Jl. Agus Salim No. 14', 'Samarinda', 'Samarinda Kota', '75121', '1800000.00', 'NIB1234567894', 'Terverifikasi', '2026-01-09 16:00:00', 'Aktif', '2026-01-05 12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `pesan`
--

CREATE TABLE `pesan` (
  `id_pesan` int NOT NULL,
  `Penjual_id_pengguna` int NOT NULL,
  `Pembeli_id_pengguna` int NOT NULL,
  `isi_pesan` text NOT NULL,
  `waktu_kirim` datetime NOT NULL,
  `dibaca` enum('Sudah Dibaca','Belum Dibaca') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pesan`
--

INSERT INTO `pesan` (`id_pesan`, `Penjual_id_pengguna`, `Pembeli_id_pengguna`, `isi_pesan`, `waktu_kirim`, `dibaca`) VALUES
(1, 10, 5, 'Apakah amplang ready?', '2026-02-01 09:00:00', 'Sudah Dibaca'),
(2, 10, 5, 'Ready kak, silahkan diorder', '2026-02-01 09:05:00', 'Sudah Dibaca'),
(3, 11, 6, 'Baju batik ukuran L ready?', '2026-02-02 10:00:00', 'Sudah Dibaca'),
(4, 11, 6, 'Ready stok kak, bahan katun halus', '2026-02-02 10:10:00', 'Sudah Dibaca'),
(5, 12, 7, 'Minimal pembelian kue keminting berapa?', '2026-02-03 11:00:00', 'Sudah Dibaca'),
(6, 12, 7, 'Bisa beli 1 bungkus kak', '2026-02-03 11:15:00', 'Sudah Dibaca'),
(7, 13, 8, 'Tas rotannya motif apa saja?', '2026-02-04 13:00:00', 'Sudah Dibaca'),
(8, 13, 8, 'Ada motif Dayak dan etnik kak', '2026-02-04 13:20:00', 'Sudah Dibaca'),
(9, 14, 9, 'Sapu lidi ikatannya kuat?', '2026-02-05 14:00:00', 'Sudah Dibaca'),
(10, 14, 9, 'Kuat banget kak dari lidi pilihan', '2026-02-05 14:05:00', 'Sudah Dibaca'),
(11, 10, 6, 'Bisa kirim luar kota?', '2026-02-06 15:00:00', 'Sudah Dibaca'),
(12, 10, 6, 'Bisa banget kak', '2026-02-06 15:10:00', 'Belum Dibaca'),
(13, 12, 7, 'Sepatu ready ukuran 42?', '2026-02-07 16:00:00', 'Belum Dibaca'),
(14, 13, 8, 'Tas rotan tahan air tidak?', '2026-02-08 17:00:00', 'Belum Dibaca'),
(15, 14, 9, 'Pengiriman sapu lidi pakai apa?', '2026-02-09 18:00:00', 'Belum Dibaca');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `no_pesanan` int NOT NULL,
  `Penjual_id_pengguna` int NOT NULL,
  `Pembeli_id_pengguna` int NOT NULL,
  `tanggal_pesanan` datetime NOT NULL,
  `jarak_km` decimal(3,1) DEFAULT NULL,
  `ongkos_kirim` decimal(12,2) NOT NULL,
  `total_bayar` decimal(12,2) NOT NULL,
  `status_pesanan` enum('Diproses','Dikirim','Selesai','Pengembalian','Dikembalikan') NOT NULL,
  `tanggal_dikirim` datetime DEFAULT NULL,
  `tanggal_diterima` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`no_pesanan`, `Penjual_id_pengguna`, `Pembeli_id_pengguna`, `tanggal_pesanan`, `jarak_km`, `ongkos_kirim`, `total_bayar`, `status_pesanan`, `tanggal_dikirim`, `tanggal_diterima`) VALUES
(101, 10, 5, '2026-02-12 08:00:00', '3.5', '12000.00', '82000.00', 'Selesai', '2026-02-12 10:00:00', '2026-02-13 14:00:00'),
(102, 11, 5, '2026-02-12 08:30:00', '5.0', '15000.00', '165000.00', 'Selesai', '2026-02-12 11:00:00', '2026-02-13 15:00:00'),
(103, 12, 5, '2026-02-12 09:00:00', '2.0', '10000.00', '100000.00', 'Selesai', '2026-02-12 11:30:00', '2026-02-13 16:00:00'),
(104, 10, 6, '2026-02-13 09:00:00', '4.0', '13000.00', '78000.00', 'Selesai', '2026-02-13 11:00:00', '2026-02-14 10:00:00'),
(105, 11, 6, '2026-02-13 09:30:00', '6.0', '18000.00', '368000.00', 'Selesai', '2026-02-13 12:00:00', '2026-02-14 11:00:00'),
(106, 14, 6, '2026-02-13 10:00:00', '1.5', '8000.00', '23000.00', 'Selesai', '2026-02-13 13:00:00', '2026-02-14 12:00:00'),
(107, 10, 7, '2026-02-14 10:00:00', '7.0', '20000.00', '120000.00', 'Selesai', '2026-02-14 13:00:00', '2026-02-15 13:00:00'),
(108, 13, 7, '2026-02-14 10:30:00', '3.0', '10000.00', '190000.00', 'Selesai', '2026-02-14 14:00:00', '2026-02-15 14:00:00'),
(109, 14, 7, '2026-02-14 11:00:00', '2.5', '10000.00', '46000.00', 'Selesai', '2026-02-14 15:00:00', '2026-02-15 15:00:00'),
(110, 13, 8, '2026-02-15 11:00:00', '4.5', '14000.00', '224000.00', 'Pengembalian', '2026-02-15 14:00:00', '2026-02-16 11:00:00'),
(111, 12, 8, '2026-02-15 11:30:00', '3.0', '10000.00', '450000.00', 'Selesai', '2026-02-15 15:00:00', '2026-02-16 12:00:00'),
(112, 14, 8, '2026-02-15 12:00:00', '5.0', '15000.00', '45000.00', 'Dikirim', '2026-02-16 09:00:00', NULL),
(113, 13, 9, '2026-02-16 13:00:00', '8.0', '22000.00', '182000.00', 'Dikirim', '2026-02-16 15:00:00', NULL),
(114, 12, 9, '2026-02-16 13:30:00', '6.5', '19000.00', '419000.00', 'Dikembalikan', '2026-02-17 08:00:00', '2026-02-18 10:00:00'),
(115, 11, 9, '2026-02-16 14:00:00', '4.0', '12000.00', '202000.00', 'Selesai', '2026-02-16 16:00:00', '2026-02-17 11:00:00'),
(116, 10, 5, '2026-10-29 02:46:58', '3.5', '12000.00', '82000.00', 'Diproses', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` int NOT NULL,
  `Penjual_id_pengguna` int NOT NULL,
  `nama_produk` varchar(150) NOT NULL,
  `deskripsi` text NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `gambar_produk` varchar(255) NOT NULL,
  `harga` decimal(12,2) NOT NULL,
  `berat_gram` int NOT NULL,
  `stok` int NOT NULL,
  `status` enum('Habis','Kritis','Waspada','Aman') NOT NULL,
  `izin_jual` enum('Diizinkan','Tidak Diizinkan','Menunggu') NOT NULL,
  `jumlah_share` int DEFAULT '0',
  `tanggal_dibuat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `Penjual_id_pengguna`, `nama_produk`, `deskripsi`, `kategori`, `gambar_produk`, `harga`, `berat_gram`, `stok`, `status`, `izin_jual`, `jumlah_share`, `tanggal_dibuat`) VALUES
(1, 10, 'Amplang Khas Samarinda', 'Amplang gurih renyah rasa ikan khas Samarinda', 'Makanan', 'amplang.jpg', '35000.00', 250, 48, 'Aman', 'Diizinkan', 12, '2026-01-10 09:00:00'),
(2, 10, 'Amplang Tenggiri', 'Amplang rasa ikan tenggiri 500g', 'Makanan', 'amplang_tenggiri.jpg', '65000.00', 500, 30, 'Aman', 'Diizinkan', 8, '2026-01-10 09:30:00'),
(3, 10, 'Kue Keminting', 'Kue keminting renyah khas Samarinda 200g', 'Makanan', 'keminting.jpg', '25000.00', 200, 40, 'Aman', 'Diizinkan', 5, '2026-01-11 10:00:00'),
(4, 11, 'Baju Batik Kaltim', 'Baju batik kemeja pria motif Kaltim', 'Pakaian', 'baju_batik.jpg', '150000.00', 300, 20, 'Aman', 'Diizinkan', 25, '2026-01-12 11:00:00'),
(5, 11, 'Baju Batik Pria', 'Baju batik lengan panjang pria', 'Pakaian', 'batik_pria.jpg', '175000.00', 350, 15, 'Waspada', 'Diizinkan', 15, '2026-01-12 11:30:00'),
(6, 11, 'Tas Rotan Etnik', 'Tas rotan motif etnik Kalimantan', 'Kerajinan', 'tas_rotan_etnik.jpg', '190000.00', 400, 12, 'Waspada', 'Diizinkan', 20, '2026-01-13 13:00:00'),
(7, 12, 'Sepatu Kasual', 'Sepatu kasual pria wanita nyaman', 'Pakaian', 'sepatu_kasual.jpg', '200000.00', 800, 15, 'Waspada', 'Diizinkan', 18, '2026-01-14 14:00:00'),
(8, 12, 'Kue Keminting Wijen', 'Kue keminting tabur wijen renyah', 'Makanan', 'keminting_wijen.jpg', '30000.00', 250, 35, 'Aman', 'Diizinkan', 8, '2026-01-14 14:30:00'),
(9, 12, 'Sepatu Kulit', 'Sepatu bahan kulit sintetis lokal', 'Pakaian', 'sepatu_kulit.jpg', '220000.00', 850, 10, 'Waspada', 'Diizinkan', 14, '2026-01-15 15:00:00'),
(10, 13, 'Tas Rotan Anyaman', 'Tas anyaman rotan tradisional asli Kaltim', 'Kerajinan', 'tas_rotan.jpg', '180000.00', 400, 10, 'Waspada', 'Diizinkan', 30, '2026-01-16 10:00:00'),
(11, 13, 'Tas Rotan Dayak', 'Tas rotan motif Dayak unik', 'Kerajinan', 'tas_rotan_dayak.jpg', '210000.00', 450, 8, 'Waspada', 'Diizinkan', 22, '2026-01-16 10:30:00'),
(12, 13, 'Baju Batik Wanita', 'Baju batik wanita model blus etnik', 'Pakaian', 'batik_wanita.jpg', '160000.00', 300, 18, 'Aman', 'Diizinkan', 16, '2026-01-17 11:00:00'),
(13, 14, 'Sapu Lidi Kelapa', 'Sapu lidi pohon kelapa kuat dan awet', 'Perlengkapan', 'sapu_lidi.jpg', '15000.00', 500, 30, 'Aman', 'Diizinkan', 5, '2026-01-18 09:00:00'),
(14, 14, 'Sapu Lidi Halaman', 'Sapu lidi besar untuk kebersihan halaman', 'Perlengkapan', 'sapu_halaman.jpg', '18000.00', 600, 25, 'Aman', 'Diizinkan', 9, '2026-01-18 09:30:00'),
(15, 14, 'Amplang Stik', 'Amplang stik renyah gurih 200g', 'Makanan', 'amplang_stik.jpg', '30000.00', 200, 40, 'Aman', 'Diizinkan', 11, '2026-01-19 10:00:00'),
(16, 10, 'Terasi Udang Paser', 'Terasi udang asli tangkapan nelayan Paser', 'Makanan', 'terasi_paser.jpg', '25000.00', 200, 30, 'Aman', 'Diizinkan', 12, '2026-10-30 10:00:00'),
(17, 11, 'Kaos Souvenir khas Samarinda', 'Kaos katun premium dengan sablon landmark Samarinda', 'Pakaian', 'kaos_samarinda.jpg', '85000.00', 200, 40, 'Aman', 'Diizinkan', 28, '2026-10-30 10:15:00'),
(18, 13, 'Dompet Manik Etnik Kaltim', 'Dompet wanita dengan hiasan manik-manik khas Kaltim', 'Kerajinan', 'dompet_manik.jpg', '55000.00', 100, 25, 'Aman', 'Diizinkan', 15, '2026-10-30 10:30:00'),
(19, 13, 'Gelang Manik Dayak', 'Gelang motif khas Dayak dari manik-manik warna warni', 'Kerajinan', 'gelang_manik.jpg', '15000.00', 50, 50, 'Aman', 'Diizinkan', 30, '2026-10-30 10:45:00'),
(20, 13, 'Kopiah songkok Khas Mahakam', 'Kopiah anyaman etnik dengan motif khas pesisir Mahakam', 'Kerajinan', 'kopiah_mahakam.jpg', '65000.00', 150, 20, 'Aman', 'Diizinkan', 22, '2026-10-30 11:00:00'),
(21, 14, 'Minyak Kayu Putih Asli Kalimantan', 'Minyak kayu putih murni hasil sulingan lokal', 'Perlengkapan', 'kayu_putih.jpg', '45000.00', 100, 35, 'Aman', 'Diizinkan', 18, '2026-10-30 11:15:00'),
(22, 14, 'Minyak Bulus Kalimantan', 'Minyak bulus kalimantan untuk perawatan kecantikan', 'Perlengkapan', 'minyak_bulus.jpg', '75000.00', 100, 18, 'Aman', 'Diizinkan', 32, '2026-10-30 11:30:00'),
(23, 14, 'Madu Hutan Asli Kaltim', 'Madu hutan murni hasil panen lebah liar Kaltim', 'Perlengkapan', 'madu_hutan.jpg', '150000.00', 500, 15, 'Waspada', 'Diizinkan', 45, '2026-10-30 11:45:00'),
(24, 14, 'Keranjang Rotan Serbaguna', 'Keranjang rotan besar untuk cucian atau perabotan', 'Perlengkapan', 'keranjang_rotan.jpg', '95000.00', 800, 12, 'Waspada', 'Diizinkan', 11, '2026-10-30 12:00:00'),
(25, 12, 'Biji Kopi Robusta Mahakam', 'Biji kopi robusta pilihan pinggiran sungai Mahakam', 'Minuman', 'biji_kopi_mahakam.jpg', '55000.00', 250, 40, 'Aman', 'Diizinkan', 25, '2026-10-30 12:15:00'),
(26, 12, 'Bubuk Kopi Lokal Samarinda', 'Kopi bubuk robusta siap seduh khas Samarinda', 'Minuman', 'bubuk_kopi.jpg', '35000.00', 250, 50, 'Aman', 'Diizinkan', 30, '2026-10-30 12:30:00'),
(27, 12, 'Sirup Tiwadak (Cempedak)', 'Sirup buah cempedak asli khas Kaltim yang harum', 'Minuman', 'sirup_tiwadak.jpg', '45000.00', 600, 20, 'Aman', 'Diizinkan', 19, '2026-10-30 12:45:00'),
(28, 12, 'Teh Pasak Bumi Herbal', 'Teh seduh akar pasak bumi untuk vitalitas dan stamina', 'Minuman', 'teh_pasakbumi.jpg', '55000.00', 100, 35, 'Aman', 'Diizinkan', 25, '2026-10-30 13:00:00'),
(29, 12, 'Minuman Sarang Burung Walet', 'Minuman kesehatan sarang burung walet asli Kaltim', 'Minuman', 'sarang_walet.jpg', '125000.00', 250, 10, 'Waspada', 'Diizinkan', 40, '2026-10-30 13:15:00'),
(30, 12, 'Jahe Merah Instan Khas Kaltim', 'Serbuk jahe merah hangat siap seduh', 'Minuman', 'jahe_merah.jpg', '35000.00', 250, 50, 'Aman', 'Diizinkan', 21, '2026-10-30 13:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `top_up`
--

CREATE TABLE `top_up` (
  `id_top_up` int NOT NULL,
  `Pembeli_id_pengguna` int NOT NULL,
  `nominal` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `top_up`
--

INSERT INTO `top_up` (`id_top_up`, `Pembeli_id_pengguna`, `nominal`) VALUES
(1, 5, '100000.00'),
(2, 5, '200000.00'),
(3, 5, '300000.00'),
(4, 6, '250000.00'),
(5, 6, '500000.00'),
(6, 6, '100000.00'),
(7, 7, '150000.00'),
(8, 7, '200000.00'),
(9, 7, '100000.00'),
(10, 8, '500000.00'),
(11, 8, '500000.00'),
(12, 8, '200000.00'),
(13, 9, '100000.00'),
(14, 9, '200000.00'),
(15, 9, '150000.00');

-- --------------------------------------------------------

--
-- Table structure for table `ulasan`
--

CREATE TABLE `ulasan` (
  `id_ulasan` int NOT NULL,
  `Pembeli_id_pengguna` int NOT NULL,
  `komentar` text NOT NULL,
  `rating` decimal(2,1) NOT NULL,
  `tanggal_ulasan` datetime NOT NULL,
  `Produk_id_produk` int NOT NULL,
  `Produk_Penjual_id_pengguna` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `ulasan`
--

INSERT INTO `ulasan` (`id_ulasan`, `Pembeli_id_pengguna`, `komentar`, `rating`, `tanggal_ulasan`, `Produk_id_produk`, `Produk_Penjual_id_pengguna`) VALUES
(1, 5, 'Amplang sangat renyah dan gurih, recommended!', '5.0', '2026-02-15 10:00:00', 1, 10),
(2, 5, 'Baju batik bahan dingin dan nyaman', '4.8', '2026-02-15 10:30:00', 4, 11),
(3, 5, 'Kue keminting pas manisnya', '4.5', '2026-02-15 11:00:00', 8, 12),
(4, 6, 'Amplang tenggiri renyah sekali', '5.0', '2026-02-16 12:00:00', 2, 10),
(5, 6, 'Baju batik pria bagus banget', '4.9', '2026-02-16 12:30:00', 5, 11),
(6, 6, 'Sapu lidi ikatannya kuat', '4.7', '2026-02-16 13:00:00', 13, 14),
(7, 7, 'Kue keminting enak buat cemilan', '4.0', '2026-02-17 14:00:00', 3, 10),
(8, 7, 'Tas rotan anyaman cantik pengerjaannya rapi', '4.6', '2026-02-17 14:30:00', 10, 13),
(9, 7, 'Sapu lidi halaman bagus', '4.2', '2026-02-17 15:00:00', 14, 14),
(10, 8, 'Tas rotan dayak estetik sekali', '4.8', '2026-02-18 16:00:00', 11, 13),
(11, 8, 'Sepatu kulit mantap', '4.9', '2026-02-18 16:30:00', 9, 12),
(12, 8, 'Amplang stik gurih', '4.5', '2026-02-18 17:00:00', 15, 14),
(13, 9, 'Baju batik wanita pas ukurannya', '5.0', '2026-02-19 18:00:00', 12, 13),
(14, 9, 'Sepatu kasual nyaman', '4.3', '2026-02-19 18:30:00', 7, 12),
(15, 9, 'Tas rotan etnik berkualitas', '4.7', '2026-02-19 19:00:00', 6, 11);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD PRIMARY KEY (`id_detail`,`Pesanan_no_pesanan`,`Pesanan_Penjual_id_pengguna`,`Pesanan_Pembeli_id_pengguna`,`Produk_id_produk`,`Produk_Penjual_id_pengguna`),
  ADD KEY `fk_detail_pesanan` (`Pesanan_no_pesanan`,`Pesanan_Penjual_id_pengguna`,`Pesanan_Pembeli_id_pengguna`),
  ADD KEY `fk_detail_produk` (`Produk_id_produk`,`Produk_Penjual_id_pengguna`),
  ADD KEY `fk_detail_pengembalian` (`Pengembalian_id_pengembalian`);

--
-- Indexes for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD PRIMARY KEY (`id_keranjang`,`Pembeli_id_pengguna`),
  ADD KEY `fk_keranjang_pembeli` (`Pembeli_id_pengguna`),
  ADD KEY `fk_keranjang_produk` (`Produk_id_produk`,`Produk_Penjual_id_pengguna`);

--
-- Indexes for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `pengelola`
--
ALTER TABLE `pengelola`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id_pengembalian`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `penjual`
--
ALTER TABLE `penjual`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `pesan`
--
ALTER TABLE `pesan`
  ADD PRIMARY KEY (`id_pesan`,`Penjual_id_pengguna`,`Pembeli_id_pengguna`),
  ADD KEY `fk_pesan_penjual` (`Penjual_id_pengguna`),
  ADD KEY `fk_pesan_pembeli` (`Pembeli_id_pengguna`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`no_pesanan`,`Penjual_id_pengguna`,`Pembeli_id_pengguna`),
  ADD KEY `fk_pesanan_penjual` (`Penjual_id_pengguna`),
  ADD KEY `fk_pesanan_pembeli` (`Pembeli_id_pengguna`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`,`Penjual_id_pengguna`),
  ADD KEY `fk_produk_penjual` (`Penjual_id_pengguna`);

--
-- Indexes for table `top_up`
--
ALTER TABLE `top_up`
  ADD PRIMARY KEY (`id_top_up`,`Pembeli_id_pengguna`),
  ADD KEY `fk_topup_pembeli` (`Pembeli_id_pengguna`);

--
-- Indexes for table `ulasan`
--
ALTER TABLE `ulasan`
  ADD PRIMARY KEY (`id_ulasan`,`Pembeli_id_pengguna`),
  ADD KEY `fk_ulasan_pembeli` (`Pembeli_id_pengguna`),
  ADD KEY `fk_ulasan_produk` (`Produk_id_produk`,`Produk_Penjual_id_pengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  MODIFY `id_detail` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `keranjang`
--
ALTER TABLE `keranjang`
  MODIFY `id_keranjang` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pengembalian`
--
ALTER TABLE `pengembalian`
  MODIFY `id_pengembalian` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pesan`
--
ALTER TABLE `pesan`
  MODIFY `id_pesan` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `no_pesanan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `top_up`
--
ALTER TABLE `top_up`
  MODIFY `id_top_up` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ulasan`
--
ALTER TABLE `ulasan`
  MODIFY `id_ulasan` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD CONSTRAINT `fk_detail_pengembalian` FOREIGN KEY (`Pengembalian_id_pengembalian`) REFERENCES `pengembalian` (`id_pengembalian`),
  ADD CONSTRAINT `fk_detail_pesanan` FOREIGN KEY (`Pesanan_no_pesanan`,`Pesanan_Penjual_id_pengguna`,`Pesanan_Pembeli_id_pengguna`) REFERENCES `pesanan` (`no_pesanan`, `Penjual_id_pengguna`, `Pembeli_id_pengguna`),
  ADD CONSTRAINT `fk_detail_produk` FOREIGN KEY (`Produk_id_produk`,`Produk_Penjual_id_pengguna`) REFERENCES `produk` (`id_produk`, `Penjual_id_pengguna`);

--
-- Constraints for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD CONSTRAINT `fk_keranjang_pembeli` FOREIGN KEY (`Pembeli_id_pengguna`) REFERENCES `pembeli` (`id_pengguna`),
  ADD CONSTRAINT `fk_keranjang_produk` FOREIGN KEY (`Produk_id_produk`,`Produk_Penjual_id_pengguna`) REFERENCES `produk` (`id_produk`, `Penjual_id_pengguna`);

--
-- Constraints for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD CONSTRAINT `fk_pembeli_pengguna` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `pengelola`
--
ALTER TABLE `pengelola`
  ADD CONSTRAINT `fk_pengelola_pengguna` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `penjual`
--
ALTER TABLE `penjual`
  ADD CONSTRAINT `fk_penjual_pengguna` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `pesan`
--
ALTER TABLE `pesan`
  ADD CONSTRAINT `fk_pesan_pembeli` FOREIGN KEY (`Pembeli_id_pengguna`) REFERENCES `pembeli` (`id_pengguna`),
  ADD CONSTRAINT `fk_pesan_penjual` FOREIGN KEY (`Penjual_id_pengguna`) REFERENCES `penjual` (`id_pengguna`);

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `fk_pesanan_pembeli` FOREIGN KEY (`Pembeli_id_pengguna`) REFERENCES `pembeli` (`id_pengguna`),
  ADD CONSTRAINT `fk_pesanan_penjual` FOREIGN KEY (`Penjual_id_pengguna`) REFERENCES `penjual` (`id_pengguna`);

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `fk_produk_penjual` FOREIGN KEY (`Penjual_id_pengguna`) REFERENCES `penjual` (`id_pengguna`);

--
-- Constraints for table `top_up`
--
ALTER TABLE `top_up`
  ADD CONSTRAINT `fk_topup_pembeli` FOREIGN KEY (`Pembeli_id_pengguna`) REFERENCES `pembeli` (`id_pengguna`);

--
-- Constraints for table `ulasan`
--
ALTER TABLE `ulasan`
  ADD CONSTRAINT `fk_ulasan_pembeli` FOREIGN KEY (`Pembeli_id_pengguna`) REFERENCES `pembeli` (`id_pengguna`),
  ADD CONSTRAINT `fk_ulasan_produk` FOREIGN KEY (`Produk_id_produk`,`Produk_Penjual_id_pengguna`) REFERENCES `produk` (`id_produk`, `Penjual_id_pengguna`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
