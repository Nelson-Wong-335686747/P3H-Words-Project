import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the welcome world
 * -The user has 2 options in the welcome world that they can choose by clicking with their mouse
 *  -InstuctionsButton; will send the user to the InstructionsWorld to see how to play the game.
 *  -startButton; starts the game immediately
 *  
 * Credit
 *  -Typing Sound --Youtube Title --> Keyboard Typing Sound Effect No Copyright For Free | Slow, Medium & Fast Typing --Link--> https://www.youtube.com/watch?v=Fixkpg_MW3M
 */
public class WelcomeWorld extends World
{
    private Button startButton;
    private Button instructionsButton;
    private GreenfootImage background;
    
    private Font comicFont = new Font ("Courier New", true, false, 60);
    
    private GreenfootSound backgroundMusic = new GreenfootSound ("Typing Sound.mp3");
    
    /**
     * Constructor for objects of class WelcomeWorld.
     * 
     */
    
    public WelcomeWorld()
    {    
       super(800, 600, 1); 
       //background = GameWorld.drawSpace(800, 600, 40);      
       background = new GreenfootImage("keyboard.png");  
       // Draw some text  
       background.setFont(comicFont);
       background.drawString ("Typing Game",200, 200);
       setBackground(background);
       
       startButton = new Button ("Play");
       addObject(startButton, 200, 500);

       instructionsButton = new Button ("Instructions");
       addObject(instructionsButton, 600, 500);

    }
    
    public void started () 
    {
        backgroundMusic.playLoop();
    }
    
    public void stopped () 
    {
        backgroundMusic.pause();
    }
    
    public void act () 
    {
        started();
        checkMouse();
    }

    private void checkMouse() 
    {
        if (Greenfoot.mouseClicked(instructionsButton))
        {
            stopped();
            Greenfoot.setWorld(new InstructionsWorld());
        }
        
        if(Greenfoot.mouseClicked(startButton))
        {
            stopped();
            Greenfoot.setWorld(new GameWorld());
        }
    }
    
}
