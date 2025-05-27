import java.awt.Point;
public abstract class Zombie extends Displayable{
    private int health;
    private final float speed;
    private Point pos;

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
            hide();
        };
    };
    public int getHealth(){
        return health;
    };
    public Point getPos(){
        return pos;
    };
    public abstract void draw();
}
