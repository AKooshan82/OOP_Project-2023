import java.util.ArrayList;

public class RATING {
    double nowRate;
    ArrayList<USER> usersRated;
    ArrayList<String> usersRatedNames;
    ArrayList<Double> usersRatingAmount;
    RATING(){
        this.nowRate=0;
        this.usersRated=new ArrayList<>();
        this.usersRatedNames=new ArrayList<>();
        this.usersRatingAmount=new ArrayList<>();
    }
    void newRating(USER user,double rate){
        this.nowRate=this.updateNowRate(rate);
        this.usersRated.add(user);
        this.usersRatedNames.add(user.username);
        this.usersRatingAmount.add(rate);
    }
    void editRating(USER user,double rate){
        int index=this.usersRatedNames.indexOf(user.username);
        double preRate=this.usersRatingAmount.get(index);
        double x=this.nowRate*(this.usersRated.size());
        x+=(rate-preRate);
        x/=this.usersRated.size();
        this.nowRate=x;
    }
    double updateNowRate(double n){
        double x=this.nowRate*this.usersRated.size();
        x+=n;
        x/=(double) (this.usersRated.size()+1);
        return x;

    }
    void showAllRatingsForadmin(){
        String s="";
        for (int i = 0; i < this.usersRated.size(); i++) {
            s="username: "+this.usersRatedNames.get(i)+"  *  rate: "+this.usersRatingAmount.get(i);
            System.out.println(s);
        }
    }
    @Override
    public String toString(){
        String s="";
        String roundedNowRate = String.format("%.1f", this.nowRate);
        s+="rating: "+roundedNowRate+"/ 5 "+"("+this.usersRated.size()+" users rated)";
        return s;
    }
}
