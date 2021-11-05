import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * GameWorld 
 * -Where the game begins
 * 
 * Credit
 * -HarryStylesWatermelonSugar --Youtube Title--> Harry Styles - Watermelon Sugar (Official Video) --Link--> https://www.youtube.com/watch?v=E07s5ZYygMg
 */
public class GameWorld extends World
{
    private GreenfootSound GameMusic = new GreenfootSound("HarryStylesWatermelonSugar.mp3");
    
    //world variables
    public static final int WORLD_WIDTH = 800;
    public static final int WORLD_HEIGHT= 700;
    
    //data structures
    private ArrayList<String> wordList = new ArrayList<String>();
    public static Queue<Word> activeWords = new Queue<Word>();
    
    private int timer = 0;
    private int n = 0;
    
    private int wordsLeft;
    private int wordsTyped;
    private int lives = 3;
    private int score;
    
    public String userString = "";
    
    //objects
    private Word tempWord;
    private Word userInput;
    
    private ScoreBar scoreBar;
    
    public GameWorld()
    {    
        super(WORLD_WIDTH,WORLD_HEIGHT,1); 
        
        try
        {
            Reader.readInto(wordList);
        } 
        catch(Exception e) 
        {
        }
        
        scoreBar = new ScoreBar(800);
        addObject(scoreBar, 400, 15);
        
        Word test = new Word(generateString(wordList));
        addObject(test, WORLD_WIDTH/2, WORLD_HEIGHT/2);
        activeWords.enqueue(test);
        
        userInput = new Word(userString);
        addObject(userInput,WORLD_WIDTH/3,WORLD_HEIGHT-50);
    }
    
    public void started () 
    {
        GameMusic.playLoop();
    }
    
    public void stopped () 
    {
        GameMusic.pause();
    }
    
    public void act(){
        GameMusic.playLoop();
        
        scoreBar.update(wordsLeft, wordsTyped, lives, score);
        
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
            //compare userString with text of first in queue 
            if(activeWords.getSize() > 0)
            {
                if(activeWords.getFirst().getText().equals(userString))
                {
                    removeObject(activeWords.getFirst());
                    Word wordSize = activeWords.dequeue();
                    
                    wordsTyped++;
                    
                    score = score + wordSize.getLength() * 50;

                }
            }
            userString = "";
            userInput.updateText(userString);
        } else {
            String key = Greenfoot.getKey();
            if(key!= null){
                if(key.equals("backspace")  && userString !=null)
                {
                    
                    userString = userString.substring(0, userString.length()-1);
                    userInput.updateText(userString);
                    
                }
                else if(key.equals("a"))
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
            userInput.updateText(userString);
        }
        
    }
    
    public String generateString(ArrayList<String> list){    
        //Gets random number, then finds that index on the list of words on the url in reader.
        return list.get(Greenfoot.getRandomNumber(10000));
    }
}