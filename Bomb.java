import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author Ryan Du
 * @version December 2022
 */
public class Bomb extends Actor
{
    GreenfootSound explode = new GreenfootSound("Explode.mp3");
    
    public void act()
    {
        int x = getX();
        int y = getY() + 1;
        setLocation(x,y);
        
        MyWorld world = (MyWorld) getWorld();
        if(isTouching(Panda.class))
        {
            explode.play();
            removeTouching(Panda.class);
            world.gameOver();
            world.removeObject(this);
        }
    }
}
