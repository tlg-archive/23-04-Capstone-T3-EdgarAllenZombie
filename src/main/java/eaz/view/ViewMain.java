package eaz.view;

import eaz.model.Mansion;

import java.io.IOException;

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
    public void displayPlayerStats(String name, int health, String[] inventory){
        gameLoop.displayPlayerStats(name, health, inventory);
    }

    public void loopDisplay(String name, int health, String[] inventory, Mansion mansion) {
        gameLoop.loopDisplayText(name, health, inventory, mansion);
    }

}   // END OF CLASS