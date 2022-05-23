package src.Main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * 
 * controlls
 * 
 * 
 */
public class KeyHandler implements KeyListener{

   GamePanel gp;
   public boolean upPressed, downPressed,leftPressed,rightPressed,shiftPressed;
   
   public KeyHandler(GamePanel gp)
   {
      this.gp= gp;
   }
   
   
   
   @Override
   public void keyTyped(KeyEvent e) {
      //wont be used
      
   }

   @Override
   public void keyPressed(KeyEvent e) {
      
      int code = e.getKeyCode();
      
      switch(code)
      {
         //controls
         case (KeyEvent.VK_W):
            upPressed=true;
            break;
         case KeyEvent.VK_S:
            downPressed=true;
            break;
         case KeyEvent.VK_D:
            rightPressed=true;
            break;
         case KeyEvent.VK_A:
            leftPressed=true;
            break;
            //increaes movement speed
         case KeyEvent.VK_SHIFT:
            shiftPressed=true;
            break;
         //movewith arrow keys
         case (KeyEvent.VK_UP):
            upPressed=true;
            break;
         case KeyEvent.VK_DOWN:
            downPressed=true;
            break;
         case KeyEvent.VK_RIGHT:
            rightPressed=true;
            break;
         case KeyEvent.VK_LEFT:
            leftPressed=true;
            break;
         case KeyEvent.VK_ESCAPE:
               if (gp.gameState ==gp.PLAY_STATE)
               {
                  gp.gameState = gp.PAUSE_STATE;
               }else if (gp.gameState ==gp.PAUSE_STATE)
               {
                  gp.gameState = gp.PLAY_STATE;
               }
               break;
         /**
          * to use later for other inputs
          case KeyEvent.VK_SHIFT:
             shiftPressed=true;
             break;
          */
      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
      

      int code = e.getKeyCode();
      
      switch(code)
      {
         case KeyEvent.VK_W:
            upPressed=false;
            break;
         case KeyEvent.VK_S:
            downPressed=false;
            break;
         case KeyEvent.VK_D:
            rightPressed=false;
            break;
         case KeyEvent.VK_A:
            leftPressed=false;
            break;
         case KeyEvent.VK_SHIFT:
            shiftPressed=false;
            break;
         //movewith arrow keys
         case (KeyEvent.VK_UP):
            upPressed=false;
            break;
         case KeyEvent.VK_DOWN:
            downPressed=false;
            break;
         case KeyEvent.VK_RIGHT:
            rightPressed=false;
            break;
         case KeyEvent.VK_LEFT:
            leftPressed=false;
            break;
         /**
          * to use later for other inputs
          case KeyEvent.VK_SHIFT:
             shiftPressed=false;
             break;
          */
      }
      
   }
   
}