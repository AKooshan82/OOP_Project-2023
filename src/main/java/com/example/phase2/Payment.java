package com.example.phase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.random.*;
public class Payment implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMain();
    }

    @FXML
    private TextArea cardNum;
    @FXML
    private TextArea cvv2;
    @FXML
    private TextArea exp;
    @FXML
    private TextArea secCode;
    @FXML
    private TextArea cardCode;
    @FXML
    private TextArea cash;
    @FXML
    private CheckBox robot;
    @FXML
    private Button charge;
    @FXML
    private Label snappFood;
    @FXML
    private Label secCodeL = new Label();
    private ArrayList<Object> textAreas = new ArrayList<>(0);
    @FXML
    private void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private boolean getCardNum() {
        String s = cardNum.getText();
        if (!s.matches("\\d{16}")) {
            cardNum.setText("");
            cardNum.setPromptText("invalid Credit Number");
            cardNum.setStyle("-fx-border-color: red;");
            return false;
        }
        return true;
    }
    @FXML
    private boolean getRobot(){
        if (robot.isSelected())
            return true;
        robot.setStyle("-fx-border-color: red;");
        return false;
    }
    @FXML
    private boolean getCVV2() {
        String s = cvv2.getText();
        if (!s.matches("\\d{3}")) {
            cvv2.setText("");
            cvv2.setPromptText("invalid CVV2");
            cvv2.setStyle("-fx-border-color: red;");
            return false;
        }
        return true;
    }
    @FXML
    private boolean getEXP(){
        String s = exp.getText();
        if (!s.matches("\\d{2}/\\d{2}")) {
            exp.setText("");
            exp.setPromptText("invalid EXP date");
            exp.setStyle("-fx-border-color: red;");
            return false;
        }
        return true;
    }
    @FXML
    private boolean getSecCode(){
        String s = secCode.getText();
        if (!s.matches("\\d{5}") || !s.equals(secCodeL.getText())) {
            secCode.setText("");
            secCode.setPromptText("invalid security code");
            secCode.setStyle("-fx-border-color: red;");
            return false;
        }
        return true;
    }
    @FXML
    private boolean getCardCode(){
        String s = cardCode.getText();
        if (!s.matches("\\d{6}")) {
            cardCode.setText("");
            cardCode.setPromptText("invalid card code");
            cardCode.setStyle("-fx-border-color: red;");
            return false;
        }
        return true;
    }
    @FXML
    private String getCash(){
        String s = cash.getText();
        if (!s.matches("\\d+")) {
            cash.setText("");
            cash.setPromptText("invalid input");
            cash.setStyle("-fx-border-color: red;");
            return "0";
        }
        return s;
    }
    @FXML
    private void payBtn(){
        setStyleToInit();
        if (getCardCode() && getEXP() && getCardNum() && getCVV2() && getCVV2()  && getSecCode() && getRobot()){
            Long newAmount = (Long.parseLong(getCash()) + Long.parseLong(Costumer.cash));
            Costumer.cash = newAmount.toString();
            charge.setText("charged!");
            setPromText();
        }
        else{
            setPromText();
        }
    }
    public void initMain(){
        textAreas.add(cardNum);
        textAreas.add(cvv2);
        textAreas.add(cash);
        textAreas.add(cardCode);
        textAreas.add(exp);
        textAreas.add(secCode);
        textAreas.add(robot);
        Costumer.cash = "20";
        snappFood.setText("Money Transfer");
        snappFood.setStyle(Them.MAIN_TOP_LABEL);
        Random r = new Random();
        Integer random = r.nextInt(10000, 99999);
        secCodeL.setText(random.toString());
    }
    public void setPromText(){
        cardNum.setPromptText("--/--/--");
        cardNum.setText("");
        cvv2.setPromptText("");
        cvv2.setText("");
        exp.setText("");
        exp.setPromptText("YY/MM");
        secCode.setPromptText("type security code");
        secCode.setText("");
        robot.setSelected(false);
        cardCode.setText("");
        cardCode.setPromptText("");
        cash.setPromptText("$$");
        cash.setText("");
        Random r = new Random();
        Integer random = r.nextInt(10000, 99999);
        secCodeL.setText(random.toString());
    }
    public void setStyleToInit(){
        for(Object o : textAreas){
            if (o instanceof TextArea)
            ((TextArea)o).setStyle("");
            else
                ((CheckBox)o).setStyle("");
        }
    }
}
