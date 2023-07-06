package com.example.phase2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SuperMarketList implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMain();
    }
@FXML
private Label snappFood;
    ArrayList<Button> buttons = new ArrayList<>(0);
    @FXML
    VBox supBut;
    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void initMain(){
        snappFood.setStyle(Them.MAIN_TOP_LABEL);
        for (SuperMarket superMarket: App.superMarkets){
                ImageView iw = superImage();
                Button b = new Button("", iw);
                b.setGraphic(iw);
                b.setText(detail(superMarket));
                b.setPrefSize(Utility.SHOW_REST_BUT, 10);
                b.setOnAction(event -> {
                    try {
                        Parent root = FXMLLoader.load(Restaurant.class.getResource("RestaurantPage.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }catch (IOException e){
                        System.out.println(e);
                    }
                });
                buttons.add(b);
        }
        supBut.setPrefSize(Utility.WIDTH, buttons.size() * 60);
        supBut.getChildren().addAll(buttons);
    }
    private ImageView superImage(){
        Image image = new Image("C:\\Users\\Victus\\Desktop\\java\\phase2\\phase2\\src\\main\\resources\\com\\example\\phase2\\supermarket.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(Utility.SHOW_REST_BUT / 10);
        imageView.setFitWidth(Utility.SHOW_REST_BUT / 10);
        return imageView;
    }
    private String detail(SuperMarket superMarket){
        StringBuilder sb = new StringBuilder();
        sb.append("name: " + superMarket.name + "\n");
        sb.append("rank: " + superMarket.rank + "/5" + "\n");
        return sb.toString();
    }
}
