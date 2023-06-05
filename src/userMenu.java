import java.util.Scanner;
import java.util.regex.Matcher;

public class userMenu {
    public void run(Scanner scanner) {
        System.out.println("you are in first page.");
        SnappFood.nowUser.showResturantsForUser();
        String result = "";
        Matcher matcher;
        String s1="";
        String str = scanner.nextLine();
        while (true) {
            if(str.matches("\\s*LOGOUT\\s*")){
                SnappFood.Logout();
                new LoginMenu().run(scanner);
                break;
            }

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.SEARCH_RESTAURANT_USER)) != null) {SnappFood.nowUser.searchRestaurantByName(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.SELECT_RESTAURANT)) != null) {
               if(SnappFood.nowUser.selectReataurant(matcher)){
                   new userRestaurantMenu().run(scanner);
               }
            }

            else if (str.equals("ACCESS ORDER HISTORY")) {SnappFood.nowUser.showAllOrderHistory();}

            else if (str.equals("DISPLAY CART STATUS")) {SnappFood.nowUser.displayCartStatus();}


            else if (str.equals("CONFIRM ORDER")) {SnappFood.nowUser.confirmOrder();}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.SELECT_ORDER)) != null) {SnappFood.nowUser.showSelectedOrderInformation(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.CHARGE_ACCOUNT)) != null) {SnappFood.nowUser.chargeAccount(matcher);}

            else if (str.equals("DISPLAY ACCOUNT CHARGE")) {SnappFood.nowUser.showAccountCharge();}

            // TODO (KOOSHAN)  else if    *SHOW ESTIMATED DELIVERY TIME*;

            else System.out.println("invalid command!");
            str = scanner.nextLine();
        }
    }
}
