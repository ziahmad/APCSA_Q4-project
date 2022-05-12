package src.Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import src.Entity.Player;
import src.Tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
   //set defaul size for game window and tiles
   public final int TILE_SIZE_START = Consts.TILE_SIZE_START;
   public final int SCALE = Consts.SCALE;

   public final int TILE_SIZE = Consts.SCALE*Consts.TILE_SIZE_START;
   public final int MAX_SCREEN_COL =Consts.MAX_SCREEN_COL;
   public final int MAX_SCREEN_ROW =Consts.MAX_SCREEN_ROW;

   public final int SCREEN_WIDTH = Consts.MAX_SCREEN_COL*Consts.TILE_SIZE;
   public final int SCREEN_HEIGHT = Consts.MAX_SCREEN_ROW*Consts.TILE_SIZE;

   //fps goal
   int fps=60;


   TileManager tileM = new TileManager(this);
   KeyHandler keyH = new KeyHandler();
   //to make the game run over time
   Thread gThread;
   Player player = new Player(this, keyH);
      

   
   public GamePanel()
   {
      this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
      this.setBackground(Color.BLACK);
      this.setDoubleBuffered(true);
      this.addKeyListener(keyH);
      this.setFocusable(true);
      
   }

   public void startGrameThread()
   {
      gThread = new Thread(this);
      
      //calls run
      gThread.start();
   }

   @Override
   //the gameloop (https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2)
   public void run() {

      double drawInterval = 1000000000/fps;
      double dt =0;
      long lastTime = System.nanoTime();
      long currentTime;
      long timer = 0;
      int drawcount = 0;

      /*while thread exists, update then repaint*/
      while (gThread!=null) //while thread exists
      {

         currentTime = System.nanoTime();
         
         dt+=(currentTime-lastTime)/drawInterval;
         timer += (currentTime-lastTime);
         lastTime=currentTime;
         if(dt>=1)
         {
            //calls update
            update();

            //calls paint component
            repaint();
            dt--;
            drawcount++;
         }
         if (timer>=1000000000)
         {
            System.out.println("FPS: "+drawcount);
            drawcount=0;
            timer=0;
         }
      }
      
   }

   //tick
   public void update ()
   {
      player.update();

   }


   //render
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      Graphics2D g2 = (Graphics2D)g;
      
      tileM.draw(g2);

      player.draw(g2);

      g2.dispose();//used to save on memory

   }
}
