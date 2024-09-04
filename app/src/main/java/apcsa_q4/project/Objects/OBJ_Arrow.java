package apcsa_q4.project.Objects;

import javax.imageio.ImageIO;

public class OBJ_Arrow extends SuperDropedItem {

   public OBJ_Arrow(int worldCol, int worldRow, int amount) {
      super(worldCol, worldRow);
      sellPrice = 2;
      buyPrice = 5;
      quantity = amount;

      name = "Arrow";
      try {
         image = ImageIO.read(getClass().getResourceAsStream("/sprites/Objects/DroppedItem/arrow.png"));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
