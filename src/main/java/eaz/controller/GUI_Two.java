package eaz.controller;

import eaz.model.*;
import eaz.view.GeneralViewItems;
import eaz.view.Music;
import eaz.view.SliderGradient;
import eaz.view.ViewMain;

import eaz.model.MyJsonReader;
import eaz.model.Player;
import eaz.view.Music;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class GUI_Two {

    JFrame window, frame;   //First Layer
    Container con;   //Placed on window
    JPanel titleGamePanel, startButtonPanel, introTextPanel, playButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, userPromptPanel, audioPanel,
            arrowPanel, outputPanel, winTextPanel, winGameButtonPanel, loseTextPanel, loseGameButtonPanel;  //Placed on container
    JLabel  introTextLabel, healthLabel, healthLabelNumber, inventoryLabel, inventoryLabelNumber, playerNameLabel,
            playerNameLabelNumber, userPromptLabel, currentLocationLabel, currentLocationLabelNumber, descriptionLabel,
            descriptionLabelNumber, directionsLabel, directionsLabelNumber, itemsLabel, itemsLabelNumber, creaturesLabel,
            creaturesLabelNumber, volumeSliderLabel, titleGameLabel, outputLabel, outputTitleLabel, winTextLabel, loseTextLabel; //Placed on panel

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 70);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    Font gameFont = new Font("Times New Roman", Font.PLAIN, 15);
    Font textFileFont = new Font("Monospaced", Font.PLAIN, 8);

    JButton startButton, playButton, playNewButton, choice1, choice2, choice3, choice4, choice5, arrowUp, arrowDown, arrowLeft, arrowRight, restartButton, quitButton;
    JTextArea mainTextArea, gameTextDisplayArea;
    JTextField userInputField;

    Icon iconEast, iconWest, iconNorth, iconSouth, iconSettings, iconPlaySound, iconStopSound, iconMap, iconHelp, iconZombie, iconBag,
           iconHB1, iconHB2, iconHB3, iconHB4, iconHB5, iconHB6, iconHB7, iconHB8, iconHB9, iconHB10, iconHB11, iconHB12, iconHB13, iconHB14;

    //  JTextPane titleGameLabel;

    SliderGradient volumeSlider;

    private Mansion mansion;

    //Private final GUIFunctionality helper;
    private GUIFunctionality_Two helper;
    public final Music backgroundMusic = new Music("music", "audioFiles/zombies.wav");
    public final Music winMusic = new Music("music", "audioFiles/win.wav");


    TitleScreenHandler tsHandler = new TitleScreenHandler();
    IntroScreenHandler isHandler = new IntroScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();
    UserInputHandler userInputHandler = new UserInputHandler();
    VolumeHandler volumeHandler = new VolumeHandler();
    ViewMain playerInfo = new ViewMain();
    ViewMain viewMain = new ViewMain();
    ByteArrayOutputStream basicOutput = new ByteArrayOutputStream();
    PrintStream printOutput = new PrintStream(basicOutput);
    MoveHandler moveHandler = new MoveHandler();
    VolumeToggleHandler volumeToggleHandler = new VolumeToggleHandler();

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
        //mansion = MyJsonReader.readMansion("saved.json");
        //helper = new GUIFunctionality_Two(mansion);
        Player player;

        window = new JFrame();
        window.setTitle("Edgar Allen Zombie");
        window.setSize(800, 820);                    //height was 650
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        titleGamePanel = new JPanel();
        titleGamePanel.setBounds(150, 100, 800, 300);
        titleGamePanel.setBackground(Color.black);
        titleGamePanel.setLayout(new GridLayout(1, 1));

        titleGameLabel = new JLabel();
        titleGameLabel.setBackground(Color.black);
        titleGameLabel.setForeground(Color.yellow);
        titleGameLabel.setHorizontalAlignment(JLabel.LEFT);
        titleGameLabel.setVerticalAlignment(JLabel.TOP);
        titleGameLabel.setHorizontalAlignment(JLabel.LEFT);
        titleGameLabel.setFont(textFileFont);

        //set up the button panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 450, 200, 100);
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

        //loadFileContent;
        loadFileContent();

        // Refreshing the window
        window.revalidate();
    }

    public void createIntroScreen() {

        titleGamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        introTextPanel = new JPanel();
        introTextPanel.setBounds(100, 100, 600, 300);
        introTextPanel.setBackground(Color.black);
        introTextPanel.setLayout(new GridLayout(1, 1));

        //IntroTextLabel
        introTextLabel = new JLabel();
        introTextPanel.setBackground(Color.black);
        introTextLabel.setForeground(Color.green);
        introTextLabel.setHorizontalAlignment(JLabel.CENTER);
        introTextLabel.setFont(normalFont);

        //Set up the button panel
        playButtonPanel = new JPanel();
        playButtonPanel.setBounds(220, 400, 300, 100);
        playButtonPanel.setBackground(Color.black);

        //Set up the play new game button
        playButton = new JButton("New Game");
        playButton.setBackground(Color.black);
        playButton.setForeground(Color.green);
        playButton.setFont(normalFont);
        playButton.addActionListener(isHandler);
        playButton.setActionCommand("newGame");
        playButton.setFocusPainted(false);

        //Set up the load saved game button
        playNewButton = new JButton("Saved Game");
        playNewButton.setBackground(Color.black);
        playNewButton.setForeground(Color.green);
        playNewButton.setFont(normalFont);
        playNewButton.addActionListener(isHandler);
        playNewButton.setActionCommand("savedGame");
        playButton.setFocusPainted(false);


        introTextPanel.add(introTextLabel);
        con.add(introTextPanel);
        playButtonPanel.add(playButton);
        playButtonPanel.add(playNewButton);

        con.add(introTextPanel);
        con.add(playButtonPanel);

        introTextSetup();
    }

    public void createWinScreen(){
        //make the game screen panels (anything attached to the container) to NOT visible
        playerPanel.setVisible(false);
        mainTextPanel.setVisible(false);
        outputPanel.setVisible(false);
        userPromptPanel.setVisible(false);
        userInputField.setVisible(false);
        choiceButtonPanel.setVisible(false);
        arrowPanel.setVisible(false);
        audioPanel.setVisible(false);


        //make the panel to display the text
        winTextPanel = new JPanel();
        winTextPanel.setBounds(100,100,600,400);
        winTextPanel.setBackground(Color.black); //change to black after testing
        winTextPanel.setLayout(new GridLayout(1,1));

        //create the text Label so Rich can work his magic
        winTextLabel = new JLabel();
        winTextLabel.setBackground(Color.black);
        winTextLabel.setForeground(Color.green);
        winTextLabel.setHorizontalAlignment(JLabel.LEFT);
        winTextLabel.setFont(normalFont);

        //setup the button Panel
        winGameButtonPanel = new JPanel();
        winGameButtonPanel.setBounds(220,600,300,100);
        winGameButtonPanel.setBackground(Color.black);//change to black after testing

        //setup restart button
        restartButton = new JButton("Restart");
        restartButton.setBackground(Color.black);
        restartButton.setForeground(Color.green);
        restartButton.setFont(normalFont);
        restartButton.addActionListener(isHandler);
        restartButton.setActionCommand("restart");
        restartButton.setFocusPainted(false);

        //setup Quit button
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.green);
        quitButton.setFont(normalFont);
        quitButton.addActionListener(isHandler);
        quitButton.setActionCommand("exit");
        quitButton.setFocusPainted(false);

        //link everything together
        winTextPanel.add(winTextLabel);
        winGameButtonPanel.add(restartButton);
        winGameButtonPanel.add(quitButton);
        con.add(winTextPanel);
        con.add(winGameButtonPanel);

        //print dialogue to screen
        winGameTextSetup();
    }

    public void createLoseScreen(){
        //make the game screen panels (anything attached to the container) to NOT visible
        playerPanel.setVisible(false);
        mainTextPanel.setVisible(false);
        outputPanel.setVisible(false);
        userPromptPanel.setVisible(false);
        userInputField.setVisible(false);
        choiceButtonPanel.setVisible(false);
        arrowPanel.setVisible(false);
        audioPanel.setVisible(false);


        //make the panel to display the text
        loseTextPanel = new JPanel();
        loseTextPanel.setBounds(100,100,600,400);
        loseTextPanel.setBackground(Color.black); //change to black after testing
        loseTextPanel.setLayout(new GridLayout(1,1));

        //create the text Label so Rich can work his magic
        loseTextLabel = new JLabel();
        loseTextLabel.setBackground(Color.black);
        loseTextLabel.setForeground(Color.green);
        loseTextLabel.setHorizontalAlignment(JLabel.LEFT);
        loseTextLabel.setFont(normalFont);

        //setup the button Panel
        loseGameButtonPanel = new JPanel();
        loseGameButtonPanel.setBounds(220,600,300,100);
        loseGameButtonPanel.setBackground(Color.black);//change to black after testing

        //setup restart button
        restartButton = new JButton("Restart");
        restartButton.setBackground(Color.black);
        restartButton.setForeground(Color.green);
        restartButton.setFont(normalFont);
        restartButton.addActionListener(isHandler);
        restartButton.setActionCommand("restart");
        restartButton.setFocusPainted(false);

        //setup Quit button
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.green);
        quitButton.setFont(normalFont);
        quitButton.addActionListener(isHandler);
        quitButton.setActionCommand("exit");
        quitButton.setFocusPainted(false);

        //link everything together
        loseTextPanel.add(loseTextLabel);
        loseGameButtonPanel.add(restartButton);
        loseGameButtonPanel.add(quitButton);
        con.add(loseTextPanel);
        con.add(loseGameButtonPanel);

        //print dialogue to screen
        loseGameTextSetup();
    }

    @SuppressWarnings("ConstantConditions")
    public void createGameScreen() {

        introTextPanel.setVisible(false);
        playButtonPanel.setVisible(false);

        try {
            iconEast = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/East.png")));
            iconWest = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/West.png")));
            iconNorth = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/North.png")));
            iconSouth = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/South.png")));
            iconSettings = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Settings.png")));
            iconPlaySound = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/PlaySound.png")));
            iconStopSound = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/StopSound.png")));
            iconMap = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Map.png")));
            iconHelp = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Help.png")));
            iconZombie = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Zombie.png")));
            iconBag = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Bag.png")));
            iconHB14 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-14.png")));
            iconHB13 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-13.png")));
            iconHB12 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-12.png")));
            iconHB11 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-11.png")));
            iconHB10 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-10.png")));
            iconHB9 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-9.png")));
            iconHB8 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-8.png")));
            iconHB7 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-7.png")));
            iconHB6 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-6.png")));
            iconHB5 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-5.png")));
            iconHB4 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-4.png")));
            iconHB3 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-3.png")));
            iconHB2 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-2.png")));
            iconHB1 = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/HB-1.png")));





        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        //Header text area with the Player information
        playerPanel = new JPanel();
        playerPanel.setBounds(60, 15, 600, 60);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridBagLayout());
        con.add(playerPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 20);

        playerNameLabel = new JLabel(iconZombie);
        playerNameLabel.setFont(normalFont);
        playerNameLabel.setForeground(Color.green);
        playerPanel.add(playerNameLabel);

        playerNameLabelNumber = new JLabel();
        playerNameLabelNumber.setFont(normalFont);
        playerNameLabelNumber.setForeground(Color.yellow);
        playerPanel.add(playerNameLabelNumber, constraints);

        healthLabel = new JLabel();
        healthLabel.setFont(normalFont);
        healthLabel.setForeground(Color.green);
        playerPanel.add(healthLabel);

//        healthLabelNumber = new JLabel();
//        healthLabelNumber.setFont(normalFont);
//        healthLabelNumber.setForeground(Color.yellow);
//        playerPanel.add(healthLabelNumber, constraints);

        inventoryLabel = new JLabel(iconBag);
        inventoryLabel.setFont(normalFont);
        inventoryLabel.setForeground(Color.green);
        playerPanel.add(inventoryLabel);

        inventoryLabelNumber = new JLabel();
        inventoryLabelNumber.setFont(normalFont);
        inventoryLabelNumber.setForeground(Color.yellow);
        playerPanel.add(inventoryLabelNumber, constraints);

        // Main Body of Text
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(70, 80, 660, 280);
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setLayout(new GridLayout(10, 1));
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

        //Output area for various function like talk, look, attack, etc
        outputPanel = new JPanel();
        outputPanel.setBounds(70, 385, 630, 90);
        outputPanel.setBackground(Color.black);
        outputPanel.setLayout(new GridLayout(2, 1));
        con.add(outputPanel);

        outputTitleLabel = new JLabel("Dialog:");
        outputTitleLabel.setForeground(Color.green);
        outputTitleLabel.setFont(gameFont);
        outputPanel.add(outputTitleLabel);

        outputLabel = new JLabel();
        outputLabel.setForeground(Color.yellow);
        outputLabel.setHorizontalAlignment(JLabel.LEFT);
        outputLabel.setFont(gameFont);
        outputPanel.add(outputLabel);

        //User input area
        userPromptPanel = new JPanel();
        userPromptPanel.setBounds(90, 500, 200, 25);           //y 415
        userPromptPanel.setBackground(Color.black);//change the color later
        userPromptPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        con.add(userPromptPanel);

        // adds the text to the area for the user prompt
        userPromptLabel = new JLabel("Enter a Command > ");
        userPromptLabel.setBackground(Color.black);
        userPromptLabel.setForeground(Color.pink);
        userPromptLabel.setFont(normalFont);
        userPromptPanel.add(userPromptLabel);

        userInputField = new JTextField();
        userInputField.setBounds(300, 505, 350, 25);    //y 420
        userInputField.setFont(normalFont);
        userInputField.setBackground(Color.pink);
        userInputField.setForeground(Color.black);
        userInputField.addActionListener(userInputHandler);
        con.add(userInputField);

        //Misc buttons area to implement
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(320, 590, 300, 45);  //y was 480
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(1, 4));
        con.add(choiceButtonPanel);

        choice1 = new JButton(iconHelp);
        choice1.setSize(10, 10);
        choice1.setBorderPainted(false);
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.green);
        choice1.setFont(normalFont);
        choiceButtonPanel.add(choice1);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choice1.setFocusPainted(false);
        choice1.setContentAreaFilled(false);

        choice2 = new JButton(iconMap);
        choice2.setBorderPainted(false);
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.green);
        choice2.setFont(normalFont);
        choiceButtonPanel.add(choice2);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choice2.setFocusPainted(false);
        choice2.setContentAreaFilled(false);

        choice3 = new JButton(iconSettings);
        choice3.setBorderPainted(false);
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.green);
        choice3.setFont(normalFont);
        choiceButtonPanel.add(choice3);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choice3.setFocusPainted(false);
        choice3.setContentAreaFilled(false);

        choice4 = new JButton(iconStopSound);
        choice4.setBorderPainted(false);
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.green);
        choice4.setFont(normalFont);
        choiceButtonPanel.add(choice4);
        choice4.setFocusPainted(false);
        choice4.addActionListener(volumeToggleHandler);
        choice4.setFocusPainted(false);
        choice4.setContentAreaFilled(false);
