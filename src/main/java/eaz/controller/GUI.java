package eaz.controller;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {

    // Declare objects for the game window
    JFrame gameWindow;
    Container container;
    JPanel titlePanel, startButtonPanel, gameTextPanel;
    JButton startButton;
    JLabel titleLabel;
    Font titleFont = new Font("Times New Roman", Font.BOLD, 60);
    Font startButtonFont = new Font("Times New Roman", Font.PLAIN, 16);
    Font normalFont = new Font("Times New Roman", Font.PLAIN,12);
    JTextArea gameTextDisplayArea;

    //This main is for testing purposes only, to periodically test adjustments to the GUI.
    public static void main(String[] args) {

        new GUI();
    }

    //Ctor
    public GUI(){

        gameWindow = new JFrame();
        gameWindow.setSize(800, 600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setBackground(Color.darkGray);
        gameWindow.setLayout(null); // absolute layout
        container = gameWindow.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.BLACK);//change color later
        titleLabel = new JLabel("Edgar Allen Zombie");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300,400,200,100);
        startButtonPanel.setBackground(Color.BLACK);//change color later, this is so i can work on the bounds

        startButton = new JButton("START");
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.BLACK);
        startButton.setFont(startButtonFont);
        startButton.addActionListener((event) -> createGameScreen());

        titlePanel.add(titleLabel);
        startButtonPanel.add(startButton);

        container.add(titlePanel);
        container.add(startButtonPanel);
        gameWindow.setVisible(true);//set visible need to be called after all contents are set
        //gameWindow.pack(); //this is something that nick had me do, not a fan if i dont need it.

    }

    public void createGameScreen(){

        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        //container.removeAll();
        gameTextPanel = new JPanel();
        gameTextPanel.setBounds(100,100,600,250);
        gameTextPanel.setBackground(Color.blue);//change color later
        container.add(gameTextPanel);

        gameTextDisplayArea = new JTextArea("Game Text will be displayed here!!!, Ticket 598 complete!!!");
        gameTextDisplayArea.setBounds(100,100,600,250);
        gameTextDisplayArea.setBackground(Color.black);//change color to dark grey later
        gameTextDisplayArea.setForeground(Color.white);
        gameTextDisplayArea.setFont(normalFont);
        gameTextDisplayArea.setLineWrap(true);
        gameTextPanel.add(gameTextDisplayArea);
    }

}