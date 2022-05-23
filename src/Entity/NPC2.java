package src.Entity;

import javax.imageio.ImageIO;

import java.util.Random;

import src.Main.GamePanel;


public class NPC2 extends Entity {

   public NPC2(GamePanel gp, int worldCol, int worldRow ) {
      super(gp, worldCol, worldRow);
      direction="left";
      speed=.04;
   
      getImage();
   }

   public void getImage ()
    {
        //getssprites
        try {
            //movement sprites
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/up0.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/up0.png"));
            up0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/up0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/down0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/down0.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/down0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/left0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/left0.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/left0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/right0.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/right0.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC2/right0.png"));
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    public void setAction()
    {
       actionLockCounter++;
       if(actionLockCounter==75)
       {
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if (i<=25)
            {
               direction = "up";
            }else if (i<=50)
            {
               direction = "down";
            }else if (i<=75)
            {
               direction = "left";
            }else if (i<=100)
            {
               direction = "right";
            }
            actionLockCounter=0;
      }
         System.out.print("");
    }
    
   
}

