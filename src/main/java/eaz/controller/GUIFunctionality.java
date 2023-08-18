package eaz.controller;

import eaz.model.Mansion;
import eaz.view.GUI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.PublicKey;

class GUIFunctionality {
    //methods to incorporate the original game into the GUI go here.
    private Mansion mansion;
//    public GUI gui = new GUI();


    ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
    PrintStream printOutput = new PrintStream(basicOutput);
//    System.setOut(printOutput);
//    basicOutput.toString(); allows the gui to print
//    basicOutput.reset();
}