package apcsa_q4.project.Objects;

public class SuperDropedItem extends SuperObject {
   public int quantity;
   public int sellPrice;
   public int buyPrice;
   public boolean armed = false;

   public SuperDropedItem(int worldCol, int worldRow) {
      super(worldCol, worldRow);
   }

}
