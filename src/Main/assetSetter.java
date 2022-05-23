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
      gp.npcs.get(0).dialogue.add("Hi lad!");
      gp.npcs.get(0).dialogue.add("This vally has been infested with zombies.");
      gp.npcs.get(0).dialogue.add("That sword of yours seems sharp");
      gp.npcs.get(0).dialogue.add("Please save us");

      gp.npcs.add(new NPC1(gp, 4, 3));
      gp.npcs.get(1).dialogue.add("I left my daughter's boat in here.");
      gp.npcs.get(1).dialogue.add("When I came to get it back, the door locked on me.");
      gp.npcs.get(1).dialogue.add("The key she hid may help with the zombies");
      
      gp.npcs.add(new NPC2(gp, 40, 40));
      gp.npcs.get(2).dialogue.add("we fled from our village \nin the south east");
      gp.npcs.get(2).dialogue.add("There may be some people there still");
      
      gp.npcs.add(new NPC2(gp, 28, 40));
      gp.npcs.get(3).dialogue.add("I have no idea where I am");
      
      gp.npcs.add(new NPC3(gp, 40, 41));
      gp.npcs.get(4).dialogue.add("I hid a key on the north west beach");
      gp.npcs.get(4).dialogue.add("My dad got mad and locked my boat in the maze");
      gp.npcs.get(4).dialogue.add("I haven't seen him since");
      
      gp.npcs.add(new NPC3(gp, 40, 41));
      gp.npcs.get(5).dialogue.add("Who are you?");

      
   }

}