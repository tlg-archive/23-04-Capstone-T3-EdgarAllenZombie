package eaz.model;

import eaz.controller.TextParser;
import eaz.view.ViewMain;

import java.util.Arrays;

public class Combat {
    ViewMain viewMain = new ViewMain();
    Player player = new Player();

    public void combat(String target, Location currentLocation, Character[] characters, Player player) {  // once player health matters return should be an int
        // check the target against characters
        Character c = getTarget(target, currentLocation, characters);
        assert c != null;

        System.out.println("Beginning combat");

        // run combat loop
        while (player.getHealth() > 0 && c.getHealth() > 0) {
            System.out.println("Would you like to attack? (yes/no)");
            String attackChoice = TextParser.getInput();
            viewMain.clearScreen();
            if (attackChoice.equals("yes")) {
                checkForHit(c, player);
                System.out.println();
                viewMain.displayEnemyHealth(c);
                enemyAttack(c, player);
                viewMain.displayPlayerHealth(player);
            }else if(attackChoice.equals("no")){
                return;
            }
            if(c.getHealth() <= 0){
                String[] enemies = {};
                currentLocation.setCharacters(enemies);
            }
        }

    }


//    }

    private Character getTarget(String target, Location currentLocation, Character[] characters) {
        if (currentLocation.getCharacters() != null) {
            for (String characterName : currentLocation.getCharacters()) {
                for (Character c : characters) {
                    if (characterName.equals(c.getName()) && characterName.equals(target)) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    private void checkForHit(Character c, Player player) {
        boolean hit = false;
        int pHit = player.getHitChance();  // save player hit
        int hitRoll = Randomizer.randomizer(20);  // save random hit chance
        int totalHit = pHit + hitRoll;  // add them up
        int targetArmor = c.getArmor();  // get character armor value
        int damage = player.getDamage();

        if (totalHit > targetArmor) {  // compare it to enemy armor value
            // display output to player
            viewMain.combatHit(c, pHit, hitRoll, totalHit, true, damage);
            c.setHealth(damage);
        } else {
            viewMain.combatHit(c, pHit, hitRoll, totalHit, false, damage);
        }
    }

    private void enemyAttack(Character c, Player player) {
        boolean hit = false;
        int eHit = c.getHit();  // save enemy hit chance
        int hitRoll = Randomizer.randomizer(20);
        int totalHit = eHit + hitRoll;
        int playerArmor = player.getArmor();
        int damage = c.getDamage();

        if (totalHit > playerArmor) {
            viewMain.enemyHit(c, eHit, hitRoll, totalHit, true, damage);
            player.setHealth(damage);
        } else {
            viewMain.enemyHit(c, eHit, hitRoll, totalHit, false, damage);
        }
    }
}   // END OF CLASS