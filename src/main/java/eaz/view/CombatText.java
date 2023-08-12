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

    void displayUpdatedHealth(Character c){
        int newHealth = c.getHealth();
        System.out.println("Targets health = " + newHealth);
    }

    void combatHit(Character c, int pHit, int hitRoll, int totalHit){
        System.out.println("You rolled a: " + hitRoll + ", plus your hit chance of: " + pHit + " caused you to hit with a total " + totalHit);
        System.out.println("You hit the " + c.getName());

    }


}   // END OF CLASS