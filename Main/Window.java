package Main;
import javax.swing.JFrame;

public class Window extends JFrame{
   
   public Window()
   {
      JFrame window =new JFrame();
      window.setDefaultCloseOperation(EXIT_ON_CLOSE);
      window.setResizable(false);
      window.setTitle("Quarter 4 Project");

      GamePanel gPanel = new GamePanel();
      window.add(gPanel);
      window.pack();

      window.setLocationRelativeTo(null);
      window.setVisible(true);

      gPanel.startGrameThread();;
   }

}
