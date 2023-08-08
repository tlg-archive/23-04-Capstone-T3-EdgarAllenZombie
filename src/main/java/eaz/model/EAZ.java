package eaz.model;

import eaz.controller.TextParser;
import eaz.view.StoryText;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static eaz.model.JsonReader.*;
import static eaz.view.StoryText.*;

public class EAZ {
    private static Mansion mansion;

    public static boolean runGame = true;
    public static String name = "Edgar Allen Zombie";
    public static int health = 25;
    public static String[] inventory = {"knife", "bat"};



    public static void quitGame() {
        System.out.println("Are you sure you want to quit? (yes/no)");
        String quitResponse = TextParser.getInput();
        if (quitResponse.equals("yes")) {
            runGame = false;
        }
    }

    public static void run() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play? (yes/no)");
        String newGameOption = TextParser.getInput();
//        LocationData locationData = new LocationData();
        mansion = JsonReader.readMansion();
//        locations = JsonReader.readLocations();
        if (newGameOption.equals("yes")) {
            clearScreen();
            gameStart();
            while (runGame) {
                // Have game logic here
                StoryText.displayPlayerStats(name, health, inventory);
                StoryText.printStarLine();
                Location currentLocation = mansion.getCurrentLocation();
                System.out.println("You are currently in: " + currentLocation.getName());
                System.out.println();
                System.out.println(currentLocation.getDescription());
                System.out.println("Available directions are: " + currentLocation.getDirections().keySet());
                System.out.println("In the room, you see: " + currentLocation.getItems());
                String[] gameCommands = TextParser.parseInput(mansion);
                System.out.println();
                System.out.println("You take 3 damage");
                health -= 3;
                System.out.println();
                StoryText.printDoubleLine();
            }
            scanner.close();
        } else {
            quitGame();
        }

    }


    /*
    Monster zombie = getCharacter(Zombie);
     */

}