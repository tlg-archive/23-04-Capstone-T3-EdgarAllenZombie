package eaz.controller;

import eaz.view.ViewMain;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ViewMain viewMain = new ViewMain();
        EAZ eaz = new EAZ();
        viewMain.introScreen();
        eaz.run();

        //to incorporate the option, prompt the user for 1) play console base/2)GUI based
    }
}