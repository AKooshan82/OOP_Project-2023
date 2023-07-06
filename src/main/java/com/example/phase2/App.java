package com.example.phase2;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static ArrayList<Restaurant> restaurants = new ArrayList<>(0);
    public static ArrayList<SuperMarket> superMarkets = new ArrayList<>(0);
    public static void init(){
        Restaurant r = new Restaurant("sib", "3", "Fast Food", "Tehran");
        Food food = new Food("pizza", "20", "fast");
        r.foods.add(food);
        Restaurant r1 = new Restaurant("sib1", "3", "Fast Food", "Tehran");
        Food food1 = new Food("pizza", "20", "fast");
        r1.foods.add(food1);
//        r1.viewButton.setOnAction(event -> {
//            System.out.println("in1");
////            try {
////                Parent root = FXMLLoader.load(Restaurant.class.getResource("RestaurantPage.fxml"));
////                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////                Scene scene = new Scene(root);
////                stage.setScene(scene);
////                stage.show();
////            }catch (IOException e){
////                System.out.println(e);
////            }
//        });
//        r.viewButton.setOnAction(event -> {
//            System.out.println("in2");
////            try {
////                Parent root = FXMLLoader.load(Restaurant.class.getResource("RestaurantPage.fxml"));
////                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////                Scene scene = new Scene(root);
////                stage.setScene(scene);
////                stage.show();
////            }catch (IOException e){
////                System.out.println(e);
////            }
//        });
        Restaurant r3 = new Restaurant("sib2", "3", "Traditional", "Tehran");
        Food food3 = new Food("kabeb", "20", "fast");
        r3.foods.add(food);
//        r3.viewButton.setOnAction(event -> {
////            try {
//                System.out.println("in3");
////                Parent root = FXMLLoader.load(Restaurant.class.getResource("RestaurantPage.fxml"));
////                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////                Scene scene = new Scene(root);
////                stage.setScene(scene);
////                stage.show();
////            }catch (IOException e){
////                System.out.println(e);
////            }
//        });
        r1.setButton(10, 10);
        r.setButton(10, 10);
        r3.setButton(10, 10);
        restaurants.add(r);
        restaurants.add(r1);
        restaurants.add(r3);
        //TODO: initializing the restaurants and costumer
    }
    //it has static variables like an arraylist of restaurants and superMarkets and cafe
}
