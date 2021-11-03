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
    
    private Word tempWord;
    private String activeString = "";
    private int x = 0;
    private int lives = 3;
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        try{
                Reader.readInto(wordList);
        } catch(Exception e) {
        }
        
        timer = new Timer();
        Word test = new Word(generateString(wordList),300);
        addObject(test, 300, 200);
        activeWords.enqueue(test);
    }
    
    public void act()
    {
        while(timer.getCycle()<10000){
            if(timer.getCycle()%1000==0){
                manageWords();
            }
        }
    }
    
    public void manageWords()
    {
        timer.reset();
        x++;
        tempWord = new Word(generateString(wordList),150*x);
        
        addObject(tempWord,150*x,200);
        if(x==3){
            x=0;
        }
    }
    
    public void checkUserInput(){
        //make it so that all the letters are added to a string/stack or something, then when 'enter' send it through
        //other than that there shouldnt need to be any other user input?
        //everything else should be related to the mouse
    }
    
    public String generateString(ArrayList<String> list){    
        //Gets random number, then finds that index on the list of words on the url in reader.
        return list.get(Greenfoot.getRandomNumber(9000));
    }
}
