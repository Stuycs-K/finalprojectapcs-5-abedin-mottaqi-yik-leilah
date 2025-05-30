import processing.core.PApplet;
import java.awt.Point;
public abstract class Plant implements Displayable{
  private int cost;
  private int health;
  private int[] pos;

  public Plant(int[] pos,int hp,int cost){
    this.pos=pos;
    this.health=hp;
    this.cost=cost;
  }
  public int[] getPos(){
    return pos;
  }
  public int getCost(){
    return cost;
  }
  public int getHealth(){
    return health;
  }
  public void updateHealth(int damage){
    if(damage>health){
      health=0;
    }
    this.health -= damage;
  }
  public abstract void update();
  public abstract void ability();
}
