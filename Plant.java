import java.awt.Point;;
public abstract class Plant{
  private int cost;
  private int health;
  private Point pos;

  public Plant(Point cell,int hp,int cost){
    this.pos=cell;
    this.health=hp;
    this.cost=cost;
  }
  public Point getPos(){
    return pos;
  }
  public int getCost(){
    return cost;
  }
  public int getHealth(){
    return health;
  }
  public void UpdateHealth(int damage){
    this.health -= damage;
  }
  public abstract void ability();
}
