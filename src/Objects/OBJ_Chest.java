package src.Objects;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{
    
    public OBJ_Chest(int worldCol, int worldRow)
    {
      super(worldCol, worldRow);
      

      name="chest";
      try {
          //TODO: Add chest Image
         image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/DroppedItem/arrow.png"));
         
      } catch (Exception e) {
         e.printStackTrace();
      }
    }
}
