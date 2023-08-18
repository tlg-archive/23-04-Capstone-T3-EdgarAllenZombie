package eaz.controller;

import eaz.model.Mansion;

import javax.swing.*;
import javax.swing.text.JTextComponent;

class GUIFunctionality {
    //methods to incorporate the original game into the GUI go here.
    private final Mansion mansion;

    GUIFunctionality(Mansion mansion) {
        this.mansion = mansion;
    }

    void handleUserInput(JTextComponent field) {
        String input = field.getText();
        // TODO: 8/18/2023 involk methods in EAZ and/or text parser to handle user input
        field.setText("");
        System.out.println(input);
        if (input.equalsIgnoreCase("help")) {
            JOptionPane.showMessageDialog(null, "Commands: | move | take | attack | talk | help | quit");
        }
    }

//
//    ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
//    PrintStream printOutput = new PrintStream(basicOutput);
//    System.setOut(printOutput);
//    basicOutput.toString(); allows the gui to print
//    basicOutput.reset();
}