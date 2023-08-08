package eaz.controller;

import eaz.model.*;
import eaz.view.StoryText;

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

        String verb = words[0];
        String noun = words.length > 1 ? words[1] : "";

        // Validate the inputs
        if (words.length == 1) {
            switch (verb) {
                case "take":
                case "use":
                    // For look, take, and use commands, keep verb as is, and reset the noun to an empty string
                    noun = "";
                    break;
                case "help":
                    StoryText.textHelp();
                    break;
                case "quit":
                    EAZ.quitGame();
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
                    StoryText.textHelp();
                    verb = "";
                    noun = "";
                    break;
            }
        } else if (words.length == 2) {
            switch(verb){
                case "look":
                    Look.look(noun, mansion.getCurrentLocation());
                    break;
                case "go":
                    mansion.move(noun);
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
