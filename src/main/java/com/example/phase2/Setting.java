package com.example.phase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Setting implements Initializable {
@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMain();
    }
    @FXML
    private Label snappFood;

    @FXML
    String name = new String("Setting.fxml");
    @FXML
    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ChangeTheme(){
        //TODO: changing the themes
    }
    private void initMain(){
        snappFood.setStyle(Them.MAIN_TOP_LABEL);
    }
}
