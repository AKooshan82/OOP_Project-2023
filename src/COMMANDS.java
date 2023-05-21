import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum COMMANDS {
    ADD_ADMIN("\\s*(ADD)\\s+(ADMIN)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    ADD_USER("\\s*(ADD)\\s+(USER)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    LOGIN_ADMIN("\\s*(LOGIN)\\s+(ADMIN)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    LOGIN_USER("\\s*(LOGIN)\\s+(USER)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    FORGET_PASSWORD_USER("\\s*(FORGET)\\s+(USER)\\s+(PASSWORD)\\s+(?<username>\\S+)\\s*"),
    FORGET_PASSWORD_ADMIN("\\s*(FORGET)\\s+(ADMIN)\\s+(PASSWORD)\\s+(?<username>\\S+)\\s*"),
    SELECT_RESTAURANT("\\s*(SELECT)\\s+(?<ID>\\d+)\\s*"),
    ADD_RESTAURANT("\\s*(ADD)\\s+(RESTAURANT)\\s+(?<name>\\S+)\\s+(?<types>[a-zA-Z][a-zA-Z ]+[a-zA-Z]$)\\s*"),
    EDIT_RESTUARANT_TYPE("\\s*(EDIT)\\s+(FOODTYPE)\\s+(?<types>[a-zA-Z][a-zA-Z ]+[a-zA-Z]$)\\s*"),
    EDIT_FOOD_NAME("\\s*(EDIT)\\s+(FOOD)\\s+(?<ID>\\d+)\\s+(NAME)\\s+(?<newName>\\S+)\\s*"),
    EDIT_FOOD_PRICE("\\s*(EDIT)\\s+(FOOD)\\s+(?<ID>\\d+)\\s+(PRICE)\\s+(?<newPrice>\\d+)\\s*"),
    ADD_FOOD("\\s*(ADD)\\s+(FOOD)\\s+(?<name>\\S+)\\s+(?<price>\\d+)\\s*"),
    DELETE_FOOD("\\s*(DELETE)\\s+(FOOD)\\s+(?<ID>\\d+)\\s*"),
    DEACTIVE_FOOD("\\s*(DEACTIVE)\\s+(FOOD)\\s+(?<ID>\\d+)\\s*"),
    ACTIVE_FOOD("\\s*(ACTIVE)\\s+(FOOD)\\s+(?<ID>\\d+)\\s*"),
    SELECT_FOOD("\\s*(SELECT)\\s+(FOOD)\\s+(?<ID>\\d+)\\s*"),

    //REMOVE_ADMIN("\\s*REMOVE\\s+ADMIN\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    //REMOVE_USER("\\s*REMOVE\\s+USER\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*"),
    ;
    public String regex;

     COMMANDS(String regex){
        this.regex = regex;
    }

    public static Matcher getMatcher(String input , COMMANDS command){
        Pattern pattern = Pattern.compile(command.regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) return matcher;
        else return null;
    }
}
