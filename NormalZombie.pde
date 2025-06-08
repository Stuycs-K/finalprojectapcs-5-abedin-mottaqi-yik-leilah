import java.awt.Point;
public class NormalZombie extends Zombie{
  private float speed = -0.27f;
  private int damageCooldown = 0;
  private int damage = 100;
  private PImage sprite;
  Game game;

  private String fullHealth = "normalzombie_1.png";
  private String medHealth = "normalzombie_2.png";
  private String lowHealth = "normalzombie_3.png";


  public NormalZombie(Point start, Game game){
    super(start,180);
    this.game = game;
  };

  @Override
  public void update() {
    int[] cell = game.getBoard().pixelToCell((int)getX(), (int)getY());
    Plant target = game.getBoard().getPlant(cell);
    if (target != null) {
      if (damageCooldown <= 0) {
        target.takeDamage(damage);
        damageCooldown=60;
        if (target.getHealth() == 0) { 
          game.getBoard().removePlant(cell);
      
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
  public void show() {
    if (getHealth() >= 120) {
      sprite = loadImage(fullHealth);
    } else if (getHealth() >= 60) {
      sprite = loadImage(medHealth);
    } else {
      sprite = loadImage(lowHealth);
    }
    image(sprite, (getX() - 20), (getY() - 60));
  }
}