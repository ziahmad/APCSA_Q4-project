package apcsa_q4.project.Monster;

import java.util.Random;

import javax.imageio.ImageIO;

import apcsa_q4.project.Main.Consts;
import apcsa_q4.project.Main.GamePanel;

public class Monster1 extends Monster {

   public Monster1(GamePanel gp, int x, int y, int Strength) {
      super(gp, x, y, Strength);
      speed = .03 * Strength;
      direction = "down";

      getImage();

   }

   public void getImage() {
      // getssprites
      try {
         // movement sprites
         up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/up1.png"));
         up1 = hueShift(up1, strength);
         up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/up2.png"));
         up2 = hueShift(up2, strength);
         up0 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/up0.png"));
         up0 = hueShift(up0, strength);
         down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/down1.png"));
         down1 = hueShift(down1, strength);
         down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/down2.png"));
         down2 = hueShift(down2, strength);
         down0 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/down0.png"));
         down0 = hueShift(down0, strength);
         left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/left1.png"));
         left1 = hueShift(left1, strength);
         left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/left2.png"));
         left2 = hueShift(left2, strength);
         left0 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/left0.png"));
         left0 = hueShift(left0, strength);
         right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/right1.png"));
         right1 = hueShift(right1, strength);
         right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/right2.png"));
         right2 = hueShift(right2, strength);
         right0 = ImageIO.read(getClass().getResourceAsStream("/sprites/NPCS/NPC1/right0.png"));
         right0 = hueShift(right0, strength);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   @Override
   public void setAction() {

      double dx = ((gp.player.screenX + gp.player.solidArea.width / 2) / Consts.TILE_SIZE - this.screenX);
      double dy = ((gp.player.screenY + gp.player.solidArea.height / 2) / Consts.TILE_SIZE - this.screenY);
      if (Math.abs(dx) < 5 && Math.abs(dy) < 5) {
         if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
               direction = "right";
            if (dx < 0)
               direction = "left";

         } else if (Math.abs(dy) > Math.abs(dx)) {
            if (dy > 0)
               direction = "down";
            if (dy < 0)
               direction = "up";
         } else {

         }
      } else {

         actionLockCounter++;
         if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
               direction = "up";
            } else if (i <= 50) {
               direction = "down";
            } else if (i <= 75) {
               direction = "left";
            } else if (i <= 100) {
               direction = "right";
            }
            actionLockCounter = 0;
         }
         System.out.print("");
      }
   }
}
