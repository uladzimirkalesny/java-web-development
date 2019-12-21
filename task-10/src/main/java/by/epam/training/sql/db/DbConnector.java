package by.epam.training.sql.db;

import by.epam.training.sql.exception.ConnectionToDbException;
import by.epam.training.sql.exception.MysqlDriverNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {
    private static volatile DbConnector instance;
    private final String DB_CONFIG = "config/db.properties";

    private String DB_DRIVER;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;


    private DbConnector() {
        init();
    }

    public static DbConnector getInstance() {
        if (instance == null) {
            synchronized (DbConnector.class) {
                if (instance == null) {
                    instance = new DbConnector();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new MysqlDriverNotFoundException("Not found driver for connection to database");
        }

        Connection connection;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionToDbException(
                    "Database access error occurs or the url is null. Connection don't get. " + e.getMessage());
        }

        return connection;
    }

    private void init() {
        Properties properties = new Properties();
        try {
            properties.load(DbConnector.class.getClassLoader().getResourceAsStream(DB_CONFIG));

            DB_DRIVER = properties.getProperty("DB_DRIVER");
            DB_URL = properties.getProperty("DB_URL");
            DB_USER = properties.getProperty("DB_USER");
            DB_PASSWORD = properties.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException("File with configuration database not found " + DB_CONFIG);
        }
    }
}
