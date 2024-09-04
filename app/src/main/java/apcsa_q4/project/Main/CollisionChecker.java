package apcsa_q4.project.Main;

import java.util.ArrayList;

import apcsa_q4.project.Entity.Entity;
import apcsa_q4.project.Entity.Player;
import apcsa_q4.project.Events.Event;
import apcsa_q4.project.Monster.Monster;
import apcsa_q4.project.Objects.OBJ_Door;
import apcsa_q4.project.Objects.SuperObject;

public class CollisionChecker {

   private GamePanel gp;

   public CollisionChecker(GamePanel gp) {
      this.gp = gp;

   }

   public void checkTile(Entity entity) {
      int entityLeftAbsX = entity.worldX * Consts.MAX_SCREEN_COL * Consts.TILE_SIZE + entity.solidArea.x;
      int entityRightAbsX = entityLeftAbsX + entity.solidArea.width;
      int entityTopAbsY = entity.worldY * Consts.MAX_SCREEN_ROW * Consts.TILE_SIZE + entity.solidArea.y;
      int entityBotAbsY = entityTopAbsY + entity.solidArea.height;

      int entityLeftCol = entityLeftAbsX / Consts.TILE_SIZE;
      int entityRightCol = entityRightAbsX / Consts.TILE_SIZE;
      int entityTopRow = entityTopAbsY / Consts.TILE_SIZE;
      int entityBotRow = entityBotAbsY / Consts.TILE_SIZE;

      int tileNum1, tileNum2;

      switch (entity.direction) {
         case "up":
            entityTopRow = (int) ((entityTopAbsY - entity.speed)) / Consts.TILE_SIZE;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
               entity.collisionOn = true;
            }
            if (entity instanceof Player) {
               ((Player) entity).speedModifier = gp.tileM.tile[tileNum1].speedModifier;
            }
            break;
         case "down":
            entityBotRow = ((int) ((entityBotAbsY + entity.speed + 1))) / Consts.TILE_SIZE;
            tileNum1 = gp.tileM.mapTileNum[entityBotRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityBotRow][entityRightCol];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
               entity.collisionOn = true;
            }
            if (entity instanceof Player) {
               ((Player) entity).speedModifier = gp.tileM.tile[tileNum1].speedModifier;
            }
            break;
         case "left":
            entityLeftCol = (int) ((entityLeftAbsX - entity.speed)) / Consts.TILE_SIZE;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityBotRow][entityLeftCol];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
               entity.collisionOn = true;
            }
            if (entity instanceof Player) {
               ((Player) entity).speedModifier = gp.tileM.tile[tileNum1].speedModifier;
            }
            break;
         case "right":
            entityRightCol = (int) ((entityRightAbsX + entity.speed + 1)) / Consts.TILE_SIZE;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            tileNum2 = gp.tileM.mapTileNum[entityBotRow][entityRightCol];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
               entity.collisionOn = true;
            }
            if (entity instanceof Player) {
               ((Player) entity).speedModifier = gp.tileM.tile[tileNum1].speedModifier;
            }
            break;

      }

   }

   public int checkObject(Entity entity, boolean player) {
      double entityleftX = entity.solidArea.x;
      double entityrightX = entity.solidArea.x + entity.solidArea.getWidth();
      double entitytopY = entity.solidArea.y;
      double entitybotY = entity.solidArea.y + entity.solidArea.getHeight();

      for (SuperObject S : gp.obj) {

         if (entity.worldX == S.worldX && entity.worldY == S.worldY) {

            switch (entity.direction) {
               case "up":

                  if (S.solidArea.contains(entityleftX, entitytopY - entity.speed)
                        || S.solidArea.contains(entityrightX, entitytopY - entity.speed)) {

                     if (S.collision) {
                        entity.collisionOn = true;

                     }
                     if (player)
                        return gp.obj.indexOf(S);
                  }
                  break;
               case "down":
                  if (S.solidArea.contains(entityleftX, entitybotY + entity.speed)
                        || S.solidArea.contains(entityrightX, entitybotY + entity.speed)) {
                     if (S.collision) {
                        entity.collisionOn = true;

                     }
                     if (player)
                        return gp.obj.indexOf(S);
                  }

                  break;
               case "left":
                  if (S.solidArea.contains(entityleftX - entity.speed, entitytopY)
                        || S.solidArea.contains(entityleftX, entitybotY)) {
                     if (S.collision) {
                        entity.collisionOn = true;

                     }
                     if (player)
                        return gp.obj.indexOf(S);
                  }
                  break;
               case "right":
                  if (S.solidArea.contains(entityrightX + entity.speed, entitytopY)
                        || S.solidArea.contains(entityrightX + entity.speed, entitybotY)) {
                     if (S.collision) {
                        entity.collisionOn = true;

                     }
                     if (player)
                        return gp.obj.indexOf(S);
                  }
                  break;
            }

         }
         // open and close door
         if (player && entity.worldX == S.worldX && entity.worldY == S.worldY
               && entity.solidArea.intersects(S.solidArea)) {

            if (S instanceof OBJ_Door && !S.collision) {
               S.hidden = true;
            }
         } else if (S instanceof OBJ_Door) {
            S.hidden = false;
         }
      }

      return -1;
   }

   // npc or monster collision
   public int checkEntity(Entity entity, ArrayList<Entity> target) {
      double entityleftX = entity.solidArea.x;
      double entityrightX = entity.solidArea.x + entity.solidArea.getWidth();
      double entitytopY = entity.solidArea.y;
      double entitybotY = entity.solidArea.y + entity.solidArea.getHeight();

      for (Entity e2 : target) {

         if (entity.worldX == e2.worldX && entity.worldY == e2.worldY) {

            switch (entity.direction) {
               case "up":

                  if (e2.solidArea.contains(entityleftX, entitytopY - entity.speed)
                        || e2.solidArea.contains(entityrightX, entitytopY - entity.speed)) {

                     entity.collisionOn = true;
                     return target.indexOf(e2);
                  }
                  break;
               case "down":
                  if (e2.solidArea.contains(entityleftX, entitybotY + entity.speed)
                        || e2.solidArea.contains(entityrightX, entitybotY + entity.speed)) {

                     entity.collisionOn = true;

                     return target.indexOf(e2);
                  }

                  break;
               case "left":
                  if (e2.solidArea.contains(entityleftX - entity.speed, entitytopY)
                        || e2.solidArea.contains(entityleftX, entitybotY)) {
                     entity.collisionOn = true;

                     return target.indexOf(e2);
                  }
                  break;
               case "right":
                  if (e2.solidArea.contains(entityrightX + entity.speed, entitytopY)
                        || e2.solidArea.contains(entityrightX + entity.speed, entitybotY)) {

                     entity.collisionOn = true;

                     return target.indexOf(e2);
                  }
                  break;
            }

         }

      }

      return -1;
   }

   public void checkPlayer(Entity entity) {

      double entityleftX = entity.solidArea.x;
      double entityrightX = entity.solidArea.x + entity.solidArea.getWidth();
      double entitytopY = entity.solidArea.y;
      double entitybotY = entity.solidArea.y + entity.solidArea.getHeight();

      Entity e2 = gp.player;

      if (entity.worldX == e2.worldX && entity.worldY == e2.worldY) {

         switch (entity.direction) {
            case "up":

               if (e2.solidArea.contains(entityleftX, entitytopY - entity.speed)
                     || e2.solidArea.contains(entityrightX, entitytopY - entity.speed)) {

                  entity.collisionOn = true;

               }
               break;
            case "down":
               if (e2.solidArea.contains(entityleftX, entitybotY + entity.speed)
                     || e2.solidArea.contains(entityrightX, entitybotY + entity.speed)) {

                  entity.collisionOn = true;

               }

               break;
            case "left":
               if (e2.solidArea.contains(entityleftX - entity.speed, entitytopY)
                     || e2.solidArea.contains(entityleftX, entitybotY)) {
                  entity.collisionOn = true;

               }
               break;
            case "right":
               if (e2.solidArea.contains(entityrightX + entity.speed, entitytopY)
                     || e2.solidArea.contains(entityrightX + entity.speed, entitybotY)) {

                  entity.collisionOn = true;

               }
               break;
         }

      }
   }

   public int checkEvent(Entity entity) {

      for (Event e : gp.events) {

         if (entity.worldX == e.worldX && entity.worldY == e.worldY) {
            if (e.solidArea.intersects(entity.solidArea) || e.solidArea.contains(entity.solidArea)
                  || entity.solidArea.contains(e.solidArea)) {
               return gp.events.indexOf(e);
            }

         }

      }
      return -1;
   }

   public int checkMonster(Entity entity) {

      for (Monster e : gp.monsters) {

         if (entity.worldX == e.worldX && entity.worldY == e.worldY) {
            if (e.solidArea.intersects(entity.solidArea) || e.solidArea.contains(entity.solidArea)
                  || entity.solidArea.contains(e.solidArea)) {
               return gp.monsters.indexOf(e);
            }

         }

      }
      return -1;
   }
}
