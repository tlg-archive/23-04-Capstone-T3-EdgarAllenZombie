package eaz.controller;

import eaz.model.Mansion;
import eaz.model.MyJsonReader;
import eaz.model.Player;
import eaz.view.Music;
import eaz.view.ViewMain;

import eaz.model.MyJsonReader;
import eaz.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


public class GUI_Two {

    JFrame window;   //First Layer
    Container con;   //Placed on window
    JPanel titleGamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, userPromptPanel;  //Placed on container
    JLabel titleGameLabel, healthLabel, healthLabelNumber, inventoryLabel, inventoryLabelNumber, playerNameLabel, playerNameLabelNumber, userPromptLabel; //Placed on panel

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 70);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    Font gameFont = new Font("Times New Roman", Font.PLAIN, 15);

    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    JTextField userInputField;

    private final Mansion mansion;
    private final GUIFunctionality helper;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    UserInputHandler userInputHandler = new UserInputHandler();
    ViewMain playerInfo = new ViewMain();

//    public static void main(String[] args) throws IOException {
//        new GUI_Two();
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GUI_Two();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public GUI_Two() throws IOException {
        mansion = MyJsonReader.readMansion("saved.json");
        Player player;
        helper = new GUIFunctionality(mansion);

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        titleGamePanel  = new JPanel();
        titleGamePanel.setBounds(100, 100, 600, 150);
        titleGamePanel.setBackground(Color.black);

        titleGameLabel = new JLabel("Edgar Allen Zombie");
        titleGameLabel.setForeground(Color.green);
        titleGameLabel.setFont(titleFont);

        //set up the button panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        //set up the button
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.green);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titleGamePanel.add(titleGameLabel);
        startButtonPanel.add(startButton);

        con.add(titleGamePanel);
        con.add(startButtonPanel);

        // Refreshing the window
        window.revalidate();
    }

    public void createGameScreen(){

        titleGamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        //Header text area with the Player information
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        //playerPanel.setLayout(new GridLayout(1, 4));
        //playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        playerPanel.setLayout(new GridBagLayout());
        con.add(playerPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 20);

        playerNameLabel = new JLabel("Name: ");
        playerNameLabel.setFont(normalFont);
        playerNameLabel.setForeground(Color.green);
        playerPanel.add(playerNameLabel);

        playerNameLabelNumber = new JLabel();
        playerNameLabelNumber.setFont(normalFont);
        playerNameLabelNumber.setForeground(Color.yellow);
        playerPanel.add(playerNameLabelNumber, constraints);

        healthLabel = new JLabel("Health: ");
        healthLabel.setFont(normalFont);
        healthLabel.setForeground(Color.green);
        playerPanel.add(healthLabel);

        healthLabelNumber = new JLabel();
        healthLabelNumber.setFont(normalFont);
        healthLabelNumber.setForeground(Color.yellow);
        playerPanel.add(healthLabelNumber, constraints);

        inventoryLabel = new JLabel("Inventory: ");
        inventoryLabel.setFont(normalFont);
        inventoryLabel.setForeground(Color.green);
        playerPanel.add(inventoryLabel);

        inventoryLabelNumber = new JLabel();
        inventoryLabelNumber.setFont(normalFont);
        inventoryLabelNumber.setForeground(Color.yellow);
        playerPanel.add(inventoryLabelNumber, constraints);

        // Main Body of Text
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 90, 600, 300);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea(helper.displayPlayerInformation());
        mainTextArea.setBounds(100, 90, 600, 300);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.green);
        mainTextArea.setFont(gameFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        //User input area
        userPromptPanel = new JPanel();
        userPromptPanel.setBounds(100, 420, 200, 25);
        userPromptPanel.setBackground(Color.black);//change the color later
        userPromptPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        con.add(userPromptPanel);

        // adds the text to the area for the user prompt
        userPromptLabel = new JLabel("Enter a Command > ");
        userPromptLabel.setBounds(100, 415, 200, 25);
        userPromptLabel.setBackground(Color.black);
        userPromptLabel.setForeground(Color.pink);
        userPromptLabel.setFont(normalFont);
        userPromptPanel.add(userPromptLabel);

        userInputField = new JTextField();
        userInputField.setBounds(310, 420, 350, 25);
        userInputField.setFont(normalFont);
        userInputField.setBackground(Color.pink);
        userInputField.setForeground(Color.black);
        userInputField.addActionListener(userInputHandler);
        con.add(userInputField);


        //Misc buttons area to implement
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(150, 480, 500, 50);
        choiceButtonPanel.setBackground(Color.red);
        choiceButtonPanel.setLayout(new GridLayout(1,4));
        con.add(choiceButtonPanel);

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.green);
        choice1.setFont(normalFont);
        choiceButtonPanel.add(choice1);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.green);
        choice2.setFont(normalFont);
        choiceButtonPanel.add(choice2);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.green);
        choice3.setFont(normalFont);
        choiceButtonPanel.add(choice3);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.green);
        choice4.setFont(normalFont);
        choiceButtonPanel.add(choice4);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");

        playerSetup();

        // Refreshing the window
        window.revalidate();

    }
    public void playerSetup() {
        Player player = mansion.getPlayer();
        String playerName = player.getName();
        int playerHealth = player.getHealth();
        List playerInventory = player.getInventory();

        playerNameLabelNumber.setText("" + playerName);
        inventoryLabelNumber.setText("" + playerInventory);
        healthLabelNumber.setText("" + playerHealth);
    }

    public class UserInputHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            helper.handleUserInput(userInputField, mainTextArea);
            playerSetup();
        }
    }


    public class TitleScreenHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }

    public class ChoiceHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();

            switch(choice) {
                case "c1":
                    break;
                case "c2":
                    break;
                case "c3":
                    break;
                case "c4":
                    break;
            }
        }
    }
}