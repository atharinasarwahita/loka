/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIPembeli;

import controll.LokaControl;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Pesanan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pesanan.class.getName());

    private int idPembeli;
    private LokaControl control = new LokaControl();
    
    public Pesanan() {
        initComponents();
        this.idPembeli = 7;
        muatNamaDanSaldoUser();
        muatDataPesanan("Semua");
    }

    public Pesanan(int idPembeli) {
        this.idPembeli = idPembeli;
        initComponents();
        muatNamaDanSaldoUser();
        muatDataPesanan("Semua");
    }

    public void muatNamaDanSaldoUser() {
        for (model.Pembeli p : control.ambilSemuaPembeli()) {
            if (p.getIdPengguna() == this.idPembeli) {
                jLabel16.setText("Halo, " + p.getNamaLengkap() + "!");
                jLblSaldoAktif.setText("Rp " + String.format("%,.0f", p.getSaldo()).replace(',', '.'));
                break;
            }
        }
    }

    public void muatDataPesanan(String statusFilter) {
        panelPenampung.removeAll();
        panelPenampung.setLayout(new javax.swing.BoxLayout(panelPenampung, javax.swing.BoxLayout.Y_AXIS));

        List<model.Pesanan> listPesanan = control.ambilSemuaPesanan();
        List<model.DetailPesanan> listDetail = control.ambilSemuaDetailPesanan();
        List<model.Produk> listProduk = control.ambilSemuaProduk();
        List<model.Penjual> listPenjual = control.ambilSemuaPenjual(); // 1. Tarik data semua penjual

        int jumlahDataDitemukan = 0;

        if (listPesanan != null) {
            for (model.Pesanan p : listPesanan) {
                if (p.getPembeliIdPengguna() == this.idPembeli) {

                    String statusPesananStr = p.getStatusPesanan() != null ? p.getStatusPesanan().toString() : "";

                    boolean cocokStatus = statusFilter.equalsIgnoreCase("Semua") || 
                                          statusPesananStr.equalsIgnoreCase(statusFilter) ||
                                          (statusFilter.equalsIgnoreCase("Belum Bayar") && statusPesananStr.equalsIgnoreCase("Menunggu"));

                    if (cocokStatus) {
                        jumlahDataDitemukan++;

                        String namaProduk = "Produk Tidak Dikenal";
                        double subtotal = 0;
                        int jumlah = 0;
                        String namaToko = "Toko LOKA";
                        String gambar = "default.png";

                        if (listDetail != null) {
                            for (model.DetailPesanan d : listDetail) {
                                if (d.getPesananNoPesanan() == p.getNoPesanan()) {
                                    jumlah = d.getJumlahProduk();
                                    subtotal = d.getSubTotal();

                                    if (listProduk != null) {
                                        for (model.Produk prod : listProduk) {
                                            if (prod.getIdProduk() == d.getProdukIdProduk()) {
                                                namaProduk = prod.getNamaProduk();
                                                gambar = prod.getGambarProduk();

                                                if (listPenjual != null) {
                                                    for (model.Penjual penjual : listPenjual) {
                                                        if (penjual.getIdPengguna() == prod.getPenjualIdPengguna()) {
                                                            namaToko = penjual.getNamaToko();
                                                            break;
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }

                        PesananRows row = new PesananRows(namaToko, namaProduk, jumlah, subtotal, statusPesananStr, gambar);
                        row.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));
                        row.setAlignmentX(javax.swing.JComponent.LEFT_ALIGNMENT);

                        panelPenampung.add(row);
                        panelPenampung.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
                    }
                }
            }
        }

        if (jumlahDataDitemukan == 0) {
            javax.swing.JLabel lblKosong = new javax.swing.JLabel("Tidak ada pesanan dengan status " + statusFilter);
            lblKosong.setFont(new java.awt.Font("Inter", 1, 14));
            lblKosong.setForeground(new java.awt.Color(150, 150, 150));
            lblKosong.setAlignmentX(javax.swing.JComponent.CENTER_ALIGNMENT);
            panelPenampung.add(lblKosong);
        }

        panelPenampung.revalidate();
        panelPenampung.repaint();
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
        jBtnPesanan = new javax.swing.JButton();
        jBtnChatPenjual = new javax.swing.JButton();
        jBtnProfilPembeli = new javax.swing.JButton();
        jBtnKeluar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jBtnSortSemua = new javax.swing.JButton();
        jBtnSortBelumBayar = new javax.swing.JButton();
        jBtnSortDiproses = new javax.swing.JButton();
        jBtnSortDikirim = new javax.swing.JButton();
        jBtnSortSelesai = new javax.swing.JButton();
        jBtnSortPengembalian = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelPenampung = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jBtnTopUpSaldoPembeli = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLblSaldoAktif = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(224, 693));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Logo Loka Pembeli.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jBtnBerandaPembeli.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnBerandaPembeli.setForeground(new java.awt.Color(71, 85, 105));
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
        jBtnKeranjangPembeli.setOpaque(true);
        jBtnKeranjangPembeli.addActionListener(this::jBtnKeranjangPembeliActionPerformed);

        jBtnPesanan.setBackground(new java.awt.Color(253, 239, 232));
        jBtnPesanan.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnPesanan.setForeground(new java.awt.Color(228, 92, 16));
        jBtnPesanan.setText("Pesanan");
        jBtnPesanan.setAlignmentX(0.5F);
        jBtnPesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnPesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnPesanan.addActionListener(this::jBtnPesananActionPerformed);

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
        jBtnKeluar.setForeground(new java.awt.Color(239, 68, 68));
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
                    .addComponent(jBtnChatPenjual, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jBtnProfilPembeli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))))
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
                .addComponent(jBtnPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnChatPenjual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnProfilPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnKeluar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBackground(new java.awt.Color(228, 92, 16));

        jLabel2.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Daftar Pesanan");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jBtnSortSemua.setBackground(new java.awt.Color(228, 92, 16));
        jBtnSortSemua.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnSortSemua.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSortSemua.setText("Semua");
        jBtnSortSemua.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnSortSemua.addActionListener(this::jBtnSortSemuaActionPerformed);

        jBtnSortBelumBayar.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnSortBelumBayar.setForeground(new java.awt.Color(71, 85, 105));
        jBtnSortBelumBayar.setText("Belum Bayar");
        jBtnSortBelumBayar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jBtnSortBelumBayar.addActionListener(this::jBtnSortBelumBayarActionPerformed);

        jBtnSortDiproses.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnSortDiproses.setForeground(new java.awt.Color(71, 85, 105));
        jBtnSortDiproses.setText("Diproses");
        jBtnSortDiproses.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jBtnSortDiproses.addActionListener(this::jBtnSortDiprosesActionPerformed);

        jBtnSortDikirim.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnSortDikirim.setForeground(new java.awt.Color(71, 85, 105));
        jBtnSortDikirim.setText("Dikirim");
        jBtnSortDikirim.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jBtnSortDikirim.addActionListener(this::jBtnSortDikirimActionPerformed);

        jBtnSortSelesai.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnSortSelesai.setForeground(new java.awt.Color(71, 85, 105));
        jBtnSortSelesai.setText("Selesai");
        jBtnSortSelesai.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jBtnSortSelesai.addActionListener(this::jBtnSortSelesaiActionPerformed);

        jBtnSortPengembalian.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnSortPengembalian.setForeground(new java.awt.Color(71, 85, 105));
        jBtnSortPengembalian.setText("Pengembalian");
        jBtnSortPengembalian.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jBtnSortPengembalian.addActionListener(this::jBtnSortPengembalianActionPerformed);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        panelPenampung.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPenampungLayout = new javax.swing.GroupLayout(panelPenampung);
        panelPenampung.setLayout(panelPenampungLayout);
        panelPenampungLayout.setHorizontalGroup(
            panelPenampungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        panelPenampungLayout.setVerticalGroup(
            panelPenampungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelPenampung);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jBtnSortSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnSortBelumBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnSortDiproses, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnSortDikirim, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnSortSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnSortPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSortSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSortBelumBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSortDiproses, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSortDikirim, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSortSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSortPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        jLabel16.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(228, 92, 16));
        jLabel16.setText("Halo, User!");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jBtnTopUpSaldoPembeli.setBackground(new java.awt.Color(228, 92, 16));
        jBtnTopUpSaldoPembeli.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jBtnTopUpSaldoPembeli.setForeground(new java.awt.Color(255, 255, 255));
        jBtnTopUpSaldoPembeli.setText("Top Up");
        jBtnTopUpSaldoPembeli.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnTopUpSaldoPembeli.addActionListener(this::jBtnTopUpSaldoPembeliActionPerformed);

        jLabel14.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel14.setText("Saldo:");

        jLblSaldoAktif.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLblSaldoAktif.setForeground(new java.awt.Color(228, 92, 16));
        jLblSaldoAktif.setText("Rp150.000");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblSaldoAktif, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnTopUpSaldoPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnTopUpSaldoPembeli, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(jLblSaldoAktif))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jBtnPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesananActionPerformed
        new GUIPembeli.Pesanan(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnPesananActionPerformed

    private void jBtnChatPenjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChatPenjualActionPerformed
        new GUIPembeli.ChatPenjual(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnChatPenjualActionPerformed

    private void jBtnProfilPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnProfilPembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnProfilPembeliActionPerformed

    private void jBtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeluarActionPerformed
        new GUIUmum.Beranda().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnKeluarActionPerformed

    private void jBtnTopUpSaldoPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTopUpSaldoPembeliActionPerformed
        new GUIPembeli.TopUpSaldoPembeli(this.idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnTopUpSaldoPembeliActionPerformed

    private void jBtnSortSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSortSemuaActionPerformed
        muatDataPesanan("Semua");
    }//GEN-LAST:event_jBtnSortSemuaActionPerformed

    private void jBtnSortBelumBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSortBelumBayarActionPerformed
        muatDataPesanan("Belum Bayar");
    }//GEN-LAST:event_jBtnSortBelumBayarActionPerformed

    private void jBtnSortDiprosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSortDiprosesActionPerformed
        muatDataPesanan("Diproses");
    }//GEN-LAST:event_jBtnSortDiprosesActionPerformed

    private void jBtnSortDikirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSortDikirimActionPerformed
        muatDataPesanan("Dikirim");
    }//GEN-LAST:event_jBtnSortDikirimActionPerformed

    private void jBtnSortSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSortSelesaiActionPerformed
        muatDataPesanan("Selesai");
    }//GEN-LAST:event_jBtnSortSelesaiActionPerformed

    private void jBtnSortPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSortPengembalianActionPerformed
        muatDataPesanan("Pengembalian");
    }//GEN-LAST:event_jBtnSortPengembalianActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Pesanan().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBerandaPembeli;
    private javax.swing.JButton jBtnChatPenjual;
    private javax.swing.JButton jBtnKeluar;
    private javax.swing.JButton jBtnKeranjangPembeli;
    private javax.swing.JButton jBtnPesanan;
    private javax.swing.JButton jBtnProfilPembeli;
    private javax.swing.JButton jBtnSortBelumBayar;
    private javax.swing.JButton jBtnSortDikirim;
    private javax.swing.JButton jBtnSortDiproses;
    private javax.swing.JButton jBtnSortPengembalian;
    private javax.swing.JButton jBtnSortSelesai;
    private javax.swing.JButton jBtnSortSemua;
    private javax.swing.JButton jBtnTopUpSaldoPembeli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLblSaldoAktif;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPenampung;
    // End of variables declaration//GEN-END:variables
}
