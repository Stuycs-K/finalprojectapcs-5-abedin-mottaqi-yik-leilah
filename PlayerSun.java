public class PlayerSun{
  private int balance = 50; // renamed sunAmount to balance b/c it sounds better

  public int getBalance(){
    return balance;
  }
  public void addSun(int sunAmount){
    this.balance+=sunAmount;
  }
  public boolean spendSun(int cost){
    if(balance>=cost){
        this.balance-=cost;
        return true;
    }
    // make the sun thingy turn red and angry
    return false;
  }

  public void reset(){
    this.balance = 50;
  }
}
