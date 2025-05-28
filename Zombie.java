import java.awt.Point;
public abstract class Zombie implements Displayable{

    /*
     * notes:
     * - basic zombie moves one square every 5 seconds
     * - flag zombies move slightly faster than normal zombies
     */

    private int health;
    private final float speed;
    private Point pos;
    private final int damage;

    public Zombie(Point start,float speed,int health){
        this.pos=start;
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
    public Point getPos(){
        return pos;
    };

    public void move(){
      pos = pos.move(pos.getX() - 5, pos.getY());
    }

    public void eat(Plant target){
      target.updateHealth(damage);
    }

    private void die(){
      hide();
    }
}
