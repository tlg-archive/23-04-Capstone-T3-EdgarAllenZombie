package Controller;

import Model.EAZ;
import Model.JsonReader;
import View.StoryText;

import java.io.IOException;
import java.util.Scanner;

public class TextParser {

    public static String GetInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a Command: ");
        return scanner.nextLine().toLowerCase();
    }

    public static String[] ParseInput() throws IOException {
        String[] words = GetInput().split(" ");

        String verb = words[0];
        String noun = words.length > 1 ? words[1] : "";

        // Validate the inputs
        if (words.length == 1) {
            switch (verb) {
                case "look":
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
                    verb = "";
                    noun = "";
                    break;
            }
        } else if (words.length == 2) {
            if (verb.equals("go")) {
                JsonReader.move(noun);
            }
        } else {
            System.out.println("Sorry, that command is not recognized. Please use basic commands like " +
                    "'Go North', 'Get Knife', 'Look' or 'Search desk'");
            verb = "";
            noun = "";
        }
        return new String[]{verb, noun};
    }
}
