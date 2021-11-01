import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
       
        
        
        
    }
    
    public String generateString(){    
        //Gets random number, then finds that index on the list of words on the url in reader.
        
        ArrayList<String> myList = new ArrayList<String>();
        try{
                Reader.readInto(myList);
        } catch(Exception e) {
        }
        //the loop above should be in the constructor of whatever world the game is on
        int n = Greenfoot.getRandomNumber(10000);
        return myList.get(n);
    }
}