//        choice4.setActionCommand("c4");

        choice5 = new JButton("save");
        choice5.setBorderPainted(false);
        choice5.setBackground(Color.black);
        choice5.setForeground(Color.green);
        choice5.setFont(normalFont);
        choiceButtonPanel.add(choice5);
        choice5.setFocusPainted(false);
        choice5.addActionListener(choiceHandler);
        choice5.setFocusPainted(false);
        choice5.setContentAreaFilled(false);

        //Move buttons area to implement
        arrowPanel = new JPanel();
        arrowPanel.setBounds(160, 570, 100, 90);    // y was 460
        arrowPanel.setLayout(new GridLayout(3, 3));
        arrowPanel.setBackground(Color.black);
//        con.add(arrowPanel);

        arrowUp = new JButton(iconNorth);
        arrowUp.setBorderPainted(false);
        arrowUp.setBackground(Color.black);
        arrowUp.setForeground(Color.green);
        arrowUp.setFont(normalFont);
        arrowPanel.add(arrowUp);
        arrowUp.setFocusPainted(false);
        arrowUp.addActionListener(moveHandler);
        arrowUp.setActionCommand("a1");
        arrowUp.setContentAreaFilled(false);

        arrowRight = new JButton(iconEast);
        arrowRight.setBorderPainted(false);
        arrowRight.setBackground(Color.black);
        arrowRight.setForeground(Color.green);
        arrowRight.setFont(normalFont);
        arrowPanel.add(arrowUp);
        arrowRight.setFocusPainted(false);
        arrowRight.addActionListener(moveHandler);
        arrowRight.setActionCommand("a4");
        arrowRight.setContentAreaFilled(false);

        arrowDown = new JButton(iconSouth);
        arrowDown.setBorderPainted(false);
        arrowDown.setBackground(Color.black);
        arrowDown.setForeground(Color.green);
        arrowDown.setFont(normalFont);
        arrowPanel.add(arrowUp);
        arrowDown.setFocusPainted(false);
        arrowDown.addActionListener(moveHandler);
        arrowDown.setActionCommand("a2");
        arrowDown.setContentAreaFilled(false);

        arrowLeft = new JButton(iconWest);
        arrowLeft.setBorderPainted(false);
        arrowLeft.setBackground(Color.black);
        arrowLeft.setForeground(Color.green);
        arrowLeft.setFont(normalFont);
        arrowPanel.add(arrowUp);
        arrowLeft.setFocusPainted(false);
        arrowLeft.addActionListener(moveHandler);
        arrowLeft.setActionCommand("a3");
        arrowLeft.setContentAreaFilled(false);

        arrowPanel.add(new JPanel());
        arrowPanel.add(arrowUp);
        arrowPanel.add(new JPanel());
        arrowPanel.add(arrowLeft);
        arrowPanel.add(new JPanel());
        arrowPanel.add(arrowRight);
        arrowPanel.add(new JPanel());
