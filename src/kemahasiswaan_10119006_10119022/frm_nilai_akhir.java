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
public class frm_nilai_akhir extends javax.swing.JFrame {
    // deklarasi variable
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    double jumlah_pertemuan, tugas1, tugas2, tugas3, uts, uas;
    double n_absen, n_tugas, n_uts, n_uas, n_akhir;
    double p_absen, p_tugas, p_uts, p_uas;
    char index;
    String keterangan;
    /**
     * Creates new form frm_nilai_akhir
     */
    public frm_nilai_akhir() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_nilai_akhir.setModel(tableModel);
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
            new String [] {"Nama M.K","Persentase Absen","Persentase Tugas",""
                    + "Persentase UTS","Persentase UAS","Absensi","Tgs 1",""
                    + "Tgs 2","Tgs 3", "UTS","UAS","Nilai Absen","Nilai Tugas",""
                    + "Nilai UTS","Nilai UAS","Nilai Akhir","Index","Keterangan"} // 18 field
        )
        {
         // Disable Edit Tabel
         boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false, false, false, false
                , false, false, false, false, false, false, false, false, false //18 field
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    // END handling table
    
    // START handling show all data in tabel_nilai_akhir
    String data[] = new String[18];
    private void settableload(){
        String stat = "";
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt=kon.createStatement();
            String SQL = "SELECT nama_mk, persen_absen, persen_tugas, persen_uts, "
                    + "persen_uas, kehadiran, tugas1, tugas2, tugas3, "
                    + "uts, uas, nilai_absen, nilai_tugas, nilai_uts, nilai_uas, "
                    + "nilai_akhir,t_nilai_akhir.index, ket\n" +
                    "FROM t_nilai_akhir\n" +
                    "JOIN t_mata_kuliah ON t_mata_kuliah.kd_mk = t_nilai_akhir.kd_mk";
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
                data[8] = res.getString(9);
                data[9] = res.getString(10);
                data[10] = res.getString(11);
                data[11] = res.getString(12);
                data[12] = res.getString(13);
                data[13] = res.getString(14);
                data[14] = res.getString(15);
                data[15] = res.getString(16);
                data[16] = res.getString(17);
                data[17] = res.getString(18);
                tableModel.addRow(data);
            }
            
            //Get nama_mk for combobox
            String SQL_nama_mk = "SELECT * FROM t_mata_kuliah";
            ResultSet res_nama_mk = stt.executeQuery(SQL_nama_mk);
            while (res_nama_mk.next()) {
                //Set Value Combobox
                String nama_mk = res_nama_mk.getString("nama_mk");
                combo_mk.addItem(nama_mk);
            }
            
            res.close();
            res_nama_mk.close();
            stt.close();
            kon.close();
            
            
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    // END handling show all data in tabel_nilai_akhir
    
    // START Method for handling UI
    public void membersihkan_teks(){
        combo_mk.setSelectedIndex(0);
        txt_kd_mk.setText("");
        txt_persen_absen.setText("");
        txt_persen_tugas.setText("");
        txt_persen_uts.setText("");
        txt_persen_uas.setText("");
        txt_kehadiran.setText("");
        txt_tugas1.setText("");
        txt_tugas2.setText("");
        txt_tugas3.setText("");
        txt_uts.setText("");
        txt_uas.setText("");
    }
    public void nonaktif_teks(){
        combo_mk.setEnabled(false);
        txt_kd_mk.setEditable(false);
        txt_persen_absen.setEnabled(false);
        txt_persen_tugas.setEnabled(false);
        txt_persen_uts.setEnabled(false);
        txt_persen_uas.setEnabled(false);
        txt_kehadiran.setEnabled(false);
        txt_tugas1.setEnabled(false);
        txt_tugas2.setEnabled(false);
        txt_tugas3.setEnabled(false);
        txt_uts.setEnabled(false);
        txt_uas.setEnabled(false);
    }
    public void aktif_teks(){
        combo_mk.setEnabled(true);
        txt_persen_absen.setEnabled(true);
        txt_persen_tugas.setEnabled(true);
        txt_persen_uts.setEnabled(true);
        txt_persen_uas.setEnabled(true);
        txt_kehadiran.setEnabled(true);
        txt_tugas1.setEnabled(true);
        txt_tugas2.setEnabled(true);
        txt_tugas3.setEnabled(true);
        txt_uts.setEnabled(true);
        txt_uas.setEnabled(true);
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
    
    public void hitung_nilai() {
        // Nilai absen
        jumlah_pertemuan = Double.valueOf(txt_kehadiran.getText());
        p_absen = Double.valueOf(txt_persen_absen.getText());
        n_absen = (((jumlah_pertemuan/14)*100*p_absen)/100);

        // Nilai Tugas
        tugas1 = Double.valueOf(txt_tugas1.getText());
        tugas2 = Double.valueOf(txt_tugas2.getText());
        tugas3 = Double.valueOf(txt_tugas3.getText());
        p_tugas = Double.valueOf(txt_persen_tugas.getText());
        n_tugas = (((tugas1+tugas2+tugas3)/3)*p_tugas/100);

        // Nilai UTS
        uts = Double.valueOf(txt_uts.getText());
        p_uts = Double.valueOf(txt_persen_uts.getText());
        n_uts = uts*(p_uts/100);

        // Nilai Uas
        uas = Double.valueOf(txt_uas.getText());
        p_uas = Double.valueOf(txt_persen_uas.getText());
        n_uas = uas*(p_uas/100);

        n_akhir = n_absen + n_tugas + n_uts + n_uas;  
        
        // START get indeks dan keterangan
        if (n_akhir >= 80 && n_akhir <=100) {
            index = 'A';
            keterangan = "Lulus";
        } else if(n_akhir >= 68) {
            index = 'B';
            keterangan = "Lulus";
        } else if(n_akhir >= 56) {
            index = 'C';
            keterangan = "Lulus";
        } else if(n_akhir >= 45) {
            index = 'D';
            keterangan = "Tidak Lulus";
        } else {
            index = 'E';
            keterangan = "Tidak Lulus";
        }
        // END get indeks dan keterangan
        
        if (jumlah_pertemuan < 11) {
            keterangan = "Tidak Lulus";
        }
    }
    
    // START Handling Update
    int row =0;
    public void tampil_field() throws ParseException{
        row = tabel_nilai_akhir.getSelectedRow();
        // Handling set input combo from table value
        String mk = tableModel.getValueAt(row, 0).toString(); 
        combo_mk.setSelectedItem(mk);
        txt_persen_absen.setText(tableModel.getValueAt(row, 1).toString());
        txt_persen_tugas.setText(tableModel.getValueAt(row, 2).toString());
        txt_persen_uts.setText(tableModel.getValueAt(row, 3).toString());
        txt_persen_uas.setText(tableModel.getValueAt(row, 4).toString());
        txt_kehadiran.setText(tableModel.getValueAt(row, 5).toString());
        txt_tugas1.setText(tableModel.getValueAt(row, 6).toString());
        txt_tugas2.setText(tableModel.getValueAt(row, 7).toString());
        txt_tugas3.setText(tableModel.getValueAt(row, 8).toString());
        txt_uts.setText(tableModel.getValueAt(row, 9).toString());
        txt_uas.setText(tableModel.getValueAt(row, 10).toString());
        
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_persen_uas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_persen_uts = new javax.swing.JTextField();
        txt_persen_tugas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_persen_absen = new javax.swing.JTextField();
        txt_kd_mk = new javax.swing.JTextField();
        combo_mk = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_tugas3 = new javax.swing.JTextField();
        txt_tugas2 = new javax.swing.JTextField();
        txt_tugas1 = new javax.swing.JTextField();
        txt_kehadiran = new javax.swing.JTextField();
        txt_uts = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_uas = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_nilai_akhir = new javax.swing.JTable();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

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
        jLabel1.setText("Form Simulasi Nilai Akhir");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_10119006_10119022/icons8_single_page_mode_50px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nama Mata Kuliah");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Kode MK");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Persentase Absen");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Persentase Tugas");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Persentase UTS");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Persentase UAS");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("%");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("%");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("%");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("%");

        combo_mk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Mata Kuliah -" }));
        combo_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mkActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Kehadiran");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Tugas 1");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Tugas 2");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Tugas 3");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("UTS");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("UAS");

        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        tabel_nilai_akhir.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_nilai_akhir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilai_akhirMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_nilai_akhir);

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

        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_10119006_10119022/icons8_search_24px_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(combo_mk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_kd_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txt_persen_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_persen_absen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_persen_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_persen_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))))
                                .addGap(142, 142, 142)
                                .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addGap(274, 274, 274)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel13))
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(217, 217, 217))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(combo_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_kd_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txt_persen_absen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(txt_persen_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17)
                            .addComponent(txt_persen_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txt_persen_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_ubah)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal)
                    .addComponent(btn_keluar)
                    .addComponent(btn_hapus))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabel_nilai_akhirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilai_akhirMouseClicked
        if(evt.getClickCount()==1){
            btn_tambah.setEnabled(false);
            btn_simpan.setEnabled(false);

            try {
                tampil_field();
            } catch (ParseException ex) {
                Logger.getLogger(frm_mhs.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tabel_nilai_akhirMouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        combo_mk.requestFocus();
        btn_tambah.setEnabled(false);
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        aktif_teks();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        // START Validasi Empty Input
        if(combo_mk.getSelectedIndex() == 0 || txt_persen_absen.getText().isEmpty() ||
            txt_persen_tugas.getText().isEmpty() || txt_persen_uts.getText().isEmpty() ||
            txt_persen_uas.getText().isEmpty() || txt_kehadiran.getText().isEmpty() || 
            txt_tugas1.getText().isEmpty() || txt_tugas2.getText().isEmpty() || 
            txt_tugas3.getText().isEmpty() || txt_uts.getText().isEmpty() || 
            txt_uas.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan lengkapi");
            if(combo_mk.getSelectedIndex() == 0) combo_mk.requestFocus();
            if(txt_persen_absen.getText().isEmpty()) txt_persen_absen.requestFocus();
            if(txt_persen_tugas.getText().isEmpty()) txt_persen_tugas.requestFocus();
            if(txt_persen_uts.getText().isEmpty()) txt_persen_uts.requestFocus();
            if(txt_persen_uas.getText().isEmpty()) txt_persen_uas.requestFocus();
            if(txt_kehadiran.getText().isEmpty()) txt_kehadiran.requestFocus();
            if(txt_tugas1.getText().isEmpty()) txt_tugas1.requestFocus();
            if(txt_tugas2.getText().isEmpty()) txt_tugas2.requestFocus();
            if(txt_tugas3.getText().isEmpty()) txt_tugas3.requestFocus();
            if(txt_uts.getText().isEmpty()) txt_uts.requestFocus();
            if(txt_uas.getText().isEmpty()) txt_uas.requestFocus();
        // END Validasi Empty Input
        }else{
            try{
                if(Integer.valueOf(txt_kehadiran.getText()) > 14) {
                    JOptionPane.showMessageDialog(null, "Kehadiran melebihi Maksimal (Max. 14 Pertemuan)");
                    txt_kehadiran.requestFocus();
                } else {
                    hitung_nilai();
                    Class.forName(driver);
                    Connection kon = DriverManager.getConnection(database, user, pass);
                    Statement stt = kon.createStatement();
                    String SQL = "UPDATE t_nilai_akhir "
                    + "SET "
                    + "kd_mk = '" + txt_kd_mk.getText() + "', "
                    + "persen_absen = '" + txt_persen_absen.getText() + "', "
                    + "persen_tugas = '" + txt_persen_tugas.getText() + "', "
                    + "persen_uts = '" + txt_persen_uts.getText() + "', "
                    + "persen_uas = '" + txt_persen_uas.getText() + "', "
                    + "kehadiran = '" + txt_kehadiran.getText() + "', "
                    + "tugas1 = '" + txt_tugas1.getText() + "', "
                    + "tugas2 = '" + txt_tugas2.getText() + "', "
                    + "tugas3 = '" + txt_tugas3.getText() + "', "
                    + "uts = '" + txt_uts.getText() + "', "
                    + "uas = '" + txt_uas.getText() + "', "
                    + "nilai_absen = '" + String.format("%.2f", n_absen) + "', "
                    + "nilai_tugas = '" + String.format("%.2f", n_tugas) + "', "
                    + "nilai_uts = '" + String.format("%.2f", n_uts) + "', "
                    + "nilai_uas = '" + String.format("%.2f", n_uas) + "', "
                    + "nilai_akhir = '" + String.format("%.2f", n_akhir) + "', "
                    + "t_nilai_akhir.index = '" + index + "', "
                    + "ket = '" + keterangan + "' "
                    + "WHERE kd_mk = '" + txt_kd_mk.getText() + "'";
                    stt.executeUpdate(SQL);
                    data[0] = combo_mk.getSelectedItem().toString();
                    data[1] = txt_persen_absen.getText();
                    data[2] = txt_persen_tugas.getText();
                    data[3] = txt_persen_uts.getText();
                    data[4] = txt_persen_uas.getText();
                    data[5] = txt_kehadiran.getText();
                    data[6] = txt_tugas1.getText();
                    data[7] = txt_tugas2.getText();
                    data[8] = txt_tugas3.getText();
                    data[9] = txt_uts.getText();
                    data[10] = txt_uas.getText();
                    data[11] = String.format("%.2f", n_absen);
                    data[12] = String.format("%.2f", n_tugas);
                    data[13] = String.format("%.2f", n_uts);
                    data[14] = String.format("%.2f", n_uas);
                    data[15] = String.format("%.2f", n_akhir);
                    data[16] = Character.toString(index);
                    data[17] = keterangan;
                    tableModel.removeRow(row);
                    tableModel.insertRow(row, data);
                    stt.close();
                    kon.close();
                    membersihkan_teks();
                    nonaktif_teks();
                    aktif_btn_default();
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                }
            }catch(Exception ex){
                System.err.println(ex.getMessage());
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
                String SQL = "DELETE FROM t_nilai_akhir WHERE "
                            + "kd_mk='"+txt_kd_mk.getText()+"'";
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
        String data[]=new String[18];
        // START Validasi Empty Input
        if(combo_mk.getSelectedIndex() == 0 || txt_persen_absen.getText().isEmpty() ||
            txt_persen_tugas.getText().isEmpty() || txt_persen_uts.getText().isEmpty() ||
            txt_persen_uas.getText().isEmpty() || txt_kehadiran.getText().isEmpty() || 
            txt_tugas1.getText().isEmpty() || txt_tugas2.getText().isEmpty() || 
            txt_tugas3.getText().isEmpty() || txt_uts.getText().isEmpty() || 
            txt_uas.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan lengkapi");
            if(combo_mk.getSelectedIndex() == 0) combo_mk.requestFocus();
            if(txt_persen_absen.getText().isEmpty()) txt_persen_absen.requestFocus();
            if(txt_persen_tugas.getText().isEmpty()) txt_persen_tugas.requestFocus();
            if(txt_persen_uts.getText().isEmpty()) txt_persen_uts.requestFocus();
            if(txt_persen_uas.getText().isEmpty()) txt_persen_uas.requestFocus();
            if(txt_kehadiran.getText().isEmpty()) txt_kehadiran.requestFocus();
            if(txt_tugas1.getText().isEmpty()) txt_tugas1.requestFocus();
            if(txt_tugas2.getText().isEmpty()) txt_tugas2.requestFocus();
            if(txt_tugas3.getText().isEmpty()) txt_tugas3.requestFocus();
            if(txt_uts.getText().isEmpty()) txt_uts.requestFocus();
            if(txt_uas.getText().isEmpty()) txt_uas.requestFocus();
        // END Validasi Empty Input
        }else{
            try{
                if(Integer.valueOf(txt_kehadiran.getText()) > 14) {
                    JOptionPane.showMessageDialog(null, "Kehadiran melebihi Maksimal (Max. 14 Pertemuan)");
                    txt_kehadiran.requestFocus();
                } else {
                   hitung_nilai();
                    Class.forName(driver);
                    Connection kon = DriverManager.getConnection(database,user,pass);
                    Statement stt = kon.createStatement();
                    String SQL = "INSERT INTO t_nilai_akhir(kd_mk, persen_absen, persen_tugas, "
                    + "persen_uts, persen_uas, kehadiran, tugas1, tugas2, tugas3, "
                    + "uts, uas, nilai_absen, nilai_tugas, nilai_uts, nilai_uas, "
                    + "nilai_akhir,t_nilai_akhir.index, ket)\n"
                    + "VALUES "
                    + "( '"+txt_kd_mk.getText()+"',"
                    + " '"+txt_persen_absen.getText()+"',"
                    + " '"+txt_persen_tugas.getText()+"',"
                    + " '"+txt_persen_uts.getText()+"',"
                    + " '"+txt_persen_uas.getText()+"',"
                    + " '"+txt_kehadiran.getText()+"',"
                    + " '"+txt_tugas1.getText()+"',"
                    + " '"+txt_tugas2.getText()+"',"
                    + " '"+txt_tugas3.getText()+"',"
                    + " '"+txt_uts.getText()+"',"
                    + " '"+txt_uas.getText()+"',"
                    + " '"+String.format("%.2f", n_absen)+"',"
                    + " '"+String.format("%.2f", n_tugas)+"',"
                    + " '"+String.format("%.2f", n_uts)+"',"
                    + " '"+String.format("%.2f", n_uas)+"',"
                    + " '"+String.format("%.2f", n_akhir)+"',"
                    + " '"+index+"',"
                    + " '"+keterangan+"' )";
                    stt.executeUpdate(SQL);
                    data[0] = combo_mk.getSelectedItem().toString();
                    data[1] = txt_persen_absen.getText();
                    data[2] = txt_persen_tugas.getText();
                    data[3] = txt_persen_uts.getText();
                    data[4] = txt_persen_uas.getText();
                    data[5] = txt_kehadiran.getText();
                    data[6] = txt_tugas1.getText();
                    data[7] = txt_tugas2.getText();
                    data[8] = txt_tugas3.getText();
                    data[9] = txt_uts.getText();
                    data[10] = txt_uas.getText();
                    data[11] = String.format("%.2f", n_absen);
                    data[12] = String.format("%.2f", n_tugas);
                    data[13] = String.format("%.2f", n_uts);
                    data[14] = String.format("%.2f", n_uas);
                    data[15] = String.format("%.2f", n_akhir);
                    data[16] = Character.toString(index);
                    data[17] = keterangan;
                    tableModel.insertRow(0, data);
                    stt.close();
                    kon.close();
                    membersihkan_teks();
                    nonaktif_teks();
                    aktif_btn_default();
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                }
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

    private void combo_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mkActionPerformed
        // TODO add your handling code here:
//        System.out.println(combo_mk.getSelectedItem());
        
        if(combo_mk.getSelectedIndex() == 0) {
            txt_kd_mk.setText("");
        } else {
            try {
              Class.forName(driver);
              Connection kon = DriverManager.getConnection(database, user, pass);
              Statement stt = kon.createStatement();
              String SQL = "SELECT * FROM t_mata_kuliah WHERE nama_mk = '"+combo_mk.getSelectedItem()+"'";
              ResultSet res = stt.executeQuery(SQL);
              while (res.next()) {
                  txt_kd_mk.setText(res.getString("kd_mk"));
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
        }
    }//GEN-LAST:event_combo_mkActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
        utama.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
        // TODO add your handling code here:
        //Menghapus seluruh isi data di dalam jtable(table_mahasiswa)
        tableModel.setRowCount(0);
        
        //gunakan query untuk mencari
        try{
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT nama_mk, persen_absen, persen_tugas, persen_uts, "
                    + "persen_uas, kehadiran, tugas1, tugas2, tugas3, "
                    + "uts, uas, nilai_absen, nilai_tugas, nilai_uts, nilai_uas, "
                    + "nilai_akhir,t_nilai_akhir.index, ket " 
                    + "FROM t_nilai_akhir "
                    + "JOIN t_mata_kuliah ON t_mata_kuliah.kd_mk = t_nilai_akhir.kd_mk "
                    + "WHERE nama_mk LIKE '%"+txt_cari.getText()+"%'";
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
                data[8] = res.getString(9);
                data[9] = res.getString(10);
                data[10] = res.getString(11);
                data[11] = res.getString(12);
                data[12] = res.getString(13);
                data[13] = res.getString(14);
                data[14] = res.getString(15);
                data[15] = res.getString(16);
                data[16] = res.getString(17);
                data[17] = res.getString(18);
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
            java.util.logging.Logger.getLogger(frm_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_nilai_akhir().setVisible(true);
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
    private javax.swing.JComboBox<String> combo_mk;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabel_nilai_akhir;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kd_mk;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_persen_absen;
    private javax.swing.JTextField txt_persen_tugas;
    private javax.swing.JTextField txt_persen_uas;
    private javax.swing.JTextField txt_persen_uts;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    private javax.swing.JTextField txt_uas;
    private javax.swing.JTextField txt_uts;
    // End of variables declaration//GEN-END:variables
}
