import java.util.ArrayList;

public class SaveAndLoadFromArrayLists {
    public SaveAndLoadFromArrayLists(){

    }
    public void loadAllTheInformationFromJson(){
        JsonFileReader<USER> userJsonDataReader = new JsonFileReader<>(USER.class,"users.json");
        JsonFileReader<ADMIN> adminJsonDataReader = new JsonFileReader<>(ADMIN.class,"admins.json");
        JsonFileReader<RESTURANT> resturantJsonDataReader = new JsonFileReader<>(RESTURANT.class,"resturants.json");
        SnappFood.AllResturants = new ArrayList<>();
        SnappFood.AllResturants = (ArrayList<RESTURANT>) resturantJsonDataReader.readJsonFile();
        SnappFood.Users = new ArrayList<>();
        SnappFood.Admins = new ArrayList<>();
        SnappFood.Admins = (ArrayList<ADMIN>) adminJsonDataReader.readJsonFile();
        for(ADMIN x : SnappFood.Admins){
            SnappFood.AdminsNames.add(x.username);
        }
        SnappFood.Users = (ArrayList<USER>) userJsonDataReader.readJsonFile();
        for(USER x : SnappFood.Users){
            SnappFood.UsersNames.add(x.username);
        }
    }
    public void writeAllTheInformationToJson(){
        JsonDataWriter<USER> userJsonDataWriter = new JsonDataWriter<>(SnappFood.Users,"users.json");
        JsonDataWriter<ADMIN> adminJsonDataWriter = new JsonDataWriter<>(SnappFood.Admins,"admins.json");
        JsonDataWriter<RESTURANT> resturantJsonDataWriter = new JsonDataWriter<>(SnappFood.AllResturants,"resturants.json");
        userJsonDataWriter.clearJsonFile("users.json");
        resturantJsonDataWriter.clearJsonFile("resturants.json");
        adminJsonDataWriter.clearJsonFile("admins.json");
        resturantJsonDataWriter.saveToJson();
        userJsonDataWriter.saveToJson();
        userJsonDataWriter.deleteLines("users.json");
        adminJsonDataWriter.saveToJson();
        adminJsonDataWriter.deleteLines("admins.json");
        resturantJsonDataWriter.deleteLines("resturants.json");
    }
}
