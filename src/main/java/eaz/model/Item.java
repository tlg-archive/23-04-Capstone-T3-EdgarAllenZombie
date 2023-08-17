package eaz.model;

import com.google.gson.annotations.Expose;
import eaz.controller.EAZ;
import eaz.view.GeneralViewItems;
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

    GeneralViewItems genItems = new GeneralViewItems();

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

        // check if the item is in the current location's item list
        if (currentLocation.getItems().contains(itemName)){
            currentLocation.getItems().remove(itemName);  // remove the item from the location
            inventory.add(itemName);  // add the item to the player's inventory
            System.out.println(genItems.purple + itemName + "has been added to your inventory!\n" + genItems.white);
            if (EAZ.playFX){
                Music getItemFX = new Music("fx", "audioFiles/getItem.wav");
                getItemFX.play("fx");
            }
        } else if (itemName != ""  && itemName != null) {
            System.out.println(genItems.red + itemName.toUpperCase() + " is not in this Room!!!\n" + genItems.white);
        } else {
            System.out.println(genItems.red + "You didn't enter a valid item to get!!!\n" + genItems.white);
        }
    }

    public void leaveItem(String itemName, Location currentLocation, List<String> inventory){

        // check if the item is in player inventory
        if (inventory.contains(itemName)){
            inventory.remove(itemName);  // remove the item from the player's inventory
            currentLocation.getItems().add(itemName);
            System.out.println(genItems.purple + "You dropped the " + itemName + ".\n" + genItems.white);
            if (EAZ.playFX){
                Music dropItemFX = new Music("fx", "audioFiles/dropItem.wav");
                dropItemFX.play("fx");
            }
        } else if (itemName != ""  && itemName != null) {
            System.out.println(genItems.red + "You don't have " + itemName.toUpperCase() + " in your inventory!!!\n" + genItems.white);
        } else {
            System.out.println(genItems.red + "You didn't enter a valid item to drop!!!\n" + genItems.white);
        }
    }

}

