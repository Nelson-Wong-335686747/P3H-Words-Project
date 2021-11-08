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
        
        GreenfootImage bg = new GreenfootImage("gameOver.png");
        setBackground(bg);
        
        goBackButton = new Button("Go Back");
        addObject(goBackButton,100,400);
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(goBackButton))        
        {
            Greenfoot.setWorld(new WelcomeWorld());
        }
    }
}
