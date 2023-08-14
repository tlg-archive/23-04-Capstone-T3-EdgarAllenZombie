package eaz.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Item {
    @Expose
    private String type;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String location;
    @Expose
    private int modifier;

    
    // public getters
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getModifier() {
        return modifier;
    }

    




    // item functions:
    public void getItem(String itemName, Location currentLocation, List<String> inventory){
        String result;

        // check if the item is in the current location's item list
        if (currentLocation.getItems().contains(itemName)){
            currentLocation.getItems().remove(itemName);  // remove the item from the location
            inventory.add(itemName);  // add the item to the player's inventory
            result = "You picked up the " + itemName + ".";
        } else{
            result = "There is no " + itemName + " here.";
        }
        System.out.println(result);
    }

    public void leaveItem(String itemName, Location currentLocation, List<String> inventory){
        String result;

        // check if the item is in player inventory
        if (inventory.contains(itemName)){
            inventory.remove(itemName);  // remove the item from the player's inventory
            currentLocation.getItems().add(itemName);
            result = "You dropped the " + itemName + ".";
        } else{
            result = "You don't have " + itemName + " in your inventory.";
        }
        System.out.println(result);
    }

    // public wrapper class for all items:
    public static class ItemList{
        private Item[] items;

        public Item[] getItems() {
            return items;
        }
    }

}

