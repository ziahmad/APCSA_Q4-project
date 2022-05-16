package src.Objects;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import src.Main.Consts;
import src.Main.GamePanel;

public class SuperObject {
   
   public BufferedImage image;   
   public String name;
   public boolean collision = false;
   public int absX, absY;
   public int worldX, worldY;
   public int screenX, screenY;

   public Rectangle solidArea;

   public SuperObject(int x, int y)
   {
      
      absX=x*Consts.TILE_SIZE;
      absY=y*Consts.TILE_SIZE;
      screenX=x%Consts.MAX_SCREEN_COL;
      screenY=y%Consts.MAX_SCREEN_ROW;
      worldX=x/Consts.MAX_SCREEN_COL;
      worldY=x/Consts.MAX_SCREEN_ROW;
      solidArea = new Rectangle(absX,absY,Consts.TILE_SIZE,Consts.TILE_SIZE);
   }

   public void draw(Graphics2D g2, GamePanel gp)
   {


             g2.drawImage(image,screenX*Consts.TILE_SIZE,screenY*Consts.TILE_SIZE,Consts.TILE_SIZE,Consts.TILE_SIZE,null);
             
             g2.draw(solidArea);
         

      
   }

}
