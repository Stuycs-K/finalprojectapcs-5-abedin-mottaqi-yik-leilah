import processing.core.PApplet;
import java.awt.Point;
public class Projectile{
  private Point pos;
  private final float speed;
  private int damage;
  private boolean remove = false;

  public Projectile(Point start,float speed,int damage){
    this.pos=new Point(start);
    this.speed=speed;
    this.damage=damage;
  }
  public void update(){
    this.pos.move(pos.getX() + speed, pos.getY());
  }
  public void draw(){

  }
  public Point getPos(){
    return pos;
  }
  public int getDamage(){
    return damage;
  }
  public void removal(){
    remove = true;
  }
  public boolean getRemove(){
    return remove;
  }
}
