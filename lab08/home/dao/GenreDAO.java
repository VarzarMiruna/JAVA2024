package home.dao;

import home.model.Genre;
import home.util.ConnectionDB;

import java.sql.*;

public class GenreDAO extends AbstractDAO<Genre> {

    @Override
    protected String getTableName() {
        return "genres";
    }

    @Override
    protected Genre mapResultSetToModel(ResultSet resultSet) throws SQLException {
        return new Genre(resultSet.getInt("id"), resultSet.getString("name"));
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
    protected void mapModelToPreparedStatement(PreparedStatement preparedStatement, Genre object) throws SQLException {
        preparedStatement.setString(1, object.getName());
    }

    public Integer findByName(String name) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT id FROM genres WHERE name='" + name + "'")) {
            return resultSet.next() ? resultSet.getInt(1) : null;
        }
    }
}
