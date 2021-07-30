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
public class frm_nilai extends javax.swing.JFrame {
    // deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    
    /**
     * Creates new form frm_nilai
     */
    public frm_nilai() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_nilai.setModel(tableModel);
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
            new String [] {"NIM","Kode Mata Kuliah","Nilai","Index","Keterangan"}
        )
        {
         // Disable Edit Tabel
         boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    // END handling table
    
    // START Method for handling UI
    public void membersihkan_teks(){
        combo_nim.setSelectedIndex(0);
        combo_kode_mk.setSelectedIndex(0);
        txt_nilai.setText("");
    }
    public void nonaktif_teks(){
        combo_nim.setEnabled(false);
        combo_kode_mk.setEnabled(false);
        txt_nilai.setEnabled(false);
    }
    public void aktif_teks(){
        combo_nim.setEnabled(true);
        combo_kode_mk.setEnabled(true);
        txt_nilai.setEnabled(true);
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
    
    // Start handling show all data in tabel_mahasiswa
    String data[] = new String[5];
    private void settableload(){
        String stat = "";
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt=kon.createStatement();
            String SQL = "SELECT nim, kd_mk, nilai, t_nilai.index, ket FROM t_nilai";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                tableModel.addRow(data);
            }
            
            //Get nim for combobox
            String SQL_nim = "SELECT nim FROM t_mahasiswa";
            ResultSet res_nim = stt.executeQuery(SQL_nim);
            while (res_nim.next()) {
                //Set Value Combobox
                String nim = res_nim.getString("nim");
                combo_nim.addItem(nim);
            }
            
            //Get kd_mk for combobox
            String SQL_kd_mk = "SELECT kd_mk FROM t_mata_kuliah";
            ResultSet res_kd_mk = stt.executeQuery(SQL_kd_mk);
            while (res_kd_mk.next()) {
                //Set Value Combobox
                String kd_mk = res_kd_mk.getString("kd_mk");
                combo_kode_mk.addItem(kd_mk);
            }
            
            res.close();
            res_nim.close();
            res_kd_mk.close();
            stt.close();
            kon.close();
            
            
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    // START Handling Update
    int row =0;
    public void tampil_field() throws ParseException{
        row = tabel_nilai.getSelectedRow();
        // Handling set input combo from table value
        combo_nim.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        combo_kode_mk.setSelectedItem(tableModel.getValueAt(row, 1).toString());
        
        btn_ubah.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_batal.setEnabled(true);
        
        aktif_teks();
    }
    // END Handling Update
    // Start handling show all data in tabel_mahasiswa
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_nilai = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_cari = new javax.swing.JButton();
        btn_tampil = new javax.swing.JButton();
        txt_cari_nim_kode_mk = new javax.swing.JTextField();
        combo_nim = new javax.swing.JComboBox<>();
        combo_kode_mk = new javax.swing.JComboBox<>();
        txt_nilai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Nilai Mahasiswa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(297, 297, 297))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(23, 23, 23))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NIM");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Kode MK");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nilai");

        tabel_nilai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_nilai);

        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pencarian Data Nilai Mahasiswa"));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("NIM/Kode MK");

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        btn_tampil.setText("Tampilkan Keseluruhan Data");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txt_cari_nim_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btn_cari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(btn_tampil, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(btn_cari)
                    .addComponent(btn_tampil)
                    .addComponent(txt_cari_nim_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        combo_nim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih NIM -" }));

        combo_kode_mk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Kode MK -" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(combo_nim, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(combo_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txt_nilai, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(combo_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_ubah)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal)
                    .addComponent(btn_keluar)
                    .addComponent(btn_hapus))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        combo_nim.requestFocus();
        btn_tambah.setEnabled(false);
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        aktif_teks();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        if(combo_nim.getSelectedIndex() == 0 || combo_kode_mk.getSelectedIndex() == 0 
            || txt_nilai.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong, silahkan lengkapi");
            if(combo_nim.getSelectedIndex() == 0) combo_nim.requestFocus();
            if(combo_kode_mk.getSelectedIndex() == 0) combo_kode_mk.requestFocus();
            if(txt_nilai.getText().isEmpty()) txt_nilai.requestFocus();
        }else{
            try{
                // START get indeks dan keterangan
                double nilai = Double.valueOf(txt_nilai.getText());
                char indeks;
                String keterangan = "Lulus";
                
                if (nilai >= 80 && nilai <=100) {
                    indeks = 'A';         
                } else if(nilai >= 68) {
                    indeks = 'B';
                } else if(nilai >= 56) {
                    indeks = 'C';
                } else if(nilai >= 45) {
                    indeks = 'D';
                } else {
                    indeks = 'E';
                    keterangan = "Tidak Lulus";
                }
                // END get indeks dan keterangan
                
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE t_nilai "
                        + "SET "
                        + "nim = '" + combo_nim.getSelectedItem() + "', "
                        + "kd_mk = '" + combo_kode_mk.getSelectedItem() + "', "
                        + "nilai = '" + txt_nilai.getText() + "', "
                        + "t_nilai.index = '" + indeks + "', "
                        + "ket = '" + keterangan + "' "
                        + "WHERE nim = '" + tableModel.getValueAt(row, 0).toString() + "'";
                stt.executeUpdate(SQL);
                data[0] = combo_nim.getSelectedItem().toString();
                data[1] = combo_kode_mk.getSelectedItem().toString();
                data[2] = txt_nilai.getText();
                data[3] = Character.toString(indeks);
                data[4] = keterangan;
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                nonaktif_teks();
                aktif_btn_default();
            }catch(Exception ex){    
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "DELETE FROM t_nilai WHERE "
                        + "nim='"+tableModel.getValueAt(row, 0).toString()+"'";
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
            nonaktif_teks();
            aktif_btn_default();
            
        }catch(Exception ex){    
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[]=new String[5];
        if(combo_nim.getSelectedIndex() == 0 || combo_kode_mk.getSelectedIndex() == 0 
            || txt_nilai.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong, silahkan lengkapi");
            if(combo_nim.getSelectedIndex() == 0) combo_nim.requestFocus();
            if(combo_kode_mk.getSelectedIndex() == 0) combo_kode_mk.requestFocus();
            if(txt_nilai.getText().isEmpty()) txt_nilai.requestFocus();
        }else{
            try{
                // START get indeks dan keterangan
                double nilai = Double.valueOf(txt_nilai.getText());
                char indeks;
                String keterangan = "Lulus";
                
                if (nilai >= 80 && nilai <=100) {
                    indeks = 'A';         
                } else if(nilai >= 68) {
                    indeks = 'B';
                } else if(nilai >= 56) {
                    indeks = 'C';
                } else if(nilai >= 45) {
                    indeks = 'D';
                } else {
                    indeks = 'E';
                    keterangan = "Tidak Lulus";
                }
                // END get indeks dan keterangan
                
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO t_nilai(nim, kd_mk, nilai, t_nilai.index, ket) "
                        + "VALUES "
                        + "( '"+combo_nim.getSelectedItem()+"',"
                        + " '"+combo_kode_mk.getSelectedItem()+"',"
                        + " '"+txt_nilai.getText()+"',"
                        + " '"+indeks+"',"
                        + " '"+keterangan+"' )";
                stt.executeUpdate(SQL);
                data[0] = combo_nim.getSelectedItem().toString();
                data[1] = combo_kode_mk.getSelectedItem().toString();
                data[2] = txt_nilai.getText();
                data[3] = Character.toString(indeks);
                data[4] = keterangan;
                tableModel.insertRow(0, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                nonaktif_teks();
                aktif_btn_default();
            }catch(Exception ex){    
                JOptionPane.showMessageDialog(null, 
                        ex.getMessage(),"error",
                        JOptionPane.INFORMATION_MESSAGE
                );
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

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
       //Menghapus seluruh isi data di dalam jtable(table_mahasiswa)
        tableModel.setRowCount(0);
        
        //gunakan query untuk mencari
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT *"
                    + "FROM t_nilai "
                    + "WHERE nim LIKE '%"+txt_cari_nim_kode_mk.getText()+"%'"
                    + "OR kd_mk LIKE '%"+txt_cari_nim_kode_mk.getText()+"%'";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                //result field SQL : kd_nilai, nim, kd_mk, nilai, index, ket
                data[0] = res.getString(2); //start field at 2 for get field nim, kd_mk, nilai, index, ket
                data[1] = res.getString(3);
                data[2] = res.getString(4);
                data[3] = res.getString(5);
                data[4] = res.getString(6);
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
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        settableload();
        aktif_btn_default();
        membersihkan_teks();
        nonaktif_teks();
        txt_cari_nim_kode_mk.setText("");
    }//GEN-LAST:event_btn_tampilActionPerformed

    private void tabel_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilaiMouseClicked
        if(evt.getClickCount()==1){
            btn_tambah.setEnabled(false);
            btn_simpan.setEnabled(false);

            try {
                tampil_field();
            } catch (ParseException ex) {
                Logger.getLogger(frm_mhs.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tabel_nilaiMouseClicked

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
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_nilai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> combo_kode_mk;
    private javax.swing.JComboBox<String> combo_nim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabel_nilai;
    private javax.swing.JTextField txt_cari_nim_kode_mk;
    private javax.swing.JTextField txt_nilai;
    // End of variables declaration//GEN-END:variables
}
