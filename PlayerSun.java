public class PlayerSun{
  private int sunAmount;
  public PlayerSun(int sunAmount){
    this.sunAmount=0; //idk how much sun you start with in pvz
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
    return false;
  }
  public boolean canAfford(int cost){
    return sunAmount>=cost;
  }
}