package eaz.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Character extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Character(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getCharacterImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getCharacterImage() {
        try{
            charZombie = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/Zombie.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        } else
        if(keyH.downPressed == true) {
            direction = "down";
            y += speed;
        } else
        if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        } else
        if (keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image =   null;
        switch(direction) {
            case "up":
                image = charZombie;
                break;
            case "down":
                image = charZombie;
                break;
            case "left":
                image = charZombie;
                break;
            case "right":
                image = charZombie;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }


}