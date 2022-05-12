package Tiles;

public class Tile  {
    double Xpos;
    double Ypos;
    
    public Tile (int x, int y)
    {
        Xpos=x;
        Ypos=y;
    }
    public double getX()
    {
        return Xpos;
    }
    public double getY()
    {
        return Ypos;
    }
}
