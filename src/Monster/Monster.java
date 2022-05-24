package src.Monster;

import src.Entity.Entity;
import src.Events.Event;
import src.Events.Hurt;
import src.Inventory.Item;
import src.Main.Consts;
import src.Main.GamePanel;
import src.Objects.OBJ_Coin;
import src.Objects.SuperDropedItem;
import src.Objects.SuperObject;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class Monster extends Entity {
   public int maxHealth;
   public int health;
   public int strength;
   public int damageWait = 10;

   public Monster(GamePanel gp, int x, int y, int Strength) {
      super(gp, x, y);
      maxHealth=15*Strength;
      health = maxHealth;
      strength = 5 * Strength;

   }
   public void dropItem()
   {
      gp.obj.add((SuperObject)(new OBJ_Coin((int)screenX+worldX*Consts.MAX_SCREEN_COL, (int)screenY+worldY*Consts.MAX_SCREEN_ROW, strength)));
   }

   public BufferedImage hueShift(BufferedImage bi, int shift) {
      // hueshifter taken from
      // http://tech.abdulfatir.com/2014/05/changing-hue-of-image.html
      // slightly modified by Zimraan Ahmad
      BufferedImage raw, processed;
      raw = bi;
      float hue = ((shift) * 25) / 360.0f;
      int WIDTH = raw.getWidth();
      int HEIGHT = raw.getHeight();
      processed = new BufferedImage(WIDTH, HEIGHT, raw.getType());

      for (int Y = 0; Y < HEIGHT; Y++) {
         for (int X = 0; X < WIDTH; X++) {
            int RGB = raw.getRGB(X, Y);
            int R = (RGB >> 16) & 0xff;
            int G = (RGB >> 8) & 0xff;
            int B = (RGB) & 0xff;
            double rbgAvg = (R + G + B) / (255.0 * 3);
            float HSV[] = new float[3];
            Color.RGBtoHSB(R, G, B, HSV);
            if (shift != 0 && rbgAvg > 70.0 / 255) {
               processed.setRGB(X, Y, Color.getHSBColor(hue, HSV[1], HSV[2]).getRGB());
            } else {
               processed.setRGB(X, Y, raw.getRGB(X, Y));
            }
         }
      }
      //
      return processed;

   }
   @Override
   public void update() {
      // TODO Auto-generated method stub
      super.update();
      int b = gp.cChecker.checkEvent(this);
      doEvent(b);
      if(health<=0)
      {
         int i =gp.monsters.indexOf(this);
         gp.monsters.remove(i);
         dropItem();
      }
      if(damageWait<30)
      {
         damageWait++;
      }
   }

   public void doEvent(int index) {
      if (index >= 0) {
          String Name = gp.events.get(index).name;
          Event event = gp.events.get(index);
          switch (Name) {
              case "hurt":
                  if (damageWait >= 10) {
                      this.health -= ((Hurt) event).damage;
                      damageWait = 0;
                  }
                  break;

          }

      }
  }

}
