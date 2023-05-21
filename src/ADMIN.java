import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

public class ADMIN {
    String username;
    String password;
    String securityWord;
    RESTURANT nowResturant=null;
    FOOD nowFood=null;
    ArrayList<RESTURANT> resturants;
    ArrayList<Integer> resturantIDs;
    ADMIN(String username,String password){
        if(ADMIN.AdminRegisterChecker(username,password)) {
            this.username=username;
            this.password=password;
            this.resturants=new ArrayList<>();
            this.resturantIDs=new ArrayList<>();
        }
    }
    static boolean AdminRegisterChecker(String username,String password){
        boolean b=false;
        if(!username.matches("^\\d*[a-zA-Z][a-zA-Z0-9]*$")) {System.out.println("username must be numbers or letters and should have at least 1 letter.");}
        else if(SnappFood.AdminsNames.contains(username)) System.out.println("username already exists.");
        else {
            if(password.length()<8) System.out.println("password must contains at least 8 characters.");
            else if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")){
                if(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")) System.out.println("password must contains at least one number.");
                else if(password.contains("//s")) System.out.println("white spaces don’t allowed in password.");
                else if(password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,20}$")) System.out.println("password must contains at least one lower case alphabet.");
                else if(password.matches("^(?=.*[a-z])(?=.*[0-9])(?=\\S+$).{8,20}$")) System.out.println("password must contains at least one upper case alphabet.");
            }
            else{b=true;}
        }
        return b;
    }
    void forgotPassword(String word){
        if(!this.securityWord.equals(word)) System.out.println("the answer is wrong.");
        else{
            System.out.println("your password is : "+this.password);
        }
    }
    void addResturant(Matcher matcher){
        String name=matcher.group("name");
        String [] arrOfString=matcher.group("types").split("\\s+");
        ArrayList<String> type= new ArrayList<>(Arrays.asList(arrOfString));
        RESTURANT resturant=new RESTURANT(name,type);
        int ID=SnappFood.AllResturants.size()+1;
        resturant.ID=ID;
        resturant.owner=this;
        this.resturants.add(resturant);
        this.resturantIDs.add(ID);
        SnappFood.AllResturants.add(resturant);
        System.out.println("Resturant with ID "+ID+" added successfully.");
    }
    boolean selectResturant(int ID){
        boolean b=false;
        if(!this.resturantIDs.contains(ID)) System.out.println("resturant with the given ID doesn't exist.");
        else {
            int n = 0;
            for (RESTURANT r : this.resturants) {
                if (r.ID == ID) {
                    n = this.resturants.indexOf(r);
                    break;
                }
            }
            this.nowResturant = this.resturants.get(n);
            b=true;
        }
        return b;
    }
    void showResturantType(){
        System.out.println(this.nowResturant.showFoodType());
    }
    void showResturantsForAdmin(){
        Collections.sort(this.resturants,RESTURANT.resturantComparator);
        for(RESTURANT r : this.resturants){
            System.out.println("Restaurant Name: "+r.name+"   Restaurant ID: "+r.ID);
        }
    }
    boolean editResturantType(ArrayList<String> type){
        boolean b=true;
        if(!this.nowResturant.activeOrders.isEmpty()){
            System.out.println("your restaurant has some active orders.please follow up them and then try again.");
            b=false;
        }
        else{
            this.editResturantTypeConfirming(type);
        }
        return b;
    }
    void editResturantTypeConfirming(ArrayList<String> type){
      this.nowResturant.type.clear();
      this.nowResturant.type=(ArrayList<String>) type.clone();
      this.nowResturant.foods.clear();
      this.nowResturant.menu.clear();
      this.nowResturant.foodIDs.clear();
        System.out.println("FoodType edited successfully.");
    }
    void showResturantFoodsForAdmin(){this.nowResturant.showFoods();}
    void addFood(Matcher matcher){
        String name=matcher.group("name");
        int price=Integer.parseInt(matcher.group("price"));
        FOOD food=new FOOD(name,price,this.nowResturant);
        this.nowResturant.newFood(food);
        System.out.println("food with ID "+food.ID+" added to your restaurant successfully.");
    }
    boolean deleteResturantFood(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        boolean b=false;
        if(!this.nowResturant.foodIDs.contains(ID)) System.out.println("food with the given ID doesn't exist in restaurant.");
        if(this.nowResturant.isInActiveOrders(ID)){System.out.println("your restaurant has some active orders of this food.please follow up them and then try again.");}
        else{
           this.deleteResturantFoodConfirming(matcher);
           b=true;
        }
        return b;
    }
    void deleteResturantFoodConfirming(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        this.nowResturant.deleteFood(ID);
        System.out.println("food deleted successfully.");
    }

    void editFoodName(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        String newName=matcher.group("newName");
        this.nowResturant.newFoodName(ID,newName);
        System.out.println("food name edited successfully.");
    }
    void editFoodPrice(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        int newPrice=Integer.parseInt(matcher.group("newPrice"));
        this.nowResturant.newFoodPrice(ID,newPrice);
        System.out.println("food price edited successfully.");
    }
    void deactiveResturantFood(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        this.nowResturant.deactiveFood(ID);
        System.out.println("deactivation done successfully.");
    }
    void activeResturantFood(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        this.nowResturant.activeFood(ID);
        System.out.println("activation done successfully.");
    }
    void selectFood(Matcher matcher){
        int ID=Integer.parseInt(matcher.group("ID"));
        this.nowFood=this.nowResturant.foods.get(ID);
        System.out.println("food selected successfully.");
    }

}
