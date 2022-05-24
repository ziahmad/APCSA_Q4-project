package src.Main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import src.Events.*;
import src.Monster.Monster;
import src.Entity.Entity;
import src.Entity.Player;
import src.Objects.OBJ_Bomb;
import src.Objects.SuperObject;
import src.Tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
   // things needed for game to work
   TileManager tileM = new TileManager(this);
   public KeyHandler keyH = new KeyHandler(this);
   public UI ui = new UI(this);
   Thread gThread;

   public assetSetter aSetter = new assetSetter(this);
   public CollisionChecker cChecker = new CollisionChecker(this);
   // entities and objects
   public ArrayList<Event> events = new ArrayList<>(0);
   public ArrayList<SuperObject> obj = new ArrayList<>(0);
   public Player player = new Player(this, keyH);
   public ArrayList<Entity> npcs = new ArrayList<>(0);
   public ArrayList<Monster> monsters = new ArrayList<>(0);
   // game state
   public int gameState;
   public final int PLAY_STATE = 1;
   public final int PAUSE_STATE = 2;
   public final int DIALOGUE_STATE = 3;
   public final int WIN_STATE = 4;
   public final int LOSE_STATE = 5;

   int sideBarWidth = 3 * Consts.TILE_SIZE;

   // goal fps
   int fps = 60;

   public GamePanel() {
      this.setPreferredSize(new Dimension(Consts.SCREEN_WIDTH + sideBarWidth, Consts.SCREEN_HEIGHT));
      this.setBackground(Color.BLACK);
      this.setDoubleBuffered(true);
      this.addKeyListener(keyH);
      this.setFocusable(true);
   }

   public void setupGame() {
      aSetter.setObject();
      aSetter.setNPCs();
      aSetter.setEvents();
      aSetter.setupMonsters();
      gameState = PLAY_STATE;
   }

   public void startGrameThread() {
      gThread = new Thread(this);

      // calls run
      gThread.start();
   }

   @Override
   // the gameloop
   // (https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2)
   public void run() {

      double drawInterval = 1000000000 / fps;
      double dt = 0;
      long lastTime = System.nanoTime();
      long currentTime;
      long timer = 0;
      int drawcount = 0;

      /* while thread exists, update then repaint */
      while (gThread != null) // while thread exists
      {

         currentTime = System.nanoTime();

         dt += (currentTime - lastTime) / drawInterval;
         timer += (currentTime - lastTime);
         lastTime = currentTime;
         if (dt >= 1) {
            // calls update
            update();

            // calls paint component
            repaint();
            dt--;
            drawcount++;

         }
         if (timer >= 1000000000) {
            System.out.println("FPS: " + drawcount);
            drawcount = 0;
            timer = 0;
            System.out.println("Stamina:" + player.stamina + " " + player.sCharged);
            System.out.println(player.speed);
         }
      }

   }

   // tick
   public void update() {
      if (gameState == PLAY_STATE) {
         // onbjects
         updateObjects();
         // NPC
         for (Entity entity : npcs) {
            entity.update();
         }
         for (Monster entity : monsters) {
            int s = monsters.size();
            entity.update();
            if (s > monsters.size()) {
               break;
            }
         }
         player.update();

      } else if (gameState == PAUSE_STATE) {

      } else if (gameState == WIN_STATE) {

      }

   }

   // render
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;
      // draw tiles
      tileM.draw(g2);
      // objects
      for (SuperObject superObject : obj) {
         if (superObject.worldX == player.worldX && superObject.worldY == player.worldY)
            superObject.draw(g2, this);
      }
      // npcs
      for (Entity e : npcs) {
         if (e.worldX == player.worldX && e.worldY == player.worldY)
            e.draw(g2);
      }
      for (Monster e : monsters) {
         if (e.worldX == player.worldX && e.worldY == player.worldY)
            e.draw(g2);
      }

      // then player
      player.draw(g2);

      // then ui
      ui.draw(g2);
      g2.dispose();// used to save on memory

   }

   // update Objects
   public void updateObjects() {
      for (int i = obj.size() - 1; i >= 0; i--) {
         // updateBomb
         if (obj.get(i) instanceof OBJ_Bomb) {
            OBJ_Bomb b;
            b = ((OBJ_Bomb) (obj.get(i))).bombCountDown((OBJ_Bomb) obj.get(i));
            if (b.counter == 0) {
               b.collision = false;
               events.add(b.rect);
            }
            if (b.explosionTime <= 0) {
               int c = events.indexOf(b.rect);
               if (c != -1)
                  events.remove(c);
               obj.remove(i);
            }
         }

      }
   }

}
