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
    //private Player player ;
    private Button startButton;
    private Button instructionsButton;
    private GreenfootImage background;
    //private GreenfootSound backgroundMusic;
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

       //player = new Player();
       //addObject (player, 400, 360);
       
       //backgroundMusic = new GreenfootSound ("nightwinds.mp3");
       //backgroundMusic.playLoop();
        
    }
    
    //public void started () 
    //{
        //backgroundMusic.play();
    //}
    
    //public void stopped () 
    //{
        //backgroundMusic.pause();
        
    //}
    
    public void act () 
    {
        //checkKeys();
        checkMouse();
    }

    private void checkMouse() 
    {
        //if (Greenfoot.mouseClicked(startButton))
        //{
            
        //}
        if (Greenfoot.mouseClicked(instructionsButton))
        {
            Greenfoot.setWorld(new InstructionsWorld());
        }
    }
    
    //private void checkKeys()
    //{
        //if (Greenfoot.isKeyDown("enter"))
        //{
            //if (startButton.touchingPlayer())
            //{
                //Greenfoot.setWorld(new Level1());
            //} 
            //if (instructionsButton.touchingPlayer())
            //
                // set World to InstructionsWorld
                //Greenfoot.setWorld(new InstructionsWorld());
            //}
        //}
        
       
    //}
}