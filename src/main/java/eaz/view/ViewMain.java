package eaz.view;

import eaz.model.Mansion;

import java.io.IOException;
import java.util.List;

public class ViewMain {
    GameIntro gameIntro = new GameIntro();
    GameLoopDisplay gameLoop = new GameLoopDisplay();
    GeneralViewItems genItems = new GeneralViewItems();
    StoryText storyText = new StoryText();

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

}   // END OF CLASS