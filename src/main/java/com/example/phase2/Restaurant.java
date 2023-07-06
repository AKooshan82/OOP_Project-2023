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
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Restaurant implements Initializable , Cloneable{
    String name, rank, path, type, address;
    ArrayList<String> comments;
    //Location location;
    //TODO add location too!
    Scene scene;
    //ImageView imageView;
    Button viewButton = new Button();
    ArrayList<Food> foods = new ArrayList<>(0);
    @FXML
    VBox commentsVBox;
    @FXML
    VBox foodsVBox;
    @FXML
    TextArea search;
    @FXML
    VBox searchVBox;
    @FXML
    private Label snappFood;
    Restaurant(String name, String rank, String type, String address){
        this.rank = rank;
        this.name = name;
        this.type = type;
        this.address = address;
    }
    Restaurant(){}
    @FXML
    private Label detail;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMain();
    }
    @FXML
    private void initMain(){
        snappFood.setStyle(Them.MAIN_TOP_LABEL);
        snappFood.setMaxSize(Utility.SNAPPFOOD_WIDTH, Utility.SNAPFOOD_HEIGHT);
        initDetail();
        initComments();
        initFoods();
        SearchBar foodSearchBar = new SearchBar(search, searchVBox);
    }
    @FXML
    private void initDetail(){
        StringBuilder sb = new StringBuilder();
        sb.append("name" + this.name + "\n");
        sb.append("type" + this.type + "\n");
        sb.append("rank" + this.rank + "\\5" + "\n");
        sb.append("address" + this.address + "\n");
        detail.setText(sb.toString());
    }
    @FXML
    private void initComments(){
        int commentVBoxHeight = 0;
        ArrayList<Label> commentLabel = new ArrayList<>(0);
        for (String c : comments){
            Label l = new Label(c);
            l.setPrefWidth(Utility.COMMENT_WIDTH);
            commentVBoxHeight += l.getHeight();
            commentLabel.add(l);
        }
        commentsVBox.setPrefSize(Utility.COMMENT_WIDTH, commentVBoxHeight);
        commentsVBox.getChildren().addAll(commentLabel);
    }
    @FXML
    private void initFoods(){
        int foodVBoxHeight = 0;
        ArrayList<Button> foodButtons = new ArrayList<>(0);
        for (Food f : foods) {
            f.foodButton.setOnAction(event -> {
                Order order = new Order(f, this);
                Costumer.notPaidOrders.add(order);
            });
            foodButtons.add(f.foodButton);
        }
        for (Button b : foodButtons) {
            b.setPrefWidth(Utility.COMMENT_WIDTH);
            foodVBoxHeight += b.getHeight();
        }
        foodsVBox.setPrefSize(Utility.COMMENT_WIDTH, foodVBoxHeight);
        foodsVBox.getChildren().addAll(foodButtons);
    }
    public void setPath(String s){
        this.path = s;
    }
    @FXML
    public Button setButton (int width, int height){
        viewButton.setText(name);
        viewButton.setOnMouseClicked(mouseEvent ->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                System.out.println(e);
            }});
//        viewButton.setGraphic(imageView);
//        Image image = new Image(this.path);
//        imageView = new ImageView(image);
        //viewButton = new Button(name, imageView);
        viewButton.setMaxSize(width, height);
        return viewButton;
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
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    @FXML
    public void showRest()throws IOException{
        System.out.println("in" + name);
    }
}
