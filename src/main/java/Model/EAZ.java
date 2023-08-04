package Model;

import Controller.TextParser;

import static View.StoryText.*;

public class EAZ {

    public static boolean runGame = true;


    public static void quitGame() {
        System.out.println("Are you sure you want to quit? (yes/no)");
        String quitResponse = TextParser.GetInput();
        if(quitResponse == "yes") {
            runGame = false;
        } else {
            return;
        }
    }

    public static void run() {
        System.out.println("Would you like to play? (yes/no");
        String newGameOption = TextParser.GetInput();
        if(newGameOption.equals("yes")) {
            clearScreen();
            gameStart();
//            Player player = JsonReader.getPlayer();
            while(runGame) {
                // Have game logic here
                String input = TextParser.GetInput();
            }

        } else {
            quitGame();
        }
    }


    /*
    Monster zombie = getCharacter(Zombie);
     */

}