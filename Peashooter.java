import processing.core.PApplet;
import java.awt.Point;
public class Peashooter extends Plant{
  // WHY IS FRAMES 81...
  // private static final int FRAMES = 81; gonna remove for now, trying to make a functioning game
  // private int cooldownTimer = FRAMES; gonna remove for now, trying to make a functioning game
  // private Board b; gonna remove for now, trying to make a functioning game

  private int cooldown = 0;
  private static Game gameRef; // need this for now to make it run properly

  public Peashooter(int row, int col){
    super(row,col);
  }

  public void update(){
    cooldown--;
    if(cooldown <= 0){
      Point peaStart = new Point(getCol() * 100 + 50, getRow() * 100 + 50 + 100);
      Pea pea = new Pea(peaStart);
      gameRef.addProjectile(pea);
      cooldown = 60;
    }
  }

  // need this for now to make it run properly
  public static void setGame(Game g){
    gameRef = g;
  }

  /* gonna remove for now, trying to make a functioning game
  @Override
  public void update(){
    // ADD METHOD TO CHECK IF BOARD HAS A ZOMOBIE IN IT
    while(cooldownTimer > 0 && b.rowHasZomb(getRow())){
      cooldownTimer--;
    }
    // if no zombie and cooldown timer is 0, leave it
    cooldownTimer = (int) Math.round((60) * (Math.random() * .15 + 1.35));
  }

  
  @Override
  public void ability(){
    Point spawn = new Point(getRow(),getCol());
    Projectile pea = new Projectile(spawn,5,20);
  }
  */

  @Override
  public void show(PApplet p){
    Point pos = getPos();
    p.fill(0, 255, 0);
    p.ellipse(getCol() * 100 + 50, getRow() * 100 + 50 + 100, 40, 40); 
  }
}
