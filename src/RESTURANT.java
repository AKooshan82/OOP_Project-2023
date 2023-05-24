import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Handler;

import static java.lang.Thread.sleep;

public class RESTURANT{
    String name;
    int ID;
    ADMIN owner;
    RATING rating;
    ArrayList<USER> Costumers;
    ArrayList<String> type;
    ArrayList<COMMENT> comments;
    ArrayList<Integer> commentsIDs;
    ArrayList<FOOD> foods;
    ArrayList<FOOD> menu;
    ArrayList<Integer> foodIDs;
    ArrayList<ORDER> activeOrders;
    ArrayList<Integer> activeOrdersIDs;
    ArrayList<ORDER> allOrders;
    RESTURANT(String name,ArrayList<String> type){
        this.name=name;
        //this.owner=SnappFood.nowAdmin;
        //this.ID=SnappFood.AllResturants.size()+1;
        this.foods=new ArrayList<>();
        this.foodIDs=new ArrayList<>();
        this.menu=new ArrayList<>();
        this.activeOrders=new ArrayList<>();
        this.comments=new ArrayList<>();
        this.commentsIDs=new ArrayList<>();
        this.type=type;
        this.allOrders=new ArrayList<>();
        this.activeOrdersIDs=new ArrayList<>();
        this.rating=new RATING();
    }
    void newFood(FOOD food){
        this.foods.add(food);
        this.foodIDs.add(food.ID);
        this.menu.add(food);
    }
    boolean deactiveFood(int ID){
        int n=0;
        boolean b=false;
        for(FOOD f : this.foods){
            if(f.ID==ID){
                if(this.activeOrders.contains(f)){
                    System.out.println("your restaurant has some active orders of this food.please follow up them and then try again.");
                    break;
                }
                else if(!f.isActive){
                    System.out.println("this food is already deactive.");
                    break;
                }
                else {
                    n = this.menu.indexOf(f);
                    this.menu.remove(n);
                    f.isActive = false;
                    b=true;
                    break;
                }
            }
        }
        return b;
    }
    boolean activeFood(int ID){
        int n=0;
        boolean b=false;
        for(FOOD f : this.foods){
            if(f.ID==ID){
                if(f.isActive){
                    System.out.println("this food is already active.");
                    break;
                }
                else {
                    f.isActive = true;
                    this.menu.add(f);
                    b=true;
                    break;
                }
            }
        }
        return b;
    }
    void deleteFood(int ID){
        int n=0;
        int n1=0;
        for(FOOD f : this.menu) {
            if (f.ID == ID) {
                n1=this.menu.indexOf(f);
                break;
            }
        }
        for(FOOD f : this.foods){
            if(f.ID==ID){
                this.foods.remove(f);
                this.menu.remove(n1);
                this.foodIDs.remove(this.foodIDs.indexOf(ID));
                break;
            }
        }
    }
    void showFoods(){
        Collections.sort(this.foods,FOOD.foodComparator);
        System.out.println("Foods:");
        for(FOOD f : this.foods){
            System.out.println(f.toString());}
    }
    void newFoodName(int ID,String  newName){
        int n=this.getIndexOfFoodByID(ID);
        this.foods.get(n).name=newName;
    }
    void newFoodPrice(int ID,int price){
        int n=this.getIndexOfFoodByID(ID);
        this.foods.get(n).price=price;
    }
    boolean isInActiveOrders(int ID){
        boolean b=false;
        for(FOOD f : this.foods) {
            if (f.ID == ID) {
                if (this.activeOrders.contains(f)) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }
    boolean discountFood(int ID,int percent, long time){
        int n=this.getIndexOfFoodByID(ID);
        boolean b=false;
        if(this.foods.get(n).hasDiscount==null){
            int t1=(int) (time*1000);
            this.foods.get(n).hasDiscount=new Timed<>(Boolean.TRUE,Boolean.FALSE,time*1000);
            this.foods.get(n).hasDiscount.tick();
            this.foods.get(n).discount=percent;
            this.setDiscountPercentRelatedToTime(this,n,t1);
            b=true;
        }
        else {System.out.println("this food already has an active discount.");}
        return b;
    }
    void setDiscountPercentRelatedToTime(RESTURANT resturant,int index,int time){
        Timer timer = new Timer(time, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                resturant.foods.get(index).hasDiscount=null;
                resturant.foods.get(index).discount=0;
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.start(); // Go!
    }
    int getIndexOfFoodByID(int ID){
        int n=-1;
        for(FOOD f : this.foods) {
            if (f.ID == ID) {
                n= this.foods.indexOf(f);
                break;
                }
            }
        return n;
    }
    int getIndexOfFoodInMenuForUserByID(int ID){
        int n=-1;
        for(FOOD f : this.menu) {
            if (f.ID == ID) {
                n= this.foods.indexOf(f);
                break;
            }
        }
        return n;
    }

    String showFoodType(){return this.type.toString();}

    void displayActiveOrders(){
        System.out.println("Active Orders:");
        for(ORDER order : this.activeOrders){
            order.showOrderDetailsForRestaurant();
        }
    }
    void sendingOrder(int ID){
     int index=this.activeOrdersIDs.indexOf(ID);
     this.activeOrders.get(ID).isSent=true;
     this.allOrders.add(this.activeOrders.get(index));
     this.activeOrders.remove(index);
     this.activeOrdersIDs.remove(index);
        System.out.println("the order is sent successfully.");
    }
    public static Comparator<RESTURANT> resturantComparator = new Comparator<RESTURANT>() {
        public int compare(RESTURANT r1, RESTURANT r2) {
            if(!r1.name.equals(r2.name)) {
                return r1.name.compareTo(r2.name);
            }
            else{
                return r1.ID-r2.ID;
            }
        }
    };

}
