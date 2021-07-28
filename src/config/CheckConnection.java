/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

//import com.sun.istack.internal.logging.Logger;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author zendila
 */
public class CheckConnection {//untuk mengecek apakah kkoneksi berhasil
  private static boolean isConnected() {
      try {
          ConnectionHelper.getConnection();
          System.out.println("databases connected");
          return true;
      } catch (SQLException e) {
          Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE,null,e);//unutk menampilkan log eror program
          System.out.println("Databases unconnected");
          return false;
      }
}
  public static void tampildata(){
      try {
          Connection conn = ConnectionHelper.getConnection(); //memnaggil method koneksi
          Statement st = conn.createStatement(); // berfungsi untuk membaca bahsa SQL
          ResultSet rs = st.executeQuery("Select * from buku");//Result et untuk menampilkan data record tabel databases
          while (rs.next()) {              
              System.out.println("ID buku :"+rs.getString("id_buku")+" Judul Buku :"+rs.getString("judul_buku")+" Pengarang :"
                      +rs.getString("pengarang")+" Tahun Terbit :"+rs.getString("tahun_terbit"));
              
          }
      } catch (SQLException e) {
          Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE,null,e);
      }
      
  }
    public static void main(String[] args) {
        isConnected();
        tampildata();
    }
}
