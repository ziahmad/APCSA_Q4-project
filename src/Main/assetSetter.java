package src.Main;

import src.Objects.*;


public class assetSetter {
   GamePanel gp;

   public assetSetter(GamePanel gp)
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
      
      //test
      gp.obj.add(new OBJ_Bomb(43,44,true));
      
   }

}