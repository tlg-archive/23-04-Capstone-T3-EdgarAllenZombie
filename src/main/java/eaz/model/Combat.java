package eaz.model;

import java.util.Arrays;

public class Combat {
    public void combat(String target, Location currentLocation, Character[] characters) {  // once player health matters return should be an int
        if(currentLocation.getCharacters() != null){
            for(String characterName : currentLocation.getCharacters()){
                for (Character c : characters){
                    if(characterName.equals(c.getName()) && characterName.equals(target)){
                        System.out.println("You attacked the " + c.getName());
                        System.out.println("You deal 5 damage");
                        c.setHealth(5);
                        System.out.println("health = " + c.getHealth());
                        System.out.println("This target's damage = " + c.getDamage());
                    }
                }
            }
        }
    }



}   // END OF CLASS