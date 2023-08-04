package Model;

import Controller.Location;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import javax.xml.stream.events.Characters;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class JsonReader {
    public static <T> T readJson(Class<T> clazz) throws IOException {
        Gson gson = new Gson();
        String filename = "src/main/resources/JsonObjects.json";
        try (Reader reader = new FileReader(filename)) {
            // Parse the JSON data into a JsonObject
           return gson.fromJson(reader, clazz);
        }
    }

    public static LocationData getLocation() throws IOException {
        return readJson(LocationData.class);
    }

    public static void main(String[] args) throws IOException {
        LocationData locationData = JsonReader.getLocation();
        System.out.println("First location is: " + locationData.locations[0].name);
//        System.out.println("Second location is: " + locationData.locations[1].name);
//        System.out.println("First location directions is: " + locationData.locations[0].directions);
//        System.out.println("Second location directions is: " + locationData.locations[1].directions);
//
    }
}


class LocationData {
    Locations[] locations;
}

class Locations {
    String name;
    JsonObject directions;
    String description;
    String[] items;

}


class Character{
    String type;
    String name;
    int health;
    int damage;
    String description;
}
class CharacterData{
    List<Character> characters;
}