package eaz.model;

import eaz.controller.TextParser;
import eaz.view.ViewMain;

import java.util.Arrays;

public class Combat {
    ViewMain viewMain = new ViewMain();
    Player player = new Player();

    public void combat(String target, Location currentLocation, Character[] characters) {  // once player health matters return should be an int
        // check the target against characters
        Character c = getTarget(target, currentLocation, characters);
        assert c != null;

        System.out.println("Would you like to attack? (yes/no)");
        String attackChoice = TextParser.getInput();

        if (attackChoice.equals("yes")) {
            checkForHit(c);
        }
//        viewMain.displayCombat(c, player, hit);

        viewMain.displayCombatHealth(c);

    }

    private Character getTarget(String target, Location currentLocation, Character[] characters){
        if(currentLocation.getCharacters() != null){
            for(String characterName : currentLocation.getCharacters()){
                for(Character c : characters){
                    if(characterName.equals(c.getName()) && characterName.equals(target)){
                        return c;
                    }
                }
            }
        } return null;
    }

    private void checkForHit(Character c){
        boolean hit = false;
        int pHit = player.getHitChance();  // save player hit
        int hitRoll = Randomizer.randomizer(20);  // save random hit chance
        int totalHit = pHit + hitRoll;  // add them up
        int targetArmor = c.getArmor();  // get character armor value
        int damage = player.getDamage();

        if(totalHit > targetArmor){  // compare it to enemy armor value
            // display output to player
            viewMain.combatHit(c, pHit, hitRoll, totalHit, true, damage);
            c.setHealth(damage);
        }else{
            viewMain.combatHit(c, pHit, hitRoll, totalHit, false, damage);
        }
    }

}   // END OF CLASS