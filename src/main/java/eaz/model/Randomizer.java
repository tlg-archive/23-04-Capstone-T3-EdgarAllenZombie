package eaz.model;
import java.util.Random;

class Randomizer {
    // need to take users input
    public static int randomizer(int max) {
        Random rand = new Random();
        // need to check what "TYPE" of randomizer to run (Dialogue, battle power, hit chance)
        // or switch cases ?
        // if( ) { }
        // return the random Integer as randomInt
        return rand.nextInt(max);
    }
}