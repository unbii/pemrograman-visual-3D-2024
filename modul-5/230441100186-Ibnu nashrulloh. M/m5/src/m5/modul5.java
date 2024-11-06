/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package m5;
//yMenghubungkan aplikasi Java dengan database.
import java.sql.Connection;
//Menjalankan pernyataan SQL dengan parameter
import java.sql.PreparedStatement;
//dMenyimpan dan memanipulasi hasil query dari database.
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
//kMembuat dan mengelola model data untuk tabel
import javax.swing.table.DefaultTableModel;

import m5.koneksi;
/**
 *
 * @author ASUS
 */
public class modul5 extends javax.swing.JFrame {
   //Nama variabel yang akan menyimpan daftar ID karyawan bertipe array.
private ArrayList<Integer> karyawanIds = new ArrayList<>();
//Nama variabel yang akan menyimpan daftar ID proyek bertipe array.
private ArrayList<Integer> proyekIds = new ArrayList<>();
    //Nama variabel yang akan menyimpan koneksi ke database dan mengelolanya juga.
    Connection conn;
    //Nama variabel yang akan menyimpan model data untuk tabel bertipe defaultmodel
    private DefaultTableModel model_tbl;
    private DefaultTableModel model_transaksi;
    Connection con;
    private DefaultTableModel model;  
    
    //end global variable
    /**
     * Creates new form terserah
     */
  
