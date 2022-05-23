package src.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import src.Events.*;
import src.Inventory.Inventory;
import src.Inventory.Item;
import src.Main.Consts;
import src.Main.GamePanel;
import src.Main.KeyHandler;
import src.Objects.*;

public class Player extends Entity {

   KeyHandler keyH;

   public boolean win = false;
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
   public int maxHealth = 100;
   public int health=maxHealth;
   public int damageWait=30;

   public int actionWait = 20;

   public int shopKeepSpawn;

   public Inventory inventory = new Inventory(true);

   //inventory

   //Constructor
   public Player (GamePanel gp, KeyHandler kHandler)
    {
        super(gp);

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
      solidArea.x= (int)screenX+8;
      solidArea.y= (int)screenY+12;
      
      
      
      isMoving =keyH.downPressed||keyH.rightPressed||keyH.leftPressed||keyH.upPressed;

      if(damageWait<40)
      {
        damageWait++;
      }
          updateStamina(sCharged, isMoving);
          checkDirection();
          checkCollision();
          move();
       doItem();

       if(win)
       {
           gp.gameState=gp.WIN_STATE;
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
                  gp.ui.stamina=gp.ui.staminadDepleted;
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
              gp.ui.stamina=gp.ui.staminaFull;
              speed=defaultSpeed*speedModifier;
           }


        }
    
   }
   
   public void checkDirection()
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
      //object touch
      int objIndex =gp.cChecker.checkObject(this, true);
      pickUpObjects(objIndex);
      //events
      int eventIndex = gp.cChecker.checkEvent(this, true);
        doEvent(eventIndex);
      //check NPC Collison
      
      if(isMoving)
      {
        gp.cChecker.checkTile(this);
        int npcIndex =gp.cChecker.checkEntity(this, gp.npcs);
        interactNPC(npcIndex);
      }
   }

    public void interactNPC(int npcIndex) {
        if (gp.keyH.spacePressed)
        {
            if(npcIndex!=-1)
            {
                gp.gameState = gp.DIALOGUE_STATE;
                gp.npcs.get(npcIndex).speak();
            }
        }
    }

    public void move()
   {
       if(isMoving)
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
                      shopKeepSpawn = (int)(Math.random()*10);
                  }
                  break;
              case "down":
                  screenY+=speed;
                  if(screenY>=(Consts.MAX_SCREEN_ROW-1)*Consts.TILE_SIZE&&worldY<Consts.WORLD_SCREENS_HEIGHT)
                  {
                      screenY=(0)*Consts.TILE_SIZE;
                      worldY++;
                      shopKeepSpawn = (int)(Math.random()*10);
                  }
                  break;
              case "left":
                  screenX-=speed;
                  if(screenX<=0&&worldX>0)
                  {
                      screenX=(Consts.MAX_SCREEN_ROW-1)*Consts.TILE_SIZE;
                      worldX--;
                      shopKeepSpawn = (int)(Math.random()*10);
                  }
                  break;
              case "right":
                  screenX+=speed;
                  if(screenX>=(Consts.MAX_SCREEN_COL-1)*Consts.TILE_SIZE&&worldX<Consts.WORLD_SCREENS_WIDTH)
                  {
                      screenX=(0)*Consts.TILE_SIZE;
                      worldX++;
                      shopKeepSpawn = (int)(Math.random()*10);
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
                                    break;
                               
                                }
                            }else{
                                inventory.inventory[i]= new Item(object);
                                inventory.inventory[i].quantity+=((SuperDropedItem)object).quantity;
                                break;

                            }
                           
                       }
                       gp.obj.set(index, null);
                       gp.obj.remove(index);
                                    
                       if(((SuperDropedItem)object).quantity!=1)
                       {
                       System.out.println("You picked up "+((SuperDropedItem)object).quantity+" "+object.name+"s" );
                       }else{
                        System.out.println("You picked up "+1+" "+object.name );
                       }
                   }
                   break;
            }
           
       }
   }

   public void doEvent(int index)
   {
       if(index>=0)
       {
           String Name = gp.events.get(index).name;
           Event event =gp.events.get(index);
           switch(Name)
           {
               case"hurt":
                if(damageWait>=30)
                {
                    this.health-=((Hurt)event).damage;
                   gp.events.set(index, null);
                   gp.events.remove(index);
                   System.out.println("ow");
                   Name="null";
                   damageWait=0;
                }
                   break;
               case"win":
                   this.win=true;
                   break;
               
            }
           
       }
   }

   public SuperDropedItem useItem()
   {
       if(gp.keyH.enterPressed)
       {
           return (SuperDropedItem)inventory.equipped[1].type;
       }
       return null;
   }

   public void doItem()
   {
       
    if(actionWait<30)
        {
        actionWait++;
        }
        
    if(actionWait>20&&keyH.enterPressed)
        {
            this.gp.obj.add(new OBJ_Bomb((int)screenX/Consts.TILE_SIZE+worldX*Consts.MAX_SCREEN_COL, (int)screenY/Consts.TILE_SIZE+worldY*Consts.MAX_SCREEN_ROW, true));
               // if (useItem()!=null)
                {

                }
                actionWait=0;
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
