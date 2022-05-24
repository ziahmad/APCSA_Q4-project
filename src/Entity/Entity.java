package src.Entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;

import src.Main.Consts;
import src.Main.GamePanel;

import java.awt.Rectangle;

//super class or all entities
public class Entity {
    public GamePanel gp;
    public String name;
    // wichever screen the entity is on
    public int worldX, worldY;

    // wichever eneity is on a screen
    public double screenX;
    public double screenY;
    // position on map as a whole
    public double absX, absY;
    public double speed;

    // sprite animation
    public BufferedImage up0, down0, right0, left0, up1, down1, right1, left1, up2, down2, right2, left2;
    public String direction;
    public boolean isMoving = false;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // for collision checks
    public Rectangle solidArea;
    public boolean collisionOn = false;

    // for NPCs
    public int actionLockCounter = 0;
    public ArrayList<String> dialogue = new ArrayList<>(0);
    int dialogueIndex = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public Entity(GamePanel gp, int x, int y) {
        this.gp = gp;
        absX = x * Consts.TILE_SIZE;
        absY = y * Consts.TILE_SIZE;
        screenX = x % Consts.MAX_SCREEN_COL;
        screenY = y % Consts.MAX_SCREEN_ROW;
        worldX = x / Consts.MAX_SCREEN_COL;
        worldY = y / Consts.MAX_SCREEN_ROW;

        solidArea = new Rectangle((int) (screenX * Consts.TILE_SIZE) + 3 * Consts.SCALE,
                (int) (screenY * Consts.TILE_SIZE) + 3 * Consts.SCALE, Consts.TILE_SIZE - 5 * Consts.SCALE,
                Consts.TILE_SIZE - 8);
    }

    public void setAction() {

    }

    public void speak() {
        gp.ui.currentDialogue = dialogue.get(dialogueIndex);
        dialogueIndex++;
        if (dialogueIndex >= dialogue.size()) {
            dialogueIndex = 0;
        }
    }

    public void update() {
        setAction();

        absX = screenX + (worldX * Consts.SCREEN_WIDTH);
        absY = screenY + (worldY * Consts.SCREEN_HEIGHT);
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npcs);
        gp.cChecker.checkPlayer(this);

        if (collisionOn && gp.player.worldX == this.worldX && gp.player.worldY == this.worldY) {
            switch (direction) {
                case "up":
                    direction = "down";
                    break;
                case "down":
                    direction = "up";
                    break;
                case "left":
                    direction = "right";
                    break;
                case "right":
                    direction = "left";
                    break;
            }
        }
        if (collisionOn == false && gp.player.worldX == this.worldX && gp.player.worldY == this.worldY) {
            switch (direction) {
                case "up":
                    screenY -= speed;

                    if (screenY <= 0 && worldY > 0) {
                        direction = "down";
                    }
                    break;
                case "down":
                    screenY += speed;
                    if (screenY >= (Consts.MAX_SCREEN_ROW - 1) && worldY < Consts.WORLD_SCREENS_HEIGHT) {
                        direction = "up";
                    }
                    break;
                case "left":
                    screenX -= speed;
                    if (screenX <= 0 && worldX > 0) {
                        direction = "right";
                    }
                    break;
                case "right":
                    screenX += speed;
                    if (screenX >= (Consts.MAX_SCREEN_COL - 1) && worldX < Consts.WORLD_SCREENS_WIDTH) {
                        direction = "left";
                    }
                    break;
            }
            this.solidArea.x = (int) (screenX * Consts.TILE_SIZE) + 2 * Consts.SCALE;
            this.solidArea.y = (int) (screenY * Consts.TILE_SIZE) + 3 * Consts.SCALE;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum++;
            spriteNum %= 2;
            /*
             * if(spriteNum==1)
             * {
             * spriteNum=2;
             * }else if(spriteNum==2)
             * {
             * spriteNum=1;
             * }
             */
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 0) {
                    image = up1;
                }
                if (spriteNum == 1) {
                    image = up2;
                }

                break;
            case "down":
                if (spriteNum == 0) {
                    image = down1;
                }
                if (spriteNum == 1) {
                    image = down2;
                }

                break;
            case "left":
                if (spriteNum == 0) {
                    image = left1;
                }
                if (spriteNum == 1) {
                    image = left2;
                }

                break;
            case "right":
                if (spriteNum == 0) {
                    image = right1;
                }
                if (spriteNum == 1) {
                    image = right2;
                }

                break;
        }

        g2.drawImage(image, (int) (screenX * Consts.TILE_SIZE), (int) (screenY * Consts.TILE_SIZE), Consts.TILE_SIZE,
                Consts.TILE_SIZE, null);
        g2.setColor(Color.BLACK);
        g2.draw(solidArea);

    }
}
