package eaz.model;
import com.google.gson.*;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CopyState {


    public static void createSavedMansion(Mansion mansion) throws IOException {

        try (JsonWriter writer = new JsonWriter(new FileWriter("saved.json"))) {
            // cast the retrieved mansion.getLocations into a Json Array
            JsonArray savedLocations = (JsonArray) new Gson().toJsonTree(mansion.getLocations());
            // cast the retrieved mansion.getCharacters into a Json Array
            JsonArray savedCharacters = (JsonArray) new Gson().toJsonTree(mansion.getCharacters());
            // cast the retrieved mansion.getItems into a Json Array
            JsonArray savedItems = (JsonArray) new Gson().toJsonTree(mansion.getItems());

            // creating a new Json object
            JsonObject mergedArrays = new JsonObject();

            // Appending all of our created JsonArrays into our newly created JsonObject
            mergedArrays.add("locations",savedLocations);
            mergedArrays.add("characters", savedCharacters);
            mergedArrays.add("items", savedItems);

            // converting our mergedArrays JsonObject into a String type
            String finalJs = new Gson().toJson(mergedArrays);
            // writing our String type mergedArrays JsonObject to our new saved.json file
            writer.jsonValue(finalJs);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


