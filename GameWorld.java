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
    private static int lives = 3;
    
    //data structures
    private ArrayList<String> wordList = new ArrayList<String>();
    private static Queue<Word> activeWords = new Queue<Word>();
    private static Stack<Character> userInput = new Stack<Character>();
    
    //instance variables
    public static int spawnTimer = 0;
    public static int moveTimer = 0;
    public static int pauseTimer = 0;
    public static boolean canMove = true;
    
    private int level;
    private int nextLevelExp;
    
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
        
        if (pauseTimer >= 10000) //When the word can't move, after a few seconds allow for the movement of words again
        {
            canMove = true;
            pauseTimer = 0; //Reset timer to 0
        }
        
        if(canMove){
            //spawns words every set amount of time, depending on the current level
            if(spawnTimer > 7500 - 500*level)  
            {
                addWord();
            }
            
            //moves all the words (aside from user input) every set amount of time, depending on the current level
            if(moveTimer>1000-(100*level))
            {
                moveWords(30);
            }
        }
        
        scoreBar.update(level, wordsTyped, lives, score); //updates the score bar at the top of the screen
        
        if(lives < 0) //If lives reach less than 0...
        {
            stopped();
            
            //reset the lives variable
            lives = 3;
            
            //Dequeue all the words and pop every letter in user input to prevent errors when replaying the game
            clearWorld();
            
            //Set world to GameOverWorld
            Greenfoot.setWorld(new GameOverWorld());
        }
        
        if(!canMove) //if the words are paused, update the pause timer, if not, then update the spawn and move timers
        {
            pauseTimer++; //Gradually increase the timer for when the words don't move
        } else {    
            spawnTimer++;
            moveTimer++;
        }
        checkUserInput(); //So that words can still be typed and entered after a word falls out of bounds
    }
    
    public void addWord() 
    {
        //Creates a word using a string from the url and adds it to the queue

        tempWord = new Word(generateString(wordList)); 
        addObject(tempWord,WORLD_WIDTH/2,75);
        activeWords.enqueue(tempWord);
        spawnTimer = 0;
    }
     
    public void moveWords(int distance)
    {
        //moves all the words that are in the active words queue
        //if one goes out of bounds,clear the world and queue, 
        //temporarily stop words from spawning and moving, 
        //and decrease player lives 
        for(Word word: activeWords)
        {
            word.move(distance);
        }
        if(!activeWords.isEmpty() && activeWords.getFirst().checkPosition()) // if the 'oldest' word goes out of bounds, get size is to prevent errors
        {
            //remove the oldest world from the world and queue
            clearWorld();
            if(level>1) //so that the game slows down a bit
            {
                level--;
            }
            canMove = false; //used to pause the spawning and movement of words
            lives--; //Reduce lives
        }
        
        moveTimer = 0; //resets the move timer
    }
    
    public void checkUserInput(){
        //make it so that all the letters are added to a string/stack or something, then when 'enter' send it through
        //other than that there shouldnt need to be any other user input?
        //everything else should be related to the mouse
        if(!activeWords.isEmpty() && Greenfoot.isKeyDown("enter"))
        {
            //compare userString with text of first in queue 
            if(activeWords.getFirst().getText().equals(userString)) //if correct,
            {
                removeObject(activeWords.getFirst());
                Word wordSize = activeWords.dequeue();
                
                wordsTyped++;
                
                score = score + wordSize.getLength() * 50; //increases score based on the length of the word
                
                canMove = true;
                
                //If it is time to level up (the amount of words typed reaches the right amount), then level up
                if(wordsTyped == nextLevelExp) //nextLevelExp is initially set to 5
                {
                    level++; //Level Increases
                    nextLevelExp = 5 * level; //Leveling up requirements (ex: level 3 requires 25 words typed before level 4)
                }
            }
            
            userString = userInput.popAll(); //clears the userInput word and user string
        } 
        else {
            String key = Greenfoot.getKey(); //gets user input for all the letters and backspace
            if(key!= null)
            {
                // Typing in a letter from a to z
                if (key.charAt(0) >= 97 && key.charAt(0) <= 122 && key.length() == 1)
                {
                     userInput.push(key.charAt(0));
                     typingSound();
                }
                // Entering backspace, and there is already userinput 
                else if(!userInput.isEmpty() && key.equals("backspace"))
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
        
        //updates the display and userString
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
    
    public void clearWorld()
    {
        for(Word word: activeWords){
            removeObject(word);
            activeWords.dequeue();
        }
        userInput.popAll();
    }
    
    public String generateString(ArrayList<String> list){    
        //Gets random number, then finds that index on the list of words on the url in reader.
        return list.get(Greenfoot.getRandomNumber(9894));
    }

}