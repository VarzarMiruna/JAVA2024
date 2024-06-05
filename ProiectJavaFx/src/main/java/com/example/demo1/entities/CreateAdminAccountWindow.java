package com.example.demo1.entities;

import com.example.demo1.Controllers.AdminController;
import com.example.demo1.entities.Admin;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateAdminAccountWindow extends Stage {

    private TextField nameTextField;
    private TextField emailTextField;
    private PasswordField passwordTextField;
    private TextField addressTextField;
    private TextField cityTextField;
    private TextField countryTextField;
    private TextField postalCodeTextField;

    private boolean accountCreated = false;
    private String createdAccountEmail = "";
    private AdminController adminController;

    public CreateAdminAccountWindow(AdminController adminController) {
        this.adminController = adminController;     // Create UI components
        Label nameLabel = new Label("Name:");
        nameTextField = new TextField();

        Label emailLabel = new Label("Email:");
        emailTextField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordTextField = new PasswordField();

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

        formPane.add(buttonPane, 1, 7);

        Scene scene = new Scene(formPane);
        setScene(scene);
        setTitle("Create Admin Account");
        initModality(Modality.APPLICATION_MODAL); // Prevent user from interacting with parent window until this window is closed
    }

    private void handleCreateAccount() {
        // Retrieve user details
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        // Create new admin object and store it in the database
        int adminId = 0;
        try {
            adminId = adminController.getNextAdminId();
        } catch (Exception e) {
            showAlert("Error", "Failed to create admin account: " + e.getMessage());
            return;
        }
        Admin newAdmin = new Admin(adminId, name, email, password);
        try {
            adminController.createAdmin(newAdmin);
            accountCreated = true;
            createdAccountEmail = newAdmin.getEmail();
            close();
        } catch (Exception e) {
            showAlert("Error", "Failed to create admin account: " + e.getMessage());
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
