package eaz.model;

import eaz.controller.TextParser;
import eaz.view.ViewMain;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static eaz.model.JsonReader.*;


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
        ViewMain viewMain = new ViewMain();

        System.out.println("Would you like to play? (yes/no)");
        String newGameOption = TextParser.getInput();
//        LocationData locationData = new LocationData();
        mansion = JsonReader.readMansion();
//        locations = JsonReader.readLocations();
        if (newGameOption.equals("yes")) {
            viewMain.clearScreen();
            viewMain.gameStart();
            while (runGame) {
                // Have game logic here
                viewMain.loopDisplay(name, health, inventory, mansion);
                String[] gameCommands = TextParser.parseInput(mansion);
                System.out.println();
                viewMain.doubleLine();
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