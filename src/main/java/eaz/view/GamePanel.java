package eaz.view;

import eaz.controller.GUI_Two;
import eaz.controller.TextParser;
import eaz.model.Mansion;

import javax.swing.JPanel;
import java.awt.*;
import java.lang.*;

public class GamePanel extends JPanel implements Runnable{


    // Screen settings
    public final int originalTileSize = 10;  //10x10 tile
    public final int scale = 3;              //scaling vairable

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 9;
    public final int maxScreenRow = 6;
    public final int screenWidth = tileSize * maxScreenCol;   // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;  // 576 pixels

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread = new Thread();
    Character character = new Character(this, keyH);

    private Mansion mansion;
    private TextParser textParser;
    private final GUI_Two gui;


    public GamePanel(GUI_Two gui) {
        this.gui = gui;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public GUI_Two getGui() {
        return gui;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS;   //0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
       tileM.loadMap("textFiles/foyer.txt");
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        //character.draw(g2);
        g2.dispose();
    }

//    public static void main(String[] args) {
//
//        JFrame window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
//        window.setTitle("FOYER");
//
//        GamePanel gamePanel = new GamePanel(mansion);
//
//        window.add(gamePanel);
//
//        window.pack();
//
//        window.setLocationRelativeTo(null);
//        window.setVisible(true);
//
//        gamePanel.startGameThread();
//
//   }
}