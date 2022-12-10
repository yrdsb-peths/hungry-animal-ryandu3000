import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bear who's always hungry.
 * 
 * @author Ryan Du
 * @version November 2022
 */
public class Bear extends Actor
{
    GreenfootSound eatingSound = new GreenfootSound("eating.mp3");
    public void act()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            move(-1);
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            move(1);
        }
        
        // Remove banana if bear eats it
        eat();
    }
    
    public void eat()
    {
        if(isTouching(Banana.class))
        {
            removeTouching(Banana.class);
            MyWorld world = (MyWorld) getWorld();
            world.createBanana();
            world.increaseScore();
            eatingSound.play();
        }
    }
}
