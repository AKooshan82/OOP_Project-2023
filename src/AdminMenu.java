import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminMenu {
    public void run(Scanner scanner) {
        System.out.println("you are in first page.");
        SnappFood.nowAdmin.showResturantsForAdmin();
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
            if(str.matches("\\s*(DISPLAY)\\s+(OPEN)\\s+(ORDERS)\\s*")){SnappFood.nowAdmin.showOpenOrders();}

            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.EDIT_ORDER)) != null){SnappFood.nowAdmin.editOrderStatus(matcher);}

            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.ADD_RESTAURANT)) != null){SnappFood.nowAdmin.addResturant(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.SELECT_RESTAURANT)) != null) {
               int id=Integer.parseInt(matcher.group("ID"));
               if(SnappFood.nowAdmin.selectResturant(id)){new AdminRestaurantMenu().run(scanner);}
            }
            // else if    *SHOW LOCATION*;
            else System.out.println("invalid command!");
            str = scanner.nextLine();
        }
    }
}
