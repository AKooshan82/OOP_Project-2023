import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

public class USER {
    String username;
    String securityWord;
    String password;
    FOOD nowUserFood;
    RESTURANT nowUserResturant;
    ArrayList<FOOD> cart;
    ArrayList<ORDER> orders;
    int balance;

    USER(String username,String password){
                this.username=username;
                this.password=password;
        this.cart = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.balance = 0;
    }

    static boolean UserRegisterChecker(String username,String password){
        boolean b=false;
        if (!username.matches("^\\d*[a-zA-Z][a-zA-Z0-9]*$")) {
            System.out.println("username must be numbers or letters and should have at least 1 letter.");
        } else if (SnappFood.UsersNames.contains(username)) System.out.println("username already exists.");
        else {b=SnappFood.passwordChecker(password);}
        return b;
    }

    void forgotPassword(String word){
        if(!this.securityWord.equals(word)) System.out.println("the answer is wrong.");
        else{
            System.out.println("your password is : "+this.password);
        }
    }

    void showResturantsForUser(){
        System.out.println("restaurants :");
        for(RESTURANT r : SnappFood.AllResturants){
            System.out.println("Restaurant Name: "+r.name+"  *  Restaurant ID: "+r.ID+"  * type: "+r.type.toString());
        }
    }
    void searchRestaurantByName(Matcher matcher) {
        String name = matcher.group("name");
        boolean b = false;
        for (RESTURANT resturant : SnappFood.AllResturants) {
            if (resturant.name.equals(name)) {
                System.out.println("Restaurant name: " + name + "  *  ID: " + resturant.ID + "  *  foodType: " + resturant.type.toString());
                b = true;
            }
        }
        if (!b) System.out.println("Restaurant with the given name doesn't exist.");
    }

    boolean selectReataurant(Matcher matcher) {
        int ID = Integer.parseInt(matcher.group("ID"));
        boolean b = false;
        if (SnappFood.AllResturants.size() < ID) System.out.println("Restaurant with the given ID doesn't exist.");
        else {
            this.nowUserResturant = SnappFood.AllResturants.get(ID - 1);
            b = true;
        }
        return b;
    }
    void showFoodsForUser(){
        System.out.println("foods :");
        for(FOOD f : this.nowUserResturant.menu){System.out.println(f.toStringForUser());}
    }
    void searchFoodByName(Matcher matcher) {
        String name = matcher.group("name");
        boolean b = false;
        for (FOOD food : this.nowUserResturant.menu) {
            if (food.name.equals(name)) {
                System.out.println("Food name: " + name + "  *  ID: " + food.ID + "  *  Price: " + food.price);
                b = true;
            }
        }
        if (!b) System.out.println("Food with the given name doesn't exist.");
}

    boolean selectFood(Matcher matcher) {
        int ID = Integer.parseInt(matcher.group("ID"));
        int n = this.nowUserResturant.getIndexOfFoodInMenuForUserByID(ID);
        boolean b = false;
        if (n == -1) System.out.println("Food with the given ID doesn't exist in Restaurant.");
        else {
            this.nowUserFood = this.nowUserResturant.menu.get(n);
            b = true;
        }
        return b;
    }
    void chargeAccount(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        this.balance += amount;
        System.out.println("account charged successfully.");
    }

    void showAccountCharge() {
        System.out.println("balance: " + this.balance);
    }

    void addCommentForFood(String content) {
        COMMENT comment = new COMMENT(content, this);
        this.nowUserFood.comments.add(comment);
        this.nowUserFood.commentsIDs.add(comment.ID);
        System.out.println("comment with ID " + comment.ID + " added for this food successfully.");
    }

    boolean editCommentForFoodErrors(int ID) {
        boolean b = true;
        if (!this.nowUserFood.commentsIDs.contains(ID))
            System.out.println("comment with the given ID doesn't exist for this food.");
        else {
            int index = this.nowUserFood.commentsIDs.indexOf(ID);
            if (this.nowUserFood.comments.get(index).commentOwner != this) {
                System.out.println("this comment is not yours.you can only edit your comments.");
            } else {
                b = false;
            }
        }
        return b;
    }

    void editCommentForFoodConfirming(int ID, String newComment) {
        int index = this.nowUserFood.commentsIDs.indexOf(ID);
        this.nowUserFood.comments.get(index).editContent(newComment);
    }

    void displayCommentsForFood() {
        System.out.println("comments:");
        for (COMMENT comment : this.nowUserFood.comments) {
            System.out.println("comment ID: " + comment.ID + " ----> " + comment.content);
            comment.getCommentResponse();
        }
    }

    void addCommentForRestaurant(String content) {
        COMMENT comment = new COMMENT(content, this);
        this.nowUserResturant.comments.add(comment);
        this.nowUserResturant.commentsIDs.add(comment.ID);
        System.out.println("comment with ID " + comment.ID + " added for this restaurant successfully.");
    }

