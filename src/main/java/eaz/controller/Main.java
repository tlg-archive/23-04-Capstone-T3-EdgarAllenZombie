package eaz.controller;

import eaz.view.GeneralViewItems;
import eaz.view.ViewMain;
import eaz.view.GeneralViewItems;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        GeneralViewItems genItems = new GeneralViewItems();
        String green = genItems.green;
        String white = genItems.white;
        Scanner scanner = new Scanner(System.in);

        System.out.println(green + "Would you like to:");
        System.out.println("1) Play the original game (console-based)");
        System.out.println("2) See the GUI-based game");
        System.out.print("Please enter '1' or '2'> " + white);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        if (choice == 1) {
            ViewMain viewMain = new ViewMain();
            EAZ eaz = new EAZ();
            viewMain.introScreen();
            eaz.run();
        } else if (choice == 2) {
            GUI_Two runGUI = new GUI_Two();
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }
}