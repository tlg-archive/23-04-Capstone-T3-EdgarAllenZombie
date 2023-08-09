package eaz.controller;

import eaz.model.*;
import eaz.view.ViewMain;

import java.io.IOException;
import java.util.Scanner;

public class TextParser {

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a Command: ");
        return scanner.nextLine().toLowerCase();
    }

    public static String[] parseInput(Mansion mansion) throws IOException {
        String[] words = getInput().split(" ");
        ViewMain viewMain = new ViewMain();
        Player player = mansion.getPlayer();

        String verb = words[0];
        String noun = words.length > 1 ? words[1] : "";

        // Validate the inputs
        if (words.length == 1) {
            switch (verb) {
                case "use":
                    // For look, take, and use commands, keep verb as is, and reset the noun to an empty string
                    noun = "";
                    break;
                case "help":
                case "info":
                    viewMain.textHelp();
                    break;
                case "quit":
                case "exit":
                case "stop":
                    EAZ.quitGame();
                    break;
                case "inventory":
                    viewMain.displayPlayerInventory(player.getInventory());
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
                    viewMain.textHelp();
                    verb = "";
                    noun = "";
                    break;
            }
        } else if (words.length == 2) {
            switch(verb){
                case "look":
                case "search":
                    if ("inventory".equals(noun)) {
                        viewMain.displayPlayerInventory(player.getInventory());
                    } else {
                        Look.look(noun, mansion.getCurrentLocation());
                    }
                    break;
                case "go":
                case "move":
                    mansion.move(noun);
                    break;
                case "take":
                case "get":
                    mansion.pickUpItem(noun);
                    break;
                default:
                    System.out.println("Sorry, that command is not recognized. Please use basic commands like " +
                            "'Go North', 'Get Knife', 'Look' or 'Search desk'");
                    verb = "";
                    noun = "";
            }

        }
        return new String[]{verb, noun};
    }
}
