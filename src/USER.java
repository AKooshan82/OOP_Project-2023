public class USER {
    String username;
    String securityWord;
    String password;
    USER(String username,String password){
                this.username=username;
                this.password=password;


    }
    static boolean UserRegisterChecker(String username,String password){
        boolean b=false;
        if(!username.matches("^\\d*[a-zA-Z][a-zA-Z0-9]*$")) {System.out.println("username must be numbers or letters and should have at least 1 letter.");}
        else if(SnappFood.UsersNames.contains(username)) System.out.println("username already exists.");
        else {
            if(password.length()<8) System.out.println("password must contains at least 8 characters.");
            else if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")){
                if(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")) System.out.println("password must contains at least one number.");
                else if(password.contains("//s")) System.out.println("white spaces don’t allowed in password.");
                else if(password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,20}$")) System.out.println("password must contains at least one lower case alphabet.");
                else if(password.matches("^(?=.*[a-z])(?=.*[0-9])(?=\\S+$).{8,20}$")) System.out.println("password must contains at least one upper case alphabet.");
            }
            else{b=true;}
        }
        return b;
    }
    void forgotPassword(String word){
        if(!this.securityWord.equals(word)) System.out.println("the answer is wrong.");
        else{
            System.out.println("your password is : "+this.password);
        }
    }
}
