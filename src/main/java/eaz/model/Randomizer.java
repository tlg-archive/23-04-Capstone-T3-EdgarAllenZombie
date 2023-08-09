package eaz.model;
import java.util.Random;

class Randomizer {
    // need to take users input
    public static Integer randomizer(String input) {
        Random rand = new Random();
        int upperBound = 20;
        int randomInt = rand.nextInt(upperBound);

        // need to check what "TYPE" of randomizer to run (Dialogue, battle power, hit chance)
        // or switch cases ?
        // if( ) { }
        // return the random Integer as randomInt
        return randomInt;
    }
}