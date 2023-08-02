package Controller;

import java.util.Scanner;

public class EAZ {

    public static boolean runGame;


    public static void quitGame() {
        System.out.println("Are you sure you want to quit?");
        String quitResponse = TextParser.GetInput();
        if(quitResponse == "yes") {
            runGame = false;
        } else {
            return;
        }
    }

    public static void run() {
        System.out.println("Would you like to play?");
        String newGameOption = TextParser.GetInput();
        if(newGameOption == "yes") {
            while(runGame) {
                // Have game logic here
            }

        } else {
            quitGame();
        }
    }
}