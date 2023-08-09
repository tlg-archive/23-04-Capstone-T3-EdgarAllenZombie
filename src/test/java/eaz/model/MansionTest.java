package eaz.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MansionTest {

    private Mansion mansion;

    @BeforeEach
    public void setUp() {
        mansion = new Mansion();

    }

    @Test
    public void testMoveUpdatesLocation() throws IOException {

        // Perform a move
        //Location newLocation = mansion.move("north");

       // assertEquals(newLocation.getName(), mansion.getCurrentLocationName());
    }
}