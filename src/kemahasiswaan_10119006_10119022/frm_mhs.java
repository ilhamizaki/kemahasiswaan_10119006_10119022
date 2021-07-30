/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan_10119006_10119022;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;

//Fungsi import yang digunakan untuk SQL
import java.sql.*;
import java.text.ParseException;

//Fungsi Import yang digunakan untuk Tanggal
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author minrn
 */
public class frm_mhs extends javax.swing.JFrame {
    // deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    
    /**
     * Creates new form frm_mhs
     */
    public frm_mhs() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_mahasiswa.setModel(tableModel);
        txt_tgl_lahir.setDateFormatString("yyyy-MM-dd");
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
            new String [] {"NIM","Nama Mahasiswa","Tempat Lahir","Tanggal Lahir","Alamat"}
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
    
    // Start handling show all data in tabel_mahasiswa
    String data[] = new String[5];
    private void settableload(){
        String stat = "";
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt=kon.createStatement();
            String SQL = "select * from t_mahasiswa";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
            
            
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    // Start handling show all data in tabel_mahasiswa
    
    // START Method for handling UI
    public void membersihkan_teks(){
        txt_nim.setText("");
        txt_nama.setText("");
        txt_tgl_lahir.setDate(null);
        txt_tmpt_lahir.setText("");
        txt_alamat.setText("");
    }
    public void nonaktif_teks(){
        txt_nim.setEnabled(false);
        txt_nama.setEnabled(false);
        txt_tgl_lahir.setEnabled(false);
        txt_tmpt_lahir.setEnabled(false);
        txt_alamat.setEnabled(false);
    }
    public void aktif_teks(){
        txt_nim.setEnabled(true);
        txt_nama.setEnabled(true);
        txt_tgl_lahir.setEnabled(true);
        txt_tmpt_lahir.setEnabled(true);
        txt_alamat.setEnabled(true);
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
        row = tabel_mahasiswa.getSelectedRow();
        txt_nim.setText(tableModel.getValueAt(row, 0).toString());
        txt_nama.setText(tableModel.getValueAt(row, 1).toString());
        txt_tmpt_lahir.setText(tableModel.getValueAt(row, 2).toString());
        
        //START Handling set tanggal lahir field
        String dd = tableModel.getValueAt(row, 3).toString();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
        txt_tgl_lahir.setDate(date);
        //END Handling set tanggal lahir field
        
        txt_alamat.setText(tableModel.getValueAt(row, 4).toString());
        
        btn_ubah.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_batal.setEnabled(true);
        aktif_teks();
    }
    // END Handling Update
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nim = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tmpt_lahir = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_tgl_lahir = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_mahasiswa = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_cari_nim = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_tampil = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

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
        jLabel1.setText("Form Mahasiswa");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(23, 23, 23))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NIM");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tempat Lahir");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Tanggal Lahir");

        jLabel6.setText("(yyyy-mm-dd)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Alamat");

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane1.setViewportView(txt_alamat);

        tabel_mahasiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_mahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_mahasiswaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_mahasiswa);

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pencarian Data Mahasiswa"));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Masukan NIM");

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
                .addComponent(txt_cari_nim, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_tampil, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_cari_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari)
                    .addComponent(btn_tampil))
                .addContainerGap(26, Short.MAX_VALUE))
        );

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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_nama)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txt_tmpt_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_tgl_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addComponent(txt_tgl_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_tmpt_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_ubah)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal)
                    .addComponent(btn_keluar)
                    .addComponent(btn_hapus))
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        txt_nim.requestFocus();
        btn_tambah.setEnabled(false);
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        aktif_teks();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[]=new String[5];
        if((txt_nim.getText().isEmpty()) || (txt_tgl_lahir.getDate() == null)){
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong, silahkan lengkapi");
            txt_nim.requestFocus();
        }else{
            // Start hanlding tanggal lahir
            String tampilan = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tglLahir = String.valueOf(fm.format(txt_tgl_lahir.getDate()));
            // END hanlding tanggal lahir
            try{
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(
                                 database,
                                 user,
                                 pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO t_mahasiswa(nim,"
                        + "nama,"
                        + "ttl,"
                        + "tgl_lahir,"
                        + "alamat) "
                        + "VALUES "
                        + "( '"+txt_nim.getText()+"',"
                        + " '"+txt_nama.getText()+"' ,"
                        + " '"+txt_tmpt_lahir.getText()+"' ,"
                        + " '"+tglLahir+" ' ,"
                        + " '"+txt_alamat.getText()+"' )";
                stt.executeUpdate(SQL);
                data[0] = txt_nim.getText();
                data[1] = txt_nama.getText();
                data[2] = txt_tmpt_lahir.getText();
                data[3] = tglLahir;
                data[4] = txt_alamat.getText();
                tableModel.insertRow(0, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_simpan.setEnabled(false);
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

    private void tabel_mahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_mahasiswaMouseClicked
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
    }//GEN-LAST:event_tabel_mahasiswaMouseClicked

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        String nim = txt_nim.getText();
        String nama= txt_nama.getText();
        String tempat_lahir= txt_tmpt_lahir.getText();
        String alamat= txt_alamat.getText();
        // Start hanlding tanggal lahir
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl_lahir = String.valueOf(fm.format(txt_tgl_lahir.getDate()));
        // END hanlding tanggal lahir
        
        if((txt_nim.getText().isEmpty()) || (txt_tgl_lahir.getDate() == null)){
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong, silahkan lengkapi");
            txt_nim.requestFocus();
        }else{
            try{
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE t_mahasiswa "
                        + "SET "
                        + "nim = '" + nim + "', "
                        + "nama = '" + nama + "', "
                        + "ttl = '" + tempat_lahir + "', "
                        + "tgl_lahir = '" + tgl_lahir + "', "
                        + "alamat = '" + alamat + "' "
                        + "WHERE nim = '" + tableModel.getValueAt(row, 0).toString() + "'";
                stt.executeUpdate(SQL);
                data[0] = nim;
                data[1] = nama;
                data[2] = tempat_lahir;
                data[3] = tgl_lahir;
                data[4] = alamat;
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
            String SQL = "DELETE FROM t_mahasiswa WHERE "
                        + "nim='"+tableModel.getValueAt(row, 0).toString()+"'";
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
            aktif_btn_default();
            
        }catch(Exception ex){    
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        //Menghapus seluruh isi data di dalam jtable(table_mahasiswa)
        tableModel.setRowCount(0);
        
        //gunakan query untuk mencari
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM t_mahasiswa "
                    + "WHERE nim LIKE '%"+txt_cari_nim.getText()+"%'";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
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
        txt_cari_nim.setText("");
    }//GEN-LAST:event_btn_tampilActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
        utama.setVisible(true);
        
        //menghilangkan form utam
        this.setVisible(false);
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        aktif_btn_default();
        membersihkan_teks();
        nonaktif_teks();
    }//GEN-LAST:event_btn_batalActionPerformed

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
            java.util.logging.Logger.getLogger(frm_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_mhs().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabel_mahasiswa;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_cari_nim;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nim;
    private com.toedter.calendar.JDateChooser txt_tgl_lahir;
    private javax.swing.JTextField txt_tmpt_lahir;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel getDefaultTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
