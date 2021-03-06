import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Display here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Word extends Actor
{
    private GreenfootImage image;
    
    
    private String text;
    
    private int x;
    
    private boolean removeMe;
    
    
    public Word(String string){ // add paramater for y value
        text = string;
        
        draw();
        setImage(image);
        
    }
    
    public int getLength() //Get's the length of the word
    {
        int lengthOfWord = text.length();
        
        return lengthOfWord;
    }
    
    public void act() 
    {
        draw();
        setImage(image);
    }    
    
    public void updateText(String str){
        text = str;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void move(int d)
    {
        setLocation(getX(),getY() + d); //random number 
    }
    
    public boolean checkPosition() //See if the words are out of bounds
    {
        //Checks if the words go out of bounds
        return getY() > GameWorld.WORLD_HEIGHT-35; 
    }
    
    public void updatePosition(int distance){
        move(distance);
    }
        
    private void draw(){
        //The length of the Word image size adjust's with the number of letters a Word has
        image = new GreenfootImage(text.length()*30 + 100,35); 
        Font font = new Font("Century Gothic", false, false, 30);
        
        image.setFont(font);
        
        image.setColor(Color.WHITE);    
        image.fill();
        
        image.setColor(Color.BLACK);
        image.setFont (font);
        drawCenteredText (image,text,27);
    }
    
    // below taken from Mr. Cohen's Button Method
    
    /**
     * <h3>Finally, draw centered text in Greenfoot!</h3>
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Use this instead of Greenfoot.drawString to center your text, or just call getStringWidth
     *    directly and draw it yourself if you prefer the control over the ease of use.</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param middleX   the x Coordinate that the text should be centered on
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText (GreenfootImage canvas, String text, int middleX, int bottomY){
        canvas.drawString (text, middleX - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }

    /**
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Similar to the method above, except it always centers the text on the whole image
     *    instead of a specified x position. UNTESTED!</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText (GreenfootImage canvas, String text, int bottomY){
        canvas.drawString (text, canvas.getWidth()/2 - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }

    /**
     * Get the Width of a String, if it was printed out using the drawString command in a particular
     * Font.
     * 
     * This is not a cheap method, and should not be called from an act method. It is appropriate
     * to call this in the constructor.
     * 
     * In advanced cases, you may want to cache the results during a loading method. You could also
     * call it manually while coding, not the results, and use literal values to avoid having this
     * code called at all.
     * 
     * @param font the GreenFoot.Font which is being used to draw text
     * @param text the actual text to be drawn
     * 
     * @since June 2021
     */
    public static int getStringWidth (Font font, String text){

        // how far past the last sighted text to keep looking
        final int END_MARGIN = 100; 

        // largest font size
        final int MAX_FONT_SIZE = 300;

        // you can make this higher if your world is bigger
        final int MAX_WIDTH = 1000; 

        
        int fontSize = font.getSize();
        GreenfootImage temp = new GreenfootImage (MAX_WIDTH, MAX_FONT_SIZE);
        temp.setFont(font);
        temp.drawString (text, 0, fontSize);

        int checkX = 0;
        int lastFound = 0;

        //int testValue = 1000;
        boolean running = true;

        int marginOfError = 3; // how many pixels can be skipped scanning vertically for pixels?
        if (fontSize < 18){
            marginOfError = 2;
        }
        while (running){ 
            // new row
            boolean found = false; 
            for (int i = 0; i < fontSize && !found; i+=marginOfError){
                if (temp.getAwtImage().getRGB(checkX, i) != 0){
                    found = true;
                    lastFound = checkX;
                }
            }
            checkX++;
            if (checkX - lastFound > END_MARGIN){ // if I have run for a certain amount and not found any new               
                running = false;                  // pixels, I'm done.
            }
        }
        return lastFound;
    }
}