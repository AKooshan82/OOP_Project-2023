package com.example.phase2;

import javafx.scene.image.Image;

import java.util.ArrayList;
public class Costumer {
    public static String userName, path, cash, address, mobileNum;
    public static ArrayList<DiscountCode> disCountCode;
    public static ArrayList<Order> preOrders = new ArrayList<>(0);
    public static ArrayList<Order> notPaidOrders = new ArrayList<>(0);
    //Location location;
    public static ArrayList<Order> currentOrders = new ArrayList<>(0);
//    public static void setCurrentOrders(){
//        Order order = new Order("kabab", "20");
//        Order order2 = new Order("An", "20");
//        currentOrders.add(order2);
//        currentOrders.add(order);
//    }
}
