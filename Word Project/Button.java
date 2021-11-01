import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is to make a button that starts the game, switch worlds, etc, 
 * when it's clicked
 */
public class Button extends Actor
{
    public static final double FONT_RATIO = 0.5;
    
    private boolean typed;
    
    private String myText;
    
    private int xHorizontal;
    private int yVertical;
    
    private GreenfootImage image;
    
    private Font buttonFont = new Font("Courier New",  true,  false,  20);
    
    public void act()
    {
        // Add your action code here.
    }
    
    public Button(String text)
    {
        image = new GreenfootImage (100, 40); //Setting the size of the text image
        myText = text;
        
        int wordLength = myText.length(); //Amount of letters
        
        //Centering the text
        int wordWidth = (int)(wordLength * buttonFont.getSize() * FONT_RATIO);
        
        xHorizontal = (image.getWidth() - wordWidth)/2;
        yVertical = image.getHeight() - (image.getHeight() - buttonFont.getSize())/2;
        
        draw();
        setImage (image);
    }
    
    public void draw()
    {
        image.setColor(Color.WHITE);
        image.fill();
        image.setColor(Color.BLACK);
        image.setFont (buttonFont);
        image.drawString (myText, xHorizontal, yVertical);
    }
}
