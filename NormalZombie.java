import java.awt.Point;
public class NormalZombie extends Zombie{
<<<<<<< HEAD
  private static final int defaultHealth = 200;
  private static final float defaultSpeed = 1.0f;

  public NormalZombie(Point start){
    super(start,defaultSpeed,defaultHealth);
  };
  @Override
  public void draw(){};
=======
  public Zombie(int row){
    startingHealh = 100;
    currHealth = 100;
    speed = 1.0;
    damage = 10;
    show();
  }
  public void updateHealth(int dmg){
    currHealth -= dmg;
  }

  public void move(){
    pos = pos.move(pos.getX() - 5, pos.getY());
  }

  public void eat(Plant target){
    target.updateHealth(damage);
  }

  private void die(){
    hide();
  }
>>>>>>> lyik60
}
