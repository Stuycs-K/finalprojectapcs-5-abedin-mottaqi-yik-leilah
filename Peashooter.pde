import java.awt.Point;
public class Peashooter extends Plant{
  private int shootCooldown = 0;
  Game game; // need this for now to make it run properly
  private int damage = 20;
  private PImage sprite;
  private String fullHealth = "peashooter_1.png";
  private String medHealth = "peashooter_2.png";
  private String lowHealth = "peashooter_3.png";

  public Peashooter(int row, int col, Game game){
    super(row,col,100,300);
    this.game = game;
  }

  public void update(){
    shootCooldown--;
    if (shootCooldown <= 0 && zombieInRow()) {
      shoot();
      shootCooldown = 86;
    }
  }

  private boolean zombieInRow() {
    for (Zombie z : game.getZombies()) {
      int zombieRow = (int) ((z.getY()-100) / 100);
      if (zombieRow == getRow()) {
        return true;
      }
    }
    return false;
  }

  private void shoot(){
    Point peaStart = new Point(getCol() * 100 + 75, getRow() * 100 + 25 + 100);
    Pea pea = new Pea(peaStart, damage);
    game.addProjectile(pea);
  }

  @Override
  public void show(){
    if (getHealth() == 300) {
      sprite = loadImage(fullHealth);
    } else if (getHealth() == 200) {
      sprite = loadImage(medHealth);
    } else {
      sprite = loadImage(lowHealth);
    }
    sprite.resize(75,75);
    image(sprite, (float)(getCol() * 100+10), (float)(getRow() * 100 + 10 + 100));
    //Point pos = getPos();
    //fill(0, 255, 0);
    //ellipse(getCol() * 100 + 50, getRow() * 100 + 50 + 100, 40, 40);
  }
}
