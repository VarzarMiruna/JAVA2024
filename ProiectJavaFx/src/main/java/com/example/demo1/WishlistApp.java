package com.example.demo1;

import com.example.demo1.Controllers.WishlistController;
import com.example.demo1.database.Database;
import com.example.demo1.entities.Wishlist;
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

public class WishlistApp extends Application {

    private final WishlistController wishlistController = new WishlistController();
    private final ObservableList<Wishlist> wishlistObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wishlist Management");
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Create header
        Label titleLabel = new Label("Wishlist Management");
        titleLabel.getStyleClass().add("title");

        Button addButton = new Button("Add at Wishlist");
        addButton.setOnAction(event -> showAddwishlistDialog());
        addButton.getStyleClass().add("add-button");

        Button findByIdButton = new Button("Find By Id");
        findByIdButton.setOnAction(event -> showFindByIdWishlistDialog());
        findByIdButton.getStyleClass().add("findById-button");

        Button deleteButton = new Button("Delete By Id");
        deleteButton.setOnAction(event -> deleteWishlistDialog());
        deleteButton.getStyleClass().add("delete-button");

        HBox buttonContainer = new HBox(10, addButton, findByIdButton, deleteButton);

        BorderPane headerPane = new BorderPane();
        headerPane.setLeft(titleLabel);
        headerPane.setRight(buttonContainer);
        headerPane.setPadding(new Insets(10, 20, 10, 20));
        headerPane.getStyleClass().add("header");

        // Create Wishlist list view
        ListView<Wishlist> wishlistListView = new ListView<>();
        wishlistListView.setPlaceholder(new Label("No wishlists found."));
        wishlistListView.getStyleClass().add("wishlist-list");
        wishlistListView.setItems(wishlistObservableList);

        // Load wishlists into the list
        loadWishlists();

        // Create main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(headerPane);
        mainPane.setCenter(wishlistListView);
        mainPane.getStyleClass().add("main");

        // Create scene
        Scene scene = new Scene(mainPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Set scene on stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadWishlists() {
        List<Wishlist> wishlists = wishlistController.getAll();
        wishlistObservableList.addAll(wishlists);
    }

    private void showAddwishlistDialog() {
        // Create dialog
        Dialog<Wishlist> dialog = new Dialog<>();
        dialog.setTitle("Add Wishlist");

        // Create input field
        Label idCustomerLabel = new Label("Id Customer:");
        Label idProductLabel = new Label("Id Product:");


        TextField idCustomerTextField = new TextField();
        TextField idProductTextField = new TextField();


        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, idCustomerLabel, idCustomerTextField);
        inputGrid.addRow(1, idProductLabel, idProductTextField);

        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                Wishlist wishlist = new Wishlist();
                wishlist.setIdCustomer(Integer.parseInt(idCustomerTextField.getText()));
                wishlist.setIdProduct(Integer.parseInt(idProductTextField.getText()));
                wishlistController.create(wishlist);
                wishlistObservableList.add(wishlist);
                return wishlist;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showFindByIdWishlistDialog() {
        // Create dialog
        Dialog<Wishlist> dialog = new Dialog<>();
        dialog.setTitle("Find By Id");

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
        ButtonType findButton = new ButtonType("Find", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(findButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == findButton) {
                int id = Integer.parseInt(idTextField.getText());
                Wishlist wishlist = wishlistController.getById(id);
                if (wishlist != null) {
                    Dialog<Wishlist> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("FindById details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Id:"+ wishlist.getIdWishlist() +
                            "\nidCustomer: " + wishlist.getIdCustomer() +
                            "\nidProduct: " + wishlist.getIdProduct());
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return wishlist;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }


    private void deleteWishlistDialog() {
        // Create dialog
        Dialog<Wishlist> dialog = new Dialog<>();
        dialog.setTitle("Delete By Id");

        // Create input fields
        Label idLabel = new Label("ID: ");
        TextField idTextField = new TextField();
        //idTextField.setPromptText("Delete Wishlist with ID: ");

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
                Wishlist wishlist = wishlistController.delete(id);
                if (wishlist != null) {
                    Dialog<Wishlist> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("Delete details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Wishlist with id: " + id + "deleted!");
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return wishlist;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }
}


