package src.Main;
/**
 * Constants for window size and pixel scale
 * 
 * 
 */
public final class Consts {
    private static final int TILE_SIZE_START = 16;
    public static final int SCALE = 2;
    //pixels per tile
    public static final int TILE_SIZE = SCALE*TILE_SIZE_START;
    
    //rows/columns per screen
    public static final int MAX_SCREEN_COL =16;
    public static final int MAX_SCREEN_ROW =16;
 
    //screen Size in pixels
    public static final int SCREEN_WIDTH = MAX_SCREEN_COL*TILE_SIZE;
    public static final int SCREEN_HEIGHT = MAX_SCREEN_ROW*TILE_SIZE;
    
    // number of screens
    public static final int WORLD_SCREENS_WIDTH = 5;
    public static final int WORLD_SCREENS_HEIGHT = 5;

    //number of tiles total
    public static final int WORLD_COL=MAX_SCREEN_COL*WORLD_SCREENS_WIDTH;
    public static final int WORLD_ROW=MAX_SCREEN_ROW*WORLD_SCREENS_HEIGHT;
    
    public static final int WORLD_WIDTH = WORLD_COL*TILE_SIZE;
    public static final int WORLD_HEIGHT = WORLD_ROW*TILE_SIZE;
}