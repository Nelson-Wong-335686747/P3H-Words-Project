import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * GameWorld 
 * -Where the game begins
 * 
 * Credit
 * -HarryStylesWatermelonSugar --Youtube Title--> Harry Styles - Watermelon Sugar (Official Video) --Link--> https://www.youtube.com/watch?v=E07s5ZYygMg
 * -Type Sound --YouTubeTitle --> Keyboard Typing Sound Effect --Link--> https://www.youtube.com/watch?v=RLTUMyiLVZE
 */
public class GameWorld extends World
{
    //Sounds
    private GreenfootSound GameMusic = new GreenfootSound("HarryStylesWatermelonSugar.mp3");
    private GreenfootSound TypeSound = new GreenfootSound("Type Sound.mp3");
    
    //world variables
    public static final int WORLD_WIDTH = 800;
    public static final int WORLD_HEIGHT= 700;
    
    
    //data structures
    private ArrayList<String> wordList = new ArrayList<String>();
    private static Queue<Word> activeWords = new Queue<Word>();
    private static Stack<Character> userInput = new Stack<Character>();
    
    //instance variables
    private int spawnTimer = 0;
    private int moveTimer = 0;
    
    public static int level;
    private int nextLevelExp;
    
    private int lives = 3;
    private int wordsTyped;
    private int score;
    
    public String userString = "";
    
    //Objects
    private Word tempWord;
    private Word uInputDisplay;
    
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
        
        //Add's the scoreBar
        scoreBar = new ScoreBar(800);
        addObject(scoreBar, 400, 15);
        
        //Makes the first word fall and enqueues it
        Word test = new Word(generateString(wordList));
        addObject(test, WORLD_WIDTH/2, WORLD_HEIGHT/2);
        activeWords.enqueue(test);
        
        //Add's the object that display's the user's text
        uInputDisplay = new Word(userString);
        addObject(uInputDisplay,WORLD_WIDTH/2,685);
        
        //Setting starting level and amount of words needed to be typed before next level
        level = 1;
        nextLevelExp = 5;
    }
    
    public void started() //Method starts music
    {
        GameMusic.setVolume(40);
        GameMusic.playLoop();
    }
    
    public void stopped() //Method stops the music
    {
        GameMusic.pause();
    }
    
    public void typingSound() //Method for a singular keyboard type sound
    {
        TypeSound.setVolume(100);
        TypeSound.play();
    }
    
    
    public void act(){
        started(); //Music
        
        checkUserInput(); //User input
        
        //spawns words every set amount of time, depending on the current level
        if(spawnTimer > (int) (7500 * 1/level)) 
        {
            addWord();
        }
        
        //moves all the words (aside from user input) every set amount of time, depending on the current level
        if(moveTimer>1000-(100*level)) 
        {
            moveWords(30);
        }
        
        scoreBar.update(level, wordsTyped, lives, score);  
        
        if(lives < 0) //If lives reach less than 0...
        {
            stopped();
            lives = 3;
            
            //Dequeue all the words and pop every letter in user input to prevent errors when replaying the game
            for(Word word: activeWords){
                removeObject(word);
                activeWords.dequeue();
            }
            
            for(char c : userInput){
                userInput.pop();
            } 
            
            //Set world to GameOverWorld
            Greenfoot.setWorld(new GameOverWorld());
        }
        
        spawnTimer++;
        moveTimer++;
        checkUserInput(); //So that words can still be typed and entered after a word falls out of bounds
    }
    
    public void addWord() 
    {
        //Creates a word using a string from the url and adds it to the queue
        spawnTimer = 0;
        tempWord = new Word(generateString(wordList)); 
        addObject(tempWord,WORLD_WIDTH/2,75);
        activeWords.enqueue(tempWord);
    }
     
    public void moveWords(int distance)
    {
        //moves all the words that are in the active words queue
        //if one goes out of bounds, remove it from the world and queue, decrease the level by three (if less than three, then reset), 
        //and decrease player lives 
        for(Word word: activeWords)
        {
            word.move(distance);
            
            if(word.checkPosition()) 
            { 
                removeObject(word);
                activeWords.dequeue();  
                if(level>2)
                {
                    level-=3;
                } else {
                    level = 1;
                }
                lives--; //Reduce lives
            }
        }
        moveTimer = 0;
    }
    
    public void checkUserInput(){
        //make it so that all the letters are added to a string/stack or something, then when 'enter' send it through
        //other than that there shouldnt need to be any other user input?
        //everything else should be related to the mouse
        if(activeWords.getSize() > 0 && Greenfoot.isKeyDown("enter"))
        {
            //compare userString with text of first in queue 
            if(activeWords.getFirst().getText().equals(userString))
            {
                removeObject(activeWords.getFirst());
                Word wordSize = activeWords.dequeue();
                
                wordsTyped++;
                
                score = score + wordSize.getLength() * 50;
                
                //If it is time to level up (the amount of words typed reaches the right amount), then level up
                if(wordsTyped == nextLevelExp) //nextLevelExp is initially set to 5
                {
                    level++; //Level Increases
                    nextLevelExp = 10 + 5 * level; //Leveling up requirements (ex: level 3 requires 25 words typed before level 4)
                }
            }
            userString = userInput.popAll();
        }else {
            String key = Greenfoot.getKey();
            if(key!= null)
            {
                // Typing in a letter from a to z
                if (key.charAt(0) >= 97 && key.charAt(0) <= 122 && key.length() == 1)
                {
                     userInput.push(key.charAt(0));
                     typingSound();
                }
                // Entering backspace, and there is already userinput 
                else if(userInput.getSize() > 0 && key.equals("backspace"))
                {
                    userInput.pop();
                    typingSound();
                }
                // Entering backspace, and there isn't userinput, and nothing is being typed
                else
                {
                    typingSound();    
                }   
            }
        } 
        
        userString = stackToString(userInput);
        uInputDisplay.updateText(userString);
        
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
        return list.get(Greenfoot.getRandomNumber(9894));
    }

}