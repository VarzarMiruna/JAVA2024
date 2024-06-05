package com.example.demo1;

import com.example.demo1.Controllers.ProductController;
import com.example.demo1.database.Database;
import com.example.demo1.entities.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductApp extends Application {

    private final ProductController productController = new ProductController();
    private final ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Product Management");
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Create header
        Label titleLabel = new Label("Product Management");
        titleLabel.getStyleClass().add("title");

        Button addButton = new Button("Add Product");
        addButton.setOnAction(event -> showAddProductDialog());
        addButton.getStyleClass().add("add-button");

        Button findByIdButton = new Button("Find By Id");
        findByIdButton.setOnAction(event -> showFindByIdProductDialog());
        findByIdButton.getStyleClass().add("findById-button");

       /* Button findByNameButton = new Button("Find By Name");
        findByNameButton.setOnAction(event -> showFindByNameProductDialog());
        findByNameButton.getStyleClass().add("findByName-button");*/

        Button deleteButton = new Button("Delete By Id");
        deleteButton.setOnAction(event -> deleteProductDialog());
        deleteButton.getStyleClass().add("delete-button");

        HBox buttonContainer = new HBox(10, addButton, findByIdButton, deleteButton);

        BorderPane headerPane = new BorderPane();
        headerPane.setLeft(titleLabel);
        headerPane.setRight(buttonContainer);
        headerPane.setPadding(new Insets(10, 20, 10, 20));
        headerPane.getStyleClass().add("header");

        // Create Product list view
        ListView<Product> productListView = new ListView<>();
        productListView.setPlaceholder(new Label("No products found."));
        productListView.getStyleClass().add("product-list");
        productListView.setItems(productObservableList);

        // Load products into the list
        loadProducts();

        // Create main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(headerPane);
        mainPane.setCenter(productListView);
        mainPane.getStyleClass().add("main");

        // Create scene
        Scene scene = new Scene(mainPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Set scene on stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadProducts() {
        List<Product> products = productController.getAll();
        productObservableList.addAll(products);
    }

    private void showAddProductDialog() {
        // Create dialog
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Add Product");

        // Create input field
        Label nameLabel = new Label("Name:");
        Label descriptionLabel = new Label("Description:");
        Label priceLabel = new Label("Price:");
        Label stockLabel = new Label("Stock:");
        Label categoryLabel = new Label("Category:");

        TextField nameTextField = new TextField();
        TextField descriptionTextField = new TextField();
        TextField priceTextField = new TextField();
        TextField stockTextField = new TextField();
        TextField categoryTextField = new TextField();

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, nameLabel, nameTextField);
        inputGrid.addRow(1, descriptionLabel, descriptionTextField);
        inputGrid.addRow(2, priceLabel, priceTextField);
        inputGrid.addRow(3, stockLabel, stockTextField);
        inputGrid.addRow(4, categoryLabel, categoryTextField);

        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                Product product = new Product();
                product.setName(nameTextField.getText());
                product.setDescription(descriptionTextField.getText());
                product.setPrice(Integer.parseInt(priceTextField.getText()));
                product.setStock(Integer.parseInt(stockTextField.getText()));
                product.setCategory(categoryTextField.getText());
                productController.create(product);
                productObservableList.add(product);
                return product;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showFindByIdProductDialog() {
        // Create dialog
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Find By Id");

        // Create input fields
        Label idLabel = new Label("ID: ");
        TextField idTextField = new TextField();
        //idTextField.setPromptText("Enter Product ID: ");

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, idLabel, idTextField);

        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType findButton = new ButtonType("Find", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(findButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == findButton) {
                int id = Integer.parseInt(idTextField.getText());
                Product product = productController.getById(id);
                if (product != null) {
                    Dialog<Product> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("FindById details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Id:"+ product.getIdProduct() +
                            "\nName: " + product.getName() +
                            "\nDescription: " + product.getDescription() +
                            "\nPrice: " + product.getPrice() +
                            "\nStock: " + product.getStock() +
                            "\nCategory: " + product.getCategory());
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return product;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }

    /*private void showFindByNameProductDialog() {
        // Create dialog
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Find By Name");

        // Create input fields
        Label nameLabel = new Label("NAME: ");
        TextField nameTextField = new TextField();
        //idTextField.setPromptText("Enter Product NAME: ");

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, nameLabel, nameTextField);

        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType findByNameButton = new ButtonType("FindByName", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(findByNameButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == findByNameButton) {
                String name = nameTextField.getText();
                Product product = productController.getByName(name);
                if (product != null) {
                    Dialog<Product> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("FindByName details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Id:"+ product.getIdProduct() +
                            "\nName: " + product.getName() +
                            "\nDescription: " + product.getDescription() +
                            "\nPrice: " + product.getPrice() +
                            "\nStock: " + product.getStock() +
                            "\nCategory: " + product.getCategory());
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();
                    return product;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }*/

    private void deleteProductDialog() {
        // Create dialog
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Delete By Id");

        // Create input fields
        Label idLabel = new Label("ID: ");
        TextField idTextField = new TextField();
        //idTextField.setPromptText("Delete Product with ID: ");

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, idLabel, idTextField);

        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType deleteButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButton) {
                int id = Integer.parseInt(idTextField.getText());
                Product product = productController.delete(id);
                if (product != null) {
                    Dialog<Product> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("Delete details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Product with id: " + id + "deleted!");
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return product;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }
}


