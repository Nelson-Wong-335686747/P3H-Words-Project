import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{

    /**
     * Constructor for objects of class GameWorld.
     * 
     */
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        ArrayList<String> wordList = new ArrayList<String>();
        try{
                Reader.readInto(wordList);
        } catch(Exception e) {
        }
        
        //Something to start the game
        //While loop
            //maybe like a timer or something
            //something to spawn the words
            //something to detect user input
            //something to detect if the words/chars are matching
            //some kind of scoring system
            //lives
    }
    
    public String generateString(ArrayList<String> list){    
        //Gets random number, then finds that index on the list of words on the url in reader.
        return list.get(Greenfoot.getRandomNumber(10000));
    }
}
