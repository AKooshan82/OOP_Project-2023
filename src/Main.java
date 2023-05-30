import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class Main {
    public static void main (String[] args){
        Scanner scanner=new Scanner(System.in);
        SnappFood.generateRandomNumsForFoodID();
        SnappFood.generateRandomNumsForCommentID();
        new LoginMenu().run(scanner);
    }
}