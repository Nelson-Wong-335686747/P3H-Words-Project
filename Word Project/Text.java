import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is used pile texts on top of each other
 */
public class Text extends Actor
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
        if(typed)
        {
            typed = true; //Sees if the text was typed
        }
        else
        {
            typed = false; //Sees if the text was not typed
        }
    }
    
    public Text(String text)
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
        image.setColor(Color.YELLOW);
        image.fill();
        image.setColor(Color.BLACK);
        image.setFont (buttonFont);
        image.drawString (myText, xHorizontal, yVertical);
    }
}
