package src.Main;

import src.Entity.*;
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
      
      
   }

   public void setNPCs()
   {
      gp.npcs.add(new NPC1(gp, 41, 40));
      gp.npcs.add(new NPC1(gp, 41, 40));
      gp.npcs.add(new NPC2(gp, 40, 40));
      gp.npcs.add(new NPC2(gp, 40, 40));
      gp.npcs.add(new NPC3(gp, 40, 41));
      gp.npcs.add(new NPC3(gp, 40, 41));
      
   }

}