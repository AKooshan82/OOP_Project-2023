import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader<T> {
    private Class<T> targetType;
    private String filePath;

    public JsonFileReader(Class<T> targetType, String filePath) {
        this.targetType = targetType;
        this.filePath = filePath;
    }

    public List<T> readJsonFile() {
        List<T> dataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Gson gson = new GsonBuilder().create();
            Type listType = TypeToken.getParameterized(ArrayList.class, targetType).getType();
            dataList = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException | JsonIOException e) {
            System.err.println("Error: Invalid JSON format or data in the file.");
            e.printStackTrace();
        }

        return dataList;
    }
}
