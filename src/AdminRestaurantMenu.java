import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminRestaurantMenu {
    public void run(Scanner scanner) {
        String result = "";
        Matcher matcher;
        String s1="";
        System.out.println("you are in restaurant page.");
        String str = scanner.nextLine();
        while (true) {
            if(str.matches("\\s*LOGOUT\\s*")){
                SnappFood.Logout();
                new LoginMenu().run(scanner);
                break;
            }
            else if(str.matches("\\s*RETURN\\s*")){
                new AdminMenu().run(scanner);
                break;
            }

            else if(str.matches("\\s*SHOW\\s+FOODTYPE\\s*")){SnappFood.nowAdmin.showResturantType();}
            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.EDIT_RESTAURANT_TYPE)) != null){
                String [] arrOfString=matcher.group("types").split("\\s+");
                ArrayList<String> Types= new ArrayList<>(Arrays.asList(arrOfString));
                if(SnappFood.nowAdmin.editResturantType(Types)){
                    System.out.println("ARE YOU SURE YOU WANT TO CHANGE YOUR RESTAURANT TYPE?");
                    s1=scanner.nextLine();
                    if(s1.equalsIgnoreCase("YES")){SnappFood.nowAdmin.editResturantTypeConfirming(Types);}
                }
            }

            else if(str.equals("DISPLAY RATING")){SnappFood.nowAdmin.diplayRestaurantRating();}

            else if(str.equals("DISPLAY ALL RATINGS")){SnappFood.nowAdmin.diplayRestaurantAllRatings();}

            else if(str.equals("DISPLAY COMMENTS")){SnappFood.nowAdmin.displayCommentsForRestaurant();}

            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.ADD_RESPONSE)) != null){
                int ID=Integer.parseInt(matcher.group("ID"));
                if(SnappFood.nowAdmin.reponseForRestaurantErrors(ID)){
                    s1=scanner.nextLine();
                    SnappFood.nowAdmin.reponseForRestaurantConfirming(ID,s1);
                }
            }
            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.EDIT_RESPONSE)) != null){
                int ID=Integer.parseInt(matcher.group("ID"));
                if(SnappFood.nowAdmin.editReponseForRestaurantErrors(ID)){
                    s1=scanner.nextLine();
                    SnappFood.nowAdmin.reponseForRestaurantConfirming(ID,s1);
                }
            }
            else if(str.matches("\\s*SELECT\\s+MENU\\s*")){SnappFood.nowAdmin.showResturantFoodsForAdmin();}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.EDIT_FOOD_NAME)) != null) {SnappFood.nowAdmin.editFoodName(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.EDIT_FOOD_PRICE)) != null) {SnappFood.nowAdmin.editFoodPrice(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.ADD_FOOD)) != null) {SnappFood.nowAdmin.addFood(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.DELETE_FOOD)) != null) {
                if(SnappFood.nowAdmin.deleteResturantFood(matcher)){
                    System.out.println("ARE YOU SURE YOU WANT TO DELETE THIS FOOD?");
                    s1=scanner.nextLine();
                    if(s1.equalsIgnoreCase("YES")){
                        SnappFood.nowAdmin.deleteResturantFoodConfirming(matcher);
                        System.out.println("food deleted successfully.");
                    }
                }
            }
            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.ACTIVE_FOOD)) != null) {SnappFood.nowAdmin.activeResturantFood(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.DEACTIVE_FOOD)) != null) {SnappFood.nowAdmin.deactiveResturantFood(matcher);}

            else if(( matcher=COMMANDS.getMatcher(str, COMMANDS.DISCOUNT_FOOD)) != null){SnappFood.nowAdmin.setDiscountForFood(matcher);}

            else if(( matcher=COMMANDS.getMatcher(str, COMMANDS.SELECT_FOOD)) != null){
                SnappFood.nowAdmin.selectFood(matcher);
                new adminFoodMenu().run(scanner);
            }
            else if(str.matches("\\s*LOGOUT\\s*")){
                SnappFood.Logout();
                new LoginMenu().run(scanner);
                break;
            }
            else System.out.println("invalid command!");

            str = scanner.nextLine();
        }
    }
}
