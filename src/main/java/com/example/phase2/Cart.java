package com.example.phase2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Cart implements Initializable {
    @FXML
    ScrollPane scrollPane;
    @FXML
    Button home;
    String orderName = "Order: ";
    String from = "From: ";
    String price = "Price: ";
    @FXML
    Label snappFood;
    public static ArrayList<Order> orders = new ArrayList<>(0);

    @FXML
    VBox center;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMain();
    }
    @FXML
    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showOrder(){
        if (orders.size() == 0){
            HBox hBox = new HBox();
            hBox.setPrefHeight(Utility.CART_HBOX_HEIGHT);
            hBox.setPrefHeight(Utility.CART_HBOX_WIDTH);
            Label l = new Label("No Orders Yet!");
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(l);
            center.getChildren().add(hBox);
        }
        for (Order order : orders) {
            HBox hBox = new HBox();
            Label l = new Label();
            l.setText(orderDetails(order).toString());
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefHeight(Utility.CART_HBOX_HEIGHT);
            hBox.setPrefHeight(Utility.CART_HBOX_WIDTH);
            hBox.getChildren().add(l);
            center.getChildren().add(hBox);
        }
    }
    public void initMain(){
        //Costumer.setCurrentOrders();
        orders.addAll(Costumer.notPaidOrders);
        snappFood.setStyle(Them.MAIN_TOP_LABEL);
        scrollPane.setMaxWidth(Utility.WIDTH);
        scrollPane.setMaxHeight(Utility.HEIGHT);

        home.setMaxSize(Utility.MAIN_BUTTON_WIDTH, Utility.MAIN_BUTTON_HEIGHT);
        showOrder();
    }
    public StringBuilder orderDetails(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append(orderName);
        sb.append(order.name + "\n");
        sb.append(from);
        sb.append(order.from + "\n");
        sb.append(price);
        sb.append(order.price + "\n");
        return sb;
    }
    public void switchToPay(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
