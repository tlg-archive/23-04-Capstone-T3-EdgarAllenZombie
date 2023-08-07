package eaz.model;

import eaz.view.StoryText;

import java.io.IOException;

import static eaz.model.JsonReader.*;
import static java.util.Objects.requireNonNull;

public class Look {
    private static String item;

    public static void look(String noun) throws IOException {
        LocationData locationData = new LocationData();
        StoryText.printDoubleLine();
        if (noun.equals("room")){
            System.out.println(requireNonNull(getLocationByName(currentLocationName)).description);
        }else
        System.out.println("look at " + noun);
        // use that user input to call JsonReader getItem function and retrieve nested description
    }

}