import processing.core.PApplet;
import java.awt.Point;
public class Peashooter extends Plant{
  // WHY IS FRAMES 81...
  private static final int FRAMES = 81;
  private int cooldownTimer = FRAMES;
  private Board b;

  public Peashooter(int[] pos){
    super(pos,300,100);
  }

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

  @Override
  public void show(){

  }
  @Override
  public void hide(){

  }
}
