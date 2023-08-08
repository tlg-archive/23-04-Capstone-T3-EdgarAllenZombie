package eaz.model;

import eaz.view.StoryText;

import java.io.IOException;


public class Look {
    private static String item;

    public static void look(String noun, Location location) throws IOException {
        StoryText.printDoubleLine();
        if (noun.equals("room")) {
            System.out.println(location.getDescription());
        } else {
            System.out.println("look at " + noun);
        }
        // use that user input to call JsonReader getItem function and retrieve nested description

    }

}