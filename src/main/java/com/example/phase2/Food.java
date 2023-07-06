package com.example.phase2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Food implements Cloneable{
    Button foodButton = new Button();
    String name, price, type;
    Food(String name, String price, String type){
        this.name = name;
        this.type = type;
        this.price = price;
        setFoodButton();
    }
    private void setFoodButton(){
        StringBuilder sb = new StringBuilder();
        sb.append("name" + this.name + "\n");
        sb.append("type" + this.type + "\n");
        sb.append("price" + this.price);
        foodButton.setText(sb.toString());
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
