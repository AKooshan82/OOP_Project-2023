import java.util.Scanner;
import java.util.regex.Matcher;

public class userFoodMenu {
    public void run(Scanner scanner) {
        String result = "";
        Matcher matcher;
        String s1 = "";
        System.out.println("you are in food page.");
        String str = scanner.nextLine();
        while (true) {
            if (str.matches("\\s*LOGOUT\\s*")) {
                SnappFood.Logout();
                new LoginMenu().run(scanner);
                break;
            }
            else if (str.matches("\\s*RETURN\\s*")) {
                new userRestaurantMenu().run(scanner);
                break;
            }
            else if(str.matches("\\s*(GO)\\s+(TO)\\s+(FIRST)\\s+(PAGE)\\s*")){
                new userMenu().run(scanner);
                break;
            }
            else if(str.equals("ADD NEW COMMENT")){
                System.out.println("Comment's content:");
                s1=scanner.nextLine();
                SnappFood.nowUser.addCommentForFood(s1);
            }

            else if(str.equals("DISPLAY COMMENTS")){SnappFood.nowUser.displayCommentsForFood();}

            else if(str.equals("DISPLAY RATING")){SnappFood.nowUser.diplayFoodRating();}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.SUBMIT_RATING)) != null) {SnappFood.nowUser.submitFoodRating(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.EDIT_RATING)) != null) {SnappFood.nowUser.editFoodRating(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.EDIT_COMMENT_USER)) != null) {
                int ID=Integer.parseInt(matcher.group("ID"));
                if(!SnappFood.nowUser.editCommentForFoodErrors(ID)){
                    System.out.println("Comment's new content:");
                    s1=scanner.nextLine();
                    SnappFood.nowUser.editCommentForFoodConfirming(ID,s1);
                }
            }

            else if (str.equals("ADD THIS FOOD TO CART")) {SnappFood.nowUser.addFoodToCart();}

            else System.out.println("invalid command!");
            str = scanner.nextLine();
        }
    }
}
