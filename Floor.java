import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Floor
 *
 * This class is for a game world to add an instance of the floor class 
 * on the ground (bottom corner of the screen)
 */
public class Floor extends Actor
{
    private GreenfootImage floor;
    
    public Floor() //Setting the class image to drawFloor().
    {
        floor = drawFloor();
        setImage(floor);
    }

    private GreenfootImage drawFloor() //Drawing the Floor
    {
        floor = new GreenfootImage(800,50); //The floor
        
        floor.setColor(Color.BLACK);
        floor.fillRect(0,0,800,50);
        
        return floor;
    }
}
