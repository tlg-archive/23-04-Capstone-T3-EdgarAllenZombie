package eaz.view;

import eaz.controller.EAZ;
import eaz.model.Character;
import eaz.model.Location;
import eaz.model.Mansion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class GameLoopDisplay {
    GeneralViewItems genItems = new GeneralViewItems();
    String doubleLines = genItems.doubleLines;
    String green = genItems.green;
    String colorReset = genItems.colorReset;
    String red = genItems.red;
    String white = genItems.white;
    String yellow = genItems.yellow;
    String purple = genItems.purple;
    String cyan = genItems.cyan;

    void displayPlayerStats(String name, int health, List<String> inventory){
        System.out.println(yellow + "Player Information:");
        System.out.println(green + doubleLines);
        System.out.println(green + "Name: " + yellow + name);
        System.out.println(green + "Your Health: " + yellow + health);
        displayPlayerInventory(inventory);
        System.out.println(green + doubleLines);
    }
    void displayPlayerInventory(List<String> inventory){
        System.out.println(green + "Your inventory items are: " + yellow + inventory);
    }

    void textHelp(){
        System.out.printf("\nTo control Edgar, use basic commands like '%sGo%s %sNorth%s', '%sGet%s %sKnife%s', '%sLook%s' or '%sSearch%s %sdesk'.\n %s\n\n",
                green, colorReset,red, colorReset, green, colorReset,
                red, colorReset, green, colorReset, green, colorReset, red, colorReset);
    }

    void loopDisplayText(String name, int health, List<String> inventory, Mansion mansion){
        inventory = mansion.getPlayer().getInventory();
        name = mansion.getPlayer().getName();
        health = mansion.getPlayer().getHealth();
        displayPlayerStats(name, health, inventory);

        Location currentLocation = mansion.getCurrentLocation();
        System.out.println(yellow + "\nRoom Information:");
        System.out.println(green + doubleLines);
        System.out.println(green + "You are currently in: " + yellow + currentLocation.getName());
        System.out.println(green + "Description: " + yellow + currentLocation.getDescription());
        System.out.println(green + "Available directions are: " + yellow + currentLocation.getDirections().keySet());
        System.out.println(green + "Items in the room: " + yellow + currentLocation.getItems());
        System.out.println(green + "Creatures in the room: " + yellow + Arrays.toString(currentLocation.getCharacters()));
        System.out.println(green + doubleLines + white +"\n");
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