package src.Main;

import src.Entity.*;
import src.Events.Win;
import src.Monster.Monster1;
import src.Objects.*;

public class assetSetter {
   GamePanel gp;

   public assetSetter(GamePanel gp) {
      this.gp = gp;

   }

   public void setObject() {
      // keys
      gp.obj.add(new OBJ_Key(9, 3, 0));
      gp.obj.add(new OBJ_Key(5, 13, 1));

      // doors
      gp.obj.add(new OBJ_Door(5, 3, 0));
      // finalDoor
      gp.obj.add(new OBJ_Door(33, 43, 20));
      // gp.obj.add(new OBJ_Key(33,42,20));

      // chests
      gp.obj.add(new OBJ_Chest(3, 3, 1));

      // test

   }

   public void setNPCs() {

      gp.npcs.add(new NPC1(gp, 39, 38));
      gp.npcs.get(0).dialogue.add("Hi lad!\n...");
      gp.npcs.get(0).dialogue.add("This vally has been infested with \nzombies.\n...");
      gp.npcs.get(0).dialogue.add("That sword of yours seems sharp..");
      gp.npcs.get(0).dialogue.add("Please save us!");

      gp.npcs.add(new NPC1(gp, 4, 3));
      gp.npcs.get(1).dialogue.add("I left my daughter's boat in here.\nI left my life savings here too.\n...");
      gp.npcs.get(1).dialogue.add("When I came to get it back, the door \nlocked on me!...");
      gp.npcs.get(1).dialogue.add("The key she hid may help with the zombies.");

      gp.npcs.add(new NPC2(gp, 40, 40));
      gp.npcs.get(2).dialogue.add("We fled from our village in the \nsouth east.\n...");
      gp.npcs.get(2).dialogue.add("There may be some people there \nstill.");

      gp.npcs.add(new NPC2(gp, 28, 40));
      gp.npcs.get(3).dialogue.add("I have no idea where I am.");

      gp.npcs.add(new NPC3(gp, 40, 41));
      gp.npcs.get(4).dialogue.add("I hid a key on the north west \nbeach!\n...");
      gp.npcs.get(4).dialogue.add("My dad got mad and locked my \nboat in the maze\n...");
      gp.npcs.get(4).dialogue.add("I haven't seen him since.");

      gp.npcs.add(new NPC3(gp, 40, 42));
      gp.npcs.get(5).dialogue.add("Who are you?");

   }

   public void setupMonsters() {
      gp.monsters.add(new Monster1(gp, 10, 2, 2));
      gp.monsters.add(new Monster1(gp, 39, 32, 1));

      gp.monsters.add(new Monster1(gp, 4, 17, 2));
      gp.monsters.add(new Monster1(gp, 17, 18, 1));
      
      gp.monsters.add(new Monster1(gp, 3, 72, 1));
      gp.monsters.add(new Monster1(gp, 6, 75, 2));
      gp.monsters.add(new Monster1(gp, 2, 69, 1));
      gp.monsters.add(new Monster1(gp, 15, 64, 1));
      gp.monsters.add(new Monster1(gp, 10, 18, 1));
      gp.monsters.add(new Monster1(gp, 20, 17, 2));
      gp.monsters.add(new Monster1(gp, 19, 19, 2));
      gp.monsters.add(new Monster1(gp, 19, 19, 1));
      gp.monsters.add(new Monster1(gp, 19, 19, 2));
      gp.monsters.add(new Monster1(gp, 19, 19, 1));

      gp.monsters.add(new Monster1(gp, 33, 14, 1));
      gp.monsters.add(new Monster1(gp, 37, 19, 2));
      gp.monsters.add(new Monster1(gp, 48, 22, 1));
      gp.monsters.add(new Monster1(gp, 35, 64, 1));
      gp.monsters.add(new Monster1(gp, 36, 20, 1));
      gp.monsters.add(new Monster1(gp, 58, 23, 2));
      gp.monsters.add(new Monster1(gp, 54, 40, 2));
      gp.monsters.add(new Monster1(gp, 37, 63, 1));
      gp.monsters.add(new Monster1(gp, 38, 63, 2));
      gp.monsters.add(new Monster1(gp, 37, 70, 1));
      
      gp.monsters.add(new Monster1(gp, 25, 70, 3));
      
      gp.monsters.add(new Monster1(gp, 55, 70, 2));
      gp.monsters.add(new Monster1(gp, 55, 72, 2));
      gp.monsters.add(new Monster1(gp, 55, 74, 2));
      gp.monsters.add(new Monster1(gp, 55, 76, 2));
      gp.monsters.add(new Monster1(gp, 55, 78, 2));
      gp.monsters.add(new Monster1(gp, 55, 68, 2));
      gp.monsters.add(new Monster1(gp, 55,66, 2));
      gp.monsters.add(new Monster1(gp, 55, 65, 2));
      gp.monsters.add(new Monster1(gp, 55, 70, 2));
      gp.monsters.add(new Monster1(gp, 55, 70, 2));
      gp.monsters.add(new Monster1(gp, 55, 70, 2));
      gp.monsters.add(new Monster1(gp, 55, 70, 2));
      gp.monsters.add(new Monster1(gp, 55, 70, 2));

      gp.monsters.add(new Monster1(gp, 4, 35, 2));
      gp.monsters.add(new Monster1(gp, 4, 35, 1));
      gp.monsters.add(new Monster1(gp, 14, 40, 2));
      gp.monsters.add(new Monster1(gp, 4, 35, 1));
      gp.monsters.add(new Monster1(gp, 6, 35, 2));
      gp.monsters.add(new Monster1(gp, 4, 35, 1));
      gp.monsters.add(new Monster1(gp, 9, 40, 2));
      gp.monsters.add(new Monster1(gp, 4, 35, 2));
      
      gp.monsters.add(new Monster1(gp, 2, 10, 10));
      gp.monsters.add(new Monster1(gp, 57, 55, 10));

   }

   public void setEvents() {
      gp.events.add(new Win(33, 43));
   }

}