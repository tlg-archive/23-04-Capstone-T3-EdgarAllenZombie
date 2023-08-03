package Model;

import com.google.gson.Gson;

import javax.xml.stream.events.Characters;
import java.io.FileNotFoundException;
import java.io.FileReader;

class JsonReader {
    public static void main(String[] args) {
        getPlayer();

    }
    private String item;
    private String room;


    public JsonReader() {

    }

    private static void getPlayer(){
        Gson gson = new Gson();

        try {
            FileReader reader = new FileReader("src/main/resources/Characters.json");
            Player player = new Gson().fromJson(reader, Player.class);
            System.out.println("Player name is: " + player.name);
            System.out.println("Player health is: " + player.health);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class Player{
    String name;
    int health;

    Player(String name, int health){
        this.name = name;
        this.health = health;
    }


}   // END OF CLASS