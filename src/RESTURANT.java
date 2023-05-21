import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;

public class RESTURANT{
    String name;
    int ID;
    ADMIN owner;
    ArrayList<USER> Costumers;
    ArrayList<String> type;
    ArrayList<FOOD> foods;
    ArrayList<FOOD> menu;
    ArrayList<Integer> foodIDs;
    ArrayList<FOOD> activeOrders;
    RESTURANT(String name,ArrayList<String> type){
        this.name=name;
        this.owner=SnappFood.nowAdmin;
        this.ID=SnappFood.AllResturants.size()+1;
        this.foods=new ArrayList<>();
        this.foodIDs=new ArrayList<>();
        this.menu=new ArrayList<>();
        this.activeOrders=new ArrayList<>();
        this.type=type;
    }
    void newFood(FOOD food){
        this.foods.add(food);
        this.foodIDs.add(food.ID);
        this.menu.add(food);
    }
    void deactiveFood(int ID){
        int n=0;
        for(FOOD f : this.foods){
            if(f.ID==ID){
                if(this.activeOrders.contains(f)){
                    System.out.println("your restaurant has some active orders of this food.please follow up them and then try again.");
                }
                else if(!f.isActive){
                    System.out.println("this food is already deactive.");
                }
                else {
                    n = this.menu.indexOf(f);
                    this.menu.remove(n);
                    f.isActive = false;
                    break;
                }
            }
        }
    }
    void activeFood(int ID){
        int n=0;
        for(FOOD f : this.foods){
            if(f.ID==ID){
                if(!f.isActive){
                    System.out.println("this food is already active.");
                }
                else {
                    f.isActive = true;
                    this.menu.add(f);
                    break;
                }
            }
        }
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
            }
        }
    }
    void showFoods(){
        System.out.println("Foods:");
        for(FOOD f : this.foods){
            System.out.println(f.toString());}
    }
    void newFoodName(int ID,String  newName){
        int n=this.foodIDs.indexOf(ID);
        this.foods.get(n).name=newName;
    }
    void newFoodPrice(int ID,int price){
        int n=this.foodIDs.indexOf(ID);
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
    String showFoodType(){return this.type.toString();}
    public static Comparator<RESTURANT> resturantComparator = new Comparator<RESTURANT>() {
        public int compare(RESTURANT r1, RESTURANT r2) {
            if(!r1.equals(r2)) {
                return r1.name.compareTo(r2.name);
            }
            else{
                return r1.ID-r2.ID;
            }
        }};

}
