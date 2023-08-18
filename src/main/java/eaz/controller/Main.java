package eaz.controller;

import eaz.view.ViewMain;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to:");
        System.out.println("1) Play the original game (console-based)");
        System.out.println("2) See the GUI-based game");
        System.out.println("Please enter '1' or '2':");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        if (choice == 1) {
            ViewMain viewMain = new ViewMain();
            EAZ eaz = new EAZ();
            viewMain.introScreen();
            eaz.run();
        } else if (choice == 2) {
            GUI runGUI = new GUI();
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }
}