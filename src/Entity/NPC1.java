package src.Entity;

import javax.imageio.ImageIO;

import java.util.Random;

import src.Main.GamePanel;
import src.Main.UI;


public class NPC1 extends Entity {

   public NPC1(GamePanel gp, int worldCol, int worldRow ) {
      super(gp, worldCol, worldRow);
      direction="left";
      speed=.03;
   
      getImage();
      setDialogue();
   }

   public void getImage ()
    {
        //getssprites
        try {
            //movement sprites
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/up2.png"));
            up0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/up0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/down2.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/down0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/left2.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/left0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/right2.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/NPCS/NPC1/right0.png"));
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    public void setDialogue() {
    }

    public void setAction()
    {
       actionLockCounter++;
       if(actionLockCounter==120)
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
      System.out.println(dialogueIndex);
    }
   
}
