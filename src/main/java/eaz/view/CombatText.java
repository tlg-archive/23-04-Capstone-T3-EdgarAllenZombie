package eaz.view;

import eaz.model.Character;
import eaz.model.Player;

class CombatText {

    void displayCombatText(Character c, Player player, int hit){
        String targetName = c.getName();
        int targetHealth = c.getHealth();
        int damage = player.getDamage();
        int pHit = player.getHitChance();
        int totalHit = hit + pHit;

        System.out.println("You attacked the " + targetName);
        System.out.println("You have a hit chance of: " + pHit);
        System.out.println("You rolled a: " + hit);
        System.out.println("You have a total of: " + totalHit + " to hit" );

        System.out.println("You deal " + damage + " damage");
        System.out.println("Targets health " + c.getHealth());
    }

    void displayEnemyHealth(Character c){
        int newHealth = c.getHealth();
        System.out.println("Targets health = " + newHealth);
    }
    void displayUpdatedPlayerHealth(Player player){
        int newHealth = player.getHealth();
        System.out.println("Your health = " + newHealth);
    }

    void combatHit(Character c, int pHit, int hitRoll, int totalHit, boolean hit, int damage){
        System.out.println("You rolled a: " + hitRoll + ", plus your hit chance of: " + pHit + " for a total " + totalHit);
        if(hit){
            System.out.println("You hit the " + c.getName() + " for " + damage + " damage");
        }else{
            System.out.println("You missed the " + c.getName());
        }
    }
    void enemyHit(Character c, int eHit, int hitRoll, int totalHit, boolean hit, int damage){
        System.out.println("The " + c.getName() + " rolled a: " + hitRoll + ", plus a hit chance of: " + eHit + " for a total " + totalHit);
        if(hit){
            System.out.println("The " + c.getName() + " hit you for " + damage + " damage");
        }else{
            System.out.println("The " + c.getName() + " missed you!");
        }
    }


}   // END OF CLASS