package eaz.view;

import eaz.model.Mansion;
import eaz.model.Player;
import eaz.model.Character;

import java.io.IOException;
import java.util.List;

public class ViewMain {
    GameIntro gameIntro = new GameIntro();
    GameLoopDisplay gameLoop = new GameLoopDisplay();
    GeneralViewItems genItems = new GeneralViewItems();
    StoryText storyText = new StoryText();
    CombatText combatText = new CombatText();

    public void introScreen(){
        gameIntro.introScreen();
    }
    public void titleScreen(){
        gameIntro.titleScreen();
    }
    public void clearScreen(){
        genItems.clearScreen();
    }
    public void gameStart(){
        storyText.gameStart();
    }
    public void textHelp(){
        gameLoop.textHelp();
    }
    public void doubleLine(){
        System.out.println(genItems.doubleLines);
    }
    public void starLine(){
        System.out.println(genItems.starLines);
    }
    public void displayPlayerStats(String name, int health, List<String> inventory){
        gameLoop.displayPlayerStats(name, health, inventory);
    }
    public void displayPlayerInventory(List<String> inventory){
        gameLoop.displayPlayerInventory(inventory);
    }

    public void loopDisplay(String name, int health, List<String> inventory, Mansion mansion) {
        gameLoop.loopDisplayText(name, health, inventory, mansion);
    }

    public void charDialog(Mansion mansion, String name){
        gameLoop.characterDialog(mansion, name);
    }

    public void displayCombat(Character c, Player p, int hit){
        combatText.displayCombatText(c, p, hit);
    }

    public void displayEnemyHealth(Character c){
        combatText.displayEnemyHealth(c);
    }
    public void displayPlayerHealth(Player player){
        combatText.displayUpdatedPlayerHealth(player);
    }

    public void combatHit(Character c, int pHit, int hitRoll, int totalHit, boolean hit, int damage){
        combatText.combatHit(c, pHit, hitRoll, totalHit, hit, damage);
    }

    public void enemyHit(Character c, int eHit, int hitRoll, int totalHit, boolean hit, int damage){
        combatText.enemyHit(c, eHit, hitRoll, totalHit, hit, damage);
    }

    public void winGame(){
        storyText.winGame();
    }

}   // END OF CLASS