    boolean editCommentForRestaurantErrors(int ID) {
        boolean b = true;
        if (!this.nowUserResturant.commentsIDs.contains(ID))
            System.out.println("comment with the given ID doesn't exist for this restaurant.");
        else {
            int index = this.nowUserResturant.commentsIDs.indexOf(ID);
            if (this.nowUserResturant.comments.get(index).commentOwner != this) {
                System.out.println("this comment is not yours.you can only edit your comments.");
            } else {
                b = false;
            }
        }
        return b;
    }

    void editCommentForRestaurantConfirming(int ID, String newComment) {
        int index = this.nowUserResturant.commentsIDs.indexOf(ID);
        this.nowUserResturant.comments.get(index).editContent(newComment);
    }

    void displayCommentsForRestaurant() {
        System.out.println("comments:");
        for (COMMENT comment : this.nowUserResturant.comments) {
            System.out.println("comment ID: " + comment.ID + " ----> " + comment.content);
            comment.getCommentResponse();
        }
    }
    void addFoodToCart() {
        this.cart.add(this.nowUserFood);
        System.out.println("this food added to your cart successfully.");
    }

    void displayCartStatus() {
        if (this.cart.size() == 0) System.out.println("there is no food in your cart.");
        else {
            System.out.println("your cart status:");
            for (FOOD food : this.cart) {
                System.out.println("Food ID: " + food.ID + "  *  name: " + food.name + "  *  price: " + food.price+"  *  discount: "+food.discount+"%");
            }
        }
    }

    void confirmOrder() {
        ORDER order = new ORDER(this);
        if(this.balance<order.totalCost) System.out.println("your balance is not enough.");
        else {
            this.balance-=order.totalCost;
            this.orders.add(order);
            this.nowUserResturant.activeOrders.add(order);
            this.nowUserResturant.activeOrdersIDs.add(order.ID);
            this.cart.clear();
            System.out.println("order confirmed successfully.");
        }
    }

    void showAllOrderHistory() {
        if (this.orders.size() == 0) System.out.println("you don't have any orders until now;");
        else {
            for (ORDER order : this.orders) {
                System.out.println("Order ID: " + order.ID + "  *  date & time: " + order.time);
            }
        }
    }

    void showSelectedOrderInformation(Matcher matcher) {
        int ID = Integer.parseInt(matcher.group("ID"));
        int index = this.getOrderIndexByID(ID);
        if (index == -1) System.out.println("there is no order with the given ID.");
        else {
            System.out.println(this.orders.get(index).toString());
        }
    }

    int getOrderIndexByID(int ID) {
        int n = -1;
        for (ORDER order : this.orders) {
            if (order.ID == ID) {
                n = this.orders.indexOf(order);
                break;
            }
        }
        return n;
    }

    //void diplayRestaurantRating(){}
    void diplayFoodRating() {
        if(this.nowUserFood.rating.usersRated.size()==0) System.out.println("no one rate this food yet.");
        else{System.out.println(this.nowUserFood.rating.toString());}
    }

    void submitFoodRating(Matcher matcher) {
        if(this.nowUserFood.rating.usersRated.contains(this)) System.out.println("yor have already rated this food before.");
        else {
            double amount = Double.parseDouble(matcher.group("amount"));
            this.nowUserFood.rating.newRating(this, amount);
            System.out.println("you rate this food successfully.");
        }
    }
    void editFoodRating(Matcher matcher) {
        if(!this.nowUserFood.rating.usersRatedNames.contains(this.username)) System.out.println("yor have not rated this food before.");
        else {
            double amount = Double.parseDouble(matcher.group("amount"));
            this.nowUserFood.rating.editRating(this, amount);
            System.out.println("your rate edited successfully..");
        }
    }
    void submitRestaurantRating(Matcher matcher) {
        if(this.nowUserResturant.rating.usersRated.contains(this)) System.out.println("yor have already rated this restaurant before.");
        else {
            double amount = Double.parseDouble(matcher.group("amount"));
            this.nowUserResturant.rating.newRating(this, amount);
            System.out.println("you rate this restaurant successfully.");
        }
    }
    void editRestaurantRating(Matcher matcher) {
        if(!this.nowUserResturant.rating.usersRatedNames.contains(this.username)) System.out.println("yor have not rated this restaurant before.");
        else {
            double amount = Double.parseDouble(matcher.group("amount"));
            this.nowUserResturant.rating.editRating(this, amount);
            System.out.println("your rate edited successfully..");
        }
    }
    void diplayRestaurantRating() {
        if(this.nowUserResturant.rating.usersRated.size()==0) System.out.println("no one rate this restaurant yet.");
        else{System.out.println(this.nowUserResturant.rating.toString());}
    }

}

