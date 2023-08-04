package Controller;
import Model.JsonReader;

public class Look {
    private static String item;

    public static String look(String noun) {
        // get users input from look prompt
        String lookItem = TextParser.GetInput();
        noun = lookItem;
        //JsonReader.readJson(filename,item.class);
        System.out.println("look at " + noun);
        // use that user input to call JsonReader getItem function and retrieve nested description
        return noun;
    }

}