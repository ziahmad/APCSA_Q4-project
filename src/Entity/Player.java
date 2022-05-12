package src.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import src.Main.Consts;
import src.Main.GamePanel;
import src.Main.KeyHandler;

//import java.awt.Color;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public double sprintScale = 2;
    public double defaultSpeed = 1*Consts.SCALE;

    public Player (GamePanel gPanel, KeyHandler kHandler)
    {
        gp=gPanel;
        keyH=kHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues ()
    {
        x= Consts.SCREEN_WIDTH/2;
        y = Consts.SCREEN_HEIGHT/2;
        speed =2*defaultSpeed;
        direction = "down";
    }

    public void getPlayerImage ()
    {
        //gets player sprites
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
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    public void update()
    {
        //if moving
        if(keyH.downPressed||keyH.rightPressed||keyH.leftPressed||keyH.upPressed)
        {
            if (keyH.shiftPressed)
            {
                speed=defaultSpeed*sprintScale;
            }else{
                speed=defaultSpeed; 
            }
            if (keyH.upPressed)
            {
              direction ="up";
              y-=speed;
            }
            else if (keyH.downPressed)
            {
              direction ="down";
              y+=speed;
            }
            else if (keyH.leftPressed)
            {
              direction ="left";
              x-=speed;
            }
            else if (keyH.rightPressed)
            {
                direction = "right";
              x+=speed;
            }
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
     //   g2.setColor(Color.GREEN);
      //g2.fillRect((int)x, (int)y, Consts.TILE_SIZE, Consts.TILE_SIZE);
    
        BufferedImage image = null;

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
        g2.drawImage(image,(int)x,(int)y,Consts.TILE_SIZE,Consts.TILE_SIZE,null);

    }
}
