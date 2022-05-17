package src.Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class OBJ_Chest extends SuperObject{
    public int keyLockPairing;
    public boolean locked=false;
    public BufferedImage unlockedChest;
    public OBJ_Chest(int worldCol, int worldRow,int keyLockPairing)
    {
      super(worldCol, worldRow);
      try {
        unlockedChest =ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/TileObjects/unlockedChest.png"));
      } catch (Exception e) {
      }
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
          image = unlockedChest;

      } catch (Exception e) {
         e.printStackTrace();
      }
    }
}
