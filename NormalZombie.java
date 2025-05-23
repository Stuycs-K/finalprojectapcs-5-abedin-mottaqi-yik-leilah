public class NormalZombie extends Zombie{
  public Zombie(int row){
    startingHealh = 100;
    currHealth = 100;
    speed = 1.0;
    damage = 10;
  }
  public void updateHealth(int dmg){
    currHealth -= dmg;
  }

  public void move(){
    pos =
  }

  public void eat(Plant target){
    target.updateHealth(damage);
  }

  private
}
