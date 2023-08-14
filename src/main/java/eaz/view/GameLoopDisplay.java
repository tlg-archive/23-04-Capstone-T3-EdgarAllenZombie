package eaz.view;

import eaz.controller.EAZ;
import eaz.model.Character;
import eaz.model.Location;
import eaz.model.Mansion;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


class GameLoopDisplay {
    GeneralViewItems genItems = new GeneralViewItems();
    String doubleLines = genItems.doubleLines;
    String green = genItems.green;
    String colorReset = genItems.colorReset;
    String red = genItems.red;


    void displayPlayerStats(String name, int health, List<String> inventory){
        System.out.println("Name: " + name);
        System.out.println("Your health " + health);
        displayPlayerInventory(inventory);
    }
    void displayPlayerInventory(List<String> inventory){
        System.out.println("Your inventory items are: " + inventory);
    }

    void textHelp(){
        System.out.printf("%s\nTo control Edgar, use basic commands like '%sGo%s %sNorth%s', '%sGet%s %sKnife%s', '%sLook%s' or '%sSearch%s %sdesk%s'.\n %s\n\n",
                doubleLines, green, colorReset,red, colorReset, green, colorReset,
                red, colorReset, green, colorReset, green, colorReset, red, colorReset, doubleLines);
    }

    void loopDisplayText(String name, int health, List<String> inventory, Mansion mansion){
        inventory = mansion.getPlayer().getInventory();
        name = mansion.getPlayer().getName();
        health = mansion.getPlayer().getHealth();
        displayPlayerStats(name, health, inventory);

        System.out.println(genItems.starLines);
        Location currentLocation = mansion.getCurrentLocation();
        System.out.println("You are currently in: " + currentLocation.getName());
        System.out.println();
        System.out.println(currentLocation.getDescription());
        System.out.println("Available directions are: " + currentLocation.getDirections().keySet());
        System.out.println("Items in the room: " + currentLocation.getItems());
        System.out.println("Creatures in the room: " + Arrays.toString(currentLocation.getCharacters()));

    }

    void characterDialog(Mansion mansion, String name) {
        Location currentLocation = mansion.getCurrentLocation();
        Character[] characters = mansion.getCharacters();
        Random random = new Random();

        // if characters at currentLocation is not null
        if (currentLocation.getCharacters() != null) {
            // for each characterName in currentLocation's list of characters (to leave room for multiple later)
            for (String characterName : currentLocation.getCharacters()) {
                // Iterate through each character in the Character[] in mansion's Characters
                for (Character c : characters) {
                    // if characterName at currentLocation equals the name in Character[]
                    if (characterName.equals(c.getName()) && characterName.equals(name)) {
                        int randIndex = random.nextInt(c.getDialog().length); // pick a random index value from the character's dialog length
                        String randDialog = c.getDialog()[randIndex];  // using the randIndex pick a random dialog to return
                        System.out.println(c.getName() + " says: " + randDialog);
                    }
                }
            }
        }
    }


}   // END OF CLASS