package com.example.demo1.Controllers;

import com.example.demo1.database.Database;
import com.example.demo1.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private final Connection connection=Database.getConnection();

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id_product"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return products;
    }

    public Product getById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE id_product = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id_product"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getString("category")
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public int create(Product product) {
        String query = "INSERT INTO PRODUCTS (name, description, price, stock, category) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setString(5, product.getCategory());
            System.out.println(stmt.toString());
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

    public void update(Product product) {
        String query = "UPDATE PRODUCTS SET name = ?, description = ?, price = ?, stock = ?, category = ?" +
                "WHERE id_product = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setString(5, product.getCategory());
            stmt.setInt(7, product.getIdProduct());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public Product delete(int id) {
        String query = "DELETE FROM PRODUCTS WHERE id_product = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
}
