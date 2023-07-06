package com.example.phase2;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchBar {
    @FXML
    private TextArea searchBar;
    public String text;
    public String fxid;
    @FXML
    public VBox vBox;
    boolean runned = false;
    ArrayList<Object> arrayList = new ArrayList<>(0);
    public SearchBar(TextArea textArea, VBox vBox){
        this.vBox = vBox;
        searchBar = textArea;
        searchBar.setId(textArea.getId());
        fillArrayList();
        textArea.setOnKeyPressed(e -> search());
        vBox.setMaxWidth(Utility.SEARCHBAR_WIDTH);
    }
    @FXML
    public void search(){
        vBox.getChildren().clear();
        StringBuilder restSearch = new StringBuilder();
        restSearch.append(".*");
        restSearch.append(searchBar.getText());
        restSearch.append(".*");
        fillVBox(restSearch.toString());
    }
    public void fillVBox(String s){
//        Image image = new Image("C:\\Users\\Victus\\Desktop\\java\\phase2\\phase2\\src\\main\\resources\\com\\example\\phase2\\user.png");
//        Image image2 = new Image("C:\\Users\\Victus\\Desktop\\java\\phase2\\phase2\\src\\main\\resources\\com\\example\\phase2\\vegetable.png");
//        ImageView imageView = new ImageView(image);
//        ImageView imageView2 = new ImageView(image2);
//        imageView2.setFitHeight(20);
//        imageView.setFitHeight(20);
//        imageView.setFitWidth(100);
//        imageView2.setFitWidth(100);
//        Button b1 = new Button("name", imageView2);
//        Button b2 = new Button("kkkk", imageView);
//        b1.setPrefSize(10, 10);
//        b2.setPrefSize(10, 10);
//        if (b1.getText().matches(s)) {
//            vBox.getChildren().add(b1);
//        }
//        if(b2.getText().matches(s)) {
//            vBox.getChildren().add(b2);
//        }
        if (arrayList.get(0) instanceof Restaurant) {
            for (Object o : arrayList) {
                if (((Restaurant)o).name.matches(s.toString())) {
                    ((Restaurant)o).viewButton.setMaxSize(Utility.SEARCHBAR_WIDTH, Utility.SEARCHBAR_HEIGHT);
                    vBox.getChildren().add(((Restaurant)o).setButton(Utility.SEARCHBAR_WIDTH, Utility.SEARCHBAR_HEIGHT));
                }
            }
        }else if (arrayList.get(0) instanceof SuperMarket){
            for (Object o : arrayList) {
                if (((SuperMarket)o).name.matches(s.toString())) {
                  vBox.getChildren().add(((SuperMarket)o).setButton(Utility.SEARCHBAR_WIDTH, Utility.SEARCHBAR_HEIGHT));
                }
            }
        }
    }
    public void fillArrayList(){
            arrayList.addAll(App.restaurants);
            arrayList.addAll(App.superMarkets);
    }
}
