abstract class Plant{
  private int cost;
  private int startingHealth;
  private int currHealth;
  private Position pos;

  public Plant(int hp,int cost){
    startingHealth=hp;
    currHealth=hp;
    this.cost=cost;
  }

  public void UpdateHealth(int damage){
    currHealth -= damage;
  }

  public int getHP() {
    return currHealth;
  }

  public abstract void ability();
}
