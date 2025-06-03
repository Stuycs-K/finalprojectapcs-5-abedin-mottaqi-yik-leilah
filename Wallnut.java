import processing.core.PApplet;
public class Wallnut extends Plant{

  public Wallnut(int row, int col){
    super(row,col,50,4000);
  }

  @Override
  public void update(){
  }

  @Override
  public void show(PApplet p){
    p.fill(88, 57, 39);
    p.ellipse(getCol() * 100 + 50, getRow() * 100 + 50 + 100, 40, 40);
  }
}
