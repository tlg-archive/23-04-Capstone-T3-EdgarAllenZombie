package Controller;
import Model.JsonReader;

public class Look {
    private static String item;

    public static String look(String item) {
        // get users input from look prompt
        String lookItem = TextParser.GetInput();
        item = lookItem;
        //JsonReader.readJson(filename,item.class);
        System.out.println("look at " + item);
        // use that user input to call JsonReader getItem function and retrieve nested description
        return item;
    }

    public static void main(String[] args) {
        look(item);
    }
}