//        arrowPanel.add(new JPanel()); // Leave this cell empty for spacing
        arrowPanel.add(arrowDown);

        for (Component component : arrowPanel.getComponents()) {
            if (component instanceof JPanel) {
                component.setBackground(Color.black);
            }
        }

        con.add(arrowPanel);

        // Create an audio panel
        audioPanel = new JPanel();
        audioPanel.setBounds(200, 690, 350, 30);        // y was 550
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

    public void introTextSetup(){
        System.setOut(printOutput);
        viewMain.gameStart();
        String textOutput = basicOutput.toString();
        String refactoredOutput = textOutput.replaceAll("\\x1B\\[[0-9;]*[mK]", "")
                .replaceAll("\\[\\s*\\]", "");
        introTextLabel.setText("<html><body>" + refactoredOutput + "</body></html");
        basicOutput.reset();
    }

    public void playerSetup() {
        Player player = mansion.getPlayer();
        String playerName = player.getName();
        int playerHealth = player.getHealth();

        if (playerHealth > 130 && playerHealth < 141) healthLabel.setIcon(iconHB14);
        if (playerHealth > 120 && playerHealth < 131) healthLabel.setIcon(iconHB13);
        if (playerHealth > 110 && playerHealth < 121) healthLabel.setIcon(iconHB12);
        if (playerHealth > 100 && playerHealth < 111) healthLabel.setIcon(iconHB11);
        if (playerHealth > 90 && playerHealth < 101) healthLabel.setIcon(iconHB10);
        if (playerHealth > 80 && playerHealth < 91) healthLabel.setIcon(iconHB9);
        if (playerHealth > 70 && playerHealth < 81) healthLabel.setIcon(iconHB8);
        if (playerHealth > 60 && playerHealth < 71) healthLabel.setIcon(iconHB7);
        if (playerHealth > 50 && playerHealth < 61) healthLabel.setIcon(iconHB6);
        if (playerHealth > 40 && playerHealth < 51) healthLabel.setIcon(iconHB5);
        if (playerHealth > 30 && playerHealth < 41) healthLabel.setIcon(iconHB4);
        if (playerHealth > 20 && playerHealth < 31) healthLabel.setIcon(iconHB3);
        if (playerHealth > 10 && playerHealth < 21) healthLabel.setIcon(iconHB2);
        if (playerHealth > 0 && playerHealth < 11) healthLabel.setIcon(iconHB1);
        // Loose statements go here.
        if (playerHealth <= 0){
            createLoseScreen();
        }

        List playerInventory = player.getInventory();

            //else do this stuff
            playerNameLabelNumber.setText("" + playerName);
            inventoryLabelNumber.setText("" + playerInventory);
            //healthLabelNumber.setText("" + playerHealth);

        //check inventory for grim and do win
        if (playerInventory.contains("grimoire")){
            createWinScreen();
        }

    }

    public void roomSetup() {

        Location currentLocation = mansion.getCurrentLocation();
        currentLocationLabelNumber.setText(currentLocation.getName());
        descriptionLabelNumber.setText("<html>" + currentLocation.getDescription() + "<html>");
        directionsLabelNumber.setText("" + currentLocation.getDirections().keySet());
        itemsLabelNumber.setText("" + currentLocation.getItems());
        creaturesLabelNumber.setText(Arrays.toString(currentLocation.getCharacters()));
    }

    public class UserInputHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            // if userInputField contains lowercase "quit"
            // this do this
            // else
            helper.handleUserInput(userInputField, outputLabel);
            playerSetup();
            roomSetup();
        }
    }

    public class TitleScreenHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            createIntroScreen();
        }
    }

    public class IntroScreenHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();

            switch (choice) {
                case "newGame":
                    try {
                        mansion = MyJsonReader.readMansion("JsonObjects.json");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "savedGame":
                    try {
                        mansion = MyJsonReader.readMansion("saved.json");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "restart":
                    backgroundMusic.close();
                    winMusic.close();
                    window.dispose();
                    try {
                        new GUI_Two();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    System.exit(0);
            }
            helper = new GUIFunctionality_Two(mansion);
            createGameScreen();
        }
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();

            switch (choice) {
                case "c1":
                    try {
                        TextParser.handleInput(mansion, "help", "");
                        helper.handleButtons("help", "", outputLabel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "c2":
                try {
                    TextParser.handleInput(mansion, "map", "");
                    helper.handleButtons("map", "", outputLabel);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                case "c3":
                    JOptionPane.showMessageDialog(null, "This Function is only available in the Paid version of the game. Please subscribe for full functionality.");
                    break;

                case "c4":
                    break;
                case "c5":
                    try {
                        MyJsonReader.writeMansion(mansion, "saved.json");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "game saved.");
                    break;
            }
        }
    }

    public class MoveHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();

            switch (choice) {
                case "a1":
                    try {
                        TextParser.handleInput(mansion, "move", "north");
                        helper.handleButtons("move", "north", outputLabel);
                        playerSetup();
                        roomSetup();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "a2":
                    try {
                        TextParser.handleInput(mansion, "move", "south");
                        helper.handleButtons("move", "south", outputLabel);
                        playerSetup();
                        roomSetup();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "a3":
                    try {
                        TextParser.handleInput(mansion, "move", "west");
                        helper.handleButtons("move", "west", outputLabel);
                        playerSetup();
                        roomSetup();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "a4":
                    try {
                        TextParser.handleInput(mansion, "move", "east");
                        helper.handleButtons("move", "east", outputLabel);
                        playerSetup();
                        roomSetup();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }

    public class VolumeToggleHandler implements ActionListener {
        boolean isMuted = false; //changed to start the game muted
        Icon iconSound;
        @Override
        public void actionPerformed(ActionEvent event) {
            if(!isMuted) {
                backgroundMusic.stop();
                choice4.setIcon(iconPlaySound);
                isMuted = true;
                //JOptionPane.showMessageDialog(null, "Music muted");
            } else {
                backgroundMusic.play("music");
                choice4.setIcon(iconStopSound);
                isMuted = false;
                //JOptionPane.showMessageDialog(null, "Music unMuted");
            }
            choice4.setFocusPainted(false);
            choice4.setContentAreaFilled(false);
        }

    }

    public class VolumeHandler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent event) {
            JSlider source = (JSlider) event.getSource();
            if (!source.getValueIsAdjusting()) {
                float volume = (float) source.getValue() / 100;
                if(volumeToggleHandler.isMuted == false) {
                    backgroundMusic.setVolume("music", volume);
                }
            }
        }
    }

    private void loadFileContent() {

        System.setOut(printOutput);
        String fileName = "textFiles/Welcome_Screen.txt";
        StringBuilder htmlText = new StringBuilder("<html>");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(GUI_Two.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace(" ", "&nbsp;");
                System.out.println(line + "\n");
                htmlText.append(line).append("<br>");
                //titleGameLabel.setText(titleGameLabel.getText() + "<html>" + line + "<br></html>");
            }
            htmlText.append("</html>");
            String textOutput = basicOutput.toString();
            basicOutput.reset();
            titleGameLabel.setText(htmlText.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void winGameTextSetup(){
        System.setOut(printOutput);
        winMusic.play("music");
        String fileName = "textFiles/Win_Text.txt";
        StringBuilder htmlText = new StringBuilder("<html>");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(GUI_Two.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace(" ", "&nbsp;");
                System.out.println(line + "\n");
                htmlText.append(line).append("<br>");
                //titleGameLabel.setText(titleGameLabel.getText() + "<html>" + line + "<br></html>");
            }
            htmlText.append("</html>");
            String textOutput = basicOutput.toString();
            basicOutput.reset();
            winTextLabel.setText(htmlText.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loseGameTextSetup(){
        System.setOut(printOutput);
        String fileName = "textFiles/Lose_Text.txt";
        StringBuilder htmlText = new StringBuilder("<html>");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(GUI_Two.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace(" ", "&nbsp;");
                System.out.println(line + "\n");
                htmlText.append(line).append("<br>");
                //titleGameLabel.setText(titleGameLabel.getText() + "<html>" + line + "<br></html>");
            }
            htmlText.append("</html>");
            String textOutput = basicOutput.toString();
            basicOutput.reset();
            loseTextLabel.setText(htmlText.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
