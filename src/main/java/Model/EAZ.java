package Model;

import Controller.TextParser;
import View.StoryText;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static Model.JsonReader.*;
import static View.StoryText.clearScreen;
import static View.StoryText.gameStart;

public class EAZ {

    public static boolean runGame = true;


    public static void quitGame() {
        System.out.println("Are you sure you want to quit? (yes/no)");
        String quitResponse = TextParser.GetInput();
        if (quitResponse.equals("yes")) {
            runGame = false;
        } else {
            return;
        }
    }

    public static void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play? (yes/no)");
        String newGameOption = TextParser.GetInput();
        LocationData locationData = new LocationData();
        if (newGameOption.equals("yes")) {
            clearScreen();
            gameStart();
//            Player player = JsonReader.getPlayer();
            while (runGame) {
                // Have game logic here
                StoryText.printDoubleLine();
                System.out.println("You are currently in: " + getCurrentLocationName());
//                StoryText.printSingleLine();
                System.out.println();
                System.out.println(Objects.requireNonNull(getLocationByName(getCurrentLocationName())).description);
                StoryText.printSingleLine();
                System.out.println("Available directions are: " + Objects.requireNonNull(getLocationByName(getCurrentLocationName())).directions.keySet());
                StoryText.printSingleLine();
                System.out.println("In the room, you see: " + Arrays.toString(Objects.requireNonNull(getLocationByName(getCurrentLocationName())).items));
                String[] gameCommands = TextParser.ParseInput();
                StoryText.printStarLine();

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