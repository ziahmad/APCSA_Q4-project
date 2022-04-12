package Entities;

import Tiles.Tile;

public class Entity {
    
    private double Xpos;
    private double Ypos;
    private int Xsize;
    private int Ysize;

    private int health;
    private int speed;
    private int range=1;

//contructors
    public Entity (int x, int y)
    {
        Xpos=x;
        Ypos=y;
        Xsize=1;
        Ysize=1;
        health=20;
        speed=1;
    }
    public Entity (int x, int y, int h)
    {
        Xpos=x;
        Ypos=y;
        health=h;
        Xsize=1;
        Ysize=1;
    }

    public Entity (int x, int y, int h, int sx, int sy)
    {
        Xpos=x;
        Ypos=y;
        health=h;
        Xsize=sx;
        Ysize=sy;
    }
//behaviors
    public void move (Tile t)
    {
        
    }





//getters
    public double getXpos ()
    {
        return this.Xpos;
    }
    public double getYpos ()
    {
        return this.Ypos;
    }
    public int getXsize ()
    {
        return this.Xsize;
    }
    public int getYsize ()
    {
        return this.Ysize;
    }
    public int getHealth ()
    {
        return this.health;
    }
    public int getSpeed ()
    {
        return this.speed;
    }
    public int getRange ()
    {
        return this.range;
    }
//setters
    public void setXpos (double x)
    {
        this.Xpos=x;
    }
    public void getYpos (double y)
    {
        this.Ypos=y;
    }
    public void setXsize (int sx)
    {
        this.Xsize=sx;
    }
    public void setYsize (int sy)
    {
        this.Ysize=sy;
    }
    public void setHealth (int h)
    {
        this.health=h;
    }
    public void getSpeed (int sp)
    {
        this.speed=sp;
    }
    public void getRange (int range)
    {
        this.range=range;
    }



}
