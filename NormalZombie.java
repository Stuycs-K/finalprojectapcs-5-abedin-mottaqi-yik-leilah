import java.awt.Point;
public class NormalZombie extends Zombie{
  private static final int defaultHealth = 200;
  private static final float defaultSpeed = 1.0f;

  public NormalZombie(Point start){
    super(start, defaultSpeed, defaultHealth);
    show();
  };

  @Override
  public void show() {
    
  }
  @Override
  public void hide() {
    
  }
}
