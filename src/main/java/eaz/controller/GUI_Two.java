package eaz.controller;

import eaz.model.*;
import eaz.view.Music;
import eaz.view.SliderGradient;
import eaz.view.ViewMain;

import eaz.model.MyJsonReader;
import eaz.model.Player;
import eaz.view.Music;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class GUI_Two {

    JFrame window;   //First Layer
    Container con;   //Placed on window
    JPanel titleGamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, userPromptPanel, audioPanel;  //Placed on container
    JLabel titleGameLabel, healthLabel, healthLabelNumber, inventoryLabel, inventoryLabelNumber, playerNameLabel,
            playerNameLabelNumber, userPromptLabel, currentLocationLabel, currentLocationLabelNumber, descriptionLabel,
            descriptionLabelNumber, directionsLabel, directionsLabelNumber, itemsLabel, itemsLabelNumber, creaturesLabel,
            creaturesLabelNumber, volumeSliderLabel; //Placed on panel

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 70);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    Font gameFont = new Font("Times New Roman", Font.PLAIN, 15);

    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea, gameTextDisplayArea;
    JTextField userInputField;

    SliderGradient volumeSlider;

    private final Mansion mansion;

//    private final GUIFunctionality helper;
    private final GUIFunctionality_Two helper;
    public final Music backgroundMusic = new Music("music", "audioFiles/zombies.wav");


    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    UserInputHandler userInputHandler = new UserInputHandler();
    VolumeHandler volumeHandler = new VolumeHandler();
    ViewMain playerInfo = new ViewMain();

//    public static void main(String[] args) throws IOException {
//        new GUI_Two();
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GUI_Two();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public GUI_Two() throws IOException {
        mansion = MyJsonReader.readMansion("saved.json");
        Player player;
        helper = new GUIFunctionality_Two(mansion);

        window = new JFrame();
        window.setSize(800, 650);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        titleGamePanel  = new JPanel();
        titleGamePanel.setBounds(100, 100, 600, 150);
        titleGamePanel.setBackground(Color.black);

//        titleGameLabel = new JLabel("Edgar Allen Zombie");
        titleGameLabel = new JLabel();
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

        splashScreenSetup();

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
        mainTextPanel.setLayout(new GridLayout(10,1));
        con.add(mainTextPanel);

        currentLocationLabel = new JLabel("Current Location: ");
        currentLocationLabel.setFont(gameFont);
        currentLocationLabel.setForeground(Color.green);
        mainTextPanel.add(currentLocationLabel);

        currentLocationLabelNumber = new JLabel();
        currentLocationLabelNumber.setFont(gameFont);
        currentLocationLabelNumber.setForeground(Color.yellow);
        mainTextPanel.add(currentLocationLabelNumber);

        descriptionLabel = new JLabel("Room Description: ");
        descriptionLabel.setHorizontalAlignment(JLabel.LEFT);
        descriptionLabel.setFont(gameFont);
        descriptionLabel.setForeground(Color.green);
        mainTextPanel.add(descriptionLabel);

        descriptionLabelNumber = new JLabel();
        descriptionLabel.setBounds(100, 300, 500, 60);
        descriptionLabelNumber.setFont(gameFont);
        descriptionLabelNumber.setForeground(Color.yellow);
        mainTextPanel.add(descriptionLabelNumber);

        directionsLabel = new JLabel("<html><br>Directions to Move: </html>");
        directionsLabel.setHorizontalAlignment(JLabel.LEFT);
        directionsLabel.setFont(gameFont);
        directionsLabel.setForeground(Color.green);
        mainTextPanel.add(directionsLabel);

        directionsLabelNumber = new JLabel();
        directionsLabelNumber.setFont(gameFont);
        directionsLabelNumber.setForeground(Color.yellow);
        mainTextPanel.add(directionsLabelNumber);

        itemsLabel = new JLabel("Items in Room: ");
        itemsLabel.setHorizontalAlignment(JLabel.LEFT);
        itemsLabel.setFont(gameFont);
        itemsLabel.setForeground(Color.green);
        mainTextPanel.add(itemsLabel);

        itemsLabelNumber = new JLabel();
        itemsLabelNumber.setFont(gameFont);
        itemsLabelNumber.setForeground(Color.yellow);
        mainTextPanel.add(itemsLabelNumber);

        creaturesLabel = new JLabel("NPCs in Room: ");
        creaturesLabel.setHorizontalAlignment(JLabel.LEFT);
        creaturesLabel.setFont(gameFont);
        creaturesLabel.setForeground(Color.green);
        mainTextPanel.add(creaturesLabel);

        creaturesLabelNumber = new JLabel();
        creaturesLabelNumber.setFont(gameFont);
        creaturesLabelNumber.setForeground(Color.yellow);
        mainTextPanel.add(creaturesLabelNumber);

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


        // Create an audio panel

        audioPanel = new JPanel();
        audioPanel.setBounds(200, 550, 350, 30);
        audioPanel.setBackground(Color.black);
        con.add(audioPanel);

        volumeSliderLabel = new JLabel("Volume Level: ");
        volumeSliderLabel.setBackground(Color.black);
        volumeSliderLabel.setForeground(Color.green);
        volumeSliderLabel.setFont(normalFont);
        audioPanel.add(volumeSliderLabel);

        //volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 70); // Initial volume at 70%
        volumeSlider = new SliderGradient();
        volumeSlider.setBackground(Color.black);
        volumeSlider.setForeground(Color.green);
        volumeSlider.addChangeListener(volumeHandler);
        audioPanel.add(volumeSlider);

        // Game initialization
        playerSetup();
        roomSetup();
        backgroundMusic.play("music");

        // Refreshing the window
        window.revalidate();

    }
    public void splashScreenSetup() {
        String displayOutput = helper.printSplashScreen();
//        String displayOutput = "this is a test";
        titleGameLabel.setText(displayOutput);
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

    public void roomSetup(){

        Location currentLocation = mansion.getCurrentLocation();
        currentLocationLabelNumber.setText(currentLocation.getName());
        descriptionLabelNumber.setText("<html>" + currentLocation.getDescription() + "<html>");
        directionsLabelNumber.setText("" + currentLocation.getDirections().keySet());
        itemsLabelNumber.setText("" + currentLocation.getItems());
        creaturesLabelNumber.setText(Arrays.toString(currentLocation.getCharacters()));
    }

    public class UserInputHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            helper.handleUserInput(userInputField, mainTextArea);
            playerSetup();
            roomSetup();
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

    public class VolumeHandler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent event) {
            JSlider source = (JSlider) event.getSource();
            if (!source.getValueIsAdjusting()) {
                float volume = (float) source.getValue() / 100;
                backgroundMusic.setVolume("music", volume);
            }
        }
    }
}


//    Location currentLocation = mansion.getCurrentLocation();
//        System.out.println(yellow + "\nRoom Information:");
//                System.out.println(green + doubleLines);
//                System.out.println(green + "You are currently in: " + yellow + currentLocation.getName());
//                System.out.println(green + "Description: " + yellow + currentLocation.getDescription());
//                System.out.println(green + "Available directions are: " + yellow + currentLocation.getDirections().keySet());
//                System.out.println(green + "Items in the room: " + yellow + currentLocation.getItems());
//                System.out.println(green + "Creatures in the room: " + yellow + Arrays.toString(currentLocation.getCharacters()));
//                System.out.println(green + doubleLines + white +"\n");