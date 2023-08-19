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

class GUIFunctionality {
    //methods to incorporate the original game into the GUI go here.
    //private final Mansion mansion;
    private Mansion mansion;
    ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
    PrintStream printOutput = new PrintStream(basicOutput);

    GUIFunctionality(Mansion mansion) {
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
// TODO: 8/19/2023 refactor the code to make it cleaner
//    public String[] parseString(JTextComponent field) {
//        String inputText = field.getText();
////        String inputNoun;
//        field.setText("");
//
//        String[] words = inputText.split(" ");
//        String verb = "";
//        String noun = "";
//        verb = words[0];
//        noun = words.length > 1 ? words[1] : "";
//
//        if (words.length >= 2) {
//            noun = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
//        }
//        return new String[] {verb, noun};
//    }

    void handleUserInput(JTextComponent field, JTextArea gameTextDisplayArea) {
        //These first three lines we can move anywhere, they set up the redirect
//       ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
//       PrintStream printOutput = new PrintStream(basicOutput);
        String inputText = field.getText();
//        String inputNoun;
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
        //This is from the user input in the panel and sending it into the text parser

        try {
            TextParser.handleInput(mansion, verb, noun);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String capturedOutput = basicOutput.toString();
        basicOutput.reset();
        capturedOutput = capturedOutput.replaceAll("\\x1B\\[[0-9;]*[mK]", "")
                .replaceAll("\\[\\s*\\]", "");


        Font font = new Font("Monospaced", Font.PLAIN, 12);
        JTextArea textArea = new JTextArea(capturedOutput);
        textArea.setFont(font);
        textArea.setTabSize(1);

        // TODO: 8/18/2023 involk methods in EAZ and/or text parser to handle user input

        //The basicOutput holds whatever info was sout in the game and the toString will direct it to our panels/windows
        //JOptionPane.showMessageDialog(null, basicOutput.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        //JOptionPane.showMessageDialog(null, capturedOutput);

        // if the field is help...
        if (verb.equalsIgnoreCase("help") || verb.equalsIgnoreCase("map") || noun.equalsIgnoreCase("on") || noun.equalsIgnoreCase("off")) {
            JOptionPane.showMessageDialog(null, panel, verb.toUpperCase(), JOptionPane.PLAIN_MESSAGE);
        } else {
            //set text to write to a jPanel
            gameTextDisplayArea.setText(displayPlayerInformation());

        }
    }


//    void handleUserInput(JTextComponent field) {
//        //These first three lines we can move anywhere, they set up the redirect
////       ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
////       PrintStream printOutput = new PrintStream(basicOutput);
//
//
//        System.setOut(printOutput);
//
//        //This is from the user input in the panel and sending it into the text parser
//        String inputText = field.getText();
////        String inputNoun;
//        field.setText("");
//
//        String[] words = inputText.split(" ");
//        String verb = "";
//        String noun = "";
//        verb = words[0];
//        noun = words.length > 1 ? words[1] : "";
//
//        if (words.length >= 2) {
//            noun = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
//        }
//
////        String[] gameCommands = TextParser.parseInput(mansion);
////        verb = gameCommands[0];
////        noun = gameCommands[1];
//
//
//        try {
//            TextParser.handleInput(mansion, verb, noun);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String capturedOutput = basicOutput.toString();
//        basicOutput.reset();
//        capturedOutput = capturedOutput.replaceAll("\\x1B\\[[0-9;]*[mK]", "")
//                .replaceAll("\\[\\s*\\]", "");
//
//
//        Font font = new Font("Monospaced", Font.PLAIN, 12);
//        JTextArea textArea = new JTextArea(capturedOutput);
//        textArea.setFont(font);
//        textArea.setTabSize(1);
//
//        // TODO: 8/18/2023 involk methods in EAZ and/or text parser to handle user input
//
//        //The basicOutput holds whatever info was sout in the game and the toString will direct it to our panels/windows
//        //JOptionPane.showMessageDialog(null, basicOutput.toString());
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        JPanel panel = new JPanel();
//        panel.add(scrollPane);
//        //JOptionPane.showMessageDialog(null, capturedOutput);
//
//        // if the field is help...
//        if (verb.equalsIgnoreCase("help") || verb.equalsIgnoreCase("map") || verb.equalsIgnoreCase("music")) {
//            JOptionPane.showMessageDialog(null, panel, verb.toUpperCase(), JOptionPane.PLAIN_MESSAGE);
//        } else {
//            //set text to write to a jPanel
//
//        }
//    }


}