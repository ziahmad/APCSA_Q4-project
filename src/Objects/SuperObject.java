package src.Objects;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import src.Main.Consts;
import src.Main.GamePanel;

public class SuperObject {
   
   public BufferedImage image;   
   public String name;
   public boolean collision = false;
   public int absX, absY;

   public void draw(Graphics2D g2, GamePanel gp)
   {

      for (int col = 0; col < Consts.MAX_SCREEN_COL; col++) {
         for (int row = 0; row < Consts.MAX_SCREEN_ROW; row++) {
             //int tileNum = mapTileNum[col+(Player.worldY*Consts.MAX_SCREEN_COL)][row+(Player.worldX*Consts.MAX_SCREEN_ROW)];
             g2.drawImage(image,row*Consts.TILE_SIZE,col*Consts.TILE_SIZE,Consts.TILE_SIZE,Consts.TILE_SIZE,null);
         }
     }
   }

}
