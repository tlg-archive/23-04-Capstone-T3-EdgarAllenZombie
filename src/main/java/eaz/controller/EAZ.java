package eaz.controller;

import eaz.model.CopyState;
import eaz.model.MyJsonReader;
import eaz.model.Mansion;
import eaz.model.Player;
import eaz.view.GeneralViewItems;
import eaz.view.Music;
import eaz.view.ViewMain;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class EAZ {
    @SuppressWarnings("FieldCanBeLocal")
    GeneralViewItems genItems = new GeneralViewItems();
    String white = genItems.white;
    String green = genItems.green;

    private Mansion mansion;
    public ViewMain viewMain = new ViewMain();
    public boolean runGame = true;
    public static Music backgroundMusic = new Music("music", "audioFiles/zombies.wav");
    public static boolean playFX = true;

    public void quitGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(white + "Are you sure you want to quit (yes/no)? > ");
        String quitResponse = scanner.nextLine().trim().toLowerCase();
        if (quitResponse.equals("no")) {
            runGame = true;
        } else {
            runGame = false;
        }
    }

    public void run() throws IOException {
        mansion = MyJsonReader.readMansion("saved.json");  // populate the mansion from the JsonObjects.json
        Player player = mansion.getPlayer();
        String inputVerb = "";  // initialization to pull verb out of loop
        String inputNoun = "";  // initialization to pull noun outside of loop


        Scanner scanner = new Scanner(System.in);
        backgroundMusic.setVolume("music", (float) 7/10);
        backgroundMusic.play("music");
        System.out.print("Would you like to play (yes/no)? > ");
        String newGameOption = scanner.nextLine().trim().toLowerCase();
        //String newGameOption = TextParser.getInput();

        if (newGameOption.equals("yes")) {
            viewMain.clearScreen();  // print 60 blank lines to clear the screen
            viewMain.gameStart();    // display the game start text
            while (runGame) {
                // Have game logic here
                viewMain.clearScreen();  // clear screen at the start of each loop
                // check to make sure verb isn't blank (prevents error message on first entry)
                viewMain.loopDisplay(player.getName(), player.getHealth(), player.getInventory(), mansion);
                if(!Objects.equals(inputVerb, "")){
                    TextParser.handleInput(mansion, inputVerb, inputNoun);  // pass previous verb and noun into the switch case
                    //viewMain.starLine();
                    // if you pick up the grimoire the game ends (currently in development)
                    if(player.getInventory().contains("grimoire")){
                        viewMain.winGame();
                        runGame = false;
                        return;
                    }
                }
                // persistent status text
                // this loop's input
                String[] gameCommands = TextParser.parseInput(mansion);
                inputVerb = gameCommands[0];
                inputNoun = gameCommands[1];

                // if quit, exit or stop are typed, run quitGame in loop
                if(inputVerb.equals("quit") || inputVerb.equals("exit")){
                    MyJsonReader.writeMansion(mansion, "saved.json");
                    // CopyState.createSavedMansion(mansion);
                    quitGame();
                    if (!runGame){
                        genItems.clearScreen();
                        System.out.println(green + "Thank you for playing!!!");
                        break;
                    }
                }
            }
            scanner.close();  // close scanner from parser
//        } else if (newGameOption.equals("no")) {
//            quitGame();  // sets rungame to false and closes runGame loop
        } else{
            System.out.println(green + "Thank you for stopping by!" + white);
//            System.out.println("Are you sure you want to quit (yes/no)? >");
//            newGameOption = scanner.nextLine().trim().toLowerCase();
        }
    }
    /*
    Monster zombie = getCharacter(Zombie);
     */

}