import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminMenu {
    public void run(Scanner scanner) {
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
            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.ADD_RESTAURANT)) != null){SnappFood.nowAdmin.addResturant(matcher);}
            // TODO : make it easier to read
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
