import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/perpustakaan";
    private final String USER = "root";
    private final String PASS = "";

    Connection conn;
    Statement stmt;
    ResultSet rs;

    public Database() {
        try {
            // register driver yang akan digunakan
            Class.forName(JDBC_DRIVER);
            
            // menghubungkan ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

