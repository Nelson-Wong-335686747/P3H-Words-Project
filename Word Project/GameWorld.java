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
    //world variables
    public static final int WORLD_WIDTH = 800;
    public static final int WORLD_HEIGHT= 700;
    
    
    //data structures
    private ArrayList<String> wordList = new ArrayList<String>();
    public static Queue<Word> activeWords = new Queue<Word>();
    
    //objects
    private Word tempWord;
    
    private int timer = 0;
    private String activeString = "";
    private int n = 0;
    private int lives = 3;
    public GameWorld()
    {    
        super(WORLD_WIDTH,WORLD_HEIGHT,1); 
        
        
        try{
              Reader.readInto(wordList);
        } catch(Exception e) {
        }
        
       
        Word test = new Word(generateString(wordList));
        addObject(test, WORLD_WIDTH/2, WORLD_HEIGHT/2);
        activeWords.enqueue(test);
    }
    
    public void act()
    {
        for(Word word: activeWords){
            word.update();
        }
        
        if(timer==10){
            manageWords();
        }
        timer++;
    }
    
    public void manageWords()
    {
        timer = 0;
        n++;
        tempWord = new Word(generateString(wordList)); 
        addObject(tempWord,WORLD_WIDTH/2,200);
        activeWords.enqueue(tempWord);
        if(n==3){
            n=0;
        }
        
        
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
