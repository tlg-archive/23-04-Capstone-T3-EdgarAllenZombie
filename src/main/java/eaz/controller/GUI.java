package eaz.controller;

import javax.swing.*;
import java.awt.*;

public class GUI {
    // Declare JFrame and Container objects for the game window
    JFrame gameWindow;
    Container container;

    //Ctor
    public GUI(){
        // Initialize the JFrame window
        gameWindow = new JFrame();

        // Sets the size of the game window to 800x600 pixels
        gameWindow.setSize(1200, 800);

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
    }

    //This main is for testing purposes only, to periodically test adjustments to the GUI.
    public static void main(String[] args) {
        new GUI();
    }
}