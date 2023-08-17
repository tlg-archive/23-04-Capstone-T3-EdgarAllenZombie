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
    private String currentLocationName  = "Foyer";
    @Expose
    private Player player;
    private Map<String, Location> locationMap;

    GeneralViewItems genItems = new GeneralViewItems();

    public Location getLocationByName(String name) {
        return getLocationMap().get(name);
    }


    // TODO: 8/8/2023 add additional fields for currentlocations, inventory, health, enemies, etc
    // TODO: 8/8/2023 define methods for manipulating the state


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
                    System.out.println(genItems.red + "You didn't enter a valid location to move to!!\n" + genItems.white);
                    genItems.pauseScreen();
                }
            }
        }
        return newLocation;
    }

    public void pickUpItem(String itemName) {
        Item item = new Item();
        // set the parameters from mansion to use in getItem
        itemName = itemName;
        Location currentLocation = getCurrentLocation();
        List<String> inventory = player.getInventory();
        // call getItem and pass mansion variables
        item.getItem(itemName, currentLocation, inventory);
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
        Combat combat = new Combat();
        Location currentLocation = getCurrentLocation();
        Character[] character = getCharacters();
        player = getPlayer();

        combat.combat(target, currentLocation,  character, player);
    }

    public void lookAtItem(String itemName){
        Location currentLocation = getCurrentLocation();
        List<String> inventory = player.getInventory();
        if (currentLocation.getItems().contains(itemName)) {
            iterateItem(itemName);
        } else if(inventory.contains(itemName)){
            System.out.println(genItems.purple + "Your looking at " + itemName + " in your inventory\n" + genItems.white);
            iterateItem(itemName);
        }
        else if (itemName != "" && itemName != null){
            System.out.println(genItems.red + "You can not look at " + itemName + " it's not in this room or your inventory!!!\n" + genItems.white);
        } else {
            System.out.println(genItems.red + "You didn't enter a valid item to look at!!!\n" + genItems.white);
        }
    }

    public void iterateItem(String itemName) {
        for (Item curItem : items) {
            if(curItem.getName().equalsIgnoreCase(itemName)) {
                System.out.println(curItem.getDescription());
            }
        }
    }
}