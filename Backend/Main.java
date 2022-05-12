package Backend;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
/**
 * Main
 * this is where the main meathod is and where the game has a constuctor
 */
public class Main extends Canvas implements Runnable {

    //16 tiels wide with each tile being 48 pixles
    public static int tileSize =48;
    public static final int WIDTH = 16*tileSize, HEIGHT = 16*tileSize;
    
    //make game thread
    private Thread thread;
    private boolean running = false;


    //contructor to creat game window
    public Main()
    {
        new Window(WIDTH, HEIGHT, "Quarter 4 Project", this);
    }


    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop ()
    {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    //game loop from https://www.youtube.com/watch?v=1gir2R7G9ws&t=631s
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns =1000000000/amountOfTicks;
        double delta =0;
        long timer = System.currentTimeMillis();
        int frames =0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime= now;
            while (delta>=1)
            {
                tick();
                delta--;
            }
            if (running)
            {
                render();
            }
            frames++;

            if (System.currentTimeMillis()-timer>1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
        
    }

    private void tick() 
    {
        
    }
    
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.dispose();
        bs.show();
    }


    public static void main(String[] args) {
        new Main();
    }
}