package eaz.controller;

import eaz.model.Mansion;
import eaz.view.ViewMain;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class GUIFunctionality {
    //methods to incorporate the original game into the GUI go here.
    private final Mansion mansion;
//    ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
//    PrintStream printOutput = new PrintStream(basicOutput);
//    System.setOut(printOutput);


    GUIFunctionality(Mansion mansion) {
        this.mansion = mansion;
    }

    void handleUserInput(JTextComponent field) {
        //These first three lines we can move anywhere, they set up the redirect
        ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(basicOutput);
        System.setOut(printOutput);



        //This is from the user input in the panel and sending it into the text parser
        String input = field.getText();
        try {
            TextParser.handleInput(mansion, input, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String capturedOutput = basicOutput.toString();

//        capturedOutput = capturedOutput.replaceAll("\\d{1,2}(;\\d{1,2})?", "");
        capturedOutput = capturedOutput.replaceAll("\\x1B\\[[0-9;]*[mK]", "")
                .replaceAll("\\[\\s*\\]", "");


        // TODO: 8/18/2023 involk methods in EAZ and/or text parser to handle user input

        //clears the panel input
        field.setText("");

        //this just appends the line to the basicOutput and added it as testing
        System.out.println("Howdy Ho and Away we Go");
        //System.out.println(input);
        //if (input.equalsIgnoreCase("map")) {
            //JOptionPane.showMessageDialog(null, "Commands: | move | take | attack | talk | help | quit");

        //The basicOutput holds whatever info was sout in the game and the toString will direct it to our panels/windows
        //JOptionPane.showMessageDialog(null, basicOutput.toString());
        JOptionPane.showMessageDialog(null, capturedOutput);
        //}
    }
//
//    void redirectingOutput() {
//        ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
//        PrintStream printOutput = new PrintStream(basicOutput);
//        basicOutput.toString(); //allows the gui to print
//    }
////
//    System.setOut(printOutput);
//    basicOutput.reset();
}