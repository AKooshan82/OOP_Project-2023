import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
public class SnappFood {
    public static USER nowUser=null;
    public static ADMIN nowAdmin=null;
    public static ArrayList<ADMIN> Admins = new ArrayList<>();
    public static ArrayList<USER> Users = new ArrayList<>();
    public static ArrayList<RESTURANT> AllResturants = new ArrayList<>();
    public static ArrayList<String> AdminsNames = new ArrayList<>();
    public static ArrayList<String> UsersNames = new ArrayList<>();
    public static Iterator randomNums1;
    public static
    int randomNums1IndexCounting=0;
    public static boolean AddUser(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(USER.UserRegisterChecker(username,password)) {
            USER user = new USER(username, password);
            SnappFood.Users.add(user);
            SnappFood.UsersNames.add(username);
            return true;
        }
        else{return false;}
    }
    public static void generateRandomNums(){
        Set<Integer> set = new Random().ints(1, 1000)
                .distinct()
                .limit(200)
                .boxed()
                .collect(Collectors.toSet());
        SnappFood.randomNums1=set.iterator();
    }
    public static void setSecurityWordForUser(String username,String word){
        int id=SnappFood.UsersNames.indexOf(username);
        SnappFood.Users.get(id).securityWord=word;
        System.out.println("user created successfully");
    }
    public static boolean AddAdmin(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(ADMIN.AdminRegisterChecker(username,password)) {
            ADMIN admin = new ADMIN(username, password);
            SnappFood.Admins.add(admin);
            SnappFood.AdminsNames.add(username);
            return true;
        }
        else{return false;}
    }
    public static void setSecurityWordForAdmin(String username,String word){
        int id=SnappFood.AdminsNames.indexOf(username);
        SnappFood.Admins.get(id).securityWord=word;
        System.out.println("admin created successfully.");
    }
    public static boolean LoginUser(Matcher matcher){
        boolean b=false;
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(!SnappFood.UsersNames.contains(username)) System.out.println("username doesn't exists.");
        else if(!SnappFood.Users.get(SnappFood.UsersNames.indexOf(username)).password.equals(password))
            System.out.println("password is incorrect");
        else {
            USER user = SnappFood.Users.get(SnappFood.UsersNames.indexOf(username));
            b=true;
            System.out.println("Logged in successfully.");
            SnappFood.nowUser=user;
            SnappFood.nowAdmin=null;
        }
        return b;
    }
    public static boolean LoginAdmin(Matcher matcher){
        boolean b =false;
        String username = matcher.group("username");
        String password = matcher.group("password");
        if(!SnappFood.AdminsNames.contains(username)) System.out.println("username doesn't exists.");
        else if(!SnappFood.Admins.get(SnappFood.AdminsNames.indexOf(username)).password.equals(password))
            System.out.println("password is incorrect.");
        else {
            ADMIN admin = SnappFood.Admins.get(SnappFood.AdminsNames.indexOf(username));
            b=true;
            System.out.println("Logged in successfully.");
            SnappFood.nowAdmin=admin;
            SnappFood.nowUser=null;
        }
        return b;
    }
    public static void Logout(){
        if(SnappFood.nowAdmin==null && SnappFood.nowUser==null) System.out.println("invalid command!.(no one had logged in)");
        else{
            SnappFood.nowUser=null;
            SnappFood.nowAdmin=null;
            System.out.println("Logged out successfully.");
        }
    }
    public static void restorePassForUser(String username,String word){
        int id=SnappFood.UsersNames.indexOf(username);
        SnappFood.Users.get(id).forgotPassword(word);
    }
    public static void restorePassForAdmin(String username,String word){
        int id=SnappFood.AdminsNames.indexOf(username);
        SnappFood.Admins.get(id).forgotPassword(word);
    }
}
