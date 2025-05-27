import java.awt.Point;
public abstract class Zombie{
    private int health;
    private final float speed;
    private Point pos;
    private boolean remove = false;

    public Zombie(Point start,float speed,int health){
        this.pos=start;
        this.speed=speed;
        this.health=health;
    };
    public void update(){};
    public void takeDamage(int dmg){
        if (dmg>health){
            health=0;
        };
        health-=dmg;
        if (health==0){
            remove = true;
        };
    };
    public int getHealth(){
        return health;
    };
    public Point getPos(){
        return pos;
    };
    public boolean getRemove(){
        return remove;
    };
    public abstract void draw();
}
