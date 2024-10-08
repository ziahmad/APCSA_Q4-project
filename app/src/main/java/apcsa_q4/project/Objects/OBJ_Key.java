package apcsa_q4.project.Objects;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
   public int keyLockPairing;

   public OBJ_Key(int worldCol, int worldRow, int keyLockPairing) {
      super(worldCol, worldRow);
      this.keyLockPairing = keyLockPairing;

      name = "Key";
      try {
         image = ImageIO.read(getClass().getResourceAsStream("/sprites/Objects/DroppedItem/key.png"));
         image = hueShift(image, keyLockPairing);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}