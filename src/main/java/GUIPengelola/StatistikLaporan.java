/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIPengelola;

import controll.LokaControl;
import javax.swing.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class StatistikLaporan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StatistikLaporan.class.getName());

    private LokaControl control = new LokaControl();
    /**
     * Creates new form MonitoringTransaksi
     */
    public StatistikLaporan() {
        initComponents();
        tampilkanBarChartKecamatan(panelGrafik);
        tampilkanPieChartKategori(panelPieChart);
    }

    private void tampilkanBarChartKecamatan(JPanel targetPanel) {
    // 1. Ambil data asli dari controller (pastikan method getStatistikKecamatan sudah ada di LokaControl)
        Map<String, Integer> dataKecamatan = control.getStatistikKecamatan();

        // 2. Masukkan ke Dataset JFreeChart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (dataKecamatan != null && !dataKecamatan.isEmpty()) {
            for (Map.Entry<String, Integer> entry : dataKecamatan.entrySet()) {
                dataset.addValue(entry.getValue(), "Jumlah Transaksi", entry.getKey());
            }
        } else {
            // Fallback data dummy kalau database masih kosong
            dataset.addValue(10, "Jumlah Transaksi", "Samarinda Ulu");
            dataset.addValue(15, "Jumlah Transaksi", "Samarinda Kota");
            dataset.addValue(8, "Jumlah Transaksi", "Sungai Kunjang");
        }

        // 3. Buat Bar Chart
        JFreeChart barChart = ChartFactory.createBarChart(
            "Statistik Transaksi Berdasarkan Kecamatan", 
            "Kecamatan", 
            "Total Transaksi", 
            dataset, 
            PlotOrientation.VERTICAL, 
            false, true, false
        );

        // Kustomisasi warna chart biar senada sama tema Loka (Oren & Gelap)
        barChart.getCategoryPlot().setBackgroundPaint(Color.WHITE);
        barChart.getPlot().setOutlinePaint(null);

        // 4. Masukkan Chart ke dalam JPanel di NetBeans (misal nama panelnya: panelGrafik)
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(targetPanel.getWidth(), targetPanel.getHeight()));

        targetPanel.removeAll();
        targetPanel.setLayout(new BorderLayout());
        targetPanel.add(chartPanel, BorderLayout.CENTER);
        targetPanel.validate();
        targetPanel.repaint();
    }
    
    private void tampilkanPieChartKategori(JPanel targetPanel) {
    // 1. Ambil data kategori produk terlaris dari database via controller/DAO
    // (Pastikan method getStatistikKategori() sudah ada di LokaControl, atau kita hitung manual di bawah)
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Logika perhitungan kategori dari DetailPesanan & Produk
        java.util.Map<String, Integer> mapKategori = new java.util.HashMap<>();
        List<model.DetailPesanan> listDetail = control.ambilSemuaDetailPesanan();
        List<model.Produk> listProduk = control.ambilSemuaProduk();

        if (listDetail != null && listProduk != null) {
            for (model.DetailPesanan dp : listDetail) {
                for (model.Produk pr : listProduk) {
                    if (pr.getIdProduk() == dp.getProdukIdProduk()) {
                        String kat = pr.getKategori();
                        if (kat != null && !kat.trim().isEmpty()) {
                            mapKategori.put(kat, mapKategori.getOrDefault(kat, 0) + dp.getJumlahProduk());
                        }
                    }
                }
            }
        }

        // Masukkan ke dataset Pie Chart
        if (!mapKategori.isEmpty()) {
            for (Map.Entry<String, Integer> entry : mapKategori.entrySet()) {
                dataset.setValue(entry.getKey(), entry.getValue());
            }
        } else {
            // Fallback data dummy kalau transaksi masih kosong
            dataset.setValue("Kuliner", 45);
            dataset.setValue("Fashion", 30);
            dataset.setValue("Kerajinan", 25);
        }

        // 2. Buat Pie Chart
        JFreeChart pieChart = ChartFactory.createPieChart(
            "Persentase Kategori Produk Terlaris", 
            dataset, 
            true, // tampilkan legend
            true, 
            false
        );

        // Kustomisasi warna background chart
        pieChart.setBackgroundPaint(Color.WHITE);

        // 3. Masukkan ke JPanel target di NetBeans (misal nama variabelnya: panelPieChart)
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(targetPanel.getWidth(), targetPanel.getHeight()));

        targetPanel.removeAll();
        targetPanel.setLayout(new BorderLayout());
        targetPanel.add(chartPanel, BorderLayout.CENTER);
        targetPanel.validate();
        targetPanel.repaint();
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
        jBtnMonitoringTransaksi = new javax.swing.JButton();
        jBtnKeluar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelGrafik = new javax.swing.JPanel();
        panelPieChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(224, 693));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/LogoLokaAdmin.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jBtnDashboard.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnDashboard.setForeground(new java.awt.Color(71, 85, 105));
        jBtnDashboard.setText("Dashboard");
        jBtnDashboard.setAlignmentX(0.5F);
        jBtnDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnDashboard.addActionListener(this::jBtnDashboardActionPerformed);

        jBtnMonitoringTransaksi.setBackground(new java.awt.Color(253, 239, 232));
        jBtnMonitoringTransaksi.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnMonitoringTransaksi.setForeground(new java.awt.Color(228, 92, 16));
        jBtnMonitoringTransaksi.setText("Statistik & Laporan");
        jBtnMonitoringTransaksi.setAlignmentX(0.5F);
        jBtnMonitoringTransaksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnMonitoringTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnMonitoringTransaksi.addActionListener(this::jBtnMonitoringTransaksiActionPerformed);

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
                    .addComponent(jBtnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnDashboard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnMonitoringTransaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jBtnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnMonitoringTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(228, 92, 16));

        jLabel2.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Statistik & Laporan");

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

        panelGrafik.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelGrafikLayout = new javax.swing.GroupLayout(panelGrafik);
        panelGrafik.setLayout(panelGrafikLayout);
        panelGrafikLayout.setHorizontalGroup(
            panelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGrafikLayout.setVerticalGroup(
            panelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelPieChart.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPieChartLayout = new javax.swing.GroupLayout(panelPieChart);
        panelPieChart.setLayout(panelPieChartLayout);
        panelPieChartLayout.setHorizontalGroup(
            panelPieChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelPieChartLayout.setVerticalGroup(
            panelPieChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelPieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGrafik, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelGrafik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        new GUIPengelola.DashboardAdmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnDashboardActionPerformed

    private void jBtnMonitoringTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMonitoringTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnMonitoringTransaksiActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new StatistikLaporan().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDashboard;
    private javax.swing.JButton jBtnKeluar;
    private javax.swing.JButton jBtnMonitoringTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel panelGrafik;
    private javax.swing.JPanel panelPieChart;
    // End of variables declaration//GEN-END:variables
}
