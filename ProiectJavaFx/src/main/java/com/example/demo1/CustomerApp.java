package com.example.demo1;

import com.example.demo1.Controllers.CustomerController;
import com.example.demo1.database.Database;
import com.example.demo1.entities.Customer;
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

public class CustomerApp extends Application {

    private final CustomerController customerController = new CustomerController();
    private final ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("~ Customer ~");
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Create header
        Label titleLabel = new Label("~ Customer ~");
        titleLabel.getStyleClass().add("title");

        Button addButton = new Button("Add Customer");
        addButton.setOnAction(event -> showAddCustomerDialog());
        addButton.getStyleClass().add("add-button");

        Button findByIdButton = new Button("Find By Id");
        findByIdButton.setOnAction(event -> showFindByIdCustomerDialog());
        findByIdButton.getStyleClass().add("findById-button");

        Button findByNameButton = new Button("Find By Name");
        findByNameButton.setOnAction(event -> showFindByNameCustomerDialog());
        findByNameButton.getStyleClass().add("findByName-button");

        Button deleteButton = new Button("Delete By Id");
        deleteButton.setOnAction(event -> deleteCustomerDialog());
        deleteButton.getStyleClass().add("delete-button");

        HBox buttonContainer = new HBox(10, addButton, findByIdButton, findByNameButton, deleteButton);

        BorderPane headerPane = new BorderPane();
        headerPane.setLeft(titleLabel);
        headerPane.setRight(buttonContainer);
        headerPane.setPadding(new Insets(10, 20, 10, 20));
        headerPane.getStyleClass().add("header");

        // Create customer list view
        ListView<Customer> customerListView = new ListView<>();
        customerListView.setPlaceholder(new Label("No customers found."));
        customerListView.getStyleClass().add("customer-list");
        customerListView.setItems(customerObservableList);

        // Load customers into the list
        loadCustomers();

        // Create main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(headerPane);
        mainPane.setCenter(customerListView);
        mainPane.getStyleClass().add("main");

        // Create scene
        Scene scene = new Scene(mainPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Set scene on stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadCustomers() {
        List<Customer> customers = customerController.getAll();
        customerObservableList.addAll(customers);
    }

    private void showAddCustomerDialog() {
        // Create dialog
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Add Customer");

        // Create input fields
        Label nameLabel = new Label("Name:");
        Label emailLabel = new Label("Email:");
        Label addressLabel = new Label("Address:");
        Label passwordLabel = new Label("Password:");
        Label countryLabel = new Label("Country:");
        Label postalCodeLabel = new Label("Postal Code:");

        TextField nameTextField = new TextField();
        TextField emailTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField passwordTextField = new TextField();
        TextField countryTextField = new TextField();
        TextField postalCodeTextField = new TextField();

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, nameLabel, nameTextField);
        inputGrid.addRow(1, emailLabel, emailTextField);
        inputGrid.addRow(2, addressLabel, addressTextField);
        inputGrid.addRow(3, passwordLabel, passwordTextField);
        inputGrid.addRow(4, countryLabel, countryTextField);
        inputGrid.addRow(5, postalCodeLabel, postalCodeTextField);

        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                Customer customer = new Customer();
                customer.setName(nameTextField.getText());
                customer.setEmail(emailTextField.getText());
                customer.setAddress(addressTextField.getText());
                customer.setPassword(passwordTextField.getText());
                customer.setCountry(countryTextField.getText());
                customer.setPostalCode(postalCodeTextField.getText());
                customerController.create(customer);
                customerObservableList.add(customer);
                return customer;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showFindByIdCustomerDialog() {
        // Create dialog
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Find By Id");

        // Create input fields
        Label idLabel = new Label("ID: ");
        TextField idTextField = new TextField();
        //idTextField.setPromptText("Enter Customer ID: ");

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
                Customer customer = customerController.getById(id);
                if (customer != null) {
                    Dialog<Customer> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("FindById details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Id:"+ customer.getIdCustomer() +
                            "\nName: " + customer.getName() +
                            "\nEmail: " + customer.getEmail() +
                            "\nAddress: " + customer.getAddress() +
                            "\nPassword: " + customer.getPassword() +
                            "\nCountry: " + customer.getCountry() +
                            "\nPostal Code: " + customer.getPostalCode());
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return customer;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }

    private void showFindByNameCustomerDialog() {
        // Create dialog
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Find By Name");

        // Create input fields
        Label nameLabel = new Label("NAME: ");
        TextField nameTextField = new TextField();
        //idTextField.setPromptText("Enter Customer NAME: ");

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
                Customer customer = customerController.getByName(name);
                if (customer != null) {
                    Dialog<Customer> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("FindByName details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Id:"+ customer.getIdCustomer() +
                            "\nName: " + customer.getName() +
                            "\nEmail: " + customer.getEmail() +
                            "\nAddress: " + customer.getAddress() +
                            "\nCountry: " + customer.getCountry() +
                            "\nPostal Code: " + customer.getPostalCode());
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();
                    return customer;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }

    private void deleteCustomerDialog() {
        // Create dialog
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Delete By Id");

        // Create input fields
        Label idLabel = new Label("ID: ");
        TextField idTextField = new TextField();
        //idTextField.setPromptText("Delete Customer with ID: ");

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
                Customer customer = customerController.delete(id);
                if (customer != null) {
                    Dialog<Customer> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("Delete details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Customer with id: " + id + "deleted!");
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return customer;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }
}
