package eaz.controller;
import eaz.model.EAZ;

import eaz.model.Item;
import eaz.model.JsonReader;
import eaz.view.StoryText;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        StoryText.introScreen();
        EAZ.run();

//        Item.ItemList itemList = JsonReader.readJson(Item.ItemList.class);
//        Item[] items = itemList.getItems();
//
//        for(Item item : items){
//            System.out.println("Type: " + item.getType());
//            System.out.println("Name: " + item.getName());
//            System.out.println("Description: " + item.getDescription());
//            System.out.println("Location: " + item.getLocation());
//            System.out.println("Modifier: " + item.getModifier());
//            System.out.println();
//        }

    }
}   // END OF CLASS