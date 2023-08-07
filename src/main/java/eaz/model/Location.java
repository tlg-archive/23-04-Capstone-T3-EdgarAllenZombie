package eaz.model;

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
