package com.example.demo1;

import com.example.demo1.Controllers.*;
import com.example.demo1.database.Database;
import com.example.demo1.entities.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

public class LoginApp extends Application {

    private final UserController userController = new UserController();
    private final ProductController productController = new ProductController();
    private TextField emailTextField;
    private PasswordField passwordTextField;
    private CheckBox adminCheckBox;
    private Customer customer = null;
    private Admin admin = null;
    private final Connection connection=Database.getConnection();
    private Stage primaryStage;
    private Scene mainScene;

    private AdminController adminController;
    private CustomerController customerController;
    private final OrderController orderController = new OrderController();

    public LoginApp() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adminController = new AdminController();
        customerController = new CustomerController();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login App");

        // Create UI components
        Label emailLabel = new Label("Email:");
        emailTextField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordTextField = new PasswordField();

        adminCheckBox = new CheckBox("Login as admin");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> handleLogin());

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(event -> handleCreateAccount());

        HBox buttonPane = new HBox(10);
        buttonPane.getChildren().addAll(loginButton, createAccountButton);

        // Create form layout
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.setPadding(new Insets(20));

        formPane.add(emailLabel, 0, 0);
        formPane.add(emailTextField, 1, 0);
        formPane.add(passwordLabel, 0, 1);
        formPane.add(passwordTextField, 1, 1);

        formPane.add(adminCheckBox, 1, 2);

        formPane.add(buttonPane, 1, 3);

        Scene scene = new Scene(formPane, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleLogin() {
        // Retrieve email and password
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        // Retrieve user from the database based on the type of user
        boolean isAdmin = adminCheckBox.isSelected();
        if (isAdmin) {
            Admin admin = userController.getAdminByEmail(email);
            if (admin == null) {
                showAlert("Error", "Invalid email or password.");
                return;
            }
            if (!admin.getPassword().equals(password)) {
                showAlert("Error", "Invalid email or password.");
                return;
            }
            showAlert("Success", "Logged in successfully as admin " + admin.getEmail());

        } else {
            Customer customer = userController.getCustomerByEmail(email);
            if (customer == null) {
                showAlert("Error", "Invalid email or password.");
                return;
            }

            if (!customer.getPassword().equals(password)) {
                showAlert("Error", "Invalid email or password.");
                return;
            }

            showAlert("Success", "Logged in successfully as customer " + customer.getEmail());
            this.customer = customer;
            if (customer != null) {
                createNewOrder(customer);
            }
            // Show main application window
            primaryStage.hide();
            GridPane mainPane = new GridPane();
            mainPane.setHgap(10);
            mainPane.setVgap(10);
            mainPane.setPadding(new Insets(20));

            // Add buttons for viewing wish list, products, etc.
            Button viewWishListButton
                    = new Button("View Wish List");

            viewWishListButton.setOnAction(event -> handleViewWishList(customer));
            Button viewProductsButton = new Button("View Products");
            viewProductsButton.setOnAction(event -> handleViewProducts());
            for (Product product : productController.getAll()) {
                Label nameLabel = new Label(product.getName());
                Button addProductButton = new Button("Add Product to Wish List");
                addProductButton.setOnAction(event -> handleAddProductToWishList(customer, product));
            }

            Button addProductButton = new Button("Add Product to Wish List");

            Button viewOrdersButton = new Button("View Orders");
            viewOrdersButton.setOnAction(event -> handleViewOrder(customer));

            // Add buttons to main application pane
            mainPane.add(viewWishListButton, 0, 0);
            mainPane.add(viewProductsButton, 0, 1);
            mainPane.add(addProductButton, 0, 2);
            mainPane.add(viewOrdersButton, 0, 3);

            // Create main application scene and show it
            mainScene = new Scene(mainPane, 400, 200);
            primaryStage.setScene(mainScene);
            primaryStage.show();
        }
    }

    private Scene currentScene;

    private void createNewOrder(Customer customer) {
        try {
            // Create new order for customer
            LocalDateTime dateTime = LocalDateTime.now();
            Time time = Time.valueOf(dateTime.toLocalTime());
            Date date = Date.valueOf(dateTime.toLocalDate());
            Order order = new Order(customer.getIdCustomer(), date);
            order.setOrderTime(time);

            // Calculate total price of the order
            double totalPrice = 0.0;

            // Add order to database
            try (PreparedStatement insertOrderStatement = connection.prepareStatement(
                    "INSERT INTO orders (id_customer, order_date, order_time, total_price, status) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                insertOrderStatement.setInt(1, customer.getIdCustomer());
                insertOrderStatement.setDate(2, order.getOrderDate());
                insertOrderStatement.setTime(3, order.getOrderTime());
                insertOrderStatement.setDouble(4, order.getTotalPrice());
                insertOrderStatement.setString(5, "Pending");
                insertOrderStatement.executeUpdate();

                // Retrieve id of newly created order
                try (ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setIdOrder(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
            }

            customerController.addOrder(customer, order);
            showAlert("Success", "New order #" + order.getIdOrder() + " created.");
        } catch (SQLException e) {
            showAlert("Error", "Error creating new order: " + e.getMessage());
        }
    }

    private void handleViewWishList(Customer customer) {

        List<Product> wishList = customerController.getWishList(customer);
        if (wishList.isEmpty()) {
            showAlert("Empty wish list", "Your wish list is empty!");
        } else {
            // Create wish list view
            GridPane wishListPane = new GridPane();
            wishListPane.setHgap(10);
            wishListPane.setVgap(10);
            wishListPane.setPadding(new Insets(20));

            // Add back button to wish list pane
            Button backButton = new Button("Back");
            backButton.setOnAction(event -> returnToMainScene());
            wishListPane.add(backButton, 1, 0);

            Label wishlistLabel = new Label("Wish List:");
            Label noOfItems = new Label(String.valueOf(wishList.size()));
            HBox wishlistTitle = new HBox(10, wishlistLabel, noOfItems);
            wishListPane.add(wishlistTitle, 0, 1);

            int row = 2;
            for (Product product : wishList) {
                Label nameLabel = new Label(product.getName());
                wishListPane.add(nameLabel, 0, row);

                Button removeButton = new Button("Remove");
                removeButton.setOnAction(event -> handleRemoveFromWishList(customer, product));
                wishListPane.add(removeButton, 1, row);
                row++;
            }

            // Store current scene
            currentScene = primaryStage.getScene();

            // Create wish list scene
            Scene wishListScene = new Scene(wishListPane, 400, 400);

            primaryStage.setScene(wishListScene);
            primaryStage.setTitle("Wish List");
        }
    }


    private void returnToMainScene() {
        if (mainScene != null) {
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Login App");
        }
    }

    private void handleRemoveFromWishList(Customer customer, Product product) {

        // Remove product from wish list
        customerController.removeFromWishList(customer, product);

        // Refresh wish list scene
        handleViewWishList(customer);

        // Return to main scene
        returnToMainScene();
    }

    private void handleViewProducts() {
        // Retrieve all products
        List<Product> products = productController.getAll();

        // Create product view
        GridPane productsPane = new GridPane();
        productsPane.setHgap(10);
        productsPane.setVgap(10);
        productsPane.setPadding(new Insets(20));

        productsPane.add(new Label("Products:"), 0, 0);

        int row = 1, row2 = 1;
        for (Product product : products) {
            Label nameLabel = new Label(product.getName());
            productsPane.add(nameLabel, 0, row);
            // Create a button for adding to wish list
            Button addButton = new Button("Add to Wish List");
            addButton.setOnAction(event -> handleAddProductToWishList(customer, product)); // Pass both customer and product
            productsPane.add(addButton, 1, row);
            row++;
        }
        for (Product product : products) {
            Button addButton2 = new Button("Add to Order");
            addButton2.setOnAction(event -> {
                try {
                    handleAddProductToOrder(customerController.getLatestOrder(customer).getIdOrder(), product.getIdProduct());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }); // Pass both customer and product
            productsPane.add(addButton2, 2, row2);
            row2++;
        }
        // Add back button to products pane
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> returnToMainScene());
        productsPane.add(backButton, 1, row);

        // Create a scroll pane to wrap the product view
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productsPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);// Add scroll pane to scene
        Scene productsScene = new Scene(scrollPane);

        // Store current scene
        currentScene = primaryStage.getScene();

        primaryStage.setScene(productsScene);
        primaryStage.show();
    }

    public void handleAddProductToOrder(int orderId, int productId) {
        try {
            addProductToOrder(orderId, productId);
            System.out.println("Produsul a fost adăugat cu succes la comanda cu id-ul " + orderId);
        } catch (SQLException e) {
            System.err.println("Eroare la adăugarea produsului la comanda cu id-ul " + orderId + ": " + e.getMessage());
        }
    }

    public void addProductToOrder(int orderId, int productId) throws SQLException {
        if (orderController.isProductInOrder(orderId, productId)) {
            try (PreparedStatement updateOrderItemStatement = connection.prepareStatement(
                    "UPDATE order_item SET productquantity = productquantity + 1 WHERE id_order = ? AND id_product = ?")) {
                updateOrderItemStatement.setInt(1, orderId);
                updateOrderItemStatement.setInt(2, productId);

                updateOrderItemStatement.executeUpdate();
            }
        } else {
            try (PreparedStatement insertOrderItemStatement = connection.prepareStatement(
                    "INSERT INTO order_item (id_order, id_product, productprice, productquantity) VALUES (?, ?,?, 1)")) {
                insertOrderItemStatement.setInt(1, orderId);
                insertOrderItemStatement.setInt(2, productId);
                insertOrderItemStatement.setInt(3, getProductPriceById(productId));
                insertOrderItemStatement.executeUpdate();
            }
        }
    }

    public int getProductQuantityById(int orderId, int productId) throws SQLException {
        try (PreparedStatement selectOrderItemStatement = connection.prepareStatement(
                "SELECT productquantity FROM order_item WHERE id_order = ? AND id_product = ?")) {
            selectOrderItemStatement.setInt(1, orderId);
            selectOrderItemStatement.setInt(2, productId);

            try (ResultSet selectOrderItemResult = selectOrderItemStatement.executeQuery()) {
                if (selectOrderItemResult.next()) {
                    return selectOrderItemResult.getInt("productquantity");
                } else {
                    return 0; // Produsul nu a fost găsit în comanda dată
                }
            }
        }
    }

    public int getProductPriceById(int productId) throws SQLException {
        try (PreparedStatement selectProductStatement = connection.prepareStatement(
                "SELECT price FROM products WHERE id_product = ?")) {
            selectProductStatement.setInt(1, productId);

            try (ResultSet selectProductResult = selectProductStatement.executeQuery()) {
                if (selectProductResult.next()) {
                    return selectProductResult.getInt("price");
                } else {
                    throw new SQLException("Nu s-a găsit nicio înregistrare pentru produsul cu id-ul " + productId);
                }
            }
        }
    }

    private void handleAddProductToWishList(Customer customer, Product product) {
        if (customer == null) {
            showAlert("Error", "Please log in to add products to your wish list.");
            return;
        }

        List<Product> wishList = customerController.getWishList(customer);
        if (wishList.stream().filter(p -> p.equals(product)).findAny().isPresent()) {
            showAlert("Error", "Product is already in the wish list.");
            return;
        }


        // Add product to wishlist
        customerController.addProductToWishList(customer, product);
        customerController.update(customer);

        showAlert("Success", "Product \"" + product.getName() + "\" added to your wish list.");
    }


    private void handleViewOrder(Customer customer) {
        double totalPrice = 0;
        Order order = null;
        try {
            order = customerController.getLatestOrder(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (order == null) {
            showAlert("Empty orders", "You have no unsent orders!");
        } else {
            // Create orders view
            GridPane ordersPane = new GridPane();
            ordersPane.setHgap(10);
            ordersPane.setVgap(10);
            ordersPane.setPadding(new Insets(20));    // Add back button to orders pane
            Button backButton = new Button("Back");
            backButton.setOnAction(event -> returnToMainScene());
            ordersPane.add(backButton, 0, 0);
            int row = 1;
            Label orderLabel = new Label("Order #" + order.getIdOrder());
            ordersPane.add(orderLabel, 1, 1);

            try {
                List<Product> productList = orderController.getProductsByOrderId(order.getIdOrder());
                for (int i = 0; i < productList.size(); i++) {
                    Product product = productList.get(i);
                    Label productLabel = new Label(product.getName() + " - " + product.getDescription() + " - "
                            + product.getPrice() + " RON");
                    ordersPane.add(productLabel, 1, row + 2 + i);

                    Label quantityLabel = new Label("Quantity: " + getProductQuantityById(order.getIdOrder(), product.getIdProduct()));
                    ordersPane.add(quantityLabel, 2, row + 2 + i);
                }
                totalPrice = calculateOrderTotalPrice(order.getIdOrder());
                Label totalPriceLabel = new Label("Total price: " + totalPrice + " RON");
                ordersPane.add(totalPriceLabel, 0, row + 2);
            } catch (SQLException e) {
                System.err.println("Eroare la extragerea produselor pentru comanda cu id-ul " + order.getIdOrder() + ": " + e.getMessage());
            }


            row = row + 2;

            // Store current scene
            currentScene = primaryStage.getScene();

            // Create orders scene
            Scene ordersScene = new Scene(ordersPane, 400, 400);
            primaryStage.setScene(ordersScene);
            primaryStage.setTitle("Unsent Orders");
            primaryStage.show();
        }
    }

    public double calculateOrderTotalPrice(int orderId) throws SQLException {
        String query = "SELECT calculateOrderTotalPrice(?) AS total_price;";
        Connection connection = Database.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("total_price");
            } else {
                throw new SQLException("Error calculating total price for order with id: " + orderId);
            }
        }
    }


    private void handleCreateAccount() {
        boolean isAdmin = adminCheckBox.isSelected();
        if (isAdmin) {
            CreateAdminAccountWindow createAdminAccountWindow = new CreateAdminAccountWindow(adminController);
            createAdminAccountWindow.showAndWait();
            // If account was successfully created, show success message
            if (createAdminAccountWindow.isAccountCreated()) {
                showAlert("Success", "Admin account created successfully for " + createAdminAccountWindow.getCreatedAccountEmail());
            }
        } else {
            // Open create customer account window
            CreateCustomerAccountWindow createCustomerAccountWindow = new CreateCustomerAccountWindow(customerController);
            createCustomerAccountWindow.showAndWait();

            // If account was successfully created, show success message
            if (createCustomerAccountWindow.isAccountCreated()) {
                showAlert("Success", "Customer account created successfully for " + createCustomerAccountWindow.getCreatedAccountEmail());
            }
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
