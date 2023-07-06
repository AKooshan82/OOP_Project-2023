package com.example.phase2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class OrderList implements Initializable{
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMain();
    //for (Order order : Costumer.Preorders){
    //  Image img = new Image(order.path);
    //ImageView iw = new ImageView(img);
    //Button b = new Button(order.name, iw);
    //switchToOrder(b, order.name);
    //vbox.getChildren().add()
    //}
    //
    }
    @FXML
    private VBox prev;
    @FXML
    private VBox curr;
    String orderName = "Order: ";
    String from = "From: ";
    String price = "Price: ";
    @FXML
    private Label snappFood;

    @FXML
    private void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showCurrOrder(){
        if (Costumer.currentOrders.size() == 0){
            HBox hBox = new HBox();
            hBox.setPrefHeight(Utility.CART_HBOX_HEIGHT);
            hBox.setPrefHeight(Utility.CART_HBOX_WIDTH);
            Label l = new Label("No Orders Yet!");
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(l);
            curr.getChildren().add(hBox);
        }
        for (Order order : Costumer.currentOrders) {
            HBox hBox = new HBox();
            Label l = new Label();
            l.setText(currOrderDetails(order).toString());
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefHeight(Utility.CART_HBOX_HEIGHT);
            hBox.setPrefHeight(Utility.CART_HBOX_WIDTH);
            hBox.getChildren().add(l);
            curr.getChildren().add(hBox);
        }
    }
    public void showPrevOrder(){
        if (Costumer.preOrders.size() == 0){
            HBox hBox = new HBox();
            hBox.setPrefHeight(Utility.CART_HBOX_HEIGHT);
            hBox.setPrefHeight(Utility.CART_HBOX_WIDTH);
            Label l = new Label ("No Orders Yet!");
            hBox.setAlignment (Pos.CENTER);
            hBox.getChildren().add(l);
            prev.getChildren().add(hBox);
        }
        for (Order order : Costumer.preOrders) {
            HBox hBox = new HBox();
            Label l = new Label();
            l.setText(preorderDetails(order).toString());
            hBox.setAlignment(Pos.CENTER);
            hBox.setPrefHeight(Utility.CART_HBOX_HEIGHT);
            hBox.setPrefHeight(Utility.CART_HBOX_WIDTH);
            hBox.getChildren().add(l);
            prev.getChildren().add(hBox);
        }
    }

    public StringBuilder preorderDetails(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append(orderName);
        sb.append(order.name + "\n");
        sb.append(from);
        sb.append(order.from + "\n");
        sb.append(price);
        sb.append(order.price + "\n");
        return sb;
    }
    public StringBuilder currOrderDetails(Order order){
        StringBuilder sb = new StringBuilder();
        sb.append(orderName);
        sb.append(order.name + "\n");
        sb.append(from);
        sb.append(order.from + "\n");
        sb.append(price);
        sb.append(order.price + "\n");
        sb.append("status" + order.status);
        return sb;
    }
    public void initMain(){
        //Costumer.setCurrentOrders();
        snappFood.setStyle(Them.MAIN_TOP_LABEL);
        showPrevOrder();
        showCurrOrder();
    }
}
