package apcsa_q4.project.Inventory;

import apcsa_q4.project.Objects.SuperObject;

import java.awt.image.BufferedImage;

public class Item {

   public int quantity = 0;
   public BufferedImage bi;
   public SuperObject type;

   public Item(SuperObject sO) {
      type = sO;
      bi = sO.image;
   }

}
