package eaz.model;

import com.google.gson.annotations.Expose;
import eaz.controller.EAZ;
import eaz.view.GeneralViewItems;
import eaz.view.Music;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mansion {

    @Expose
    private Location[] locations;
    @Expose
    private Item[] items;
    @Expose
    private Character[] characters;
    private Location currentLocation;
    @Expose
    private String currentLocationName  = "Foyer";
    @Expose
    private Player player;
    private Map<String, Location> locationMap;

    GeneralViewItems genItems = new GeneralViewItems();

    public Location getLocationByName(String name) {
        return getLocationMap().get(name);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

// TODO: 8/8/2023 add additional fields for currentlocations, inventory, health, enemies, etc
    // TODO: 8/8/2023 define methods for manipulating the state


    public void setCurrentLocationName(String currentLocationName) {
        this.currentLocationName = currentLocationName;
    }

    public Location[] getLocations() {
        return locations;
    }

    public Item[] getItems() {
        return items;
    }

    public Character[] getCharacters() {
        return characters;
    }

    public synchronized Location getCurrentLocation() {
        if (currentLocation == null ){
            currentLocation = getLocationMap().get(currentLocationName);
        }
        return currentLocation;
    }

    private Map<String, Location> getLocationMap() {
        if (locationMap == null) {
            locationMap = Arrays.stream(locations)
                    .collect(Collectors.toMap(Location::getName,Function.identity()));
        }
        return locationMap;
    }

    public String getCurrentLocationName() {
        return currentLocationName;
    }

    public Player getPlayer() {
        return player;
    }


    public Location move(String noun) throws IOException {
        Location newLocation = null;

        for (Location loc : locations) {
            if (loc.getName().equals(currentLocationName)) {
                String newLocationName = loc.getDirections().get(noun);
                if (newLocationName != null) {
                    newLocation = getLocationByName(newLocationName);
                    if (newLocation != null) {
                        this.currentLocation = newLocation;
                        this.currentLocationName = newLocationName;
                        if (EAZ.playFX){
                            Music moveRoomFX = new Music("fx", "audioFiles/moveRoom.wav");
                            moveRoomFX.play("fx");
                        }
                    }
                    break;
                } else {
                    System.out.println(genItems.red + "Error: You didn't enter a valid location to move to!! Please try again.\n" + genItems.white);
                    //genItems.pauseScreen();
                }
            }
        }
        return newLocation;
    }

    public List<String> pickUpItem(String itemName) {
        Item item = new Item();
        // set the parameters from mansion to use in getItem
        itemName = itemName;
        Location currentLocation = getCurrentLocation();
        List<String> inventory = player.getInventory();
        // call getItem and pass mansion variables
        item.getItem(itemName, currentLocation, inventory);
        return inventory;
    }


    public void playMusic(String musicType){
        if (musicType.equals("music")) {
            EAZ.backgroundMusic.play("music");
        } else {
            EAZ.playFX = true;
        }
    }

    public void stopMusic(String musicType){
        if (musicType.equals("music")) {
            EAZ.backgroundMusic.stop();
        } else {
            EAZ.playFX = false;
        }
    }



    public void dropItem(String itemName){
        Item item = new Item();
        // set the parameters from mansion to use in dropItem
        Location currentLocation = getCurrentLocation();
        List<String> inventory = player.getInventory();
        String result = null;
        // call leaveItem and pass mansion variables
        item.leaveItem(itemName, currentLocation, inventory);
    }

    public void fight(String target){
        if (target.length() == 0){
            System.out.println("Please enter who you want to attack!!! Please try again.");
        } else {
            Combat combat = new Combat();
            Location currentLocation = getCurrentLocation();
            Character[] character = getCharacters();
            player = getPlayer();

            combat.combat(target, currentLocation, character, player);
        }
    }

    public String lookAtItem(String itemName){
        // get the current location
        Location currentLocation = getCurrentLocation();
        // get the player inventory
        List<String> inventory = player.getInventory();
        // set a string to save the item description for testing
        String itemDescription = "match not found";
        // if the current location contains the item
        if (currentLocation.getItems().contains(itemName)) {
            //call the iterateItem function
            itemDescription =  iterateItem(itemName);
            // if the player inventory has the item
        } else if(inventory.contains(itemName)) {
            // print a useful statement
            System.out.println(genItems.purple + "Your looking at " + itemName + " in your inventory\n" + genItems.white);
            // call the iterateItem function
           itemDescription = iterateItem(itemName);
        }else{
            // print a helpful statement
            System.out.println(genItems.red + "Error: You didn't enter a valid item to look at!!! Please try again.\n" + genItems.white);
        }
        return itemDescription;
    }
//    public void lookAtItem(String itemName){
//        // get the current location
//        Location currentLocation = getCurrentLocation();
//        // get the player inventory
//        List<String> inventory = player.getInventory();
//        // if the current location contains the item
//        if (currentLocation.getItems().contains(itemName)) {
//            //call the iterateItem function
//            iterateItem(itemName);
//            // if the player inventory has the item
//        } else if(inventory.contains(itemName)) {
//            // print a useful statement
//            System.out.println(genItems.purple + "Your looking at " + itemName + " in your inventory\n" + genItems.white);
//            // call the iterateItem function
//            iterateItem(itemName);
//        }else{
//            // print a helpful statement
//            System.out.println(genItems.red + "Error: You didn't enter a valid item to look at!!! Please try again.\n" + genItems.white);
//        }
//
//    }

// iterate over items in the json file
//    public void iterateItem(String itemName) {
//        // for every item in the items section in the json
//
//        for (Item curItem : items) {
//            // if the current item name equals the itemName
//            if(curItem.getName().equalsIgnoreCase(itemName)) {
//                // print the description
//                System.out.println(curItem.getDescription());
//            }
//        }
//    }
public String iterateItem(String itemName) {
    // for every item in the items section in the json
String itemDescription = "match not found";
    for (Item curItem : items) {
        // if the current item name equals the itemName
        if(curItem.getName().equalsIgnoreCase(itemName)) {
            // print the description
            itemDescription = curItem.getDescription();
            System.out.println(itemDescription);
        }
    }
    return itemDescription;
}
}