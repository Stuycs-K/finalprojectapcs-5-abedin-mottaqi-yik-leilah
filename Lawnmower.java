import processing.core.PApplet;
import java.util.*;
import java.awt.Point;
public class Lawnmower implements Displayable{
  private float x, y;
  private boolean active = false;
  private boolean used = false;
  private final float speed = 5;
  private int row;

  public Lawnmower(int row){
    this.row = row;
    this.x = 0;
    this.y = row * 100 + 50 + 100;
  }

  public void trigger(){
    if (!used){
      active = true;
      used = true;
    }
  }

  public void update(ArrayList<Zombie> zombies){
    if (active){
      x+=speed;
      for(int i=zombies.size()-1; i>=0; i--) {
        Zombie z = zombies.get(i);
        int zombieRow = (int) ((z.getY()-100) / 100);
        if (zombieRow == row && Math.abs(z.getX() - x) < 40) {
          zombies.remove(i);
        }
      }
    }
  }

  public boolean isActive(){
    return active;
  }

  public boolean isUsed(){
    return used;
  }

  @Override
  public void show(PApplet p){
    if (!used || active){
      p.fill(200,0,0);
      p.rect(x,y-25,40,50); 
    }
  }

  @Override
  public Point getPos(){
    return new Point((int)x,(int)y);
  }
}
