package com.example.demo1.Controllers;


import com.example.demo1.database.Database;
import com.example.demo1.entities.Order;
import com.example.demo1.entities.OrderItem;
import com.example.demo1.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemController {
    private final Connection connection=Database.getConnection();

    public List<OrderItem> getAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDER_ITEM");
            while (rs.next()) {
                orderItems.add(new OrderItem(
                        rs.getInt("id_order_item"),
                        rs.getInt("id_order"),
                        rs.getInt("id_product"),
                        rs.getInt("product_quantity"),
                        rs.getInt("product_price")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return orderItems;
    }

    public OrderItem getById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ORDER_ITEM WHERE id_order_item = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new OrderItem(
                        rs.getInt("id_order_item"),
                        rs.getInt("id_order"),
                        rs.getInt("id_product"),
                        rs.getInt("product_quantity"),
                        rs.getInt("product_price")
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public int create(OrderItem orderItem) {
        String query = "INSERT INTO ORDER_ITEM (id_order, id_product, product_quantity, product_price) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderItem.getIdOrder());
            stmt.setInt(2, orderItem.getIdProduct());
            stmt.setInt(3, orderItem.getProductQuantity());
            stmt.setDouble(4, orderItem.getProductPrice());
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

    public void update(OrderItem orderItem) {
        String query = "UPDATE ORDER_ITEM SET id_order = ?, id_product = ?, product_quantity = ?, product_price = ? " +
                "WHERE id_order_item = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderItem.getIdOrder());
            stmt.setInt(2, orderItem.getIdProduct());
            stmt.setInt(3, orderItem.getProductQuantity());
            stmt.setDouble(4, orderItem.getProductPrice());
            stmt.setInt(5, orderItem.getIdOrderItem());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private static final String INSERT_ORDER_ITEM_SQL = "INSERT INTO order_item (id_order, id_product, product_quantity, product_price) VALUES (?, ?, ?, ?)";

    public void addProductToOrder(Order order, Product product) {
        try (PreparedStatement insertOrderItemStatement = connection.prepareStatement(INSERT_ORDER_ITEM_SQL)) {
            insertOrderItemStatement.setInt(1, order.getIdOrder());
            insertOrderItemStatement.setInt(2, product.getIdProduct());
            insertOrderItemStatement.setInt(3, 1); // assuming we are adding only one product to the order
            insertOrderItemStatement.setDouble(4, product.getPrice());
            insertOrderItemStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding product to order: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM ORDER_ITEM WHERE id_order_item = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

