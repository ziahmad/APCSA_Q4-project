package src.Events;

import src.Main.Consts;
import java.awt.Rectangle;

public class Event {
    public String name;
    public int absX, absY;
    public int worldX, worldY;
    public int screenX, screenY;

    public Rectangle solidArea;

    public Event(int x, int y, int sizeX, int sizeY)
   {
      int shirnk= 2*Consts.SCALE;
      absX=x*Consts.TILE_SIZE;
      absY=y*Consts.TILE_SIZE;
      screenX=x%Consts.MAX_SCREEN_COL;
      screenY=y%Consts.MAX_SCREEN_ROW;
      worldX=x/Consts.MAX_SCREEN_COL;
      worldY=y/Consts.MAX_SCREEN_ROW;
      
      solidArea = new Rectangle((screenX-sizeX/2)*Consts.TILE_SIZE+shirnk,(screenY-sizeY/2)*Consts.TILE_SIZE+shirnk,(Consts.TILE_SIZE*sizeX)-2*shirnk,(Consts.TILE_SIZE*sizeY)-2*shirnk);
   }
   public Event(int x, int y)
   {
      int shirnk= 2*Consts.SCALE;
      absX=x*Consts.TILE_SIZE;
      absY=y*Consts.TILE_SIZE;
      screenX=x%Consts.MAX_SCREEN_COL;
      screenY=y%Consts.MAX_SCREEN_ROW;
      worldX=x/Consts.MAX_SCREEN_COL;
      worldY=y/Consts.MAX_SCREEN_ROW;

      solidArea = new Rectangle(screenX*Consts.TILE_SIZE+shirnk,screenY*Consts.TILE_SIZE+shirnk,Consts.TILE_SIZE-2*shirnk,Consts.TILE_SIZE-2*shirnk);
   }
      
    
}
