import java.util.ArrayList;


public class FOOD {
    String name;
    boolean isActive;
    int price;
    ArrayList<Integer> ratings;
    int discount;
    int ID;
    RESTURANT resturant;

    FOOD(String name,int price,RESTURANT resturant){
        this.name=name;
        this.price=price;
        this.resturant=resturant;
        this.ID=(int)SnappFood.randomNums1.next();
        this.discount=0;
        this.isActive=true;
        this.ratings=new ArrayList<>();
    }
    //void setResturant(RESTURANT resturant){this.resturant=resturant;}
    void editFoodName(String newName){this.name=newName;}
    void editFoodPrice(int newPrice){this.price=newPrice;}
    void deactiveFood(){this.isActive=false;}
    void activeFood(){this.isActive=true;}
    @Override
    public String toString(){
        String s= "ID: "+this.ID+"  *  name: "+this.name+"  *  price: "+this.price+"  *  discount: "+this.discount+"%";
        return s;
    }
}
