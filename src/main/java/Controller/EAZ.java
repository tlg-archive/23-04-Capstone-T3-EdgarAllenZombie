package Controller;

import java.util.Scanner;
import Controller.StoryText;

import static Controller.StoryText.textHelp;

public class EAZ {

    public static boolean runGame = true;


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
        if(newGameOption.equals("yes")) {
            while(runGame) {
                // Have game logic here
                String input = TextParser.GetInput();
                if(input.equals("help")){
                    textHelp();
                }

            }

        } else {
            quitGame();
        }
    }


}