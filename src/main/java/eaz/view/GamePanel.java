//package eaz.view;
//
//import eaz.controller.GUI;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.*;
//import java.awt.*;
//import java.io.IOException;
//
//class GamePanel extends JPanel{
//
//
//    // Screen settings
//    final int originalTileSize = 16;  //16x16 tile
//    final int scale = 3;              //scaling vairable
//
//    final int tileSize = originalTileSize * scale;
//    final int maxScreenCol = 16;
//    final int maxScreenRow = 12;
//    final int screenWidth = tileSize * maxScreenCol;   // 768 pixels
//    final int screenHeight = tileSize * maxScreenRow;  // 576 pixels
//
//    Thread gameThread;
//
//    public GamePanel() {
//        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//        this.setBackground(Color.black);
//        this.setDoubleBuffered(true);
//    }
//
//    public void startGameThread(){
//        gameThread = new Thread(this);
//        gameThread.start();
//    }
//
//    @Override
//    public void run(){
//
//        while(gameThread != null) {
//
//        }
//
//    }
//
//
//
//    public static void main(String[] args) {
//
//        JFrame window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
//        window.setTitle("2D Adventure");
//
//        GamePanel gamePanel = new GamePanel();
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
//}