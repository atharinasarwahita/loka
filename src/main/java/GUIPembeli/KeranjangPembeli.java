/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIPembeli;

import controll.LokaControl;
import model.Keranjang;
import model.Produk;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class KeranjangPembeli extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KeranjangPembeli.class.getName());

    private int idPembeli;
    private LokaControl control = new LokaControl();

    public KeranjangPembeli() {
        initComponents();
        List<model.Pembeli> listPembeli = control.ambilSemuaPembeli();
        if (listPembeli != null && !listPembeli.isEmpty()) {
            this.idPembeli = listPembeli.get(0).getIdPengguna();
        } else {
            this.idPembeli = 1;
        }
        muatNamaDanSaldoUser();
        muatDataKeranjang();
    }

    public KeranjangPembeli(int idPembeli) {
        initComponents();
        this.idPembeli = idPembeli;
        muatNamaDanSaldoUser();
        muatDataKeranjang();
    }
    
    public int getIdPembeli() {
        return this.idPembeli;
    }
    
    private Double mintaJarak() {
        while (true) {
            String input = JOptionPane.showInputDialog(this,
                    "Masukkan jarak pengiriman (km):",
                    "Jarak Pengiriman",
                    JOptionPane.QUESTION_MESSAGE);

            if (input == null) return null;   // user pencet Cancel / close

            try {
                double jarak = Double.parseDouble(input.trim().replace(',', '.'));
                if (jarak <= 0) {
                    JOptionPane.showMessageDialog(this, "Jarak harus lebih dari 0!",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                if (jarak > 25) {
                    JOptionPane.showMessageDialog(this, "Maksimal jarak layanan 25 km!",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                return jarak;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan angka yang valid (contoh: 4.5)",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void muatDataKeranjang() {
        panelPenampung.removeAll();
        panelPenampung.setLayout(new javax.swing.BoxLayout(panelPenampung, javax.swing.BoxLayout.Y_AXIS));

        List<Keranjang> listKeranjang = control.ambilSemuaKeranjang();
        List<Produk> listProduk = control.ambilSemuaProduk();

        if (listKeranjang != null && listProduk != null) {
            for (Keranjang k : listKeranjang) {
                if (k.getPembeliIdPengguna() == this.idPembeli) {
                    String namaProduk = "Produk Tidak Dikenal";
                    double harga = 0.0;
                    String gambar = "default.png";

                    for (Produk p : listProduk) {
                        if (p.getIdProduk() == k.getProdukIdProduk()) {
                            namaProduk = p.getNamaProduk();
                            harga = p.getHarga();
                            gambar = p.getGambarProduk();
                            break;
                        }
                    }

                    ItemKeranjang item = new ItemKeranjang(k.getProdukIdProduk(), namaProduk, harga, k.getJumlah(), gambar);
                    item.getCheckBoxPilih().addItemListener(e -> hitungTotalTagihan());

                    item.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, item.getPreferredSize().height));
                    panelPenampung.add(item);
                }
            }
        }
        panelPenampung.revalidate();
        panelPenampung.repaint();
        hitungTotalTagihan();
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

    public void hitungTotalTagihan() {
        double total = 0.0;
        for (java.awt.Component comp : panelPenampung.getComponents()) {
            if (comp instanceof ItemKeranjang) {
                ItemKeranjang item = (ItemKeranjang) comp;
                if (item.isSelected()) {
                    total += item.getSubtotal();
                }
            }
        }
        jLabel13.setText("Rp " + String.format("%,.0f", total).replace(',', '.'));
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
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jBtnLanjutCheckout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelPenampung = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jBtnTopUpSaldoPembeli = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLblSaldoAktif = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

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

        jBtnKeranjangPembeli.setBackground(new java.awt.Color(253, 239, 232));
        jBtnKeranjangPembeli.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnKeranjangPembeli.setForeground(new java.awt.Color(228, 92, 16));
        jBtnKeranjangPembeli.setText("Keranjang");
        jBtnKeranjangPembeli.setAlignmentX(0.5F);
        jBtnKeranjangPembeli.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnKeranjangPembeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnKeranjangPembeli.setOpaque(true);
        jBtnKeranjangPembeli.addActionListener(this::jBtnKeranjangPembeliActionPerformed);

        jBtnPesanan.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnPesanan.setForeground(new java.awt.Color(71, 85, 105));
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
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(228, 92, 16));
        jLabel2.setText("Keranjang");

        jLabel4.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(75, 93, 22));
        jLabel4.setText("Produk");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 776, -1));

        jPanel5.setBackground(new java.awt.Color(228, 92, 16));
        jPanel5.setForeground(new java.awt.Color(228, 92, 16));

        jLabel3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Produk");

        jLabel5.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga");

        jLabel6.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jumlah");

        jLabel7.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Subtotal");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(17, 17, 17))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 47, 757, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(2, 44, 34));
        jLabel8.setText("Total tagihan keranjang");

        jLabel13.setBackground(new java.awt.Color(153, 153, 153));
        jLabel13.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(234, 88, 12));
        jLabel13.setText("Rp140.000");

        jBtnLanjutCheckout.setBackground(new java.awt.Color(228, 92, 16));
        jBtnLanjutCheckout.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jBtnLanjutCheckout.setForeground(new java.awt.Color(255, 255, 255));
        jBtnLanjutCheckout.setText("Lanjut ke Checkout");
        jBtnLanjutCheckout.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnLanjutCheckout.addActionListener(this::jBtnLanjutCheckoutActionPerformed);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnLanjutCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jBtnLanjutCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 750, -1));

        panelPenampung.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPenampungLayout = new javax.swing.GroupLayout(panelPenampung);
        panelPenampung.setLayout(panelPenampungLayout);
        panelPenampungLayout.setHorizontalGroup(
            panelPenampungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        panelPenampungLayout.setVerticalGroup(
            panelPenampungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelPenampung);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 115, -1, 460));

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
                .addComponent(jLblSaldoAktif, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
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

        jLabel16.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(228, 92, 16));
        jLabel16.setText("Halo, User!");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
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
        new GUIPembeli.BerandaPembeli(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnBerandaPembeliActionPerformed

    private void jBtnPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesananActionPerformed
        new GUIPembeli.Pesanan(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnPesananActionPerformed

    private void jBtnChatPenjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChatPenjualActionPerformed
        new GUIPembeli.ChatPenjual(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnChatPenjualActionPerformed

    private void jBtnKeranjangPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeranjangPembeliActionPerformed
        new GUIPembeli.KeranjangPembeli(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnKeranjangPembeliActionPerformed

    private void jBtnLanjutCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLanjutCheckoutActionPerformed
        int itemDipilih = 0;
        int idProdukTerpilih = -1;
        int jumlahTerpilih = 0;

        for (java.awt.Component comp : panelPenampung.getComponents()) {
            if (comp instanceof ItemKeranjang) {
                ItemKeranjang item = (ItemKeranjang) comp;
                if (item.isSelected()) {
                    itemDipilih++;
                    idProdukTerpilih = item.getIdProduk();
                    jumlahTerpilih = item.getJumlah();
                }
            }
        }

        if (itemDipilih == 0) {
            JOptionPane.showMessageDialog(this, "Pilih 1 produk yang ingin di-checkout terlebih dahulu!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (itemDipilih > 1) {
            JOptionPane.showMessageDialog(this, "Sistem hanya mengizinkan 1 produk untuk di-checkout dalam sekali transaksi!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Double jarak = mintaJarak();
        if (jarak == null) return;

        new CheckoutPembeli(idPembeli, idProdukTerpilih, jumlahTerpilih, jarak, true).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnLanjutCheckoutActionPerformed

    private void jBtnTopUpSaldoPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTopUpSaldoPembeliActionPerformed
        new GUIPembeli.TopUpSaldoPembeli(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnTopUpSaldoPembeliActionPerformed

    private void jBtnProfilPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnProfilPembeliActionPerformed
        new GUIPembeli.ProfilPembeli(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnProfilPembeliActionPerformed

    private void jBtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeluarActionPerformed
        new GUIUmum.Beranda().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnKeluarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new KeranjangPembeli().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBerandaPembeli;
    private javax.swing.JButton jBtnChatPenjual;
    private javax.swing.JButton jBtnKeluar;
    private javax.swing.JButton jBtnKeranjangPembeli;
    private javax.swing.JButton jBtnLanjutCheckout;
    private javax.swing.JButton jBtnPesanan;
    private javax.swing.JButton jBtnProfilPembeli;
    private javax.swing.JButton jBtnTopUpSaldoPembeli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLblSaldoAktif;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPenampung;
    // End of variables declaration//GEN-END:variables
}
