package Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonReader {

    public static String currentLocationName = "Foyer";

    public static LocationData getLocation() throws IOException {
        return readJson(LocationData.class);
    }

    public static <T> T readJson(Class<T> clazz) throws IOException {
        Gson gson = new Gson();
        String filename = "src/main/resources/JsonObjects.json";
        try (Reader reader = new FileReader(filename)) {
            return gson.fromJson(reader, clazz);
        }
    }

    public static void move(String noun) throws IOException {
        LocationData locationData = getLocation();
        for (Locations location : locationData.locations) {
            if (location.name.equals(currentLocationName)) {
                if (location.directions.has(noun)) {
                    currentLocationName = location.directions.get(noun).getAsString();
                    System.out.println("You have moved to " + currentLocationName + ".");
                    return;
                }
            }
        }
        System.out.println("You can't go that way.");
    }

    public static Locations getLocationByName(String currentLocationName) throws IOException {
//        return readJson(LocationData.class);
        LocationData locationData = readJson(LocationData.class);
        for(Locations location : locationData.locations) {
            if(location.name.equalsIgnoreCase(currentLocationName)){
                return location;
            }
        }
        return null;
    }

    public static String getCurrentLocationName() {
        return currentLocationName;
    }

    static public class LocationData {
        Locations[] locations;
    }

    static public class Locations {
        String name;
        JsonObject directions;
        public String description;
        String[] items;
    }
}
