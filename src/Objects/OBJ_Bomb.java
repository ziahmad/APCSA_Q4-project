package src.Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class OBJ_Bomb extends SuperDropedItem{
   public int maxCounter =100;
   public int counter =maxCounter+50;

   
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
      }


   }

   public OBJ_Bomb bombCountDown(OBJ_Bomb bomb)
   {
      
      if (counter<=0)
      {
         return bomb;
      }
      if(armed)
      {
         counter--;
         image= hueShift(image, counter/maxCounter);
      }else{
         return null;
      }
      return null;

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
}
