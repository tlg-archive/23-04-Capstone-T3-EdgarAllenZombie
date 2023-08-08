package eaz.view;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StoryText {

    static String doubleLines = "=============================================================================================\n";
    static String singleLines = "---------------------------------------------------------------------------------------------\n";
    static String starLines =   "*********************************************************************************************\n";
    static String red = "\u001B[31m";
    static String green = "\u001B[32m";
    static String colorReset = "\u001B[0m";

    public static void introScreen(){
        clearScreen();
        titleScreen();
    }

    public static void clearScreen(){
        int i;
        for (i = 0; i < 41; i++){
            System.out.println();
        }
    }

    public static void titleScreen() {

        System.out.printf("" +
                " ___       __   _______   ___       ________  ________  _____ ______   _______           _________  ________         \n" +
                "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\         |\\___   ___\\\\   __  \\        \n" +
                "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|        \\|___ \\  \\_\\ \\  \\|\\  \\       \n" +
                " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__           \\ \\  \\ \\ \\  \\\\\\  \\      \n" +
                "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\           \\ \\  \\ \\ \\  \\\\\\  \\     \n" +
                "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\           \\ \\__\\ \\ \\_______\\    \n" +
                "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______|\\|__|     \\|__|\\|_______|            \\|__|  \\|_______|    \n" +
                "                                                                                                                     \n" +
                "                                                                                                                     \n" +
                "                                                                                                                     \n" +
                "                                            _______   ________  ________                                             \n" +
                "                                           |\\  ___ \\ |\\   __  \\|\\_____  \\                                            \n" +
                "                                           \\ \\   __/|\\ \\  \\|\\  \\\\|___/  /|                                           \n" +
                "                                            \\ \\  \\_|/_\\ \\   __  \\   /  / /                                           \n" +
                "                                             \\ \\  \\_|\\ \\ \\  \\ \\  \\ /  /_/__                                          \n" +
                "                                              \\ \\_______\\ \\__\\ \\__\\\\________\\                                        \n" +
                "                                               \\|_______|\\|__|\\|__|\\|_______|                                        \n" +
                "                                                                                                                     \n" +
                "                                                                                \n \n \n" +
                "                                     Uncover your dark side and grow to epic proportions!    \n \n");
    }

    public static void displayPlayerStats(String name, int health, String[] inventory){
        System.out.println("Name: " + name);
        System.out.println("Your health " + health);
        System.out.println("Your inventory is: " + Arrays.toString(inventory));
    }

    public static void printDoubleLine(){
        System.out.println(doubleLines);
    }
    public static void printSingleLine(){
        System.out.println(singleLines);
    }
    public static void printStarLine(){
        System.out.println(starLines);
    }

    public static void sleepTimer() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void gameStart() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s " +
                "  You are 18 year old Edgar Allen, \n" +
                "   known to your friends as Edgar Allen \"Zombie\" \n " +
                "  due to your unique fascination with all things dark and grim. \n " +
                "  You enjoy searching through abandoned buildings others deem haunted,\n " +
                "  just to get that feeling of excitement and the tingle of unknown, \n " +
                "  but all good things come to an end when you always find the open window \n " +
                "  or creaky floorboard that caused others to believe in ghosts. \n" +
                "%s ",
                doubleLines, singleLines);
        System.out.println("             Press Enter to continue.....");
        scanner.nextLine();
        System.out.printf(
                "  Tonight you find yourself in another ordinary abandoned mansion, \n" +
                "  determined to enjoy the thrill for as long as you can.  \n" +
                "  There’s plenty of rooms in this one that could hold untold mysteries. \n" +
                "  Why not enjoy it while it lasts.\n" +
                "%s", singleLines);
        System.out.println("             Press Enter to continue.....");
        scanner.nextLine();
        System.out.printf(
                "  To control Edgar, use basic commands like '%sGo%s %sNorth%s', '%sGet%s %sKnife%s', '%sLook%s' or '%sSearch%s %sdesk%s'.\n\n" +
                "%s", green, colorReset,red, colorReset, green, colorReset,
                red, colorReset, green, colorReset, green, colorReset, red, colorReset, doubleLines);
        System.out.println("             Press Enter to continue.....");
        scanner.nextLine();
        clearScreen();
    }


    public static void textHelp(){
        System.out.printf("%s\nTo control Edgar, use basic commands like '%sGo%s %sNorth%s', '%sGet%s %sKnife%s', '%sLook%s' or '%sSearch%s %sdesk%s'.\n %s\n\n",
                doubleLines, green, colorReset,red, colorReset, green, colorReset,
                red, colorReset, green, colorReset, green, colorReset, red, colorReset, doubleLines);
    }

}   // END OF CLASS