package com.example.demo1.Controllers;


import com.example.demo1.database.Database;
import com.example.demo1.entities.Order;
import com.example.demo1.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private final Connection connection = Database.getConnection();

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS");
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id_order"),
                        rs.getInt("id_customer"),
                        rs.getDate("order_date"),
                        rs.getTime("order_time"),
                        rs.getInt("total_price"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return orders;
    }
    public boolean isProductInOrder(int orderId, int productId) throws SQLException {
        try (PreparedStatement selectOrderItemStatement = connection.prepareStatement(
                "SELECT * FROM order_item WHERE id_order = ? AND id_product = ?")) {
            selectOrderItemStatement.setInt(1, orderId);
            selectOrderItemStatement.setInt(2, productId);

            try (ResultSet selectOrderItemResult = selectOrderItemStatement.executeQuery()) {
                return selectOrderItemResult.next();
            }
        }
    }

    public List<Product> getProductsByOrderId(int orderId) throws SQLException {
        List<Product> products = new ArrayList<Product>();

        try (PreparedStatement selectProductsStatement = connection.prepareStatement(
                "SELECT p.* FROM products p " +
                        "JOIN order_item oi ON p.id_product = oi.id_product " +
                        "WHERE oi.id_order = ?")) {
            selectProductsStatement.setInt(1, orderId );

            try (ResultSet selectProductsResult = selectProductsStatement.executeQuery()) {
                while (selectProductsResult.next()) {
                    Product product = new Product(selectProductsResult.getInt("id_product"),
                            selectProductsResult.getString("name"),
                            selectProductsResult.getString("description"),
                            selectProductsResult.getInt("price"));
                    products.add(product);
                }
            }
        }

        return products;
    }

    public Order getById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ORDERS WHERE id_order = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id_order"),
                        rs.getInt("id_customer"),
                        rs.getDate("order_date"),
                        rs.getTime("order_time"),
                        rs.getInt("total_price"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public int create(Order order) {
        String query = "INSERT INTO ORDERS (id_customer, order_date, order_time, total_price, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getIdCustomer());
            stmt.setDate(2, new Date(order.getOrderDate().getTime()));
            stmt.setTime(3, order.getOrderTime());
            stmt.setInt(4, order.getTotalPrice());
            stmt.setString(5, order.getStatus());
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

    public void update(Order order) {
        String query = "UPDATE ORDERS SET id_customer = ?, order_date = ?, order_time = ?, total_price = ?, status = ? " +
                "WHERE id_order = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getIdCustomer());
            stmt.setDate(2, new Date(order.getOrderDate().getTime()));
            stmt.setTime(3, order.getOrderTime());
            stmt.setInt(4, order.getTotalPrice());
            stmt.setString(5, order.getStatus());
            stmt.setInt(6, order.getIdOrder());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public Order delete(int id) {
        String query = "DELETE FROM ORDERS WHERE id_order = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
}
