package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.Consts;
import java.awt.Graphics2D;
import java.awt.Color;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public double sprintScale = 1.5;
    public double defaultSpeed = 2*Consts.SCALE;

    public Player (GamePanel gPanel, KeyHandler kHandler)
    {
        gp=gPanel;
        keyH=kHandler;
        setDefaultValues();
    }

    public void setDefaultValues ()
    {
        x= Consts.SCREEN_WIDTH/2;
        y = Consts.SCREEN_HEIGHT/2;
        speed =2*defaultSpeed;
    }

    public void getPlayerImage ()
    {
        try {
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    public void update()
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

    public void draw(Graphics2D g2)
    {
        g2.setColor(Color.GREEN);
      g2.fillRect((int)x, (int)y, Consts.TILE_SIZE, Consts.TILE_SIZE);
    }
}
