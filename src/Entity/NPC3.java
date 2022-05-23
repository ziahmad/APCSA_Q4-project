package src.Entity;

import javax.imageio.ImageIO;

import java.util.Random;

import src.Main.GamePanel;


public class NPC3 extends Entity {

   public NPC3(GamePanel gp, int worldCol, int worldRow ) {
      super(gp, worldCol, worldRow);
      direction="down";
      speed=.06;
   
      getImage();
   }

   public void getImage ()
    {
        //getssprites
        try {
            //movement sprites
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/up2.png"));
            up0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/up0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/down2.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/down0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/left0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/left0.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/left0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/right0.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/right0.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC3/right0.png"));
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    public void setAction()
    {
       actionLockCounter++;
       if(actionLockCounter==30)
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
    public void speak()
    {
      gp.ui.currentDialogue = dialogue.get(dialogueIndex);
      dialogueIndex++;
      if(dialogueIndex>=dialogue.size())
      {
         dialogueIndex=0;
      }
    }
   
}

