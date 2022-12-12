import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Food for the bear.
 * 
 * @author Ryan Du
 * @version November 2022
 */
public class Banana extends Actor
{
    int speed = 1;
    
    /**
     * Act - do whatever the Banana wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        int x = getX();
        int y = getY() + speed;
        setLocation(x,y);
        
        // Remove banana and draw game over when banana gets to the bottom
        MyWorld world = (MyWorld) getWorld();
        if(getY() >= 400)
        {       
            world.gameOver();
            world.removeObject(this);
        }
    }
    
    public void setSpeed(int spd)
    {
        speed = spd;
    }
}
