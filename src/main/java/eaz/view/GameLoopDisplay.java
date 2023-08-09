package eaz.view;

import eaz.model.Location;
import eaz.model.Mansion;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


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
        System.out.println("In the room, you see: " + currentLocation.getItems());


    }

}   // END OF CLASS