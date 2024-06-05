package com.example.demo1;

import com.example.demo1.Controllers.AdminController;
import com.example.demo1.database.Database;
import com.example.demo1.entities.Admin;
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

public class AdminApp extends Application {

    private final AdminController adminController = new AdminController();
    private final ObservableList<Admin> adminObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("~ Admin ~");
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Create header
        Label titleLabel = new Label("~ Admin ~");
        titleLabel.getStyleClass().add("title");

        Button addButton = new Button("Add Admin");
        addButton.setOnAction(event -> showAddAdminDialog());
        addButton.getStyleClass().add("add-button");

        Button findByIdButton = new Button("Find By Id");
        findByIdButton.setOnAction(event -> showFindByIdAdminDialog());
        findByIdButton.getStyleClass().add("findById-button");

        Button findByNameButton = new Button("Find By Email");
        findByNameButton.setOnAction(event -> showFindByEmailAdminDialog());
        findByNameButton.getStyleClass().add("findByName-button");
        
        Button deleteButton = new Button("Delete By Id");
        deleteButton.setOnAction(event -> deleteAdminDialog());
        deleteButton.getStyleClass().add("delete-button");

        HBox buttonContainer = new HBox(10, addButton, findByIdButton, findByNameButton, deleteButton);

        BorderPane headerPane = new BorderPane();
        headerPane.setLeft(titleLabel);
        headerPane.setRight(buttonContainer);
        headerPane.setPadding(new Insets(10, 20, 10, 20));
        headerPane.getStyleClass().add("header");

        // Create Admin list view
        ListView<Admin> adminListView = new ListView<>();
        adminListView.setPlaceholder(new Label("No admins found."));
        adminListView.getStyleClass().add("admin-list");
        adminListView.setItems(adminObservableList);

        // Load admins into the list
        loadAdmins();

        // Create main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(headerPane);
        mainPane.setCenter(adminListView);
        mainPane.getStyleClass().add("main");

        // Create scene
        Scene scene = new Scene(mainPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Set scene on stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadAdmins() {
        List<Admin> admins = adminController.getAllAdmins();
        adminObservableList.addAll(admins);
    }

    private void showAddAdminDialog() {
        // Create dialog
        Dialog<Admin> dialog = new Dialog<>();
        dialog.setTitle("Add Admin");

        // Create input fields
        Label nameLabel = new Label("Name:");
        Label emailLabel = new Label("Email:");
        Label passwordLabel = new Label("Password:");


        TextField nameTextField = new TextField();
        TextField emailTextField = new TextField();
        TextField passwordTextField = new TextField();

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, nameLabel, nameTextField);
        inputGrid.addRow(1, emailLabel, emailTextField);
        inputGrid.addRow(2, passwordLabel, passwordTextField);


        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                Admin admin = new Admin();
                admin.setName(nameTextField.getText());
                admin.setEmail(emailTextField.getText());
                admin.setPassword(passwordTextField.getText());
                adminController.createAdmin(admin);
                adminObservableList.add(admin);
                return admin;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showFindByIdAdminDialog() {
        // Create dialog
        Dialog<Admin> dialog = new Dialog<>();
        dialog.setTitle("Find By Id");

        // Create input fields
        Label idLabel = new Label("ID: ");
        TextField idTextField = new TextField();
        //idTextField.setPromptText("Enter Admin ID: ");

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
                Admin admin = adminController.getAdminById(id);
                if (admin != null) {
                    Dialog<Admin> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("FindById details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Id:"+ admin.getId() +
                            "\nName: " + admin.getName() +
                            "\nEmail: " + admin.getEmail() +
                            "\nPassword: " + admin.getPassword());
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return admin;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }

    private void showFindByEmailAdminDialog() {
        // Create dialog
        Dialog<Admin> dialog = new Dialog<>();
        dialog.setTitle("Find By Email");

        // Create input fields
        Label emailLabel = new Label("EMAIL: ");
        TextField emailTextField = new TextField();

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, emailLabel, emailTextField);

        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType findByEmailButton = new ButtonType("FindByEmail", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(findByEmailButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == findByEmailButton) {
                String email = emailTextField.getText();
                Admin admin = adminController.getAdminByEmail(email);
                if (admin != null) {
                    Dialog<Admin> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("FindByName details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Id:"+ admin.getId() +
                            "\nName: " + admin.getName() +
                            "\nEmail: " + admin.getEmail());
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();
                    return admin;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }
    private void deleteAdminDialog() {
        // Create dialog
        Dialog<Admin> dialog = new Dialog<>();
        dialog.setTitle("Delete By Id");

        // Create input fields
        Label idLabel = new Label("ID: ");
        TextField idTextField = new TextField();

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
                Admin admin = adminController.delete(id);
                if (admin != null) {
                    Dialog<Admin> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("Delete details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("admin with id: " + id + "deleted!");
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return admin;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }


}
