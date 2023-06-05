import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

public class adminFoodMenu {
    public void run(Scanner scanner) {
        String result = "";
        Matcher matcher;
        String s1="";
        System.out.println("you are in food page.");
        String str = scanner.nextLine();
        while (true) {
            if(str.matches("\\s*LOGOUT\\s*")){
                SnappFood.Logout();
                new LoginMenu().run(scanner);
                break;
            }
            else if(str.matches("\\s*RETURN\\s*")){
                new AdminRestaurantMenu().run(scanner);
                break;
            }
            else if(str.matches("\\s*(GO)\\s+(TO)\\s+(FIRST)\\s+(PAGE)\\s*")){
                new AdminMenu().run(scanner);
                break;
            }
            else if(str.equals("DISPLAY RATING")){SnappFood.nowAdmin.diplayFoodRating();}

            else if(str.equals("DISPLAY ALL RATINGS")){SnappFood.nowAdmin.diplayFoodAllRatings();}

            else if(str.equals("DISPLAY COMMENTS")){SnappFood.nowAdmin.displayCommentsForFood();}

            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.ADD_RESPONSE)) != null){
                int ID=Integer.parseInt(matcher.group("ID"));
                if(SnappFood.nowAdmin.reponseForFoodErrors(ID)){
                    s1=scanner.nextLine();
                    SnappFood.nowAdmin.reponseForFoodConfirming(ID,s1);
                }
            }
            else if((matcher = COMMANDS.getMatcher(str, COMMANDS.EDIT_RESPONSE)) != null){
                int ID=Integer.parseInt(matcher.group("ID"));
                if(SnappFood.nowAdmin.editReponseForFoodErrors(ID)){
                    s1=scanner.nextLine();
                    SnappFood.nowAdmin.reponseForFoodConfirming(ID,s1);
                }
            }
            else System.out.println("invalid command!");

            str = scanner.nextLine();
        }
    }
}
