import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Dangerous for pandas
 * 
 * @author Ryan Du
 * @version December 2022
 */
public class Bomb extends Actor
{
    // Sounds
    GreenfootSound explode = new GreenfootSound("Explode.mp3");
    
    public void act()
    {
        // Spawns a bomb at a random location at the top and drops it 
        int x = getX();
        int y = getY() + 1;
        setLocation(x,y);
        
        MyWorld world = (MyWorld) getWorld();
        // Ends the game when the bomb touches the panda
        if(isTouching(Panda.class))
        {
            explode.play();
            removeTouching(Panda.class);
            world.gameOver();
            world.removeObject(this);
        }
    }
}
