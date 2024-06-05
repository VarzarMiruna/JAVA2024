package com.example.demo1;

import com.example.demo1.Controllers.OrderController;
import com.example.demo1.entities.Order;
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
import java.util.List;

public class OrderApp extends Application {

    private final OrderController orderController = new OrderController();
    private final ObservableList<Order> orderObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("~ Orders ~");

        // Create header
        Label titleLabel = new Label("~ Orders ~");
        titleLabel.getStyleClass().add("title");

        Button addButton = new Button("Add Order");
        addButton.setOnAction(event -> showAddOrderDialog());
        addButton.getStyleClass().add("add-button");

        Button deleteButton = new Button("Delete Order");
        deleteButton.setOnAction(event -> deleteOrderDialog());
        deleteButton.getStyleClass().add("delete-button");

        HBox buttonContainer = new HBox(10, addButton, deleteButton);

        BorderPane headerPane = new BorderPane();
        headerPane.setLeft(titleLabel);
        headerPane.setRight(buttonContainer);
        headerPane.setPadding(new Insets(10, 20, 10, 20));
        headerPane.getStyleClass().add("header");

        // Create order list view
        ListView<Order> orderListView = new ListView<>();
        orderListView.setPlaceholder(new Label("No orders found."));
        orderListView.getStyleClass().add("order-list");
        orderListView.setItems(orderObservableList);

        // Load orders into the list
        loadOrders();

        // Create main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(headerPane);
        mainPane.setCenter(orderListView);
        mainPane.getStyleClass().add("main");

        // Create scene
        Scene scene = new Scene(mainPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Set scene on stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadOrders() {
        List<Order> orders = orderController.getAll();
        orderObservableList.addAll(orders);
    }

    private void showAddOrderDialog() {
        // Create dialog
        Dialog<Order> dialog = new Dialog<>();
        dialog.setTitle("Add Order");

        // Create input fields
        Label idOrderLabel = new Label("idOrder:");

        TextField idOrderTextField = new TextField();
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(20));

        inputGrid.addRow(0, idOrderLabel, idOrderTextField);

        // Set dialog content
        dialog.getDialogPane().setContent(inputGrid);

        // Add buttons
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, cancelButton);

        // Execute dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                Order order = new Order();
                order.setIdOrder(Integer.parseInt(idOrderTextField.getText()));
                orderController.create(order);
                orderObservableList.add(order);
                return order;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void deleteOrderDialog() {
        // Create dialog
        Dialog<Order> dialog = new Dialog<>();
        dialog.setTitle("Delete By Id");

        // Create input fields
        Label idLabel = new Label("ID order: ");
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
                Order order = orderController.delete(id);
                if (order != null) {
                    Dialog<Order> detailsDialog = new Dialog<>();
                    detailsDialog.setTitle("Delete details");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    detailsDialog.getDialogPane().getButtonTypes().addAll(exitButton);
                    Label detailsLabel = new Label("Order with id: " + id + "deleted!");
                    detailsDialog.getDialogPane().setContent(detailsLabel);
                    detailsDialog.showAndWait();

                    return order;
                }
            }
            return null;
        });
        dialog.showAndWait();
    }
}
