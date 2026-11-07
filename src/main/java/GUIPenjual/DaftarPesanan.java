/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIPenjual;

import controll.LokaControl;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import model.Pesanan;
import model.Pengembalian;
import java.util.List;
/**
 *
 * @author LENOVO
 */
public class DaftarPesanan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DaftarPesanan.class.getName());

    /**
     * Creates new form DaftarPesanan
     */
    
    private int idPenjual;
    private LokaControl control = new LokaControl();

    // Constructor default
    public DaftarPesanan() {
        initComponents();
        loadTabelPesanan();
        jTblDaftarPesanan.setSelectionBackground(new java.awt.Color(228, 92, 16)); // Warna oren khas Loka
        jTblDaftarPesanan.setSelectionForeground(java.awt.Color.WHITE); // Teks berubah jadi putih terang pas diklik

        // 2. Tambahkan aksi double-click untuk melihat detail lengkap (termasuk alasan pengembalian penuh)
        jTblDaftarPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Cek jika double click
                    int selectedRow = jTblDaftarPesanan.getSelectedRow();
                    if (selectedRow != -1) {
                        Object noPesanan = jTblDaftarPesanan.getValueAt(selectedRow, 0);
                        Object pembeli = jTblDaftarPesanan.getValueAt(selectedRow, 1);
                        Object tanggal = jTblDaftarPesanan.getValueAt(selectedRow, 2);
                        Object total = jTblDaftarPesanan.getValueAt(selectedRow, 3);
                        Object status = jTblDaftarPesanan.getValueAt(selectedRow, 4);
                        Object alasan = jTblDaftarPesanan.getValueAt(selectedRow, 5);

                        String pesanDetail = "<html><b>Detail Pesanan #" + noPesanan + "</b><br>" +
                                             "ID Pembeli: " + pembeli + "<br>" +
                                             "Tanggal: " + tanggal + "<br>" +
                                             "Total Bayar: " + total + "<br>" +
                                             "Status: " + status + "<br><br>" +
                                             "<b>Alasan Pengembalian/Retur:</b><br>" +
                                             "<p style='width: 250px;'>" + alasan + "</p></html>";

                        javax.swing.JOptionPane.showMessageDialog(
                            DaftarPesanan.this, 
                            pesanDetail, 
                            "Informasi Lengkap Pesanan", 
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });
    }

    public DaftarPesanan(int idPenjual) {
        this.idPenjual = idPenjual;
        initComponents();
        loadTabelPesanan();
        
        jTblDaftarPesanan.setSelectionBackground(new java.awt.Color(228, 92, 16)); // Warna oren khas Loka
        jTblDaftarPesanan.setSelectionForeground(java.awt.Color.WHITE); // Teks berubah jadi putih terang pas diklik

        // 2. Tambahkan aksi double-click untuk melihat detail lengkap (termasuk alasan pengembalian penuh)
        jTblDaftarPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Cek jika double click
                    int selectedRow = jTblDaftarPesanan.getSelectedRow();
                    if (selectedRow != -1) {
                        Object noPesanan = jTblDaftarPesanan.getValueAt(selectedRow, 0);
                        Object pembeli = jTblDaftarPesanan.getValueAt(selectedRow, 1);
                        Object tanggal = jTblDaftarPesanan.getValueAt(selectedRow, 2);
                        Object total = jTblDaftarPesanan.getValueAt(selectedRow, 3);
                        Object status = jTblDaftarPesanan.getValueAt(selectedRow, 4);
                        Object alasan = jTblDaftarPesanan.getValueAt(selectedRow, 5);

                        String pesanDetail = "<html><b>Detail Pesanan #" + noPesanan + "</b><br>" +
                                             "ID Pembeli: " + pembeli + "<br>" +
                                             "Tanggal: " + tanggal + "<br>" +
                                             "Total Bayar: " + total + "<br>" +
                                             "Status: " + status + "<br><br>" +
                                             "<b>Alasan Pengembalian/Retur:</b><br>" +
                                             "<p style='width: 250px;'>" + alasan + "</p></html>";

                        javax.swing.JOptionPane.showMessageDialog(
                            DaftarPesanan.this, 
                            pesanDetail, 
                            "Informasi Lengkap Pesanan", 
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });
    }
    
    private void loadTabelPesanan() {
        DefaultTableModel model = new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "No. Pesanan", "Pembeli", "Tanggal Pesan", "Total Bayar", "Status Pesanan", "Alasan Pengembalian"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        jTblDaftarPesanan.setModel(model); 
        
        List<Pesanan> listPesanan = control.ambilSemuaPesanan();
        List<Pengembalian> listPengembalian = control.ambilSemuaPengembalian(); 

        if (listPesanan != null) {
            for (Pesanan p : listPesanan) {
                String alasan = "-";
                
                if (p.getStatusPesanan() != null && String.valueOf(p.getStatusPesanan()).equalsIgnoreCase("Dikembalikan")) {
                    if (listPengembalian != null) {
                        for (Pengembalian pg : listPengembalian) {
                            alasan = (pg.getAlasan() != null && !pg.getAlasan().isEmpty()) ? pg.getAlasan() : "Tidak ada alasan";
                            break;
                        }
                    } else {
                        alasan = "Pengajuan Retur";
                    }
                }

                Object[] row = {
                    p.getNoPesanan(),
                    p.getPembeliIdPengguna(), 
                    p.getTanggalPesanan(),
                    "Rp " + String.format("%,.0f", p.getTotalBayar()),
                    p.getStatusPesanan(),
                    alasan 
                };
                model.addRow(row);
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
        jBtnDashboard = new javax.swing.JButton();
        jBtnKelolaProduk = new javax.swing.JButton();
        jBtnDaftarPesanan = new javax.swing.JButton();
        jBtnChatPelanggan = new javax.swing.JButton();
        jBtnKeluar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jBtnUpdateStatus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblDaftarPesanan = new javax.swing.JTable();

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

        jBtnDaftarPesanan.setBackground(new java.awt.Color(253, 239, 232));
        jBtnDaftarPesanan.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnDaftarPesanan.setForeground(new java.awt.Color(228, 92, 16));
        jBtnDaftarPesanan.setText("Daftar Pesanan");
        jBtnDaftarPesanan.setAlignmentX(0.5F);
        jBtnDaftarPesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnDaftarPesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnDaftarPesanan.setOpaque(true);
        jBtnDaftarPesanan.addActionListener(this::jBtnDaftarPesananActionPerformed);

        jBtnChatPelanggan.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnChatPelanggan.setForeground(new java.awt.Color(71, 85, 105));
        jBtnChatPelanggan.setText("Chat Pelanggan");
        jBtnChatPelanggan.setAlignmentX(0.5F);
        jBtnChatPelanggan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnChatPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnChatPelanggan.addActionListener(this::jBtnChatPelangganActionPerformed);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(228, 92, 16));

        jLabel2.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Daftar Pesanan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jBtnUpdateStatus.setBackground(new java.awt.Color(49, 160, 96));
        jBtnUpdateStatus.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jBtnUpdateStatus.setForeground(new java.awt.Color(255, 255, 255));
        jBtnUpdateStatus.setText("Update Status");
        jBtnUpdateStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jBtnUpdateStatus.addActionListener(this::jBtnUpdateStatusActionPerformed);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jTblDaftarPesanan.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jTblDaftarPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No. Pesanan", "Pembeli", "Tanggal Pesan", "Total Bayar", "Status Pesanan", "Alasan Pengembalian"
            }
        ));
        jTblDaftarPesanan.setSelectionBackground(new java.awt.Color(255, 224, 178));
        jScrollPane2.setViewportView(jTblDaftarPesanan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jBtnUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1030, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE)))
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

    private void jBtnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDashboardActionPerformed
        new DashboardPenjual(this.idPenjual).setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_jBtnDashboardActionPerformed

    private void jBtnKelolaProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKelolaProdukActionPerformed
        new KelolaProdukPenjual(this.idPenjual).setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_jBtnKelolaProdukActionPerformed

    private void jBtnDaftarPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDaftarPesananActionPerformed
        new DaftarPesanan(this.idPenjual).setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_jBtnDaftarPesananActionPerformed

    private void jBtnChatPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChatPelangganActionPerformed
        new ChatPelanggan(this.idPenjual).setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_jBtnChatPelangganActionPerformed

    private void jBtnUpdateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpdateStatusActionPerformed
        int selectedRow = jTblDaftarPesanan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih pesanan dari tabel yang ingin di-update statusnya!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int noPesanan = Integer.parseInt(jTblDaftarPesanan.getValueAt(selectedRow, 0).toString());

        String statusLama = jTblDaftarPesanan.getValueAt(selectedRow, 4).toString();

        String[] options = {"Diproses", "Dikirim", "Selesai", "Pengembalian", "Dikembalikan"};
        String statusPilihan = (String) JOptionPane.showInputDialog(
            this, 
            "Pilih Status Pesanan Baru:\n(Status Lama: " + statusLama + ")", 
            "Update Status Pesanan", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[1]
        );

        if (statusPilihan != null) {
           
            if (statusLama.equalsIgnoreCase("Selesai")) {
                JOptionPane.showMessageDialog(this, "Pesanan yang sudah selesai tidak dapat diubah statusnya lagi!", "Validasi Gagal", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if ((statusLama.equalsIgnoreCase("Pengembalian") || statusLama.equalsIgnoreCase("Dikembalikan")) 
                && (statusPilihan.equalsIgnoreCase("Diproses") || statusPilihan.equalsIgnoreCase("Dikirim"))) {
                JOptionPane.showMessageDialog(this, "Status pesanan tidak dapat dikembalikan ke tahap sebelumnya!", "Validasi Gagal", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (statusLama.equalsIgnoreCase("Dikirim") && statusPilihan.equalsIgnoreCase("Diproses")) {
                JOptionPane.showMessageDialog(this, "Pesanan yang sudah dikirim tidak dapat diubah kembali menjadi diproses!", "Validasi Gagal", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                enums.StatusPesanan statusBaru = enums.StatusPesanan.valueOf(statusPilihan);

                control.updateStatusPesanan(noPesanan, statusBaru, new java.util.Date(), null);

                JOptionPane.showMessageDialog(this, "Status pesanan berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadTabelPesanan(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal mengupdate status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBtnUpdateStatusActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new DaftarPesanan().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnChatPelanggan;
    private javax.swing.JButton jBtnDaftarPesanan;
    private javax.swing.JButton jBtnDashboard;
    private javax.swing.JButton jBtnKelolaProduk;
    private javax.swing.JButton jBtnKeluar;
    private javax.swing.JButton jBtnUpdateStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTblDaftarPesanan;
    // End of variables declaration//GEN-END:variables
}
