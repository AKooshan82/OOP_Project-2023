package com.example.phase2;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Order {
    String name, status, price, from;
    Restaurant restaurant;
    Scene scene;
    Order(String name, String price){
        this.name = name;
        this.price = price;
    }
    Order(Food food, Restaurant restaurant){
        this.name = food.name;
        this.price = food.price;
        try {
            this.restaurant = (Restaurant) restaurant.clone();
        }catch (CloneNotSupportedException e){
            System.out.println(e);
        }
    }
    Order( Order order){
        this.name = order.name;
        this.price = order.price;
        this.from = order.from;
        try {
            this.restaurant = (Restaurant) restaurant.clone();
        }catch (CloneNotSupportedException e){
            System.out.println(e);
        }
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void sceneBuilder(){
        //TODO: order's scene is built when it's clicked
    }
}
