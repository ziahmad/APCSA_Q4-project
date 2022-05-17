package src.Items;

import java.util.ArrayList;

import src.Objects.OBJ_Key;

public class Inventory {
   public Item[] equipped = new Item[2];
   public Item[] backPack = new Item[15];
   public int coins = 0;
   public ArrayList<OBJ_Key> keys = new ArrayList<>();

   public Inventory(boolean Player)
   {//TODO: add inventory
      if(Player)
      {
         
      }
   }
}
