import processing.core.PApplet;
import processing.core.PImage;
public class Wallnut extends Plant{
  private PImage full;

  public Wallnut(int row, int col){
    super(row,col,50,4000);
  }

  @Override
  public void update(){
  }

  @Override
  public void show(PApplet p){
    full = p.loadImage("wallnut_1.png");
    full.resize(75,75);
    p.image(full, (float)(getCol() * 100+10), (float)(getRow() * 100 + 10 + 100));
    // p.fill(88, 57, 39);
    // p.ellipse(getCol() * 100 + 50, getRow() * 100 + 50 + 100, 40, 40);
  }
}
