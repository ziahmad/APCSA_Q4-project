package src.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import src.Inventory.Inventory;
import src.Inventory.Item;
import src.Main.Consts;
import src.Main.GamePanel;
import src.Main.KeyHandler;
import src.Objects.*;

public class Player extends Entity {
   GamePanel gp;
   KeyHandler keyH;

   //speed
   public double sprintScale = 2;
   public double defaultSpeed = 1*Consts.SCALE;
   public double speedModifier =1;
      //movement
      public boolean isMoving;
   //stamina
   public boolean sCharged = true;
   public int maxStamina = 600;
   public double stamina=maxStamina;
   //health
   public boolean alive = true;
   public int maxHealth = 50;
   public int health=maxStamina;

   public Inventory inventory = new Inventory(true);

   //inventory

   //Constructor
   public Player (GamePanel gPanel, KeyHandler kHandler)
    {
        gp=gPanel;
        keyH=kHandler;

        

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues ()
    {
      //center of world
      worldX= Consts.WORLD_SCREENS_WIDTH/2;
      worldY = Consts.WORLD_SCREENS_HEIGHT/2;
      //center of screen
      screenX=Consts.SCREEN_WIDTH/2-(Consts.TILE_SIZE/2);
      screenY=Consts.SCREEN_HEIGHT/2-(Consts.TILE_SIZE/2);
      //speed
         //TODO check what start speed should be
      speed =defaultSpeed;

      absX = screenX+(worldX*Consts.SCREEN_WIDTH);
      absY = screenY+(worldY*Consts.SCREEN_HEIGHT);

      //collider is on player
      solidArea= new Rectangle((int)screenX+4*Consts.SCALE,(int)screenY+6*Consts.SCALE,8*Consts.SCALE,10*Consts.SCALE);
      //starts fcaing downwards
      direction = "down";
    }

    public void getPlayerImage ()
    {
        //gets player sprites
        //TODO: implement non movement sprites
        try {
            //movement sprites
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/up2.png"));
            up0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/up0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/down2.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/down0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/left2.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/left0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/right2.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/right0.png"));
            //attack sprites
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

   public void update()
   {
      //set solid area of current player position
      solidArea= new Rectangle((int)(screenX+8),(int)(screenY+12),8*Consts.SCALE,10*Consts.SCALE);
      
      isMoving =keyH.downPressed||keyH.rightPressed||keyH.leftPressed||keyH.upPressed;
      if(isMoving)
      {
      updateStamina(sCharged, isMoving);
      checkDirection(isMoving);
      checkCollision();
      move();
      }

   
   }

   public void updateStamina(boolean sCharged, boolean isMoving)
   {
      if(stamina>=maxStamina)
      {
         stamina=maxStamina;
      }
      if (stamina<0)
         {
                this.sCharged=false;
                stamina=0;
         }
      if(sCharged)
      {
         if(isMoving&&keyH.shiftPressed)
         {
            speed=defaultSpeed*sprintScale*speedModifier;
            stamina-=3;
            
         }else if(stamina<maxStamina)
         {
            stamina+=1;
         }

         if(isMoving&&!keyH.shiftPressed)
         {
            speed=defaultSpeed*speedModifier;
         }
         


      }else
      {
         speed=defaultSpeed*.6*speedModifier;
         if(stamina<maxStamina)
         {
            stamina+=1;
         }
         if(stamina>=maxStamina)
         {
            this.sCharged=true;
            speed=defaultSpeed*speedModifier;
         }


      }
   }
   
   public void checkDirection(boolean isMoving)
   {
      if (keyH.upPressed)
      {
          direction ="up";
          
      }
      else if (keyH.downPressed)
      {
        direction ="down";
        
      }
      else if (keyH.leftPressed)
      {
          direction ="left";
        
      }
      else if (keyH.rightPressed)
      {
          direction = "right";
          
      }
      
   }

   public void checkCollision()
   {
      collisionOn = false;
      gp.cChecker.checkTile(this);
      //object touch
      int objIndex =gp.cChecker.checkObject(this, true);
      pickUpObjects(objIndex);
   }

   public void move()
   {
      absX = screenX+(worldX*Consts.SCREEN_WIDTH);
      absY = screenY+(worldY*Consts.SCREEN_HEIGHT);

      if(collisionOn==false)
      {
         switch(direction)
         {
            case "up":
                screenY-=speed;
                if(screenY<=0&&worldY>0)
                {
                    screenY=(Consts.MAX_SCREEN_ROW-1)*Consts.TILE_SIZE;
                    worldY--;
                }
                break;
            case "down":
                screenY+=speed;
                if(screenY>=(Consts.MAX_SCREEN_ROW-1)*Consts.TILE_SIZE&&worldY<Consts.WORLD_SCREENS_HEIGHT)
                {
                    screenY=(0)*Consts.TILE_SIZE;
                    worldY++;
                }
                break;
            case "left":
                screenX-=speed;
                if(screenX<=0&&worldX>0)
                {
                    screenX=(Consts.MAX_SCREEN_ROW-1)*Consts.TILE_SIZE;
                    worldX--;
                }
                break;
            case "right":
                screenX+=speed;
                if(screenX>=(Consts.MAX_SCREEN_COL-1)*Consts.TILE_SIZE&&worldX<Consts.WORLD_SCREENS_WIDTH)
                {
                    screenX=(0)*Consts.TILE_SIZE;
                    worldX++;
                }
                break;
         }
      }
            

      //change sprite for animation
      spriteCounter++;
      if(spriteCounter>12/(speed/defaultSpeed))
      {
          spriteNum++;
          spriteNum%=2;
          /*
          if(spriteNum==1)
          {
              spriteNum=2;
          }else if(spriteNum==2)
          {
              spriteNum=1;
          }*/
          spriteCounter=0;
      }
   }


   public void pickUpObjects(int index)
   {
       if(index>=0)
       {
           String obj_Name = gp.obj.get(index).name;
           SuperObject object =gp.obj.get(index);
           switch(obj_Name)
           {
               case"Key":
                   inventory.keys.add((OBJ_Key)object);
                   gp.obj.set(index, null);
                   gp.obj.remove(index);
                   break;
               case"Door":
                   if(((OBJ_Door)object).locked)
                   {
                       for (OBJ_Key key : inventory.keys) 
                       {
                           if(key.keyLockPairing==((OBJ_Door)object).keyLockPairing)
                           {
                               object.image=((OBJ_Door)object).unlockedDoor;
                               object.collision=false;
                               ((OBJ_Door)object).locked=false;
                               int i = inventory.keys.indexOf(key);
                               inventory.keys.remove(i);
                               break;
                           }
                       }
                   }else{
                       ((OBJ_Door)object).hidden = true;
                   }
                    
                   break;
               case "Chest":
                   if(((OBJ_Chest)object).locked)
                   {
                       for (OBJ_Key key : inventory.keys) 
                       {
                           if(key.keyLockPairing==((OBJ_Chest)object).keyLockPairing)
                           {
                               object.image=((OBJ_Chest)object).unlockedChest;
                               ((OBJ_Chest)object).locked=false;
                               int i = inventory.keys.indexOf(key);
                               inventory.keys.remove(i);
                               break;
                           }
                       }
                   }
                   break;
                default:
                   if(!((SuperDropedItem)object).armed)
                   {
                       for(int i = 0; i<inventory.inventory.length;i++)
                       {
                            if(inventory.inventory[i]!=null)
                            {
                                if(object.name.equals(inventory.inventory[i].type.name))
                                {
                                    inventory.inventory[i].quantity+=((SuperDropedItem)object).quantity;
                                    gp.obj.set(index, null);
                                    gp.obj.remove(index);
                                    break;
                               
                                }
                            }else{
                                inventory.inventory[i]= new Item(object);
                                inventory.inventory[i].quantity+=((SuperDropedItem)object).quantity;
                                break;

                            }
                           
                       }
                       if(((SuperDropedItem)object).quantity!=1)
                       {
                       System.out.println("You picked up "+((SuperDropedItem)object).quantity+" "+object.name+"s" );
                       }else{
                        System.out.println("You picked up "+((SuperDropedItem)object).quantity+" "+object.name );
                       }
                   }
                   break;
            }
           
       }
   }



   public void draw(Graphics2D g2)
   {
   
       BufferedImage image = null;

       switch(direction)
       {
           case "up":
               if(spriteNum==0)
               {
               image=up1;
               }
               if(spriteNum==1)
               {
               image=up2;
               }
               if(!isMoving)
               {
                   image = up0;
               }
               break;
           case "down":
               if(spriteNum==0)
               {
               image=down1;
               }
               if(spriteNum==1)
               {
               image=down2;
               }
               if(!isMoving)
               {
                   image = down0;
               }
               break;
           case "left":
               if(spriteNum==0)
               {
               image=left1;
               }
               if(spriteNum==1)
               {
               image=left2;
               }
               if(!isMoving)
               {
                   image = left0;
               }
               break;
           case "right":
               if(spriteNum==0)
               {
               image=right1;
               }
               if(spriteNum==1)
               {
               image=right2;
               }
               if(!isMoving)
               {
                   image = right0;
               }
               break;
       }
       
       g2.drawImage(image,(int)screenX,(int)screenY,Consts.TILE_SIZE,Consts.TILE_SIZE,null);
       g2.setColor(Color.BLUE);
       g2.draw(solidArea);

   }



}
