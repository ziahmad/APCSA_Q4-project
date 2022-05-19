package src.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.HashMap;

import src.Main.GamePanel;
import src.Main.Consts;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    static private HashMap<Color, Integer> colorMap = new HashMap<>();
        

    public TileManager (GamePanel gp)
    {
        


        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[Consts.WORLD_COL][Consts.WORLD_ROW];
        getTileImage();
        loaMap("map1.png");
    }

    private int colorToNum(Color c)
    {
        if(colorMap.get(c)!=null)
            return colorMap.get(c);
        else
        return 0;
    }
    public void loaMap(String mapFileName)
    {
        try {
            //File input = new File(getClass().getResource("/resources/Maps/map1.png"));
            BufferedImage bi = ImageIO.read(getClass().getResource("/resources/Maps/"+mapFileName));
        
            for (int row = 0; row < Consts.WORLD_ROW; row++) {
                for (int col = 0; col < Consts.WORLD_COL; col++) {
                    Color c = new Color(bi.getRGB(col,row));
                    int num = colorToNum(c);

                    mapTileNum[row][col]= num;
                }
            }
            
        } catch (Exception e) {
            
        }

    }

    public void getTileImage()
    {
        try {
            //placeholder
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/placeholder.png"));
            colorMap.put(Color.BLACK, 0);
            //brick
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/wall.png"));
            colorMap.put(Color.RED, 1);
            tile[1].collision=true;
            //floor
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/floor.png"));
            colorMap.put(new Color(100,100,100), 2);
            tile[2].speedTile=true;
            tile[2].speedModifier=1.4;
            //grass
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/grass.png"));
            colorMap.put(new Color(0,255,0), 3);
            //mud
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/mud.png"));
            colorMap.put(new Color(66,44,8), 4);
            tile[4].speedTile=true;
            tile[4].speedModifier=.6;
            //sand
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/sand.png"));
            colorMap.put(new Color(255,255,0), 5);
            //water
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/water.png"));
            colorMap.put(new Color(0,0,255), 6);
            tile[6].collision=true;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        
        for (int col = 0; col < Consts.MAX_SCREEN_COL; col++) {
            for (int row = 0; row < Consts.MAX_SCREEN_ROW; row++) {
                int tileNum = mapTileNum[col+(gp.player.worldY*Consts.MAX_SCREEN_COL)][row+(gp.player.worldX*Consts.MAX_SCREEN_ROW)];
                g2.drawImage(tile[tileNum].image,row*Consts.TILE_SIZE,col*Consts.TILE_SIZE,Consts.TILE_SIZE,Consts.TILE_SIZE,null);
            }
        }
        
    }
}