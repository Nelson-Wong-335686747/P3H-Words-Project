import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionsWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionsWorld extends World
{
    private GreenfootImage background;
    //private Player player;
    private Button returnButton;
    private Button beginButton;
    private Font comicFont = new Font ("Courier New", true, false, 60);
    
    /**
     * Constructor for objects of class InstructionWorld.
     * 
     */
    
    public InstructionsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        //Set instruction world 
        background = new GreenfootImage("keyboard.png");   
        setBackground(background);
        background.setFont(comicFont);
        //Draw instruction title
        background.drawString ("Instructions",170, 70);
        
        //Add player
        //player = new Player();
        //addObject (player, 400, 360);
        
        //Draw two buttons
        returnButton = new Button ("Back");
        beginButton = new Button ("Begin");
        
        //Set location for buttons
        addObject(returnButton, 100, 570);
        addObject(beginButton, 700, 570);
    }

    public void act () 
    {
        //checkKeys();
        checkMouse();
    }
    
    private void checkMouse() 
    {
        if (Greenfoot.mouseClicked(returnButton))
        {
            Greenfoot.setWorld(new WelcomeWorld());
        }
        //else if (Greenfoot.mouseClicked(beginButton))
        //{
            //Greenfoot.setWorld(new Level1());
        //}
    }
    
    //private void checkKeys()
    //{
        //if (Greenfoot.isKeyDown("enter"))
        //{
           //if (returnButton.touchingPlayer())
           //{
                //Greenfoot.setWorld(new WelcomeWorld());
           //}
           //else if (beginButton.touchingPlayer())
           //{
               //Greenfoot.setWorld(new Level1());
           //}  
        ///}
    //
}