    /**
     * Creates new form modul5
     */
    public modul5() {
        initComponents();
        conn = koneksi.getConnection();
        // model_tbl adalah objek DefaultTableModel yang akan digunakan sebagai model untuk tabel_karyawan. 
    model_tbl = new DefaultTableModel();
    tabel_karyawan.setModel(model_tbl);
    
    model_tbl.addColumn("id");
    model_tbl.addColumn("nama");
    model_tbl.addColumn("jabatan");
    model_tbl.addColumn("departemen");
        loadDatakaryawan();{
         con = koneksi.getConnection();
    model = new DefaultTableModel();
    tabel_proyek.setModel(model);
        
    model.addColumn("id proyek");
    model.addColumn("nama proyek");
    model.addColumn("durasi pengerjaan");
    
    model_transaksi = new DefaultTableModel();
        tabel_transaksi.setModel(model_transaksi);
        model_transaksi.addColumn("ID Karyawan");
        model_transaksi.addColumn("Nama Karyawan");
        model_transaksi.addColumn("ID Proyek");
        model_transaksi.addColumn("Nama Proyek"); 
        model_transaksi.addColumn("Peran");
        loadDatakaryawan();
        loadDataproyek();
        loadComboKaryawan();
        loadComboProyek();
        loadDataTransaksi();
        
    }
    }
    private void loadDatakaryawan() {
      model_tbl.setRowCount(0);

      try {
          String sql = "SELECT * FROM tb_karyawan";
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet hasil = ps.executeQuery();
          while (hasil.next()) {
             // Menambahkan baris ke dalam model tabel
             model_tbl.addRow(new Object[]{
             hasil.getInt("id"),
             hasil.getString("nama"),
             hasil.getString("jabatan"),
             hasil.getString("departemen"),
                     
           });
          }
      } catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       }
    }
    private void saveDatakaryawan() {
  try{
     String sql = "INSERT INTO tb_karyawan (nama,jabatan,departemen) VALUES (?,?,?)";
     PreparedStatement ps = conn.prepareStatement(sql);
     ps.setString(1, tf_karyawan.getText());
     ps.setString(2, tf_jabatan.getText());
     ps.setString(3, tf_departemen.getText());
     ps.executeUpdate();
     JOptionPane.showMessageDialog(this, "Data saved successfully");
     loadDatakaryawan();
     loadComboKaryawan();
     clearTextFields();
   } catch (SQLException e) {
     System.out.println("Error Save Data" + e.getMessage());
   }
 }
     private void updateDatakaryawan() {
  try {
      String sql = "UPDATE tb_karyawan SET nama = ?, jabatan = ?, departemen = ? WHERE id = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, tf_karyawan.getText());
      ps.setString(2, tf_jabatan.getText());
      ps.setString(3, tf_departemen.getText());
      ps.setInt(4, Integer.parseInt(tf_idkaryawan.getText()));
      ps.executeUpdate();
      JOptionPane.showMessageDialog(this, "Data updated successfully");
      loadDatakaryawan();
      loadComboKaryawan();
      clearTextFields();
  }  catch (SQLException e) {
      System.out.println("Error Save Data" + e.getMessage());
  }
 }
    private void deleteDatakaryawan() {
 try  {
      String sql = "DELETE FROM tb_karyawan WHERE id = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, Integer.parseInt(tf_idkaryawan.getText()));
      ps.executeUpdate();
      JOptionPane.showMessageDialog(this, "Data deleted successfully");
      loadDatakaryawan();
      loadComboKaryawan();
      clearTextFields();
      resetAutoIncrement("tb_karyawan"); 
 } catch (SQLException e) {
      System.out.println("Error Save Data" + e.getMessage());
  }
}
    private void loadDataproyek() {
      model.setRowCount(0);

      try {
          String sql = "SELECT * FROM tb_proyek";
          PreparedStatement ps = con.prepareStatement(sql);
          ResultSet hasil = ps.executeQuery();
          while (hasil.next()) {
             // Menambahkan baris ke dalam model tabel
             model.addRow(new Object[]{
             hasil.getInt("id"),
             hasil.getString("nama_proyek"),
             hasil.getString("durasi_pengerjaan")     
           });
          }
      } catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       }
    }
      private void saveDataproyek() {
  try{
     String sql = "INSERT INTO tb_proyek (nama_proyek,durasi_pengerjaan) VALUES (?,?)";
     PreparedStatement ambil = con.prepareStatement(sql);
     ambil.setString(1, tf_namaproyek.getText());
     ambil.setString(2, tf_durasipengerjaan.getText());
     ambil.executeUpdate();
     JOptionPane.showMessageDialog(this, "Data saved successfully");
     loadDataproyek();
      loadComboProyek();
      clearTextFields();
   } catch (SQLException e) {
     System.out.println("Error Save Data" + e.getMessage());
   }
 }
    private void updateDataproyek() {
  try {
      String sql = "UPDATE tb_proyek SET nama_proyek = ?, durasi_pengerjaan = ? WHERE id = ?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, tf_namaproyek.getText());
      ps.setString(2, tf_durasipengerjaan.getText());
      ps.setInt(3, Integer.parseInt(tf_idproyek.getText()));
      ps.executeUpdate();
      JOptionPane.showMessageDialog(this, "Data updated successfully");
      loadDataproyek();
      loadComboProyek();
      clearTextFields();
  }  catch (SQLException e) {
      System.out.println("Error Save Data" + e.getMessage());
  }
 }
      private void deleteDataproyek() {
 try  {
      String sql = "DELETE FROM tb_proyek WHERE id = ?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, Integer.parseInt(tf_idproyek.getText()));
      ps.executeUpdate();
      JOptionPane.showMessageDialog(this, "Data deleted successfully");
      loadDataproyek();
      loadComboProyek();
      clearTextFields();
      resetAutoIncrement("tb_proyek"); 
 } catch (SQLException e) {
      System.out.println("Error Save Data" + e.getMessage());
  }
}
      private void loadDataTransaksi() {
    model_transaksi.setRowCount(0);
    try {
        //JOIN digunakan untuk menggabungkan dua tabel atau lebih berdasarkan kolom yang saling terkait.
        //ON digunakan untuk menentukan kondisi penggabungan antara dua tabel. 
        String sql = """
            SELECT t.id_karyawan, k.nama AS nama_karyawan, 
                   t.id_proyek, p.nama_proyek, t.peran
            FROM tbl_transaksi t
            JOIN tb_karyawan k ON t.id_karyawan = k.id
            JOIN tb_proyek p ON t.id_proyek = p.id
        """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model_transaksi.addRow(new Object[]{
                rs.getInt("id_karyawan"),
                rs.getString("nama_karyawan"),
                rs.getInt("id_proyek"),
                rs.getString("nama_proyek"),
                rs.getString("peran")
            });
        }
    } catch (SQLException e) {
        System.out.println("Error loading transaction data: " + e.getMessage());
    }
}
           private void loadComboKaryawan() {
        combokaryawan.removeAllItems();
        
        karyawanIds.clear();

        try {
            String sql = "SELECT id, nama FROM tb_karyawan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");

                karyawanIds.add(id);
                combokaryawan.addItem(nama);
            }
        } catch (SQLException e) {
            System.out.println("Error loading karyawan data: " + e.getMessage());
        }
    }

    private void loadComboProyek() {
        comboproyek.removeAllItems();
        //menghapus combo box dan agar bisa menampilkan combo box baru yang sudah diinputkan
        proyekIds.clear();
        //agar hanya ID yang baru saja diambil dari database yang ada di dalam daftar
        try {
            String sql = "SELECT id, nama_proyek FROM tb_proyek";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String namaProyek = rs.getString("nama_proyek");

                proyekIds.add(id);
                comboproyek.addItem(namaProyek);
            }
        } catch (SQLException e) {
            System.out.println("Error loading proyek data: " + e.getMessage());
        }
    }

    private int getSelectedKaryawanId() {
        int selectedIndex = combokaryawan.getSelectedIndex();
        return selectedIndex >= 0 ? karyawanIds.get(selectedIndex) : -1;
    }

    private int getSelectedProyekId() {
        int selectedIndex = comboproyek.getSelectedIndex();
        return selectedIndex >= 0 ? proyekIds.get(selectedIndex) : -1;
    }

    private void saveTransaksi() {
    int karyawanId = getSelectedKaryawanId();
    int proyekId = getSelectedProyekId();
    String peran = tf_peran.getText();
    
    if (karyawanId == -1 || proyekId == -1 || peran.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please complete all fields before saving.");
        return;
    }

    try {
        String sql = "INSERT INTO tbl_transaksi (id_karyawan, id_proyek, peran) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, karyawanId);
        ps.setInt(2, proyekId);
        ps.setString(3, peran );
        
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Transaction saved successfully.");
        loadDataTransaksi(); // Refresh tabel transaksi setelah menyimpan data
        clearTextFields();
    } catch (SQLException e) {
        System.out.println("Error saving transaction: " + e.getMessage());
    }
}
    private void updateTransaksi() {
    int karyawanId = getSelectedKaryawanId();
    int proyekId = getSelectedProyekId();
    String peran = tf_peran.getText();
    
    if (karyawanId == -1 || proyekId == -1 || peran.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please complete all fields before updating.");
        return;
    }

    try {
        String sql = "UPDATE tbl_transaksi SET peran = ? WHERE id_karyawan = ? AND id_proyek = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, peran);
        ps.setInt(2, karyawanId);
        ps.setInt(3, proyekId);
        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Transaction updated successfully.");
            loadDataTransaksi(); // Refresh tabel transaksi setelah update data
             clearTextFields();
        } else {
            JOptionPane.showMessageDialog(this, "Update failed: Transaction not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error updating transaction: " + e.getMessage());
    }
}
    private void deleteTransaksi() {
    int karyawanId = getSelectedKaryawanId();
    int proyekId = getSelectedProyekId();
    
    if (karyawanId == -1 || proyekId == -1) {
        JOptionPane.showMessageDialog(this, "Please select a valid transaction before deleting.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this transaction?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            String sql = "DELETE FROM tbl_transaksi WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, karyawanId);
            ps.setInt(2, proyekId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Transaction deleted successfully.");
                loadDataTransaksi(); // Refresh tabel transaksi setelah data dihapus
                resetAutoIncrement("tbl_transaksi"); 
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed: Transaction not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting transaction: " + e.getMessage());
        }
    }
}
    private void clearTextFields() {
    tf_karyawan.setText("");
    tf_jabatan.setText("");
    tf_departemen.setText("");
    tf_idkaryawan.setText("");
    tf_namaproyek.setText("");
    tf_durasipengerjaan.setText("");
    tf_idproyek.setText("");
    tf_peran.setText("");
}
    private void resetAutoIncrement(String tableName) {
    try {
        // Set ulang ID pada tabel yang sudah kosong
        String sqlResetIncrement = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
        PreparedStatement psReset = conn.prepareStatement(sqlResetIncrement);
        psReset.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error resetting auto-increment: " + e.getMessage());
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

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_idkaryawan = new javax.swing.JTextField();
        tf_karyawan = new javax.swing.JTextField();
        tf_jabatan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tf_departemen = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_karyawan = new javax.swing.JTable();
        btnsimpankarywan = new javax.swing.JButton();
        btnupdatekaryawan = new javax.swing.JButton();
        btndeletekaryawan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tf_idproyek = new javax.swing.JTextField();
        tf_namaproyek = new javax.swing.JTextField();
        tf_durasipengerjaan = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_proyek = new javax.swing.JTable();
        btnsimpanproyek = new javax.swing.JButton();
        btnupdateproyek = new javax.swing.JButton();
        btndeleteproyek = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        combokaryawan = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        comboproyek = new javax.swing.JComboBox<>();
        tf_peran = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_transaksi = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APLIKASI MANAJEMEN PROYEK");
        jPanel7.add(jLabel1);

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setText("ID :");

        jLabel3.setText("Nama Karyawan:");

        jLabel4.setText("jabatan :");

        tf_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_karyawanActionPerformed(evt);
            }
        });

        jLabel5.setText("Departemen:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_idkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_departemen, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(tf_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(tf_idkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(tf_departemen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(tf_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(204, 255, 255));

        tabel_karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_karyawan);

        btnsimpankarywan.setText("SIMPAN");
        btnsimpankarywan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpankarywanActionPerformed(evt);
            }
        });

        btnupdatekaryawan.setText("UPDATE");
        btnupdatekaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdatekaryawanActionPerformed(evt);
            }
        });

        btndeletekaryawan.setText("DELETE");
        btndeletekaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletekaryawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsimpankarywan)
                    .addComponent(btnupdatekaryawan)
                    .addComponent(btndeletekaryawan))
                .addGap(24, 24, 24))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnsimpankarywan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnupdatekaryawan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndeletekaryawan)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, 450));

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Karyawan", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(0, 255, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(51, 102, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("PEMILIHAN PROYEK");
        jPanel13.add(jLabel6);

        jPanel14.setBackground(new java.awt.Color(102, 255, 255));

        jLabel7.setText("ID PROYEK   :");

        jLabel8.setText("Nama proyek :");

        jLabel9.setText("Durasi pengerjaan :");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_idproyek, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_durasipengerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_namaproyek, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tf_idproyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tf_namaproyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_durasipengerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(153, 255, 255));

        tabel_proyek.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabel_proyek);

        btnsimpanproyek.setText("SIMPAN");
        btnsimpanproyek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanproyekActionPerformed(evt);
            }
        });

        btnupdateproyek.setText("UPDATE");
        btnupdateproyek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateproyekActionPerformed(evt);
            }
        });

        btndeleteproyek.setText("DELETE");
        btndeleteproyek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteproyekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsimpanproyek)
                    .addComponent(btnupdateproyek)
                    .addComponent(btndeleteproyek))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnsimpanproyek)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnupdateproyek)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndeleteproyek)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, 450));

        jPanel2.add(jPanel11, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Proyek", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel16.setBackground(new java.awt.Color(0, 204, 204));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(153, 204, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("TRANSAKSI PROYEK");
        jPanel18.add(jLabel10);

        jPanel19.setBackground(new java.awt.Color(204, 255, 255));

        combokaryawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Daftar Karyawan :");

        jLabel12.setText("Daftar Proyek :");

        comboproyek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Peran:");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(16, 16, 16)
                .addComponent(combokaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboproyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_peran, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_peran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combokaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)
                        .addComponent(comboproyek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(204, 255, 255));

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
        ));
        jScrollPane3.setViewportView(tabel_transaksi);

        jButton7.setText("SIMPAN");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("UPDATE");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("DELETE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jButton7)
                    .addComponent(jButton9))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addGap(0, 208, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, 450));

        jPanel3.add(jPanel16, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Transaksi", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tf_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_karyawanActionPerformed

    private void btnsimpankarywanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpankarywanActionPerformed
        // TODO add your handling code here:
        saveDatakaryawan();
    }//GEN-LAST:event_btnsimpankarywanActionPerformed

    private void btnupdatekaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdatekaryawanActionPerformed
        // TODO add your handling code here:
         updateDatakaryawan(); 
    }//GEN-LAST:event_btnupdatekaryawanActionPerformed

    private void btndeletekaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletekaryawanActionPerformed
        // TODO add your handling code here:
        deleteDatakaryawan();
    }//GEN-LAST:event_btndeletekaryawanActionPerformed

    private void btnsimpanproyekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanproyekActionPerformed
        // TODO add your handling code here:
        saveDataproyek();
    }//GEN-LAST:event_btnsimpanproyekActionPerformed

    private void btnupdateproyekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateproyekActionPerformed
        // TODO add your handling code here:
        updateDataproyek();
    }//GEN-LAST:event_btnupdateproyekActionPerformed

    private void btndeleteproyekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteproyekActionPerformed
        // TODO add your handling code here:
        deleteDataproyek();
    }//GEN-LAST:event_btndeleteproyekActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        saveTransaksi();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        updateTransaksi();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        deleteTransaksi();
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(modul5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modul5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modul5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modul5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modul5().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeletekaryawan;
    private javax.swing.JButton btndeleteproyek;
    private javax.swing.JButton btnsimpankarywan;
    private javax.swing.JButton btnsimpanproyek;
    private javax.swing.JButton btnupdatekaryawan;
    private javax.swing.JButton btnupdateproyek;
    private javax.swing.JComboBox<String> combokaryawan;
    private javax.swing.JComboBox<String> comboproyek;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabel_karyawan;
    private javax.swing.JTable tabel_proyek;
    private javax.swing.JTable tabel_transaksi;
    private javax.swing.JTextField tf_departemen;
    private javax.swing.JTextField tf_durasipengerjaan;
    private javax.swing.JTextField tf_idkaryawan;
    private javax.swing.JTextField tf_idproyek;
    private javax.swing.JTextField tf_jabatan;
    private javax.swing.JTextField tf_karyawan;
    private javax.swing.JTextField tf_namaproyek;
    private javax.swing.JTextField tf_peran;
    // End of variables declaration//GEN-END:variables
}
