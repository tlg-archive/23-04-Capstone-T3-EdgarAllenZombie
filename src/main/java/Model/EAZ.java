package Model;

import Controller.TextParser;

import java.io.IOException;
import java.util.Scanner;

import static Model.JsonReader.getCurrentLocationName;
import static View.StoryText.clearScreen;
import static View.StoryText.gameStart;

public class EAZ {

    public static boolean runGame = true;


    public static void quitGame() {
        System.out.println("Are you sure you want to quit? (yes/no)");
        String quitResponse = TextParser.GetInput();
        if (quitResponse.equals("yes")) {
            runGame = false;
        } 
    }

    public static void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play? (yes/no)");
        String newGameOption = TextParser.GetInput();
        if (newGameOption.equals("yes")) {
            clearScreen();
            gameStart();
//            Player player = JsonReader.getPlayer();
            while (runGame) {
                // Have game logic here
                System.out.println(getCurrentLocationName());
                String[] gameCommands = TextParser.ParseInput();
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