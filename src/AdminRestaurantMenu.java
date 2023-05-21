import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminRestaurantMenu {
    public void run(Scanner scanner) {
        String result = "";
        Matcher matcher;
        String s1="";
        String str = scanner.nextLine();
        while (true) {

            if(str.matches("\\s*RETURN\\s*")){
                new AdminMenu().run(scanner);
                break;
            }

            else if(str.matches("\\s*SHOW\\s+FOODTYPE\\s*")){SnappFood.nowAdmin.showResturantType();}
            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.EDIT_RESTUARANT_TYPE)) != null){
                String [] arrOfString=matcher.group("types").split("\\s+");
                ArrayList<String> Types= new ArrayList<>(Arrays.asList(arrOfString));
                if(SnappFood.nowAdmin.editResturantType(Types)){
                    System.out.println("ARE YOU SURE YOU WANT TO CHANGE YOUR RESTAURANT TYPE?");
                    s1=scanner.nextLine();
                    if(s1.equalsIgnoreCase("YES")){SnappFood.nowAdmin.editResturantTypeConfirming(Types);}
                }
            }
            else if(str.matches("\\s*SELECT\\s+MENU\\s*")){SnappFood.nowAdmin.showResturantFoodsForAdmin();}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.EDIT_FOOD_NAME)) != null) {SnappFood.nowAdmin.editFoodName(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.EDIT_FOOD_PRICE)) != null) {SnappFood.nowAdmin.editFoodPrice(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.ADD_FOOD)) != null) {SnappFood.nowAdmin.addFood(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.DELETE_FOOD)) != null) {
                if(SnappFood.nowAdmin.deleteResturantFood(matcher)){
                    System.out.println("ARE YOU SURE YOU WANT TO CHANGE YOUR DELETE THIS FOOD?");
                    s1=scanner.nextLine();
                    if(s1.equalsIgnoreCase("YES")){SnappFood.nowAdmin.deleteResturantFoodConfirming(matcher);}
                }
            }
            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.ACTIVE_FOOD)) != null) {SnappFood.nowAdmin.activeResturantFood(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.DEACTIVE_FOOD)) != null) {SnappFood.nowAdmin.deactiveResturantFood(matcher);}

            else if(( matcher=COMMANDS.getMatcher(str, COMMANDS.SELECT_FOOD)) != null){
                SnappFood.nowAdmin.selectFood(matcher);
                new adminFoodMenu().run(scanner);
            }
            else System.out.println("invalid command!");

            str = scanner.nextLine();
        }
    }
}
