import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonDataWriter<T> {
    private List<T> dataList;
    private String filePath;

    public JsonDataWriter(List<T> dataList, String filePath) {
        this.dataList = dataList;
        this.filePath = filePath;
    }

    public void saveToJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonArray jsonArray = new JsonArray();

            if (Files.exists(Paths.get(filePath))) {
                JsonElement existingData = gson.fromJson(Files.newBufferedReader(Paths.get(filePath)), JsonElement.class);

                if (existingData.isJsonArray()) {
                    jsonArray = existingData.getAsJsonArray();
                } else if (existingData.isJsonObject()) {
                    System.err.println("Warning: JSON file contains an object instead of an array.");
                    System.err.println("Existing data will be preserved, but make sure to handle it appropriately.");
                    jsonArray = existingData.getAsJsonObject().getAsJsonArray("data");
                }
            }

            for (T element : dataList) {
                jsonArray.add(gson.toJsonTree(element));
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("data", jsonArray);
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(jsonObject, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteLines(String filePath) {
        try {
            File inputFile = new File(filePath);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            int lineNumber = 1;
            int totalLines = getTotalLines(filePath);

            while ((line = reader.readLine()) != null) {
                if (lineNumber == 1 || lineNumber == totalLines || (lineNumber == 2 && line.length() > 10)) {
                    if (lineNumber == 2) {
                        line = line.substring(10);
                    }
                    lineNumber++;
                    continue;
                }
                writer.write(line);
                writer.newLine();
                lineNumber++;
            }

            reader.close();
            writer.close();

            // Delete the original file
            inputFile.delete();

            // Rename the temporary file to the original file name
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int getTotalLines(String filePath) throws IOException {
        int totalLines = 0;
        try (LineNumberReader reader = new LineNumberReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                totalLines++;
            }
        }
        return totalLines;
    }
}
