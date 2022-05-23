package src.Objects;

import javax.imageio.ImageIO;

import src.Events.Hurt;
import src.Main.Consts;
import src.Main.GamePanel;
import src.Main.Window;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

public class OBJ_Bomb extends SuperDropedItem{
   public int maxCounter =100;
   public int counter =maxCounter+50;
   public int explosionTime=50;
   int damage = 25;
   public Hurt rect;
   

   
   public OBJ_Bomb (int worldCol, int worldRow,boolean armed)
   {
      super(worldCol, worldRow);
      quantity=1;
      this.armed = armed;

      name="Bomb";
         try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/DroppedItem/bomb.png"));
         } catch (Exception e) {
            e.printStackTrace();
         }
      if(armed)
      {
         image= hueShift(image, counter/100);
         collision=true;
         rect = new Hurt(screenX+worldX*Consts.TILE_SIZE,screenY+worldY*Consts.TILE_SIZE, damage,3 ,3);
      }


   }

   public OBJ_Bomb bombCountDown(OBJ_Bomb bomb)
   {
      
      if(armed&&counter>=0)
      {
         counter--;
         image= hueShift(image, counter/maxCounter);
      }
      if(counter==0){
         collision=false;
      }
      if(counter<0)
      {
         explosionTime--;
      }
      if(explosionTime==0){
         System.out.println("boom");
      }
      return bomb;

   }


   @Override
   public BufferedImage hueShift(BufferedImage bi, int shift)
   {
      //hueshifter taken from 
      //http://tech.abdulfatir.com/2014/05/changing-hue-of-image.html
      //slightly modified by Zimraan Ahmad
      BufferedImage raw,processed;
      raw = bi;
      float hue = ((shift));
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
            float HSV[]=new float[3];
            Color.RGBtoHSB(R,G,B,HSV);
               if(RGB!=0)
               {
               processed.setRGB(X,Y,Color.getHSBColor(hue,HSV[1],HSV[2]+(maxCounter/(shift+1))/maxCounter+.0085f).getRGB());
               }else{
                  processed.setRGB(X, Y, raw.getRGB(X, Y));
               }
         }
       }
      //
      return processed;  
   }
   @Override
   public void draw(Graphics2D g2, GamePanel gp) {
       // TODO Auto-generated method stub
       if(counter>=0)
       super.draw(g2, gp);
       else if( explosionTime>=0)
       {
          g2.setColor(new Color (255,00,0,100));
         g2.fill(rect.solidArea);
       }
   }

}
