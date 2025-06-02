import processing.core.PApplet;
import java.awt.Point;
public class Peashooter extends Plant{
  private int shootCooldown = 0;
  private static Game gameRef; // need this for now to make it run properly
  private int damage = 20;

  public Peashooter(int row, int col){
    super(row,col,100,300);
  }

  public void update(){
    shootCooldown--;
    if (shootCooldown <= 0 && zombieInRow()) {
      shoot();
      shootCooldown = 86;
    }
  }

  private boolean zombieInRow() {
    for (Zombie z : gameRef.getZombies()) {
      int zombieRow = (int) ((z.getY()-100) / 100);
      if (zombieRow == getRow()) {
        return true;
      }
    }
    return false;
  }
  
  private void shoot(){
    Point peaStart = new Point(getCol() * 100 + 50, getRow() * 100 + 50 + 100);
    Pea pea = new Pea(peaStart, damage);
    gameRef.addProjectile(pea);
  }

  // need this for now to make it run properly
  public static void setGame(Game g){
    gameRef = g;
  }

  @Override
  public void show(PApplet p){
    Point pos = getPos();
    p.fill(0, 255, 0);
    p.ellipse(getCol() * 100 + 50, getRow() * 100 + 50 + 100, 40, 40); 
  }
}
