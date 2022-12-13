import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title Screen.
 * 
 * @author Ryan Du
 * @version December 2022
 */
public class TitleScreen extends World
{
    // Labels
    Label titleLabel = new Label("Hungry Panda", 80);
    Label label = new Label("Press <space> to Start", 40);
    Label label2 = new Label("Use \u2190 \u2192 to Move", 40);
    
    // Sounds
    GreenfootSound introMusic = new GreenfootSound("intro.mp3");

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Label positioning
        addObject(titleLabel, getWidth()/2, 100);
        addObject(label, getWidth()/2,370);
        addObject(label2,getWidth()/2,320);
        prepare();
    }

    /**
     * The main world act loop
     */
    public void act()
    {
        introMusic.playLoop();
        
        // Start game if user presses the space bar
        if(Greenfoot.isKeyDown("space"))
        {
            MyWorld gameWorld = new MyWorld();
            introMusic.stop();
            Greenfoot.setWorld(gameWorld);
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Panda panda = new Panda();
        addObject(panda,352,240);
        Decoration decoration = new Decoration();
        addObject(decoration,238,233);
    }
}
