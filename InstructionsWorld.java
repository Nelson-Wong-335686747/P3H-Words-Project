import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * InstructionsWorld
 * -Gives a breif explanation of how the game should be played
 * -beginButton starts the game (GameWorld)
 * -returnButton goes back to the main menu (WelcomeWorld)
 * 
 * Credits 
 * -CP - Pizza Parlor --Youtube Tite --> Club Penguin Music - Pizza Parlor --Link--> https://www.youtube.com/watch?v=srNKLDAt3Pk
 */
public class InstructionsWorld extends World
{
    private GreenfootImage background;
    private Button returnButton;
    private Button beginButton;
    private Font comicFont = new Font ("Courier New", true, false, 18);
    
    private GreenfootSound InstructionsMusic = new GreenfootSound("CP - Pizza Parlor.mp3");
    
    /**
     * Constructor for objects of class InstructionWorld.
     * 
     */
    
    public InstructionsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        //Set instruction world 
        background = new GreenfootImage("Instructions.png");   
        setBackground(background);
        background.setFont(comicFont);
        //Draw instruction title
        background.drawString ("Instructions",635, 70);
        
        //Draw two buttons
        returnButton = new Button ("Back");
        beginButton = new Button ("Begin Game");
        
        //Set location for buttons
        addObject(returnButton, 100, 570);
        addObject(beginButton, 675, 570);
    }

    public void started () 
    {
        InstructionsMusic.playLoop();
    }
    
    public void stopped () 
    {
        InstructionsMusic.pause();
    }
    
    public void act () 
    {
        started();
        checkMouse();
    }
    
    private void checkMouse() 
    {
        if (Greenfoot.mouseClicked(returnButton))
        {
            stopped();
            Greenfoot.setWorld(new WelcomeWorld());
        }
        
        if(Greenfoot.mouseClicked(beginButton))
        {
            stopped();
            Greenfoot.setWorld(new GameWorld());
        }
    }
    
}