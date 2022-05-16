package src.Main;

import src.Entity.Entity;
import src.Entity.Player;
import src.Objects.SuperObject;

public class CollisionChecker {
   
   private GamePanel gp;

   public CollisionChecker(GamePanel gp)
   {
      this.gp = gp;



   }

   public void checkTile(Entity entity)
   {  
      int entityLeftAbsX = (int)entity.absX+ entity.solidArea.x;
      int entityRightAbsX = (int)entity.absX+ entity.solidArea.x + entity.solidArea.width;
      int entityTopAbsY = (int)entity.absY+ entity.solidArea.y;
      int entityBotAbsY = (int)entity.absY+ entity.solidArea.y + entity.solidArea.height;
      
      int entityLeftCol = entityLeftAbsX/Consts.TILE_SIZE;
      int entityRightCol = entityRightAbsX/Consts.TILE_SIZE;
      int entityTopRow = entityTopAbsY/Consts.TILE_SIZE;
      int entityBotRow = entityBotAbsY/Consts.TILE_SIZE;
   
   
      int tileNum1, tileNum2;

      switch(entity.direction){
         case "up":
            entityTopRow = (int)((entityTopAbsY-entity.speed))/Consts.TILE_SIZE;
            tileNum1=gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2=gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true)
            {
               entity.collisionOn=true;
            }
            if (entity instanceof Player &&(gp.tileM.tile[tileNum1].speedTile==true&&gp.tileM.tile[tileNum2].speedTile==true))
            {
               ((Player)entity).speedModifier=gp.tileM.tile[tileNum1].speedModifier;
            }
            break;
         case "down":
            entityBotRow = ((int)((entityBotAbsY+entity.speed+1)))/Consts.TILE_SIZE;
            tileNum1=gp.tileM.mapTileNum[entityBotRow][entityLeftCol];
            tileNum2=gp.tileM.mapTileNum[entityBotRow][entityRightCol];
            if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true)
            {
               entity.collisionOn=true;
            }
            if (entity instanceof Player &&(gp.tileM.tile[tileNum1].speedTile==true&&gp.tileM.tile[tileNum2].speedTile==true))
            {
               ((Player)entity).speedModifier=gp.tileM.tile[tileNum1].speedModifier;
            }
            break;
         case "left":
            entityLeftCol = (int)((entityLeftAbsX-entity.speed))/Consts.TILE_SIZE;
            tileNum1=gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2=gp.tileM.mapTileNum[entityBotRow][entityLeftCol];
            if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true)
            {
               entity.collisionOn=true;
            }
            if (entity instanceof Player &&(gp.tileM.tile[tileNum1].speedTile==true&&gp.tileM.tile[tileNum2].speedTile==true))
            {
               ((Player)entity).speedModifier=gp.tileM.tile[tileNum1].speedModifier;
            }
            break;
         case "right":
            entityRightCol = (int)((entityRightAbsX+entity.speed+1))/Consts.TILE_SIZE;
            tileNum1=gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            tileNum2=gp.tileM.mapTileNum[entityBotRow][entityRightCol];
            if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true)
            {
               entity.collisionOn=true;
            }
            if (entity instanceof Player &&(gp.tileM.tile[tileNum1].speedTile==true&&gp.tileM.tile[tileNum2].speedTile==true))
            {
               ((Player)entity).speedModifier=gp.tileM.tile[tileNum1].speedModifier;
            }
            break;


      }
   
   }
   public int checkObject(Entity entity, boolean player )
   {
      int i=0;



      return i;
   }


}
