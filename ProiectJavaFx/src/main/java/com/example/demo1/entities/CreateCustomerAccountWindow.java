package com.example.demo1.entities;

import com.example.demo1.Controllers.CustomerController;
import com.example.demo1.entities.Customer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateCustomerAccountWindow extends Stage {

    private TextField nameTextField;
    private TextField emailTextField;
    private PasswordField passwordTextField;
    private TextField addressTextField;
    private TextField countryTextField;
    private TextField postalCodeTextField;

    private boolean accountCreated = false;
    private String createdAccountEmail = "";
    private CustomerController customerController;

    public CreateCustomerAccountWindow(CustomerController customerController) {
        this.customerController = customerController;     // Create UI components
        Label nameLabel = new Label("Name:");
        nameTextField = new TextField();

        Label emailLabel = new Label("Email:");
        emailTextField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordTextField = new PasswordField();

        Label addressLabel = new Label("Address:");
        addressTextField = new TextField();

        Label countryLabel = new Label("Country:");
        countryTextField = new TextField();

        Label postalCodeLabel = new Label("Postal Code:");
        postalCodeTextField = new TextField();

        Button createButton = new Button("Create Account");
        createButton.setOnAction(event -> handleCreateAccount());

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> close());

        HBox buttonPane = new HBox(10);
        buttonPane.getChildren().addAll(createButton, cancelButton);

        // Create form layout
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.setPadding(new Insets(20));

        formPane.add(nameLabel, 0, 0);
        formPane.add(nameTextField, 1, 0);
        formPane.add(emailLabel, 0, 1);

        formPane.add(emailTextField, 1, 1);
        formPane.add(passwordLabel, 0, 2);
        formPane.add(passwordTextField, 1, 2);
        formPane.add(addressLabel, 0, 3);
        formPane.add(addressTextField, 1, 3);
        formPane.add(countryLabel, 0, 4);
        formPane.add(countryTextField, 1, 4);
        formPane.add(postalCodeLabel, 0, 5);
        formPane.add(postalCodeTextField, 1, 5);
        formPane.add(buttonPane, 0, 6);

        Scene scene = new Scene(formPane);
        setScene(scene);
        setTitle("Create Customer Account");
        initModality(Modality.APPLICATION_MODAL); // Prevent user from interacting with parent window until this window is closed
    }

    private void handleCreateAccount() {
        // Retrieve user details
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String address = addressTextField.getText();
        String country = countryTextField.getText();
        String postalCode = postalCodeTextField.getText();
        // Create new customer object and store it in the database
        int customerId = 0;
        try {
            customerId = customerController.getNextCustomerId();
        } catch (Exception e) {
            showAlert("Error", "Failed to create customer account: " + e.getMessage());
            return;
        }
        Customer newCustomer = new Customer(customerId, name, email, address, password, country, postalCode);
        try {
            customerController.create(newCustomer);
            accountCreated = true;
            createdAccountEmail = newCustomer.getEmail();
            close();
        } catch (Exception e) {
            showAlert("Error", "Failed to create customer account: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean isAccountCreated() {
        return accountCreated;
    }

    public String getCreatedAccountEmail() {
        return createdAccountEmail;
    }
}
