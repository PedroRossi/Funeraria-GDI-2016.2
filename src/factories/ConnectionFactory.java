package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pedro on 28/11/16.
 */
public class ConnectionFactory {

    private static final String dbUrl = "jdbc:oracle:thin:@localhost:1521:";
    private static final String user = "system";
    private static final String password = "oracle";

    public static Connection createConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}