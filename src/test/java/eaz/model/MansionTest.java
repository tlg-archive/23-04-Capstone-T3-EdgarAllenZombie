package eaz.model;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

class MansionTest {
    private Mansion mansion;
    private Player player;
    private Item[] items;
    private Item item;

    @BeforeEach
    public void setUp() throws IOException {
        mansion = new Mansion();
        player = new Player();
        mansion = MyJsonReader.readMansion("JsonObjects.json");  // populate the mansion from the JsonObjects.json
        item = new Item();
    }

    @Test
    public void testMoveUpdatesLocation() throws IOException {

        // Perform a move
        //Location newLocation = mansion.move("north");

        // assertEquals(newLocation.getName(), mansion.getCurrentLocationName());
    }

    @Test
    public void lookAtItemsInRoom_ShouldReturnItemDescription() {
        mansion.setCurrentLocationName("Foyer");
//        mansion.getItems();
        String test =  mansion.lookAtItem("knife");
        String expected = "";
        for (Item curItem : mansion.getItems()){
            // if the current item name equals the itemName
            if(curItem.getName().equalsIgnoreCase("knife")) {
                // print the description
                expected = curItem.getDescription();

            }
        }
        Assertions.assertEquals(test, expected);

    }

    @Test
    public void lookAtItemsInRoom_ShouldNotReturnItemDescription() {
        mansion.setCurrentLocationName("Foyer");
        String test = mansion.lookAtItem("plate of food");
        String expected = "";
        for(Item curItem : mansion.getItems()) {
            if(curItem.getName().equalsIgnoreCase("plate of food")){
                expected = curItem.getDescription();
            } else {
                expected = "match not found";
            }
        }
        Assertions.assertEquals(test, expected);


    }

    @Test
    public void pickUpItem_ShouldAddItemToInventory() {
        mansion.setCurrentLocationName("Foyer");
        List<String> inventory;
        inventory = mansion.pickUpItem("knife");
        String test = inventory.toString();
        String expected = "[knife]";
        Assertions.assertEquals(test, expected);

    }

    @Test
    public void pickUpItem_ShouldNotAddItemToInventory() {
        mansion.setCurrentLocationName("Foyer");
        List<String> inventory;
        inventory = mansion.pickUpItem("plate of food");
        String test = inventory.toString();
        String expected = "[]";
        Assertions.assertEquals(test, expected);
    }

    @Test
    public void testValidDropItem() {
        mansion.setCurrentLocationName("Foyer");
        List<String> inventory;
        inventory = mansion.pickUpItem("knife");
        inventory.toString();
        List<String> pack = mansion.getPlayer().getInventory();
        mansion.dropItem("knife");
        System.out.println(pack);
        //should see dropped knife message, and [] -- passed
    }

    @Test
    public void testInvalidDropItem(){
        mansion.setCurrentLocationName("Foyer");
        List<String> inventory = mansion.getPlayer().getInventory();
        inventory.toString();
        System.out.println(inventory);
        mansion.dropItem("knife");
        //should print error message -- passed
    }


}

