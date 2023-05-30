import java.util.Scanner;
import java.util.regex.Matcher;

public class userRestaurantMenu {
    public void run(Scanner scanner) {
        System.out.println("you are in restaurant page.");
        SnappFood.nowUser.showFoodsForUser();
        String result = "";
        Matcher matcher;
        String s1 = "";
        String str = scanner.nextLine();
        while (true) {
            if (str.matches("\\s*LOGOUT\\s*")) {
                SnappFood.Logout();
                new LoginMenu().run(scanner);
                break;
            }
            else if (str.matches("\\s*RETURN\\s*")) {
                new userMenu().run(scanner);
                break;
            }
            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.SEARCH_FOOD_USER)) != null) {SnappFood.nowUser.searchFoodByName(matcher);}


            else if(str.equals("ADD NEW COMMENT")){
                System.out.println("Comment's content:");
                s1=scanner.nextLine();
                SnappFood.nowUser.addCommentForRestaurant(s1);
            }

            else if(str.equals("DISPLAY COMMENTS")){SnappFood.nowUser.displayCommentsForRestaurant();}

            else if(str.equals("DISPLAY RATING")){SnappFood.nowUser.diplayRestaurantRating();}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.SUBMIT_RATING)) != null) {SnappFood.nowUser.submitRestaurantRating(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.EDIT_RATING)) != null) {SnappFood.nowUser.editRestaurantRating(matcher);}

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.EDIT_COMMENT_USER)) != null) {
                int ID=Integer.parseInt(matcher.group("ID"));
                if(!SnappFood.nowUser.editCommentForRestaurantErrors(ID)){
                    System.out.println("Comment's new content:");
                    s1=scanner.nextLine();
                    SnappFood.nowUser.editCommentForRestaurantConfirming(ID,s1);
                }
            }

            else if (( matcher=COMMANDS.getMatcher(str, COMMANDS.SELECT_FOOD)) != null) {
                if(SnappFood.nowUser.selectFood(matcher)){
                     new userFoodMenu().run(scanner);
                }
            }
            // else if    *SHOW LOCATION*;
            else System.out.println("invalid command!");
            str = scanner.nextLine();
        }
    }
}
