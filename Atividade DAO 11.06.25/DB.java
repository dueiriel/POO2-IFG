import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            String dbUrl = "jdbc:postgresql://localhost:5432/atividadedao";
            String user = "postgres";
            String password = "123";
            
            conn = DriverManager.getConnection(dbUrl, user, password);
        }
        return conn;
    }
}
