package com.example.demo1.Controllers;



import com.example.demo1.database.Database;
import com.example.demo1.entities.Wishlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistController {
    private final Connection connection=Database.getConnection();

    public List<Wishlist> getAll() {
        List<Wishlist> wishlists = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM wishlist");
            while (rs.next()) {
                wishlists.add(new Wishlist(
                        rs.getInt("id_wishlist"),
                        rs.getInt("id_customer"),
                        rs.getInt("id_product")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return wishlists;
    }

    public Wishlist getById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM wishlist WHERE id_wishlist = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Wishlist(
                        rs.getInt("id_wishlist"),
                        rs.getInt("id_customer"),
                        rs.getInt("id_product")
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public int create(Wishlist wishlist) {
        String query = "INSERT INTO wishlist (id_customer, id_product) " +
                "VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, wishlist.getIdCustomer());
            stmt.setInt(2, wishlist.getIdProduct());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return -1;
    }

    public void update(Wishlist wishlist) {
        String query = "UPDATE wishlist SET id_customer = ?, id_product = ? " +
                "WHERE id_wishlist = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, wishlist.getIdCustomer());
            stmt.setInt(2, wishlist.getIdProduct());
            stmt.setInt(3, wishlist.getIdWishlist());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public Wishlist delete(int id) {
        String query = "DELETE FROM wishlist WHERE id_wishlist = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
}