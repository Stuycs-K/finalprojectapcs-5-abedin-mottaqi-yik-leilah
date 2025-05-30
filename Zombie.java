import processing.core.PApplet;
import java.awt.Point;
import processing.core.PApplet;
public abstract class Zombie implements Displayable{

    /*
     * notes:
     * - basic zombie moves one square every 5 seconds
     * - flag zombies move slightly faster than normal zombies
     */

    private int health;
    private final float speed;
    private double pos;
    private int row;
    private final int damage = 20;
    private PApplet p;

    public Zombie(int row,float speed,int health){
        this.row = row;
        this.pos = p.width;
        this.speed=speed;
        this.health=health;
    };
    public void takeDamage(int dmg){
        if (dmg>health){
            health=0;
        };
        health-=dmg;
        if (health==0){
            hide();
        };
    };
    public int getHealth(){
        return health;
    };
    public double getPos(){
        return pos;
    };

    public int getRow(){
      return row;
    }

    public void move(){
      pos -= speed;
    }

    public void eat(Plant target){
      target.updateHealth(damage);
    }

    private void die(){
      hide();
    }
}
