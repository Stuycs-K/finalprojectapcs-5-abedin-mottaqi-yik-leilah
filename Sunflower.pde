import java.awt.Point;
public class Sunflower extends Plant {
    private int cooldown = 0; // renamed for consistency
    Game game; // need this for now to make it run properly
    private PImage sprite;
    private String fullHealth = "sunflower_1.png";
    private String medHealth = "sunflower_2.png";
    private String lowHealth = "sunflower_3.png";

    public Sunflower(int row, int col, Game game){
      super(row,col,50,300);
      this.game = game;
    }

    @Override
    public void update(){
      cooldown--;
      if(cooldown <= 0){
        int px = getCol() * 100 + 50;
        int py = getRow() * 100 + 50 + 100;
        game.spawnSun(new Point(px, py));
        cooldown = 1440; // every 24 seconds
      }
    }
    @Override
    public void show(){
      if (getHealth() == 300) {
        sprite = loadImage(fullHealth);
      } else if (getHealth() == 200) {
        sprite = loadImage(medHealth);
      } else {
        sprite = loadImage(lowHealth);
      }
      sprite.resize(75,75);
      image(sprite, (float)(getCol() * 100+10), (float)(getRow() * 100 + 10 + 100));
      // fill(255, 204, 0);
      // ellipse(getCol() * 100 + 50, getRow() * 100 + 50 + 100, 40, 40);
    }
}
