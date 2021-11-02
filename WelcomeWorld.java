import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WelcomeWorld here.
 * 
 * @author Sean Xue
 * @version (a version number or a date)
 * 
 *
 */
public class WelcomeWorld extends World
{
    private Button startButton;
    private Button instructionsButton;
    private GreenfootImage background;
    private GreenfootSound backgroundMusic;
    private Font comicFont = new Font ("Courier New", true, false, 60);
    
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

       backgroundMusic = new GreenfootSound ("Typing Sound.mp3");
       backgroundMusic.playLoop();
        
    }
    
    public void started () 
    {
        backgroundMusic.play();
    }
    
    public void stopped () 
    {
        backgroundMusic.pause();
    }
    
    public void act () 
    {
        checkMouse();
    }

    private void checkMouse() 
    {
        if (Greenfoot.mouseClicked(instructionsButton))
        {
            stopped();
            Greenfoot.setWorld(new InstructionsWorld());
        }
    }
    
}
