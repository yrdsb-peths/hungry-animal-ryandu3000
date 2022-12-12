import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bear who's always hungry.
 * 
 * @author Ryan Du
 * @version November 2022
 */
public class Panda extends Actor
{
    GreenfootSound eatingSound = new GreenfootSound("eating.mp3");
    GreenfootImage[] frameLeft = new GreenfootImage[3];
    GreenfootImage[] frameRight = new GreenfootImage[3];

    
    // Direction the panda is facing
    String facing = "left";
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer cooldown = new SimpleTimer();
    Boolean slideActive = false;
    
    public Panda()
    {
        for(int i = 0; i < frameLeft.length; i++)
        {
            frameLeft[i] = new GreenfootImage("images/panda_frames/frame" + i + ".png");
            frameLeft[i].scale(100,100);
        }
        
        for(int i = 0; i < frameRight.length; i++)
        {
            frameRight[i] = new GreenfootImage("images/panda_frames/frame" + i + ".png");
            frameRight[i].mirrorHorizontally();
            frameRight[i].scale(100,100);
        }
        
        animationTimer.mark();
        
        // Initial panda image
        setImage(frameLeft[0]);
    }
    
    int imageIndex = 0;
    public void animatePanda()
    {
        if(animationTimer.millisElapsed() < 150)
        {
            return;
        }
        animationTimer.mark();
        
        if(facing.equals("left"))
        {
            setImage(frameLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % frameLeft.length;
        }
        else
        {
            setImage(frameRight[imageIndex]);
            imageIndex = (imageIndex + 1) % frameRight.length;
        }
    }
    public void act()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            move(-3);
            facing = "left";
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            move(3);
            facing = "right";
        }
        
        
        if(slideActive)
        {
            if(Greenfoot.isKeyDown("down"))
            {
                    if(facing.equals("right"))
                    {
                        move(20);
                        slideActive = false;
                    }
                    else
                    {
                        move(-20);
                        slideActive = false;
                    }
            }
        }
        else
        {
            cooldown.mark();
            if(cooldown.millisElapsed() >= 500)
            {
                slideActive = true;
            }
        }
        
        // Remove bamboo if panda eats it
        eat();
        
        // Animate the panda
        animatePanda();
    }
    
    public void eat()
    {
        if(isTouching(Bamboo.class))
        {
            removeTouching(Bamboo.class);
            MyWorld world = (MyWorld) getWorld();
            world.createBamboo();
            world.increaseScore();
            eatingSound.play();
        }
    }
}
