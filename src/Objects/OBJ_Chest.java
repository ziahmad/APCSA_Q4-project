package src.Objects;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{
    int keyLockPairing;
    boolean locked=false;
    public OBJ_Chest(int worldCol, int worldRow,int keyLockPairing)
    {
      super(worldCol, worldRow);
      
      this.keyLockPairing=keyLockPairing;
      if(keyLockPairing>=0)
      {
        locked=true;
      }
      collision =true;

      name="chest";
      try {
        if(locked)
        {
          image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/TileObjects/lockedChest.png"));
          image = hueShift(image, keyLockPairing);
        }
        else
          image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/TileObjects/unlockedChest.png"));

      } catch (Exception e) {
         e.printStackTrace();
      }
    }
}
