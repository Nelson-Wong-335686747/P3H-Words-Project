import greenfoot.*;

/**
 * When the user loses all 3 lives, then they will be directed into the GamOverWorld.
 */
public class GameOverWorld extends World  
{
    private Button goBackButton;
    
    private Font comicFont = new Font ("Courier New", true, false, 60);
    
    public GameOverWorld()
    {
        super(800, 600, 1); 
        
        //Set the image
        //GreenfootImage bg = new GreenfootImage("gameOver.png");
        //setBackground(bg);
        
        //Add a button
        goBackButton = new Button("Go Back");
        addObject(goBackButton,100,400);
    }
    
    public void act()
    {
        //If the goBack button is pressed, go back to WelcomeWorld
        if(Greenfoot.mouseClicked(goBackButton))        
        {
            Greenfoot.setWorld(new WelcomeWorld());
        }
    }
}
