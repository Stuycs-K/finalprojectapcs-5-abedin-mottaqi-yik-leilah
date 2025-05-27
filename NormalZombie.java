public class NormalZombie extends Zombie{
  public Zombie(int row){
    startingHealh = 100;
    currHealth = 100;
    speed = 1.0;
    damage = 10;
    show();
  }
  public void updateHealth(int dmg){
    currHealth -= dmg;
  }

  public void move(){
    pos = pos.move(pos.getX() - 5, pos.getY());
  }

  public void eat(Plant target){
    target.updateHealth(damage);
  }

  private void die(){
    hide();
  }
}
