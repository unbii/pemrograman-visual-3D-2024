/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package m5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ASUS
 */
public class koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_karyawan";
    private static final String USER = "root";
    private static final String PASS = "";
    //Deklarasi Metode:
    public static Connection getConnection() {
        //membuat variable bertipe connection bernilai null
        Connection conn = null;
        try {
            //akan mencoba menghubungkan ke database menggunakan URL, username, dan password yang diberikan.
            //adi, conn menyimpan hasil dari upaya koneksi ke database. Jika koneksi berhasil, conn akan berisi objek koneksi yang dapat digunakan untuk berinteraksi dengan database. Jika gagal, conn tetap bernilai null.
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("terkoneksi");
        } catch (SQLException e) {
            System.out.println("error bang : " + e.getMessage());
        }
        return conn;
}
     public static void main(String[] args) {
        getConnection();  
    }
}
