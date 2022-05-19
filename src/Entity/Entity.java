package src.Entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
//super class or all entities
public class Entity {
   //wichever screen the entity is on
    public int worldX, worldY;
    //wichever eneity is on a screen
    public double screenX;
    public double screenY;
    //position on map as a whole
    public double absX,absY;
    public double speed;

    //sprite animation
    public BufferedImage up0, down0, right0, left0, up1, down1, right1, left1, up2, down2, right2, left2;
    public String direction;

    public int spriteCounter=0;
    public int spriteNum=1;

    //for collision checks
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
