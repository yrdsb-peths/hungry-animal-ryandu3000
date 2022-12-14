import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Panda who's always hungry, the hero of the game.
 * 
 * @author Ryan Du
 * @version November 2022
 */
public class Panda extends Actor
{
    // Sounds
    GreenfootSound eatingSound = new GreenfootSound("eating.mp3");
    
    //Variables for the pandas animation
    GreenfootImage[] frameLeft = new GreenfootImage[3];
    GreenfootImage[] frameRight = new GreenfootImage[3];
    SimpleTimer animationTimer = new SimpleTimer();
    int imageIndex = 0;
    
    // Direction the panda is facing
    String facing = "left";
    
    // Variables for the slide
    int timerTime = 180;
    Boolean slideActive = true;
    
    // Panda animation
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
    
    // Panda animation pt 2
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
    
    // Movement
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
                        setLocation(getX()+200,getY());
                        slideActive = false;
                        timerTime = 3* 60;
                    }
                    else
                    {
                        setLocation(getX()-200,getY());
                        slideActive = false;
                        timerTime = 3* 60;
                    }
            }
        }
        else
        {
            if(timerTime == 0)
            {
                slideActive = true;
            }
        }
        timerTime --;
        
        // Remove bamboo if panda eats it
        eat();
        
        // Animate the panda
        animatePanda();
    }
    
    // Increases the score and spawns new bamboo
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
