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
    
    
    private int timer = 0;
    private int n = 0;
    private int lives = 3;
    
    public String userString = "";
    
    //objects
    private Word tempWord;
    private Word userInput;
    
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
        checkUserInput();
        
        if(timer==100){
            manageWords();
            for(Word word: activeWords){
                word.updatePosition();
            }
        }
        timer++;
    }
    
    public void manageWords()
    {
        timer = 0;
        n++;
        tempWord = new Word(generateString(wordList)); 
        addObject(tempWord,WORLD_WIDTH/2,0);
        activeWords.enqueue(tempWord);
        if(n==3){
            n=0;
        }
         
        
    }
    
    public void checkUserInput(){
        //make it so that all the letters are added to a string/stack or something, then when 'enter' send it through
        //other than that there shouldnt need to be any other user input?
        //everything else should be related to the mouse
        if(Greenfoot.isKeyDown("enter"))
        {
            //compare with queue
        } else {
            String key = Greenfoot.getKey();
            if(key!= null){
                if(key.equals("a"))
                {
                    userString += "a";
                } else if(key.equals("b"))
                {
                    userString += "b";
                } else if(key.equals("c"))
                {
                    userString += "c";
                } else if(key.equals("d"))
                {
                    userString += "d";
                } else if(key.equals("e"))
                {
                    userString += "e";
                } else if(key.equals("f"))
                {
                    userString += "f";
                } else if(key.equals("g"))
                {
                    userString += "g";
                } else if(key.equals("h"))
                {
                    userString += "h";
                } else if(key.equals("i"))
                {
                    userString += "i";
                } else if(key.equals("j"))
                {
                    userString += "j";
                } else if(key.equals("k"))
                {
                    userString += "k";
                } else if(key.equals("l"))
                {
                    userString += "l";
                } else if(key.equals("m"))
                {
                    userString += "m";
                } else if(key.equals("n"))
                {
                    userString += "n";
                } else if(key.equals("o"))
                {
                    userString += "o";
                } else if(key.equals("p"))
                {
                    userString += "p";
                } else if(key.equals("q"))
                {
                    userString += "q";
                } else if(key.equals("r"))
                {
                    userString += "r";
                } else if(key.equals("s"))
                {
                    userString += "s";
                } else if(key.equals("t"))
                {
                    userString += "t";
                } else if(key.equals("u"))
                {
                    userString += "u";
                } else if(key.equals("v"))
                {
                    userString += "v";
                } else if(key.equals("w"))
                {
                    userString += "w";
                } else if(key.equals("x"))
                {
                    userString += "x";
                } else if(key.equals("y"))
                {
                    userString += "y";
                } else if(key.equals("z"))
                {
                    userString += "z";
                }
            }
        }
        if(userString.length()>0){
            addObject(new Word(userString), 50, WORLD_HEIGHT - 50);
        }
        
    }
    
    public String generateString(ArrayList<String> list){    
        //Gets random number, then finds that index on the list of words on the url in reader.
        return list.get(Greenfoot.getRandomNumber(10000));
    }
}
