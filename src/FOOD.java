import java.util.ArrayList;
import java.util.Comparator;


public class FOOD {
    String name;
    boolean isActive;
    int price;

    RATING rating;
    //ArrayList<Integer> ratings;
    ArrayList<COMMENT> comments;
    ArrayList<Integer> commentsIDs;
    int discount;
    Timed<Boolean> hasDiscount;
    int ID;
    RESTURANT resturant;

    FOOD(String name,int price,RESTURANT resturant){
        this.name=name;
        this.price=price;
        this.resturant=resturant;
        this.ID=(int)SnappFood.randomNums1.next();
        this.discount=0;
        this.isActive=true;
       // this.ratings=new ArrayList<>();
        this.comments=new ArrayList<>();
        this.commentsIDs=new ArrayList<>();
        this.rating=new RATING();
    }
    void editFoodName(String newName){this.name=newName;}
    void editFoodPrice(int newPrice){this.price=newPrice;}
    void deactiveFood(){this.isActive=false;}
    void activeFood(){this.isActive=true;}
    @Override
    public String toString(){
        String s1 ="";
        if(this.isActive){s1="Active";}
        else{s1="Deactive";}
        String s= "ID: "+this.ID+"  *  name: "+this.name+"  *  price: "+this.price+"  *  discount: "+this.discount+"%  *  "+s1;
        return s;
    }
    public String toStringForUser(){
        String s= "ID: "+this.ID+"  *  name: "+this.name+"  *  price: "+this.price+"  *  discount: "+this.discount+"%";
        return s;
    }
    public static Comparator<FOOD> foodComparator = new Comparator<FOOD>() {
        public int compare(FOOD f1, FOOD f2) {
                return f1.ID-f2.ID;
        }
    };
}
