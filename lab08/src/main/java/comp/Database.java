package comp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() throws SQLException {
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/books bd", "postgres", "miruna");
            this.connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed: " + ex.getMessage());
            throw ex;
        }
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        } else if (instance.getConnection().isClosed()) {
            instance = new Database();
        }
        return instance;
    }
    public Connection getConnection() {

        return connection;
    }
}

