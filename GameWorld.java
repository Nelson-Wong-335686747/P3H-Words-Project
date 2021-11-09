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
    private static final int WORLD_WIDTH = 800;
    private static final int WORLD_HEIGHT= 700;
    private static int GAME_LIVES = 3;
    
    //data structures
    private ArrayList<String> wordList = new ArrayList<String>();
    private static Queue<Word> activeWords = new Queue<Word>();
    private static Stack<Character> userInput = new Stack<Character>();
    
    //instance variables
    private int timer = 0;
    private int canMoveTimer = 0;
    private int numMissed = 0;
    private boolean immune = false;
    private boolean canMove = true;
    
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
    
    public boolean checkPosition(Word w) //See if the words are out of bounds
    {
        //Checks if the words go out of bounds
        return w.getY() > GameWorld.WORLD_HEIGHT-35; 
    }
    
    public void act(){
        started(); //Music
        
        checkUserInput(); //User input
        
        if (canMoveTimer >= 120) //When the word can't move, after a few seconds allow for the movement of words again
        {
            canMove = true;
            canMoveTimer = 0; //Reset timer to 0
        }
        
        if(timer >= (int) (1.0/level * 100)) // Way to adjust timer accordingly with the level
        {

            if(canMove)
            {
                manageWords();
            }
            
            for(Word word: activeWords)
            {
                if(canMove)
                {
                    word.move();
                }
                
                if(checkPosition(word)) //If the words fall out of bounds..
                { 
                    activeWords.dequeue();  
                    this.removeObject(word);
                    
                    if(checkPosition(activeWords.getFirst()))
                    {
                        this.removeObject(activeWords.getFirst());
                    }
 
                    canMove = false;
                    
                    numMissed++;
                    
                    /*
                     * Allows for an invincibility to last until 3 words are fallen.
                     */   
                    if(numMissed == 3) 
                    {
                        immune = false; //After 3 words fall, invincibility is gone.
                    }
                    
                    if(!immune) //If immune is false
                    {
                        GameWorld.GAME_LIVES--; //Reduce lives
                        immune = true; //set immune to true
                        numMissed = 0; 
                    }
                    
                }
            }
        }
        
        scoreBar.update(level, wordsTyped, GAME_LIVES, score);  
        
        if(GAME_LIVES < 0) //If lives reach less than 0...
        {
            stopped();
            GAME_LIVES = 3;
            canMove = true;
            
            //Dequeue all the words and pop every letter in user input
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
        
        timer++;
        
        if (!canMove)
        {
            canMoveTimer++; //Fradually increase the timer for when the words don't move
        }
       
        checkUserInput(); //So that words can still be typed and entered after a word falls out of bounds

    }
    
    public void manageWords() //Creates more words and makes them fall down
    {
        timer = 0;
        tempWord = new Word(generateString(wordList)); 
        addObject(tempWord,WORLD_WIDTH/2,100);
        activeWords.enqueue(tempWord);
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
                
                canMove = true;
                
                //If it is time to level up (the amount of words typed reaches the right amount), then level up
                if(wordsTyped == nextLevelExp) //nextLevelExp is initially set to 5
                {
                    level++; //Level Increases
                    nextLevelExp = 10 + 5 * level; //Leveling up requirements (ex: level 3 requires 25 words typed before level 4)
                }
            }
            
            userString = userInput.popAll();
        } 
        else {
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