package eaz.model;

import com.google.gson.annotations.Expose;
import eaz.controller.EAZ;
import eaz.view.Music;

import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Item {
    private static Item[] items;
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
    public Item[] getItems() {
        return items;
    }

    public static void setItems(Item[] items) {
        Item.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void setItems(){
        Item.items = items;
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
            if (EAZ.playFX){
                Music getItemFX = new Music("fx", "audioFiles/getItem.wav");
                getItemFX.play("fx");
            }
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
            if (EAZ.playFX){
                Music dropItemFX = new Music("fx", "audioFiles/dropItem.wav");
                dropItemFX.play("fx");
            }
        } else{
            result = "You don't have " + itemName + " in your inventory.";
        }
        System.out.println(result);
    }


}

