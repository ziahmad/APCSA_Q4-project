package src.Main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import src.Entity.Player;
import src.Objects.OBJ_Bomb;
import src.Objects.SuperObject;
import src.Tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
   //things needed for game to work
   TileManager tileM = new TileManager(this);
   KeyHandler keyH = new KeyHandler();

   Thread gThread;
   public assetSetter aSetter = new assetSetter(this);
   public ArrayList<SuperObject> obj=new ArrayList<>(0);
   public CollisionChecker cChecker = new CollisionChecker(this);
   public Player player = new Player(this, keyH);



   //goal fps
   int fps = 60;
   public GamePanel()
   {
      this.setPreferredSize(new Dimension(Consts.SCREEN_WIDTH,Consts.SCREEN_HEIGHT));
      this.setBackground(Color.BLACK);
      this.setDoubleBuffered(true);
      this.addKeyListener(keyH);
      this.setFocusable(true);
   }

   public void setupGame() {
      aSetter.setObject();
   }


   public void startGrameThread()
   {
      gThread = new Thread(this);
      
      //calls run
      gThread.start();
   }

   @Override
    //the gameloop (https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2)
   public void run() 
   {

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
               System.out.println("Stamina:"+player.stamina+" "+player.sCharged);
               System.out.println(player.speed);
            }
         }
      
   }

   //tick
   public void update ()
   {
      player.update();
      for (int i= obj.size()-1; i>=0;i--) {
         OBJ_Bomb b;
         if(obj.get(i) instanceof OBJ_Bomb)
         {
            b=((OBJ_Bomb)(obj.get(i))).bombCountDown((OBJ_Bomb)obj.get(i));
            if(b!=null)
            {
               obj.remove(i);
            }
         }
         
      }

   }

   //render
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      Graphics2D g2 = (Graphics2D)g;
      //draw tiles 
      tileM.draw(g2);
      //objects
      for (SuperObject superObject : obj) {
         if(superObject.worldX==player.worldX&&superObject.worldY==player.worldY)
         superObject.draw(g2, this);
      }
      

      //then player
      player.draw(g2);

      //then ui
     // ui.draw(g2);
      g2.dispose();//used to save on memory

   }


   
}
