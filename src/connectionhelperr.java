import com.mysql.jdbc.Driver;
import config.ConnectionHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author kumala citra
 */
public class connectionhelperr {
    private static final String Db_NAME = "perpustakaan";//nama database
private static final String USER = "root";
private static final String PASSWORD = "";
private static final String URL = "jdbc:mysql://localhost:3306/"+Db_NAME;
    public static Connection getConnection() throws SQLException{
        DriverManager.registerDriver(new Driver());//mengambil driver koneksi 
        Connection connection =DriverManager.getConnection(URL, USER, PASSWORD);
    return connection;
    }
}
