package eaz.controller;

import eaz.model.Mansion;
import eaz.model.MyJsonReader;
import eaz.model.Player;
import eaz.view.Music;
import eaz.view.ViewMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

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
    private final Mansion mansion;
    private final GUIFunctionality helper;
    public static Music backgroundMusic = new Music("music", "audioFiles/zombies.wav");


    //This main is for testing purposes only, to periodically test adjustments to the GUI.
        // Set up the sout redirect to Buffer Stream

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //Ctor
    public GUI() throws IOException {
        mansion = MyJsonReader.readMansion("saved.json");
        Player player;
        helper = new GUIFunctionality(mansion);



        // Set up the main game window
        gameWindow = new JFrame();
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameWindow.setSize(800, 600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setBackground(Color.darkGray);
        gameWindow.setLayout(null); // Use absolute layout
        container = gameWindow.getContentPane();


        // Set up the panel for the game title
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.black);//change color later
        titleLabel = new JLabel("Edgar Allen Zombie");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(titleFont);

        // Set up the panel for the start button
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
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
                    //start the music
                    backgroundMusic.setVolume("music", (float) 7/10);
                    backgroundMusic.play("music");
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

    public void createGameScreen() {
        ViewMain viewMain = new ViewMain();

        int userInputY_val = 390;

        // Hide the title and start button panels
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);

//______Game Text Area: Displays game text of the story flow______________________________________
        //make the panel to hold the game text
        gameTextPanel = new JPanel();
        //gameTextPanel.setBounds(100, 50, 600, 250);
        gameTextPanel.setBounds(200, 50, 800, 300);
        gameTextPanel.setBackground(Color.blue);//change color later
        container.add(gameTextPanel);

        //add the text area to the panel so it can actually be displayed.
        gameTextDisplayArea = new JTextArea("Game Text will be displayed here!!!, Ticket 598 complete!!!");
        gameTextDisplayArea.setText(helper.displayPlayerInformation()); // TODO: 8/19/2023 change to append maybe?
        //helper.displayPlayerInformation(gameTextDisplayArea);
        //gameTextDisplayArea.setBounds(200, 100, 600, 600);
        gameTextDisplayArea.setBounds(200, 50, 700, 200);
        gameTextDisplayArea.setBackground(Color.black);//change color to dark grey later
        gameTextDisplayArea.setForeground(Color.white);
        gameTextDisplayArea.setFont(normalFont);
        gameTextDisplayArea.setLineWrap(true);
        gameTextPanel.add(gameTextDisplayArea);

//______User Input Field: Prompts user for input fields____________________________________________
        //creates the area for the user prompt
        userPromptPanel = new JPanel();
        userPromptPanel.setBounds(300, userInputY_val, 150, 25);
        userPromptPanel.setBackground(Color.red);//change the color later
        container.add(userPromptPanel);

        // adds the text to the area for the user prompt
        userPromptArea = new JTextArea("What would you like to do? ");
        userPromptArea.setBounds(300, userInputY_val, 150, 25);
        userPromptArea.setBackground(Color.black);
        userPromptArea.setForeground(Color.white);
        userPromptArea.setFont(normalFont);
        userPromptArea.setEditable(false);
        userPromptPanel.add(userPromptArea);

        // please dont use!!!
//        userInputArea = new JTextArea();
//        userInputArea.setBounds(260,userInputY_val,440,25);
//        userInputArea.setBackground(Color.black);
//        userInputArea.setForeground(Color.white);
//        container.add(userInputArea);

        // creates the text field
        userInputField = new JTextField();
        userInputField.setBounds(460, userInputY_val, 440, 25);
        userInputField.setFont(normalFont);
        userInputField.setBackground(Color.black);
        userInputField.setForeground(Color.white);
        container.add(userInputField);

        userInputField.addActionListener(event -> helper.handleUserInput(userInputField, gameTextDisplayArea));

        // Create a slider to control music volume
        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 70); // Initial volume at 70%
        volumeSlider.setBounds(200, userInputY_val + 30, 300, 25);
        container.add(volumeSlider);

        backgroundMusic.play("music");
        volumeSlider.addChangeListener(event -> {
            JSlider source = (JSlider) event.getSource();
            if (!source.getValueIsAdjusting()) {
                float volume = (float) source.getValue() / 100;
                backgroundMusic.setVolume("music", volume);
            }
        });

    }

}