package src.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import src.Main.Consts;
import src.Main.GamePanel;
import src.Main.KeyHandler;
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
    public int maxStamina = 300;
    public boolean sCharge = true;
    public double stamina=maxStamina;

    public double screenX;
    public double screenY;

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
        direction = "down";
    }

    public void getPlayerImage ()
    {
        //gets player sprites
        //TODO: implement actual sprites
        try {
            //movement sprites
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/sprites/Player/right2.png"));
            //attack sprites
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    public void update()
    {
        //TODO: add stamina meter
        isMoving =keyH.downPressed||keyH.rightPressed||keyH.leftPressed||keyH.upPressed;
        
        if((!sCharge||!isMoving)&&stamina<maxStamina)
        {
            stamina+=0.75;
            
        }else if(stamina>=maxStamina)
        {
            stamina=maxStamina;
            sCharge=true;
        }

        if(isMoving)
        {
            if (keyH.shiftPressed&&sCharge)
            {
                speed=defaultSpeed*sprintScale;
                stamina--;
                if (stamina<=0)
                {
                    sCharge=false;
                    stamina=0;
                }

            }else if (!sCharge){
                speed=defaultSpeed*.7;


            }else{
                speed=defaultSpeed;
                
            }
            if (keyH.upPressed)
            {
                direction ="up";
                screenY-=speed;
                if(screenY<=0&&worldY>0)
                {
                    screenY=(Consts.MAX_SCREEN_ROW-1)*Consts.TILE_SIZE;
                    worldY--;
                }
            }
            else if (keyH.downPressed)
            {
              direction ="down";
              screenY+=speed;
              if(screenY>=(Consts.MAX_SCREEN_ROW-1)*Consts.TILE_SIZE&&worldY<Consts.WORLD_SCREENS_HEIGHT)
              {
                  screenY=(0)*Consts.TILE_SIZE;
                  worldY++;
              }
            }
            else if (keyH.leftPressed)
            {
              direction ="left";
              screenX-=speed;
              if(screenX<=0&&worldX>0)
                {
                    screenX=(Consts.MAX_SCREEN_ROW-1)*Consts.TILE_SIZE;
                    worldX--;
                }
            }
            else if (keyH.rightPressed)
            {
                direction = "right";
                screenX+=speed;
                if(screenX>=(Consts.MAX_SCREEN_COL-1)*Consts.TILE_SIZE&&worldX<Consts.WORLD_SCREENS_WIDTH)
              {
                  screenX=(0)*Consts.TILE_SIZE;
                  worldX++;
              }
            }
            //change sprite for animation
            spriteCounter++;
            if(spriteCounter>12/(speed/defaultSpeed))
            {
                if(spriteNum==1)
                {
                    spriteNum=2;
                }else if(spriteNum==2)
                {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }
    }

    public void draw(Graphics2D g2)
    {
    
        BufferedImage image = null;
//TODO: add if passing edge of screen, change world by screen size
        switch(direction)
        {
            case "up":
                if(spriteNum==1)
                {
                image=up1;
                }
                if(spriteNum==2)
                {
                image=up2;
                }
                break;
            case "down":
                if(spriteNum==1)
                {
                image=down1;
                }
                if(spriteNum==2)
                {
                image=down2;
                }
                break;
            case "left":
                if(spriteNum==1)
                {
                image=left1;
                }
                if(spriteNum==2)
                {
                image=left2;
                }
                break;
            case "right":
                if(spriteNum==1)
                {
                image=right1;
                }
                if(spriteNum==2)
                {
                image=right2;
                }
                break;
        }
        
        g2.drawImage(image,(int)screenX,(int)screenY,Consts.TILE_SIZE,Consts.TILE_SIZE,null);

    }
}
