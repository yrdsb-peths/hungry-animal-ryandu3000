import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world the hungry bear lives in.
 * 
 * @author Ryan Du
 * @version November 2022
 */
public class MyWorld extends World
{
    public int score = 0;
    Label scoreLabel;
    Label slideLabel = new Label("Slide \u2193", 40);
    int level = 1;
    GreenfootSound backgroundMusic = new GreenfootSound("background.mp3");
    GreenfootSound gameOverMusic = new GreenfootSound("gameover.mp3");

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        // Create the bear object
        Panda panda = new Panda();
        addObject(panda, 300, 300);
        
        // Create a label
        scoreLabel = new Label(0, 80);
        addObject(scoreLabel, 50, 50);
        
        addObject(slideLabel, 530,30);
        
        createBamboo();
        backgroundMusic.playLoop();
    }
    
    /**
     * End the game and draw 'GameOver'
     */
    public void gameOver()
    {
        Label gameOverLabel = new Label("Game Over", 100);
        backgroundMusic.stop();
        addObject(gameOverLabel, 300, 200);
        gameOverMusic.play();
    }
    
    /**
     * Increase score
     */
    public void increaseScore()
    {
        score++;
        scoreLabel.setValue(score);
        if(score % 5 == 0 && score <= 10)
        {
            level +=1;
        }
        if(score > 10)
        {
            int c = Greenfoot.getRandomNumber(3);
            if(c == 1)
            {
                createBomb();
            }
        }
    }
    /**
     * Create a new banana at random location at top the screen
     */
    public void createBamboo()
    {
        Bamboo bamboo = new Bamboo();
        bamboo.setSpeed(level);
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(bamboo, x, y);
    }
    
    public void createBomb()
    {
        Bomb bomb = new Bomb();
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(bomb, x, y);
    }
}
