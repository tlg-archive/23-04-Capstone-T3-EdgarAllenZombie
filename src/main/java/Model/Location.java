package Model;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class Location {
    private String name;
    private String[] directions;
    private String description;
    private String[] items;


    public Location(String name, String[] directions, String description, String[] items) {
        this.name = name;
        this.directions = directions;
        this.description = description;
        this.items = items;
//        this.directions = new HashMap<>();
    }

//    public void addDirection(String direction, String destination) {
//        directions.put(direction.toLowerCase(), destination);
//    }

    // Getters and Setters (You can use Lombok or manually create them if needed)

//    public static void main(String[] args) {
//        // Sample usage to create a location
//        Location location = new Location("Fayer", "This is the starting room.");
//        location.addDirection("north", "north_room");
//        location.addDirection("east", "east_room");
//        location.addDirection("south", "south_room");
//        location.addDirection("west", "west_room");
//
//        Gson gson = new Gson();
//        String locationJson = gson.toJson(location);
//        System.out.println(locationJson);
//    }
}
