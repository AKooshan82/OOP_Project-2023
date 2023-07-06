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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FastFood implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMain();
    }
    private ArrayList<Button> buttons = new ArrayList<>(0);
    @FXML
    private void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("costumerFirstPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    VBox restBut;
    private ImageView fastFoodImage(){
        Image image = new Image("C:\\Users\\Victus\\Desktop\\java\\phase2\\phase2\\src\\main\\resources\\com\\example\\phase2\\fast-food.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(Utility.SHOW_REST_BUT / 10);
        imageView.setFitWidth(Utility.SHOW_REST_BUT / 10);
        return imageView;
    }
    private String detail(Restaurant restaurant){
        StringBuilder sb = new StringBuilder();
        sb.append("name: " + restaurant.name + "\n");
        sb.append("rank: " + restaurant.rank + "/5" + "\n");
        return sb.toString();
    }
    private void initMain(){
        restBut.setAlignment(Pos.CENTER);
        for (Restaurant restaurant : App.restaurants){
            if (restaurant.type.equals("Fast Food")) {
                ImageView iw = fastFoodImage();
                Button b = new Button("", iw);
                b.setGraphic(iw);
                b.setText(detail(restaurant));
                b.setPrefSize(Utility.SHOW_REST_BUT, 10);
                b.setOnAction(event -> {
                    try {
                        Parent root = FXMLLoader.load(Restaurant.class.getResource("Restaurant.fxml"));
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
        }
        restBut.setPrefSize(Utility.WIDTH, buttons.size() * 60);
        restBut.getChildren().addAll(buttons);
    }
}
