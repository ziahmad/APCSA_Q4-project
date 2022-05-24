package src.Inventory;

import java.util.ArrayList;

import src.Objects.OBJ_Key;

public class Inventory {
   public Item[] equipped;
   public Item[] inventory;
   public int coins;
   public ArrayList<OBJ_Key> keys = new ArrayList<>();

   public Inventory(boolean Player) {// TODO: add inventory
      if (Player) {
         coins = 0;
         equipped = new Item[2];
         inventory = new Item[15];
         keys = new ArrayList<>();
      } else {
         inventory = new Item[9];
      }
   }
}