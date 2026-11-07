/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIPembeli;

import controll.LokaControl;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produk;

/**
 *
 * @author LENOVO
 */
public class DetailProduk extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DetailProduk.class.getName());

    private int idPembeli;
    private int idProduk;
    private LokaControl control = new LokaControl();
    private Produk produkAktif;

    public DetailProduk() {
        initComponents();
    }

    // Konstruktor utama ketika dipanggil dari Beranda
    public DetailProduk(int idPembeli, int idProduk) {
        this.idPembeli = idPembeli;
        this.idProduk = idProduk;
        initComponents();
        tampilkanDetailProduk();
        muatNamaDanSaldoUser();
    }

    private void tampilkanDetailProduk() {
        produkAktif = control.cariProduk(idProduk);
        if (produkAktif != null) {
            jLabel8.setText(produkAktif.getNamaProduk());
            jLabel9.setText("Rp " + String.format("%,.0f", produkAktif.getHarga()));
            jLabel10.setText(produkAktif.getDeskripsi());

            try {
                String namaFileGambar = produkAktif.getGambarProduk();
                if (namaFileGambar != null && !namaFileGambar.isEmpty()) {
                    java.net.URL imageUrl = getClass().getResource("/gambarproduk/" + namaFileGambar);
                    if (imageUrl != null) {
                        javax.swing.ImageIcon iconOriginal = new javax.swing.ImageIcon(imageUrl);
                        java.awt.Image imgSkala = iconOriginal.getImage().getScaledInstance(jLabel7.getWidth() > 0 ? jLabel7.getWidth() : 500, 247, java.awt.Image.SCALE_SMOOTH);
                        jLabel7.setIcon(new javax.swing.ImageIcon(imgSkala));
                        jLabel7.setText("");
                    } else {
                        jLabel7.setIcon(null);
                        jLabel7.setText("Gambar tidak ditemukan");
                    }
                } else {
                    jLabel7.setIcon(null);
                    jLabel7.setText("Tidak ada foto");
                }
            } catch (Exception e) {
                jLabel7.setIcon(null);
                jLabel7.setText("Error Load Gambar");
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Data produk tidak ditemukan!");
        }
    }
    
    public void muatNamaDanSaldoUser() {
        for (model.Pembeli p : control.ambilSemuaPembeli()) {
            if (p.getIdPengguna() == this.idPembeli) {
                jLblNamaUser.setText("Halo, " + p.getNamaLengkap() + "!");
                jLblSaldoAktif.setText("Rp " + String.format("%,.0f", p.getSaldo()).replace(',', '.'));
                break;
            }
        }
    }
    
    private void actionShareProduk() {
        if (produkAktif != null) {
            control.tambahJumlahShare(produkAktif.getIdProduk());
            
            String linkProduk = "https://loka-marketplace.com/produk/" + produkAktif.getIdProduk() + "-" + produkAktif.getNamaProduk().replaceAll(" ", "-");
            
            java.awt.datatransfer.StringSelection stringSelection = new java.awt.datatransfer.StringSelection(linkProduk);
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Link produk berhasil disalin ke clipboard!\n" + linkProduk + "", 
                "Bagikan Produk", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actionChatPenjual() {
        if (produkAktif != null) {
            int idPenjualTujuan = produkAktif.getPenjualIdPengguna();
            new GUIPembeli.ChatPenjual(this.idPembeli, idPenjualTujuan).setVisible(true);
            this.dispose();
        }
    }

    private void actionTambahKeranjang() {
        if (produkAktif == null) return;

        String inputJumlah = JOptionPane.showInputDialog(this, 
                "Masukkan jumlah produk yang ingin dimasukkan ke keranjang:", 
                "Jumlah Pembelian", JOptionPane.QUESTION_MESSAGE);

        if (inputJumlah != null && !inputJumlah.trim().isEmpty()) {
            try {
                int jumlah = Integer.parseInt(inputJumlah.trim());
                if (jumlah <= 0) {
                    JOptionPane.showMessageDialog(this, "Jumlah harus lebih dari 0!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (produkAktif.getStok() < jumlah) {
                    JOptionPane.showMessageDialog(this, "Stok tidak mencukupi! Sisa stok: " + produkAktif.getStok(), "Stok Habis", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Generate ID Keranjang otomatis
                int idKeranjang = 1;
                List<model.Keranjang> allK = control.ambilSemuaKeranjang();
                if (allK != null) {
                    for (model.Keranjang k : allK) {
                        if (k.getIdKeranjang() >= idKeranjang) {
                            idKeranjang = k.getIdKeranjang() + 1;
                        }
                    }
                }

                // Panggil method controller untuk simpan ke keranjang
                control.tambahKeranjang(idKeranjang, this.idPembeli, jumlah, new java.util.Date(), produkAktif.getIdProduk(), produkAktif.getPenjualIdPengguna());

                JOptionPane.showMessageDialog(this, "Berhasil memasukkan " + jumlah + " produk ke keranjang!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actionBeliSekarang() {
        if (produkAktif == null) return;

        if (produkAktif.getStok() < 1) {
            JOptionPane.showMessageDialog(this, "Stok produk habis!", "Stok Habis", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Pop-up input jarak untuk Beli Sekarang (default jumlah 1)
        String inputJarak = JOptionPane.showInputDialog(this, 
                "Masukkan jarak pengiriman (dalam km):", 
                "Jarak Pengiriman", JOptionPane.QUESTION_MESSAGE);

        if (inputJarak != null && !inputJarak.trim().isEmpty()) {
            try {
                double jarakKm = Double.parseDouble(inputJarak.trim());
                if (jarakKm < 0) {
                    JOptionPane.showMessageDialog(this, "Jarak tidak boleh minus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Lempar ke halaman CheckoutPembeli (membawa id pembeli, id produk, jumlah=1, dan jarak)
                new GUIPembeli.CheckoutPembeli(this.idPembeli, produkAktif.getIdProduk(), 1, jarakKm, false).setVisible(true);
                this.dispose();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan angka jarak yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBtnBerandaPembeli = new javax.swing.JButton();
        jBtnKeranjangPembeli = new javax.swing.JButton();
        jBtnPesananPembeli = new javax.swing.JButton();
        jBtnChatPenjual = new javax.swing.JButton();
        jBtnProfilPembeli = new javax.swing.JButton();
        jBtnKeluar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jBtnKeranjang = new javax.swing.JButton();
        jBtnBeliSkrg = new javax.swing.JButton();
        jBtnShare = new javax.swing.JButton();
        jBtnChat = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jBtnTopUpSaldoPembeli = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLblSaldoAktif = new javax.swing.JLabel();
        jLblNamaUser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(246, 244, 241));
        jPanel1.setMaximumSize(new java.awt.Dimension(1024, 796));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(224, 693));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Logo Loka Pembeli.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jBtnBerandaPembeli.setBackground(new java.awt.Color(253, 239, 232));
        jBtnBerandaPembeli.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnBerandaPembeli.setForeground(new java.awt.Color(228, 92, 16));
        jBtnBerandaPembeli.setText("Beranda");
        jBtnBerandaPembeli.setAlignmentX(0.5F);
        jBtnBerandaPembeli.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnBerandaPembeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnBerandaPembeli.addActionListener(this::jBtnBerandaPembeliActionPerformed);

        jBtnKeranjangPembeli.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnKeranjangPembeli.setForeground(new java.awt.Color(71, 85, 105));
        jBtnKeranjangPembeli.setText("Keranjang");
        jBtnKeranjangPembeli.setAlignmentX(0.5F);
        jBtnKeranjangPembeli.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnKeranjangPembeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnKeranjangPembeli.addActionListener(this::jBtnKeranjangPembeliActionPerformed);

        jBtnPesananPembeli.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnPesananPembeli.setForeground(new java.awt.Color(71, 85, 105));
        jBtnPesananPembeli.setText("Pesanan");
        jBtnPesananPembeli.setAlignmentX(0.5F);
        jBtnPesananPembeli.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnPesananPembeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnPesananPembeli.addActionListener(this::jBtnPesananPembeliActionPerformed);

        jBtnChatPenjual.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnChatPenjual.setForeground(new java.awt.Color(71, 85, 105));
        jBtnChatPenjual.setText("Chat Penjual");
        jBtnChatPenjual.setAlignmentX(0.5F);
        jBtnChatPenjual.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnChatPenjual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnChatPenjual.addActionListener(this::jBtnChatPenjualActionPerformed);

        jBtnProfilPembeli.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnProfilPembeli.setForeground(new java.awt.Color(71, 85, 105));
        jBtnProfilPembeli.setText("Profil");
        jBtnProfilPembeli.setAlignmentX(0.5F);
        jBtnProfilPembeli.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnProfilPembeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnProfilPembeli.addActionListener(this::jBtnProfilPembeliActionPerformed);

        jBtnKeluar.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnKeluar.setForeground(new java.awt.Color(71, 85, 105));
        jBtnKeluar.setText("Keluar");
        jBtnKeluar.setAlignmentX(0.5F);
        jBtnKeluar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnKeluar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnKeluar.addActionListener(this::jBtnKeluarActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnPesananPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnKeranjangPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jBtnBerandaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnKeluar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnProfilPembeli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnChatPenjual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jBtnBerandaPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnKeranjangPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnPesananPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnChatPenjual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnProfilPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnKeluar)
                .addContainerGap(311, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 730));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.setPreferredSize(new java.awt.Dimension(575, 477));

        jPanel8.setBackground(new java.awt.Color(228, 92, 16));
        jPanel8.setPreferredSize(new java.awt.Dimension(575, 55));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Foto");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(75, 93, 22));
        jLabel8.setText("Amplang Ikan Haruan");

        jLabel9.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(228, 92, 16));
        jLabel9.setText("Rp25.000");

        jLabel10.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Dibuat dari ikan haruan segar pilihan khas Sungai Mahakam");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jBtnKeranjang.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jBtnKeranjang.setForeground(new java.awt.Color(228, 92, 16));
        jBtnKeranjang.setText("+ Keranjang");
        jBtnKeranjang.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnKeranjang.addActionListener(this::jBtnKeranjangActionPerformed);

        jBtnBeliSkrg.setBackground(new java.awt.Color(228, 92, 16));
        jBtnBeliSkrg.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jBtnBeliSkrg.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBeliSkrg.setText("Beli Sekarang");
        jBtnBeliSkrg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnBeliSkrg.addActionListener(this::jBtnBeliSkrgActionPerformed);

        jBtnShare.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jBtnShare.setForeground(new java.awt.Color(228, 92, 16));
        jBtnShare.setText("Share");
        jBtnShare.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnShare.addActionListener(this::jBtnShareActionPerformed);

        jBtnChat.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jBtnChat.setForeground(new java.awt.Color(228, 92, 16));
        jBtnChat.setText("Chat");
        jBtnChat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnChat.addActionListener(this::jBtnChatActionPerformed);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jBtnShare, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnChat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnKeranjang, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnBeliSkrg, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnKeranjang, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jBtnBeliSkrg, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jBtnShare, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jBtnChat, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jPanel6.add(jPanel7, new java.awt.GridBagConstraints());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(228, 92, 16));
        jLabel5.setText("Detail");

        jLabel6.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(75, 93, 22));
        jLabel6.setText("Produk");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 56, 788, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jBtnTopUpSaldoPembeli.setBackground(new java.awt.Color(228, 92, 16));
        jBtnTopUpSaldoPembeli.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jBtnTopUpSaldoPembeli.setForeground(new java.awt.Color(255, 255, 255));
        jBtnTopUpSaldoPembeli.setText("Top Up");
        jBtnTopUpSaldoPembeli.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnTopUpSaldoPembeli.addActionListener(this::jBtnTopUpSaldoPembeliActionPerformed);

        jLabel2.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel2.setText("Saldo:");

        jLblSaldoAktif.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLblSaldoAktif.setForeground(new java.awt.Color(228, 92, 16));
        jLblSaldoAktif.setText("Rp150.000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblSaldoAktif, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnTopUpSaldoPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnTopUpSaldoPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLblSaldoAktif))
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(649, 6, -1, -1));

        jLblNamaUser.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLblNamaUser.setForeground(new java.awt.Color(228, 92, 16));
        jLblNamaUser.setText("Halo, User!");
        jLblNamaUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jPanel1.add(jLblNamaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 13, 267, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBerandaPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBerandaPembeliActionPerformed
        new GUIPembeli.BerandaPembeli(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnBerandaPembeliActionPerformed

    private void jBtnKeranjangPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeranjangPembeliActionPerformed
        new GUIPembeli.KeranjangPembeli(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnKeranjangPembeliActionPerformed

    private void jBtnPesananPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesananPembeliActionPerformed
        new GUIPembeli.Pesanan(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnPesananPembeliActionPerformed

    private void jBtnChatPenjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChatPenjualActionPerformed
        new GUIPembeli.ChatPenjual(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnChatPenjualActionPerformed

    private void jBtnTopUpSaldoPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTopUpSaldoPembeliActionPerformed
        new GUIPembeli.TopUpSaldoPembeli(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnTopUpSaldoPembeliActionPerformed

    private void jBtnProfilPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnProfilPembeliActionPerformed
        new GUIPembeli.ProfilPembeli(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnProfilPembeliActionPerformed

    private void jBtnKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeranjangActionPerformed
        actionTambahKeranjang();
    }//GEN-LAST:event_jBtnKeranjangActionPerformed

    private void jBtnBeliSkrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBeliSkrgActionPerformed
        actionBeliSekarang();
    }//GEN-LAST:event_jBtnBeliSkrgActionPerformed

    private void jBtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeluarActionPerformed
        new GUIUmum.Beranda().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnKeluarActionPerformed

    private void jBtnShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnShareActionPerformed
        actionShareProduk();
    }//GEN-LAST:event_jBtnShareActionPerformed

    private void jBtnChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChatActionPerformed
        actionChatPenjual();
    }//GEN-LAST:event_jBtnChatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DetailProduk().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBeliSkrg;
    private javax.swing.JButton jBtnBerandaPembeli;
    private javax.swing.JButton jBtnChat;
    private javax.swing.JButton jBtnChatPenjual;
    private javax.swing.JButton jBtnKeluar;
    private javax.swing.JButton jBtnKeranjang;
    private javax.swing.JButton jBtnKeranjangPembeli;
    private javax.swing.JButton jBtnPesananPembeli;
    private javax.swing.JButton jBtnProfilPembeli;
    private javax.swing.JButton jBtnShare;
    private javax.swing.JButton jBtnTopUpSaldoPembeli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblNamaUser;
    private javax.swing.JLabel jLblSaldoAktif;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
