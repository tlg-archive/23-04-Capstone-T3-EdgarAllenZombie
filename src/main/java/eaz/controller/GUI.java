package eaz.controller;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class GUI {

    // Declare objects for the game window
    JFrame gameWindow;  // The main game window
    Container container;  // The container that holds various GUI components
    JPanel titlePanel, startButtonPanel, gameTextPanel, userPromptPanel, userInputPanel;  // Different panels for organizing components
    JButton startButton;  // The button to start the game
    JLabel titleLabel;  // The label for the game title
    Font titleFont = new Font("Times New Roman", Font.BOLD, 60);  // Font for the game title
    Font startButtonFont = new Font("Times New Roman", Font.PLAIN, 16);  // Font for the start button
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 12);  // Normal font
    JTextArea gameTextDisplayArea, userPromptArea, userInputArea;  // Text areas for displaying game text and user input prompt
    JTextField userInputField;  // Text field for user input


    //This main is for testing purposes only, to periodically test adjustments to the GUI.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI();
        });
    }

    //Ctor
    public GUI(){

        // Set up the main game window
        gameWindow = new JFrame();
        gameWindow.setSize(800, 600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setBackground(Color.darkGray);
        gameWindow.setLayout(null); // Use absolute layout
        container = gameWindow.getContentPane();

        // Set up the panel for the game title
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.BLACK);//change color later
        titleLabel = new JLabel("Edgar Allen Zombie");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(titleFont);

        // Set up the panel for the start button
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300,400,200,100);
        startButtonPanel.setBackground(Color.BLACK);//change color later, this is so i can work on the bounds

        //Set up the Start Button
        startButton = new JButton("START");
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.BLACK);
        startButton.setFont(startButtonFont);
        //add an action listener for the start button
        startButton.addActionListener((event) -> createGameScreen());
        //add an action listener to accept the enter key as an additional input option to clear the title screen
        gameWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createGameScreen();
                }
            }
        });
        titlePanel.add(titleLabel);
        startButtonPanel.add(startButton);

        container.add(titlePanel);
        container.add(startButtonPanel);

        gameWindow.setVisible(true);//set visible need to be called after all contents are set
        gameWindow.setFocusable(true);
        gameWindow.requestFocus();
    }

    public void createGameScreen(){
        int userInputY_val = 310;

        // Hide the title and start button panels
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);

//______Game Text Area: Displays game text of the story flow______________________________________
        //make the panel to hold the game text
        gameTextPanel = new JPanel();
        gameTextPanel.setBounds(100,50,600,250);
        gameTextPanel.setBackground(Color.blue);//change color later
        container.add(gameTextPanel);

        //add the text area to the panel so it can actually be displayed.
        gameTextDisplayArea = new JTextArea("Game Text will be displayed here!!!, Ticket 598 complete!!!");
        gameTextDisplayArea.setBounds(100,100,400,250);
        gameTextDisplayArea.setBackground(Color.black);//change color to dark grey later
        gameTextDisplayArea.setForeground(Color.white);
        gameTextDisplayArea.setFont(normalFont);
        gameTextDisplayArea.setLineWrap(true);
        gameTextPanel.add(gameTextDisplayArea);

//______User Input Field: Prompts user for input fields____________________________________________
        userPromptPanel = new JPanel();
        userPromptPanel.setBounds(100,userInputY_val,150,25);
        userPromptPanel.setBackground(Color.red);//change the color later
        container.add(userPromptPanel);

        userPromptArea = new JTextArea("What would you like to do? ");
        userPromptArea.setBounds(100,userInputY_val,150,25);
        userPromptArea.setBackground(Color.black);
        userPromptArea.setForeground(Color.white);
        userPromptArea.setFont(normalFont);
        userPromptArea.setEditable(false);
        userPromptPanel.add(userPromptArea);

        userInputArea = new JTextArea();
        userInputArea.setBounds(260,userInputY_val,440,25);
        userInputArea.setBackground(Color.black);
        userInputArea.setForeground(Color.white);
        container.add(userInputArea);

        userInputField = new JTextField();
        userInputField.setBounds(260,userInputY_val,440,25);
        userInputField.setFont(normalFont);
        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = userInputField.getText().trim();
                // Process the user input here
                userInputArea.append("User input: " + userInput + "\n");
                userInputField.setText(""); // Clear the input field
            }
        });
        userInputArea.add(userInputField);
    }

}