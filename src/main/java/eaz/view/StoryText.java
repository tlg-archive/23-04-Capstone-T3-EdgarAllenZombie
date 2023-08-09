package eaz.view;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class StoryText {
    GeneralViewItems genItems = new GeneralViewItems();
    String red = genItems.red;
    String green = genItems.green;
    String colorReset = genItems.colorReset;
    String doubleLines = genItems.doubleLines;
    String singleLines = genItems.singleLines;

    void gameStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s " +
                "  You are 18 year old Edgar Allen, \n" +
                "   known to your friends as Edgar Allen \"Zombie\" \n " +
                "  due to your unique fascination with all things dark and grim. \n " +
                "  You enjoy searching through abandoned buildings others deem haunted,\n " +
                "  just to get that feeling of excitement and the tingle of unknown, \n " +
                "  but all good things come to an end when you always find the open window \n " +
                "  or creaky floorboard that caused others to believe in ghosts. \n" +
                "%s ",doubleLines, singleLines);
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
        genItems.clearScreen();
    }

}   // END OF CLASS