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
        System.out.println("Would you like to play? (yes/no)");
        String newGameOption = TextParser.GetInput();
        String CurrentLocation = "Current location";
        if(newGameOption.equals("yes")) {
            clearScreen();
            gameStart();
//            Player player = JsonReader.getPlayer();
            while(runGame) {
                // Have game logic here
                System.out.println(CurrentLocation);
                String[] gameCommands = TextParser.ParseInput();
            }

        } else {
            quitGame();
        }
    }


    /*
    Monster zombie = getCharacter(Zombie);
     */

}