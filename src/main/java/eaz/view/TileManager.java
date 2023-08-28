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
        String locationName = location.getName();




        loadMap("textFiles/foyer.txt");

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


    public void loadMap(String filePath) {

         Location location = GUI_Two.getMansion().getCurrentLocation();
         String roomInventory = location.getItems().toString();
         String content = roomInventory.substring(1, roomInventory.length() - 1).trim();
         String[] items = content.split(", ");
         ArrayList<String> arlist = new ArrayList<String>(java.util.List.of(items));
         //JOptionPane.showMessageDialog(null, "THIS IS THE CURRENT LOCATION " + arlist);


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

            switch (tileNumber){
                case 2:
                    //JOptionPane.showMessageDialog(null, "You Clicked on the Ghost");
                    gp.getGui().getHelper().handleButtons("talk", "ghost", gp.getGui().getOutputLabel());
                    break;
            }

//            for(Item currItem : mansion.getItems()) {
//                String clickedItem = currItem.getName();
//                if(itemClicked(currItem, x, y)) {
//                    try {
//                        TextParser.handleInput(mansion, "get", clickedItem);
//                        break;
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//
//            for (eaz.model.Character currChar : mansion.getCharacters()) {
//                String clickedChar = currChar.getName();
//                if(characterClicked(currChar, x, y)) {
//                    try {
//                        TextParser.handleInput(mansion, "talk", clickedChar);
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
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


    private boolean itemClicked(Item item, int mouseX, int mouseY) {
        int itemBoundX = 0;
        int itemBoundY = 0;
        // Implement logic to check if the mouse click is within the item's bounds
        if(mouseX == itemBoundX && mouseY == itemBoundY) {
            // Return true if clicked, false otherwise
            return true;
        }
        return false;
    }

    private boolean characterClicked(eaz.model.Character character, int mouseX, int mouseY) {
        int charBoundX = 0;
        int charBoundY = 0;
        // Implement logic to check if the mouse click is within the character's bounds
        return (mouseX == charBoundX && mouseY == charBoundY);
            // Return true if clicked, false otherwise
    }
}
