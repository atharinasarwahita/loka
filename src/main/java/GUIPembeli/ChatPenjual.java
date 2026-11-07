/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIPembeli;

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
public class ChatPenjual extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ChatPenjual.class.getName());

   private LokaControl control = new LokaControl();
    private int idPembeli;

    private int idPenjualDipilih = -1;
    private static final String PLACEHOLDER_BALAS = "Balas pesan...";
    
     private int selectedPenjualId = -1;
    
    public ChatPenjual() {
        this(6);
    }
    
    public ChatPenjual(int idPembeli) {
        this.idPembeli = idPembeli;
        initComponents();
        muatNamaDanSaldoUser();
        muatListPesanMasuk();
    }

    public ChatPenjual(int idPembeli, int idPenjualTujuan) {
        this.idPembeli = idPembeli;
        this.selectedPenjualId = idPenjualTujuan;
        this.idPenjualDipilih = idPenjualTujuan; 
        initComponents();
        muatNamaDanSaldoUser();
        muatListPesanMasuk();
        

        if (idPenjualTujuan != -1) {
            model.Penjual p = control.getDetailPenjualById(idPenjualTujuan);
            String namaToko = (p != null && p.getNamaToko() != null) ? p.getNamaToko() : "Toko #" + idPenjualTujuan;
            bukaRoomChat(idPenjualTujuan, namaToko);
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

    public void muatListPesanMasuk() {
        jPanelListKiri.removeAll();
        jPanelListKiri.setLayout(new BoxLayout(jPanelListKiri, BoxLayout.Y_AXIS));

        List<Pesan> allPesan = control.ambilSemuaPesan();
        if (allPesan != null) {

            java.util.Map<Integer, Pesan> mapLatest = new java.util.LinkedHashMap<>();
            for (Pesan item : allPesan) {
                if (item.getPembeliIdPengguna() == idPembeli) {

                    mapLatest.put(item.getPenjualIdPengguna(), item); 
                }
            }
            
            for (Pesan item : mapLatest.values()) {
                PembeliPesanMasuk row = new PembeliPesanMasuk();
                
                int idPenjual = item.getPenjualIdPengguna();
                model.Penjual p = control.getDetailPenjualById(idPenjual);
                String namaToko = (p != null && p.getNamaToko() != null) ? p.getNamaToko() : "Toko #" + idPenjual;
                
                row.setData(
                    idPenjual,
                    namaToko,
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

    public void bukaRoomChat(int idPenjual, String namaToko) {
        this.idPenjualDipilih = idPenjual;
        jLblNamaHeaderChat.setText(namaToko);
        muatIsiPercakapan(idPenjual, idPembeli);
    }

    public void muatIsiPercakapan(int idPenjual, int idPembeliParam) {
        panelDaftar.removeAll();
        panelDaftar.setLayout(new BoxLayout(panelDaftar, BoxLayout.Y_AXIS));

        List<Pesan> listChat = control.getDetailChat(idPenjual, idPembeliParam);
        if (listChat != null) {
            for (Pesan c : listChat) {
                JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                wrapper.setOpaque(false);

                String waktu = String.valueOf(c.getWaktuKirim()).replaceAll("\\.0$", "");

                BubbleChatPembeli bubble = new BubbleChatPembeli();
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

    private void kirimBalasan() {
        if (idPenjualDipilih < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih toko/penjual dulu!");
            return;
        }
        String isi = jTextField1.getText().trim();
        if (isi.isEmpty() || isi.equals(PLACEHOLDER_BALAS)) return;

        control.kirimPesanDariPembeli(idPenjualDipilih, idPembeli, isi);

        jTextField1.setText("");
        muatIsiPercakapan(idPenjualDipilih, idPembeli); 
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
        jBtnBerandaPembeli = new javax.swing.JButton();
        jBtnKeranjangPembeli = new javax.swing.JButton();
        jBtnPesanan = new javax.swing.JButton();
        jBtnChatPenjual = new javax.swing.JButton();
        jBtnProfilPembeli = new javax.swing.JButton();
        jBtnKeluar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLblSaldoAktif = new javax.swing.JLabel();
        jLblNamaUser = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLblNamaHeaderChat = new javax.swing.JLabel();
        jScrollPaneChat = new javax.swing.JScrollPane();
        panelDaftar = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelListKiri = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(246, 244, 241));

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
        jBtnKeranjangPembeli.addActionListener(this::jBtnKeranjangPembeliActionPerformed);

        jBtnPesanan.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnPesanan.setForeground(new java.awt.Color(71, 85, 105));
        jBtnPesanan.setText("Pesanan");
        jBtnPesanan.setAlignmentX(0.5F);
        jBtnPesanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnPesanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnPesanan.addActionListener(this::jBtnPesananActionPerformed);

        jBtnChatPenjual.setBackground(new java.awt.Color(253, 239, 232));
        jBtnChatPenjual.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jBtnChatPenjual.setForeground(new java.awt.Color(228, 92, 16));
        jBtnChatPenjual.setText("Chat Penjual");
        jBtnChatPenjual.setAlignmentX(0.5F);
        jBtnChatPenjual.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));
        jBtnChatPenjual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBtnChatPenjual.setOpaque(true);
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
                        .addComponent(jBtnKeluar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addComponent(jBtnProfilPembeli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addContainerGap(305, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(228, 92, 16));
        jButton2.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Top Up");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(228, 92, 16), 1, true));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jLabel2.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLabel2.setText("Saldo:");

        jLblSaldoAktif.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLblSaldoAktif.setForeground(new java.awt.Color(228, 92, 16));
        jLblSaldoAktif.setText("Rp.150.000");

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
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLblSaldoAktif))
                .addContainerGap())
        );

        jLblNamaUser.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLblNamaUser.setForeground(new java.awt.Color(228, 92, 16));
        jLblNamaUser.setText("Halo, User!");
        jLblNamaUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 12, 1, 1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(228, 92, 16));

        jLabel6.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pesan Masuk ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLblNamaHeaderChat.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jLblNamaHeaderChat.setForeground(new java.awt.Color(28, 25, 23));
        jLblNamaHeaderChat.setText("Nama Toko");

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
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(15, 15, 15)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneChat, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(60, 60, 60))))
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
                        .addComponent(jLblNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLblNamaUser)))
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
        new GUIPembeli.BerandaPembeli(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnBerandaPembeliActionPerformed

    private void jBtnKeranjangPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeranjangPembeliActionPerformed
        new GUIPembeli.KeranjangPembeli(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnKeranjangPembeliActionPerformed

    private void jBtnPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesananActionPerformed
        new GUIPembeli.Pesanan(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnPesananActionPerformed

    private void jBtnChatPenjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChatPenjualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnChatPenjualActionPerformed

    private void jBtnProfilPembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnProfilPembeliActionPerformed
        new GUIPembeli.ProfilPembeli(idPembeli).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnProfilPembeliActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jBtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKeluarActionPerformed
        new GUIUmum.Beranda().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnKeluarActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ChatPenjual().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBerandaPembeli;
    private javax.swing.JButton jBtnChatPenjual;
    private javax.swing.JButton jBtnKeluar;
    private javax.swing.JButton jBtnKeranjangPembeli;
    private javax.swing.JButton jBtnPesanan;
    private javax.swing.JButton jBtnProfilPembeli;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLblNamaHeaderChat;
    private javax.swing.JLabel jLblNamaUser;
    private javax.swing.JLabel jLblSaldoAktif;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelListKiri;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneChat;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelDaftar;
    // End of variables declaration//GEN-END:variables
}
