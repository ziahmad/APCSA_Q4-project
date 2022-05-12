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
    Tile[] tile;
    int mapTileNum[][];
    static private HashMap<Color, Integer> colorMap = new HashMap<>(3);
        

    public TileManager (GamePanel gp)
    {
        colorMap.put(Color.BLACK, 0);
        colorMap.put(Color.RED, 1);
        colorMap.put(new Color(100,100,100), 2);


        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[Consts.MAX_SCREEN_ROW][Consts.MAX_SCREEN_COL];
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
        
            for (int row = 0; row < Consts.MAX_SCREEN_ROW; row++) {
                for (int col = 0; col < Consts.MAX_SCREEN_ROW; col++) {
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
            
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/placeholder.png"));
       
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/wall.png"));
       
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/resources/sprites/Tiles/floor.png"));
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        for (int col = 0; col < Consts.MAX_SCREEN_COL; col++) {
            for (int row = 0; row < Consts.MAX_SCREEN_ROW; row++) {
                int tileNum = mapTileNum[col][row];
                g2.drawImage(tile[tileNum].image,row*Consts.TILE_SIZE,col*Consts.TILE_SIZE,Consts.TILE_SIZE,Consts.TILE_SIZE,null);
            }
        }
        
    }
}
