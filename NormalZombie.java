import processing.core.PApplet;
import java.awt.Point;
public class NormalZombie extends Zombie{
  private float speed = 0.5f; // made slower for testing

  public NormalZombie(Point start){
    super(start,100); // 100 HP
  };

  @Override
  public void update() {
    addX(-speed);
  }
  @Override
  public void show(PApplet p) {
    p.fill(100, 150, 100);
    p.rect(getX() - 20, getY() - 40, 40, 80);
  }
}
