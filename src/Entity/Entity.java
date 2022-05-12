package src.Entity;

import java.awt.image.BufferedImage;
//super class or all entities
public class Entity {
    public double worldX, worldY;
    public double speed;

    public BufferedImage up1, down1, right1, left1, up2, down2, right2, left2;
    public String direction;

    public int spriteCounter=0;
    public int spriteNum=1;
}
