package src.Objects;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

import src.Main.Consts;
import src.Main.GamePanel;

public class SuperObject {
   
   public BufferedImage image;   
   public String name;
   public boolean collision = false;
   public int absX, absY;
   public int worldX, worldY;
   public int screenX, screenY;
   public boolean hidden=false;

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

   public BufferedImage hueShift(BufferedImage bi, int shift)
   {
      //hueshifter taken from 
      //http://tech.abdulfatir.com/2014/05/changing-hue-of-image.html
      //slightly modified by Zimraan Ahmad
      BufferedImage raw,processed;
      raw = bi;
      float hue = ((shift)*25)/360.0f;
      int WIDTH = raw.getWidth();
      int HEIGHT = raw.getHeight();
      processed = new BufferedImage(WIDTH,HEIGHT,raw.getType());
         
      for(int Y=0; Y<HEIGHT;Y++)
      {
       for(int X=0;X<WIDTH;X++)
       {
            int RGB = raw.getRGB(X, Y);
            int R = (RGB >> 16) & 0xff;
            int G = (RGB >> 8) & 0xff;
            int B = (RGB) & 0xff;
            double rbgAvg= (R+G+B)/(255.0*3);
            float HSV[]=new float[3];
            Color.RGBtoHSB(R,G,B,HSV);
               if(shift!=0&&rbgAvg>70.0/255)
               {
               processed.setRGB(X,Y,Color.getHSBColor(hue,HSV[1],HSV[2]).getRGB());
               }else{
                  processed.setRGB(X, Y, raw.getRGB(X, Y));
               }
         }
       }
      //
      return processed;
      
      
   }

   public void draw(Graphics2D g2, GamePanel gp)
   {

            if(!hidden)
            {
               g2.drawImage(image,screenX*Consts.TILE_SIZE,screenY*Consts.TILE_SIZE,Consts.TILE_SIZE,Consts.TILE_SIZE,null);
            }
             
             g2.draw(solidArea);
         

      
   }

}
