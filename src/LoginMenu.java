import java.util.Scanner;
import java.util.regex.Matcher;
public class LoginMenu {
    public void run(Scanner scanner) {
        String result = "";
        Matcher matcher;
        String s1="";
        String str = scanner.nextLine();
        while (!str.matches("\\s*EXIT\\s*")) {
            if (( matcher=COMMANDS.getMatcher(str, COMMANDS.ADD_USER)) != null) {
                if(SnappFood.AddUser(matcher)) {
                    System.out.println("What is your favorite animal?");
                    s1 = scanner.nextLine();
                    SnappFood.setSecurityWordForUser(matcher.group("username"),s1);
                }
            }
            else if ((matcher = COMMANDS.getMatcher(str, COMMANDS.ADD_ADMIN)) != null) {
                if(SnappFood.AddAdmin(matcher)) {
                    System.out.println("What is your favorite animal?");
                    s1 = scanner.nextLine();
                    SnappFood.setSecurityWordForAdmin(matcher.group("username"),s1);
                }
            }
            else if ((matcher = COMMANDS.getMatcher(str, COMMANDS.LOGIN_USER)) != null) {
               // if(SnappFood.LoginUser(matcher)) new UserMenu().run(scanner);
            }
            else if ((matcher = COMMANDS.getMatcher(str, COMMANDS.LOGIN_ADMIN)) != null) {
                if(SnappFood.LoginAdmin(matcher)) new AdminMenu().run(scanner);
            }
             else if ((matcher = COMMANDS.getMatcher(str, COMMANDS.FORGET_PASSWORD_USER)) != null) {
                 String s=matcher.group("username");
                 if(!SnappFood.UsersNames.contains(s)) System.out.println("username doesn't exist.");
                 else{
                     System.out.println("What is your favorite animal?");
                     s1=scanner.nextLine();
                     SnappFood.restorePassForUser(s,s1);
                 }
            }
             else if ((matcher = COMMANDS.getMatcher(str, COMMANDS.FORGET_PASSWORD_ADMIN)) != null) {
                String s=matcher.group("username");
                if(!SnappFood.AdminsNames.contains(s)) System.out.println("username doesn't exist.");
                else{
                    System.out.println("What is your favorite animal?");
                    s1=scanner.nextLine();
                    SnappFood.restorePassForAdmin(s,s1);
                }
            }
             else System.out.println("invalid command!");

            str = scanner.nextLine();
        }
    }
}