package eaz.model;

import java.util.Arrays;

public class Combat {
    public void combat(String target, String[] character) {  // once player health matters return should be an int

        if (Arrays.asList(character).contains(target)) {
            System.out.println("You attacked " + target);
        } else {
            System.out.println(target + " does not exist at this location");
        }

    }
}   // END OF CLASS