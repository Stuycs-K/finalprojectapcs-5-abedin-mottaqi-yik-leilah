import java.awt.Point;
public class Peashooter extends Plant{
  private static final int FRAMES = 81;
  private int cooldownTimer = FRAMES;
  private 

  public Peashooter(Point cell){
    super(cell,100,300);
  }

  @Override
  public void update(){
    while(cooldownTimer > 0 && ){
      cooldownTimer--;
    }
    cooldownTimer = (int) (60) * (Math.random() * .15 + 1.35);
  }

  @Override
  public void ability(){
    Point spawn = new Point(getPos());
    Projectile pea = new Projectile(spawn,5,20);
  }

  @Override
  public void show(){

  }
  @Override
  public void hide(){

  }
}
