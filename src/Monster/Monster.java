package src.Monster;

import src.Entity.Entity;

import src.Main.GamePanel;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class Monster extends Entity {

   public int maxHealth;
   public int Health;
   public int strength;

   public Monster(GamePanel gp, int x, int y, int Strength) {
      super(gp, x, y);
      strength = 5 * Strength;

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

}
