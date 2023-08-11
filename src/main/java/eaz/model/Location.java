package eaz.model;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Map;

public class Location {
    private static Location[] locations;

    @Expose
    private String name;
    @Expose
    private Map<String, String> directions;
    @Expose
    private String description;
    @Expose
    private List<String> items;
    @Expose
    private String[] characters;


    public static Location[] getLocations() {
        return locations;
    }

    public static void setLocations(Location[] locations) {
        Location.locations = locations;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return items;
    }
    public String[] getCharacters(){
        return characters;
    }
}
