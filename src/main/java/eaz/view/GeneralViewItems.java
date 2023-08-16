package eaz.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class GeneralViewItems {
    public final String doubleLines = "=============================================================================================";
    public final String singleLines = "---------------------------------------------------------------------------------------------";
    public final String starLines = "*********************************************************************************************";
    public final String red = "\u001B[31m";
    public final String green = "\u001B[32m";
    public final String white= "\033[1;37;40m";
    public final String yellow = "\033[1;33;40m";
    public final String cyan = "\033[1;36;40m";
    public final String purple = "\033[1;35;40m";
    public final String colorReset = "\u001B[0m";

    void clearScreen(){
        int i;
        for (i = 0; i < 71; i++){
            System.out.println();
        }
    }

    void pauseScreen(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press any key to continue...");
        scanner.nextLine();
    }

    void sleepTimer() throws InterruptedException {
        Thread.sleep(1000);
    }

    void printTextFile(String fileName, String color) {
        // Prints the opening splash screen
        //noinspection ConstantConditions
        try (BufferedReader br = new BufferedReader(new InputStreamReader(GameLoopDisplay.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(color + line + white);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}   // END OF CLASS