package src.Main;


import src.Entity.Entity;
import src.Entity.Player;
import src.Objects.OBJ_Door;
import src.Objects.SuperObject;


public class CollisionChecker {
   
   private GamePanel gp;

   public CollisionChecker(GamePanel gp)
   {
      this.gp = gp;



   }

   public void checkTile(Entity entity)
   {  
      int entityLeftAbsX = entity.worldX*Consts.MAX_SCREEN_COL*Consts.TILE_SIZE +entity.solidArea.x;
      int entityRightAbsX = entityLeftAbsX + entity.solidArea.width;
      int entityTopAbsY = entity.worldY*Consts.MAX_SCREEN_ROW*Consts.TILE_SIZE+entity.solidArea.y;
      int entityBotAbsY = entityTopAbsY + entity.solidArea.height;
      
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
            if (entity instanceof Player)
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
            if (entity instanceof Player)
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
            if (entity instanceof Player)
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
            if (entity instanceof Player )
            {
               ((Player)entity).speedModifier=gp.tileM.tile[tileNum1].speedModifier;
            }
            break;


      }
   
   }

   public int checkObject(Entity entity, boolean player )
   {
      double entityleftX = entity.solidArea.x;
      double entityrightX = entity.solidArea.x+entity.solidArea.getWidth();
      double entitytopY = entity.solidArea.y;
      double entitybotY = entity.solidArea.y+entity.solidArea.getHeight();
      
//TODO:Major Issue, If you walk into an object with collision, you get stuck
//moving backwards deosn't help becuase can cause abilyty to walk throuhg object

      for (SuperObject S : gp.obj) 
      {
         
         if(entity.worldX==S.worldX&&entity.worldY==S.worldY)
         {
            
            switch(entity.direction)
            {
               case"up":

                  if(S.solidArea.contains(entityleftX, entitytopY-entity.speed)||S.solidArea.contains(entityrightX, entitytopY-entity.speed))
                  {
                     
                     if(S.collision)
                     {
                        entity.collisionOn=true;
                     
                     }
                     if(player)
                        return gp.obj.indexOf(S);
                  }
                  break;
               case"down":
                  if(S.solidArea.contains(entityleftX, entitybotY+entity.speed)||S.solidArea.contains(entityrightX, entitybotY+entity.speed))
                  {
                     if(S.collision)
                     {
                        entity.collisionOn=true;

                     }
                     if(player)
                        return gp.obj.indexOf(S);
                  }
                  
                  break;
               case"left":
                  if(S.solidArea.contains(entityleftX-entity.speed, entitytopY)||S.solidArea.contains(entityleftX, entitybotY))
                  {
                     if(S.collision)
                     {
                        entity.collisionOn=true;

                     }
                     if(player)
                        return gp.obj.indexOf(S);
                  }
                  break;
               case"right":
                  if(S.solidArea.contains(entityrightX+entity.speed, entitytopY)||S.solidArea.contains(entityrightX+entity.speed, entitybotY))
                     {
                     if(S.collision)
                     {
                        entity.collisionOn=true;

                     }
                     if(player)
                        return gp.obj.indexOf(S);
                  }
                  break;
            }
         
         }
         //open and close door
         if(entity.worldX==S.worldX&&entity.worldY==S.worldY&&entity.solidArea.intersects(S.solidArea))
         {

            if(S instanceof OBJ_Door&&!S.collision)
            {
               S.hidden=true;
            }
         }else if (S instanceof OBJ_Door){
               S.hidden=false;
         }
      }

      return -1;
   }


}
