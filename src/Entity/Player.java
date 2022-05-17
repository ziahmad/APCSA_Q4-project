package src.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import src.Items.Inventory;
import src.Main.Consts;
import src.Main.GamePanel;
import src.Main.KeyHandler;
import src.Objects.*;
/**
 * 
 * notes or future, to get player to mmove in the screen
 * then move camera after passing edge, consider sub coordinates
 * 
 */

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public double sprintScale = 2;
    public double defaultSpeed = 1*Consts.SCALE;
    public double speedModifier =1;
    public int maxStamina = 600;
    public boolean sCharge = true;
    public double stamina=maxStamina;

    public Inventory inventory=new Inventory(true);



    

    public boolean isMoving;

    public Player (GamePanel gPanel, KeyHandler kHandler)
    {
        gp=gPanel;
        keyH=kHandler;

        

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues ()
    {
        worldX= Consts.WORLD_SCREENS_WIDTH/2;
        worldY = Consts.WORLD_SCREENS_HEIGHT/2;
        screenX=Consts.SCREEN_WIDTH/2-(Consts.TILE_SIZE/2);
        screenY=Consts.SCREEN_HEIGHT/2-(Consts.TILE_SIZE/2);
        speed =2*defaultSpeed;

        absX = screenX+(worldX*Consts.SCREEN_WIDTH);
        absY = screenY+(worldY*Consts.SCREEN_HEIGHT);

        solidArea= new Rectangle((int)screenX+4*Consts.SCALE,(int)screenY+6*Consts.SCALE,8*Consts.SCALE,10*Consts.SCALE);
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
        solidArea= new Rectangle((int)(screenX+8),(int)(screenY+12),8*Consts.SCALE,10*Consts.SCALE);
        //TODO: add stamina meter
        isMoving =keyH.downPressed||keyH.rightPressed||keyH.leftPressed||keyH.upPressed;
        
        if(stamina<maxStamina)
        {
            stamina+=1;
            
        }else if(stamina>=maxStamina)
        {
            stamina=maxStamina;
            sCharge=true;
        }

        if(isMoving)
        {
            if (keyH.shiftPressed&&sCharge)
            {
                speed=defaultSpeed*sprintScale*speedModifier;
                stamina-=3;
                if (stamina<=0)
                {
                    sCharge=false;
                    stamina=0;
                }

            }else if (!sCharge){
                speed=defaultSpeed*.6*speedModifier;


            }else{
                speed=defaultSpeed*speedModifier;
                
            }
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
            absX = screenX+(worldX*Consts.SCREEN_WIDTH);
            absY = screenY+(worldY*Consts.SCREEN_HEIGHT);
            //collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //object touch
            int objIndex =gp.cChecker.checkObject(this, true);
            pickUpObjects(objIndex);
                //if not colliding
            if(collisionOn==false)
            {
                switch(direction){
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
                    for (OBJ_Key key : inventory.keys) {
                        if(key.keyLockPairing==((OBJ_Door)object).keyLockPairing)
                        {
                            object.image=((OBJ_Door)object).unlockedDoor;
                            object.collision=false;
                            int i = inventory.keys.indexOf(key);
                            inventory.keys.remove(i);
                            break;
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
