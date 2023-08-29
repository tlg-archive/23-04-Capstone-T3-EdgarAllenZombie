package eaz.view;

import eaz.controller.GUI_Two;
import eaz.model.Item;
import eaz.model.Location;
import eaz.model.Mansion;

import eaz.model.MyJsonReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];


    public  TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[15];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        Location location = GUI_Two.getMansion().getCurrentLocation();
        gp.addMouseListener(new MouseClick());
    }

    public void getTileImage() {

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/floor.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/wall.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/ghost.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/key_map.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/edgar_map.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/knife_map.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/bat_map.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/diary_map.png"));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/grimoire_map.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/jacket_map.png"));
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/plate_map.png"));
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Zombie2.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {

         Location location = GUI_Two.getMansion().getCurrentLocation();
         String roomInventory = location.getItems().toString();
         String content = roomInventory.substring(1, roomInventory.length() - 1).trim();
         String[] items = content.split(", ");
         ArrayList<String> arlist = new ArrayList<String>(java.util.List.of(items));
         //JOptionPane.showMessageDialog(null, "THIS IS THE CURRENT LOCATION " + arlist);
        String locationName = location.getName();
        String filePath = "textFiles/foyer.txt";

        switch (locationName){
            case "Foyer":
                filePath = "textFiles/foyer.txt";
                break;
            case "Hallway 1E":
                filePath = "textFiles/hallway1e.txt";
                break;
            case "Hallway 1W":
                filePath = "textFiles/hallway1w.txt";
                break;
            case "Coat Closet":
                filePath = "textFiles/coatcloset.txt";
                break;
            case "Butler's Room":
                filePath = "textFiles/butlersroom.txt";
                break;
            case "Secret Room":
                filePath = "textFiles/secretroom.txt";
                break;
            case "Kitchen":
                filePath = "textFiles/kitchen.txt";
                break;
            case "Dining Room":
                filePath = "textFiles/diningroom.txt";
                break;
            case "Hallway 2S":
                filePath = "textFiles/hallway2s.txt";
                break;
            case "Hallway 2N":
                filePath = "textFiles/hallway2n.txt";
                break;
            case "Ballroom":
                filePath = "textFiles/ballroom.txt";
                break;
            case "Storage Room":
                filePath = "textFiles/storageroom.txt";
                break;
            case "Home Office":
                filePath = "textFiles/homeoffice.txt";
                break;
            case "Hallway 3E":
                filePath = "textFiles/hallway3e.txt";
                break;
            case "Hallway 3W":
                filePath = "textFiles/hallway3w.txt";
                break;
            case "Bedroom":
                filePath = "textFiles/bedroom.txt";
                break;
            case "Master Bedroom":
                filePath = "textFiles/masterbedroom.txt";
                break;
            case "Bathroom":
                filePath = "textFiles/bathroom.txt";
                break;
            case "Library":
                filePath = "textFiles/library.txt";
                break;
        }

        try {

            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            int length;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);;
                    if (row == 4  && col > 0 && arlist.size() > 0) {
                        String item = arlist.get(0);
                        switch (item) {
                            case "key":
                                mapTileNum[col][row] = 3;
                                arlist.remove(item);
                                break;
                            case "knife":
                                mapTileNum[col][row] = 5;
                                arlist.remove(item);
                                break;
                            case "bat":
                                mapTileNum[col][row] = 6;
                                arlist.remove(item);
                                break;
                            case "diary":
                                mapTileNum[col][row] = 7;
                                arlist.remove(item);
                                break;
                            case "grimoire":
                                mapTileNum[col][row] = 8;
                                arlist.remove(item);
                                break;
                            case "jacket":
                                mapTileNum[col][row] = 9;
                                arlist.remove(item);
                                break;
                            case "plate":
                                mapTileNum[col][row] = 10;
                                arlist.remove(item);
                                break;
                            default:
                                mapTileNum[col][row] = num;
                        }
                    } else {
                        mapTileNum[col][row] = num;

                    }
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }

    }
    public void draw(Graphics2D g2) {
       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;

       while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

           int tileNum = mapTileNum[col][row];
           g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
           col++;
           x += gp.tileSize;

           if(col == gp.maxScreenCol) {
               col = 0;
               x = 0;
               row++;
               y += gp.tileSize;
           }
       }
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

                    JOptionPane.showMessageDialog(null, "You Clicked on the Ghost");
        }

    }

    private class MouseClick implements MouseListener {

        private Mansion mansion = GUI_Two.getMansion();
        private GUI_Two g2;


        @Override
        public void mouseClicked(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            int row = y/gp.tileSize;
            int col = x/gp.tileSize;
            int tileNumber = mapTileNum[col][row];
            int getResponse;
            String[] responses = {"Talk", "Attack"};
            ImageIcon icon;

            switch (tileNumber){
                case 2:
                    icon = new ImageIcon(getClass().getClassLoader().getResource("images/iconGhost.png"));
                    getResponse = JOptionPane.showOptionDialog(null,
                            "Do you want to talk to or attack the Ghost?",
                            "Talk/Attack Ghost?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            icon,
                            responses,
                            0);
                    switch (getResponse){
                        case 0:
                            gp.getGui().getHelper().handleButtons("talk", "ghost", gp.getGui().getOutputLabel());
                            break;
                        case 1:
                            gp.getGui().getHelper().handleButtons("attack", "ghost", gp.getGui().getOutputLabel());
                            break;
                    }
                    break;
                case 3:
                    gp.getGui().getHelper().handleButtons("get", "key", gp.getGui().getOutputLabel());
                    break;
                case 4:
                    icon = new ImageIcon(getClass().getClassLoader().getResource("images/Edgar.png"));
                    JOptionPane.showOptionDialog(null, "This is you!!!", "Edgar Allen Zombie",
                            JOptionPane.PLAIN_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            icon,
                            null,
                            0);
                    break;
                case 5:
                    gp.getGui().getHelper().handleButtons("get", "knife", gp.getGui().getOutputLabel());
                    break;
                case 6:
                    gp.getGui().getHelper().handleButtons("get", "bat", gp.getGui().getOutputLabel());
                    break;
                case 7:
                    gp.getGui().getHelper().handleButtons("get", "diary", gp.getGui().getOutputLabel());
                    break;
                case 8:
                    gp.getGui().getHelper().handleButtons("get", "grimoire", gp.getGui().getOutputLabel());
                    break;
                case 9:
                    gp.getGui().getHelper().handleButtons("get", "jacket", gp.getGui().getOutputLabel());
                    break;
                case 10:
                    gp.getGui().getHelper().handleButtons("get", "plate", gp.getGui().getOutputLabel());
                    break;
                case 11:
                    icon = new ImageIcon(getClass().getClassLoader().getResource("images/iconZombie.png"));
                    getResponse = JOptionPane.showOptionDialog(null,
                            "Do you want to talk to or attack the Zombie?",
                            "Talk/Attack Zombie?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            icon,
                            responses,
                            0);
                    switch (getResponse){
                        case 0:
                            gp.getGui().getHelper().handleButtons("talk", "zombie", gp.getGui().getOutputLabel());
                            break;
                        case 1:
                            gp.getGui().getHelper().handleButtons("attack", "zombie", gp.getGui().getOutputLabel());
                            break;
                    }
            }
            gp.getGui().playerSetup();
            gp.getGui().roomSetup();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
