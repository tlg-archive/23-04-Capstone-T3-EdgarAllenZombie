package eaz.controller;

import eaz.model.Mansion;
import eaz.view.ViewMain;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class GUIFunctionality {
    //methods to incorporate the original game into the GUI go here.
    private final Mansion mansion;

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
        field.setText("");

        try {
            TextParser.handleInput(mansion, input, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String capturedOutput = basicOutput.toString();
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
        JOptionPane.showMessageDialog(null, panel, input.toUpperCase(), JOptionPane.PLAIN_MESSAGE);
    }
}