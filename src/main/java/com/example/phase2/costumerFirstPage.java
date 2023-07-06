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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class costumerFirstPage implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Image image = new Image("C:\\Users\\Victus\\Desktop\\java\\phase2\\phase2\\src\\main\\resources\\com\\example\\phase2\\soft-drink.png");
//        Image image1 = new Image("C:\\Users\\Victus\\Desktop\\java\\phase2\\phase2\\src\\main\\resources\\com\\example\\phase2\\supermarket.png");
//        Image image2 = new Image("C:\\Users\\Victus\\Desktop\\java\\phase2\\phase2\\src\\main\\resources\\com\\example\\phase2\\vegetable.png");
//        ImageView imageView = new ImageView(image);
//        ImageView imageView1 = new ImageView(image1);
//        ImageView imageView2 = new ImageView(image2);
//        imageView.setFitWidth(40);
//        imageView1.setFitWidth(40);
//        imageView2.setFitWidth(40);
//        imageView.setFitHeight(40);
//        imageView1.setFitHeight(40);
//        imageView2.setFitHeight(40);
//        hBox.getChildren().add(imageView);
//        hBox.getChildren().add(imageView1);
//        hBox.getChildren().add(imageView2);
           //TODO:fillScrollBar(findCloseRest());
            initMain();
    }
    @FXML
    private Stage stage;
    @FXML
    private HBox hBox;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    private OrderList orders = new OrderList();
    @FXML
    private PersonalInformation pI = new PersonalInformation();
    @FXML
    private SuperMarketList SM = new SuperMarketList();
    @FXML
    private StackPane main;
    @FXML
    public TextArea textArea = new TextArea();
    @FXML
    public VBox searchBarVBox = new VBox();
    @FXML
    private Button PI;
    @FXML
    private Button setting;
    @FXML
    private Button orderList;
    @FXML
    private Label snappFood;
    @FXML
    public void initMain(){
        snappFood.setStyle(Them.MAIN_TOP_LABEL);
        main.setPrefHeight(Utility.HEIGHT);
        main.setPrefWidth(Utility.WIDTH);
        SearchBar searchBar = new SearchBar(textArea, searchBarVBox);

    }
    public void switchToMarket (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("superList.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPI (ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("personalInformation.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void swtichToOrder (ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("OrderList.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void swtichToSetting (ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void fillScrollBar(ArrayList<Restaurant> restaurants){
        for (Restaurant restaurant : restaurants){
            restaurant.viewButton.setMaxSize(Utility.CLOSEREST_BUTTON, Utility.CLOSEREST_BUTTON);
            hBox.getChildren().add(restaurant.viewButton);
        }
    }
    public ArrayList<Restaurant> findCloseRest(){
        ArrayList<Restaurant>  closeRest = new ArrayList<>(0);
        for (Restaurant restaurant : App.restaurants){
            //TODO: find colses restaurants due to their location and costumer's
        }
        return closeRest;
    }
    public void switchToCart(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("cart.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToFastFood(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("fastFood.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTrad(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("trad.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCafe(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("cafe.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}