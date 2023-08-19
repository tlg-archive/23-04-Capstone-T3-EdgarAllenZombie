package eaz.view;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


class StoryText {
    GeneralViewItems genItems = new GeneralViewItems();
    String red = genItems.red;
    String green = genItems.green;
    String yellow = genItems.yellow;
    String colorReset = genItems.colorReset;
    String doubleLines = genItems.doubleLines;
    String singleLines = genItems.singleLines;

    void gameStart(){
        genItems.printTextFile("textFiles/Description.txt", green);
//        System.out.printf("%s " +
//                "  You are 18 year old Edgar Allen, \n" +
//                "  known to your friends as Edgar Allen \"Zombie\" \n " +
//                "  due to your unique fascination with all things dark and grim. \n " +
//                "  You enjoy searching through abandoned buildings others deem haunted,\n " +
//                "  just to get that feeling of excitement and the tingle of unknown, \n " +
//                "  but all good things come to an end when you always find the open window \n " +
//                "  or creaky floorboard that caused others to believe in ghosts. \n" +
//                "%s ",doubleLines, singleLines);
//        System.out.println("             Press Enter to continue.....");
//        scanner.nextLine();
//        System.out.printf(
//                "  Tonight you find yourself in another ordinary abandoned mansion, \n" +
//                "  determined to enjoy the thrill for as long as you can.  \n" +
//                "  There\'s plenty of rooms in this one that could hold untold mysteries. \n" +
//                "  Why not enjoy it while it lasts.\n" +
//                "%s", singleLines);
//        System.out.println("             Press Enter to continue.....");
//        scanner.nextLine();
        System.out.printf(green + "To control Edgar, use basic commands like '%sGo%s %sNorth%s', '%sGet%s %sKnife%s', '%sLook%s' or '%sSearch%s %sdesk'.\n\n" +
                "%s", yellow, colorReset,red, colorReset, yellow, colorReset,
                red, colorReset, yellow, colorReset, yellow, colorReset, red, colorReset);
        genItems.pauseScreen();
        genItems.clearScreen();
    }

    void winGame(){
        Scanner scanner = new Scanner(System.in);
        Music winFX = new Music("fx", "audioFiles/win.wav");
        winFX.play("fx");
        System.out.printf("%s " +
                "                              CONGRATULATIONS, \n" +
                "              You have completed the current version of this game! \n " +
                "             EAZ is still in development and there is more to come. \n " +
                "     Like and subscribe to get regular emails as we get them pushed out.\n " +
                "  You can visit our socials %sGroup2IsAwesome%s for the most up to date info\n " +
                "  We hope you enjoyed your time playing and we hope you have the day you deserve \n " +
                "%s ",doubleLines, green, colorReset, singleLines);
        genItems.pauseScreen();
    }

}   // END OF CLASS