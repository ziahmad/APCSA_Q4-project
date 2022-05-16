package src.Objects;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{
   
   public OBJ_Key(int worldCol, int worldRow)
   {
      super(worldCol, worldRow);
      

      name="Key";
      try {
         image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/DroppedItem/key.png"));
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
