package eaz.controller;
//package eaz.view;

import eaz.model.Mansion;
import eaz.model.Player;
//import eaz.view.GameIntro;
import eaz.view.GameLoopDisplay;
import eaz.view.GeneralViewItems;
import eaz.view.ViewMain;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Locale;

public class GUIFunctionality_Two {
    //methods to incorporate the original game into the GUI go here.
    private Mansion mansion;
    private GUI_Two gui;
    ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
    PrintStream printOutput = new PrintStream(basicOutput);

    public GUIFunctionality_Two(Mansion mansion) {
        this.mansion = mansion;
    }

    String displayPlayerInformation() {
        //These first three lines we can move anywhere, they set up the redirect

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

    void handleUserInput(JTextComponent field, JLabel outPutLabel) {

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


        handleButtons(verb, noun, outPutLabel);

    }

    public String printTextFileToGui(String fileName) {
        // Prints the opening splash screen
        //noinspection ConstantConditions
        System.setOut(printOutput);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(GameLoopDisplay.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line + "\n");
            }
            String textOutput = basicOutput.toString();
            String refactoredOutput = textOutput.replaceAll("\\x1B\\[[0-9;]*[mK]", "")
                    .replaceAll("\\[\\s*\\]", "");
            basicOutput.reset();
            return refactoredOutput;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void handleButtons(String verb, String noun, JLabel outputPanel) {
        System.setOut(printOutput);
        try {
            TextParser.handleInput(mansion, verb, noun);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String capturedOutput = basicOutput.toString();
        basicOutput.reset();
        outputPanel.setText("");
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

        JTextArea textArea = new JTextArea();
        Font font = new Font("Monospaced", Font.PLAIN, 12);
        textArea.setFont(font);
        textArea.setTabSize(1);
        textArea.setText(capturedOutput.trim()); // Set the text
        textArea.setEditable(false); // Prevent user editing
        if (capturedOutput.contains("Error:")) {
            outputPanel.setForeground(Color.red);
            outputPanel.setText("<html>" + capturedOutput + "<html>");
        } else {
            switch (verb) {
                case "help":
                case "look":
                case "attack":
                case "talk":
                    outputPanel.setForeground(Color.yellow);
                    outputPanel.setText("<html>" + capturedOutput + "<html>");
                    break;
                case "map":
                    JOptionPane.showMessageDialog(null, textArea, verb.toUpperCase(), JOptionPane.PLAIN_MESSAGE);
                    break;
                // not fully implemented
                case "quit":
                    JOptionPane.showMessageDialog(null, "Quitting game");
                    // need a way to stop the game
                    break;
            }
        }


//        switch (verb){
//            case "help":
//            case "look":
//            case "attack":
//            case "talk":
//                outputPanel.setForeground(Color.yellow);
//                outputPanel.setText("<html>" + capturedOutput + "<html>");
//                break;
//            case "map":
//                JOptionPane.showMessageDialog(null, textArea, verb.toUpperCase(), JOptionPane.PLAIN_MESSAGE);
//                break;
//            default:
//                switch (capturedOutput.length()) {
//                    case 0:
//                        break;
//                    default:
//                        if (capturedOutput.contains("Error:")) {
//                            outputPanel.setForeground(Color.red);
//                            outputPanel.setText("<html>" + capturedOutput + "<html>");
//                        }
//                }
//        }

        basicOutput.reset();
    }
}