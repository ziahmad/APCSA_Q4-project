package src.Objects;

import javax.imageio.ImageIO;

public class OBJ_Coin extends SuperDropedItem {

   public int quantity;
   public OBJ_Coin(int worldCol, int worldRow, int worth) {
      super(worldCol, worldRow);
      quantity=worth;
      name = "Coin";
      solidArea.grow(10, 10);
      try {
         image = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Objects/DroppedItem/coins.png"));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
}
