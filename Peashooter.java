import java.awt.Point;
public class Peashooter extends Plant{
  private static final int FRAMES = 60;
  private int cooldownTimer = FRAMES;

  public Peashooter(Point cell){
    super(cell,100,300);
  }

  @Override
  public void update(){
  }

  @Override
  public void ability(){
    Point spawn = new Point(getPos());
    Projectile pea = new Projectile(spawn,5,20);
  }
}
