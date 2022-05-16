package src.Objects;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    
    public OBJ_Door(int worldCol, int worldRow)
   {
      super(worldCol, worldRow);
      

      name="Door";
      try {
          //TODO: Add door Image
         image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/DroppedItem/bomb.png"));
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      collision=true;
   }
}
