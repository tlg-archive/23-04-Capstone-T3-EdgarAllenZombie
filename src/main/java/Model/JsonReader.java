package Model;

import Controller.Location;
import com.google.gson.Gson;
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
    public static <T> T readJson(String filename, Class<T> clazz) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filename)) {
            // Parse the JSON data into a JsonObject
           return gson.fromJson(reader, clazz);
        }
    }
}


class Directions {
    Map<String, String> directions;
}

class Locations {
    String name;
    Directions directions;
    String description;
    String[] items;
}

class LocationData {
    Locations[] locations;
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