package com.example.phase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SuperMarket {
    static String name, rank, path;
    //Location location;
    //TODO add location too!
    ImageView imageView;
    Button viewButton;
    SuperMarket(String name, String rank, String path){
        this.rank = rank;
        this.name = name;
        this.path = path;
        //this.location = l;
    }
    public Button setButton(int width, int height){
//        viewButton.setOnMouseClicked(showRest(ActionEvent e);
//            });
        viewButton.setGraphic(imageView);
        Image image = new Image(this.path);
        imageView = new ImageView(image);
        viewButton = new Button(name, imageView);
        viewButton.setMaxSize(width, height);
        return viewButton;
    }
}
