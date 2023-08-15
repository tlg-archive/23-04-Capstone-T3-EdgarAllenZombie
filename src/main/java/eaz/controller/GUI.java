package eaz.controller;

import javax.swing.*;

import java.awt.*;


public class GUI {
    // Declare objects for the game window
    JFrame gameWindow;
    Container container;
    JPanel titlePanel;
    JPanel startButtonPanel;
    JButton startButton;
    JLabel titleLabel;
    Font titleFont = new Font("Times New Roman", Font.BOLD, 60);
    Font startButtonFont = new Font("Times New Roman", Font.PLAIN, 16);

    //Ctor
    public GUI(){
        gameWindow = new JFrame();
        gameWindow.setSize(800, 600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setBackground(Color.darkGray);
        gameWindow.setLayout(null);
        gameWindow.setVisible(true);
        container = gameWindow.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.BLACK);
        titleLabel = new JLabel("Edgar Allen Zombie");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300,400,200,100);
        startButtonPanel.setBackground(Color.BLACK);

        startButton = new JButton("START");
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.BLACK);
        startButton.setFont(startButtonFont);

        titlePanel.add(titleLabel);
        startButtonPanel.add(startButton);
        container.add(titlePanel);
        container.add(startButtonPanel);
    }

    //This main is for testing purposes only, to periodically test adjustments to the GUI.
    public static void main(String[] args) {
        new GUI();
    }
}