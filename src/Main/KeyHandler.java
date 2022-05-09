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
         case KeyEvent.VK_W:
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
         case KeyEvent.VK_SHIFT:
            shiftPressed=true;
            break;
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
      }
      
   }
   
}
