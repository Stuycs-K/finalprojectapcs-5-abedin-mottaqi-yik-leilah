public abstract class Zombie implements Displayable{

    /*
     * notes:
     * - basic zombie moves one square every 5 seconds
     * - flag zombies move slightly faster than normal zombies
     */

    private int health;
    // private final float speed; gonna remove for now, trying to make a functioning game
    private float x,y;
    // private int row; gonna remove for now, trying to make a functioning game
    // private final int damage = 20; gonna remove for now, trying to make a functioning game

    public Zombie(Point pos,int health){
        this.x = pos.x;
        this.y = pos.y+100;
        this.health=health;
    };
    public void takeDamage(int dmg){
      health -= dmg;
    };
    public int getHealth(){
        return health;
    };

    public void addX(float s){
        x+=s;
    };

    public float getX(){
        return x;
    };

    public float getY(){
      return y;
    }
    
    public Point getPos(){
      return new Point((int)x,(int)y);
    }

    public abstract void update();
    public abstract void show();
}
