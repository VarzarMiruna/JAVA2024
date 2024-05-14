package home.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//singleton class
public class ConnectionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/home";
    private static final String USER = "postgres";
    private static final String PASSWORD = "miruna";
    private static Connection connection = null;

    private static final String POOL_NAME = "ConnectionPool";
    private static final int MAX_POOL_SIZE = 500;
    private static HikariDataSource dataSource;

    private ConnectionDB() {}

    public static Connection getConnection() {
        if (connection == null) {
            if(dataSource == null){
                initializeDataSource();
            }
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public static void initializeDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setPoolName(POOL_NAME);
        config.setMaximumPoolSize(MAX_POOL_SIZE);
        config.addDataSourceProperty("serverName","localhost");
        config.addDataSourceProperty("portNumber","5432");
        config.addDataSourceProperty("databaseName","database");
        config.setAutoCommit(false);

        dataSource = new HikariDataSource(config);
    }

    public static void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

}