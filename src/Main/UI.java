package src.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;

public class UI {
   GamePanel gp;
   Graphics2D g2;
   Font arial_32,arial_40, arial_80b;
   public String message = "";
   public  boolean gameFinished = false;
   public String currentDialogue = "";
   //sidebar
   Color sidebarColor = new Color(220,200,150);
   Color sideBarBoarder = new Color(170,140,10);
   public Rectangle sidebar= new Rectangle();
   //meters
   public Color staminaFull = new Color(0,150,0);
   public Color stamina = staminaFull;
   public Color staminadDepleted = Color.gray;
   public Rectangle Staminabar= new Rectangle();
   public Rectangle StaminaMeter= new Rectangle();

   Color health = Color.red;
   public Rectangle Healthbar= new Rectangle();
   public Rectangle HealthMeter= new Rectangle();



   public UI (GamePanel gp)
   {
      this.gp= gp;

      arial_32 = new Font("Arial", Font.PLAIN,20);
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
         drawSideBar(Consts.SCREEN_WIDTH,0,Consts.SCREEN_HEIGHT,gp.sideBarWidth);
      }
      //pauseState
      else if (gp.gameState== gp.PAUSE_STATE)
      {

            drawPauseScreen();

      }
      //dialogue state
      else if (gp.gameState==gp.DIALOGUE_STATE)
      {
         drawSideBar(Consts.SCREEN_WIDTH,0,Consts.SCREEN_HEIGHT,gp.sideBarWidth);
         drawDialogueScreen();
      }
      //winState
      else if (gp.gameState==gp.WIN_STATE)
      {
         drawSideBar(Consts.SCREEN_WIDTH,0,Consts.SCREEN_HEIGHT,gp.sideBarWidth);
         drawSubWindow(1*Consts.TILE_SIZE, 1*Consts.TILE_SIZE, gp.getWidth()-2*Consts.TILE_SIZE, gp.getHeight()-2*Consts.TILE_SIZE);
         g2.setColor(Color.WHITE);
         g2.setFont(arial_80b);
         int x = getXForCenterText("YOU WIN!");
         int y= gp.getHeight()/2;
         g2.drawString("YOU WIN!",x,y);
      }
      //lose state
      else if (gp.gameState==gp.LOSE_STATE)
      {
         drawSideBar(Consts.SCREEN_WIDTH,0,Consts.SCREEN_HEIGHT,gp.sideBarWidth);
         drawSubWindow(1*Consts.TILE_SIZE, 1*Consts.TILE_SIZE, gp.getWidth()-2*Consts.TILE_SIZE, gp.getHeight()-2*Consts.TILE_SIZE);
         g2.setColor(Color.RED);
         g2.setFont(arial_80b);
         int x = getXForCenterText("YOU LOSE :(");
         int y= gp.getHeight()/2;
         g2.drawString("YOU LOSE :(",x,y);
      }
   }
   public void drawSideBar(int x, int y, int height, int width)
   {
      sidebar.x=x;
      sidebar.y=y;
      sidebar.height=height;
      sidebar.width=width;
      g2.setColor(sidebarColor);
      g2.fill(sidebar);
      g2.setColor(sideBarBoarder);
      int strokewidth = 2*Consts.SCALE;
      g2.setStroke(new BasicStroke(strokewidth));
      g2.drawRect(sidebar.x+strokewidth/2, 0+strokewidth/2, sidebar.width, sidebar.height-strokewidth);
//meters
   //stamina
      //meter
      g2.setColor(stamina);
      StaminaMeter.x = sidebar.x+strokewidth+8*Consts.SCALE;
      StaminaMeter.height = (int)((gp.player.stamina/gp.player.maxStamina)*4*Consts.TILE_SIZE);
      StaminaMeter.y =8*Consts.SCALE;
      StaminaMeter.width =Consts.TILE_SIZE/2;
      //bar
      int barBorder = 2;
      Staminabar.x = StaminaMeter.x-(barBorder*Consts.SCALE);
      Staminabar.y =8*Consts.SCALE-(barBorder*Consts.SCALE);
      Staminabar.width =StaminaMeter.width+(2*barBorder*Consts.SCALE);
      Staminabar.height = 4*Consts.TILE_SIZE+(2*barBorder*Consts.SCALE);
      g2.setStroke(new BasicStroke(2));
      g2.draw(Staminabar);
      g2.fill(StaminaMeter);
   //health
      //meter
      g2.setColor(health);
      HealthMeter.x = (int)(StaminaMeter.x+1.25*Consts.TILE_SIZE);
      HealthMeter.height = (int)(((double)gp.player.health/gp.player.maxHealth)*4*Consts.TILE_SIZE);
      HealthMeter.y =8*Consts.SCALE;
      HealthMeter.width =Consts.TILE_SIZE/2;
      //bar
      Healthbar.x = HealthMeter.x-(barBorder*Consts.SCALE);
      Healthbar.y =8*Consts.SCALE-(barBorder*Consts.SCALE);
      Healthbar.width =HealthMeter.width+(2*barBorder*Consts.SCALE);
      Healthbar.height = 4*Consts.TILE_SIZE+(2*barBorder*Consts.SCALE);
      g2.setStroke(new BasicStroke(2));
      g2.draw(Healthbar);
      g2.fill(HealthMeter);


      

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
      for(String line : currentDialogue.split("\n"))
      {
         g2.drawString(line,x,y);
         y+=32;
      }
      
   }

   public void drawSubWindow(int x, int y, int width, int height)
   {
      Color c = new Color(0,0,0,200);
      g2.setColor(c);
      g2.fillRoundRect(x, y, width, height, Consts.TILE_SIZE, Consts.TILE_SIZE);
      g2.setColor(Color.WHITE);
      g2.setStroke(new BasicStroke(Consts.SCALE));
      g2.drawRoundRect(x, y, width, height, Consts.TILE_SIZE, Consts.TILE_SIZE);
   }

   public void drawPauseScreen()
   {
      g2.setColor(new Color(0,0,0,150));
      g2.fillRect(0, 0, Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT);
      drawSideBar(5*Consts.TILE_SIZE, 0, Consts.SCREEN_HEIGHT, Consts.SCREEN_WIDTH-Consts.TILE_SIZE);

   }
   
   public int getXForCenterText(String text)
   {
      int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
      return gp.getWidth()/2-length/2;
   }
}
