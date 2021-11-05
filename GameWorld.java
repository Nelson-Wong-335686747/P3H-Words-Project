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
    public static int GAME_LIVES = 3;
    
    //data structures
    private ArrayList<String> wordList = new ArrayList<String>();
    public static Queue<Word> activeWords = new Queue<Word>();
    public static Stack<Character> userInput = new Stack<Character>();
    
    private int timer = 0;
    private int n = 0;
    
    private int wordsLeft;
    private int wordsTyped;
    private int score;
    
    public String userString = "";
    
    //objects
    private Word tempWord;
    private Word uInputDsplay;
    
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
        
        uInputDsplay = new Word(userString);
        addObject(uInputDsplay,WORLD_WIDTH/3,WORLD_HEIGHT-50);
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
        
        scoreBar.update(wordsLeft, wordsTyped, GAME_LIVES, score);
        
        if(GAME_LIVES <= 0)
        {
            stopped();
            //Greenfoot.setWorld(new GameOverWorld());
        }
        
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
            userString = userInput.popAll();
            uInputDsplay.updateText(userString);

        } else {
            String key = Greenfoot.getKey();
            if(key!= null){
                if(key.equals("backspace")  && userString !=null)
                {
                    userInput.pop();
                }
                else if(key.equals("a"))
                {
                    userInput.push('a');
                } else if(key.equals("b"))
                {
                    userInput.push('b');
                } else if(key.equals("c"))
                {
                    userInput.push('c');
                } else if(key.equals("d"))
                {
                    userInput.push('d');
                } else if(key.equals("e"))
                {
                    userInput.push('e');
                } else if(key.equals("f"))
                {
                    userInput.push('f');
                } else if(key.equals("g"))
                {
                    userInput.push('g');
                } else if(key.equals("h"))
                {
                    userInput.push('h');
                } else if(key.equals("i"))
                {
                    userInput.push('i');
                } else if(key.equals("j"))
                {
                    userInput.push('j');
                } else if(key.equals("k"))
                {
                    userInput.push('k');
                } else if(key.equals("l"))
                {
                    userInput.push('l');
                } else if(key.equals("m"))
                {
                    userInput.push('m');
                } else if(key.equals("n"))
                {
                    userInput.push('n');
                } else if(key.equals("o"))
                {
                    userInput.push('o');
                } else if(key.equals("p"))
                {
                    userInput.push('p');
                } else if(key.equals("q"))
                {
                    userInput.push('q');
                } else if(key.equals("r"))
                {
                    userInput.push('r');
                } else if(key.equals("s"))
                {
                    userInput.push('s');
                } else if(key.equals("t"))
                {
                    userInput.push('t');
                } else if(key.equals("u"))
                {
                    userInput.push('u');
                } else if(key.equals("v"))
                {
                    userInput.push('v');
                } else if(key.equals("w"))
                {
                    userInput.push('w');
                } else if(key.equals("x"))
                {
                    userInput.push('x');
                } else if(key.equals("y"))
                {
                    userInput.push('y');
                } else if(key.equals("z"))
                {
                    userInput.push('z');
                }
            }
        }
        
        if(!userInput.isEmpty()){
            userString = stackToString(userInput);
            
            uInputDsplay.updateText(userString);
        }
        
    }

    public String stackToString(Stack<Character> stack)
    {
        String str = "";
        for(char c : stack)
        {
            str = c + str;
        }
        return str;
    }
     
    public String generateString(ArrayList<String> list){    
        //Gets random number, then finds that index on the list of words on the url in reader.
        return list.get(Greenfoot.getRandomNumber(10000));
    }

}