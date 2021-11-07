import greenfoot.*;
/**
 * 
 */
public class GameOverWorld extends World  
{
    private Button goBackButton;
    
    private Font comicFont = new Font ("Courier New", true, false, 60);
    
    private int actsLeft;
    
    public GameOverWorld()
    {
        super(800, 600, 1); 
        
        //setBackground(new GreenfootImage("GameOver");
        
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
