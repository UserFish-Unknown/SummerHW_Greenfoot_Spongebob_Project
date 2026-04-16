import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class dictates how Player (Spongebob) moves, monitors how much gold they have, and
 * checks if they are hit by any falling objects (and plays the corresponding sound effects). The
 * class also has a key press that opens up the shop where the Player can use the gold they have
 * collected and buy upgrades with it. To close the shop, the Player has to input 0 to resume gameplay.
 * 
 * @author Chloe Barlow, Richie Shin 
 * @version 4
 */

import java.util.Scanner;

public class Sbob extends Actor
{
    /**
     * Act - do whatever the Sbob wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public int goldAmount = 0;
    public int walkSpeed = 1;
    
    private int speedBoostCost = 10;
    private int extraHeartCost = 50;
    
    public void act()
    {
        if (canCatchGold()){
            CatchGold();
            goldAmount = goldAmount + Greenfoot.getRandomNumber(20);
        }
        
        if (canBeHit()){
            getHit();
        }
        
        if (Greenfoot.isKeyDown("d")){
            setLocation(getX() + walkSpeed, getY());
        }
        
        if (Greenfoot.isKeyDown("a")){
            setLocation(getX() - walkSpeed, getY());
        }
        
        if (Greenfoot.isKeyDown("space")){
            openShop();
        }
    }
    
    private boolean canCatchGold(){
        Actor gold = getOneObjectAtOffset(0, 0, PirateGold.class);
        if (gold != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void CatchGold(){
        Actor gold = getOneObjectAtOffset(0, 0, PirateGold.class);
        if (gold != null){
            Greenfoot.playSound("cha_ching.wav");
            getWorld().removeObject(gold);
        }
    }
    
    private boolean canBeHit(){
        Actor anvil = getOneObjectAtOffset(0, 1, Anvil.class);
        Actor crabTrap = getOneObjectAtOffset(0, 1, CrabTrap.class);
        if (anvil != null || crabTrap != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void getHit(){
        Waterworld world = (Waterworld) getWorld();
        
        Actor anvil = getOneObjectAtOffset(0, 1, Anvil.class);
        Actor crabTrap = getOneObjectAtOffset(0, 1, CrabTrap.class);
        if (anvil != null){
            Greenfoot.playSound("metal_pipe.mp3");
            getWorld().removeObject(anvil);
            world.loseHeart();
        }
        else if (crabTrap != null){
            Greenfoot.playSound("bonk.wav");
            getWorld().removeObject(crabTrap);
            world.loseHalfHeart();
        }
    }
    
    public void openShop(){
        Waterworld world = (Waterworld) getWorld();
        
        System.out.println("\u000C");
        
        System.out.println("\n------------------------------------------");
        System.out.println("\nWelcome to the shop!\n\nHere you can use the gold you've collected and buy\nupgrades such as increasing your walk speed and\nincreasing your health!\n");
        System.out.println("Enter '1' if you would like a speed upgrade.\nEnter '2' if you would like some extra health.\n\nIf you would like to return to the game, enter '0'.\n");
        System.out.println("Your total amount of gold: " + goldAmount + "\n");
        System.out.println("1: Speed Boost | " + speedBoostCost);
        System.out.println("2: Extra Heart | " + extraHeartCost);
        
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        try {
            while (exit != true) {
                        int response = scanner.nextInt();
                        if (response == 1){
                            if (goldAmount >= speedBoostCost){
                                walkSpeed += 1;
                                System.out.println("Your new speed is: " + walkSpeed);
                                goldAmount -= speedBoostCost;
                                speedBoostCost += 10;
                                System.out.println("\u000C");
        
                                System.out.println("\n------------------------------------------");
                                System.out.println("\nWelcome to the shop!\n\nHere you can use the gold you've collected and buy\nupgrades such as increasing your walk speed and\nincreasing your health!\n");
                                System.out.println("Enter '1' if you would like a speed upgrade.\nEnter '2' if you would like some extra health.\n\nIf you would like to return to the game, enter '0'.\n");
                                System.out.println("Your total amount of gold: " + goldAmount + "\n");
                                System.out.println("1: Speed Boost | " + speedBoostCost);
                                System.out.println("2: Extra Heart | " + extraHeartCost);
                            }
                            else{
                                System.out.println("Sorry, you do not have enough gold to buy this.\n");
                                response = scanner.nextInt();
                            }
                        }
                        else if (response == 2){
                            if (goldAmount >= extraHeartCost){
                                world.addHeart();
                                System.out.println("Your health has increased!");
                                goldAmount -= extraHeartCost;
                                extraHeartCost += 50;
                                System.out.println("\u000C");
        
                                System.out.println("\n------------------------------------------");
                                System.out.println("\nWelcome to the shop!\n\nHere you can use the gold you've collected and buy\nupgrades such as increasing your walk speed and\nincreasing your health!\n");
                                System.out.println("Enter '1' if you would like a speed upgrade.\nEnter '2' if you would like some extra health.\n\nIf you would like to return to the game, enter '0'.\n");
                                System.out.println("Your total amount of gold: " + goldAmount + "\n");
                                System.out.println("1: Speed Boost | " + speedBoostCost);
                                System.out.println("2: Extra Heart | " + extraHeartCost);
                            }
                            else{
                                System.out.println("Sorry, you do not have enough gold to buy this.\n");
                                response = scanner.nextInt();
                            }
                        }
                        else if (response ==0){
                            break;
                        }
                        else {
                        System.out.println("Invalid input, please try again");
                        }
            } 
        }catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again");
        }finally{ 
            scanner.close();
        }
    }
}
