package com.example.demo1.Controllers;

import com.example.demo1.database.Database;
import com.example.demo1.entities.Customer;
import com.example.demo1.entities.Order;
import com.example.demo1.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private final OrderController orderController = new OrderController();
    private final Connection connection = Database.getConnection();

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMERS");
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id_customer"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("password"),
                        rs.getString("country"),
                        rs.getString("postal_code")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return customers;
    }

    public List<Product> getWishList(Customer customer) {
        try {
            if (connection.isClosed()) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "SELECT products.* FROM products, wishlist WHERE wishlist.id_customer=? AND wishlist.id_product=products.id_product";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customer.getIdCustomer());
            ResultSet resultSet = statement.executeQuery();

            List<Product> wishList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_product");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");
                String category = resultSet.getString("category");


                Product product = new Product(id, name, description, price, stock, category);
                wishList.add(product);
            }
            return wishList;
        } catch (SQLException e) {
            System.out.println("Error getting wish list: " + e.getMessage());
            return null;
        }
    }

    public Customer getById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE id_customer = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id_customer"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("password"),
                        rs.getString("country"),
                        rs.getString("postal_code")
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public int create(Customer customer) {
        String query = "INSERT INTO CUSTOMERS (name, email, address, password, country, postal_code) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getCountry());
            stmt.setString(6, customer.getPostalCode());
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

    public void update(Customer customer) {
        String query = "UPDATE CUSTOMERS SET name = ?, email = ?, address = ?, password = ?, country = ?, postal_code = ? " +
                "WHERE id_customer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getCountry());
            stmt.setString(6, customer.getPostalCode());
            stmt.setInt(7, customer.getIdCustomer());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public int getNextCustomerId() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id_customer) FROM customers");
            resultSet.next();
            return resultSet.getInt(1) + 1;
        }
    }

    public void removeFromWishList(Customer customer, Product product) {
        String sql = "DELETE FROM wishlist WHERE id_customer=? AND id_product=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customer.getIdCustomer());
            statement.setInt(2, product.getIdProduct());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errorre moving product from wish list: " + e.getMessage());
        }
    }

    public void addProductToWishList(Customer customer, Product product) {
        String sql = "INSERT INTO wishlist (id_customer, id_product) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customer.getIdCustomer());
            statement.setInt(2, product.getIdProduct());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding product to wishlist: " + e.getMessage());
        }
    }

    public Order getLatestOrder(Customer customer) throws SQLException {
        Order order = null;

        try (PreparedStatement selectOrderStatement = connection.prepareStatement(
                "SELECT * FROM orders WHERE id_customer = ? ORDER BY order_date DESC, id_order DESC LIMIT 1")) {
            selectOrderStatement.setInt(1, customer.getIdCustomer());
            try (ResultSet selectOrderResult = selectOrderStatement.executeQuery()) {
                if (selectOrderResult.next()) {
                    order = new Order(selectOrderResult.getInt("id_order"),
                            selectOrderResult.getInt("id_customer"),
                            selectOrderResult.getDate("order_date"),
                            selectOrderResult.getTime("order_time"),
                            selectOrderResult.getInt("total_price"),
                            selectOrderResult.getString("status"));
                }
            }
        }

        return order;
    }

    public void addOrder(Customer customer, Order order) throws SQLException {
        try (PreparedStatement insertOrderStatement =
                     connection.prepareStatement("INSERT INTO orders (id_customer, order_date, order_time, total_price, status) " +
                             "VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertOrderStatement.setInt(1, order.getIdCustomer());
            insertOrderStatement.setDate(2, order.getOrderDate());
            insertOrderStatement.setTime(3, order.getOrderTime());
            insertOrderStatement.setInt(4, order.getTotalPrice());
            insertOrderStatement.setString(5, order.getStatus());
            insertOrderStatement.executeUpdate();

            // Set the ID of the last inserted row in the Order object
            try (ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setIdOrder(generatedKeys.getInt(1));
                } else {
                    System.out.println("Aici e eroarea");
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        }
    }

    public void updateOrder(Customer customer, Order order) throws SQLException {
        try (PreparedStatement updateOrderStatement = connection.prepareStatement(
                "UPDATE orders SET id_customer = ?, order_date = ?, order_time = ?, total_price = ?, status = ? WHERE id_order = ?")) {
            updateOrderStatement.setInt(1, customer.getIdCustomer());
            updateOrderStatement.setDate(2, order.getOrderDate());
            updateOrderStatement.setTime(3, order.getOrderTime());
            updateOrderStatement.setInt(4, order.getTotalPrice());
            updateOrderStatement.setString(5, order.getStatus());
            updateOrderStatement.setInt(6, order.getIdOrder());
            updateOrderStatement.executeUpdate();
        }
    }



    public List<Order> getUnsentOrders(int customerId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> unsentOrders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM orders WHERE id_customer = ? AND status = 'Pending'";
            if (connection.isClosed()) {
                connection.setAutoCommit(true);
            }
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("id_order");
                Date orderDate = resultSet.getDate("order_date");
                String status = resultSet.getString("status");
                int totalPrice = resultSet.getInt("total_price");

                Order order = new Order(orderId, orderDate, totalPrice, status);
                unsentOrders.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting unsent orders: " + e.getMessage(), e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // log or print the exception
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // log or print the exception
                }
            }
        }

        return unsentOrders;
    }

    public Customer getByName(String name) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE name = ?")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id_customer"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("password"),
                        rs.getString("country"),
                        rs.getString("postal_code")
                );
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    public Customer delete(int id) {
        String query = "DELETE FROM CUSTOMERS WHERE id_customer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
}
