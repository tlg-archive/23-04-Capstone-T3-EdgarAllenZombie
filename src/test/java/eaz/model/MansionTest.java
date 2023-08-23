package eaz.model;

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


    @BeforeEach
    public void setUp() throws IOException {
        mansion = new Mansion();
        player = new Player();
        mansion = MyJsonReader.readMansion("JsonObjects.json");  // populate the mansion from the JsonObjects.json

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
        mansion.lookAtItem("knife");
    }

    @Test
    public void lookAtItemsInRoom_ShouldNotReturnItemDescription() {
        mansion.setCurrentLocationName("Foyer");
        mansion.lookAtItem("plate of food");
    }
}