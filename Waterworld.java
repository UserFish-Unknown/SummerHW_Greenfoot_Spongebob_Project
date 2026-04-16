import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class controls when and where the falling objects spawn in the game and spawns the 
 * "Player" (i.e. Spongebob). The class also has functions that manipulate the number of hearts that
 * are displayed on the screen for the user to reference to know how much health they have.
 * 
 * @author Chloe Barlow, Richie Shin
 * @version 4
 */

import java.util.ArrayList;

public class Waterworld extends World
{  
    public ArrayList<Health> numberOfHearts = new ArrayList<Health>();
    public int counter = 0;
    
    /**
     * Constructor for objects of class Waterworld.
     * 
     */
    public Waterworld()
    {    
        super(800, 600, 1); 
        prepare();
    }
    
    public void dropGold(){
        PirateGold g = new PirateGold();
        addObject(g, Greenfoot.getRandomNumber(getWidth()), 10);
    }
    
    public void dropTrap(){
        CrabTrap t = new CrabTrap();
        addObject(t, 50 + Greenfoot.getRandomNumber(getWidth() - 100), 10);
    }
    
    public void dropAnvil(){
        Anvil a = new Anvil();
        addObject(a, 50 + Greenfoot.getRandomNumber(getWidth() - 100), 10);
    }
    
    public void act(){
        if (Greenfoot.getRandomNumber(50) == 5){
            dropGold();
        }
        if (Greenfoot.getRandomNumber(150) == 20){
            dropTrap();
        }
        if (Greenfoot.getRandomNumber(400) == 50){
            dropAnvil();
        }
    }
    
    private void prepare(){
        Sbob sbob = new Sbob();
        addObject(sbob, 328, 537);
        sbob.setLocation(399, 527);
        
        for (int i = 0; i < 3; i++){
            Health heart = new Health();
            numberOfHearts.add(heart);
            addObject(heart, 10 + i * 20, 590);
        }
    }
    
    public void loseHeart(){
        if (numberOfHearts.size() > 1){
            for (Health heart : numberOfHearts){
                removeObject(heart);    
            }
            numberOfHearts.remove(numberOfHearts.size() - 2);
            int i = 0;
            for (Health heart : numberOfHearts){
                addObject(heart, 10 + i * 20, 590);
                i++;
            }
        }
        else if (numberOfHearts.size() == 1){
            for (Health heart : numberOfHearts){
                removeObject(heart);
            }
            numberOfHearts.remove(numberOfHearts.size() - 1);
        }
        if (numberOfHearts.isEmpty()){
            System.out.println("\u000C");
            System.out.println("GAMEOVER");
            Greenfoot.stop();
        }
    }

    public void loseHalfHeart(){
        if (numberOfHearts.size() > 0){
            if (counter % 2 == 0){
                Health lastHeart = numberOfHearts.get(numberOfHearts.size() - 1);
                lastHeart.setImage("half_heart.png");
                counter++;
            }
            else if (counter % 2 == 1){
                for (Health heart : numberOfHearts){
                removeObject(heart);    
                }
                numberOfHearts.remove(numberOfHearts.size() - 1);
                int i = 0;
                for (Health heart : numberOfHearts){
                    addObject(heart, 10 + i * 20, 590);
                    i++;
                }
                counter++;
            }
        }
        if (numberOfHearts.isEmpty()){
            System.out.println("\u000C");
            System.out.println("GAMEOVER");
            Greenfoot.stop();
        }
    }
    
    public void addHeart(){
        for (Health heart : numberOfHearts){
            removeObject(heart);    
        }
        Health extraHeart = new Health();
        numberOfHearts.add(0, extraHeart);
        int i = 0;
        for (Health heart : numberOfHearts){
            addObject(heart, 10 + i * 20, 590);
            i++;
        }
    }
}
