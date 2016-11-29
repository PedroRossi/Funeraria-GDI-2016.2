package factories;

import models.Telefone;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public class Database {
    private static final Database ourInstance = new Database();
    private Connection connection;

    private Database() {
        try {
            connection = ConnectionFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (ourInstance.connection == null || !ourInstance.connection.isValid(4))
                ourInstance.connection = ConnectionFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ourInstance.connection;
    }
}

