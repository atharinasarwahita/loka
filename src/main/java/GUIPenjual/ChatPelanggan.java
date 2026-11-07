/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIPenjual;

import controll.LokaControl;
import model.Pesan;
import java.awt.*;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
public class ChatPelanggan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ChatPelanggan.class.getName());

    private LokaControl control = new LokaControl();
    private int idPenjual; // ID penjual dari sesi login
    private int idPembeliDipilih = -1;

    public ChatPelanggan(int idPenjual) {
        this.idPenjual = idPenjual;
        initComponents();
        muatListPesanMasuk();

        jLblNamaHeaderChat.setText("Pilih percakapan di samping");
        panelDaftar.removeAll();
        jScrollPaneChat.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneChat.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneChat.getVerticalScrollBar().setUnitIncrement(20);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }


    public ChatPelanggan() {
        this(10);
    }

    public void muatListPesanMasuk() {
        jPanelListKiri.removeAll();
        jPanelListKiri.setLayout(new BoxLayout(jPanelListKiri, BoxLayout.Y_AXIS));

        List<Pesan> listInbox = control.getInboxPenjual(idPenjual);
        if (listInbox != null) {
            for (Pesan item : listInbox) {
                PenjualPesanMasukk row = new PenjualPesanMasukk();
                row.setData(
                    item.getIdPembeli(),
                    item.getNamaPembeli(),
                    item.getIsiPesan(),
                    item.getStatusDibaca(),
                    this
                );
                row.setMaximumSize(new Dimension(Integer.MAX_VALUE, row.getPreferredSize().height));
                row.setAlignmentX(Component.LEFT_ALIGNMENT);
                jPanelListKiri.add(row);
                jPanelListKiri.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        jPanelListKiri.revalidate();
        jPanelListKiri.repaint();
    }

    public void bukaRoomChat(int idPembeli, String namaPembeli) {
        this.idPembeliDipilih = idPembeli;
        jLblNamaHeaderChat.setText(namaPembeli);
        muatIsiPercakapan(idPenjual, idPembeli);
    }

    public void muatIsiPercakapan(int idPenjualParam, int idPembeli) {
        panelDaftar.removeAll();
        panelDaftar.setLayout(new BoxLayout(panelDaftar, BoxLayout.Y_AXIS));

        List<Pesan> listChat = control.getDetailChat(idPenjualParam, idPembeli);
        if (listChat != null) {
            for (Pesan c : listChat) {
                JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                wrapper.setOpaque(false);

                String waktu = String.valueOf(c.getWaktuKirim()).replaceAll("\\.0$", "");

                BubbleChatPenjual bubble = new BubbleChatPenjual();
                bubble.setData(c.getIsiPesan(), waktu);

                bubble.setPreferredSize(new Dimension(470, bubble.getPreferredSize().height));
                wrapper.add(bubble);

                wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, wrapper.getPreferredSize().height));
                wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
                panelDaftar.add(wrapper);
                panelDaftar.add(Box.createRigidArea(new Dimension(0, 8)));
            }
        }
        panelDaftar.revalidate();
        panelDaftar.repaint();

        javax.swing.SwingUtilities.invokeLater(() ->
            jScrollPaneChat.getVerticalScrollBar().setValue(
                jScrollPaneChat.getVerticalScrollBar().getMaximum()));
    }
    

    private static final String PLACEHOLDER_BALAS = "Balas pesan...";

    private void kirimBalasan() {
        if (idPembeliDipilih < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih percakapan dulu!");
            return;
        }
        String isi = jTextField1.getText().trim();
        if (isi.isEmpty() || isi.equals(PLACEHOLDER_BALAS)) return;

        control.kirimPesan(0, idPenjual, idPembeliDipilih, isi,
                new java.util.Date(), enums.StatusDibacaPesan.SudahDibaca);

        jTextField1.setText("");
        muatIsiPercakapan(idPenjual, idPembeliDipilih); 
        muatListPesanMasuk();                             
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
        jBtnDashboard = new javax.swing.JButton();
        jBtnKelolaProduk = new javax.swing.JButton();
        jBtnDaftarPesanan = new javax.swing.JButton();
        jBtnChatPelanggan = new javax.swing.JButton();
        jBtnKeluar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLblNamaHeaderChat = new javax.swing.JLabel();
        jScrollPaneChat = new javax.swing.JScrollPane();
        panelDaftar = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelListKiri = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(224, 693));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/LogoLokaPenjual.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jBtnDashboard.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnDashboard.setForeground(new java.awt.Color(71, 85, 105));
        jBtnDashboard.setText("Dashboard");
        jBtnDashboard.setAlignmentX(0.5F);
        jBtnDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnDashboard.addActionListener(this::jBtnDashboardActionPerformed);

        jBtnKelolaProduk.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnKelolaProduk.setForeground(new java.awt.Color(71, 85, 105));
        jBtnKelolaProduk.setText("Kelola Produk");
        jBtnKelolaProduk.setAlignmentX(0.5F);
        jBtnKelolaProduk.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnKelolaProduk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnKelolaProduk.setOpaque(true);
        jBtnKelolaProduk.addActionListener(this::jBtnKelolaProdukActionPerformed);

        jBtnDaftarPesanan.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnDaftarPesanan.setForeground(new java.awt.Color(71, 85, 105));
        jBtnDaftarPesanan.setText("Daftar Pesanan");
        jBtnDaftarPesanan.setAlignmentX(0.5F);
        jBtnDaftarPesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnDaftarPesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnDaftarPesanan.setOpaque(true);
        jBtnDaftarPesanan.addActionListener(this::jBtnDaftarPesananActionPerformed);

        jBtnChatPelanggan.setBackground(new java.awt.Color(253, 239, 232));
        jBtnChatPelanggan.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnChatPelanggan.setForeground(new java.awt.Color(228, 92, 16));
        jBtnChatPelanggan.setText("Chat Pelanggan");
        jBtnChatPelanggan.setAlignmentX(0.5F);
        jBtnChatPelanggan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnChatPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnChatPelanggan.addActionListener(this::jBtnChatPelangganActionPerformed);

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
                    .addComponent(jBtnDaftarPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnKelolaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jBtnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnKeluar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addComponent(jBtnChatPelanggan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jBtnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnKelolaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnDaftarPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnChatPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(387, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(228, 92, 16));

        jLabel2.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pesan Masuk ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLblNamaHeaderChat.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLblNamaHeaderChat.setForeground(new java.awt.Color(28, 25, 23));
        jLblNamaHeaderChat.setText("Nama Pembeli");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLblNamaHeaderChat, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblNamaHeaderChat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDaftar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelDaftarLayout = new javax.swing.GroupLayout(panelDaftar);
        panelDaftar.setLayout(panelDaftarLayout);
        panelDaftarLayout.setHorizontalGroup(
            panelDaftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );
        panelDaftarLayout.setVerticalGroup(
            panelDaftarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jScrollPaneChat.setViewportView(panelDaftar);

        jTextField1.setText("Balas pesan...");
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton1.setBackground(new java.awt.Color(228, 92, 16));
        jButton1.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Kirim");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jPanelListKiri.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelListKiriLayout = new javax.swing.GroupLayout(jPanelListKiri);
        jPanelListKiri.setLayout(jPanelListKiriLayout);
        jPanelListKiriLayout.setHorizontalGroup(
            jPanelListKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jPanelListKiriLayout.setVerticalGroup(
            jPanelListKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanelListKiri);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneChat, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnKelolaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKelolaProdukActionPerformed
        new KelolaProdukPenjual(this.idPenjual).setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_jBtnKelolaProdukActionPerformed

    private void jBtnDaftarPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDaftarPesananActionPerformed
        new DaftarPesanan(this.idPenjual).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnDaftarPesananActionPerformed

    private void jBtnChatPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChatPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnChatPelangganActionPerformed

    private void jBtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnKeluarActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jBtnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDashboardActionPerformed
        new DashboardPenjual(this.idPenjual).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnDashboardActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        kirimBalasan();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ChatPelanggan().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnChatPelanggan;
    private javax.swing.JButton jBtnDaftarPesanan;
    private javax.swing.JButton jBtnDashboard;
    private javax.swing.JButton jBtnKelolaProduk;
    private javax.swing.JButton jBtnKeluar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLblNamaHeaderChat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelListKiri;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneChat;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelDaftar;
    // End of variables declaration//GEN-END:variables
}
