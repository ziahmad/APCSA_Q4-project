package src.Main;

import src.Objects.OBJ_Key;

public class assestSetter {
   GamePanel gp;

   public assestSetter(GamePanel gp)
   {
      this.gp = gp;

   }

   public void setObject()
   {
      gp.obj[0] = new OBJ_Key();
      gp.obj[0].absX = 9*gp.TILE_SIZE;
      gp.obj[0].absY = 3*gp.TILE_SIZE;

      gp.obj[1] = new OBJ_Key();
      gp.obj[1].absX = 5*gp.TILE_SIZE;
      gp.obj[1].absY = 13*gp.TILE_SIZE;
   }

}
