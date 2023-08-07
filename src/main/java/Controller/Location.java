package Controller;

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

}
