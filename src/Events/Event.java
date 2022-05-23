package src.Events;

import src.Main.Consts;
import java.awt.Rectangle;

public class Event {
    public String name;
    public int absX, absY;
    public int worldX, worldY;
    public int screenCol, screenRow;
    public int screenX, screenY;

    public Rectangle solidArea;

    public Event(int x, int y, int sizeX, int sizeY)
   {
      int shrink= 2*Consts.SCALE;
      absX=x*Consts.TILE_SIZE;
      absY=y*Consts.TILE_SIZE;
      screenCol=x%Consts.MAX_SCREEN_COL;
      screenRow=y%Consts.MAX_SCREEN_ROW;
      worldX=x/Consts.MAX_SCREEN_COL;
      worldY=y/Consts.MAX_SCREEN_ROW;
      screenX=screenCol*Consts.TILE_SIZE;
      screenY=screenRow*Consts.TILE_SIZE;
      
      solidArea = new Rectangle(screenX-(sizeX/2)*Consts.TILE_SIZE+shrink,screenY-(sizeY/2)*Consts.TILE_SIZE+shrink,(Consts.TILE_SIZE*sizeX)-2*shrink,(Consts.TILE_SIZE*sizeY)-2*shrink);
   }
   public Event(int x, int y)
   {
      int shrink= 2*Consts.SCALE;
      absX=x*Consts.TILE_SIZE;
      absY=y*Consts.TILE_SIZE;
      screenCol=x%Consts.MAX_SCREEN_COL;
      screenRow=y%Consts.MAX_SCREEN_ROW;
      worldX=x/Consts.MAX_SCREEN_COL;
      worldY=y/Consts.MAX_SCREEN_ROW;
      screenX=screenCol*Consts.TILE_SIZE;
      screenY=screenRow*Consts.TILE_SIZE;

      solidArea = new Rectangle(screenX+shrink,screenY+shrink,Consts.TILE_SIZE-2*shrink,Consts.TILE_SIZE-2*shrink);
   }
      
    
}
