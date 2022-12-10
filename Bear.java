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
    GreenfootImage[] frameLeft = new GreenfootImage[3];
    GreenfootImage[] frameRight = new GreenfootImage[3];

    
    // Direction the elephant is facing
    String facing = "left";
        SimpleTimer animationTimer = new SimpleTimer();
    
    public Bear()
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
        
        // Initial elephant image
        setImage(frameLeft[0]);
    }
    
    int imageIndex = 0;
    public void animateBear()
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
            move(-2);
            facing = "left";
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            move(2);
            facing = "right";
        }
        
        // Remove banana if bear eats it
        eat();
        
        // Anime the bear
        animateBear();
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
