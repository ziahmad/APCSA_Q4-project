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
      gp.obj.add(new OBJ_Key(9,3));


      gp.obj.add(new OBJ_Key(5,13));


   }

}
