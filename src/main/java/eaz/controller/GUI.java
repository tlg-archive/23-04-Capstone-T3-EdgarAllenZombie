package eaz.controller;

import javax.swing.*;

import java.awt.*;


public class GUI {
    // Declare objects for the game window
    JFrame gameWindow;
    Container container;
    JPanel titlePanel;
    JLabel titleLabel;
    Font titleFont = new Font("Times New Roman", Font.BOLD, 60);

    //Ctor
    public GUI(){
        // Initialize the JFrame window
        gameWindow = new JFrame();

        // Sets the size of the game window to 800x600 pixels
        gameWindow.setSize(800, 600);

        // Defines what happens when the user closes the window (exit the application)
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the background color of the content pane of the window to dark gray
        gameWindow.getContentPane().setBackground(Color.darkGray);

        // Disables the layout manager, allowing manual positioning of components
        gameWindow.setLayout(null);

        // Makes the game window visible to the user
        gameWindow.setVisible(true);

        // Get the content pane of the game window and store it in the 'container' variable
        container = gameWindow.getContentPane();

        // Create a JPanel for the title
        titlePanel = new JPanel();

        // Set the position and size of the title panel
        titlePanel.setBounds(100, 100, 600, 150);

        // Set the background color of the title panel to red
        titlePanel.setBackground(Color.RED);

        // Create a JLabel for the title text
        titleLabel = new JLabel("Edgar Allen Zombie");

        // Set the text color of the title label to black
        titleLabel.setForeground(Color.BLACK);

        // Set the font of the title label
        titleLabel.setFont(titleFont);

        // Add the title label to the title panel
        titlePanel.add(titleLabel);

        // Add the title panel to the main content pane
        container.add(titlePanel);
    }

    //This main is for testing purposes only, to periodically test adjustments to the GUI.
    public static void main(String[] args) {
        new GUI();
    }
}