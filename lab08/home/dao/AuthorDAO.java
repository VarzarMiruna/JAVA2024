package home.dao;

import home.dao.AbstractDAO;
import home.model.Author;
import home.util.ConnectionDB;
import homework.util.Database;

import java.sql.*;

public class AuthorDAO extends AbstractDAO<Author> {
    @Override
    protected String getTableName() {
        return "authors";
    }

    @Override
    protected Author mapResultSetToModel(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getInt("id"), resultSet.getString("name"));
    }

    @Override
    protected String createFieldInsertList() {
        return "name";
    }

    @Override
    protected String createPlaceholderList() {
        return "?";
    }

    @Override
    protected void mapModelToPreparedStatement(PreparedStatement preparedStatement, Author object) throws SQLException {
        preparedStatement.setString(1, object.getName());
    }

    public Integer findByName(String name) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT id FROM authors WHERE name='" + name + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }


    public void create(Author author) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO authors (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, author.getName());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    author.setId(resultSet.getInt(1));
                }
            }
        }
    }


}
