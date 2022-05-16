package src.Objects;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{
   
   public OBJ_Key()
   {
      name="Key";
      try {
         image = ImageIO.read(getClass().getResourceAsStream(name));
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
