package eaz.controller;
import eaz.model.EAZ;

import eaz.model.Item;
import eaz.model.JsonReader;
import eaz.view.ViewMain;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ViewMain viewMain = new ViewMain();
        viewMain.introScreen();
        EAZ.run();

    }
}