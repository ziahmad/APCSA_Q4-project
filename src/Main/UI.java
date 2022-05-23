package src.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
   GamePanel gp;
   Graphics2D g2;
   Font arial_40, arial_80b;
   public String message = "";
   public  boolean gameFinished = false;


   public UI (GamePanel gp)
   {
      this.gp= gp;

      arial_40 = new Font("Arial", Font.PLAIN,40);
      arial_80b = new Font("Arial", Font.BOLD,80);

   }

   public void draw (Graphics2D g2)
   {
      this.g2 = g2;
      g2.setFont(arial_40);
      g2.setColor(Color.GRAY);

      if(gp.gameState ==gp.PLAY_STATE)
      {

      }else if (gp.gameState== gp.PAUSE_STATE)
      {
         drawPauseScreen();
      }
   }

   public void drawPauseScreen()
   {
      g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
      String text = "PAUSED";
      int x = getXForCenterText(text);
      int y =Consts.SCREEN_HEIGHT/2;

      g2.drawString(text, x,y);

   }
   public int getXForCenterText(String text)
   {
      int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
      return Consts.SCREEN_WIDTH/2-length/2;
   }
}
