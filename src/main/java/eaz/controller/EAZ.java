package eaz.controller;

import eaz.controller.TextParser;
import eaz.model.JsonReader;
import eaz.model.Mansion;
import eaz.model.Player;
import eaz.view.ViewMain;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static eaz.model.JsonReader.*;


public class EAZ {
    private Mansion mansion;
    ViewMain viewMain = new ViewMain();

    public boolean runGame = true;

    public void quitGame() {
        System.out.println("Are you sure you want to quit? (yes/no)");
        String quitResponse = TextParser.getInput();
        if (quitResponse.equals("yes")) {
            runGame = false;
        }
    }

    public void run() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        mansion = JsonReader.readMansion("JsonObjects.json");  // populate the mansion from the JsonObjects.json
        Player player = mansion.getPlayer();
        String inputVerb = "";  // initialization to pull verb out of loop
        String inputNoun = "";  // initialization to pull noun outside of loop


        System.out.println("Would you like to play? (yes/no)");
        String newGameOption = TextParser.getInput();

        if (newGameOption.equals("yes")) {
            viewMain.clearScreen();  // print 40 blank lines to clear the screen
            viewMain.gameStart();    // display the game start text
            while (runGame) {
                // Have game logic here
                viewMain.clearScreen();  // clear screen at the start of each loop
                // check to make sure verb isn't blank (prevents error message on first entry)
                if(!Objects.equals(inputVerb, "")){
                    TextParser.handleInput(mansion, inputVerb, inputNoun);  // pass previous verb and noun into the switch case
                    viewMain.starLine();
                    // if you pick up the grimoire the game ends (currently in development)
                    if(player.getInventory().contains("grimoire")){
                        viewMain.winGame();
                        runGame = false;
                        return;
                    }
                }
                // persistent status text
                viewMain.loopDisplay(player.getName(), player.getHealth(), player.getInventory(), mansion);
                // this loop's input
                String[] gameCommands = TextParser.parseInput(mansion);
                inputVerb = gameCommands[0];
                inputNoun = gameCommands[1];

                // if quit, exit or stop are typed, run quitGame in loop
                if(inputVerb.equals("quit") || inputVerb.equals("exit") || inputVerb.equals("stop")){
                    quitGame();
                }
            }
            scanner.close();  // close scanner from parser
        } else if (newGameOption.equals("no")) {
            quitGame();  // sets rungame to false and closes runGame loop
        } else{
            System.out.println("Would you like to play? (yes/no)");
            newGameOption = TextParser.getInput();
        }
    }
    /*
    Monster zombie = getCharacter(Zombie);
     */

}