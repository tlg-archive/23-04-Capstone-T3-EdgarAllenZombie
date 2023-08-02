package Controller;

import java.util.Scanner;

public class EAZ {

    boolean runGame = true;

    public void quitGame() {
        System.out.println("Are you sure you want to quit?");
        String quitResponse = TextParser.GetInput();
        if(quitResponse == "yes") {
            runGame = false;
        } else {
            return;
        }
    }
}