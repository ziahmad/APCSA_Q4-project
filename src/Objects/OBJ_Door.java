package src.Objects;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
   int keyLockPairing;
   boolean locked=false;
   public OBJ_Door(int worldCol, int worldRow, int keyLockPairing)
   {
      super(worldCol, worldRow);
      
      if(keyLockPairing>=0)
         locked=true;

      name="Door";
      try {

            image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/TileObjects/lockedDoor.png"));
            collision=true;
            image = hueShift(image, keyLockPairing);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   public OBJ_Door(int worldCol, int worldRow)
   {
      super(worldCol, worldRow);

      name="Door";
      try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/TileObjects/unlockedDoor.png"));
            collision=false;
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
