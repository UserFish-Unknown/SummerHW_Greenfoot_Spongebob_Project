import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Allows the falling of the objects and controls the speed. If the object hits the "ground",
 * they will disappear. 
 * 
 * @author Chloe Barlow, Richie Shin
 * @version 4
 */
public class FallingObjects extends Actor
{
    /**
     * Act - do whatever the FallingObjects wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int dropSpeed = 1;
    private boolean onBottom = false;
    
    public void act()
    {
        fall();
    }
    
    private void fall(){
        setLocation(getX(), getY()+dropSpeed);
        
        if (!onBottom){
            this.setLocation(getX(), getY() + dropSpeed);
            onBottom = checkBottom();
        }
    }
    
    private boolean checkBottom(){
        if (getY() > 590){
            getWorld().removeObject(this);
            return true;
        }
        else{
            return false;
        }
    }
}
