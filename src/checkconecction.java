import config.ConnectionHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author kumala citra
 */
public class checkconecction {
    private static boolean isConnected() {
      try {
          ConnectionHelper.getConnection();
          System.out.println("databases connected");
          return true;
      } catch (SQLException e) {
          Logger.getLogger(checkconecction.class.getName()).log(Level.SEVERE,null,e);
          System.out.println("Databases unconnected");
          return false;
      }
}
  public static void tampildata(){
      try {
          Connection conn = ConnectionHelper.getConnection(); 
          Statement st = conn.createStatement(); 
          ResultSet rs = st.executeQuery("Select * from buku");
          while (rs.next()) {              
              System.out.println("ID buku :"+rs.getString("id_buku")+" Judul Buku :"+rs.getString("judul_buku")+" Pengarang :"
                      +rs.getString("pengarang")+" Tahun Terbit :"+rs.getString("tahun_terbit"));
              
          }
      } catch (SQLException e) {
          Logger.getLogger(checkconecction.class.getName()).log(Level.SEVERE,null,e);
      }
      
  }
    public static void main(String[] args) {
        isConnected();
        tampildata();
    }
}


