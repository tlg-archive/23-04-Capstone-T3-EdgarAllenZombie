package eaz.view;

class GeneralViewItems {
    public final String doubleLines = "=============================================================================================\n";
    public final String singleLines = "---------------------------------------------------------------------------------------------\n";
    public final String starLines = "*********************************************************************************************\n";
    public final String red = "\u001B[31m";
    public final  String green = "\u001B[32m";
    public final String colorReset = "\u001B[0m";

    void clearScreen(){
        int i;
        for (i = 0; i < 41; i++){
            System.out.println();
        }
    }

    void sleepTimer() throws InterruptedException {
        Thread.sleep(1000);
    }



}   // END OF CLASS