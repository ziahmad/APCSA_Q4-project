package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
   //set defaul size for game window and tiles
   final int TILE_SIZE_START = 16;
   final int SCALE = 4;

   final int TILE_SIZE = SCALE*TILE_SIZE_START;
   final int MAX_SCREEN_COL =16;
   final int MAX_SCREEN_ROW =16;

   final int SCREEN_WIDTH = MAX_SCREEN_COL*TILE_SIZE;
   final int SCREEN_HEIGHT = MAX_SCREEN_ROW*TILE_SIZE;

   //fps
   int fps=60;

   KeyHandler keyH = new KeyHandler();
   //to make the game run over time
   Thread gThread;

   //player default position
   double x = SCREEN_WIDTH/2;
   double y = SCREEN_HEIGHT/2;
   double sprintScale = 1.5;
   final double defaultSpeed = 2*SCALE;
   double speed = defaultSpeed;

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
      if (keyH.shiftPressed)
      {
         speed=defaultSpeed*sprintScale;
      }else{
         speed=defaultSpeed; 
      }
      if (keyH.upPressed)
      {
         y-=speed;
      }
      else if (keyH.downPressed)
      {
         y+=speed;
      }
      else if (keyH.leftPressed)
      {
         x-=speed;
      }
      else if (keyH.rightPressed)
      {
         x+=speed;
      }

   }


   //render
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      Graphics2D g2 = (Graphics2D)g;
      //test
      g2.setColor(Color.GREEN);
      g2.fillRect((int)x, (int)y, TILE_SIZE, TILE_SIZE);

      g2.dispose();//used to save on memory

   }
}
