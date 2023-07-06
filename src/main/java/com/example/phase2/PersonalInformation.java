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

public class PersonalInformation implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMain();
        userName.setPromptText("pantea");
        mobileNum.setPromptText("9188070780");
        address.setPromptText("Tehran. Sharif");
        cashl.setText(Costumer.cash);
//        userName.setPromptText(Costumer.userName);
//        mobileNum.setPromptText(Costumer.mobileNum);
//        address.setPromptText(Costumer.address);
    }
    @FXML
    TextArea userName;
    @FXML
    TextArea mobileNum;
    @FXML
    TextArea address;
    @FXML
    String name = new String("personalInformation.fxml");
    @FXML
    Label snappFood;
    @FXML
    Button userNameB;
    @FXML
    Button mobileNumB;
    @FXML
    Button addressB;
    @FXML
    Button cashButton;
    @FXML
    Label cashl;

    @FXML
    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void changeUserName(){
        Costumer.userName = userName.getText();
        userNameB.setText("changed!");
        userName.setText("");
        userName.setPromptText(Costumer.userName);
    }
    @FXML
    private void changeAddress(){
        Costumer.address = address.getText();
        address.setText("");
        addressB.setText("changed!");
        address.setPromptText(Costumer.address);
    }
    @FXML
    private void changeNum(){
        Costumer.mobileNum = mobileNum.getText();
        mobileNum.setText("");
        mobileNum.setPromptText(Costumer.mobileNum);
        mobileNumB.setText("changed!");
    }
    @FXML
    private void switchToPayment(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("payment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void initMain(){
       snappFood.setStyle(Them.MAIN_TOP_LABEL);
//        userName.setPromptText(Costumer.userName);
//        mobileNum.setPromptText(Costumer.mobileNum);
//        address.setPromptText(Costumer.address);
        //cashl.setText(Costumer.cash);
    }
}
