package eaz.controller;

import eaz.model.*;
import eaz.view.GeneralViewItems;
import eaz.view.ViewMain;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class TextParser {
    public static GeneralViewItems genItems = new GeneralViewItems();

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a Command: ");
        return scanner.nextLine().toLowerCase();
    }

    public static String[] parseInput(Mansion mansion) throws IOException {
        String[] words = getInput().split(" ");
        String verb = "";
        String noun = "";
        verb = words[0];
        noun = words.length > 1 ? words[1] : "";

        if (words.length >= 2) {
            noun = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        }
        return new String[]{verb, noun};
    }

    public static void handleInput(Mansion mansion, String verb, String noun) throws IOException {
        ViewMain viewMain = new ViewMain();
        Player player = mansion.getPlayer();
        Item item = new Item();
        EAZ eaz = new EAZ();
        Combat combat = new Combat();
        // Validate the inputs
        switch (verb) {
            case "use":
                // For look, take, and use commands, keep verb as is, and reset the noun to an empty string
                noun = "";
                System.out.println("Use feature not implemented yet");
                break;
            case "help":
            case "info":
                viewMain.textHelp();
                break;
            case "inventory":
                viewMain.displayPlayerInventory(player.getInventory());
                break;
            case "look":
            case "search":
                if ("inventory".equals(noun)) {
                    viewMain.displayPlayerInventory(player.getInventory());
                } else {
                    mansion.lookAtItem(noun);
                }
                break;
            case "heal":
                if (noun.equals("player") || noun.equals("")) {
                    player.increaseHealth(10);
                }
                break;
            case "go":
            case "move":
                mansion.move(noun);
                viewMain.clearScreen();
                viewMain.loopDisplay(player.getName(), player.getHealth(), player.getInventory(), mansion);
                break;
            case "take":
            case "get":
                mansion.pickUpItem(noun);
                viewMain.clearScreen();
                viewMain.loopDisplay(player.getName(), player.getHealth(), player.getInventory(), mansion);
                break;
            case "drop":
            case "leave":
                mansion.dropItem(noun);
                viewMain.clearScreen();
                viewMain.loopDisplay(player.getName(), player.getHealth(), player.getInventory(), mansion);
                break;
            case "talk":
            case "speak":
                viewMain.charDialog(mansion, noun);
                break;
            case "attack":
            case "fight":
            case "hit":
                mansion.fight(noun);
                break;
            case "off":
                EAZ.backgroundMusic.stop();
                EAZ.playFX = false;
                break;
            case "on":
                EAZ.backgroundMusic.play("music");
                EAZ.playFX = true;
                break;
            case "map":
                //viewMain.clearScreen();
                genItems.printTextFile("textFiles/Castle_Map.txt", genItems.green);
                //genItems.pauseScreen();
                //viewMain.clearScreen();
                //viewMain.loopDisplay(player.getName(), player.getHealth(), player.getInventory(), mansion);
            case "quit":
                break;
            default:
                System.out.println(genItems.red + "Invalid command. Try again." + genItems.white);
                viewMain.textHelp();
                verb = "";
                noun = "";
                break;
        }
    }
}

