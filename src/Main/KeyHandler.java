package src.Main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

   public boolean upPressed, downPressed,leftPressed,rightPressed,shiftPressed;
   @Override
   public void keyTyped(KeyEvent e) {
      //wont be used
      
   }

   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
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
      // TODO Auto-generated method stub

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
