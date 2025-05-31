import processing.core.PApplet;
import java.awt.Point;
public abstract class Projectile implements Displayable{
  private float x,y;
  private float speed;
  private int damage;
  private boolean remove = false;

  public Projectile(Point start,float speed,int damage){
    this.x=start.x;
    this.y=start.y;
    this.speed=speed;
    this.damage=damage;
  }
  public Point getPos(){
    return new Point((int)x,(int)y);
  }

  public int getDamage(){
    return damage;
  }

  public void markRemoval(){
    remove = true;
  }

  public boolean shouldRemove(){
    return remove;
  }

  public abstract void update();
  public abstract void show(PApplet p);
}
