import processing.core.PApplet;
import processing.core.PImage;
public class Wallnut extends Plant{
  private PImage sprite;
  private String fullHealth = "wallnut_1.png";
  private String medHealth = "wallnut_2.png";
  private String lowHealth = "wallnut_3.png";

  public Wallnut(int row, int col){
    super(row,col,50,4000);
  }
  
  @Override
  public void update(){
  }

  @Override
  public void show(PApplet p){
    if (getHealth() >= 2700) {
      sprite = p.loadImage(fullHealth);
    } else if (getHealth() >= 1400) {
      sprite = p.loadImage(medHealth);
    } else {
      sprite = p.loadImage(lowHealth);
    }
    sprite.resize(75,75);
    p.image(sprite, (float)(getCol() * 100+10), (float)(getRow() * 100 + 10 + 100));
    // p.fill(88, 57, 39);
    // p.ellipse(getCol() * 100 + 50, getRow() * 100 + 50 + 100, 40, 40);
  }
}
