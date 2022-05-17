package src.Main;

import src.Objects.OBJ_Chest;
import src.Objects.OBJ_Door;
import src.Objects.OBJ_Key;

public class assestSetter {
   GamePanel gp;

   public assestSetter(GamePanel gp)
   {
      this.gp = gp;

   }

   public void setObject()
   {
      //keys
      gp.obj.add(new OBJ_Key(9,3,0));
      gp.obj.add(new OBJ_Key(5,13,1));

      //doors
      gp.obj.add(new OBJ_Door(5,3,0));
      

      //chests
      gp.obj.add(new OBJ_Chest(3,3,1));

   }

}
