package src.Events;

public class Hurt extends Event {

    public int damage;
    public Hurt(int x, int y, int damage, int sizeX, int sizeY) {
        super(x, y,sizeX,sizeY);
        this.damage=damage;
        name="hurt";
        //TODO Auto-generated constructor stub
    }

    public Hurt(int x, int y, int damage ) {
        super(x, y);
        this.damage=damage;
        name="hurt";
        //TODO Auto-generated constructor stub
    }

    
    
}
