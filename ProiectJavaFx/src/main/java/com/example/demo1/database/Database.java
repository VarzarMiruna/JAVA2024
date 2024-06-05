package com.example.demo1.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/SGBD";
    private static final String USER = "postgres";
    private static final String PASSWORD = "miruna";
    private static Connection connection = null;

    public Database() {
    }

    /**
     * This method connects the application to the database if it does not already exist
     *
     * @return the connection as an object.
     */
    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    /**
     * The creation of the connection. It loads the driver class and then connects to the database using
     * the URL, username and password. setAutoCommit(false) ensures that no changes are committed without
     * using the commit() method.
     */
    public static void createConnection() {
        try {
            //loading the driver class
            Class.forName("org.postgresql.Driver");
            //creating the connection object
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Failed to create database connection: " + e.getMessage());
        }
    }

    /**
     * Closes the connection.
     */
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection: " + e.getMessage());
        }
    }

    /**
     * Commits all changes made prior to the call.
     */
    public static void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Failed to commit database transaction: " + e.getMessage());
        }
    }

    /**
     * Rolls back all changes.
     */
    public static void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.err.println("Failed to rollback database transaction: " + e.getMessage());
        }
    }
}
