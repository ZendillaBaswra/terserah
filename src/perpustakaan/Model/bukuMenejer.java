/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.Model;

import config.CheckConnection;
import config.ConnectionHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import perpustakaan.Model.bukuMenejer;
import perpustakaan.ui.JFrame;
        
        


/**
 *
 * @author zendilla
 */

public class bukuMenejer {
     public static List<Buku> ShowAllbook(){
        List<Buku> bukuList = new ArrayList<>();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from buku");
            while (rs.next()){
                Buku buku = new Buku();
                buku.setId_buku(Integer.parseInt(rs.getString("id_buku")));
                buku.setJudul_buku(rs.getString("judul_buku"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setTahun_terbit(Integer.parseInt(rs.getString("tahun_terbit")));
                
                bukuList.add(buku);
            }
        } catch (SQLException e) {
             Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE,null,e);
        }
        return bukuList;
    }
      public static void updateBook (Buku buku)throws SQLException{
         Connection conn = ConnectionHelper.getConnection();
         PreparedStatement ptam = conn.prepareStatement("UPDATE buku SET judul_buku = ?,"
                 + "pengarang = ?, penerbit = ?, tahun_terbit = ? WHERE id_buku= ? ");
         ptam.setString(1, buku.getJudul_buku());
         ptam.setString(2, buku.getPengarang());
         ptam.setString(3, buku.getPenerbit());
         ptam.setInt(4, buku.getTahun_terbit());
         ptam.setInt(5, buku.getId_buku());
        int option = ptam.executeUpdate();
         if (option > 0) {
             JOptionPane.showMessageDialog(null, "data berhasil di ubah");
             JFrame.loadBook();
             
         }else{
             JOptionPane.showMessageDialog(null, "data gagal diubah");
         }
                 
     }
     public static Buku showbBuku(int id)throws SQLException{
         Connection conn = ConnectionHelper.getConnection();
         Buku buku = new Buku();
         
         PreparedStatement patm = conn.prepareStatement("Select * from buku where id_buku = ?");
                patm.setInt(1, id);
                ResultSet rs = patm.executeQuery();
                
                while (rs.next()){
            buku.setId_buku(Integer.parseInt(rs.getString("id_buku")));
            buku.setJudul_buku(rs.getString("judul_buku"));
            buku.setPenerbit(rs.getString("penerbit"));
            buku.setPengarang(rs.getString("pengarang"));
            buku.setTahun_terbit(Integer.valueOf(rs.getString("tahun_terbit")));
                }
                return buku;
     }
    
     public static boolean inserBook (Buku buku) throws SQLException{
         PreparedStatement insertBook = null;
         try {
             Connection conn = ConnectionHelper.getConnection();
             insertBook = conn.prepareStatement("INSERT INTO buku(judul_buku,pengarang,penerbit,tahun_terbit)"+
                  "values (?,?,?,?)");
        insertBook.setString(1, buku.getJudul_buku());
        insertBook.setString(2, buku.getPengarang());
        insertBook.setString(3, buku.getPenerbit());
        insertBook.setInt(4,buku.getTahun_terbit());
        int i =insertBook.executeUpdate();
             if (i>0) {
                 JOptionPane.showMessageDialog(null,"data berhasil disimpan");
                 JFrame.loadBook();
                 return true;
             }else{
                 JOptionPane.showMessageDialog(null, "data tidak tersimpan");
                 return false;
             }
         } catch (Exception e) {
             System.out.println(e);
         }finally{
             insertBook.close();
         }
         return true;
     }
        
    
     }
