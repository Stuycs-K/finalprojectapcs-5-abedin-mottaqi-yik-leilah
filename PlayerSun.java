public class PlayerSun{
  private int sunAmount;
  public PlayerSun(){sunAmount = 0;}
  public PlayerSun(int sunAmount){
    this.sunAmount=sunAmount; //idk how much sun you start with in pvz
    // its variable but usually 0 so
  }
  public int getSunAmount(){
    return sunAmount;
  }
  public void addSun(int sunAmount){
    this.sunAmount+=sunAmount;
  }
  public boolean spendSun(int cost){
    if(sunAmount>=cost){
        this.sunAmount-=cost;
        return true;
    }
    // make the sun thingy turn red and angry
    return false;
  }
  // why do you have this and then not use it bruh
  public boolean canAfford(int cost){
    return sunAmount>=cost;
  }
}