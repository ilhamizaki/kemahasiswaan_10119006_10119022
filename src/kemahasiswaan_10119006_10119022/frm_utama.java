/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan_10119006_10119022;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
/**
 *
 * @author minrn
 */
public class frm_utama extends javax.swing.JFrame {

    /**
     * Creates new form frm_utama
     */
    public frm_utama() {
        initComponents();
    }
    private static class JTatto extends Component {
        public JTatto() {

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

        jToolBar1 = new javax.swing.JToolBar();
        menu_form_mahasiswa = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        menu_matkul = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        menu_form_mahasiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119006_10119022/mahasiswa.png"))); // NOI18N
        menu_form_mahasiswa.setText("Data Mahasiswa");
        menu_form_mahasiswa.setFocusable(false);
        menu_form_mahasiswa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menu_form_mahasiswa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menu_form_mahasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_form_mahasiswaActionPerformed(evt);
            }
        });
        jToolBar1.add(menu_form_mahasiswa);
        jToolBar1.add(jSeparator3);

        menu_matkul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119006_10119022/mata_kuliah.png"))); // NOI18N
        menu_matkul.setText("Data Mata Kuliah");
        menu_matkul.setFocusable(false);
        menu_matkul.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menu_matkul.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menu_matkul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_matkulActionPerformed(evt);
            }
        });
        jToolBar1.add(menu_matkul);
        jToolBar1.add(jSeparator1);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119006_10119022/nilai.png"))); // NOI18N
        jButton5.setText("Data Nilai Mahasiswa");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119006_10119022/nilai.png"))); // NOI18N
        jButton1.setText("Simulasi Nilai Akhir");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator4);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119006_10119022/nilai.png"))); // NOI18N
        jButton2.setText("Simulasi Kasus");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator5);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("10119006 / Ilham zaki");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("10119022 / Muhammad Ikhlas N R");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119006_10119022/ilham-006.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10119006_10119022/ikhlas-022.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Foto Pembuat ");

        jMenu1.setText("Aplikasi Kemahasiswaan");

        jMenuItem2.setText("Tentang Pembuat");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menu_form_mahasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_form_mahasiswaActionPerformed
        // TODO add your handling code here:
        frm_mhs mhs = new frm_mhs();
        mhs.setVisible(true);
        
        //menghilangkan form utama
        this.setVisible(false);
    }//GEN-LAST:event_menu_form_mahasiswaActionPerformed

    private void menu_matkulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_matkulActionPerformed
        // TODO add your handling code here:
        frm_matkul mk = new frm_matkul();
        mk.setVisible(true);
        
        //menghilangkan form utama
        this.setVisible(false);
    }//GEN-LAST:event_menu_matkulActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        frm_nilai nl = new frm_nilai();
        nl.setVisible(true);
        
        //menghilangkan form utama
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel("com.jtatto.plaf."
             + "aluminium.AluminiumLookAndFell");
            SwingUtilities.updateComponentTreeUI(new JTatto());
        } catch (Exception e) {
            
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton menu_form_mahasiswa;
    private javax.swing.JButton menu_matkul;
    // End of variables declaration//GEN-END:variables
}