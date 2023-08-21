package eaz.controller;

import eaz.model.Mansion;
import eaz.model.Player;
import eaz.view.ViewMain;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

class GUIFunctionality_Two {
    //methods to incorporate the original game into the GUI go here.
    //private final Mansion mansion;
    private Mansion mansion;
    ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
    PrintStream printOutput = new PrintStream(basicOutput);


    GUIFunctionality_Two(Mansion mansion) {
        this.mansion = mansion;
    }

    String displayPlayerInformation() {
        //These first three lines we can move anywhere, they set up the redirect
//        ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
//        PrintStream printOutput = new PrintStream(basicOutput);

        System.setOut(printOutput);
        Player player = mansion.getPlayer();
        ViewMain viewMain = new ViewMain();
        viewMain.loopDisplay(player.getName(), player.getHealth(), player.getInventory(), mansion);
        String textOutput = basicOutput.toString();
        String refactoredOutput = textOutput.replaceAll("\\x1B\\[[0-9;]*[mK]", "")
                .replaceAll("\\[\\s*\\]", "");
        basicOutput.reset();
        return refactoredOutput;
    }

    void handleUserInput(JTextComponent field, JTextArea gameTextDisplayArea) {

        String inputText = field.getText();
        field.setText("");
        String[] words = inputText.split(" ");
        String verb = "";
        String noun = "";
        verb = words[0];
        noun = words.length > 1 ? words[1] : "";

        if (words.length >= 2) {
            noun = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        }

        System.setOut(printOutput);
        // This is from the user input in the panel and sending it into the text parser

        try {
            TextParser.handleInput(mansion, verb, noun);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String capturedOutput = basicOutput.toString();
        basicOutput.reset();
        capturedOutput = capturedOutput.replaceAll("\\x1B\\[[0-9;]*[mK]", "")
                .replaceAll("\\[\\s*\\]", "");

        // If there is an Error we need to strip out the information text based games adds.
        if (capturedOutput.contains("Error:")){
            String longText = capturedOutput;
            String target = "Please try again.";
            int index = longText.indexOf(target);
            if (index != -1) {
                String result = longText.substring(0, index + target.length());
                capturedOutput = result;
            }
        }
        // Create a JTextArea and set its properties
        Font font = new Font("Monospaced", Font.PLAIN, 12);
        JTextArea textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setTabSize(1);
        textArea.setText(capturedOutput.trim()); // Set the text
        textArea.setEditable(false); // Prevent user editing

        // If the field is help...
        if (verb.equalsIgnoreCase("help") || verb.equalsIgnoreCase("map") || capturedOutput.contains("Error:")) {
            JOptionPane.showMessageDialog(null, textArea, verb.toUpperCase(), JOptionPane.PLAIN_MESSAGE);
        } else {
            // Set text to write to a jPanel
            //gameTextDisplayArea.setText(displayPlayerInformation());
        }
    }
}