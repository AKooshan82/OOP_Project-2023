package RestaurantOwner;

import java.util.ArrayList;

public class Restaurant {
    String name, type, rank, path;
    //path is for pictures of restaurant
    ArrayList<String> comments;
    ArrayList<Food> foods = new ArrayList<Food>(0);
    //TODO:Location
    Restaurant(String name, String type, String rank){
        this.rank = rank;
        this.name = name;
        this.type = type;
    }
    public Restaurant createRestaurant(){
        //
        return null;
    }
}
