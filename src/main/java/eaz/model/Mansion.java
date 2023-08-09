package eaz.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mansion {
    private Location[] locations;
    private Item[] items;
    private Character[] characters;
    private Location currentLocation;
    private String currentLocationName  = "Foyer";
    private Player player = new Player();
    private Map<String, Location> locationMap;

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
                    }
                    break;
                }
            }
        }
        return newLocation;
    }

    public String pickUpItem(String itemName){
        Location currentLocation = getCurrentLocation();
        List<String> inventory = player.getInventory();
        String result = null;

        // check if the item is in the current location's item list
        if (getCurrentLocation().getItems().contains(itemName)){
            currentLocation.getItems().remove(itemName);  // remove the item from the location
            inventory.add(itemName);  // add the item to the player's inventory
            result = "You picked up the " + itemName + ".";
        } else{
            result = "There is no " + itemName + " here.";
        }
        return result;
    }

    public String dropItem(String itemName){
        Location currentLocation = getCurrentLocation();
        List<String> inventory = player.getInventory();
        String result = null;

        // check if the item is in player inventory
        if (inventory.contains(itemName)){
            inventory.remove(itemName);  // remove the item from the player's inventory
            currentLocation.getItems().add(itemName);
            result = "You dropped the " + itemName + ".";
        } else{
            result = "You don't have " + itemName + " in your inventory.";
        }
        return result;
    }


}