import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Point;
public class Sunflower extends Plant {
    private int cooldown = 0; // renamed for consistency
    private static Game gameRef; // need this for now to make it run properly
    private PImage sprite;
    private String fullHealth = "sunflower_1.png";
    private String medHealth = "sunflower_2.png";
    private String lowHealth = "sunflower_3.png";

    public Sunflower(int row, int col){
      super(row,col,50,300);
    }

    @Override
    public void update(){
      cooldown--;
      if(cooldown <= 0){
        int px = getCol() * 100 + 50;
        int py = getRow() * 100 + 50 + 100;
        gameRef.spawnSun(new Point(px, py));
        cooldown = 1440; // every 24 seconds
      }
    }
    @Override
    public void show(PApplet p){
      if (getHealth() == 300) {
        sprite = p.loadImage(fullHealth);
      } else if (getHealth() == 200) {
        sprite = p.loadImage(medHealth);
      } else {
        sprite = p.loadImage(lowHealth);
      }
      sprite.resize(75,75);
      p.image(sprite, (float)(getCol() * 100+10), (float)(getRow() * 100 + 10 + 100));
      // p.fill(255, 204, 0);
      // p.ellipse(getCol() * 100 + 50, getRow() * 100 + 50 + 100, 40, 40);
    }

    // need this for now to make it run properly
    public static void setGame(Game g){
      gameRef = g;
    }
}
