package Backend;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
/**
 * Window
 * i got this code from a tutorial(https://www.youtube.com/watch?v=1gir2R7G9ws&t=631s_)because just waned a game scrren that would work
 */

public class Window extends Canvas
{

   public Window(int width, int height, String Title, Main game)
   {
      JFrame frame = new JFrame(Title);
      frame.setPreferredSize(new Dimension(width,height));
      frame.setMaximumSize(new Dimension(width,height));
      frame.setMinimumSize(new Dimension(width,height));

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      frame.add(game);
      frame.setVisible(true);
      game.start();
   }
}