package comp;

import java.sql.*;

public class AuthorDAO {

    public int create(String name) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO authors (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {//cheia
                if (generatedKeys.next()) {
                    int authorId = generatedKeys.getInt(1);
                    return authorId;
                } else {
                    throw new SQLException("Failed!");
                }
            }
        }
    }

//update: nume+id
    public void update(int authorId, String newName) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("UPDATE Authors SET Name = ? WHERE AuthorID = ?")) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, authorId);
            pstmt.executeUpdate();
        }
    }

    //delete id
    public void delete(int authorId) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("DELETE FROM Authors WHERE AuthorID = ?")) {
            pstmt.setInt(1, authorId);
            pstmt.executeUpdate();
        }
    }


    public Author findByName(String name) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("SELECT AuthorID, Name FROM Authors WHERE Name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Author(rs.getInt("AuthorID"), rs.getString("Name"));
            }
        }
        return null;
    }

    public Author findById(int id) throws SQLException {
        Connection con = Database.getInstance().getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("SELECT AuthorID, Name FROM Authors WHERE AuthorID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Author(rs.getInt("AuthorID"), rs.getString("Name"));
            }
        }
        return null;
    }
}

