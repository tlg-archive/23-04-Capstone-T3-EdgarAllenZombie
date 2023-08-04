package Controller;
import Model.EAZ;
import View.StoryText;

import java.util.Scanner;

import static Model.EAZ.quitGame;
import static View.StoryText.textHelp;

public class TextParser {

    public static String GetInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a Command: ");
        return scanner.nextLine().toLowerCase();
    }

    public static String[] ParseInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a Command: ");
        String input = scanner.nextLine().toLowerCase();

        // Parse the input
        String[] words = input.split(" ");
        String verb = words[0];
//        String noun = words.length > 1 ? words[1] : "";
        String noun = "";

        // Validate the inputs
        if (words.length > 1){
            noun = words[1];
        }

        // Validate the inputs
        if ( words.length == 1) {
            switch (verb) {
                case "go":
                case "look":
                case "take":
                case "use":
                    verb = words[0];
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
                    verb = "";
                    noun = "";
            }
        }
        else if (words.length == 2 ) {
            switch (noun) {
                case "north":
                case "south":
                case "east":
                case "west":
//                    noun = words[1];
                    break;

                default:
                    System.out.println("Invalid Direction. Try again.");
                    noun = "";
                    break;
            }
        }
        else {
            System.out.println("Sorry, that command is not recognized.  Please use basic commands like " +
                    "'Go North', 'Get Knife', 'Look' or 'Search desk'");
        }
        return new String[]{verb, noun};
    }
}
