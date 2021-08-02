-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 02 Agu 2021 pada 11.36
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kemahasiswaan_10119006_10119022`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_mahasiswa`
--

CREATE TABLE `t_mahasiswa` (
  `nim` varchar(8) NOT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `ttl` varchar(20) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `alamat` varchar(40) DEFAULT NULL,
  `kelas` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_mahasiswa`
--

INSERT INTO `t_mahasiswa` (`nim`, `nama`, `ttl`, `tgl_lahir`, `alamat`, `kelas`) VALUES
('10102102', 'Ujang Komasudin', 'Bandung', '1998-06-17', 'Jl. Cikijing 15', 'IF-2'),
('10102103', 'Hendra Dedi Supriadi', 'Sumedang', '1994-07-14', 'Jl. Ciamplas 20', 'IF-2'),
('10102104', 'Willy Samuel', 'Serang', '1996-04-24', 'Jl. Sukajadi 49', 'IF-1'),
('10105120', 'Hendro Herlambang', 'Bandung', '1988-05-21', 'Jl. Tubagus Ismail No 5', 'IF-4'),
('10105121', 'Ratu Husna', 'Bandung', '1988-03-20', 'Jl. Cimandiri 15', 'IF-5'),
('10105122', 'Angga Setiyadi', 'Bandung', '1988-10-13', 'Jl. Sekeloa 20', 'IF-1'),
('10105123', 'Valen', 'Manado', '1988-02-14', 'Jl. Gedebage 80', 'IF-10');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_mata_kuliah`
--

CREATE TABLE `t_mata_kuliah` (
  `kd_mk` varchar(8) NOT NULL,
  `nama_mk` varbinary(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_mata_kuliah`
--

INSERT INTO `t_mata_kuliah` (`kd_mk`, `nama_mk`) VALUES
('IF200012', 0x50656d6f6772616d616e204461736172),
('IF34348', 0x50656d6f6772616d616e204c616e6a7574),
('IF37325P', 0x4b6f6d70757465722047726166696b),
('IF99191', 0x416c676f7269746d61),
('IF99192', 0x54656f72656d6120426168617361);

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_nilai`
--

CREATE TABLE `t_nilai` (
  `kd_nilai` int(11) NOT NULL,
  `nim` varchar(8) DEFAULT NULL,
  `kd_mk` varchar(8) DEFAULT NULL,
  `kehadiran` double DEFAULT NULL,
  `tugas1` double DEFAULT NULL,
  `tugas2` double DEFAULT NULL,
  `tugas3` double DEFAULT NULL,
  `uts` double DEFAULT NULL,
  `uas` double DEFAULT NULL,
  `nilai_absen` double DEFAULT NULL,
  `nilai_tugas` double DEFAULT NULL,
  `nilai_uts` double DEFAULT NULL,
  `nilai_uas` double DEFAULT NULL,
  `nilai_akhir` double DEFAULT NULL,
  `index` char(1) DEFAULT NULL,
  `ket` varchar(11) DEFAULT NULL,
  `angkatan` year(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_nilai`
--

INSERT INTO `t_nilai` (`kd_nilai`, `nim`, `kd_mk`, `kehadiran`, `tugas1`, `tugas2`, `tugas3`, `uts`, `uas`, `nilai_absen`, `nilai_tugas`, `nilai_uts`, `nilai_uas`, `nilai_akhir`, `index`, `ket`, `angkatan`) VALUES
(28, '10102104', 'IF34348', 13, 45, 67, 87, 90, 55, 4.64, 16.58, 27, 22, 70.23, 'B', 'Lulus', 2021),
(30, '10105121', 'IF37325P', 13, 56, 78, 34, 90, 78, 4.64, 14, 27, 31.2, 76.84, 'B', 'Lulus', 2021),
(32, '10102103', 'IF99192', 10, 78, 90, 56, 67, 89, 3.57, 18.67, 20.1, 35.6, 77.94, 'B', 'Tidak Lulus', 2021),
(33, '10105122', 'IF99191', 14, 89, 67, 90, 89, 78, 5, 20.5, 26.7, 31.2, 83.4, 'A', 'Lulus', 2021),
(34, '10105123', 'IF34348', 12, 56, 89, 67, 56, 67, 4.29, 17.67, 16.8, 26.8, 65.55, 'C', 'Lulus', 2021),
(35, '10102102', 'IF99191', 11, 56, 98, 45, 45, 34, 3.93, 16.58, 13.5, 13.6, 47.61, 'D', 'Tidak Lulus', 2021),
(36, '10105120', 'IF200012', 9, 78, 56, 90, 90, 89, 3.21, 18.67, 27, 35.6, 84.48, 'A', 'Tidak Lulus', 2021),
(37, '10102103', 'IF37325P', 14, 56, 89, 87, 89, 90, 5, 19.33, 26.7, 36, 87.03, 'A', 'Lulus', 2021);

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_nilai_akhir`
--

CREATE TABLE `t_nilai_akhir` (
  `kd_nilai_akhir` int(11) NOT NULL,
  `kd_mk` varchar(8) DEFAULT NULL,
  `persen_absen` double DEFAULT NULL,
  `persen_tugas` double DEFAULT NULL,
  `persen_uts` double DEFAULT NULL,
  `persen_uas` double DEFAULT NULL,
  `kehadiran` double DEFAULT NULL,
  `tugas1` double DEFAULT NULL,
  `tugas2` double DEFAULT NULL,
  `tugas3` double DEFAULT NULL,
  `uts` double DEFAULT NULL,
  `uas` double DEFAULT NULL,
  `nilai_absen` double DEFAULT NULL,
  `nilai_tugas` double DEFAULT NULL,
  `nilai_uts` double DEFAULT NULL,
  `nilai_uas` double DEFAULT NULL,
  `nilai_akhir` double DEFAULT NULL,
  `index` char(1) DEFAULT NULL,
  `ket` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_nilai_akhir`
--

INSERT INTO `t_nilai_akhir` (`kd_nilai_akhir`, `kd_mk`, `persen_absen`, `persen_tugas`, `persen_uts`, `persen_uas`, `kehadiran`, `tugas1`, `tugas2`, `tugas3`, `uts`, `uas`, `nilai_absen`, `nilai_tugas`, `nilai_uts`, `nilai_uas`, `nilai_akhir`, `index`, `ket`) VALUES
(1, 'IF99191', 25, 25, 25, 25, 13, 45, 43, 34, 45, 30, 23.21, 10.17, 11.25, 7.5, 52.13, 'D', 'Tidak Lulus'),
(2, 'IF37325P', 5, 15, 30, 40, 10, 89, 56, 78, 56, 78, 3.57, 11.15, 16.8, 31.2, 62.72, 'C', 'Tidak Lulus'),
(3, 'IF200012', 5, 15, 30, 40, 14, 78, 56, 89, 78, 90, 5, 11.15, 23.4, 36, 75.55, 'B', 'Lulus'),
(5, 'IF99192', 10, 20, 30, 40, 12, 67, 45, 89, 90, 78, 8.57, 13.4, 27, 31.2, 80.17, 'A', 'Lulus'),
(6, 'IF34348', 10, 25, 25, 40, 11, 45, 67, 78, 90, 89, 7.86, 15.83, 22.5, 35.6, 81.79, 'A', 'Lulus'),
(7, 'IF99191', 25, 25, 25, 25, 13, 45, 43, 34, 45, 30, 23.21, 10.17, 11.25, 7.5, 52.13, 'D', 'Tidak Lulus'),
(8, 'IF200012', 5, 15, 40, 40, 14, 56, 87, 45, 89, 67, 5, 9.4, 35.6, 26.8, 76.8, 'B', 'Lulus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_paket`
--

CREATE TABLE `t_paket` (
  `kd_paket` varchar(2) NOT NULL,
  `nama_pkt` varchar(20) NOT NULL,
  `harga_pkt` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_paket`
--

INSERT INTO `t_paket` (`kd_paket`, `nama_pkt`, `harga_pkt`) VALUES
('P1', 'Ekonomis', 6000),
('P2', 'Reguler', 7000),
('P3', 'Express', 8000),
('P4', 'Super', 12500);

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_pelanggan`
--

CREATE TABLE `t_pelanggan` (
  `id_pelanggan` int(5) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `telepon` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_pelanggan`
--

INSERT INTO `t_pelanggan` (`id_pelanggan`, `nama`, `alamat`, `telepon`) VALUES
(11111, 'Rizki', 'Pamuruyan', '08129554322'),
(11112, 'Kanjeng', 'Tubagus', '0845595929'),
(11113, 'Ningrat', 'Bakti', '0852048315'),
(11114, 'Nuri Anjasmara', 'Ahmad Yani', '0896959655'),
(11115, 'Zaki', 'Nusa Indah', '0822534721'),
(11116, 'Asep', 'Melati', '0823462362'),
(11133, 'Siti Nur', 'Jl. Ciputat No 16', '0885347244');

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_transaksi`
--

CREATE TABLE `t_transaksi` (
  `id_transaksi` int(5) NOT NULL,
  `id_pelanggan` int(3) DEFAULT NULL,
  `jmlh_cucian` int(5) DEFAULT NULL,
  `kd_paket` varchar(2) DEFAULT NULL,
  `total_berat` int(5) DEFAULT NULL,
  `total_harga` int(10) DEFAULT NULL,
  `tgl_masuk` date DEFAULT current_timestamp(),
  `keterangan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_transaksi`
--

INSERT INTO `t_transaksi` (`id_transaksi`, `id_pelanggan`, `jmlh_cucian`, `kd_paket`, `total_berat`, `total_harga`, `tgl_masuk`, `keterangan`) VALUES
(17288, 11111, 6, 'P3', 5, 40000, '2021-04-21', 'Sepatu'),
(17289, 11112, 3, 'P1', 2, 12000, '2021-08-02', 'Kemeja'),
(17290, 11113, 8, 'P2', 2, 14000, '2021-04-24', 'Celana Jeans'),
(17291, 11114, 1, 'P3', 2, 16000, '2021-04-20', 'Sarung bantal'),
(17292, 11115, 5, 'P4', 3, 37000, '2021-04-19', 'Selimut'),
(17293, 11116, 6, 'P2', 3, 21000, '2021-08-02', 'Jeans'),
(17309, 11133, 4, 'P1', 3, 18000, '2021-08-02', 'Baju Renang');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `t_mahasiswa`
--
ALTER TABLE `t_mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indeks untuk tabel `t_mata_kuliah`
--
ALTER TABLE `t_mata_kuliah`
  ADD PRIMARY KEY (`kd_mk`);

--
-- Indeks untuk tabel `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD PRIMARY KEY (`kd_nilai`),
  ADD KEY `nim` (`nim`),
  ADD KEY `kd_mk` (`kd_mk`);

--
-- Indeks untuk tabel `t_nilai_akhir`
--
ALTER TABLE `t_nilai_akhir`
  ADD PRIMARY KEY (`kd_nilai_akhir`),
  ADD KEY `kd_mk` (`kd_mk`);

--
-- Indeks untuk tabel `t_paket`
--
ALTER TABLE `t_paket`
  ADD PRIMARY KEY (`kd_paket`);

--
-- Indeks untuk tabel `t_pelanggan`
--
ALTER TABLE `t_pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `t_transaksi`
--
ALTER TABLE `t_transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pelanggan_FK1` (`id_pelanggan`),
  ADD KEY `kd_paket_FK1` (`kd_paket`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `t_nilai`
--
ALTER TABLE `t_nilai`
  MODIFY `kd_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT untuk tabel `t_nilai_akhir`
--
ALTER TABLE `t_nilai_akhir`
  MODIFY `kd_nilai_akhir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `t_pelanggan`
--
ALTER TABLE `t_pelanggan`
  MODIFY `id_pelanggan` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11134;

--
-- AUTO_INCREMENT untuk tabel `t_transaksi`
--
ALTER TABLE `t_transaksi`
  MODIFY `id_transaksi` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17310;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD CONSTRAINT `t_nilai_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `t_mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_nilai_ibfk_2` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `t_nilai_akhir`
--
ALTER TABLE `t_nilai_akhir`
  ADD CONSTRAINT `t_nilai_akhir_ibfk_1` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `t_transaksi`
--
ALTER TABLE `t_transaksi`
  ADD CONSTRAINT `id_pelanggan_FK1` FOREIGN KEY (`id_pelanggan`) REFERENCES `t_pelanggan` (`id_pelanggan`) ON DELETE CASCADE,
  ADD CONSTRAINT `kd_paket_FK1` FOREIGN KEY (`kd_paket`) REFERENCES `t_paket` (`kd_paket`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
