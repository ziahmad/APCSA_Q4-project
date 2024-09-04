package apcsa_q4.project.Objects;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
   public int keyLockPairing;
   public boolean locked = false;
   public BufferedImage unlockedDoor;

   public OBJ_Door(int worldCol, int worldRow, int keyLockPairing) {
      super(worldCol, worldRow);
      this.keyLockPairing = keyLockPairing;
      try {
         unlockedDoor = ImageIO
               .read(getClass().getResourceAsStream("/sprites/Objects/TileObjects/unlockedDoor.png"));
      } catch (Exception e) {
      }

      if (keyLockPairing >= 0)
         locked = true;

      name = "Door";
      try {

         image = ImageIO.read(getClass().getResourceAsStream("/sprites/Objects/TileObjects/lockedDoor.png"));
         collision = true;
         image = hueShift(image, keyLockPairing);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public OBJ_Door(int worldCol, int worldRow) {
      super(worldCol, worldRow);

      name = "Door";
      try {
         image = ImageIO
               .read(getClass().getResourceAsStream("/sprites/Objects/TileObjects/unlockedDoor.png"));
         collision = false;

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
