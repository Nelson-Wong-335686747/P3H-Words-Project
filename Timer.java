import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    private int cycle = 0;
    
    public Timer(){
    }
    
    public void act() 
    {
        cycle++;
    }    
    
    public int getCycle(){
        return cycle;
    }
    
    public void reset(){
        cycle = 0;
    }