import java.awt.Point;
public class NormalZombie extends Zombie{
  private static final int defaultHealth = 200;
  private static final float defaultSpeed = 1.0f;

  public NormalZombie(int row){
    super(row, defaultSpeed, defaultHealth);
    show();
  };

  @Override
  public void show() {
    
  }
  @Override
  public void hide() {
    
  }
}
