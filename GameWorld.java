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
    private Timer timer;
    private ArrayList<String> wordList = new ArrayList<String>();
    private Queue<Word> activeWords = new Queue<Word>();
    private String activeString = "";
    
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        try{
                Reader.readInto(wordList);
        } catch(Exception e) {
        }
        
        timer = new Timer();
        
    }
    
    public void act(){
        //while(lives>0){
            if(timer.getCycle() == 100){ //random number for now
                timer.reset();
                //add a parameter for the x value of the words
                //and use a counter up to three or smthg for the three? 
                //collumns of words
                activeWords.enqueue(new Word(generateString(wordList)));
            }
            /**
             * for(Word word : activeWords){
             *    word.move() -- go down a certain amount
             *    if word.get y below a certain amount lose a life
             * }
             */
            //checkUserInput();
                //something to detect if the words/chars are matching
                    // compare user input string to first q
                        //if match, delete,dequeue, and give score
                        //if not, we have two options:
                            //make the word do more 'damage' if it passes
                            //straight up make the player take damage
            //some kind of scoring system
            //lives
            
        
    }
    
    public void checkUserInput(){
        //make it so that all the letters are added to a string/stack or something, then when 'enter' send it through
        //other than that there shouldnt need to be any other user input?
        //everything else should be related to the mouse
    }
    
    public String generateString(ArrayList<String> list){    
        //Gets random number, then finds that index on the list of words on the url in reader.
        return list.get(Greenfoot.getRandomNumber(10000));
    }
}