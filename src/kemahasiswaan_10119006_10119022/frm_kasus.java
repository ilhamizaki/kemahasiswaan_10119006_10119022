/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kemahasiswaan_10119006_10119022;
//Fungsi import yang digunakan untuk SQL
import java.sql.*;
import java.text.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author Ilham zaki
 */
public class frm_kasus extends javax.swing.JFrame {
    // deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    int total_harga;
    
    
    /** Creates new form frm_kasus */
    public frm_kasus() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_transaksi.setModel(tableModel);
        settableload();
        aktif_btn_default();
        nonaktif_teks();
    }
    
    // Start handling table
    private javax.swing.table.DefaultTableModel tableModel= getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel(){
        //membuat judul header
        return new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String [] {"Id Transaksi","Nama Pelanggan","Jenis Paket",""
            + "Jumlah Cucian","Total Berat","Total Harga","Tanggal Masuk",""
            + "Keterangan"} // 8 field
        )
        {
        // Disable Edit Tabel
        boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false, false, false // 8 field
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    // END handling table
    
    // START handling show all data in tabel_transaksi
    String data[] = new String[8];
    private void settableload(){
        String stat = "";
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt=kon.createStatement();
            String SQL = "SELECT id_transaksi, nama, nama_pkt, jmlh_cucian, "
                + "total_berat, total_harga, tgl_masuk, keterangan\n" +
                "FROM t_transaksi\n" +
                "JOIN t_pelanggan ON t_pelanggan.id_pelanggan = t_transaksi.id_pelanggan\n" +
                "JOIN t_paket ON t_paket.kd_paket = t_transaksi.kd_paket";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                tableModel.addRow(data);
            }
            
            //Get nama for combobox
            String SQL_pkt = "SELECT * FROM t_paket";
            ResultSet res_pkt = stt.executeQuery(SQL_pkt);
            while (res_pkt.next()) {
                //Set Value Combobox
                String nama_pkt = res_pkt.getString("nama_pkt");
                combo_paket.addItem(nama_pkt);
            }
            
            res.close();
            res_pkt.close();
            stt.close();
            kon.close();
            
            
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    // END handling show all data in tabel_transaksi
    
    // START Method for handling UI
    public void membersihkan_teks(){
        txt_nama.setText("");
        txt_no_telp.setText("");
        txt_alamat.setText("");
        combo_paket.setSelectedIndex(0);
        txt_cucian.setText("");
        txt_berat.setText("");
        txt_keterangan.setText("");
    }
    public void nonaktif_teks(){
        txt_nama.setEnabled(false);
        txt_no_telp.setEnabled(false);
        txt_alamat.setEnabled(false);
        combo_paket.setEnabled(false);
        txt_cucian.setEnabled(false);
        txt_berat.setEnabled(false);
        txt_keterangan.setEnabled(false);
    }
    public void aktif_teks(){
        txt_nama.setEnabled(true);
        txt_no_telp.setEnabled(true);
        txt_alamat.setEnabled(true);
        combo_paket.setEnabled(true);
        txt_cucian.setEnabled(true);
        txt_berat.setEnabled(true);
        txt_keterangan.setEnabled(true);
    }
    public void aktif_btn_default(){
        btn_tambah.setEnabled(true);
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        btn_batal.setEnabled(false);
        btn_keluar.setEnabled(true);
    }
    // END Method for handling UI
    
    // START Handling Update
    int row =0;
    public void tampil_field() throws ParseException{
        row = tabel_transaksi.getSelectedRow();
        txt_nama.setText(tableModel.getValueAt(row, 1).toString());
        combo_paket.setSelectedItem(tableModel.getValueAt(row, 2).toString());
        txt_cucian.setText(tableModel.getValueAt(row, 3).toString());
        txt_berat.setText(tableModel.getValueAt(row, 4).toString());
        txt_keterangan.setText(tableModel.getValueAt(row, 7).toString());
        
        // get noTelp and Alamat
        try {
              Class.forName(driver);
              Connection kon = DriverManager.getConnection(database, user, pass);
              Statement stt = kon.createStatement();
              String SQL = "SELECT telepon, alamat FROM t_pelanggan "
                      + "WHERE nama = '"+txt_nama.getText()+"' ";
              ResultSet res = stt.executeQuery(SQL);
              while (res.next()) {
                  txt_no_telp.setText(res.getString(1));
                  txt_alamat.setText(res.getString(2));
              }
              res.close();
              stt.close();
              kon.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                ex.getMessage(), "Error",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        
        btn_ubah.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_batal.setEnabled(true);
        aktif_teks();
    }
    // END Handling Update
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        txt_no_telp = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        combo_paket = new javax.swing.JComboBox<>();
        txt_cucian = new javax.swing.JTextField();
        txt_berat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_keterangan = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_transaksi = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplikasi Kemahasiswaan_10119006_10119022");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Transaksi Hemat Wash Laundry");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_10119006_10119022/icons8_transaction_list_50px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(187, 221, 255));

        jPanel3.setBackground(new java.awt.Color(187, 221, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Pelanggan"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nama");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("No Telp");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Alamat");

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane2.setViewportView(txt_alamat);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_no_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_no_telp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(187, 221, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Pesanan"));

        combo_paket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Paket -" }));

        txt_keterangan.setColumns(20);
        txt_keterangan.setRows(5);
        jScrollPane1.setViewportView(txt_keterangan);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Keterangan");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Berat (Kg)");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Jumlah Cucian");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Jenis Paket");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_berat, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_cucian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(combo_paket, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(combo_paket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_cucian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_berat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(187, 221, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informasi"));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Daftar Paket");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Paket Ekonomis - 3 Hari");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Rp 6.000 / Kg (Min. 2 kg)");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Paket Regular - 2 Hari");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Rp 7.000 / Kg (Min. 2 kg)");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Paket Express - 1 Hari");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Rp 8000 / Kg (Min. 2 kg)");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Paket Super - 8 Jam");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Rp 12.500 / Kg (Min. 2 kg)");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("*1 Customer 1 Mesin");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)))
                    .addComponent(jLabel9)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(20, 20, 20))
        );

        tabel_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_transaksiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_transaksi);

        btn_tambah.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setBackground(new java.awt.Color(255, 255, 255));
        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setBackground(new java.awt.Color(255, 255, 255));
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_keluar.setBackground(new java.awt.Color(255, 255, 255));
        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_10119006_10119022/icons8_search_24px_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_ubah)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal)
                    .addComponent(btn_keluar)
                    .addComponent(btn_hapus))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabel_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_transaksiMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            btn_tambah.setEnabled(false);
            btn_simpan.setEnabled(false);

            try {
                tampil_field();
            } catch (ParseException ex) {
                Logger.getLogger(frm_mhs.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tabel_transaksiMouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        txt_nama.requestFocus();
        btn_tambah.setEnabled(false);
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        aktif_teks();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        String data[]=new String[8];
        // START Validasi Empty Input
        if(txt_nama.getText().isEmpty() || txt_no_telp.getText().isEmpty() ||
            txt_alamat.getText().isEmpty() || combo_paket.getSelectedIndex() == 0 ||
            txt_cucian.getText().isEmpty() || txt_berat.getText().isEmpty() ||
            txt_keterangan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan lengkapi");
            if(txt_nama.getText().isEmpty()) txt_nama.requestFocus();
            if(txt_no_telp.getText().isEmpty()) txt_no_telp.requestFocus();
            if(txt_alamat.getText().isEmpty()) txt_alamat.requestFocus();
            if(combo_paket.getSelectedIndex() == 0) combo_paket.requestFocus();
            if(txt_cucian.getText().isEmpty()) txt_cucian.requestFocus();
            if(txt_berat.getText().isEmpty()) txt_berat.requestFocus();
            if(txt_keterangan.getText().isEmpty()) txt_keterangan.requestFocus();
        // END Validasi Empty Input
        }else{
            try{
                if(Integer.valueOf(txt_berat.getText()) < 2) {
                    JOptionPane.showMessageDialog(null, "Minimal Berat 2 Kg");
                    txt_berat.requestFocus();
                } else {
                    Class.forName(driver);
                    Connection kon = DriverManager.getConnection(database,user,pass);
                    Statement stt = kon.createStatement();

                    // Get Id_Pelanggan
                    String SQL_getId = "SELECT id_pelanggan FROM t_pelanggan "
                                + "WHERE nama = '"+tableModel.getValueAt(row, 1).toString()+"'";
                    ResultSet res_getId = stt.executeQuery(SQL_getId);
                    while (res_getId.next()) {
                        int getIdPelanggan = res_getId.getInt(1);
    //                    System.out.println("Id Pelanggan : " + getIdPelanggan);             //check result

                        // Set nama, no telp, alamat form t_pelanggan
                        String SQL_pelanggan = "UPDATE t_pelanggan "
                                + "SET "
                                + "nama = '"+txt_nama.getText()+"',"
                                + "telepon = '"+txt_no_telp.getText()+"',"
                                + "alamat = '"+txt_alamat.getText()+"' "
                                + "WHERE id_pelanggan = '"+getIdPelanggan+"'";
                        stt.executeUpdate(SQL_pelanggan);
    //                    System.out.println("Nama : " + txt_nama.getText());                 //check result

                        // Get kd_paket
    //                    System.out.println(combo_paket.getSelectedItem());                  //check result
                        String SQL_getKd_pkt = "SELECT kd_paket, harga_pkt FROM t_paket "
                                + "WHERE nama_pkt = '"+combo_paket.getSelectedItem()+"'";
                        ResultSet res_getKd_pkt = stt.executeQuery(SQL_getKd_pkt);
                        while (res_getKd_pkt.next()) {
                            String getKd_pkt = res_getKd_pkt.getString("kd_paket");
    //                        System.out.println("Kd Paket : " + getKd_pkt);                  //check result   

                            int harga_pkt = res_getKd_pkt.getInt("harga_pkt");
    //                        System.out.println("Harga paket : " + harga_pkt);               //check result
                            int berat = Integer.valueOf(txt_berat.getText());
    //                        System.out.println("Berat : " + berat);                         //check result
                            total_harga =  berat * harga_pkt;
    //                        System.out.println("Total Harga : " + total_harga);             //check result

                            String SQL_setId = "UPDATE t_transaksi "
                                    + "SET "
                                    + "kd_paket = '"+getKd_pkt+"',"
                                    + "jmlh_cucian = '"+txt_cucian.getText()+"',"
                                    + "total_berat = '"+txt_berat.getText()+"',"
                                    + "total_harga = '"+total_harga+"',"
                                    + "keterangan = '"+txt_keterangan.getText()+"' "
                                    + "WHERE id_pelanggan = '"+getIdPelanggan+"'";
                            stt.executeUpdate(SQL_setId);

                            String SQL = "SELECT id_transaksi, nama, nama_pkt, jmlh_cucian, "
                                + "total_berat, total_harga, tgl_masuk, keterangan\n" +
                                "FROM t_transaksi\n" +
                                "JOIN t_pelanggan ON t_pelanggan.id_pelanggan = t_transaksi.id_pelanggan\n" +
                                "JOIN t_paket ON t_paket.kd_paket = t_transaksi.kd_paket\n" +
                                "WHERE t_transaksi.id_pelanggan = '"+getIdPelanggan+"'";
                            ResultSet res = stt.executeQuery(SQL);
                            while(res.next()){
                                data[0] = res.getString(1);
                                data[1] = res.getString(2);
                                data[2] = res.getString(3);
                                data[3] = res.getString(4);
                                data[4] = res.getString(5);
                                data[5] = res.getString(6);
                                data[6] = res.getString(7);
                                data[7] = res.getString(8);
                                tableModel.removeRow(row);
                                tableModel.insertRow(row, data);
                            }
                            res.close();
                            membersihkan_teks();
                            nonaktif_teks();
                            aktif_btn_default();          
                            JOptionPane.showMessageDialog(null, "Data berhasil diubah");    // proses berhasil sampai sini
                        }
                        res_getKd_pkt.close();
                    }
                    res_getId.close();
                    stt.close();
                    kon.close();
                }
            }catch(Exception ex){
//                JOptionPane.showMessageDialog(null,
//                    ex.getMessage(),"error",
//                    JOptionPane.INFORMATION_MESSAGE
//                );
            }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try{
            if (JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin akan menghapus ?", "Peringatan",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "DELETE FROM t_transaksi WHERE "
                + "id_transaksi='"+tableModel.getValueAt(row, 0).toString()+"'";
                stt.executeUpdate(SQL);
                tableModel.removeRow(row);
                stt.close();
                kon.close();
                membersihkan_teks();
                nonaktif_teks();
                aktif_btn_default();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            } else {
                // no option
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[]=new String[8];
        // START Validasi Empty Input
        if(txt_nama.getText().isEmpty() || txt_no_telp.getText().isEmpty() ||
            txt_alamat.getText().isEmpty() || combo_paket.getSelectedIndex() == 0 ||
            txt_cucian.getText().isEmpty() || txt_berat.getText().isEmpty() ||
            txt_keterangan.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan lengkapi");
            if(txt_nama.getText().isEmpty()) txt_nama.requestFocus();
            if(txt_no_telp.getText().isEmpty()) txt_no_telp.requestFocus();
            if(txt_alamat.getText().isEmpty()) txt_alamat.requestFocus();
            if(combo_paket.getSelectedIndex() == 0) combo_paket.requestFocus();
            if(txt_cucian.getText().isEmpty()) txt_cucian.requestFocus();
            if(txt_berat.getText().isEmpty()) txt_berat.requestFocus();
            if(txt_keterangan.getText().isEmpty()) txt_keterangan.requestFocus();
        // END Validasi Empty Input
        }else{
            try{
                if(Integer.valueOf(txt_berat.getText()) < 2) {
                    JOptionPane.showMessageDialog(null, "Minimal Berat 2 Kg");
                    txt_berat.requestFocus();
                } else {
                    Class.forName(driver);
                    Connection kon = DriverManager.getConnection(database,user,pass);
                    Statement stt = kon.createStatement();

                    // Set nama, no telp, alamat form t_pelanggan
                    String SQL_pelanggan = "INSERT INTO t_pelanggan (nama,telepon,alamat) "
                            + "VALUES "
                            + "('"+txt_nama.getText()+"',"
                            + "'"+txt_no_telp.getText()+"',"
                            + "'"+txt_alamat.getText()+"')";
                    stt.executeUpdate(SQL_pelanggan);
    //                System.out.println("Nama : " + txt_nama.getText());                      //check result

                    // Get Id_Pelanggan
                    String SQL_getId = "SELECT id_pelanggan FROM t_pelanggan "
                                + "WHERE nama = '"+txt_nama.getText()+"'";
                    ResultSet res_getId = stt.executeQuery(SQL_getId);
                    while (res_getId.next()) {
                        int getIdPelanggan = res_getId.getInt(1);
    //                    System.out.println("Id Pelanggan : " + getIdPelanggan);             //check result

                        // Get kd_paket
    //                    System.out.println(combo_paket.getSelectedItem());                  //check result
                        String SQL_getKd_pkt = "SELECT kd_paket, harga_pkt FROM t_paket "
                                + "WHERE nama_pkt = '"+combo_paket.getSelectedItem()+"'";
                        ResultSet res_getKd_pkt = stt.executeQuery(SQL_getKd_pkt);
                        while (res_getKd_pkt.next()) {
                            String getKd_pkt = res_getKd_pkt.getString("kd_paket");
    //                        System.out.println("Kd Paket : " + getKd_pkt);                  //check result   

                            int harga_pkt = res_getKd_pkt.getInt("harga_pkt");
    //                        System.out.println("Harga paket : " + harga_pkt);               //check result
                            int berat = Integer.valueOf(txt_berat.getText());
    //                        System.out.println("Berat : " + berat);                         //check result
                            total_harga =  berat * harga_pkt;
    //                        System.out.println("Total Harga : " + total_harga);             //check result

                            String SQL_setId = "INSERT INTO t_transaksi (id_Pelanggan, kd_paket, "
                                    + "jmlh_cucian, total_berat, total_harga, keterangan) "
                                    + "VALUES "
                                    + "('"+getIdPelanggan+"',"
                                    + "'"+getKd_pkt+"',"
                                    + "'"+txt_cucian.getText()+"',"
                                    + "'"+txt_berat.getText()+"',"
                                    + "'"+total_harga+"',"
                                    + "'"+txt_keterangan.getText()+"')";
                            stt.executeUpdate(SQL_setId);

                            String SQL = "SELECT id_transaksi, nama, nama_pkt, jmlh_cucian, "
                                + "total_berat, total_harga, tgl_masuk, keterangan\n" +
                                "FROM t_transaksi\n" +
                                "JOIN t_pelanggan ON t_pelanggan.id_pelanggan = t_transaksi.id_pelanggan\n" +
                                "JOIN t_paket ON t_paket.kd_paket = t_transaksi.kd_paket\n" +
                                "WHERE t_transaksi.id_pelanggan = '"+getIdPelanggan+"'";
                            ResultSet res = stt.executeQuery(SQL);
                            while(res.next()){
                                data[0] = res.getString(1);
                                data[1] = res.getString(2);
                                data[2] = res.getString(3);
                                data[3] = res.getString(4);
                                data[4] = res.getString(5);
                                data[5] = res.getString(6);
                                data[6] = res.getString(7);
                                data[7] = res.getString(8);
                                tableModel.insertRow(0, data);
                            }
                            res.close();
                            membersihkan_teks();
                            nonaktif_teks();
                            aktif_btn_default();          
                            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");      // proses berhasil sampai sini
                        }
                        res_getKd_pkt.close();
                    }
                    res_getId.close();
                    stt.close();
                    kon.close();
                }
            }catch(Exception ex){
//                JOptionPane.showMessageDialog(null,
//                    ex.getMessage(),"error",
//                    JOptionPane.INFORMATION_MESSAGE
//                );
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        aktif_btn_default();
        membersihkan_teks();
        nonaktif_teks();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
        utama.setVisible(true);

        //menghilangkan form utam
        this.setVisible(false);
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
        //Menghapus seluruh isi data di dalam jtable(table_mahasiswa)
        tableModel.setRowCount(0);
        
        //gunakan query untuk mencari
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT id_transaksi, nama, nama_pkt, jmlh_cucian, "
                    + "total_berat, total_harga, tgl_masuk, keterangan\n"
                    + "FROM t_transaksi\n"
                    + "JOIN t_pelanggan ON t_pelanggan.id_pelanggan = t_transaksi.id_pelanggan\n"
                    + "JOIN t_paket ON t_paket.kd_paket = t_transaksi.kd_paket "
                    + "WHERE id_transaksi LIKE '%"+txt_cari.getText()+"%'"
                    + "OR nama LIKE '%"+txt_cari.getText()+"%'"
                    + "OR nama_pkt LIKE '%"+txt_cari.getText()+"%'"
                    + "OR tgl_masuk LIKE '%"+txt_cari.getText()+"%'"
                    + "OR keterangan LIKE '%"+txt_cari.getText()+"%'";
            
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
            aktif_btn_default();
            membersihkan_teks();
            nonaktif_teks();
        }catch(Exception ex){    
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_txt_cariKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
        utama.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_kasus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_kasus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_kasus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_kasus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_kasus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> combo_paket;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabel_transaksi;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_berat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_cucian;
    private javax.swing.JTextArea txt_keterangan;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_no_telp;
    // End of variables declaration//GEN-END:variables

}
