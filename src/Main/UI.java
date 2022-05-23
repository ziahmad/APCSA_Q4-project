package src.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class UI {
   GamePanel gp;
   Graphics2D g2;
   Font arial_32,arial_40, arial_80b;
   public String message = "";
   public  boolean gameFinished = false;
   public String currentDialogue = "";


   public UI (GamePanel gp)
   {
      this.gp= gp;

      arial_32 = new Font("Arial", Font.PLAIN,16);
      arial_40 = new Font("Arial", Font.PLAIN,40);
      arial_80b = new Font("Arial", Font.BOLD,80);

   }

   public void draw (Graphics2D g2)
   {
      this.g2 = g2;
      g2.setFont(arial_40);
      g2.setColor(Color.GRAY);

      //playSTate
      if(gp.gameState ==gp.PLAY_STATE)
      {

      }
      //pauseState
      else if (gp.gameState== gp.PAUSE_STATE)
      {
         drawPauseScreen();
      }
      //dialogue state
      else if (gp.gameState==gp.DIALOGUE_STATE)
      {
         drawDialogueScreen();
      }
   }

   private void drawDialogueScreen() {

      //window
      int x =Consts.TILE_SIZE*2;
      int y = Consts.TILE_SIZE;
      int width=Consts.SCREEN_WIDTH-(Consts.TILE_SIZE*4);
      int height=Consts.TILE_SIZE*5;

      drawSubWindow(x, y, width, height);
      g2.setFont(arial_32);
      x+=Consts.TILE_SIZE;
      y+=Consts.TILE_SIZE;
      g2.drawString(currentDialogue,x,y);
   }

   public void drawSubWindow(int x, int y, int width, int height)
   {
      Color c = new Color(0,0,0,200);
      g2.setColor(c);
      g2.fillRoundRect(x, y, width, height, Consts.TILE_SIZE, Consts.TILE_SIZE);
      g2.setColor(Color.WHITE);
      g2.setStroke(new BasicStroke(Consts.SCALE));
      //g2.setStroke(new BasicStoke(5*Consts.SCALE));
      g2.drawRoundRect(x, y, width, height, Consts.TILE_SIZE, Consts.TILE_SIZE);
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
