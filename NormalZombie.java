import processing.core.PApplet;
import java.awt.Point;
public class NormalZombie extends Zombie{
  private float speed = -0.27f;
  private int damageCooldown = 0;
  private static Game gameRef;
  private int damage = 100;


  public NormalZombie(Point start){
    super(start,180);
  };

  @Override
  public void update() {
    int[] cell = gameRef.getBoard().pixelToCell((int)getX(), (int)getY());
    Plant target = gameRef.getBoard().getPlant(cell);
    if (target != null) {
      if (damageCooldown <= 0) {
        target.takeDamage(damage);
        damageCooldown=60;
        if (target.getHealth() == 0) { 
          gameRef.getBoard().removePlant(cell);
      
        }
      }
    } else {
      addX(speed);
    }
    if (damageCooldown > 0) {
      damageCooldown--;
    }
  }
  @Override
  public void show(PApplet p) {
    p.fill(100, 150, 100);
    p.rect(getX() - 20, getY() - 40, 40, 80);
  }

  public static void setGame(Game g){
    gameRef = g;
  }
}
