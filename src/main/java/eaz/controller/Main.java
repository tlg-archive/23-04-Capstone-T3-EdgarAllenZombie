package eaz.controller;
import eaz.model.EAZ;

import eaz.model.Item;
import eaz.model.JsonReader;
import eaz.view.StoryText;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        StoryText.introScreen();
        EAZ.run();

    }
}