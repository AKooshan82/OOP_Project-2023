package com.example.phase2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderRegistration implements Initializable {
@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
    initMain(); }
@FXML
Label detail;
@FXML
Label price;
@FXML
    TextArea disCount;
    @FXML
    Label snappFood;
    @FXML
    Button pay;
    Order order;
    @FXML
    Button charge;
    @FXML
    Label alert;
    OrderRegistration(Order order){
        this.order = order;
    }
    private void initDetail(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + order.name + "\n");
        sb.append("Restaurant: " + order.from + "\n");
        detail.setText(sb.toString());
    }
    private void initMain(){
        snappFood.setStyle(Them.MAIN_TOP_LABEL);
        initDetail();
        price.setText(order.price + "$");
        disCount.setOnKeyPressed(keyEvent -> getDisCount());
    }
    private void getDisCount(){
        String dis = disCount.getText();
        for (DiscountCode d : Costumer.disCountCode){
            if (d.stringEquals(dis)){
                order.price = Double.toString(Integer.parseInt(order.price) * (1. - Double.parseDouble(d.percent)));
                price.setText(order.price);
            }
        }
    }
    @FXML
    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("cart.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void pay(){
        if (Integer.parseInt(order.price) > Integer.parseInt(Costumer.cash)){
            pay.setText("Successful!");
            Costumer.cash = Integer.toString(Integer.parseInt(Costumer.cash) - Integer.parseInt(order.price));
            order.status = "preparing";
            Order o = new Order(order);
            Costumer.notPaidOrders.remove(o);
            Costumer.currentOrders.add(o);
        }else{
            charge.setStyle("-fx-border-color: red;");
            alert.setStyle("-fx-background-color: red;");
            alert.setText("Charge your wallet first!");
        }
    }
    @FXML
    public void switchToCharge(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("payment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}